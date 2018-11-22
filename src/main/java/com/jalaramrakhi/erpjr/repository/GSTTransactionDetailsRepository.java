package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.GSTTransactionDetails;
import com.jalaramrakhi.erpjr.entity.GSTTransactionDetailsIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GSTTransactionDetailsRepository extends JpaRepository<GSTTransactionDetails, GSTTransactionDetailsIdentity> {
}
