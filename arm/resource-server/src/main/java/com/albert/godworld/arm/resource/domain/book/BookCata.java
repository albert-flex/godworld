package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

@Data
public class BookCata {

    private Long id;
    private Long parentId = 0L;
    private Long bookId;
    private String name;
}
