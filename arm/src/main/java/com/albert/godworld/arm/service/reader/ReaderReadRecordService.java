package com.albert.godworld.arm.service.reader;

import com.albert.godworld.arm.domain.reader.ReaderReadRecord;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReaderReadRecordService extends IService<ReaderReadRecord> {

    void markReadBook(Long readerId,Long bookId,Long chapterId);

    Page<ReaderReadRecord> pageOf(Page<ReaderReadRecord> page,Long readerId);
}
