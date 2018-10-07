package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class InventoryItem {

	@Id
	private Integer item_id;
	@NotNull
    private String item_name;
    private String item_description;
    private double item_cost_price;
    private double item_selling_price;
    private double item_qty;
    @OneToOne
    private Unit unit;
    @OneToOne
    private Category category;
    
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	/**
	 * @return the item_id
	 */
	public Integer getItem_id() {
		return item_id;
	}
	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	/**
	 * @return the item_name
	 */
	public String getItem_name() {
		return item_name;
	}
	/**
	 * @param item_name the item_name to set
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	/**
	 * @return the item_description
	 */
	public String getItem_description() {
		return item_description;
	}
	/**
	 * @param item_description the item_description to set
	 */
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	/**
	 * @return the item_cost_price
	 */
	public double getItem_cost_price() {
		return item_cost_price;
	}
	/**
	 * @param item_cost_price the item_cost_price to set
	 */
	public void setItem_cost_price(double item_cost_price) {
		this.item_cost_price = item_cost_price;
	}
	/**
	 * @return the item_selling_price
	 */
	public double getItem_selling_price() {
		return item_selling_price;
	}
	/**
	 * @param item_selling_price the item_selling_price to set
	 */
	public void setItem_selling_price(double item_selling_price) {
		this.item_selling_price = item_selling_price;
	}
	/**
	 * @return the item_qty
	 */
	public double getItem_qty() {
		return item_qty;
	}
	/**
	 * @param item_qty the item_qty to set
	 */
	public void setItem_qty(double item_qty) {
		this.item_qty = item_qty;
	}

    
    }
