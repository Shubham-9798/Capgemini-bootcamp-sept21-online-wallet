package com.onlinewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinewallet.entities.WalletTransaction;

import java.util.List;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer>{

	@Query(value="select wt from WalletTransaction wt where wt.accountId= :accountId")
	public List<WalletTransaction> findAllByAccountId(@Param("accountId")int accountId);

}
