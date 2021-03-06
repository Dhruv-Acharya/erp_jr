package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Unit {

	@Id
	@GeneratedValue
	private Long unit_id;
	private String unit_type;

	public Unit() {
	}

	public Unit(Long unit_id, String unit_type) {
		this.unit_id = unit_id;
		this.unit_type = unit_type;
	}

	/**
	 * @return the unit_id
	 */
	public Long getUnit_id() {
		return unit_id;
	}
	/**
	 * @param unit_id the unit_id to set
	 */
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	/**
	 * @return the unit_type
	 */
	public String getUnit_type() {
		return unit_type;
	}
	/**
	 * @param unit_type the unit_type to set
	 */
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}
	
	
}
