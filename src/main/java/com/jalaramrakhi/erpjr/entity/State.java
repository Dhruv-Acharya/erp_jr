package com.jalaramrakhi.erpjr.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class State {
    @Id
    private int state_id;
    private String state_name;
}
