package com.jalaramrakhi.erpjr.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class InvoiceItem {

	@EmbeddedId
	private InvoiceItemIdentity invoiceItemIdentity;

	private int item_quantity;
	private double item_price;
	@OneToOne
    @JoinColumn(name = "unit_id")
	private Unit unit;
	private double tax;

	public InvoiceItem() {
	}

	public InvoiceItem(InvoiceItemIdentity invoiceItemIdentity, int item_quantity, double item_price, Unit unit, double tax) {
		this.invoiceItemIdentity = invoiceItemIdentity;
		this.item_quantity = item_quantity;
		this.item_price = item_price;
		this.unit = unit;
		this.tax = tax;
	}

	public InvoiceItemIdentity getInvoiceItemIdentity() {
		return invoiceItemIdentity;
	}

	public void setInvoiceItemIdentity(InvoiceItemIdentity invoiceItemIdentity) {
		this.invoiceItemIdentity = invoiceItemIdentity;
	}

	/**
	 * @return the item_quantity
	 */
	public int getItem_quantity() {
		return item_quantity;
	}
	/**
	 * @param item_quantity the item_quantity to set
	 */
	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}
	/**
	 * @return the item_price
	 */
	public double getItem_price() {
		return item_price;
	}
	/**
	 * @param item_price the item_price to set
	 */
	public void setItem_price(double item_price) {
		this.item_price = item_price;
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
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
}
