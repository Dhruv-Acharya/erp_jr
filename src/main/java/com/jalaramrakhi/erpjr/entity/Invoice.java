package com.jalaramrakhi.erpjr.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Invoice {
	@Id
	@GeneratedValue
	private Long invoice_number;
	@OneToOne
	private Account debit_account;
    private Long invoice_amount;
    private Date invoice_date;
    private String invoice_narration;
    @OneToOne
	private Account credit_account;
    @OneToOne
    private User preparedBy;
    @OneToOne
	@JoinColumn(name = "company_id")
	private Company company;
	private Long invoice_LR_number;
	private String invoice_transport;
	private Long invoice_phone_number;
	@OneToOne
	private Address address;

	public Invoice() {
	}

	public Invoice(Account debit_account, Long invoice_amount, Date invoice_date, String invoice_narration, Account credit_account, User preparedBy, Company company, Long invoice_LR_number, String invoice_transport, Long invoice_phone_number, Address address) {
		this.debit_account = debit_account;
		this.invoice_amount = invoice_amount;
		this.invoice_date = invoice_date;
		this.invoice_narration = invoice_narration;
		this.credit_account = credit_account;
		this.preparedBy = preparedBy;
		this.company = company;
		this.invoice_LR_number = invoice_LR_number;
		this.invoice_transport = invoice_transport;
		this.invoice_phone_number = invoice_phone_number;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(User preparedBy) {
		this.preparedBy = preparedBy;
	}

	public Long getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(Long invoice_number) {
		this.invoice_number = invoice_number;
	}

	public Account getDebit_account() {
		return debit_account;
	}

	public void setDebit_account(Account debit_account) {
		this.debit_account = debit_account;
	}

	public Account getCredit_account() {
		return credit_account;
	}

	public void setCredit_account(Account credit_account) {
		this.credit_account = credit_account;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


    
    /**
	 * @return the invoice_id
	 */
	public Long getInvoice_id() {
		return invoice_number;
	}
	/**
	 * @param invoice_id the invoice_id to set
	 */
	public void setInvoice_id(Long invoice_id) {
		this.invoice_number = invoice_id;
	}
	/**
	 * @return the invoice_amount
	 */
	public Long getInvoice_amount() {
		return invoice_amount;
	}
	/**
	 * @param invoice_amount the invoice_amount to set
	 */
	public void setInvoice_amount(Long invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	/**
	 * @return the invoice_date
	 */
	public Date getInvoice_date() {
		return invoice_date;
	}
	/**
	 * @param invoice_date the invoice_date to set
	 */
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	/**
	 * @return the invoice_narration
	 */
	public String getInvoice_narration() {
		return invoice_narration;
	}
	/**
	 * @param invoice_narration the invoice_narration to set
	 */
	public void setInvoice_narration(String invoice_narration) {
		this.invoice_narration = invoice_narration;
	}
	/**
	 * @return the invoice_LR_number
	 */
	public Long getInvoice_LR_number() {
		return invoice_LR_number;
	}
	/**
	 * @param invoice_LR_number the invoice_LR_number to set
	 */
	public void setInvoice_LR_number(Long invoice_LR_number) {
		this.invoice_LR_number = invoice_LR_number;
	}
	/**
	 * @return the invoice_transport
	 */
	public String getInvoice_transport() {
		return invoice_transport;
	}
	/**
	 * @param invoice_transport the invoice_transport to set
	 */
	public void setInvoice_transport(String invoice_transport) {
		this.invoice_transport = invoice_transport;
	}
	/**
	 * @return the invoice_phone_number
	 */
	public Long getInvoice_phone_number() {
		return invoice_phone_number;
	}
	/**
	 * @param invoice_phone_number the invoice_phone_number to set
	 */
	public void setInvoice_phone_number(Long invoice_phone_number) {
		this.invoice_phone_number = invoice_phone_number;
	}
}
