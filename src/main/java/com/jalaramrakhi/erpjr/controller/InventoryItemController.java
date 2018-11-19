package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.InventoryItem;
import com.jalaramrakhi.erpjr.service.InventoryItemService;
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
@RequestMapping(value = "/inventoryItem")
@RestController
public class InventoryItemController {
    
    @Autowired
    private InventoryItemService inventoryItemService;

    // List All InventoryItems
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() throws Throwable {
        return inventoryItemService.getAllInventoryItems();
    }

    // Get One InventoryItem
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<InventoryItem> getSingleInventoryItem(@PathVariable Integer id) {
        return inventoryItemService.getSingleInventoryItem(id);
    }

    // Add New InventoryItem
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InventoryItem> createNewInventoryItem(@RequestBody InventoryItem inventoryItem, HttpServletRequest req) {
        return inventoryItemService.addNewInventoryItem(inventoryItem, req);
    }

    // Update InventoryItem with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Integer id, @RequestBody InventoryItem inventoryItem) {
        return inventoryItemService.updateInventoryItem(id, inventoryItem);
    }

    // Delete InventoryItem
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<InventoryItem> deleteInventoryItem(@PathVariable Integer id) {
        return inventoryItemService.deleteInventoryItem(id);
    }
}
