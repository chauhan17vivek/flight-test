package com.flightNet.demo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightNet.demo.demo.dao.Dao;
@Service
public class DiscountServiceImpl implements DiscountService {

	
@Autowired
Dao dao;


public int getDiscount(String userType, int amount){
	int discount=dao.getDiscount(userType, amount);
	return amount-discount;
}
	
}
