package com.flightNet.demo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightNet.demo.demo.services.DiscountService;

@RestController
public class DiscountController {
	
	
	@Autowired
	DiscountService discountService;
	
    @RequestMapping(value="/discount", method=RequestMethod.GET)
	public int getDiscount(@RequestParam String userType, @RequestParam int amount){
    	return discountService.getDiscount(userType, amount);
	}
	
     
}
