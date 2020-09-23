package com.capgemini.onlinewallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.onlinewallet.entity.User;
import com.capgemini.onlinewallet.service.DemoService;

import static org.mockito.Mockito.*;

@SpringBootTest
class OnlinewalletApplicationTests {
	DemoService demoservice;
	User user;
	
	@Autowired
	public void setDemoservice(DemoService demoservice) {
		this.demoservice = demoservice;
	}


	public static void main(String[] args){
		  OnlinewalletApplicationTests tester = new OnlinewalletApplicationTests();
	      tester.setUp();
//	      System.out.println(tester.testMarketValue()?"pass":"fail");
	   }
	  
	  
	   public void setUp(){
		      user = new User(); 
		     
		      //Create the mock object of stock service
		      demoservice = mock(DemoService.class);		
		  
		      //set the stockService to the portfolio

		   }

}




