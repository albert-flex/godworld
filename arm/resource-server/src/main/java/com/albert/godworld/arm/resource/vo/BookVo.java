package com.albert.godworld.arm.resource.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BookVo {
    private Long id;
    private String author;
    private String name;
    private String tagWords;//1,2,3,4,5,6,7
    private String[] tags;
    private String boardName;
    private String description;
    private String updateChapter;
    private Date updateTime;
    private Long wordCount;

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

    public static String createTagWords(String[] tags){
        if(tags.length==0)return "";

        StringBuilder builder = new StringBuilder();
        for (String tag : tags) {
            builder.append('\'').append(tag).append('\'').append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}
