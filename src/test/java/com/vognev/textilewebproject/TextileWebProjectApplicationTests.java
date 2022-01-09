package com.vognev.textilewebproject;

//import com.vognev.textilewebproject.controller.MessageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user")
@TestPropertySource("/application-test.properties")
class TextileWebProjectApplicationTests {
//@Autowired
//private MessageController messageController;

	@Autowired
	private MockMvc mockMvc;
	@Test
	void test() throws Exception{
	//	assertThat(messageController).isNotNull();
	}
	@Test
	void contextLoads() throws Exception{
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, guest")));
				//.andExpect(content().string(containsString("Будьласка, залогіньтесь")));
	}

	@Test
	public void accessDeniedTest() throws Exception{
		this.mockMvc.perform(get("/main"))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	@Sql(value = {"/create-user-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void correctLoginTest() throws Exception{
		this.mockMvc.perform(formLogin().user("user").password("1"))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

	}
	@Test
	public void badCredentials() throws Exception{
		this.mockMvc.perform(post("/login").param("user","Bla-Bla"))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
}
