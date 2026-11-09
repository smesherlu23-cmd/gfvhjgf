package com.passwordmanager.dto;

import jakarta.validation.constraints.NotBlank;


public class CreatePasswordEntryRequest {
    @NotBlank(message = "Название записи обязательно!")
    private String title;
    @NotBlank(message = "Названиек вебсайта обязательно!")
    private String website;
    @NotBlank(message = "Никнейм обязателен!")
    private String username;
    @NotBlank(message = "Пароль обязателен!")
    private String encryptedPassword;
    private String notes;

    public CreatePasswordEntryRequest(){
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
