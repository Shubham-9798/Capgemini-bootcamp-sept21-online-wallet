  package com.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.onlinewallet.entities.WalletUser;
import com.onlinewallet.exception.*;
import com.onlinewallet.service.IService;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired 
	IService walletService;
	
// create new wallet user 
@PostMapping("/addUser")
public WalletUser addUser(@RequestBody WalletUser user)
{
   WalletUser add = new WalletUser();
   add = walletService.addUser(user);
      if (user == null)
      {
		throw new EntityNotFoundException("Record Not Found");

	  } 
		return add;
}

// get user details on the basis of Id
@GetMapping("/findUserById/{id}")
@ExceptionHandler(EntityNotFoundException.class)
public WalletUser showUserById(@PathVariable("id") int userId) 
{
		WalletUser user = new WalletUser();
		user = walletService.showUserById(userId);
        if (user == null)
        {
			throw new EntityNotFoundException("Record Not Found");
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
public String validUserLogin(@PathVariable("loginName") String loginName,@PathVariable("password") String password)
{
	int x= walletService.checkUserLogin(loginName,password);
				if(x==1) {
					return "login successful";
				}
				else
					return "login denied";
			}
	
}
