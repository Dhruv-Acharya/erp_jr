package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.AccountMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.AccountNotFoundException;
import com.jalaramrakhi.erpjr.entity.Account;
import com.jalaramrakhi.erpjr.repository.AccountRepository;
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
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        Assert.notNull(accountRepository, "AccountRepository must not be null!");
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();

        return new ResponseEntity<List<Account>>(allAccounts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> getSingleAccount(Integer id) {
        Account getAccount = findAccountIfExists(id);
        return new ResponseEntity<Account>(getAccount, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Account> addNewAccount(Account account, HttpServletRequest request) {
        if(null != account.getAccount_name() && account.getAccount_name().length() > 0) {
            accountRepository.saveAndFlush(account);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", accountUrlHelper(account, request));
            return new ResponseEntity<Account>(account, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new AccountMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Account> updateAccount(Integer id, Account account) {
        Account existingAccount = findAccountIfExists(id);

        if(null != account.getAccount_name() && account.getAccount_name().length() > 0) {
            BeanUtils.copyProperties(account, existingAccount);

            // Ensure ID remains unchanged
            existingAccount.setAccount_code(id);

            Account updatedAccount = accountRepository.saveAndFlush(existingAccount);
            return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
        } else {
            throw new AccountMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Account> deleteAccount(Integer id) {
        Account existingAccount = findAccountIfExists(id);
        accountRepository.delete(existingAccount);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }

    private String accountUrlHelper(Account account, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(account.getAccount_code());

        return resourcePath.toString();
    }

    private Account findAccountIfExists(Integer id) {
        Optional<Account> account = accountRepository.findById(id);

        if(account.isPresent()){
            return account.get();
        }
        else {
            throw new AccountNotFoundException();
        }
    }
}
