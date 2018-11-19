package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.AccountType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountTypeService {
    public ResponseEntity<List<AccountType>> getAllAccountTypes();
    public ResponseEntity<AccountType> getSingleAccountType(Long id);
    public ResponseEntity<AccountType> addNewAccountType(AccountType accountType, HttpServletRequest request);
    public ResponseEntity<AccountType> updateAccountType(Long id, AccountType accountType);
    public ResponseEntity<AccountType> deleteAccountType(Long id);
}
