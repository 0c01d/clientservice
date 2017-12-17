package com.client.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileRequest {
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String phone;
    private String gender;
    private String dateOfBirth;
}

