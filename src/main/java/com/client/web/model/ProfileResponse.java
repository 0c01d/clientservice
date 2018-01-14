package com.client.web.model;

import com.client.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
public class ProfileResponse {
    private Integer id;
    private String email;
    private String phoneNumber;

    public ProfileResponse(Profile profile) {
        this.id = profile.getId();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
    }
}