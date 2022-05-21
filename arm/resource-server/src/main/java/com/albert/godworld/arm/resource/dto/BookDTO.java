package com.albert.godworld.arm.resource.dto;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String name;
    private Long authorId;
    private String boardName;
    private String[] tags;
    private String description;
}
