package com.albert.godworld.arm.authorize.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyUserAuthenticationConvert extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String,Object> response=new LinkedHashMap<>();
        response.put("user_name",authentication);
        Collection<? extends GrantedAuthority> as=authentication.getAuthorities();
        List<String> ats=new LinkedList<>();
        for(GrantedAuthority a:as){
            ats.add(a.getAuthority());
        }
        response.put("authorities",ats);
        return response;
    }
}
