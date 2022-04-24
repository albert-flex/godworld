package com.albert.godworld.arm.domain.reader;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ReaderInfo {

    @TableId
    private Long id;
    private Long userId;
    private String name;
    private Boolean locked;
    private String phone;
    private String email;
    private String moto;
}
