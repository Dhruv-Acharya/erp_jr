package com.jalaramrakhi.erpjr.service;

import com.jalaramrakhi.erpjr.Exceptions.StateMissingInformationException;
import com.jalaramrakhi.erpjr.Exceptions.StateNotFoundException;
import com.jalaramrakhi.erpjr.entity.State;
import com.jalaramrakhi.erpjr.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService{

    private StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        Assert.notNull(stateRepository, "StateRepository must not be null!");
        this.stateRepository = stateRepository;
    }

    @Override
    public ResponseEntity<List<State>> getAllStates() {
        List<State> allStates = stateRepository.findAll();

        return new ResponseEntity<List<State>>(allStates, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<State> getSingleState(Long id) {
        State getState = findStateIfExists(id);
        return new ResponseEntity<State>(getState, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<State> addNewState(State state, HttpServletRequest request) {
        if(null != state.getState_name() && state.getState_name().length() > 0) {
            stateRepository.saveAndFlush(state);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", stateUrlHelper(state, request));
            return new ResponseEntity<State>(state, responseHeaders, HttpStatus.CREATED);
        }
        else {
            throw new StateMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<State> updateState(Long id, State state) {
        State existingState = findStateIfExists(id);

        if(null != state.getState_name() && state.getState_name().length() > 0) {
            BeanUtils.copyProperties(state, existingState);

            // Ensure ID remains unchanged
            existingState.setState_id(id);

            State updatedState = stateRepository.saveAndFlush(existingState);
            return new ResponseEntity<State>(updatedState, HttpStatus.OK);
        } else {
            throw new StateMissingInformationException();
        }
    }

    @Override
    public ResponseEntity<State> deleteState(Long id) {
        State existingState = findStateIfExists(id);
        stateRepository.delete(existingState);
        return new ResponseEntity<State>(HttpStatus.NO_CONTENT);
    }

    private String stateUrlHelper(State state, HttpServletRequest request) {
        StringBuilder resourcePath = new StringBuilder();

        resourcePath.append(request.getRequestURL());
        resourcePath.append("/");
        resourcePath.append(state.getState_id());

        return resourcePath.toString();
    }

    private State findStateIfExists(Long id) {
        Optional<State> state = stateRepository.findById(id);

        if(state.isPresent()){
            return state.get();
        }
        else {
            throw new StateNotFoundException();
        }
    }
}
