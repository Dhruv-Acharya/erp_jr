package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class State {
    @Id
    @GeneratedValue
    private Long state_id;
    private String state_name;

    public State(String state_name) {
        this.state_name = state_name;
    }

    public State() {
    }

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
