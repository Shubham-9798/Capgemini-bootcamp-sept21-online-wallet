package com.capgemini.onlinewallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinewallet.dao.DemoRepository;
import com.capgemini.onlinewallet.dto.UserCredential;
import com.capgemini.onlinewallet.dto.UserDto;

@Service
public class DemoService {
	
	@Autowired
	DemoRepository repo;
	
	public UserDto userLogin(UserCredential credential) {
		return repo.demoLogin(credential);
	}
}
