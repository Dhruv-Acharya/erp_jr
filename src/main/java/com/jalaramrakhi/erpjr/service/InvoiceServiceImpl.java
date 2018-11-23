package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.InvoiceMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.InvoiceNotFoundException;
import com.jalaramrakhi.erpjr.Utils.InvoiceWrapper;
import com.jalaramrakhi.erpjr.entity.AccountTransactionDetails;
import com.jalaramrakhi.erpjr.entity.InventoryItem;
import com.jalaramrakhi.erpjr.entity.Invoice;
import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.entity.InvoiceItemIdentity;
import com.jalaramrakhi.erpjr.repository.AccountTransactionDetailsRepository;
import com.jalaramrakhi.erpjr.repository.InventoryItemRepository;
import com.jalaramrakhi.erpjr.repository.InvoiceItemRepository;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private InvoiceRepository invoiceRepository;
    private AccountTransactionDetailsRepository accountTransactionDetailsRepository;
    private InventoryItemRepository inventoryItemRepository;
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, AccountTransactionDetailsRepository accountTransactionDetailsRepository, InventoryItemRepository inventoryItemRepository) {
        Assert.notNull(invoiceRepository, "InvoiceRepository must not be null!");
        Assert.notNull(accountTransactionDetailsRepository, "AccountTransactionDetailsRepository must not be null!");
        this.invoiceRepository = invoiceRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.accountTransactionDetailsRepository = accountTransactionDetailsRepository;
        this.inventoryItemRepository = inventoryItemRepository;
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
    public ResponseEntity<Invoice> addNewInvoice(InvoiceWrapper invoiceWrapper, HttpServletRequest request) {
        if(null != invoiceWrapper.getCompany() && invoiceWrapper.getDebit_account() != null && invoiceWrapper.getCredit_account() != null) {
            Invoice invoice = new Invoice();

            invoice.setAddress(invoiceWrapper.getAddress());
            invoice.setCompany(invoiceWrapper.getCompany());
            invoice.setCredit_account(invoiceWrapper.getCredit_account());
            invoice.setDebit_account(invoiceWrapper.getDebit_account());
            invoice.setInvoice_amount(invoiceWrapper.getInvoice_amount());
            invoice.setInvoice_date(invoiceWrapper.getInvoice_date());
            invoice.setInvoice_narration(invoiceWrapper.getInvoice_narration());
            invoice.setPreparedBy(invoiceWrapper.getPreparedBy());
            invoice.setInvoice_LR_number(invoiceWrapper.getInvoice_LR_number());
            invoice.setInvoice_transport(invoiceWrapper.getInvoice_transport());
            invoice.setInvoice_phone_number(invoiceWrapper.getInvoice_phone_number());
            Invoice savedInvoice = invoiceRepository.save(invoice);

            AccountTransactionDetails creditTransaction = new AccountTransactionDetails();
            creditTransaction.setAccount(invoiceWrapper.getCredit_account());
            creditTransaction.setTransaction_amount(invoiceWrapper.getInvoice_amount());
            creditTransaction.setTransaction_date(new Date());
            accountTransactionDetailsRepository.saveAndFlush(creditTransaction);

            AccountTransactionDetails debitTransaction = new AccountTransactionDetails();
            debitTransaction.setAccount(invoiceWrapper.getDebit_account());
            debitTransaction.setTransaction_amount(invoiceWrapper.getInvoice_amount());
            debitTransaction.setTransaction_date(new Date());
            accountTransactionDetailsRepository.saveAndFlush(debitTransaction);

            InvoiceItemIdentity invoiceItemIdentity = new InvoiceItemIdentity();
            InventoryItem inventoryItem;
            InvoiceItem invoiceItem = new InvoiceItem();
            for (int i = 0; i < invoiceWrapper.getInvoiceItems().size(); i++) {
                inventoryItem = inventoryItemRepository.getOne(invoiceWrapper.getInvoiceItems().get(i).getInvoiceItemIdentity().getInventoryItem().getItem_id());
                invoiceItemIdentity.setInventoryItem(inventoryItem);
                invoiceItemIdentity.setInvoice(savedInvoice);
                invoiceItem.setInvoiceItemIdentity(invoiceItemIdentity);
                invoiceItem.setItem_price(invoiceWrapper.getInvoiceItems().get(i).getItem_price());
                invoiceItem.setItem_quantity(invoiceWrapper.getInvoiceItems().get(i).getItem_quantity());
                invoiceItem.setUnit(invoiceWrapper.getInvoiceItems().get(i).getUnit());

                inventoryItem.setItem_qty(inventoryItem.getItem_qty()- invoiceItem.getItem_quantity());

                invoiceItemRepository.saveAndFlush(invoiceItem);
            }

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
