package com.passwordmanager.dto;

public class UpdatePasswordEntryRequest {
    private String title;
    private String website;
    private String username;
    private String encryptedPassword;
    private String notes;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getWebsite() {
        return website;
    }

    public String getTitle() {
        return title;
    }
}
