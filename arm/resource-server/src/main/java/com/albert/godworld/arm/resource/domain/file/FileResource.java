package com.albert.godworld.arm.resource.domain.file;

import lombok.Data;

@Data
public class FileResource {

    private Long id;
    private String name;
    private String format;

    private String lib;
    private Long ownId;
}
