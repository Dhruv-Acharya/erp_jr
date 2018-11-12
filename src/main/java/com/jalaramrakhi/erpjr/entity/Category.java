package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	private Integer HSN_code;
	private String category_name;
	private double tax_rate;

	public Category() {
	}

	public Category(Integer HSN_code, String category_name, double tax_rate) {
		this.HSN_code = HSN_code;
		this.category_name = category_name;
		this.tax_rate = tax_rate;
	}

	/**
	 * @return the hSN_code
	 */
	public Integer getHSN_code() {
		return HSN_code;
	}
	/**
	 * @param hSN_code the hSN_code to set
	 */
	public void setHSN_code(Integer hSN_code) {
		HSN_code = hSN_code;
	}
	/**
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * @param category_name the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * @return the tax_rate
	 */
	public double getTax_rate() {
		return tax_rate;
	}
	/**
	 * @param tax_rate the tax_rate to set
	 */
	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}
	
}
