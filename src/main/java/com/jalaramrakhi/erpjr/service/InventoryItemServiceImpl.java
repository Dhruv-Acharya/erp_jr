package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.InventoryItemMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.InventoryItemNotFoundException;
import com.jalaramrakhi.erpjr.entity.InventoryItem;
import com.jalaramrakhi.erpjr.repository.InventoryItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{

    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryItemServiceImpl(InventoryItemRepository inventoryItemRepository) {
        Assert.notNull(inventoryItemRepository, "InventoryItemRepository must not be null!");
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        List<InventoryItem> allInventoryItems = inventoryItemRepository.findAll();

        return new ResponseEntity<List<InventoryItem>>(allInventoryItems, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventoryItem> getSingleInventoryItem(Long id) {
        InventoryItem getInventoryItem = findInventoryItemIfExists(id);
        return new ResponseEntity<InventoryItem>(getInventoryItem, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventoryItem> addNewInventoryItem(InventoryItem inventoryItem, HttpServletRequest request) {
        if(null != inventoryItem.getItem_name() && inventoryItem.getItem_name().length() > 0) {
            inventoryItemRepository.saveAndFlush(inventoryItem);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", inventoryItemUrlHelper(inventoryItem, request));
            return new ResponseEntity<InventoryItem>(inventoryItem, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new InventoryItemMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<InventoryItem> updateInventoryItem(Long id, InventoryItem inventoryItem) {
        InventoryItem existingInventoryItem = findInventoryItemIfExists(id);

        if(null != inventoryItem.getItem_name() && inventoryItem.getItem_name().length() > 0) {
            BeanUtils.copyProperties(inventoryItem, existingInventoryItem);

            // Ensure ID remains unchanged
            existingInventoryItem.setItem_id(id);

            InventoryItem updatedInventoryItem = inventoryItemRepository.saveAndFlush(existingInventoryItem);
            return new ResponseEntity<InventoryItem>(updatedInventoryItem, HttpStatus.OK);
        } else {
            throw new InventoryItemMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<InventoryItem> deleteInventoryItem(Long id) {
        InventoryItem existingInventoryItem = findInventoryItemIfExists(id);
        inventoryItemRepository.delete(existingInventoryItem);
        return new ResponseEntity<InventoryItem>(HttpStatus.NO_CONTENT);
    }

    private String inventoryItemUrlHelper(InventoryItem inventoryItem, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(inventoryItem.getItem_id());

        return resourcePath.toString();
    }

    private InventoryItem findInventoryItemIfExists(Long id) {
        Optional<InventoryItem> inventoryItem = inventoryItemRepository.findById(id);

        if(inventoryItem.isPresent()){
            return inventoryItem.get();
        }
        else {
            throw new InventoryItemNotFoundException();
        }
    }
}
