package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.CartDto;
import com.vognev.textilewebproject.model.dto.OrderDto;
import com.vognev.textilewebproject.model.dto.ProductDto;
import com.vognev.textilewebproject.model.dto.UserProductDto;
import com.vognev.textilewebproject.model.util.DateHelper;
import com.vognev.textilewebproject.model.util.JsonSimpleParser;
import com.vognev.textilewebproject.service.CartService;
import com.vognev.textilewebproject.service.OrderService;
import com.vognev.textilewebproject.service.ProductService;
import com.vognev.textilewebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * textilewebproject_3  11/11/2021-7:53
 */
@Controller
@RequestMapping("/order")

public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/view-order")
    public String orderList(Model model){

        model.addAttribute("orderList",orderService.orderAll());
        model.addAttribute("products", productService.getProductDtoList());

        return "admin-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/view-order/{order}")
    public String orderEdit(
            @PathVariable Order order,
            Model model
    ){
        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("order",order);
        model.addAttribute("url","/order");
        model.addAttribute("status1",statuses);

        return "parts/orderEdit";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userList/{order}")
    public String orderListUser(
            @PathVariable Long order,
            Model model
    ){
        model.addAttribute("order",orderService.getOrderByIdFromOrderDto(order));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("url","/admin/order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "parts/orderEdit";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-order-user/{order}/{user}")
    public String orderUpUser(
            @PathVariable Long order,
            @PathVariable MyUser user,
            Model model
    ){
        model.addAttribute("order",orderService.getOrderByIdFromOrderDto(order));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("userN", user);
        model.addAttribute("url","/admin/view-order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "parts/orderEdit";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-order-user/{order}/{user}")
    public String orderChangeUser(
            @PathVariable Order order,
            @PathVariable Long user,
            @RequestParam(name="phone_id")Long phone_id,
            @RequestParam  (name="address_id")Long address_id,
            @RequestParam  (name="post_office_id")Long post_office_id,
            @RequestParam  (name="summ")Double summ,
            @RequestParam  (name="dat_dispatch")String dat_dispatch,
            @RequestParam  (name="status")String status,
            @RequestParam  (name="delivery")String delivery,
            @RequestParam  (name="info_order")String info_order,
            Model model
    ){
        Date dat= DateHelper.convertStringToDate(dat_dispatch);

        OrderDto orderDto =new OrderDto(order.getId(),dat,status,delivery, info_order,
                order.getDat_create(),summ,
                user,phone_id,address_id,post_office_id);

        orderService.updateOrderByUser(orderDto);

        return "redirect:/order/view-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-order/{order}")
    public String orderUpdate(
            @PathVariable Long order,
            Model model
    ){
        model.addAttribute("order",orderService.getOrderByIdFromOrderDto(order));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("url","/order/view-order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "parts/orderEdit";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-order/{order}")
    public String orderUpdateSave(
            @PathVariable  Order order,
            @RequestParam  (name="user_id")Long user_id,
            @RequestParam  (name="phone_id")Long phone_id,
            @RequestParam  (name="address_id")Long address_id,
            @RequestParam  (name="post_office_id")Long post_office_id,
            @RequestParam  (name="dat_dispatch")String dat_dispatch,
            @RequestParam  (name="status")String status,
            @RequestParam  (name="delivery")String delivery,
            @RequestParam  (name="info_order")String info_order,
            Model model
    ){

        orderService.updateOrder(order,user_id,phone_id,address_id,post_office_id,dat_dispatch,status,delivery,info_order);

        model.addAttribute("order",orderService.getOrderByIdFromOrderDto(order.getId()));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("url","/admin/view-order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "redirect:/order/view-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-cart/{cart}")
    public String cartUpdate(
            @PathVariable Cart cart,
            Model model
    ){
        model.addAttribute("cart",cart);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("url","/order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "admin-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-cart")
    public String cartUpdateSave(
            @RequestParam  (name="cart_id")Long cartId,
            @RequestParam  (name="order_id") Long orderId,
            @RequestParam  (name="product_id")Long product_id,
            @RequestParam  (name="salePrice")Double salePrice,
            @RequestParam  (name="siz")Double siz,
            @RequestParam  (name="discount_price")Double discount_price,
            @RequestParam  (name="summ")Double summ,//
            @RequestParam  (name="product_name")String product_name,
            @RequestParam  (name="info_cart")String info_cart,
            @RequestParam  (name="balance_product")Double balance_product,//
            Model model
    ){
//        ProductDto productDto=productService.getProductDtoByProductId(product_id);
//
//        if(productDto.getSizeProduct()-productDto.getSelling_size()!=balance_product){
//
//            productService.updateProductSallingSize(product_id,balance_product,false);
//        }
//        Order order=orderService.getOrderById(orderId);
//
//        Cart cart=new Cart(cartId,product_id,salePrice,product_name,siz,
//                discount_price,info_cart,order.getDat_create(),order);

        cartService.updateCart(cartId,product_id,salePrice,siz,discount_price,product_name, info_cart,balance_product);
        //cartService.saveCart(cart);

        return "redirect:/order/view-order";
    }


    @RequestMapping(value="/add-cart", method= RequestMethod.POST ,
            headers = "Accept=*/*",
            produces = "application/json",
            consumes="application/json"
    )
    public String addCart(
            @RequestBody  CartDto[] cartValue,
            Model model
    ){
       cartService.addCartToOrder(cartValue);

        model.addAttribute("orderList",orderService.orderAll());
        model.addAttribute("products", productService.getProductDtoList());
        return "admin-order";//"redirect:/view-order";//order
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-order")
    public String addOrder(Model model){

        model.addAttribute("users", userService.listUserDto());
        model.addAttribute("products", productService.getProductDtoList());

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("statusList", statuses);

        return "parts/order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add-order", method = RequestMethod.POST )
    public String createOrder(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "addressId") Long addressId,
            @RequestParam(name = "phoneId") Long phoneId,
            @RequestParam(name = "postOfficeId") Long postOfficeId,
            @RequestParam(name = "dat_dispatch") String dat_dispatch,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "delivery") String delivery,
            @RequestParam(name = "info") String info,
            @RequestParam(name = "carts")String carts,
            Model model
    ) {
        Date dat_dispatching = DateHelper.convertStringToDate(dat_dispatch);

      Order order = orderService.createOrder(dat_dispatching, status, delivery, info, userId, addressId, phoneId, postOfficeId);

        if (order != null) {

        List<CartDto> cartDtoList = JsonSimpleParser.parseArrayCartDto(carts);

         cartService.createCartByOrder(order, cartDtoList);
        }
        model.addAttribute("users", userService.listUserDto());
        model.addAttribute("products", productService.getProductDtoList());

        Set<Status> statuses = EnumSet.allOf(Status.class);
        model.addAttribute("statusList", statuses);

        return "parts/order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-order/{order}")
    public String deleteOrder(
            @PathVariable Long order,
            Model model
    ){
        orderService.deleteOrder(order);

        return "redirect:/order/view-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-cart/{cart}")
    public String deleteCart(
            @PathVariable Cart cart,
            Model model
    ){
        System.out.println(cart.getNameProduct());
        cartService.deleteCart(cart);
        return "redirect:/order/view-order";
    }


    @GetMapping("/user-product/{id}")
    public String productUserList(
            @PathVariable(name="id")Long id,
            Model model
    ){
        List<UserProductDto> carts=cartService.getUserProduct(id);

        ProductDto product = productService.getProductDtoByProductId(id);

        model.addAttribute("userProductList",carts);
        model.addAttribute("userProduct",product);

        return "parts/product-user";
    }
}