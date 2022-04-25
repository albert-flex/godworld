package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookVolume;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookVolumeService extends IService<BookVolume> {
    //publish / edit / remove Book, Part, Volume, Chapter.
    BookVolume publish(BookVolume volume);
    boolean edit(BookVolume volume);
    boolean removeVolume(Long id);

    Page<BookVolume> pageOf(Page<BookVolume> page, Long partId);
}
