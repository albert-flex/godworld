package com.albert.godworld.arm.domain.author;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorInfo {

    @TableId
    private Long id;
    private Long userId;
    private Boolean locked;
    private String penName;
    private String phone;
    private String email;
    private String moto;
    private String address;
    private Date linkTime;
}
