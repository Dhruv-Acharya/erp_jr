package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
