package com.albert.godworld.arm.service.reader;

import com.albert.godworld.arm.domain.reader.ReaderShelfBook;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReaderShelfBookService extends IService<ReaderShelfBook> {

    Page<ReaderShelfBook> pageOf(Page<ReaderShelfBook> page,Long readerId);
}
