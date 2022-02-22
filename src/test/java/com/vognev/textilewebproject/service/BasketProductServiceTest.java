package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.util.Constants;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * textilewebproject_3  03/01/2022-11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-basket-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/create-basket-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BasketProductServiceTest {

 @Autowired
 private BasketProductService basketProductService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postOfficeService;

    @Test
    void saveBasketProduct() {
        List<BasketProduct> basketProductList = basketProductService.findAll();
        Assert.assertEquals(6,basketProductList.size());
    }
    @Test
    void addBasketProduct() {
        Basket basket = basketService.getBasketToId(1L);

        Date dat = new Date();
        BasketProduct basketProduct = new BasketProduct(2L,1.0,60.0," "," ",dat,basket);
        basketProductService.save(basketProduct);
        Assert.assertEquals(7,basketProductService.findAll().size());

    }

    @Test
    void deleteBasketProduct() {
        BasketProduct basketProduct = basketProductService.getById(6L);
        basketProductService.delete(basketProduct);
        List<BasketProduct> basketProductList = basketProductService.findAll();
        Assert.assertEquals(5,basketProductList.size());
    }

    @Test
    void getBasketList() {
       // int siz=basketProductService.getBasketProductList(1L).size();
        List<BasketProduct> basketProductList = basketProductService.getBasketProductList(1L);
        int siz=basketProductList.size();
        System.out.println(siz);
        Assert.assertEquals(1,siz);
    }

    @Test
    void test()throws Exception{
        assertThat(basketProductService).isNotNull();
    }

    @Test
    void createCartsInBasket()throws Exception{
        Order order = create(3L);
        Basket basket = createBasket();
        createBasketProduct(basket);
        basketService.createCarts(order,basket.getId());
        Assert.assertEquals(6,cartService.findAll().size());
    }

    @Test
    void addBasket()throws Exception{
        createBasket();
        Assert.assertEquals(7,basketService.findAll().size());
    }
    @Test
    void deleteBasket()throws Exception{
        createBasket();
        Assert.assertEquals(7,basketService.findAll().size());
        Basket basket= basketService.getById(1L);
        basketService.basketDelete(basket);
        Assert.assertEquals(6,basketService.findAll().size());
    }

    Basket createBasket(){
        Date dat = new Date();
        long today=dat.getTime();
        Date today1=new Date(today);
        Basket basket = new Basket(null,"7777777777777777","test","12345"," ",
                dat,today1,2L,null);
        return   basketService.saveBasket(basket);
    }

    BasketProduct createBasketProduct(Basket basket){
        //Long productId, Double size, Double price, String img, String info, Date dat, Basket basket
        Date dat = new Date();
        BasketProduct basketProduct = new BasketProduct(1L,1.0,110.0,"13_1.jpg"," ",dat,basket);
        return basketProductService.save(basketProduct);
    }

    Order create(Long id){
        Date today=new Date();
        long ltime=today.getTime()+3*Constants.ONE_DAY;
        Date today3=new Date(ltime);

        MyUser myUser= createUser();

        PhoneUser phone = createPhone(myUser);

        AddressUser address = createAddress(myUser);

        PostOfficeUser postOffice = createPostOffice(myUser);

        Order order = new Order(3L,today3,"не оплачений"," ","info",today,myUser,address,
                phone,
                postOffice);
        return orderService.save(order);
    }

    MyUser createUser(){
        MyUser myUser= new MyUser("name",
                "test@test.test",
                "$2a$08$PVL4km1oIGHK66PDPb0rK.bnb1QjrGhAO17V2UBpcEJES5eUs5k8G",
                100,
                true);
        return userService.save(myUser);
    }

    PhoneUser createPhone(MyUser myUser){

        PhoneUser phoneUser = new PhoneUser("1234567890",true," ",myUser);
        return phoneService.save(phoneUser);
    }

    AddressUser createAddress(MyUser myUser){
        AddressUser address = new AddressUser("region",
                "district" ,"city","address","postCode","info",true,myUser);
        return addressService.save(address);
    }

    PostOfficeUser createPostOffice(MyUser myUser){
        PostOfficeUser postOfficeUser = new PostOfficeUser("nova",true,myUser,"info");
        return postOfficeService.save(postOfficeUser);
    }
}