package com.university.repairagency.registration;

import com.university.repairagency.password.annotation.PasswordMatches;
import lombok.Data;
import lombok.ToString;

@Data
@PasswordMatches
@ToString
public class RegistrationRequest {

    private String firstName;
    private String lastName;

    private String username;

    private String password;
    private String matchingPassword;
}
