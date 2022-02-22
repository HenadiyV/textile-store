package com.vognev.textilewebproject.controller;

/**
 * textilewebproject  17/09/2021-17:30
 */

//import com.vognev.textilewebproject.model.Message;
//import com.vognev.textilewebproject.repository.MessageRepository;
import com.vognev.textilewebproject.dto.BasketDto;
import com.vognev.textilewebproject.model.Product;
        import com.vognev.textilewebproject.repository.ProductRepository;
import com.vognev.textilewebproject.service.BasketService;
import com.vognev.textilewebproject.service.ProductService;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
        import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String greeting(
            @CookieValue(value = "textile-basket", required = false) String cookieValue,
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)  Pageable pageable,
            HttpServletRequest request
            ){
        Page<Product> productList = productService.getProductListAllPageable(pageable);

        model.addAttribute("page", productList);
        model.addAttribute("col_product",productService.colProduct());
        model.addAttribute("url","/");
        model.addAttribute("col_user",userService.colUsers());

        if(cookieValue!=null&&!cookieValue.isEmpty()){

        Long basketId=basketService.getBasketProductIdToToken(cookieValue);
            userService.sendAdmin(new BasketDto());
        if(basketId>0){
            model.addAttribute("cookie",cookieValue);
            model.addAttribute("basketId",basketId);
        }
        }
        String userIP = request.getRemoteAddr();
        String br = request.getHeader("User-Agent");
       // String method = request.getMethod();
        String requestedSessionId = request.getRequestedSessionId();
       // String requestURI = request.getRequestURI();
//        String authType = request.getAuthType();
//        String contentType = request.getContentType();
//        String contextType = request.getContextPath();
//        String pathInfo = request.getPathInfo();
//        String queryString = request.getQueryString();
//        String remoteUser = request.getRemoteUser();


        String locale = request.getLocale().getCountry();
      //  String localAddr = request.getLocalAddr();
       // String localName = request.getLocalName();
      //  String localHost = request.getRemoteHost();
      //  String characterEncoding = request.getCharacterEncoding();
       // int serverPort = request.getServerPort();
       // int remotePort = request.getRemotePort();
       // String displayName = request.getLocale().getDisplayName();
//        String displayScript = request.getLocale().getDisplayScript();
//        String script = request.getLocale().getScript();
       // String iSO3Country = request.getLocale().getISO3Country();
      //  String iSO3Language = request.getLocale().getISO3Language();
       // String varia = request.getLocale().getVariant();
       // String scheme = request.getScheme();
       // String pathTranslated = request.getPathTranslated();
       // String cookN = request.getCookies()[0].getName();
       // String cookV = request.getCookies()[0].getValue();


        System.out.println("IP :"+userIP);
        System.out.println("User-Agent :"+br);
        System.out.println("requestedSessionId :"+requestedSessionId);
        System.out.println("locale :"+locale);


      // System.out.println("method :"+method);

//        System.out.println("authType :"+authType);
//        System.out.println("contentType :"+contentType);
//        System.out.println("contextType :"+contextType);
//        System.out.println("pathInfo :"+pathInfo);
//        System.out.println("queryString :"+queryString);
//        System.out.println("remoteUser :"+remoteUser);

      //  System.out.println("requestURI :"+requestURI);


       // System.out.println("localAddr :"+localAddr);
       // System.out.println("localName :"+localName);
       // System.out.println("localHost :"+localHost);
       // System.out.println("characterEncoding :"+characterEncoding);
       // System.out.println("serverPort :"+serverPort);
       // System.out.println("remotePort :"+remotePort);
       // System.out.println("displayName :"+displayName);
//        System.out.println("displayScript :"+displayScript);
//        System.out.println("script :"+script);
    //    System.out.println("iSO3Country :"+iSO3Country);
      //  System.out.println("iSO3Language :"+iSO3Language);
       // System.out.println("variant :"+varia);
      //  System.out.println("scheme :"+scheme);
      //  System.out.println("pathTranslated :"+pathTranslated);
      //  System.out.println("cookN :"+cookN);
       // System.out.println("cookV :"+cookV);
        return "index";
    }


    @GetMapping("/zbir")
    public String zbirList(
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable
    ){
        model.addAttribute("page", productService.getProductZbirList(1,pageable));
        model.addAttribute("url", "/zbir");

        return "index";
    }


    @GetMapping("/lito")
    public String litoList(
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ){
        model.addAttribute("productList", productService.getProductZbirList(2,pageable));

        return "index";
    }


    @GetMapping("/zima")
    public String zimaList(
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable
    ){
        model.addAttribute("productList", productService.getProductZbirList(3,pageable));

        return "index";
    }

    @GetMapping("zamovlenja")
     public String zamovlenja(Model model){
        return "index";
    }

}
//==================MainController ==============
