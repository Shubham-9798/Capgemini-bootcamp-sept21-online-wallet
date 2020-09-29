package com.onlinewallet.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinewallet.entities.BankAccount;
import com.onlinewallet.entities.WalletAccount;
import com.onlinewallet.entities.WalletTransaction;
import com.onlinewallet.entities.WalletUser;

public interface IService {
	
	WalletUser showUserById(int userId);

	WalletUser addUser(WalletUser user);

	String updateUser(int userId, WalletUser walletUser);

	WalletUser checkUserLogin(String loginName, String password);
	
	BankAccount addBankAccount(BankAccount bankAccount);

	BankAccount findBankAccount(int walletId);

	BankAccount showBankAccount(int walletId);
	
	WalletAccount showAccountById(int senderId);

	double updateBalance(WalletAccount senderAccount);

	WalletAccount createWalletAccount(WalletAccount account);

	WalletAccount showAccount(int userId);

	double getBalance(int senderId);
	
	
	public void createTransaction(int senderId,WalletTransaction transaction);
	
	public List<WalletTransaction> findAllByAccountId(@Param("accountId")int accountId);
	
	List<WalletTransaction> getTransactionList(int accountId);
}
