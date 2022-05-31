package com.nagarro.assignment.accountstatements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.nagarro.assignment.accountstatements.config.JwtTokenUtil;
import com.nagarro.assignment.accountstatements.controller.dtos.AccountStatementRequest;
import com.nagarro.assignment.accountstatements.service.AccountStatementsService;
import com.nagarro.assignment.accountstatements.service.JwtUserDetailsService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountStatementsTests {

	@MockBean
	private AccountStatementsService accountStatementsService;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void fetchStatementForUser() throws Exception {
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername("user");

		final String token = jwtTokenUtil.generateToken(userDetails);
		
        AccountStatementRequest accountStatementRequest = new AccountStatementRequest();
		accountStatementRequest.setAccountNumber("0012250016001");
		
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customer/fetchStatement")
        		.header("authorization", "Bearer " + token)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(new Gson().toJson(accountStatementRequest)))
        		.andExpect(MockMvcResultMatchers.status().is(200))
        		.andReturn();
        
        assertEquals(result.getResponse().getStatus(), 200);
        	
    }
	
	@Test
    public void fetchStatementForUserNegative() throws Exception {
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername("user");

		final String token = jwtTokenUtil.generateToken(userDetails);
		
        AccountStatementRequest accountStatementRequest = new AccountStatementRequest();
		accountStatementRequest.setAccountNumber("0012250016001");
		accountStatementRequest.setFromAmount("200");
		
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customer/fetchStatement")
        		.header("authorization", "Bearer " + token)
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(new Gson().toJson(accountStatementRequest)))
        		.andExpect(MockMvcResultMatchers.status().is(200))
        		.andReturn();
        
        assertEquals(result.getResponse().getStatus(), 200);
        	
    }

	
}
