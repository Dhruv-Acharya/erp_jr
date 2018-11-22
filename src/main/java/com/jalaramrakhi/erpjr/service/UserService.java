package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public ResponseEntity<User> getSingleUser(Long id);
    public ResponseEntity<User> getSingleUserbyUsername(String username, Long company_id);
    public ResponseEntity<User> createNewUser(User user, HttpServletRequest request);
    public ResponseEntity<User> updateUser(Long id, User userUpdates);
    public ResponseEntity<User> deleteUser(Long id);
}
