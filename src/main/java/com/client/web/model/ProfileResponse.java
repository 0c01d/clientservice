package com.client.web.model;

import com.client.domain.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileResponse {
    private Integer id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String phone;
    private String gender;
    private String dateOfBirth;

    public ProfileResponse(Profile profile) {
        this.id = profile.getId();
        this.firstname = profile.getFirstname();
        this.middlename = profile.getMiddlename();
        this.lastname = profile.getLastname();
        this.email = profile.getEmail();
        this.phone = profile.getPhone();
        this.gender = profile.getGender();
        this.dateOfBirth = profile.getDateOfBirth();
    }
}