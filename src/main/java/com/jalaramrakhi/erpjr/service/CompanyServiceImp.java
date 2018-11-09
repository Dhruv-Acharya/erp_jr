package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.User;
import com.jalaramrakhi.erpjr.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public void CompanyServiceImpl(CompanyRepository companyRepository) {
        Assert.notNull(companyRepository, "CompanyRepository must not be null!");
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<List<Company>> getAllCompany() {
        List<Company> allCompanies = companyRepository.findAll();

        return new ResponseEntity<List<Company>>(allCompanies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Company> getSingleCompany(Integer id) {
        Company company = companyRepository.findOne(id);

        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Company> createNewCompany(Company contact, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Company> patchUpdateCompany(Long id, Company contactUpdates) {
        return null;
    }

    @Override
    public ResponseEntity<Company> deleteCompany(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<User> addUser(User user, HttpServletRequest request) {
        return null;
    }
}
