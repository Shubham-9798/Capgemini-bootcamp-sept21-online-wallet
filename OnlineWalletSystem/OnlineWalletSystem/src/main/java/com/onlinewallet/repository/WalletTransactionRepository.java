package com.onlinewallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinewallet.entities.WalletTransaction;

import java.util.List;

@Repository("walletTransactionRepository")
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer>{

	List<WalletTransaction> findAllByAccountId(int accountId);

}
