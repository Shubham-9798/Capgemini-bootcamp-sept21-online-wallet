  package com.onlinewallet.controller;

import com.onlinewallet.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.onlinewallet.entities.WalletUser;
import com.onlinewallet.exception.*;
import com.onlinewallet.service.IUserService;


@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired 
	IUserService walletService;
	
// create new wallet user 
@PostMapping("/addUser")
public WalletUser addUser(@RequestBody WalletUser user)
{
	System.out.println(user.toString());
   WalletUser add = new WalletUser();
   add = walletService.addUser(user);
      if (user == null)
      {
		throw new UserNotFoundException("Record Not Found");

	  } 
		return add;
}

// get user details on the basis of Id
@GetMapping("/findUserById/{id}")
@ExceptionHandler(UserNotFoundException.class)
public WalletUser showUserById(@PathVariable("id") int userId) 
{
		WalletUser user = new WalletUser();
		user = walletService.showUserById(userId);
        if (user == null)
        {
			throw new UserNotFoundException("Record Not Found");
        }
			return user;
}

//update user id on the basis of id
@PutMapping("/updateUser/{id}")
public String updateUser(@PathVariable(value = "id") int userId,@Validated @RequestBody WalletUser walletUser) 
{
	walletService.updateUser(userId, walletUser);
	return "User updated";
}

//check user login to the wallet portal
@GetMapping("/login/{loginName}/{password}")
public WalletUser validUserLogin(@PathVariable("loginName") String loginName,@PathVariable("password") String password)
{
	WalletUser user = walletService.checkUserLogin(loginName,password);
				if(user != null) {
					return user;
				}
				
	throw new UserNotFoundException("User Not Found");
}

//@ExceptionHandler({ InvalidPasswordException.class, UserNotFoundException.class })
//public ResponseEntity<Object> handleException(InvalidPasswordException msg) {
//	ResponseMsg object = new ResponseMsg();
//	object.setMsg(msg.getMessage());
//	object.setStatusCode("0");
//	
//	return new ResponseEntity<>(object, HttpStatus.valueOf(422));
//}

	
}
