package com.albert.godworld.arm.resource.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookQueryDTO {

    private String board;
    //tags: [1,2,3,4,56,6]
    private List<Long> tags;
    private Integer year;
    private Integer month;
}
