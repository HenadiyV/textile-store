package com.vognev.textilewebproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

/**
 * textilewebproject_3  01/11/2021-6:31
 */
@SpringBootTest
@AutoConfigureMockMvc

@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/create-user-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("admin")
    public void addProduct(){

    }
    @Test
    public void productListTest() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(xpath("//div[@id='product-list']/div").nodeCount(20));
    }
    @Test
    public void productListTest1() throws Exception{
        this.mockMvc.perform(
                get("/"))
                .andDo(print());
    }
}
