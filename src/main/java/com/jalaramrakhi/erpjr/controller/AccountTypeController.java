package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.AccountType;
import com.jalaramrakhi.erpjr.service.AccountTypeService;
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
@RequestMapping(value = "/accountType")
@RestController
public class AccountTypeController {
    
    @Autowired
    private AccountTypeService accountTypeService;

    // List All Companies
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccountType>> getAllAccountTypes() throws Throwable {
        return accountTypeService.getAllAccountTypes();
    }

    // Get One AccountType
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountType> getSingleAccountType(@PathVariable Long id) {
        return accountTypeService.getSingleAccountType(id);
    }

    // Add New AccountType
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountType> createNewAccountType(@RequestBody AccountType accountType, HttpServletRequest req) {
        return accountTypeService.addNewAccountType(accountType, req);
    }

    // Update AccountType with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AccountType> updateAccountType(@PathVariable Long id, @RequestBody AccountType accountType) {
        return accountTypeService.updateAccountType(id, accountType);
    }

    // Delete AccountType
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AccountType> deleteAccountType(@PathVariable Long id) {
        return accountTypeService.deleteAccountType(id);
    }
}
