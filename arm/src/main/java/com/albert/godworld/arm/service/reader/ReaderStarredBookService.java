package com.albert.godworld.arm.service.reader;

import com.albert.godworld.arm.domain.reader.ReaderStarredBook;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReaderStarredBookService extends IService<ReaderStarredBook> {

    Page<ReaderStarredBook> pageOf(Page<ReaderStarredBook> page,Long readerId);
}
