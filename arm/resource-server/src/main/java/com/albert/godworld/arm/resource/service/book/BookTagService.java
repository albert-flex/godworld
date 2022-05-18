package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookTagService extends IService<BookTag>{
    void changeBookTag(Long bookId,BookTag[] tags);
}
