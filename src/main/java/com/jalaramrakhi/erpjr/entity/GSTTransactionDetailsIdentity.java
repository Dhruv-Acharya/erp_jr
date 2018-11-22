package com.jalaramrakhi.erpjr.entity;
//
//import javax.persistence.Embeddable;
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class GSTTransactionDetailsIdentity implements Serializable {
//    @NotNull
//    private Category category;
//
//    @NotNull
//    private Invoice invoice;
//
//    public GSTTransactionDetailsIdentity(@NotNull Category category, @NotNull Invoice invoice) {
//        this.category = category;
//        this.invoice = invoice;
//    }
//
//    public GSTTransactionDetailsIdentity() {
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Invoice getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(Invoice invoice) {
//        this.invoice = invoice;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof GSTTransactionDetailsIdentity)) return false;
//        GSTTransactionDetailsIdentity that = (GSTTransactionDetailsIdentity) o;
//        return Objects.equals(getCategory(), that.getCategory()) &&
//                Objects.equals(getInvoice(), that.getInvoice());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getCategory(), getInvoice());
//    }
//}
