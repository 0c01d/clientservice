package com.client.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class ProfileRequest {

    /*@NotEmpty(message = "Enter firstname")
    @Size(min = 2, max = 15, message = "Size invalid")*/


    /*@NotEmpty(message = "Enter middlename")
    @Size(min = 2, max = 15, message = "Size invalid")*/


    /*@NotEmpty(message = "Enter lastname")
    @Size(min = 2, max = 15, message = "Size invalid")*/

    /*@Email
    @NotEmpty(message = "Enter email")
    @Size(min = 2, max = 45, message = "Size invalid")*/
    private String email;

    /*@NotEmpty(message = "Enter current phone")*/
    private String phoneNumber;

    /*@NotEmpty(message = "Enter current gender")*/

    /*@NotEmpty(message = "Enter current gender")
    @Size(min = 6, max = 10, message = "Size invalid")*/
}

