package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Invoice;
import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InvoiceService {
    public ResponseEntity<List<Invoice>> getAllInvoices();
    public ResponseEntity<Invoice> getSingleInvoice(Long id);
    public ResponseEntity<Invoice> addNewInvoice(Invoice invoice, HttpServletRequest request, @RequestBody List<InvoiceItem> body);
    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoice);
    public ResponseEntity<Invoice> deleteInvoice(Long id);
}
