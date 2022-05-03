package com.albert.godworld.arm.authorize.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class MyUserAuthenticationConvert extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String,Object> response=new LinkedHashMap<>();
        response.put("user_name",authentication);
        return response;
    }
}
