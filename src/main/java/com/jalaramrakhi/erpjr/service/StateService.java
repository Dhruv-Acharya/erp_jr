package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.entity.State;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StateService {
    public ResponseEntity<List<State>> getAllStates();
    public ResponseEntity<State> getSingleState(Integer id);
    public ResponseEntity<State> addNewState(State state, HttpServletRequest request);
    public ResponseEntity<State> updateState(Integer id, State state);
    public ResponseEntity<State> deleteState(Integer id);
}
