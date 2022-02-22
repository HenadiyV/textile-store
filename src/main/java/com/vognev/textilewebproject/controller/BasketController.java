package com.vognev.textilewebproject.controller;


import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.dto.BasketDto;
import com.vognev.textilewebproject.dto.BasketProductDto;
import com.vognev.textilewebproject.util.MyCookies;
import com.vognev.textilewebproject.service.BasketProductService;
import com.vognev.textilewebproject.service.BasketService;
import com.vognev.textilewebproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * textilewebproject_3  29/12/2021-14:58
 */
@Controller
@RequestMapping("basket")
public class BasketController extends HttpServlet {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketProductService basketProductService;

    @Autowired
    private ProductService productService;

    @PostMapping("add-product")
    public String addProductToBasket(
            @RequestParam("size")double size,
            @RequestParam("productId")Long productId,
            @RequestParam("price")double price,
            @RequestParam("info")String info,
            @RequestParam("img")String img,
            @RequestParam("token")String token
    ){
        BasketProductDto basketProductDto = new BasketProductDto(productId,info,size,price,img,token);

        basketProductService.saveBasketProduct(basketProductDto);

    return"redirect:/";
    }


    @PostMapping("ordering")
    public String ordering(
            @RequestParam("username")String username,
            @RequestParam("phone")String phone,
            @RequestParam("info")String info,
            @RequestParam("token")String token,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        BasketDto basketDto= new BasketDto(username,phone,info,token);
        basketService.createOrder(basketDto);
//        System.out.println(username);
//        System.out.println(phone);
//        System.out.println(info);
//        System.out.println(token);

        return"redirect:/";
    }


     @PostMapping("user-ordering")
    public String orderingUser(
             @RequestParam("userId")MyUser user,
             @RequestParam("phone")PhoneUser phone,
            @RequestParam("address")AddressUser address,
            @RequestParam("postOffice")PostOfficeUser postOffice,
            @RequestParam("info")String info,
            @RequestParam("token")String token,
            HttpServletRequest request,
            HttpServletResponse response
    ){

         basketService.createOrderToUser(user,phone, address,postOffice,info,token);

        MyCookies.deleteCookie(request,response);


        return"redirect:/";
    }


    @GetMapping("delete/{id}")
    public String deleteBasket(
            @PathVariable("id")Long basketId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        basketService.deleteBasketById(basketId);

        MyCookies.deleteCookie(request,response);

        return"redirect:/";
    }
}
//=========== BasketController ================

//  @RequestParam("userId")Long userId,
// basketService.createOrder(basketDto);

// MyCookies.deleteCookie(request,response);