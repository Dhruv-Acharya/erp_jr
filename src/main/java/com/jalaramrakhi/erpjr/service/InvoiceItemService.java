package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.entity.InvoiceItemIdentity;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InvoiceItemService {
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems();
    public ResponseEntity<InvoiceItem> getSingleInvoiceItem(InvoiceItemIdentity id);
    public ResponseEntity<InvoiceItem> addNewInvoiceItem(InvoiceItem invoiceItem, HttpServletRequest request);
    public ResponseEntity<InvoiceItem> updateInvoiceItem(InvoiceItemIdentity id, InvoiceItem invoiceItem);
    public ResponseEntity<InvoiceItem> deleteInvoiceItem(InvoiceItemIdentity id);
}
