package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String add(@RequestBody Company company) {
//		//Company company = new Company(req)
//		Company result = companyRepository.save(company);
//		if(result != null) {
//			return "Successful";
//		}
//		else  {
//			return "Something Went Wrong!";
//		}
//	}
//
//	@RequestMapping(method = RequestMethod.DELETE)
//	public String remove(@RequestBody Company company) {
//		//Company company = new Company(req)
//		companyRepository.delete(company);
//		return company.getCompany_name();
//	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getAllCompanies() throws Throwable {
		return companyService.getAllCompany();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Company> getSingleCompany(@PathVariable Integer id) throws Throwable {
		return companyService.getSingleCompany(id);
	}
}

