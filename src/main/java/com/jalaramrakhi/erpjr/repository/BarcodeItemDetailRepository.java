package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.BarcodeItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeItemDetailRepository extends JpaRepository<BarcodeItemDetails, Integer> {
}
