package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.CompanyMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.CompanyNotFoundException;
import com.jalaramrakhi.erpjr.Exceptions.UserIncorrectInformationException;
import com.jalaramrakhi.erpjr.Utils.CompanyWrapper;
import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.User;
import com.jalaramrakhi.erpjr.repository.CompanyRepository;
import com.jalaramrakhi.erpjr.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void CompanyServiceImpl(CompanyRepository companyRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Assert.notNull(companyRepository, "CompanyRepository must not be null!");
        Assert.notNull(userRepository, "UserRepository must not be null!");
        Assert.notNull(userRepository, "bCryptPasswordEncoder must not be null!");
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEntity<List<Company>> getAllCompany() {
        List<Company> allCompanies = companyRepository.findAll();

        return new ResponseEntity<List<Company>>(allCompanies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Company> getSingleCompany(Integer id) {
        Company getCompany = findCompanyIfExists(id);
        return new ResponseEntity<Company>(getCompany, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Company> createNewCompany(CompanyWrapper companyWrapper, HttpServletRequest request) {
        if(null != companyWrapper.getCompany_name() && companyWrapper.getCompany_name().length() > 0) {
            if(companyWrapper.getUser_name() != null && companyWrapper.getUser_name().length() > 0) {
                if (companyWrapper.getUser_password() != null && companyWrapper.getUser_password().length() > 6) {

                    Company newCompany = new Company(companyWrapper.getCompany_name());
                    companyRepository.saveAndFlush(newCompany);

                    User newUser = new User(companyWrapper.getUser_name(), companyWrapper.getUser_password(), companyWrapper.getUser_confirm_password(), newCompany);
                    newUser.setUser_password(bCryptPasswordEncoder.encode(newUser.getUser_password()));
                    userRepository.saveAndFlush(newUser);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("Location", companyUrlHelper(newCompany, request));
                    return new ResponseEntity<Company>(newCompany, responseHeaders, HttpStatus.CREATED);
                }
                else {
                    throw new UserIncorrectInformationException();
                }
            }
            else {
                throw new UserIncorrectInformationException();
            }
        } else {
            throw new CompanyMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<Company> updateCompany(Integer id, Company companyUpdates) {
        Company existingCompany = findCompanyIfExists(id);

        if(null != companyUpdates.getCompany_name() && companyUpdates.getCompany_name().length() > 0) {
            BeanUtils.copyProperties(companyUpdates, existingCompany);

            // Ensure ID remains unchanged
            existingCompany.setCompany_id(id);

            Company updatedCompany = companyRepository.saveAndFlush(existingCompany);
            return new ResponseEntity<Company>(updatedCompany, HttpStatus.OK);
        } else {
            throw new CompanyMissingInformationException();
        }
    }

//    @Override
//    public ResponseEntity<Company> deleteCompany(Long id) {
//        return null;
//    }


    private String companyUrlHelper(Company company, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(company.getCompany_id());

        return resourcePath.toString();
    }

    private Company findCompanyIfExists(Integer id) {
        Optional<Company> company = companyRepository.findById(id);

        if(company.isPresent()){
            return company.get();
        }
        else {
            throw new CompanyNotFoundException();
        }
    }
}
