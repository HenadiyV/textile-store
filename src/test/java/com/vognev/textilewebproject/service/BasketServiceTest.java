package com.vognev.textilewebproject.service;


import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.dto.BasketDto;
import com.vognev.textilewebproject.model.util.Constants;
import com.vognev.textilewebproject.repository.BasketRepository;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * textile-store  07/01/2022-11:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/create-user-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-product-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/create-product-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-basket-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/create-basket-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BasketServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postService;

    @Autowired
    private OrderService orderService;

    @Test
     void test()throws Exception{
        assertThat(basketService).isNotNull();
    }

    @Test
    void basketListToData()throws Exception{
//        Calendar calendar = new GregorianCalendar(2021,Calendar.APRIL,11);
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );// HH:mm:ss

            Date dt=simpleDateFormat.parse("2021-04-20");

        Date dat1 = new Date();

        System.out.println("dat1 "+simpleDateFormat.format(dat1));

        List<Basket> basketList=basketRepository.searchBasketToClearDate(dt);
        Assert.assertEquals(2,basketList.size());

       List<Basket> basketList1=basketRepository.searchBasketToClearDate(simpleDateFormat.parse(simpleDateFormat.format(dat1)));//"2022-01-07"
        Assert.assertEquals(0,basketList1.size());

        Assert.assertEquals(0,basketService.getBasketListToClearDate().size());
    }

    @Test
    void basketListDb()throws Exception{
        Assert.assertNotNull(basketService.getBasketList());
    }

    @Test
    void basketList()throws Exception{
        Assert.assertEquals(6,basketService.getBasketList().size());
    }

    @Test
    void getOneBasket()throws Exception {
        Basket basket = basketService.getBasketToId(1L);
        Assert.assertNotNull(basket);
    }

    @Test
    void deleteBasket()throws Exception{
// добавил в application-test.properties
// строчку : spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
        basketService.basketDelete(basketService.getBasketToId(6L));

        Assert.assertEquals(5,basketService.getBasketList().size());
    }

    @Test
    void addBasket(){

        createBasket();

        List<Basket> basketList = basketService.getBasketList();
        Assert.assertEquals(7,basketList.size());
    }

    @Test
    void getBasketByIdFromTrue()throws Exception{
        Basket basket = basketService.getBasketToId(1L);
        Assert.assertNotNull(basket);
    }

    @Test
    void getBasketByIdFromFalse()throws Exception{
        Basket basket = basketService.getBasketToId(100L);
        System.out.println(basket);
        Assert.assertNull(basketService.getBasketToId(100L));
    }

    @Test
    void deleteRancidCookies()throws Exception{

        createBasket();

        List<Basket> basketList = basketService.getBasketList();
        Assert.assertEquals(7,basketList.size());

        basketService.deleteRancidCookies();

        List<Basket> basketList1 = basketService.getBasketList();
        Assert.assertEquals(6,basketList1.size());
    }

    @Test
    void addBasketToUser()throws Exception{
        basketService.createOrder(new BasketDto("test","123321"," ","8888888888888888888",2L));
//        MyUser user =  new MyUser("test","test@test.test","1",100,true);
//        PhoneUser phoneUser=new PhoneUser("123321",true,user);
//        AddressUser addressUser = new AddressUser("city","address","1234567890"," ",user);
//        PostOfficeUser postOfficeUser = new PostOfficeUser("nova",true,user," ");
        //List<MyUser> userList = userService.findAll();
        Assert.assertEquals(5,userService.findAll().size());
        Assert.assertEquals(5,addressService.findAll().size());
        Assert.assertEquals(7,phoneService.findAll().size());
        Assert.assertEquals(5,postService.findAll().size());


    }

    @Test
    void addBasketToUserAddress()throws Exception{
        basketService.createOrder(new BasketDto("test","123321"," ","8888888888888888888",2L));

        Assert.assertEquals(5,addressService.findAll().size());

    }
    @Test
    void addBasketToUserPhone()throws Exception{
        basketService.createOrder(new BasketDto("test","123321"," ","8888888888888888888",2L));

        Assert.assertEquals(7,phoneService.findAll().size());

    }
    @Test
    void addBasketToUserPostOffice()throws Exception{
        basketService.createOrder(new BasketDto("test","123321"," ","8888888888888888888",2L));

        Assert.assertEquals(6,postService.findAll().size());
    }

    @Test
    void addBasketToOrder()throws Exception{
        basketService.createOrder(new BasketDto("test","123321"," ","8888888888888888888",2L));

        Assert.assertEquals(3,orderService.findAll().size());
    }

    @Test
    void getClearDatList()throws Exception{
        Date dat = new Date();
        Assert.assertEquals(6,basketRepository.searchBasketToClearDate(dat).size());
    }

    Basket createBasket(){
        Date dat = new Date();
        long today=dat.getTime();
        Date today1=new Date(today);
        Basket basket = new Basket(null,"7777777777777777","test","12345"," ",
                dat,today1,2L,null);
     return   basketService.saveBasket(basket);
    }
}