package com.vognev.textilewebproject;

import com.vognev.textilewebproject.controller.UserController;
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
 * textilewebproject_3  01/11/2021-8:56
 */
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/create-product-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-product-after.sql", "/create-user-after.sql" },executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    public void userPageTest() throws Exception{
        this.mockMvc.perform(
                get("/user/profile"))
                .andDo(print())
                .andExpect(authenticated())
        .andExpect(xpath("//*[@id='navbarSupportedContent']/div").string("user"));
        ////*[@id="navbarSupportedContent"]/div
    }

    @Test
    public void nextTest1() throws Exception{
        this.mockMvc.perform(
                get("/admin"))
                .andDo(print());
    }
}
