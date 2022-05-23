package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookTagService extends IService<BookTag>{
    void changeBookTag(Long bookId,BookTag[] tags);
    List<Long> tagIdListOf(List<String> names);
    List<Long> idOfBookOnTagBinds(List<String> names,Long bookId);
    void reAttachTags(Long bookId,List<Long> tags);
    void addTags(List<Long> tags,Long bookId);
}
