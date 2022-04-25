package com.albert.godworld.arm.service.author;

import com.albert.godworld.arm.domain.author.AuthorRecord;
import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorRecordService extends IService<AuthorRecord> {

    void putRecord(Long authorId, AuthorRecordType type,Long destId);
}
