package com.client.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class ProfileRequest {

    @Email
    private String email;
    @NotEmpty
    private String phoneNumber;

    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileRequest(@JsonProperty("email") String email,
                          @JsonProperty("phoneNumber") String phoneNumber){
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

