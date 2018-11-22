package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.User;
import com.jalaramrakhi.erpjr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get One User
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getSingleUser(@PathVariable Long id) {
        return userService.getSingleUser(id);
    }

    // Add New User
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createNewUser(@RequestBody User user, HttpServletRequest req) {
        return userService.createNewUser(user, req);
    }

    // Get One User by Username
    @RequestMapping(value = "/{username}/{company_id}", method = RequestMethod.GET)
    public ResponseEntity<User> getSingleUserbyUsername(@PathVariable String username, @PathVariable Long company_id) {
        return userService.getSingleUserbyUsername(username, company_id);
    }

    // Update User with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete User
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
