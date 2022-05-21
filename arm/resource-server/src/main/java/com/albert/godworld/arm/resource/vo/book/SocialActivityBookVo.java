package com.albert.godworld.arm.resource.vo.book;

import lombok.Data;

import java.util.Date;

@Data
public class SocialActivityBookVo {
    private Long activityId;
    private String activityName;
    private Long bookId;
    private String bookName;
    private Long authorId;
    private String authorName;
    private String tagWords;//1,2,3,4,5,6,7
    private String[] tags;
    private Date updateTime;
    private String description;
    private String activityDesc;
    public String[] getTags() {
        if (tags == null && tagWords != null && !tagWords.isEmpty()) {
            this.tags = tagWords.split(",");
            for (int i = 0; i != tags.length; ++i) {
                tags[i] = tags[i].trim();
            }
        }
        return tags;
    }

    public String createTagWords() {
        if (tagWords == null && tags != null && tags.length > 0) {
            tagWords = "";
            StringBuilder builder = new StringBuilder();
            for (String tag : tags) {
                builder.append(tag).append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            tagWords = builder.toString();
        }
        return tagWords;
    }
}
