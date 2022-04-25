package com.albert.godworld.arm.service.reader;

import com.albert.godworld.arm.domain.reader.ReaderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReaderInfoService extends IService<ReaderInfo> {

    ReaderInfo findReaderByUserId(Long userId);

    //禁言，不能发布评论
    boolean notWord(Long readerId);

    //是否为禁言
    boolean isNotWord(Long readerId);

    //解除禁言，可以发布评论
    boolean doWord(Long readerId);
}
