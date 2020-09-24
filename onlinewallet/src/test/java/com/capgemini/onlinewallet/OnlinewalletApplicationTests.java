package com.capgemini.onlinewallet;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.onlinewallet.entity.User;
import com.capgemini.onlinewallet.dao.*;
import com.capgemini.onlinewallet.dto.UserCredential;
import com.capgemini.onlinewallet.dto.UserDto;
import com.capgemini.onlinewallet.service.DemoService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OnlinewalletApplicationTests {
	@InjectMocks
	private DemoService demoservice;
	
	@Mock
	private DemoRepository demorepository;

	@Test
	public void loginUserTest_02() {
		   when(demorepository.demoLogin(new UserCredential("user", "password")))
		   .thenReturn(new UserDto("id","user", "user")); // id,user name,role
		   
		   assertEquals(new UserDto("id","user", "user"), demoservice.userLogin(new UserCredential("user", "password")));
	   }
	   
	@Test
    public void loginUserTest_03() {
		   when(demorepository.demoLogin(new UserCredential("invalid", "invalid")))
		   .thenReturn(null); // id,user name,role
		   
		   assertEquals(null, demoservice.userLogin(new UserCredential("user", "password")));
	   }
	   

}




