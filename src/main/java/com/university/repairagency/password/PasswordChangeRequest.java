package com.university.repairagency.password;

import com.university.repairagency.password.annotation.OldPasswordMatches;
import com.university.repairagency.user.User;
import lombok.Data;

@Data
@OldPasswordMatches
public class PasswordChangeRequest {

    private User user;

    private String oldPassword;
    private String newPassword;

}
