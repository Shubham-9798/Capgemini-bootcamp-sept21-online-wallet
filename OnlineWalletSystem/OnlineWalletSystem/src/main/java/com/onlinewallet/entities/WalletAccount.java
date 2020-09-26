package com.onlinewallet.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class WalletAccount {
	
	@Id
	@Column(length=5,name="WalletId")
	private int walletId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	Status status;

	
	@Column(name="AccountBalance")
	private double accountBalance;
	

	public int getAccountId() {
		return walletId;
	}

	public void setAccountId(int accountId) {
		this.walletId = accountId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UserId")
	@JsonBackReference
	private WalletUser walletUser;

	@OneToOne(cascade = CascadeType.ALL,mappedBy="walletAccount")
	@JoinColumn(name = "AccountNo")
	@JsonManagedReference
	private BankAccount bankAccount;

	public WalletAccount(int accountId, Status status, double accountBalance, WalletUser walletUser,
			BankAccount bankAccount) {
		super();
		this.walletId = accountId;
		this.status = status;
		this.accountBalance = accountBalance;
		this.walletUser = walletUser;
		this.bankAccount = bankAccount;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public WalletUser getWalletUser() {
		return walletUser;
	}

	public void setWalletUser(WalletUser walletUser) {
		this.walletUser = walletUser;
	}

	public WalletAccount() {
		
	}
	
	
}
