package com.onlinewallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinewallet.entities.WalletUser;

@Repository
public interface UserRepository  extends JpaRepository< WalletUser ,Integer> {
		
		@Query( value="select user_id from wallet_user where user_name=:userName and user_password=:password", nativeQuery=true)
		public Optional<WalletUser> checkUserLogin(String userName, String password);
		
		

	

	
}
