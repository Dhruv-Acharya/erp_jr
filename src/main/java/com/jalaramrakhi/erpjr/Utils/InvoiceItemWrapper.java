package com.jalaramrakhi.erpjr.Utils;

import com.jalaramrakhi.erpjr.entity.Unit;

public class InvoiceItemWrapper {
    private Long invoice_id;
    private Long item_id;
    private Long item_quantity;
    private Long item_price;
    private Unit unit;

    public InvoiceItemWrapper() {
    }

    public InvoiceItemWrapper(Long invoice_id, Long item_id, Long item_quantity, Long item_price, Unit unit) {
        this.invoice_id = invoice_id;
        this.item_id = item_id;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
        this.unit = unit;
    }

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Long getInventoryItem_id() {
        return item_id;
    }

    public void setInventoryItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(Long item_quantity) {
        this.item_quantity = item_quantity;
    }

    public Long getItem_price() {
        return item_price;
    }

    public void setItem_price(Long item_price) {
        this.item_price = item_price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
