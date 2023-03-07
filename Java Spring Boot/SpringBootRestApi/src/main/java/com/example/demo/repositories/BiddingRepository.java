package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.BiddingTransaction;

@Transactional
@Repository
public interface BiddingRepository extends JpaRepository<BiddingTransaction, Integer> {

	@Query(value = "select * from bidding_transaction\r\n"
			+ "where P_Id=:P_Id and bid_price = (select max(bid_price) \r\n"
			+ "								from bidding_transaction\r\n"
			+ "                                where P_Id=:P_Id)"
			+ "limit 1; ", nativeQuery=true)
	public BiddingTransaction findMaxBid(int P_Id);
	
	
//	public BiddingTransaction bid(int P_Id,int bidder_id);
}
