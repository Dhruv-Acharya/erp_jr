package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompanyService {

    public ResponseEntity<List<Company>> getAllCompany();
    public ResponseEntity<Company> getSingleCompany(Integer id);
    public ResponseEntity<Company> createNewCompany(Company company, HttpServletRequest request);
    public ResponseEntity<Company> patchUpdateCompany(Long id, Company companyUpdates);
    public ResponseEntity<Company> deleteCompany(Long id);
    public ResponseEntity<User> addUser(User user, HttpServletRequest request);
}