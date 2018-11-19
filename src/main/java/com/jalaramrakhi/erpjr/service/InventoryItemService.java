package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.InventoryItem;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InventoryItemService {
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems();
    public ResponseEntity<InventoryItem> getSingleInventoryItem(Integer id);
    public ResponseEntity<InventoryItem> addNewInventoryItem(InventoryItem inventoryItem, HttpServletRequest request);
    public ResponseEntity<InventoryItem> updateInventoryItem(Integer id, InventoryItem inventoryItem);
    public ResponseEntity<InventoryItem> deleteInventoryItem(Integer id);
}
