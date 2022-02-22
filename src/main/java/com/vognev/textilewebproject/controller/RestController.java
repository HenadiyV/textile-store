package com.vognev.textilewebproject.controller;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.dto.*;
import com.vognev.textilewebproject.util.Constants;
import com.vognev.textilewebproject.util.MyCookies;
import com.vognev.textilewebproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
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
    private OrderService orderService;

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


    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("us/{id}")
    public MyUser getUserFromOrder(@PathVariable(name = "id") Long id){
        return userService.getUser(id);
    }


   @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("user/{id}")
    public UserDto getUserOrder(@PathVariable(name = "id") Long id){
        return userService.getUserToOrder(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search-user/{name}")
    public List<UserDto> searchUser(
            @PathVariable String name
    ){

     return userService.searchUser(name);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/user-list/{direction}", method= RequestMethod.GET )
    public List<UserDto> listUser(
            @PathVariable Integer direction
    ){

        List<UserDto> userList=new ArrayList<>();

            userList= userService.scrollMyUserList(direction);

     return userList;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/us-list/", method= RequestMethod.GET )
    public List<UserDto> listUserDto(){

        List<UserDto> userList=new ArrayList<>();

            userList= userService.listUserDto();

     return userList;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/userList/", method= RequestMethod.GET )
    public List<UserDto> getListUserDto(@PageableDefault(size = 20) Pageable pageable
){

        List<UserDto> userList=new ArrayList<>();

            userList= userService.listUserLimit(pageable);//userService.listUserDto();

     return userList;
    }


    @GetMapping("/product-list/{positionList}")
    public List<ProductDto> productDtoList( @PathVariable int positionList){

        List<ProductDto> productDtoList=productService.getProductDtoList(positionList);

       return productDtoList;
    }


    @GetMapping("/search-product-list/{name}")
    public List<ProductDto> searchProductDtoList(@PathVariable String name ){

        List<ProductDto> productDtoList=new ArrayList<>();

        if(!name.isEmpty()){

            productDtoList = productService.searchProduct(name);

        }else {

          productDtoList=productService.getProductDtoList(0);
        }

       return productDtoList;
    }


     @GetMapping("/search-product/{name}")
    public List<ProductDto> searchProductDtoList1(
            @PathVariable String name
            ){
        List<ProductDto> productDtoList=new ArrayList<>();

        if(!name.isEmpty()){

            productDtoList = productService.searchProduct(name);
        }else {

          productDtoList=productService.getProductDtoList(0);
        }
       return productDtoList;
    }


     @GetMapping("/search-product")
    public List<ProductDto> searchProductDtoList2( ){

        List<ProductDto> productDtoList=new ArrayList<>();

          productDtoList=productService.getProductDtoList(0);

       return productDtoList;
    }


    @GetMapping("/product/{id}")
    public ProductDto getProductDto(@PathVariable Long id ){

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


    @PostMapping("/cart-update")
    public CartDto updateCart(
            @RequestBody      CartDto cartDto
    ){
        Cart cart= cartService.getById(cartDto.getCartId());
        cart.setDiscount_price(cartDto.getDiscountPrice());
        cart.setSiz(cartDto.getSiz());
        cart.setInfo(cartDto.getInfoCart());
cartService.save(cart);
        return cartDto;
    }


    @RequestMapping(value="/basket", method= RequestMethod.POST,
            produces = "application/json",
            consumes="application/json"
    )
    public void addProductToBasket(
            @RequestBody BasketProductDto basketProductDto

    ){
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


    @GetMapping("user-address/{id}")
    public AddressUserDto getAddressUser(
            @PathVariable(name="id")AddressUser addressUser
    ){
        return addressService.getAddressUserDtoByAddressUser(addressUser);
    }


    @GetMapping("user-update-phone/{id}")
    public PhoneUserDto getPhoneUser(
            @PathVariable(name="id")PhoneUser phoneUser
    ){
        return phoneService.getPhoneUserDtoByPhoneUser(phoneUser);
    }


    @GetMapping("user-postOffice/{id}")
    public PostOfficeUserDto getPostOfficeUser(
            @PathVariable(name="id")PostOfficeUser postOfficeUser
    ){
        return postOfficeService.getPoastOfficeUserDtoByPostOfficeUser(postOfficeUser) ;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("statistic-product")
    public List<ProductStatistic> getProductStatistic(

    ){
        return productService.statisticProduct() ;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("statistic-cart")
    public List<ProductStatistic> getCartStatistic(

    ){
        return cartService.statisticCart() ;
    }


    @RequestMapping(value="/edit-selling-size", method= RequestMethod.POST ,
            headers = "Accept=*/*",
            produces = "application/json",
            consumes="application/json"
    )
    public void corectSellingSize(
            @RequestBody  BasketProductDto[] basketProductDtos
    ){
        basketProductService.corectSizeSellingProduct(basketProductDtos);
    }

}
//==================RestCotroller ===============

//            @PathVariable Long cartId,
//            @PathVariable Double siz,
//            @PathVariable Double discountPrice,
//            @PathVariable String infoCart
// (required = false)List<UserDto> userList=new ArrayList<>();
//        System.out.println("name "+name);
//        System.out.println("name1 "+name.isEmpty());


// userList= userService.searchUser(name);
// if(!name.isEmpty()){}else{

//userList= userService.listUserDto();
//}userList
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @RequestMapping(value="/add-cart-to-order", method= RequestMethod.POST
//    )
//    public OrderDto addCartToOrder(
//            @RequestParam  Long orderId,
//            @RequestParam  Long productId,
//            @RequestParam String productName,
//            @RequestParam Double sellingPrice,
//            @RequestParam Double sizeProduct,
//            @RequestParam Double balance,
//            @RequestParam Double siz,
//            @RequestParam Double summ,
//            @RequestParam Double discountPrice,
//            @RequestParam String infoCart,
//            @RequestParam String img
//
//    ){
//
////       productId: 164
////orderId: 18
////productName: Америк. штапель креш марсала
////sellingPrice: 88
////sizeProduct: 33
////balance: 18.5
////siz: 12
////summ: 1046
////discountPrice: 10
////infoCart:
////img: noimage.png
//// cartService.addCartToOrder(cartValue);
////
////        model.addAttribute("orderList",orderService.orderAll());
////        model.addAttribute("products", productService.getProductDtoList(0));
//        String url="edit/"+18;
//        return orderService.getOrderByIdFromOrderDto(orderId);
//    }