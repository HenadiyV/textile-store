package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.Basket;
import com.vognev.textilewebproject.model.BasketProduct;
import com.vognev.textilewebproject.repository.BasketProductRepository;
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
import static org.junit.jupiter.api.Assertions.*;

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
    private BasketProductRepository basketProductRepository;

    @Test
    void saveBasketProduct() {
        List<BasketProduct> basketProductList = basketProductRepository.findAll();
        Assert.assertEquals(6,basketProductList.size());
    }
    @Test
    void addBasketProduct() {
        Basket basket = basketService.getBasketToId(1L);
//        Assert.assertEquals(1,basket.getBasketProducts().size());
        Date dat = new Date();
        BasketProduct basketProduct = new BasketProduct(2L,1.0,60.0," "," ",dat,basket);
        basketProductRepository.save(basketProduct);

    }

    @Test
    void deleteBasketProduct() {
        BasketProduct basketProduct = basketProductRepository.getById(6L);
        basketProductRepository.delete(basketProduct);
        List<BasketProduct> basketProductList = basketProductRepository.findAll();
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
    public void test()throws Exception{
        assertThat(basketProductService).isNotNull();
    }
}