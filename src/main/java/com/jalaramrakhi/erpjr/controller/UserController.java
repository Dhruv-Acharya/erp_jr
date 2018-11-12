package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.User;
import com.jalaramrakhi.erpjr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Delete User
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
