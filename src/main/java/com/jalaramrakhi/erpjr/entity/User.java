package com.jalaramrakhi.erpjr.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tbl_user")
public class User {

	@Id
	@GeneratedValue
	private Integer user_id;
	private String user_name;
	private String user_password;
	@Transient
	private String user_confirm_password;
	@OneToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public User() {
	}

	public User(String user_name, String user_password, String user_confirm_password, Company company) {
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_confirm_password = user_confirm_password;
		this.company = company;
	}

	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the user_password
	 */
	public String getUser_password() {
		return user_password;
	}
	/**
	 * @param user_password the user_password to set
	 */
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_confirm_password() {
		return user_confirm_password;
	}

	public void setUser_confirm_password(String user_confirm_password) {
		this.user_confirm_password = user_confirm_password;
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


}
