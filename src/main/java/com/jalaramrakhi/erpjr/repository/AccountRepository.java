package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
