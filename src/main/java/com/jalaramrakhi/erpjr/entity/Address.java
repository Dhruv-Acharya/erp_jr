package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long addresss_id;
    private String address_line;
    @OneToOne
    @JoinColumn(name = "state_id")
    private State state;

    public Address() {
    }

    public Address(String address_line, State state) {
        this.address_line = address_line;
        this.state = state;
    }

    public Long getAddress_id() {
        return addresss_id;
    }

    public void setAddress_id(Long addresss_id) {
        this.addresss_id = addresss_id;
    }

    public String getAddress_line() {
        return address_line;
    }

    public void setAddress_line(String address_line) {
        this.address_line = address_line;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
