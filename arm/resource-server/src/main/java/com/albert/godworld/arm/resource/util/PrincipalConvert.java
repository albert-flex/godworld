package com.albert.godworld.arm.resource.util;

import com.albert.godworld.arm.resource.domain.user.Permission;
import com.albert.godworld.arm.resource.domain.user.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class PrincipalConvert {

//    public static void main(String[] args) {
//        String text="2022-05-03T23:23:49.000+08:00";
//        try {
//        Date date=new PrincipalConvert().convert(text);
//            System.out.println(date.toString());
//        } catch (ParseException e) {
//            System.out.println("errorIndex:"+e.getErrorOffset());
//        }
//    }

    public Date convert(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.parse(dateStr);
    }

    public User convert(Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        Map<String, Object> ob = (Map<String, Object>) authentication.getPrincipal();
        Map<String, Object> b = (Map<String, Object>) ob.get("principal");
        User user = new User();
        user.setId(Long.parseLong(b.get("id") + ""));
        user.setUsername("" + b.get("username"));
        if (b.containsKey("createTime")) {
            Object lt = b.get("createTime");
            if (lt != null) {
                try {
                    user.setCreateTime(convert((String) lt));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (b.containsKey("lastLoginTime")) {
            Object lt = b.get("lastLoginTime");
            if (lt != null) {
                try {
                    user.setLastLoginTime(convert((String) lt));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        user.setLocked(Boolean.parseBoolean(b.get("lock") + ""));
        user.setLogin(Boolean.parseBoolean(b.get("login")+""));
        user.setEmail((String) b.get("email"));
        user.setAllPermission((List<Permission>) b.get("allPermission"));
        return user;
    }
}
