package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BarcodeItemDetails {
    @Id
    @GeneratedValue
    private Long barcode_id;
    @OneToOne
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;
    private double item_price;
    private double item_quantity;

    public BarcodeItemDetails() {
    }

    public BarcodeItemDetails(Long barcode_id, InventoryItem inventoryItem, double item_price, double item_quantity) {
        this.barcode_id = barcode_id;
        this.inventoryItem = inventoryItem;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
    }

    public Long getBarcode_id() {
        return barcode_id;
    }

    public void setBarcode_id(Long barcode_id) {
        this.barcode_id = barcode_id;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public double getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(double item_quantity) {
        this.item_quantity = item_quantity;
    }
}
