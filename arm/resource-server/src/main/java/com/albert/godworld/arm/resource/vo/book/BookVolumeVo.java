package com.albert.godworld.arm.resource.vo.book;

import lombok.Data;

import java.util.*;

@Data
public class BookVolumeVo {

    private Long id;
    private String title;
    private List<BookChapterVo> chapters;

    private BookVolumeVo(){}

    public static List<BookVolumeVo> parse(List<BookChapterVo> chapters){
        Map<Long,BookVolumeVo> volumeVoMap=new HashMap<>();
        for (BookChapterVo chapter : chapters) {
            BookVolumeVo volumeVo;
            if (!volumeVoMap.containsKey(chapter.getVolumeId())) {
                volumeVo = new BookVolumeVo();
                volumeVo.setId(chapter.getVolumeId());
                volumeVo.setTitle(chapter.getVolumeTitle());
                volumeVo.setChapters(new ArrayList<>());
                volumeVoMap.put(volumeVo.getId(), volumeVo);
            } else {
                volumeVo = volumeVoMap.get(chapter.getVolumeId());
            }
            volumeVo.getChapters().add(chapter);
        }
        List<BookVolumeVo> volumeVos=new ArrayList<>();
        for(Long key: volumeVoMap.keySet()){
            volumeVos.add(volumeVoMap.get(key));
        }
        return volumeVos;
    }
}
