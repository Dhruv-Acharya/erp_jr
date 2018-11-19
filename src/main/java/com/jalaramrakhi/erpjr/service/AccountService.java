package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Account;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService {
    public ResponseEntity<List<Account>> getAllAccounts();
    public ResponseEntity<Account> getSingleAccount(Long id);
    public ResponseEntity<Account> addNewAccount(Account account, HttpServletRequest request);
    public ResponseEntity<Account> updateAccount(Long id, Account account);
    public ResponseEntity<Account> deleteAccount(Long id);
}
