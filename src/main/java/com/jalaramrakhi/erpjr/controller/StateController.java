package com.jalaramrakhi.erpjr.controller;

import com.jalaramrakhi.erpjr.entity.State;
import com.jalaramrakhi.erpjr.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/state")
@RestController
public class StateController {
    
    @Autowired
    private StateService stateService;

    // List All States
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<State>> getAllStates() throws Throwable {
        return stateService.getAllStates();
    }

    // Get One State
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<State> getSingleState(@PathVariable Long id) {
        return stateService.getSingleState(id);
    }

    // Add New State
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<State> createNewState(@RequestBody State state, HttpServletRequest req) {
        return stateService.addNewState(state, req);
    }

    // Update State with PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<State> updateState(@PathVariable Long id, @RequestBody State state) {
        return stateService.updateState(id, state);
    }

    // Delete State
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<State> deleteState(@PathVariable Long id) {
        return stateService.deleteState(id);
    }
}
