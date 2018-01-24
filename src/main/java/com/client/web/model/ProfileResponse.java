package com.client.web.model;

import com.client.domain.Profile;

import java.util.UUID;

public class ProfileResponse {
    private Long id;
    private String email;
    private String phoneNumber;
    private UUID walletUUID;

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public UUID getWalletUUID() {
        return walletUUID;
    }

    public ProfileResponse(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.walletUUID = profile.getWalletUUID();
    }
}