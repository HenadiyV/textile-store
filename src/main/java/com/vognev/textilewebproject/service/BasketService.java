package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.BasketDto;
import com.vognev.textilewebproject.model.dto.BasketProductDto;
import com.vognev.textilewebproject.model.util.Constants;
import com.vognev.textilewebproject.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * textilewebproject_3  29/12/2021-8:52
 */
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketProductService basketProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postOfficeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;


     void saveBasket(Basket basket){
        basketRepository.save(basket);
    }


     List<Basket> getBasketList(){
        return basketRepository.findAll();
    }

     Basket getBasketToToken(String token){

        return basketRepository.searchBasketToToken(token);
    }


     List<Basket> getBasketListToClearDate(){
        try {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );// HH:mm:ss

        Date dat = new Date();

          return  basketRepository.searchBasketToClearDate(simpleDateFormat.parse(simpleDateFormat.format(dat)));
        } catch (ParseException e) {
            e.printStackTrace();
          return null;
        }
    }


     Basket getBasketToId(Long id){
        try{
            if(basketRepository.foundBasketId(id)){
                return   basketRepository.getById(id);
            }else{
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }


    public List<BasketProductDto> getBasketDtoListToToken(String token){

        if(token.length()==Constants.LENGHT_TOKEN){

            Basket basket = getBasketToToken(token);

            return getBasketProductDtoList(basket);
        }
        return null;
    }


    public void deleteBasketById(Long id){

        Basket basket = basketRepository.getById(id);

        basketDelete(basket);
    }


    void basketDelete(Basket basket){

        for(BasketProduct basketProduct : basket.getBasketProducts()){

            basketProductService.deleteBasketProduct(basketProduct.getId());
        }
        basketRepository.delete(basket);
    }

    public void deleteRancidCookies(){
        Date dat = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );// HH:mm:ss
        try {

            List<Basket> basketList = basketRepository.searchBasketToClearDate(simpleDateFormat.parse(simpleDateFormat.format(dat)));

            if(basketList!=null){
                for(Basket basket:basketList){
                    basketDelete(basket);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private List<BasketProductDto> getBasketProductDtoList(Basket basket) {

        if(basket!=null) {

            List<BasketProductDto> basketDtoList = new ArrayList<>();

            for (BasketProduct basketProduct : basket.getBasketProducts()) {

                BasketProductDto basketProductDto = getBasketProductDto(basket, basketProduct);

                basketDtoList.add(basketProductDto);
            }
            return basketDtoList;
        }
        return null;
    }


    private BasketProductDto getBasketProductDto(Basket basket, BasketProduct basketProduct) {

        return new BasketProductDto(
                basketProduct.getId(),
                basketProduct.getProductId(),
                basketProduct.getInfo(),
                basket.getToken(),
                basketProduct.getSize(),
                basketProduct.getPrice(),
                basketProduct.getImg()
        );
    }


    public List<BasketProductDto> deleteBasketProductInBasket(Long id,String token){

    int countProductToBasket = getBasketDtoListToToken(token).size();

        if(countProductToBasket>1){

            basketProductService.deleteBasketProduct(id);

            return getBasketDtoListToToken(token);
        }else{

            basketProductService.deleteBasketProduct(id);

            Basket basket = basketRepository.searchBasketToToken(token);

            basketRepository.delete(basket);

            return null;
        }
    }


    public BasketDto getBasketDtoToToken(String token){

        Basket basket=basketRepository.searchBasketToToken(token);

        if(basket!=null){
            return getBasketToBasketDto(basket);
        }
        return null;
    }


    public Long getBasketProductIdToToken(String token){

        Basket basket=basketRepository.searchBasketToToken(token);

        if(basket!=null){
            return basket.getId();
        }
        return -1L;
    }


    private BasketDto getBasketToBasketDto(Basket basket){

        return new BasketDto(
                basket.getId(),
                basket.getUsername(),
                basket.getPhone(),
                basket.getInfo(),
                basket.getToken(),
                basket.getDat().toString(),
                basket.getDat_clear().toString()
        );

    }


    public void createOrder(BasketDto basketDto){

        PhoneUser phoneUserDB =  phoneService.getNumberPhone(basketDto.getPhone());

        AddressUser addressUser =new AddressUser();

        PostOfficeUser postOfficeUser = new PostOfficeUser();

        MyUser myUser =new MyUser();

        if(phoneUserDB ==null){

            myUser =new MyUser(
                    basketDto.getUsername(),
                    basketDto.getUsername()+"@temp.com",
                    passwordEncoder.encode(basketDto.getUsername()),
                    100,
                    true
            );
            MyUser myUserDB =userService.saveUserBasket(myUser);

            phoneService.saveUserPhone(basketDto.getPhone(),myUserDB);

            addressService.saveBasketAdddress(myUserDB);

            postOfficeService.savePostOfficeBasket(myUserDB);

        }else{
            myUser =phoneUserDB.getPhoneUser();
        }
        Order order=orderService.saveOrderBasket(myUser,basketDto.getInfo());

        if(order!=null){
            createCarts(order,basketDto.getId());
        }
    }


    private void createCarts(Order order, Long basketId){

        List<BasketProduct> basketProductList = basketProductService.getBasketProductList(basketId);

        Date today=new Date();

        for(BasketProduct basketProduct: basketProductList){

            Product product = productService.getProductById(basketProduct.getProductId());

            Cart cart = new Cart(
                    null,
                    product.getId(),
                    product.getSellingPrice(),
                    product.getName(),
                    basketProduct.getSize(),
                    0.0,
                    basketProduct.getInfo(),
                    today,
                    order
            );
            cartService.saveCart(cart);
        }
    }
}