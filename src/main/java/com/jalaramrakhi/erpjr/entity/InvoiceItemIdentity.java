package com.jalaramrakhi.erpjr.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class InvoiceItemIdentity implements Serializable {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_number")
    private Invoice invoice;

    @NotNull
    @OneToOne
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    public InvoiceItemIdentity(@NotNull Invoice invoice, @NotNull InventoryItem inventoryItem) {
        this.invoice = invoice;
        this.inventoryItem = inventoryItem;
    }

    public InvoiceItemIdentity() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceItemIdentity)) return false;
        InvoiceItemIdentity that = (InvoiceItemIdentity) o;
        return Objects.equals(getInvoice(), that.getInvoice()) &&
                Objects.equals(getInventoryItem(), that.getInventoryItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoice(), getInventoryItem());
    }
}
