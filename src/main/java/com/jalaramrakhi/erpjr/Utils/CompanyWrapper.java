package com.jalaramrakhi.erpjr.Utils;

public class CompanyWrapper {
    private String company_name;
    private String user_password;
    private String user_confirm_password;
    private String user_name;

    public CompanyWrapper(String company_name, String user_password, String user_confirm_password, String user_name) {
        this.company_name = company_name;
        this.user_password = user_password;
        this.user_confirm_password = user_confirm_password;
        this.user_name = user_name;
    }

    public CompanyWrapper() {
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_confirm_password() {
        return user_confirm_password;
    }

    public void setUser_confirm_password(String user_confirm_password) {
        this.user_confirm_password = user_confirm_password;
    }
}
