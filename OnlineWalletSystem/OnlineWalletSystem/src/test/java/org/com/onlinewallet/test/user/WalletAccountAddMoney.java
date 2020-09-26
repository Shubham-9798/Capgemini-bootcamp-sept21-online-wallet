package org.com.onlinewallet.test.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.onlinewallet.entities.WalletAccount;
import com.onlinewallet.entities.WalletUser;

@ContextConfiguration
public class WalletAccountAddMoney {
RestTemplate template;
	
	@BeforeEach
	public void setUp()
	{
		template=new RestTemplate();		
	}
	
//	@Test
//	public void updateBalance() {
//		
//		
//		WalletAccount wallet =template.getForObject("http://localhost:9090/WalletAccount/addMoney/62484/5000", WalletAccount.class);
//		Assertions.assertNotNull(wallet);
//		
//		System.out.println("Add Amount Working "+wallet);
//		
//	}
}
