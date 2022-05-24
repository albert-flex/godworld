package com.albert.godworld.arm.resource.domain.file;

import lombok.Data;

/**
 * lib: user-avator, book-pic, social-avator, activity-pic, author-avator
 * ownId: userId, bookId, socialId, activityId, authorId
 */
@Data
public class FileResource {

    private Long id;
    private String name;
    private String format;

    private String lib;
    private Long ownId;
}
