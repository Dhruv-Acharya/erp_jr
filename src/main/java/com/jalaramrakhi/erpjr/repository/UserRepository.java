package com.jalaramrakhi.erpjr.repository;

import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM tbl_user WHERE user_name = ?1 AND company_id = ?2", nativeQuery = true)
    User findUserByCompany(String username, Long company);
}