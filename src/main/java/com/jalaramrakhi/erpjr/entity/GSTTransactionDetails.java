package com.jalaramrakhi.erpjr.entity;
//
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.validation.constraints.NotNull;
//import java.util.Date;
//
//@Entity
//public class GSTTransactionDetails {
//
//    @EmbeddedId
//    private GSTTransactionDetailsIdentity gstTransactionDetailsIdentity;
//
//    @NotNull
//    private double transaction_amount;
//
//    @NotNull
//    private Date getTransaction_date;
//
//    public GSTTransactionDetails() {
//    }
//
//    public GSTTransactionDetails(GSTTransactionDetailsIdentity gstTransactionDetailsIdentity, @NotNull double transaction_amount, @NotNull Date getTransaction_date) {
//        this.gstTransactionDetailsIdentity = gstTransactionDetailsIdentity;
//        this.transaction_amount = transaction_amount;
//        this.getTransaction_date = getTransaction_date;
//    }
//
//    public GSTTransactionDetailsIdentity getGstTransactionDetailsIdentity() {
//        return gstTransactionDetailsIdentity;
//    }
//
//    public void setGstTransactionDetailsIdentity(GSTTransactionDetailsIdentity gstTransactionDetailsIdentity) {
//        this.gstTransactionDetailsIdentity = gstTransactionDetailsIdentity;
//    }
//
//    public double getTransaction_amount() {
//        return transaction_amount;
//    }
//
//    public void setTransaction_amount(double transaction_amount) {
//        this.transaction_amount = transaction_amount;
//    }
//
//    public Date getGetTransaction_date() {
//        return getTransaction_date;
//    }
//
//    public void setGetTransaction_date(Date getTransaction_date) {
//        this.getTransaction_date = getTransaction_date;
//    }
//}
