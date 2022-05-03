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

    public static void main(String[] args) {
        String text="2022-05-03T23:23:49.000+00:00";
        try {
            Date date=new PrincipalConvert().convert(text);
            System.out.println(date.toString());
        } catch (ParseException e) {
            System.out.println("errorIndex:"+e.getErrorOffset());
        }
    }

    public Date convert(String dateStr) throws ParseException {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        DateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=df.parse(dateStr);
        return df2.parse(df2.format(date));
    }

    public User convert(Principal principal){
        OAuth2Authentication authentication=(OAuth2Authentication) principal;
        Map<String,Object> ob= (Map<String, Object>) authentication.getPrincipal();
        Map<String,Object> b= (Map<String, Object>) ob.get("principal");
        User user=new User();
        user.setId(Long.parseLong(b.get("id")+""));
        user.setUsername(""+b.get("username"));
        if(b.containsKey("createTime")){
            try {
                user.setCreateTime(convert(""+b.get("createTime")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if(b.containsKey("lastLoginTime")){
            try {
                user.setLastLoginTime(convert(""+b.get("lastLoginTime")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        user.setEnabled(Boolean.parseBoolean(b.get("enabled")+""));
        user.setAllPermission((List<Permission>)b.get("allPermission"));
        return user;
    }
}
