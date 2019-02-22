package com.flightNet.demo.demo.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.flightNet.demo.demo.services.DiscountService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DiscountController.class, secure = false)
public class DiscountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiscountService discountService;
	
	int discountedAmount=1045;
	
	@Test
	public void getDiscount() throws Exception {

		Mockito.when(
				discountService.getDiscount(Mockito.anyString(),
						Mockito.anyInt())).thenReturn(discountedAmount);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/discount?userType=Standard&amount=5500");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "1045";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	

}
