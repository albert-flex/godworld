package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookCata;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookCataService extends IService<BookCata>{

    Page<BookCata> rootCataOfBook(Page<BookCata> page,Long bookId);
    Page<BookCata> cataOfParent(Page<BookCata> page,Long parentId);

    boolean removeCata(Long cataId);
}
