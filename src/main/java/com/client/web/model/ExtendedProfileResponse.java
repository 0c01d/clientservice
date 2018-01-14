package com.client.web.model;

import com.client.domain.ExtendedProfile;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendedProfileResponse {

    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private String dateOfBirth;
    private Long profileId;

    public Long getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getMiddlename() {
        return middlename;
    }
    public String getLastname() {
        return lastname;
    }
    public String getGender() {
        return gender;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public Long getProfileId() {
        return profileId;
    }

    public ExtendedProfileResponse(ExtendedProfile extendedProfile){
        this.id = extendedProfile.getId();
        this.firstname = extendedProfile.getFirstname();
        this.middlename = extendedProfile.getMiddlename();
        this.lastname = extendedProfile.getLastname();
        this.gender = extendedProfile.getGender();
        this.dateOfBirth = extendedProfile.getDateOfBirth();
        this.profileId = extendedProfile.getProfile().getId();
    }

}
