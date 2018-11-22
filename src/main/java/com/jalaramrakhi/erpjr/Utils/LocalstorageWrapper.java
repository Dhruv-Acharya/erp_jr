package com.jalaramrakhi.erpjr.Utils;

import com.jalaramrakhi.erpjr.entity.Company;
import com.jalaramrakhi.erpjr.entity.User;

public class LocalstorageWrapper {
    private String token;
    private Company company;
    private User user;

    public LocalstorageWrapper(String token, Company company, User user) {
        this.token = token;
        this.company = company;
        this.user = user;
    }

    public LocalstorageWrapper() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{\"token\" : \"" + token + '\"' +
                ", \"company\" : " + company.toString() +
                ", \"user\" : " + user.toString()+ "}";
    }
}
