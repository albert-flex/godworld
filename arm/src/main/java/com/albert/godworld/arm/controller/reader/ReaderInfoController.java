package com.albert.godworld.arm.controller.reader;

import com.albert.godworld.arm.controller.CurdController;
import com.albert.godworld.arm.domain.reader.ReaderInfo;
import com.albert.godworld.arm.service.reader.ReaderInfoService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reader")
public class ReaderInfoController extends CurdController<ReaderInfo> {

    private final ReaderInfoService readerInfoService;

    @Autowired
    public ReaderInfoController(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    @Override
    protected IService<ReaderInfo> service() {
        return readerInfoService;
    }

    @GetMapping("/find_by_user_id/{userId}")
    public ReaderInfo findReaderByUserId(@PathVariable("userId") Long userId) {
        return readerInfoService.findReaderByUserId(userId);
    }

    //禁言，不能发布评论
    @PutMapping("/notWord/{readerId}")
    public boolean notWord(Long readerId) {
        return readerInfoService.notWord(readerId);
    }

    //是否为禁言
    @GetMapping("/is_not_word/{readerId}")
    public boolean isNotWord(@PathVariable("readerId") Long readerId) {
        return readerInfoService.isNotWord(readerId);
    }

    //解除禁言，可以发布评论
    @PutMapping("/do_word/{readerId}")
    public boolean doWord(@PathVariable("readerId") Long readerId) {
        return readerInfoService.doWord(readerId);
    }
}
