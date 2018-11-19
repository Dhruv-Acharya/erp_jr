package com.jalaramrakhi.erpjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jalaramrakhi.erpjr.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
