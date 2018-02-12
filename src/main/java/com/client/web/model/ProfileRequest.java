package com.client.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.UUID;

public class ProfileRequest {

    @Email
    private String email;
    @NotEmpty
    private String phoneNumber;

    private UUID walletUUID;
    private String username;

    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public UUID getWalletUUID() {
        return walletUUID;
    }
    public String getUsername() {
        return username;
    }

    public ProfileRequest(@JsonProperty("email") String email,
                          @JsonProperty("phoneNumber") String phoneNumber,
                          @JsonProperty("walletUUID") UUID walletUUID,
                          @JsonProperty("username") String username){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.walletUUID = walletUUID;
        this.username = username;
    }
}

