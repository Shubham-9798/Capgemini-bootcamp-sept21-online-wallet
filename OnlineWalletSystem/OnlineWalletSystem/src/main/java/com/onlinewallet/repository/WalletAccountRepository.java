package com.onlinewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinewallet.entities.*;

@Repository
public interface WalletAccountRepository extends JpaRepository<WalletAccount,Integer>{

	@Query( value="select * from wallet_account where user_id=:user_id", nativeQuery=true)
	public WalletAccount findWalletByUserId(@Param("user_id")int user_id);
}
