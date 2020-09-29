package org.com.onlinewallet.test.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.onlinewallet.entities.BankAccount;
import com.onlinewallet.entities.WalletAccount;

@ContextConfiguration
public class BankAccountTest {
	RestTemplate template;
	
	@BeforeEach
	public void setUp()
	{
		template=new RestTemplate();		
	}
	
	
	@Test
	public void findBankByWalletId() {
		BankAccount bank =template.getForObject("http://localhost:9090/BankAccount/findBankAccount/42357", BankAccount.class);
		Assertions.assertNotNull(bank);
		System.out.println("!! Search wallet account By Wallet Id Works !!");
	}
	
	
 
	
	
}
