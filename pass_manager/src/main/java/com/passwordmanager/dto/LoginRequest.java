package com.passwordmanager.dto;

public class LoginRequest {
    private String username;
    private String website;

    public LoginRequest(String username, String website){
        this.username = username;
        this.website = website;
    }

    public String getUsername(){
        return username;
    }

    public String getWebsite(){
        return website;
    }
}
