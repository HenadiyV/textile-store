package com.vognev.textilewebproject.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * textilewebproject  18/09/2021-9:38
 */
public enum Role implements GrantedAuthority {
    USER,ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
