package com.msusers.diego.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utilities {
    private Utilities() {
    }

    public static final String encryptText(String text){
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        return  bCryptPasswordEncoder.encode(text);
    }
    public static final  boolean isValid(String password, Pattern pattern) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
