package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.InvoiceItem;
import com.jalaramrakhi.erpjr.entity.InvoiceItemIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
    InvoiceItem findByInvoiceItemIdentity(InvoiceItemIdentity invoiceItemIdentity);
}
