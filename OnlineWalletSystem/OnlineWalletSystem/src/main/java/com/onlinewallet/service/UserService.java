package com.onlinewallet.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlinewallet.entities.BankAccount;
import com.onlinewallet.entities.WalletAccount;
import com.onlinewallet.entities.WalletTransaction;
import com.onlinewallet.entities.WalletUser;
import com.onlinewallet.exception.InvalidPasswordException;
import com.onlinewallet.exception.UserNotFoundException;
import com.onlinewallet.repository.BankAccountRepository;
import com.onlinewallet.repository.UserRepository;
import com.onlinewallet.repository.WalletAccountRepository;
import com.onlinewallet.repository.WalletTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService{
	
	@Autowired
	BankAccountRepository bankAccountRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	WalletTransactionRepository walletTransactionRepository;
	
	@Autowired
	WalletAccountRepository walletAccountRepository;

	
	//---------------------------------------------
	//register new user	
	public WalletUser addUser(WalletUser user)
	{
		Optional<WalletUser> findById= userRepository.findById(user.getUserId());
			if(!findById.isPresent()) 
			{
				WalletUser newUser=userRepository.save(user);
				return newUser;
			}
			return null;
	}
		
	//returns user by Id
	public WalletUser showUserById(int userId) 
	{
	   Optional<WalletUser> findById = userRepository.findById(userId);
		  if (findById.isPresent()) 
		  {
			 WalletUser user = findById.get();
				return user;
		  }
				return null;
	}
			
	// update data of user
	public String updateUser(int userId, WalletUser walletUser) 
	{
	   Optional<WalletUser> findById = userRepository.findById(userId);
			if (findById.isPresent())
			{
			  WalletUser user = findById.get();
			  user.setUserName(walletUser.getUserName());
			  user.setPassword(walletUser.getPassword());
			  user.setPhoneNumber(walletUser.getPhoneNumber());
			  userRepository.save(walletUser);
				return "User Updated";
			 }
			else 
			{
				throw new UserNotFoundException("User Not Found for Id " + userId);
			}
				
	}

	//authenticate user to login by using name and password
	public WalletUser checkUserLogin(String loginName,String password) 
	{
		Optional<WalletUser> user = userRepository.checkUserLogin(loginName,password);
			if(user.isPresent()) 
			 {
					return user.get();
			 }
			else
		 	 throw new InvalidPasswordException("User Not Found");
	}
	
	//------------------------------------------------------
	//add bank account to wallet
	@Override
	public BankAccount addBankAccount(BankAccount bank)
		{
			BankAccount bankAccount=null;
			Random rand = new Random(); 
			bank.setAccountNo(rand.nextInt(100000));	
		    bankAccount=bankAccountRepository.save(bank);
			return bankAccount;
		}

	// show account details on the basis of id
	public BankAccount showBankAccount(int walletId) 
	{
			WalletAccount bankId=walletAccountRepository.findById(walletId).get();
			Optional<BankAccount> findById = bankAccountRepository.findById(bankId.getAccountId());
			if (findById.isPresent()) 
			{
				BankAccount user = findById.get();
				return user;
			}
			return null;	
	}

	//search bank account on the basis of id
	public BankAccount findBankAccount(int walletId)
	{
			Optional<WalletAccount> findById = walletAccountRepository.findById(walletId);
		      if (findById.isPresent())
		      {
				BankAccount account = bankAccountRepository.findBankByWalletId(walletId);
			    return account;
		       }
		return null;
	}
	//------------------------------------------------------
	//create wallet account
	public WalletAccount createWalletAccount(WalletAccount account) 
	{
			WalletAccount wa = null;
			wa = walletAccountRepository.save(account);
			return wa;
	}
		
	//update wallet account
	public WalletAccount updateWalletAccount(WalletAccount account) 
	{
			WalletAccount walletAccount = null;
			walletAccount = walletAccountRepository.save(account);
			return walletAccount;
	}

	//show user account details on the basis of id
	public WalletAccount showAccount(int userId)
	{
			Optional<WalletUser> findById = userRepository.findById(userId);
		if (findById.isPresent())
		{
			WalletAccount wallet = walletAccountRepository.findWalletByUserId(userId);
			return wallet;
		}
		return null;
	}
		
	//show wallet account on the basis of id
	public WalletAccount showAccountById(int userId) 
	{
	Optional<WalletAccount> findById = walletAccountRepository.findById(userId);
		if (findById.isPresent()) 
		{
			WalletAccount user = findById.get();
			return user;
		}
	return null;
	}

	//find bank exists in wallet
	public boolean findBankToWallet(int walletAccountId) 
	{
	boolean flag = false;
			flag = bankAccountRepository.existsById(walletAccountId);
			return flag;
	}

	//get sender  balance on the basis of id
	public double getBalance(int accountId)
	{
	double balance = 0;
	balance = walletAccountRepository.findById(accountId).get().getAccountBalance();
	return balance;
	}

	//update wallet account balance 
	public double updateBalance(WalletAccount account)
	{
		double totalBalance = 0;
			walletAccountRepository.save(account);
			totalBalance = this.getBalance(account.getAccountId());
	return totalBalance;

	}
	
	// --------------------------------
	@Override
	public void createTransaction(int senderId,WalletTransaction transaction)
	{
			   LocalDateTime now = LocalDateTime.now();  
			   WalletAccount senderAccount = new WalletAccount();
			   WalletAccount receiverAccount = new WalletAccount();
			   
				 int recipientAccountId = transaction.getReceiverAccountId();
			     double transferAmount = transaction.getAmount();
				 senderAccount = this.showAccountById(senderId);
				 System.out.println(senderAccount);
				 
				 double senderPrevBalance = senderAccount.getAccountBalance();
				 receiverAccount = this.showAccountById(transaction.getReceiverAccountId());
				 
				 double receiverPrevBalance = receiverAccount.getAccountBalance();
				 double senderFinalBalance  = senderPrevBalance - transferAmount;
				 double receiverfinalBalance = receiverPrevBalance + transferAmount;
				 senderAccount.setAccountBalance(senderFinalBalance);
				 receiverAccount.setAccountBalance(receiverfinalBalance);
				
				 senderFinalBalance = this.updateBalance(senderAccount);
				 receiverfinalBalance = this.updateBalance(receiverAccount);
				 transaction.setAccountId(senderId);
			     transaction.setAccountBalance(senderFinalBalance);
				 transaction.setDateOfTransaction(now);
				 walletTransactionRepository.save(transaction);	
	}
	 
	//list all the transactions done in wallet
	@Override
		public List<WalletTransaction> getTransactionList(int accountId) {
	   	Optional<WalletAccount> isUser = walletAccountRepository.findById(accountId);
	   	if(isUser.isPresent())
			    return walletTransactionRepository.findAllByAccountId(accountId);
	   	else	
				return null;
	}
		@Override
		public List<WalletTransaction> findAllByAccountId(int accountId) {
			// TODO Auto-generated method stub
			return null;
		}
}
