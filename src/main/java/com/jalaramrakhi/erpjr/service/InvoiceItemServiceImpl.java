package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.InvoiceItemMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.InvoiceItemNotFoundException;
import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.entity.InvoiceItemIdentity;
import com.jalaramrakhi.erpjr.repository.InvoiceItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService{

    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository) {
        Assert.notNull(invoiceItemRepository, "InvoiceItemRepository must not be null!");
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems() {
        List<InvoiceItem> allInvoiceItems = invoiceItemRepository.findAll();

        return new ResponseEntity<List<InvoiceItem>>(allInvoiceItems, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceItem> getSingleInvoiceItem(InvoiceItemIdentity id) {
        InvoiceItem getInvoiceItem = findInvoiceItemIfExists(id);
        return new ResponseEntity<InvoiceItem>(getInvoiceItem, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceItem> addNewInvoiceItem(InvoiceItem invoiceItem, HttpServletRequest request) {
        if(invoiceItem.getItem_quantity() > 0 && invoiceItem.getItem_price() > 0) {
            invoiceItemRepository.saveAndFlush(invoiceItem);
//            HttpHeaders responseHeaders = new HttpHeaders();
//            responseHeaders.set("Location", invoiceItemUrlHelper(invoiceItem, request));
            return new ResponseEntity<InvoiceItem>(invoiceItem, HttpStatus.CREATED);
        }
        else {
            throw new InvoiceItemMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<InvoiceItem> updateInvoiceItem(InvoiceItemIdentity id, InvoiceItem invoiceItem) {
        InvoiceItem existingInvoiceItem = findInvoiceItemIfExists(id);

        if(invoiceItem.getItem_price() > 0 && invoiceItem.getItem_quantity() > 0) {
            BeanUtils.copyProperties(invoiceItem, existingInvoiceItem);

            // Ensure ID remains unchanged
            existingInvoiceItem.setInvoiceItemIdentity(id);

            InvoiceItem updatedInvoiceItem = invoiceItemRepository.saveAndFlush(existingInvoiceItem);
            return new ResponseEntity<InvoiceItem>(updatedInvoiceItem, HttpStatus.OK);
        } else {
            throw new InvoiceItemMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<InvoiceItem> deleteInvoiceItem(InvoiceItemIdentity id) {
        InvoiceItem existingInvoiceItem = findInvoiceItemIfExists(id);
        invoiceItemRepository.delete(existingInvoiceItem);
        return new ResponseEntity<InvoiceItem>(HttpStatus.NO_CONTENT);
    }

//    private String invoiceItemUrlHelper(InvoiceItem invoiceItem, HttpServletRequest request) {
//        StringBuilder resourcePath = new StringBuilder();
//
//        resourcePath.append(request.getRequestURL());
//        resourcePath.append("/");
//        resourcePath.append(invoiceItem.getInvoiceItemIdentity());
//
//        return resourcePath.toString();
//    }

    private InvoiceItem findInvoiceItemIfExists(InvoiceItemIdentity id) {
        InvoiceItem invoiceItem = invoiceItemRepository.findByInvoiceItemIdentity(id);

        if(invoiceItem != null){
            return invoiceItem;
        }
        else {
            throw new InvoiceItemNotFoundException();
        }
    }
}
