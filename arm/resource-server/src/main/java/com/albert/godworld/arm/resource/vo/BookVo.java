package com.albert.godworld.arm.resource.vo;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
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

    public void parse(BookInfo info){
        this.id=info.getId();
        this.name=info.getName();
        this.description=info.getDescription();
    }

    public BookVo prepareTags(){
        if(tagWords!=null && tagWords.isEmpty()){
            this.tags= tagWords.split(",");
            for(int i=0;i!=tags.length;++i){
                tags[i]=tags[i].trim();
            }
        }
        return this;
    }
}
