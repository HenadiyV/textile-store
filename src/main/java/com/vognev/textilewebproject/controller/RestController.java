package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.*;
import com.vognev.textilewebproject.model.util.Constants;
import com.vognev.textilewebproject.model.util.MyCookies;
import com.vognev.textilewebproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * textilewebproject_2  21/10/2021-21:03
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postOfficeService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageProductService imageProductService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketProductService basketProductService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("us/{id}")
    public MyUser getUserFromOrder(@PathVariable(name = "id") Long id){
        return userService.getUser(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("user/{id}")
    public UserDto getUserOrder(@PathVariable(name = "id") Long id){
        return userService.getUserToOrder(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/us-search/{us}",method= RequestMethod.GET)
    public List<UserDto> searchUser(
            @PathVariable(name="us",required = false) String us
    ){
        List<UserDto> userList=new ArrayList<>();

        if(!us.isEmpty()){

            userList= userService.searchUser(us);
        }else{

            userList= userService.listUserDto();
        }
     return userList;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/us-list/", method= RequestMethod.GET )
    public List<UserDto> listUserDto(){

        List<UserDto> userList=new ArrayList<>();

            userList= userService.listUserDto();

     return userList;
    }


    @GetMapping("/product-list")
    public List<ProductDto> productDtoList( ){

        List<ProductDto> productDtoList=productService.getProductDtoList();

       return productDtoList;
    }


    @GetMapping("/search-product-list/{name}")
    public List<ProductDto> searchProductDtoList(@PathVariable String name ){

        List<ProductDto> productDtoList=new ArrayList<>();

        if(!name.isEmpty()){

            productDtoList = productService.searchProduct(name);

        }else {

          productDtoList=productService.getProductDtoList();
        }

       return productDtoList;
    }


     @GetMapping("/search-product/{name}")
    public List<ProductDto> searchProductDtoList1(@PathVariable String name ){

        List<ProductDto> productDtoList=new ArrayList<>();

        if(!name.isEmpty()){

            productDtoList = productService.searchProduct(name);
        }else {

          productDtoList=productService.getProductDtoList();
        }
       return productDtoList;
    }


     @GetMapping("/search-product")
    public List<ProductDto> searchProductDtoList2( ){

        List<ProductDto> productDtoList=new ArrayList<>();

          productDtoList=productService.getProductDtoList();

       return productDtoList;
    }


    @GetMapping("/product/{id}")
    public ProductDto getProductDto(@PathVariable Long id ){
        //System.out.println(id);
       return productService.getProductToOrderDto(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/showcase-image/{productId}/{id}")
    public void checkedImageShowcese(
            @PathVariable Long productId,
            @PathVariable Long id
    ){
        for(ImageProduct imageProduct:productService.getListImagesProduct(productId)){

            if(imageProduct.getId().equals(id)){

                imageProduct.setShowcase(true);
            }else{

                imageProduct.setShowcase(false);
            }
            imageProductService.updateShowcaseImageProduct(imageProduct);
        }
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/update-phone", method= RequestMethod.POST  )
    public  Map<String,String> updatePhone( @RequestBody   PhoneUserDto ph){

        Map<String,String> result= new LinkedHashMap<>();

        if(!phoneService.updatePhoneUser(ph)) {

            result.put("phone","phone");
            result.put("id","phone"+ph.getId().toString());
            result.put("idMes","phoneErr"+ph.getId().toString());
            result.put("error","Номер існує");

        }else{

            result.put("phone","phone");
            result.put("id","phone"+ph.getId().toString());
            result.put("idMes","phoneErr"+ph.getId().toString());
            result.put("res","Номер оновленно");
        }
        return result;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-post-office")
    public  Map<String,String> updatePostOffice( @RequestBody PostOfficeUserDto po ){

        Map<String,String> result= new LinkedHashMap<>();

        if(!postOfficeService.updatePostOfficeUser(po)) {

            result.put("postOffice","postOffice");
            result.put("id","postOffice"+po.getId().toString());
            result.put("idMes","postOfficeErr"+po.getId().toString());
            result.put("error","Помилка оновлення");

        }else{

            result.put("postOffice","postOffice");
            result.put("id","postOffice"+po.getId().toString());
            result.put("idMes","postOfficeErr"+po.getId().toString());
            result.put("res","Почту оновленно");
        }
        return result;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-address")
    public  Map<String,String> updateAddress( @RequestBody   AddressUserDto addressUserDto ){

        Map<String,String> result= new LinkedHashMap<>();

        if(!addressService.updateAddressUser(addressUserDto)) {

            result.put("address","address");
            result.put("id","address"+addressUserDto.getId().toString());
            result.put("idMes","addressErr"+addressUserDto.getId().toString());
            result.put("error","Помилка оновлення");

        }else{

            result.put("address","address");
            result.put("id","address"+addressUserDto.getId().toString());
            result.put("idMes","addressErr"+addressUserDto.getId().toString());
            result.put("res","Адресу оновленно");

        }
        return result;
    }

    @GetMapping("/cart-view/{cart}")
    public CartDto orderCartView(@PathVariable Long cart){

        return cartService.getCartDto(cart);
    }


    @RequestMapping(value="/basket", method= RequestMethod.POST,
            produces = "application/json",
            consumes="application/json"
    )
    public void addProductToBasket(
            @RequestBody BasketProductDto basketProductDto

    ){
        System.out.println("addProductToBasket 262");
        System.out.println(basketProductDto.getProductId());
        System.out.println(basketProductDto.getSize());
        System.out.println(basketProductDto.getInfo());
        System.out.println(basketProductDto.getToken());
        System.out.println(basketProductDto.getSize());
        System.out.println(basketProductDto.getPrice());
        System.out.println(basketProductDto.getImg());
        basketProductService.saveBasketProduct(basketProductDto);

    }


    @GetMapping("/basket-view/{token}")
    public List<BasketProductDto> getBasketProductList(@PathVariable String token){

    if(token.length()==Constants.LENGHT_TOKEN){
        return basketService.getBasketDtoListToToken(token);
     }
        return null;
    }


    @GetMapping("/delete-basket-product/{token}/{id}")
    public List<BasketProductDto> deleteBasketProduct(
            @PathVariable String token,
            @PathVariable Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        List<BasketProductDto> basketProductDtoList = basketService.deleteBasketProductInBasket(id,token);
        if(basketProductDtoList!=null){
            return basketProductDtoList;
        }else{
            MyCookies.deleteCookie(request, response);
        }
        return null;
    }


    @GetMapping("/search-token/{token}")
    public boolean searchToken(
            @PathVariable String token
    ){
        BasketDto basketDto=basketService.getBasketDtoToToken(token);
        if(basketDto!=null){
            return false;
        }
        return true;
    }
}
