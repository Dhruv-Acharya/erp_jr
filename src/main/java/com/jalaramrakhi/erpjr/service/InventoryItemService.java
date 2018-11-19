package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.InventoryItem;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InventoryItemService {
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems();
    public ResponseEntity<InventoryItem> getSingleInventoryItem(Long id);
    public ResponseEntity<InventoryItem> addNewInventoryItem(InventoryItem inventoryItem, HttpServletRequest request);
    public ResponseEntity<InventoryItem> updateInventoryItem(Long id, InventoryItem inventoryItem);
    public ResponseEntity<InventoryItem> deleteInventoryItem(Long id);
}
