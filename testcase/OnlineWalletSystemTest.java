package org.com.onlinewallet.test.user;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.onlinewallet.entities.WalletAccount;
import com.onlinewallet.entities.WalletUser;


@ContextConfiguration
 public class OnlineWalletSystemTest {

	//static Logger logger=LoggerFactory.getLogger(OnlineWalletSystemTest.class);
	RestTemplate template;
		
		@BeforeEach
		public void setUp()
		{
			template=new RestTemplate();		
		}
		
		@Test
		public void addUser() {
			WalletUser user=new WalletUser();
			user.setUserId(903);
		user.setUserName("Manish Mittal");
		user.setPassword("Man13");		
		user.setPhoneNumber(1234567090);
			
			WalletUser user1=template.postForObject
					("http://localhost:9090/User/addUser", 
					user, WalletUser.class);
			Assertions.assertNotNull(user1);
			System.out.println("Add User Working "+user1);
			
		}
		

		
//		@Test
//		public void updateUser() {
//			WalletUser user=new WalletUser();
//			
//			user.setUserName("Manisha Mittal");
//			user.setPassword("Man132");
//			user.setPhoneNumber(1234967090);
//			String user1=template.put("http://localhost:9090/User/updateUser/902", user);
//					
//			Assertions.assertNotNull(user1);
//			System.out.println("Update User Working "+user1);
//		}
		
		@Test
		public void showUserByUserId() {
			
			WalletUser user =template.getForObject("http://localhost:9090/User/findUserById/1", WalletUser.class);
			Assertions.assertNotNull(user);
			System.out.println("!! Search user By User Id Works !!");
		}
		 
}