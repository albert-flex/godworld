package com.albert.godworld.arm.service.author.impl;

import com.albert.godworld.arm.domain.author.AuthorRecord;
import com.albert.godworld.arm.domain.author.AuthorRecordType;
import com.albert.godworld.arm.mapper.author.AuthorRecordMapper;
import com.albert.godworld.arm.service.author.AuthorRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorRecordServiceSPI extends ServiceImpl<AuthorRecordMapper, AuthorRecord>
    implements AuthorRecordService {

    @Override
    public void putRecord(Long authorId,AuthorRecordType type,Long destId) {
        AuthorRecord record = new AuthorRecord(authorId, AuthorRecordType.PUBLISH_BOOK, destId);
        super.save(record);
    }
}
