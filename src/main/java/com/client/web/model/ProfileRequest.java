package com.client.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class ProfileRequest {

    @NotEmpty(message = "Enter firstname")
    @Size(min = 2, max = 15, message = "Size invalid")
    private String firstname;

    @NotEmpty(message = "Enter middlename")
    @Size(min = 2, max = 15, message = "Size invalid")
    private String middlename;

    @NotEmpty(message = "Enter lastname")
    @Size(min = 2, max = 15, message = "Size invalid")
    private String lastname;

    @Email
    @NotEmpty(message = "Enter email")
    @Size(min = 2, max = 45, message = "Size invalid")
    private String email;

    @NotEmpty(message = "Enter current phone")
    private String phone;

    @NotEmpty(message = "Enter current gender")
    private String gender;

    @NotEmpty(message = "Enter current gender")
    @Size(min = 6, max = 10, message = "Size invalid")
    private String dateOfBirth;
}

