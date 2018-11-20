package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.BarcodeItemDetails;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BarcodeItemDetailService {
    public ResponseEntity<List<BarcodeItemDetails>> getAllBarcodeItemDetails();
    public ResponseEntity<BarcodeItemDetails> getSingleBarcodeItemDetails(Long id);
    public ResponseEntity<BarcodeItemDetails> addNewBarcodeItemDetails(BarcodeItemDetails barcodeItemDetails, HttpServletRequest request);
    public ResponseEntity<BarcodeItemDetails> updateBarcodeItemDetails(Long id, BarcodeItemDetails barcodeItemDetails);
    public ResponseEntity<BarcodeItemDetails> deleteBarcodeItemDetails(Long id);
}
