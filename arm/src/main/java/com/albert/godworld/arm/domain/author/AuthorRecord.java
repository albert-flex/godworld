package com.albert.godworld.arm.domain.author;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorRecord {

    @TableId
    private Long id;
    private Long authorId;
    private AuthorRecordType type;
    private Long destId;
    private String description;
    private Date time;

    public AuthorRecord() {
    }

    public AuthorRecord(Long authorId, AuthorRecordType type, Long destId) {
        String template="{authorId} {type} , destId: {destId}";
        String s=template
                .replace("{authorId}",authorId.toString())
                .replace("{type}",type.getDescription())
                .replace("{destId}",destId.toString());
        this.authorId = authorId;
        this.type = type;
        this.destId = destId;
        this.description=s;
        this.time=new Date();
    }
}
