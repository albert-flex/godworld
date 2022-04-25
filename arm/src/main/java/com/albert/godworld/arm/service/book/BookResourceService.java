package com.albert.godworld.arm.service.book;

import com.albert.godworld.arm.domain.book.BookResource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BookResourceService extends IService<BookResource> {

    //publish / remove book resource.
    BookResource publish(BookResource resource);
    boolean removeResource(Long resourceId);

    Page<BookResource> getBookResource(Page<BookResource> bookResource,Long chapterId);
}
