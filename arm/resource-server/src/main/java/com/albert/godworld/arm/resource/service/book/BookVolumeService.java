package com.albert.godworld.arm.resource.service.book;

import com.albert.godworld.arm.resource.domain.book.BookVolume;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BookVolumeService extends IService<BookVolume> {
    List<BookVolume> volumeOf(Long bookId);
    boolean create(Long bookId,String name,Long prevVolumeId);
    boolean change(Long volumeId, Long prevVolumeId);
}
