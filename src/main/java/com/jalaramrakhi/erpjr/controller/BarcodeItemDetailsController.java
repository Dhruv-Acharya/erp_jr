package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.BarcodeItemDetails;
import com.jalaramrakhi.erpjr.service.BarcodeItemDetailService;
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
@RequestMapping(value = "/barcodeItemDetails")
@RestController
public class BarcodeItemDetailsController {
    
    @Autowired
    private BarcodeItemDetailService barcodeItemDetailService;

    // List All Companies
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BarcodeItemDetails>> getAllBarcodeItemDetailss() throws Throwable {
        return barcodeItemDetailService.getAllBarcodeItemDetails();
    }

    // Get One BarcodeItemDetails
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BarcodeItemDetails> getSingleBarcodeItemDetails(@PathVariable Long id) {
        return barcodeItemDetailService.getSingleBarcodeItemDetails(id);
    }

    // Add New BarcodeItemDetails
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BarcodeItemDetails> createNewBarcodeItemDetails(@RequestBody BarcodeItemDetails barcodeItemDetails, HttpServletRequest req) {
        return barcodeItemDetailService.addNewBarcodeItemDetails(barcodeItemDetails, req);
    }

    // Update BarcodeItemDetails with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BarcodeItemDetails> updateBarcodeItemDetails(@PathVariable Long id, @RequestBody BarcodeItemDetails barcodeItemDetails) {
        return barcodeItemDetailService.updateBarcodeItemDetails(id, barcodeItemDetails);
    }

    // Delete BarcodeItemDetails
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BarcodeItemDetails> deleteBarcodeItemDetails(@PathVariable Long id) {
        return barcodeItemDetailService.deleteBarcodeItemDetails(id);
    }
}
