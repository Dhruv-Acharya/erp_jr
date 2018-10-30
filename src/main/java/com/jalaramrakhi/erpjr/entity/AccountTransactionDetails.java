package com.jalaramrakhi.erpjr.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccountTransactionDetails {

	@Id
	private Integer transaction_id;
	@ManyToOne
	private Account account;
	private double transaction_amount;
	private Date transaction_date;
	private String transaction_narration;


	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	/**
	 * @return the transaction_amount
	 */
	public double getTransaction_amount() {
		return transaction_amount;
	}
	/**
	 * @param transaction_amount the transaction_amount to set
	 */
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	/**
	 * @return the transaction_date
	 */
	public Date getTransaction_date() {
		return transaction_date;
	}
	/**
	 * @param transaction_date the transaction_date to set
	 */
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	/**
	 * @return the transaction_narration
	 */
	public String getTransaction_narration() {
		return transaction_narration;
	}
	/**
	 * @param transaction_narration the transaction_narration to set
	 */
	public void setTransaction_narration(String transaction_narration) {
		this.transaction_narration = transaction_narration;
	}
}
