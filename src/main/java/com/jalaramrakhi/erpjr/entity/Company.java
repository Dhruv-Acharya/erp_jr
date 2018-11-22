package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Company implements Serializable {

	@Id
	@GeneratedValue
	private Long company_id;
	@Column(unique = true)
	private String company_name;

	public Company(String company_name) {
		this.company_name = company_name;
	}
	public Company(){}

	/**
	 * @return the company_id
	 */
	public Long getCompany_id() {
		return company_id;
	}
	/**
	 * @param company_id the company_id to set
	 */
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return company_name;
	}
	/**
	 * @param company_name the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Override
	public String toString() {
		return "{" +
				"\"company_id\" : \"" + company_id + "\"" +
				", \"company_name\" :\"" + company_name + '\"' +
				'}';
	}
}
