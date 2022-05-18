package com.albert.godworld.arm.resource.vo;

import lombok.Data;

@Data
public class BookVo {
    private Long id;
    private String author;
    private String name;
    private String tagWords;//1,2,3,4,5,6,7
    private String[] tags;
    private String boardName;
    private String description;
    private Long wordCount;

    public String[] getTags() {
        if (tags == null) {
            prepareTags();
        }
        return tags;
    }

    public String getTagWords() {
        if (tagWords != null && !tagWords.isEmpty() && tags != null && tags.length > 0) {
            tagWords = "";
            StringBuilder builder = new StringBuilder();
            for (String tag : tags) {
                builder.append(tag).append(",");
            }
            tagWords = builder.toString();
        }
        return tagWords;
    }

    private void prepareTags() {
        if (tagWords != null && !tagWords.isEmpty()) {
            this.tags = tagWords.split(",");
            for (int i = 0; i != tags.length; ++i) {
                tags[i] = tags[i].trim();
            }
        }
    }
}
