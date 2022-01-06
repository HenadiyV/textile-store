package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.BasketDto;
import com.vognev.textilewebproject.model.dto.BasketProductDto;
import com.vognev.textilewebproject.model.util.DateHelper;
import com.vognev.textilewebproject.repository.BasketProductRepository;
import com.vognev.textilewebproject.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private Basket getBasketToToken(String token){
        return basketRepository.searchBasketToToken(token);
    }


    public void saveBasketProduct(BasketProductDto basketProductDto){

        Basket basket = getBasketToToken(basketProductDto.getToken());

        Date dat= new  Date();

        if(basket==null){
            basket= new Basket();
            basket.setToken(basketProductDto.getToken());
            basket.setDat(dat);
            basketRepository.save(basket);
        }
        productService.updateProductSallingSize(basketProductDto.getProductId(),basketProductDto.getSize(),true);

        BasketProduct basketProduct = new BasketProduct(
                basketProductDto.getProductId(),
                basketProductDto.getSize(),
                basketProductDto.getPrice(),
                basketProductDto.getImg(),
                basketProductDto.getInfo(),
                dat,
                basket
        );

        basketProductService.saveBasketProduct(basketProduct);
    }


    public Basket getBasketToId(Long id){
        return basketRepository.getById(id);
    }


    public List<BasketProductDto> getBasketDtoListToToken(String token){
        Basket basket = getBasketToToken(token);
        return getBasketProductDtoList(basket);
    }


    private List<BasketProductDto> getBasketProductDtoList(Basket basket) {
        List<BasketProductDto>basketDtoList=new ArrayList<>();

        for(BasketProduct basketProduct:basket.getBasketProducts()){

            BasketProductDto basketProductDto = getBasketProductDto(basket, basketProduct);
            basketDtoList.add(basketProductDto);
        }
        return basketDtoList;
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

        basketProductService.deleteBasketProduct(id);

        return getBasketDtoListToToken(token);
    }


    public BasketDto getBasketDtoToToken(String token){

        Basket basket=basketRepository.searchBasketToToken(token);

        if(basket!=null){
            return getBasketToBasketDto(basket);
        }
        return null;
    }


    private BasketDto getBasketToBasketDto(Basket basket){

        return new BasketDto(
                basket.getId(),
                basket.getUsername(),
                basket.getPhone(),
                basket.getInfo(),
                basket.getToken(),
                basket.getDat().toString()
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