package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Utils.CompanyWrapper;
import com.jalaramrakhi.erpjr.entity.Company;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompanyService {

    public ResponseEntity<List<Company>> getAllCompany();
    public ResponseEntity<Company> getSingleCompany(Long id);
    public ResponseEntity<Company> createNewCompany(CompanyWrapper companyWrapper, HttpServletRequest request);
    public ResponseEntity<Company> updateCompany(Long id, Company companyUpdates);
//    public ResponseEntity<Company> deleteCompany(Long id);
}