package com.pk.thesis.devbook.util;

import com.pk.thesis.devbook.security.jwt.AuthTokenFilter;
import org.springframework.stereotype.Component;

@Component
public  class AuthUtil {

    private static AuthTokenFilter authentication;

    public AuthUtil(AuthTokenFilter authentication) {
        this.authentication = authentication;
    }

    public static String getLoggedUsername(){
        return authentication.getLoggedUsername();
    }

}
