package com.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlinewallet.entities.BankAccount;
import com.onlinewallet.entities.WalletAccount;
import com.onlinewallet.exception.*;
import com.onlinewallet.service.IService;

@RestController
@RequestMapping("/BankAccount")
public class BankAccountController {
	
	@Autowired 
	IService walletService;
	

	
//add bank account to the wallet
@PostMapping("/addAccount/{walletId}")
public String addBankAccount(@PathVariable int walletId,@RequestBody BankAccount bankAccount) 
	{
		BankAccount bank=new BankAccount();
		WalletAccount account = new WalletAccount();
		account=walletService.showAccountById(walletId);
		bankAccount.setWalletAccount(account);
		bank = walletService.addBankAccount(bankAccount);
		return "bank account added successfully" +" "+bank.getAccountNo();
	}
		
//find the bank account on the basis of wallet id
@GetMapping("/findBankAccount/{walletId}")
public BankAccount findBankAccount(@PathVariable int walletId) 
	{
		BankAccount bank = new BankAccount();
		bank=walletService.findBankAccount(walletId);
			if (bank == null)
			{
				throw new UserNotFoundException("Record Not Found");
		    }
		return bank;
	}

}
