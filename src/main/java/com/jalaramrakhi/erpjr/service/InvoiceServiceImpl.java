package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.InvoiceMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.InvoiceNotFoundException;
import com.jalaramrakhi.erpjr.entity.Invoice;
import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.repository.AccountTransactionDetailsRepository;
import com.jalaramrakhi.erpjr.repository.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;
    private AccountTransactionDetailsRepository accountTransactionDetailsRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, AccountTransactionDetailsRepository accountTransactionDetailsRepository) {
        Assert.notNull(invoiceRepository, "InvoiceRepository must not be null!");
        Assert.notNull(accountTransactionDetailsRepository, "AccountTransactionDetailsRepository must not be null!");
        this.invoiceRepository = invoiceRepository;
        this.accountTransactionDetailsRepository = accountTransactionDetailsRepository;
    }

    @Override
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> allInvoices = invoiceRepository.findAll();

        return new ResponseEntity<List<Invoice>>(allInvoices, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invoice> getSingleInvoice(Long id) {
        Invoice getInvoice = findInvoiceIfExists(id);
        return new ResponseEntity<Invoice>(getInvoice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invoice> addNewInvoice(Invoice invoice, HttpServletRequest request, @RequestBody List<InvoiceItem> body) {
        if(null != invoice.getCompany() && invoice.getDebit_account() != null && invoice.getCredit_account() != null) {

//            boolean isAdded = addInvoiceItem(body);

            invoiceRepository.saveAndFlush(invoice);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", invoiceUrlHelper(invoice, request));
            return new ResponseEntity<Invoice>(invoice, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new InvoiceMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoice) {
        Invoice existingInvoice = findInvoiceIfExists(id);

        if(null != invoice.getCompany() && invoice.getDebit_account() != null && invoice.getCredit_account() != null) {
            BeanUtils.copyProperties(invoice, existingInvoice);

            // Ensure ID remains unchanged
            existingInvoice.setInvoice_id(id);

            Invoice updatedInvoice = invoiceRepository.saveAndFlush(existingInvoice);
            return new ResponseEntity<Invoice>(updatedInvoice, HttpStatus.OK);
        } else {
            throw new InvoiceMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Invoice> deleteInvoice(Long id) {
        Invoice existingInvoice = findInvoiceIfExists(id);
        invoiceRepository.delete(existingInvoice);
        return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
    }

    private String invoiceUrlHelper(Invoice invoice, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(invoice.getInvoice_id());

        return resourcePath.toString();
    }

    private Invoice findInvoiceIfExists(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);

        if(invoice.isPresent()){
            return invoice.get();
        }
        else {
            throw new InvoiceNotFoundException();
        }
    }

//    private boolean addInvoiceItem(List<InvoiceItem> body) {
//        int i, len = body.size();
//        for(i = 0; i < len; i++) {
//            if(invoiceRepository.saveAll(() -> body<InvoiceItem>));
//        }
//    }
}
