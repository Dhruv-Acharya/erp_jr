package com.jalaramrakhi.erpjr.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long account_code;
	private String account_name;
	@OneToOne
	@JoinColumn(name = "account_type")
	private AccountType accountType;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public Account() {
	}

	public Account(Long account_code, String account_name, AccountType accountType, Company company) {
		this.account_code = account_code;
		this.account_name = account_name;
		this.accountType = accountType;
		this.company = company;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * @return the account_code
	 */
	public Long getAccount_code() {
		return account_code;
	}
	/**
	 * @param account_code the account_code to set
	 */
	public void setAccount_code(Long account_code) {
		this.account_code = account_code;
	}
	/**
	 * @return the account_name
	 */
	public String getAccount_name() {
		return account_name;
	}
	/**
	 * @param account_name the account_name to set
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	
	
}
