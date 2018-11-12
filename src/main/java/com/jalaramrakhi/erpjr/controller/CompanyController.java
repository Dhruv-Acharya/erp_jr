package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// List All Companies
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getAllCompanies() throws Throwable {
		return companyService.getAllCompany();
	}

	// List One Company
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Company> getSingleCompany(@PathVariable Integer id) throws Throwable {
		return companyService.getSingleCompany(id);
	}

	// Create New Company
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Company> createNewCompany(@RequestBody Company company, HttpServletRequest req) {
		return companyService.createNewCompany(company, req);
	}

	// Update Contact with PATCH
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Company> updateCompany(@PathVariable Integer id, @RequestBody Company company) {
		return companyService.updateCompany(id, company);
	}
}

