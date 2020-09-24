package com.capgemini.onlinewallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinewallet.dto.UserCredential;
import com.capgemini.onlinewallet.dto.UserDto;
import com.capgemini.onlinewallet.entity.User;

@Repository
public interface DemoRepository extends JpaRepository<User, Integer> {
	UserDto demoLogin(UserCredential credential);
}