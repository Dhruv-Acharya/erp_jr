package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Utils.InvoiceWrapper;
import com.jalaramrakhi.erpjr.entity.Invoice;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InvoiceService {
    public ResponseEntity<List<Invoice>> getAllInvoices();
    public ResponseEntity<Invoice> getSingleInvoice(Long id);
    public ResponseEntity<Invoice> addNewInvoice(InvoiceWrapper invoiceWrapper, HttpServletRequest request);
    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoice);
    public ResponseEntity<Invoice> deleteInvoice(Long id);
}
