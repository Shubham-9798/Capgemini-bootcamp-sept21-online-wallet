package com.onlinewallet.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BankAccount {
	
	@Id
	@Column(name = "BankAccountNo")
	private int bankAccountNo;
	
	@Column(name = "HolderName")
	private String holderName;
	
	@Column(name = "IFSCCode")
	private String ifscCode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private WalletAccount walletAccount;

	public WalletAccount getWalletAccount() {
		return walletAccount;
	}

	public void setWalletAccount(WalletAccount walletAccount) {
		this.walletAccount = walletAccount;
	}

	public BankAccount(int accountNo, String holderName, String ifscCode, String bankName,
			WalletAccount walletAccount) {
		super();
		this.bankAccountNo = accountNo;
		this.holderName = holderName;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.walletAccount = walletAccount;
	}

	@Column(name = "BankName")
	private String bankName;

	public int getAccountNo() {
		return bankAccountNo;
	}

	public void setAccountNo(int i) {
		this.bankAccountNo = i;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BankAccount() {
		super();
	}
	
	public BankAccount(int accountNo, String holderName, String ifscCode, String bankName) {
		super();
		this.bankAccountNo = accountNo;
		this.holderName = holderName;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
	}
	
	
	


	
	

}
