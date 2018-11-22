package com.jalaramrakhi.erpjr.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="tbl_user")
public class User {

	@Id
	@GeneratedValue
	private Long user_id;
	@Column(name = "user_name")
	private String username;
	private String user_password;
	@Transient
	private String user_confirm_password;
	@OneToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public User() {
	}

	public User(String username, String user_password, String user_confirm_password, Company company) {
		this.username = username;
		this.user_password = user_password;
		this.user_confirm_password = user_confirm_password;
		this.company = company;
	}

	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}
	/**
	 * @return the username
	 */
	public String getUser_name() {
		return username;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @param username the user_name to set
	 */
	public void setUser_name(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "{" +
				"\"user_id\" : \"" + user_id + "\"" +
				", \"username\" : \"" + username + '\"' +
				", \"user_password\" : \"" + user_password + '\"' +
				", \"user_confirm_password\" : \"" + user_confirm_password + '\"' +
				", \"company\" : " + company.toString() +
				'}';
	}
}
