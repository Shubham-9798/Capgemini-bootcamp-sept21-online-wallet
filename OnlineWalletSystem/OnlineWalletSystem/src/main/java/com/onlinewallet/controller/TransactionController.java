package com.onlinewallet.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinewallet.entities.WalletTransaction;
import com.onlinewallet.exception.LowBalanceException;
import com.onlinewallet.service.IUserService;


@RestController
public class TransactionController {
	@Autowired 
	IUserService walletService;


// transfer the fund from one account to the other account
@PostMapping("/transaction/{id}")
public String transferFund(@PathVariable("id") int senderId, @RequestBody WalletTransaction transaction)throws LowBalanceException 
{
		double availableBalance = walletService.getBalance(senderId);
		System.out.println(availableBalance);
		System.out.println(transaction.getAmount());
		if (availableBalance >= transaction.getAmount()) 
		{
			walletService.createTransaction(senderId, transaction);
			return "Transaction Created";
		}
		else 
		{
			throw new LowBalanceException("Low Balance Exception...");
		}
	}

// this method is used to display the list of transactions done by a user
@GetMapping("/transactionList/{accountId}")
public List<WalletTransaction> getTransactionList(@PathVariable int accountId){
		List<WalletTransaction> list = walletService.getTransactionList(accountId);
		if(list == null)
			return new ArrayList<>();
		else
			return list;
	
}
}
