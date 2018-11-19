package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
}
