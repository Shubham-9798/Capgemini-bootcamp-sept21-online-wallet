package org.com.onlinewallet.test.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.onlinewallet.entities.WalletAccount;

@ContextConfiguration
public class WalletAccountTest {
	RestTemplate template;
	
	@BeforeEach
	public void setUp()
	{
		template=new RestTemplate();		
	}
	
	
	@Test
	public void showAccountById() {
		WalletAccount wallet =template.getForObject("http://localhost:9090/WalletAccount/seeWalletAccount/62484", WalletAccount.class);
		Assertions.assertNotNull(wallet);
		System.out.println("!! Search wallet account By Wallet Id Works !!");
	}
	
	
  @Test
	public void showAccountByUserId() {
		
		WalletAccount wallet =template.getForObject("http://localhost:9090/WalletAccount/seeWalletAccountByUser/1", WalletAccount.class);
		Assertions.assertNotNull(wallet);
		System.out.println("!! Search wallet account By User Id Works !!");
	}
	
	
	
}
