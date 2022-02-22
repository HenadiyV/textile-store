package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.dto.BasketDto;
import com.vognev.textilewebproject.dto.BasketProductDto;
import com.vognev.textilewebproject.util.Constants;
import com.vognev.textilewebproject.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    Basket saveBasket(Basket basket){
        return  basketRepository.save(basket);
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

        Optional<Basket> basket = basketRepository.findById(id);

        return basket.orElse(null);
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

            basketProductService.deleteBasketProduct(basketProduct.getId(),false);
        }
        basketRepository.delete(basket);
    }


    void deleteRancidCookies(){

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

            basketProductService.deleteBasketProduct(id,true);

            return getBasketDtoListToToken(token);
        }else{

            basketProductService.deleteBasketProduct(id,true);

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
                basket.getDat_clear().toString(),
                basket.getUserId()
        );

    }


    public void createOrder(BasketDto basketDto){

        MyUser user= new MyUser();

        AddressUser address = new AddressUser();

        PostOfficeUser postOffice = new PostOfficeUser();

        PhoneUser phoneUserDB =  phoneService.getNumberPhone(basketDto.getPhone());

        MyUser myUser =new MyUser();

        if(phoneUserDB ==null){

                int colUser=userService.colUsers();

            String  pass=basketDto.getPhone().replace("+38(","")
                    .replace(")","")
                    .replace("-","");
                myUser =new MyUser(
                        basketDto.getName(),
                        basketDto.getName()+"@temp.com",
                        passwordEncoder.encode(pass),
                        100,
                        true
                );
                myUser.setUsername(basketDto.getPhone());
                myUser.setInfo(" ");
                myUser.setEmail(basketDto.getPhone()+"@temp.temp");//Constants.shortUUID()+"-"+
                myUser.setRoles(Collections.singleton(Role.USER));

                MyUser myUserDB =userService.saveUserBasket(myUser);

                phoneUserDB= phoneService.saveUserPhone(basketDto.getPhone(),myUserDB);

                address =  addressService.saveBasketAdddress(myUserDB);

                postOffice =  postOfficeService.savePostOfficeBasket(myUserDB);

                user=userService.getUser(myUser.getId());
        }else{
        myUser =phoneUserDB.getPhoneUser();

        address = orderService.getAdressUser(myUser);

        postOffice = orderService.getPostOfficeUser(myUser);

        }
        Order order=orderService.saveOrderBasket(user,address,phoneUserDB,postOffice, basketDto.getInfo());

        if(order!=null){

            Basket basket = basketRepository.searchBasketToToken(basketDto.getToken());

            createCarts(order,basket.getId());

            basketDelete(basketRepository.getById(basket.getId()));

            userService.sendAdmin(basketDto);
        }
    }


    public void createOrderToUser(MyUser user,
                                  PhoneUser phon,
                                  AddressUser address,
                                  PostOfficeUser poastOffice,
                                  String info,
                                  String token
    ){
        Date dat = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );// HH:mm:ss

        Order orderNew = new Order(null,dat,"не оплачений"," ",info,dat,user,address,phon,poastOffice);

        Order orderDb = orderService.save(orderNew);

        Basket basket=basketRepository.searchBasketToToken(token);

        createCarts(orderDb,basket.getId());

        basketDelete(basketRepository.getById(basket.getId()));
    }


    void createCarts(Order order, Long basketId){

        List<BasketProduct> basketProductList = basketProductService.getBasketProductList(basketId);

        Date today=new Date();

        for(BasketProduct basketProduct: basketProductList){

            Product product = productService.getProductById(basketProduct.getProductId());

            //productService.updateProductSallingSize(product.getId(),basketProduct.getSize(),false);

            Cart cart = new Cart(product.getId(),
                    product.getSellingPrice(),
                    product.getName(),
                    basketProduct.getSize(),
                    0.0,
                    basketProduct.getInfo(),
                    today,
                    order);

            cartService.save(cart);
        }
    }


    public List<Basket> findAll() {
        return basketRepository.findAll();
    }


    public Basket getById(long basketId) {

        Optional<Basket>basketOptional = basketRepository.findById(basketId);

        return basketOptional.orElse(null);
    }

}