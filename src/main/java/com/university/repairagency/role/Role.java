package com.university.repairagency.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MASTER, USER;

    @Override
    public String getAuthority() {
        return "ROLE_"+name();
    }
}
