package com.vognev.textilewebproject.controller;


import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.BasketDto;
import com.vognev.textilewebproject.model.dto.BasketProductDto;
import com.vognev.textilewebproject.model.util.MyCookies;
import com.vognev.textilewebproject.service.BasketProductService;
import com.vognev.textilewebproject.service.BasketService;
import com.vognev.textilewebproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            @RequestParam("userId")Long userId,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        BasketDto basketDto= new BasketDto(username,phone,info,token,userId);

        MyCookies.deleteCookie(request,response);

        basketService.createOrder(basketDto);

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
        //basketService.createOrder(basketDto);
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
