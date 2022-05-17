package com.albert.godworld.arm.resource.dto;

import com.albert.godworld.arm.resource.domain.user.User;
import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private Long authorId;
    private Long socialId;

    private UserDTO(){

    }

    public static UserDTO create(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(user.getId()+"");
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
