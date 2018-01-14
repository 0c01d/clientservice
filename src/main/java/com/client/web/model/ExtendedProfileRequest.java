package com.client.web.model;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExtendedProfileRequest {

    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private String dateOfBirth;
    private Integer profileId;
}
