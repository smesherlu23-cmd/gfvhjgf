package com.passwordmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class PasswordEntry{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String website;
    private String username;
    private String encryptedPassword;
    private String notes;

    protected PasswordEntry(){

    }

    public PasswordEntry(String title, String website, String username, String encryptedPassword, String notes){
        this.title = title;
        this.website = website;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.notes = notes;
    }

    public Long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getNotes() {
        return notes;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEncryptedPassword(String encryptedPassword){
        this.encryptedPassword = encryptedPassword;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

}