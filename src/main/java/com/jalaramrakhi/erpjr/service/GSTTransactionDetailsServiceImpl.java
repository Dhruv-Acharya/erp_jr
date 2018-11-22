package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.GSTTransactionDetailsMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.GSTTransactionDetailsNotFoundException;
import com.jalaramrakhi.erpjr.entity.GSTTransactionDetails;
import com.jalaramrakhi.erpjr.entity.GSTTransactionDetailsIdentity;
import com.jalaramrakhi.erpjr.repository.GSTTransactionDetailsRepository;
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
public class GSTTransactionDetailsServiceImpl implements GSTTransactionDetailsService{

    private GSTTransactionDetailsRepository gstTransactionDetailsRepository;

    @Autowired
    public GSTTransactionDetailsServiceImpl(GSTTransactionDetailsRepository gstTransactionDetailsRepository) {
        Assert.notNull(gstTransactionDetailsRepository, "GSTTransactionDetailsRepository must not be null!");
        this.gstTransactionDetailsRepository = gstTransactionDetailsRepository;
    }

    @Override
    public ResponseEntity<List<GSTTransactionDetails>> getAllGSTTransactionDetails() {
        List<GSTTransactionDetails> allGSTTransactionDetailss = gstTransactionDetailsRepository.findAll();

        return new ResponseEntity<List<GSTTransactionDetails>>(allGSTTransactionDetailss, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GSTTransactionDetails> getSingleGSTTransactionDetails(GSTTransactionDetailsIdentity id) {
        GSTTransactionDetails getGSTTransactionDetails = findGSTTransactionDetailsIfExists(id);
        return new ResponseEntity<GSTTransactionDetails>(getGSTTransactionDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GSTTransactionDetails> addNewGSTTransactionDetails(GSTTransactionDetails gstTransactionDetails, HttpServletRequest request) {
        if(null != gstTransactionDetails.getGstTransactionDetailsIdentity()) {
            gstTransactionDetailsRepository.saveAndFlush(gstTransactionDetails);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", gstTransactionDetailsUrlHelper(gstTransactionDetails, request));
            return new ResponseEntity<GSTTransactionDetails>(gstTransactionDetails, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new GSTTransactionDetailsMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<GSTTransactionDetails> updateGSTTransactionDetails(GSTTransactionDetailsIdentity id, GSTTransactionDetails gstTransactionDetails) {
        GSTTransactionDetails existingGSTTransactionDetails = findGSTTransactionDetailsIfExists(id);

        if(null != gstTransactionDetails.getGstTransactionDetailsIdentity()) {
            BeanUtils.copyProperties(gstTransactionDetails, existingGSTTransactionDetails);

            // Ensure ID remains unchanged
            existingGSTTransactionDetails.setGstTransactionDetailsIdentity(id);

            GSTTransactionDetails updatedGSTTransactionDetails = gstTransactionDetailsRepository.saveAndFlush(existingGSTTransactionDetails);
            return new ResponseEntity<GSTTransactionDetails>(updatedGSTTransactionDetails, HttpStatus.OK);
        } else {
            throw new GSTTransactionDetailsMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<GSTTransactionDetails> deleteGSTTransactionDetails(GSTTransactionDetailsIdentity id) {
        GSTTransactionDetails existingGSTTransactionDetails = findGSTTransactionDetailsIfExists(id);
        gstTransactionDetailsRepository.delete(existingGSTTransactionDetails);
        return new ResponseEntity<GSTTransactionDetails>(HttpStatus.NO_CONTENT);
    }

    private String gstTransactionDetailsUrlHelper(GSTTransactionDetails gstTransactionDetails, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(gstTransactionDetails.getGstTransactionDetailsIdentity());

        return resourcePath.toString();
    }

    private GSTTransactionDetails findGSTTransactionDetailsIfExists(GSTTransactionDetailsIdentity id) {
        Optional<GSTTransactionDetails> gstTransactionDetails = gstTransactionDetailsRepository.findById(id);

        if(gstTransactionDetails.isPresent()){
            return gstTransactionDetails.get();
        }
        else {
            throw new GSTTransactionDetailsNotFoundException();
        }
    }
}
