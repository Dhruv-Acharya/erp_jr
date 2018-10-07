package com.jalaramrakhi.erpjr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jalaramrakhi.erpjr.repository.CompanyRepository;

@RestController
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@RequestMapping("/add")
	public String add() {
		return "Hi";
	}
}
