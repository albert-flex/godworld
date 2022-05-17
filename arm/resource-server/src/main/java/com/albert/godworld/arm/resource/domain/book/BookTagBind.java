package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

@Data
public class BookTagBind {
    private Long id;
    private Long bookId;
    private Long tagId;
}
