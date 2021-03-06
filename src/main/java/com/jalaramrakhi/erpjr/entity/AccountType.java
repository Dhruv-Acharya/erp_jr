package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AccountType {

	@Id
	@GeneratedValue
	private Long account_id;
	private String account_type;

	public AccountType() {
	}

	public AccountType(String account_type) {
		this.account_type = account_type;
	}

	/**
	 * @return the account_id
	 */
	public Long getAccount_id() {
		return account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	/**
	 * @return the account_type
	 */
	public String getAccount_type() {
		return account_type;
	}
	/**
	 * @param account_type the account_type to set
	 */
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
}
