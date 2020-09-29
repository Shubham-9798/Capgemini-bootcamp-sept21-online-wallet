package com.onlinewallet.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinewallet.entities.Status;
import com.onlinewallet.entities.WalletAccount;
import com.onlinewallet.entities.WalletUser;
import com.onlinewallet.exception.*;
import com.onlinewallet.service.IUserService;


@RestController
@RequestMapping("/WalletAccount")
public class WalletController{
	@Autowired 
	IUserService walletService;


//create walletUser account
@GetMapping(value = "/create/{userId}")
public Integer createWalletUser(@PathVariable int userId) {
	WalletAccount account = new WalletAccount();
	WalletUser user=new WalletUser();
	user=walletService.showUserById(userId);
	Random random=new Random();
	account.setAccountId(random.nextInt(100000));
	account.setStatus(Status.ACTIVE);
	account.setAccountBalance(0);
	account.setWalletUser(user);
	account = walletService.createWalletAccount(account);
	return account.getAccountId();
}

//show account details on the basis of id
@GetMapping("/seeWalletAccount/{id}")
@ExceptionHandler(UserNotFoundException.class)
public WalletAccount showAccount(@PathVariable("id") int walletId) 
{
	WalletAccount wallet = new WalletAccount();
	wallet = walletService.showAccountById(walletId);
      if (wallet == null)
      {
			throw new UserNotFoundException("Record Not Found");
      }
	return wallet;
}

// add money to wallet
@GetMapping("/addMoney/{accountId}/{amount}")
public double addMoney(@PathVariable int accountId, @PathVariable double amount) 
{
	double finalBalance = 0;
		WalletAccount account = walletService.showAccountById(accountId);
		double prevBalance = account.getAccountBalance();
		finalBalance  = prevBalance + amount;
		account.setAccountBalance(finalBalance);
		finalBalance = walletService.updateBalance(account);
return finalBalance;
}
}
