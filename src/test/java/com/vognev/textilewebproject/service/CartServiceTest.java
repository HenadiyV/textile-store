package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.Cart;
import com.vognev.textilewebproject.model.Order;
import com.vognev.textilewebproject.model.dto.CartDto;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * textile-store  11/01/2022-10:13
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
class CartServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Test
    void exitCartService()throws Exception{
        assertThat(cartService).isNotNull();
    }
    @Test
    void exitOrderService()throws Exception{
        assertThat(orderService).isNotNull();
    }

    @Test
    void addCart()throws Exception{
        createCart(1L);
        Assert.assertEquals(6,cartService.findAll().size());
    }

    @Test
    void deleteCart()throws Exception{
        Cart cart = cartService.getById(1L);
        cartService.deleteCart(cart);
        Assert.assertEquals(4,cartService.findAll().size());
    }

    @Test
    void notCartById()throws Exception{
        Assert.assertNull(cartService.getById(100L));
    }

    @Test
    void updateCart()throws Exception{
        Cart cart = cartService.getById(1L);
        cart.setNameProduct("test");
        cartService.save(cart);
        Assert.assertEquals("test",cartService.getById(1L).getNameProduct());
    }

    @Test
    void addCartProductChangeSize()throws Exception{
        addCartToOrder(1L);
        Assert.assertEquals(31,productService.getById(1L).getSelling_size(),0.1);
    }
    @Test
    void deleteCartProductChangeSize()throws Exception{
        Cart cart = cartService.getById(1L);
            cartService.deleteCart(cart);
        //cartService.
        Assert.assertEquals(0.0,productService.getById(1L).getSelling_size(),0.1);
    }

    Cart createCart(Long orderId){
        Order order = orderService.getOrderById(orderId);
        //cartService.createCartByOrder
        Date dat = new Date();
        Cart cart = new Cart(
                1L,
                110.0,
                "Name",
                1.0,
                0.0,
                "name",
                dat,order);
        return cartService.save(cart);
    }

    void addCartToOrder(long orderId){
        Order order = orderService.getOrderById(orderId);
        List<CartDto>cartDtoList= new ArrayList<>();
        CartDto cartDto = new CartDto(null,
                order.getId(),
                1L,
                "Name",
                110.0,
                1.0, 19.0,
                1.0,
                110.0,
                0.0, "inf","n_img");
        cartDtoList.add(cartDto);
        cartService.createCartByOrder(order,cartDtoList);
    }
}