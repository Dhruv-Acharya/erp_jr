package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.AccountTransactionDetails;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountTransactionDetailsService {
    public ResponseEntity<List<AccountTransactionDetails>> getAllAccountTransactionDetails();
    public ResponseEntity<AccountTransactionDetails> getSingleAccountTransactionDetails(Long id);
    public ResponseEntity<AccountTransactionDetails> addNewAccountTransactionDetails(AccountTransactionDetails barcodeItemDetails, HttpServletRequest request);
    public ResponseEntity<AccountTransactionDetails> updateAccountTransactionDetails(Long id, AccountTransactionDetails barcodeItemDetails);
    public ResponseEntity<AccountTransactionDetails> deleteAccountTransactionDetails(Long id);
}
