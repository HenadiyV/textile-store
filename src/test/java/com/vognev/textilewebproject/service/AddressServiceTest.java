package com.vognev.textilewebproject.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * textile-store  13/01/2022-11:54
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
class AddressServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AddressService addressService;

    @Test
     void testAddressService()throws Exception{
        assertThat(addressService).isNotNull();
       // Assert.assertEquals(4,addressService.getAll().size());
    }
    @Test
     void addressAll()throws Exception{

       // Assert.assertEquals(4,addressService.getAll().size());
    }
}
