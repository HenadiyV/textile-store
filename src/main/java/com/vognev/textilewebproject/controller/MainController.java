package com.vognev.textilewebproject.controller;

/**
 * textilewebproject  17/09/2021-17:30
 */

//import com.vognev.textilewebproject.model.Message;
import com.vognev.textilewebproject.model.MyUser;
//import com.vognev.textilewebproject.repository.MessageRepository;
import com.vognev.textilewebproject.model.Product;
import com.vognev.textilewebproject.model.dto.ProductDto;
import com.vognev.textilewebproject.model.util.MyCookies;
import com.vognev.textilewebproject.repository.ProductRepository;
import com.vognev.textilewebproject.service.BasketService;
import com.vognev.textilewebproject.service.ProductService;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
    public String greeting(@CookieValue(value = "textile-basket", required = false) String cookieValue,  Model model) {

        List<Product> productList = productService.findAll();

        model.addAttribute("productList", productList);
        model.addAttribute("col_product",productService.colProduct());
        model.addAttribute("sumPurchace",productService.warehouseDto());
        model.addAttribute("col_user",userService.colUsers());
        System.out.println(cookieValue);
        basketService.deleteRancidCookies();
//        String cookieValue=MyCookies.getMyCookies(request);
//       ;HttpServlet serv,
        if(cookieValue!=null&&!cookieValue.isEmpty()){
            //System.out.println(serv.getServletInfo());

        Long basketId=basketService.getBasketProductIdToToken(cookieValue);
        if(basketId>0){
            model.addAttribute("cookie",cookieValue);
            model.addAttribute("basketId",basketId);
        }

        }
        return "index";
    }


    @GetMapping("/zbir")
    public String zbirList(Model model){

        model.addAttribute("productList", productService.getProductZbirList(1));

        return "index";
    }


    @GetMapping("/lito")
    public String litoList(Model model){

        model.addAttribute("productList", productService.getProductZbirList(2));

        return "index";
    }


    @GetMapping("/zima")
    public String zimaList(Model model){

        model.addAttribute("productList", productService.getProductZbirList(3));

        return "index";
    }

    @GetMapping("zamovlenja")
     public String zamovlenja(Model model){
        return "index";
    }

}