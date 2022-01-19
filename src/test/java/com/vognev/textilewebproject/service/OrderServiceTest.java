package com.vognev.textilewebproject.service;


import com.vognev.textilewebproject.model.*;
import com.vognev.textilewebproject.model.util.Constants;
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

import static java.awt.SystemColor.info;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * textile-store  10/01/2022-21:30
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
class OrderServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PostOfficeService postOfficeService;


    @Test
     void testOrderService()throws Exception{
        assertThat(orderService).isNotNull();
    }

    @Test
    void orders()throws Exception{
        Assert.assertEquals(2,orderService.findAll().size());
    }

    @Test
    void addOrder()throws Exception{
        create(3L);
        Assert.assertEquals(3,orderService.findAll().size());
    }

    @Test
    void getOrderListByUserId()throws Exception{

        Assert.assertEquals(1,orderService.getListOrderByUserId(2L).size());
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

        PhoneUser phoneUser = new PhoneUser("1234567890",true,myUser);
        return phoneService.save(phoneUser);
    }

    AddressUser createAddress(MyUser myUser){
        AddressUser address = new AddressUser("city","address","postCode","info",true,myUser);
        return addressService.save(address);
    }

    PostOfficeUser createPostOffice(MyUser myUser){
        PostOfficeUser postOfficeUser = new PostOfficeUser("nova",true,myUser,"info");
        return postOfficeService.save(postOfficeUser);
    }
}
