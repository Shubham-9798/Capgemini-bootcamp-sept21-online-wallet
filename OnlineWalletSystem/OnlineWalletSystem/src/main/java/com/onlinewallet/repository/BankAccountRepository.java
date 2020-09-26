package com.onlinewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinewallet.entities.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{
	
	@Query( value="select * from bank_account where wallet_account_wallet_id=:wallet_account_wallet_id", nativeQuery=true)
	public BankAccount findBankByWalletId(@Param("wallet_account_wallet_id")int wallet_account_wallet_id);
	
}