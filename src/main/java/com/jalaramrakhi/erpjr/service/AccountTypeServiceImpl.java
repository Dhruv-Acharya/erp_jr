package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.AccountTypeMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.AccountTypeNotFoundException;
import com.jalaramrakhi.erpjr.entity.AccountType;
import com.jalaramrakhi.erpjr.repository.AccountTypeRepository;
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
public class AccountTypeServiceImpl implements AccountTypeService{

    private AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        Assert.notNull(accountTypeRepository, "AccountTypeRepository must not be null!");
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        List<AccountType> allAccountTypes = accountTypeRepository.findAll();

        return new ResponseEntity<List<AccountType>>(allAccountTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountType> getSingleAccountType(Long id) {
        AccountType getAccountType = findAccountTypeIfExists(id);
        return new ResponseEntity<AccountType>(getAccountType, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountType> addNewAccountType(AccountType accountType, HttpServletRequest request) {
        if(null != accountType.getAccount_type() && accountType.getAccount_type().length() > 0) {
            accountTypeRepository.saveAndFlush(accountType);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", accountTypeUrlHelper(accountType, request));
            return new ResponseEntity<AccountType>(accountType, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new AccountTypeMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<AccountType> updateAccountType(Long id, AccountType accountType) {
        AccountType existingAccountType = findAccountTypeIfExists(id);

        if(null != accountType.getAccount_type() && accountType.getAccount_type().length() > 0) {
            BeanUtils.copyProperties(accountType, existingAccountType);

            // Ensure ID remains unchanged
            existingAccountType.setAccount_id(id);

            AccountType updatedAccountType = accountTypeRepository.saveAndFlush(existingAccountType);
            return new ResponseEntity<AccountType>(updatedAccountType, HttpStatus.OK);
        } else {
            throw new AccountTypeMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<AccountType> deleteAccountType(Long id) {
        AccountType existingAccountType = findAccountTypeIfExists(id);
        accountTypeRepository.delete(existingAccountType);
        return new ResponseEntity<AccountType>(HttpStatus.NO_CONTENT);
    }

    private String accountTypeUrlHelper(AccountType accountType, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(accountType.getAccount_id());

        return resourcePath.toString();
    }

    private AccountType findAccountTypeIfExists(Long id) {
        Optional<AccountType> accountType = accountTypeRepository.findById(id);

        if(accountType.isPresent()){
            return accountType.get();
        }
        else {
            throw new AccountTypeNotFoundException();
        }
    }
}
