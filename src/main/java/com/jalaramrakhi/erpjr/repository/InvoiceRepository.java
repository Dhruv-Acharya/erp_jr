package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
