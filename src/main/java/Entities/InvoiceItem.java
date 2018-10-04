package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class InvoiceItem {

	@Id
	@ManyToOne
	private Invoice invoice;
	@Id
	@OneToOne
	private InventoryItem inventoryItem;
	private int item_quantity;
	private double item_price;
	@OneToOne
	private Unit unit;
	private double tax;
	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}
	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	/**
	 * @return the inventoryItem
	 */
	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}
	/**
	 * @param inventoryItem the inventoryItem to set
	 */
	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
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
