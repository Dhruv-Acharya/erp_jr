package com.jalaramrakhi.erpjr.Utils;

import com.jalaramrakhi.erpjr.entity.Account;
import com.jalaramrakhi.erpjr.entity.Address;
import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.entity.User;

import java.util.Date;
import java.util.List;

public class InvoiceWrapper {
    private Account debit_account;
    private Long invoice_amount;
    private Date invoice_date;
    private String invoice_narration;
    private Account credit_account;
    private User preparedBy;
    private Company company;
    private Long invoice_LR_number;
    private String invoice_transport;
    private Long invoice_phone_number;
    private Address address;
    private List<InvoiceItemWrapper> invoiceItemsWrapper;

    public InvoiceWrapper() {
    }

    public InvoiceWrapper(Account debit_account, Long invoice_amount, Date invoice_date, String invoice_narration, Account credit_account, User preparedBy, Company company, Long invoice_LR_number, String invoice_transport, Long invoice_phone_number, Address address, List<InvoiceItemWrapper> invoiceItemsWrapper) {
        this.debit_account = debit_account;
        this.invoice_amount = invoice_amount;
        this.invoice_date = invoice_date;
        this.invoice_narration = invoice_narration;
        this.credit_account = credit_account;
        this.preparedBy = preparedBy;
        this.company = company;
        this.invoice_LR_number = invoice_LR_number;
        this.invoice_transport = invoice_transport;
        this.invoice_phone_number = invoice_phone_number;
        this.address = address;
        this.invoiceItemsWrapper = invoiceItemsWrapper;
    }

    public Account getDebit_account() {
        return debit_account;
    }

    public void setDebit_account(Account debit_account) {
        this.debit_account = debit_account;
    }

    public Long getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(Long invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getInvoice_narration() {
        return invoice_narration;
    }

    public void setInvoice_narration(String invoice_narration) {
        this.invoice_narration = invoice_narration;
    }

    public Account getCredit_account() {
        return credit_account;
    }

    public void setCredit_account(Account credit_account) {
        this.credit_account = credit_account;
    }

    public User getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(User preparedBy) {
        this.preparedBy = preparedBy;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getInvoice_LR_number() {
        return invoice_LR_number;
    }

    public void setInvoice_LR_number(Long invoice_LR_number) {
        this.invoice_LR_number = invoice_LR_number;
    }

    public String getInvoice_transport() {
        return invoice_transport;
    }

    public void setInvoice_transport(String invoice_transport) {
        this.invoice_transport = invoice_transport;
    }

    public Long getInvoice_phone_number() {
        return invoice_phone_number;
    }

    public void setInvoice_phone_number(Long invoice_phone_number) {
        this.invoice_phone_number = invoice_phone_number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<InvoiceItemWrapper> getInvoiceItemsWrapper() {
        return invoiceItemsWrapper;
    }

    public void setInvoiceItemsWrapper(List<InvoiceItemWrapper> invoiceItemsWrapper) {
        this.invoiceItemsWrapper = invoiceItemsWrapper;
    }

    @Override
    public String toString() {
        return "InvoiceWrapper{" +
                "debit_account=" + debit_account +
                ", invoice_amount=" + invoice_amount +
                ", invoice_date=" + invoice_date +
                ", invoice_narration='" + invoice_narration + '\'' +
                ", credit_account=" + credit_account +
                ", preparedBy=" + preparedBy +
                ", company=" + company +
                ", invoice_LR_number=" + invoice_LR_number +
                ", invoice_transport='" + invoice_transport + '\'' +
                ", invoice_phone_number=" + invoice_phone_number +
                ", address=" + address +
                ", invoiceItems=" + invoiceItemsWrapper +
                '}';
    }
}
