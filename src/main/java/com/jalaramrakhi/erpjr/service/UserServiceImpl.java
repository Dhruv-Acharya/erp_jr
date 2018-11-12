package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.UserIncorrectInformationException;
import com.jalaramrakhi.erpjr.Exceptions.UserNotFoundException;
import com.jalaramrakhi.erpjr.entity.User;
import com.jalaramrakhi.erpjr.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public void UserServiceImpl(UserRepository userRepository) {
        Assert.notNull(userRepository, "UserRepository must not be null!");
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> getSingleUser(Integer id) {
        User getUser = findUserIfExists(id);
        return new ResponseEntity<User>(getUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> createNewUser(User user, HttpServletRequest request) {
        if(user.getUser_name() != null && user.getUser_name().length() > 0) {
            if(user.getUser_password() != null && user.getUser_password().length() > 6) {
                User newUser = userRepository.saveAndFlush(user);
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Location", userUrlHelper(newUser, request));
                return new ResponseEntity<User>(newUser, responseHeaders, HttpStatus.CREATED);
            }
            else {
                throw new UserIncorrectInformationException();
            }
        }
        else {
            throw new UserIncorrectInformationException();
        }
    }

    @Override
    public ResponseEntity<User> updateUser(Integer id, User userUpdates) {
        User existingUser = findUserIfExists(id);

        if(userUpdates.getUser_name() != null && userUpdates.getUser_name().length() > 0) {
            if(userUpdates.getUser_password() != null && userUpdates.getUser_password().length() > 6) {
                BeanUtils.copyProperties(userUpdates, existingUser);

                // Ensure ID remains unchanged
                existingUser.setUser_id(id);

                User updatedUser = userRepository.saveAndFlush(existingUser);
                return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
            }
            else {
                throw new UserIncorrectInformationException();
            }
        }
        else {
            throw new UserIncorrectInformationException();
        }
    }

    @Override
    public ResponseEntity<User> deleteUser(Integer id) {
        User existingUser = findUserIfExists(id);
        userRepository.delete(existingUser);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }


    private String userUrlHelper(User user, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(user.getUser_id());

        return resourcePath.toString();
    }

    private User findUserIfExists(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
