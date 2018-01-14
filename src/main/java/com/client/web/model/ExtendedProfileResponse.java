package com.client.web.model;

import com.client.domain.ExtendedProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendedProfileResponse {

    private Integer id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private String dateOfBirth;
    private Integer profileId;

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
