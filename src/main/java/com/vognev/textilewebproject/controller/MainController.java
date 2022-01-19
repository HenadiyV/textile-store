package com.vognev.textilewebproject.controller;

/**
 * textilewebproject  17/09/2021-17:30
 */

//import com.vognev.textilewebproject.model.Message;
import com.vognev.textilewebproject.model.MyUser;
//import com.vognev.textilewebproject.repository.MessageRepository;
import com.vognev.textilewebproject.model.Product;
import com.vognev.textilewebproject.model.dto.ProductDto;
import com.vognev.textilewebproject.model.dto.ProductPageDto;
import com.vognev.textilewebproject.model.util.Constants;
import com.vognev.textilewebproject.model.util.MyCookies;
import com.vognev.textilewebproject.repository.ProductRepository;
import com.vognev.textilewebproject.service.BasketService;
import com.vognev.textilewebproject.service.ProductService;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String greeting(
            @CookieValue(value = "textile-basket", required = false) String cookieValue,
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)  Pageable pageable
            ){
        Page<Product> productList = productService.getProductListAllPageable(pageable);

        model.addAttribute("page", productList);
        model.addAttribute("col_product",productService.colProduct());
        model.addAttribute("url","/");
        model.addAttribute("col_user",userService.colUsers());

        if(cookieValue!=null&&!cookieValue.isEmpty()){

        Long basketId=basketService.getBasketProductIdToToken(cookieValue);

        if(basketId>0){
            model.addAttribute("cookie",cookieValue);
            model.addAttribute("basketId",basketId);
        }
        }
        return "index";
    }


    @GetMapping("/zbir")
    public String zbirList(
            Model model,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable
    ){
        model.addAttribute("productList", productService.getProductZbirList(1,pageable));

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
