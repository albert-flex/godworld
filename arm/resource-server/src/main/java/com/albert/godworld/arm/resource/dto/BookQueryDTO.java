package com.albert.godworld.arm.resource.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookQueryDTO {

    private String board;
    private List<String> tags;
    private Integer year;
    private Integer month;
}
