package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
	
	@Id
	private Integer company_id;
	private String company_name;
	/**
	 * @return the company_id
	 */
	public Integer getCompany_id() {
		return company_id;
	}
	/**
	 * @param company_id the company_id to set
	 */
	public void setCompany_id(Integer company_id) {
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
	
}
