package com.albert.godworld.arm.resource.domain.book;

import lombok.Data;

@Data
public class BookVolume {

    private Long id;
    private Long bookId;
    private Long prev_volume_id;
    private Long next_volume_id;
    private String name;
}
