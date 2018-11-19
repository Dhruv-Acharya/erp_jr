package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.Account;
import com.jalaramrakhi.erpjr.service.AccountService;
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
@RequestMapping(value = "/account")
@RestController
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    // List All Companies
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts() throws Throwable {
        return accountService.getAllAccounts();
    }

    // Get One Account
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getSingleAccount(@PathVariable Integer id) {
        return accountService.getSingleAccount(id);
    }

    // Add New Account
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> createNewAccount(@RequestBody Account account, HttpServletRequest req) {
        return accountService.addNewAccount(account, req);
    }

    // Update Account with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    // Delete Account
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteAccount(@PathVariable Integer id) {
        return accountService.deleteAccount(id);
    }
}
