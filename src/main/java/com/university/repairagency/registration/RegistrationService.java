package com.university.repairagency.registration;

import com.university.repairagency.password.PasswordService;
import com.university.repairagency.role.Role;
import com.university.repairagency.user.User;
import com.university.repairagency.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class RegistrationService {

    private UserService userService;
    private PasswordService passwordService;

    public void register(RegistrationRequest registrationRequest) {
        register(extractUserFromRequest(registrationRequest));
    }

    private void register(User user) {
        String encodedPassword = passwordService.encrypt(user.getPassword());

        user.setPassword(encodedPassword);
        user.setAuthorities(Set.of(Role.USER));

        userService.save(user);
    }

    private User extractUserFromRequest(RegistrationRequest registrationRequest) {
        return User.builder()
                .username(registrationRequest.getUsername())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .patronymic(registrationRequest.getPatronymic())
                .password(registrationRequest.getPassword())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }

}
