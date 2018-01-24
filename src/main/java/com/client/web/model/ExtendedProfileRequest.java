package com.client.web.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExtendedProfileRequest {

    @Size(min = 3, max = 15)
    private String firstname;

    @Size(min = 3, max = 15)
    private String middlename;

    @Size(min = 3, max = 15)
    private String lastname;


    private String gender;


    private String dateOfBirth;


    private Long profileId;

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

    public ExtendedProfileRequest(@JsonProperty("firstname") String firstname,
                                  @JsonProperty("middlename") String middlename,
                                  @JsonProperty("lastname") String lastname,
                                  @JsonProperty("gender") String gender,
                                  @JsonProperty("dateOfBirth") String dateOfBirth,
                                  @JsonProperty("profileId") Long profileId ){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.profileId = profileId;
    }

}
