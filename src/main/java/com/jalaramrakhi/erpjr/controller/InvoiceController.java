package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.Utils.InvoiceWrapper;
import com.jalaramrakhi.erpjr.entity.Invoice;
import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/invoice")
@RestController
public class InvoiceController {
    
    @Autowired
    private InvoiceService invoiceService;

    // List All Invoices
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> getAllInvoices() throws Throwable {
        return invoiceService.getAllInvoices();
    }

    // Get One Invoice
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> getSingleInvoice(@PathVariable Long id) {
        return invoiceService.getSingleInvoice(id);
    }

    // Add New Invoice
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Invoice> createNewInvoice(@RequestBody InvoiceWrapper invoiceWrapper, HttpServletRequest req) {
        return invoiceService.addNewInvoice(invoiceWrapper, req);
    }

    // Update Invoice with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        return invoiceService.updateInvoice(id, invoice);
    }

    // Delete Invoice
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id) {
        return invoiceService.deleteInvoice(id);
    }
}
