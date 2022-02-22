package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.dto.CartDto;
import com.vognev.textilewebproject.dto.OrderDto;
import com.vognev.textilewebproject.dto.ProductDto;
import com.vognev.textilewebproject.dto.UserProductDto;
import com.vognev.textilewebproject.model.Cart;
import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.Order;
import com.vognev.textilewebproject.model.Status;
import com.vognev.textilewebproject.service.CartService;
import com.vognev.textilewebproject.service.OrderService;
import com.vognev.textilewebproject.service.ProductService;
import com.vognev.textilewebproject.service.UserService;
import com.vognev.textilewebproject.util.DateHelper;
import com.vognev.textilewebproject.util.JsonSimpleParser;
import com.vognev.textilewebproject.util.MyCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

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
    public String orderList(
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                            Model model
    ){
        Page orderList =orderService.getOrderListAllPageable(pageable);//getPageOrders(pageable);
       // pageable.
//List<OrderDto> orderDtos=orderService.getOrderDtoList(orderList.getContent());
        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("page",orderList);

        model.addAttribute("url","/order/view-order");

        model.addAttribute("products", productService.getProductDtoList(0));

        model.addAttribute("statuses",statuses);

        return "admin-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search/{search_type}/{search_order}")
    public String searchOrder(
            @PathVariable String search_type,
            @PathVariable String search_order,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ){
        Page<OrderDto>orderList = orderService.searchOrder(search_type,search_order,pageable);

        model.addAttribute("page",orderList);

        if(!search_order.isEmpty()&&!search_type.isEmpty()){

        String url = "/order/search/"+search_type+"/"+search_order;

        model.addAttribute("url",url);
        }else{

            model.addAttribute("url","/order/view-order");
        }

       Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("statuses",statuses);

        return "admin-order";
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

        OrderDto orderDto =new OrderDto(
                order.getId(),
                dat,
                status,
                delivery,
                info_order,
                order.getCreat(),
                summ,
                user,
                phone_id,
                address_id,
                post_office_id);

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
    @GetMapping("/edit/{order}")
    public String orderEdit(
            @PathVariable Long order,
            Model model
    ){
        model.addAttribute("order",orderService.getOrderByIdFromOrderDto(order));

        model.addAttribute("url","/order/view-order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "parts/edit-order";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit/{order}")
    public String orderEditSave(
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
            @RequestParam  (name="balance_product")Double balance_product
    ){
        cartService.updateCart(cartId,product_id,salePrice,siz,discount_price,product_name, info_cart,balance_product);

        return "redirect:/order/view-order";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

        model.addAttribute("products", productService.getProductDtoList(0));

        return "admin-order";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/add-cart-to-order", method= RequestMethod.POST
    )
    public String addCartToOrder(
            @RequestParam  Order order,
            @RequestParam  Long productId,
            @RequestParam String productName,
            @RequestParam Double sellingPrice,
            @RequestParam Double sizeProduct,
            @RequestParam Double balance,
            @RequestParam Double siz,
            @RequestParam Double summ,
            @RequestParam Double discountPrice,
            @RequestParam String infoCart,
            @RequestParam String img,
            Model model

    ){
        Date dat = new Date();

        Cart cart= new Cart(productId,sellingPrice,productName,siz,discountPrice,infoCart,dat,order);

        cartService.save(cart);

        model.addAttribute("order",orderService.getOrderByIdFromOrderDto(order.getId()));

        model.addAttribute("url","/order/view-order");

        Set<Status> statuses=EnumSet.allOf(Status.class);

        model.addAttribute("status1",statuses);

        return "redirect:/order/edit/"+order.getId();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add-order")
    public String addOrder(
            Model model,
            @PageableDefault(size = 20) Pageable pageable
    ){
//        sort = {"id"},direction = Sort.Direction.DESC
        model.addAttribute("users", userService.listUserLimit(pageable));

        model.addAttribute("products", productService.getProductDtoList(0));

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

        model.addAttribute("products", productService.getProductDtoList(0));

        Set<Status> statuses = EnumSet.allOf(Status.class);

        model.addAttribute("statusList", statuses);

        return "parts/order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-order/{order}")
    public String deleteOrder(
            @PathVariable Long order
    ){
        orderService.deleteOrder(order);

        return "redirect:/order/view-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete-cart/{cart}")
    public String deleteCart(
            @PathVariable Cart cart
    ){
        cartService.deleteCart(cart);

        return "redirect:/order/view-order";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
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


    @GetMapping("user/{id}")
    public String getOrderByUser(
            @PathVariable(name="id")Long id,
            Model model
    ){
        model.addAttribute("orderList",orderService.orderAllToUser(id));

        model.addAttribute("products", productService.getProductDtoList(0));

        return "user";
    }
}
//=============== OrderController ===========
// model.addAttribute("users", userService.partMyUserList());
//model.addAttribute("products", productService.getProductDtoList(0));
// String url="edit/"+18;//return orderService.getOrderByIdFromOrderDto(orderId)

// model.addAttribute("users", userService.partMyUserList());
//model.addAttribute("products", productService.getProductDtoList(0));

//        ,
//        sizeProduct,balance,
//        summ,img);
//       productId: 164
//orderId: 18
//productName: Америк. штапель креш марсала
//sellingPrice: 88
//sizeProduct: 33
//balance: 18.5
//siz: 12
//summ: 1046
//discountPrice: 10
//infoCart:
//img: noimage.png
// cartService.addCartToOrder(cartValue);
//
//        model.addAttribute("orderList",orderService.orderAll());
//        model.addAttribute("products", productService.getProductDtoList(0));

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/view-order/{order}")
//    public String orderEdit(
//            @PathVariable Order order,
//            Model model
//    ){
//        Set<Status> statuses=EnumSet.allOf(Status.class);
//
//        model.addAttribute("order",order);
//        model.addAttribute("url","/order");
//        model.addAttribute("status1",statuses);
//
//        return "parts/orderEdit";
//    }

//orderList = orderService.getPageOrders(pageable);//getOrderListAllPageable(pageable);?page=1&size=10?search_order="+search_order+"&search_type="+search_type

//return "redirect:"+url;
//model.addAttribute("page",orderList);search?search_order=val&search_type=2
//MyCookies.setCookies(request,response);
//       // model.addAttribute("order",order);
//        model.addAttribute("url","/order");
//        model.addAttribute("status1",statuses);
