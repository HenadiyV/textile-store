package com.vognev.textilewebproject;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * textilewebproject  07/10/2021-6:49
 */
public class DumpPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return String.format("secret: %s",rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
