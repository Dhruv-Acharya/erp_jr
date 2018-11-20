package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.AccountTransactionDetails;
import com.jalaramrakhi.erpjr.service.AccountTransactionDetailsService;
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
@RequestMapping(value = "/accountTransactionDetails")
@RestController
public class AccountTransactionDetailsController {
    
    @Autowired
    private AccountTransactionDetailsService accountTransactionDetailsService;

    // List All Companies
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccountTransactionDetails>> getAllAccountTransactionDetailss() throws Throwable {
        return accountTransactionDetailsService.getAllAccountTransactionDetails();
    }

    // Get One AccountTransactionDetails
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountTransactionDetails> getSingleAccountTransactionDetails(@PathVariable Long id) {
        return accountTransactionDetailsService.getSingleAccountTransactionDetails(id);
    }

    // Add New AccountTransactionDetails
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountTransactionDetails> createNewAccountTransactionDetails(@RequestBody AccountTransactionDetails accountTransactionDetails, HttpServletRequest req) {
        return accountTransactionDetailsService.addNewAccountTransactionDetails(accountTransactionDetails, req);
    }

    // Update AccountTransactionDetails with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountTransactionDetails> updateAccountTransactionDetails(@PathVariable Long id, @RequestBody AccountTransactionDetails accountTransactionDetails) {
        return accountTransactionDetailsService.updateAccountTransactionDetails(id, accountTransactionDetails);
    }

    // Delete AccountTransactionDetails
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AccountTransactionDetails> deleteAccountTransactionDetails(@PathVariable Long id) {
        return accountTransactionDetailsService.deleteAccountTransactionDetails(id);
    }
}
