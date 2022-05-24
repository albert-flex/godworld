package com.albert.godworld.arm.resource.vo.social;

import com.albert.godworld.arm.resource.vo.book.SocialActivityBookVo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class ActivityVo {

    @Data
    @AllArgsConstructor
    public static class BookV{
        private Long bookId;
        private String name;
        private Long authorId;
        private String authorName;
        private String bookDesc;
    }

    private Long id;
    private String name;
    private String description;
    private Boolean on;
    private List<BookV> books=new ArrayList<>();

    private ActivityVo(){}

    public static Map<String,ActivityVo> parse(List<SocialActivityBookVo> list){
        if(list==null||list.isEmpty())return new HashMap<>();

        Map<String,ActivityVo> map=new HashMap<>();
        for(SocialActivityBookVo v: list){
            if(map.containsKey(v.getActivityName())){
                ActivityVo av=map.get(v.getActivityName());
                av.books.add(new BookV(v.getBookId(),v.getBookName(),v.getAuthorId(),v.getAuthorName(),v.getDescription()));
            }else{
                ActivityVo av=new ActivityVo();
                av.id=v.getActivityId();
                av.on=v.getActOn();
                av.name=v.getActivityName();
                av.description=v.getActivityDesc();
                av.books.add(new BookV(v.getBookId(),v.getBookName(),v.getAuthorId(),v.getAuthorName(),v.getDescription()));
                map.put(av.name,av);
            }
        }
        return map;
    }
}
