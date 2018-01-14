package com.client.web.model;

import com.client.domain.Profile;

public class ProfileResponse {
    private Long id;
    private String email;
    private String phoneNumber;

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileResponse(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
    }
}