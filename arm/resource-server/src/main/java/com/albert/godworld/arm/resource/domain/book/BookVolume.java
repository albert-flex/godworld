package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

@Data
public class BookVolume {

    private Long id;
    private Long bookId;
    private Long prevVolumeId;
    private Long nextVolumeId;
    private String name;
}
