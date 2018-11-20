package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.AccountTransactionDetailsNotFoundException;
import com.jalaramrakhi.erpjr.Exceptions.AccountTransactionDetailsMissingInformationException;
import com.jalaramrakhi.erpjr.entity.AccountTransactionDetails;
import com.jalaramrakhi.erpjr.repository.AccountTransactionDetailsRepository;
import com.jalaramrakhi.erpjr.repository.AccountTransactionDetailsRepository;
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
public class AccountTransactionDetailsServiceImpl implements AccountTransactionDetailsService{

    private AccountTransactionDetailsRepository accountTransactionDetailsRepository;

    @Autowired
    public AccountTransactionDetailsServiceImpl(AccountTransactionDetailsRepository accountTransactionDetailsRepository) {
        Assert.notNull(accountTransactionDetailsRepository, "AccountTransactionDetailsRepository must not be null!");
        this.accountTransactionDetailsRepository = accountTransactionDetailsRepository;
    }

    @Override
    public ResponseEntity<List<AccountTransactionDetails>> getAllAccountTransactionDetails() {
        List<AccountTransactionDetails> allAccountTransactionDetails = accountTransactionDetailsRepository.findAll();

        return new ResponseEntity<List<AccountTransactionDetails>>(allAccountTransactionDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountTransactionDetails> getSingleAccountTransactionDetails(Long id) {
        AccountTransactionDetails getAccountTransactionDetails = findAccountTransactionDetailsIfExists(id);
        return new ResponseEntity<AccountTransactionDetails>(getAccountTransactionDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountTransactionDetails> addNewAccountTransactionDetails(AccountTransactionDetails accountTransactionDetails, HttpServletRequest request) {
        if(accountTransactionDetails.getAccount() != null) {
            accountTransactionDetailsRepository.saveAndFlush(accountTransactionDetails);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", accountTransactionDetailsUrlHelper(accountTransactionDetails, request));
            return new ResponseEntity<AccountTransactionDetails>(accountTransactionDetails, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new AccountTransactionDetailsMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<AccountTransactionDetails> updateAccountTransactionDetails(Long id, AccountTransactionDetails accountTransactionDetails) {
        AccountTransactionDetails existingAccountTransactionDetails = findAccountTransactionDetailsIfExists(id);

        if(null != accountTransactionDetails.getAccount()) {
            BeanUtils.copyProperties(accountTransactionDetails, existingAccountTransactionDetails);

            // Ensure ID remains unchanged
            existingAccountTransactionDetails.setTransaction_id(id);

            AccountTransactionDetails updatedAccountTransactionDetails = accountTransactionDetailsRepository.saveAndFlush(existingAccountTransactionDetails);
            return new ResponseEntity<AccountTransactionDetails>(updatedAccountTransactionDetails, HttpStatus.OK);
        } else {
            throw new AccountTransactionDetailsMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<AccountTransactionDetails> deleteAccountTransactionDetails(Long id) {
        AccountTransactionDetails existingAccountTransactionDetails = findAccountTransactionDetailsIfExists(id);
        accountTransactionDetailsRepository.delete(existingAccountTransactionDetails);
        return new ResponseEntity<AccountTransactionDetails>(HttpStatus.NO_CONTENT);
    }

    private String accountTransactionDetailsUrlHelper(AccountTransactionDetails accountTransactionDetails, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(accountTransactionDetails.getTransaction_id());

        return resourcePath.toString();
    }

    private AccountTransactionDetails findAccountTransactionDetailsIfExists(Long id) {
        Optional<AccountTransactionDetails> accountTransactionDetails = accountTransactionDetailsRepository.findById(id);

        if(accountTransactionDetails.isPresent()){
            return accountTransactionDetails.get();
        }
        else {
            throw new AccountTransactionDetailsNotFoundException();
        }
    }
}
