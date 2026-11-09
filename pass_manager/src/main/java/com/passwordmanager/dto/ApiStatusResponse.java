package com.passwordmanager.dto;

public class ApiStatusResponse {
    private String application;
    private String status;
    private String version;
    private String description;

    public ApiStatusResponse(String application, String status, String version, String description){
        this.application = application;
        this.status = status;
        this.version = version;
        this.description = description;
    }

    public String getApplication(){
        return application;
    }

    public String getStatus(){
        return status;
    }

    public String getVersion(){
        return version;
    }

    public String getDescription(){
        return description;
    }
}
