package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookTag;
import com.albert.godworld.arm.domain.book.BookTagBind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookTagBindService extends IService<BookTagBind> {

    //publish bind book tags.
    boolean bind(List<BookTag> tags, Long bookId);

    List<BookTag> tagBindsOf(Long bookId);
}
