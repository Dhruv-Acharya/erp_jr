package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.BarcodeItemDetailsMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.BarcodeItemDetailNotFoundException;
import com.jalaramrakhi.erpjr.entity.BarcodeItemDetails;
import com.jalaramrakhi.erpjr.repository.BarcodeItemDetailRepository;
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
public class BarcodeItemDetailServiceImpl implements BarcodeItemDetailService{

    private BarcodeItemDetailRepository barcodeItemDetailRepository;

    @Autowired
    public BarcodeItemDetailServiceImpl(BarcodeItemDetailRepository barcodeItemDetailRepository) {
        Assert.notNull(barcodeItemDetailRepository, "BarcodeItemDetailRepository must not be null!");
        this.barcodeItemDetailRepository = barcodeItemDetailRepository;
    }

    @Override
    public ResponseEntity<List<BarcodeItemDetails>> getAllBarcodeItemDetails() {
        List<BarcodeItemDetails> allBarcodeItemDetails = barcodeItemDetailRepository.findAll();

        return new ResponseEntity<List<BarcodeItemDetails>>(allBarcodeItemDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BarcodeItemDetails> getSingleBarcodeItemDetails(Long id) {
        BarcodeItemDetails getBarcodeItemDetails = findBarcodeItemDetailsIfExists(id);
        return new ResponseEntity<BarcodeItemDetails>(getBarcodeItemDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BarcodeItemDetails> addNewBarcodeItemDetails(BarcodeItemDetails barcodeItemDetails, HttpServletRequest request) {
        if(barcodeItemDetails.getInventoryItem() != null) {
            barcodeItemDetailRepository.saveAndFlush(barcodeItemDetails);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", barcodeItemDetailsUrlHelper(barcodeItemDetails, request));
            return new ResponseEntity<BarcodeItemDetails>(barcodeItemDetails, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new BarcodeItemDetailsMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<BarcodeItemDetails> updateBarcodeItemDetails(Long id, BarcodeItemDetails barcodeItemDetails) {
        BarcodeItemDetails existingBarcodeItemDetails = findBarcodeItemDetailsIfExists(id);

        if(null != barcodeItemDetails.getInventoryItem()) {
            BeanUtils.copyProperties(barcodeItemDetails, existingBarcodeItemDetails);

            // Ensure ID remains unchanged
            existingBarcodeItemDetails.setBarcode_id(id);

            BarcodeItemDetails updatedBarcodeItemDetails = barcodeItemDetailRepository.saveAndFlush(existingBarcodeItemDetails);
            return new ResponseEntity<BarcodeItemDetails>(updatedBarcodeItemDetails, HttpStatus.OK);
        } else {
            throw new BarcodeItemDetailsMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<BarcodeItemDetails> deleteBarcodeItemDetails(Long id) {
        BarcodeItemDetails existingBarcodeItemDetails = findBarcodeItemDetailsIfExists(id);
        barcodeItemDetailRepository.delete(existingBarcodeItemDetails);
        return new ResponseEntity<BarcodeItemDetails>(HttpStatus.NO_CONTENT);
    }

    private String barcodeItemDetailsUrlHelper(BarcodeItemDetails barcodeItemDetails, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(barcodeItemDetails.getBarcode_id());

        return resourcePath.toString();
    }

    private BarcodeItemDetails findBarcodeItemDetailsIfExists(Long id) {
        Optional<BarcodeItemDetails> barcodeItemDetails = barcodeItemDetailRepository.findById(id);

        if(barcodeItemDetails.isPresent()){
            return barcodeItemDetails.get();
        }
        else {
            throw new BarcodeItemDetailNotFoundException();
        }
    }
}
