package com.example.demo.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.BiddingTransaction;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.BiddingRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class BiddingService {

	@Autowired
	BiddingRepository brepo;
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	UserRepository urepo;
	
	public BiddingTransaction findMaxBid(int P_Id)
	{
		return brepo.findMaxBid(P_Id);
	}
	
	public BiddingTransaction bid(int P_ID,int bidder_Id,float bid_price)
	{
		Product P_Id=prepo.findById(P_ID).get();
		User bidder_id=urepo.findById(bidder_Id).get();
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
		
		BiddingTransaction bt= new BiddingTransaction(P_Id,bidder_id,bid_price,date);
		
		return brepo.save(bt);
	}
}
