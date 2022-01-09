package com.vognev.textilewebproject;

import com.vognev.textilewebproject.controller.MainController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

/**
 * textilewebproject_3  01/11/2021-6:56
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MainController controller;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(
                get("/")).
                andDo(print()).
                andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, guest")))
                .andExpect(content().string(containsString("Вхід")));
    }

    @Test
    public void accesDeniedTest() throws Exception{
        this.mockMvc.perform(
                get("/admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void correctLogin() throws Exception{
        this.mockMvc.perform(formLogin().user("user").password("1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentails() throws Exception{
        this.mockMvc.perform(post("/login").param("user","Alfred"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


    @Test
    public void nextTest() throws Exception{

    }

}
