package com.university.repairagency.password;

import com.university.repairagency.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordService {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean isMatches(String firstPassword, String secondPassword) {
        return (firstPassword != null && secondPassword != null) &&
                (firstPassword.equals(secondPassword));
    }

    public boolean isEncryptedMatches(String notEncrypted, String encryptedPassword) {
        return (notEncrypted != null && encryptedPassword != null) &&
                passwordEncoder.matches(notEncrypted, encryptedPassword);
    }

//    public void changePassword(PasswordChangeRequest passwordChangeRequest) {
//        User user = passwordChangeRequest.getUser();
//
//        if (isEncryptedMatches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
//            String encryptedNewPassword = encrypt(passwordChangeRequest.getNewPassword());
//            user.setPassword(encryptedNewPassword);
//            userService.save(user);
//        } else {
//            //TODO add error message to binding result
//        }
//    }
//
//    public boolean isMatches(String firstPassword, String secondPassword) {
//        return (firstPassword != null && secondPassword != null) &&
//                (firstPassword.equals(secondPassword));
//    }
//
//    private boolean isEncryptedMatches(String decryptedPassword, String encryptedPassword) {
//        return passwordEncoder.matches(decryptedPassword, encryptedPassword);
//    }

}
