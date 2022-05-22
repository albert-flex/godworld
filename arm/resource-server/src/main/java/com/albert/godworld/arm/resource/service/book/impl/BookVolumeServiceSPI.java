package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookVolume;
import com.albert.godworld.arm.resource.mapper.book.BookVolumeMapper;
import com.albert.godworld.arm.resource.service.book.BookVolumeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookVolumeServiceSPI extends ServiceImpl<BookVolumeMapper, BookVolume>
    implements BookVolumeService {

    @Override
    public List<BookVolume> volumeOf(Long bookId) {
        LambdaQueryWrapper<BookVolume> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookVolume::getBookId,bookId);
        return super.list(queryWrapper);
    }

    @Override
    public boolean create(Long bookId, String name, Long prevVolumeId) {
        BookVolume bookVolume=new BookVolume();
        bookVolume.setPrevVolumeId(0L);
        bookVolume.setNextVolumeId(0L);
        bookVolume.setBookId(bookId);
        bookVolume.setName(name);
        if(!super.save(bookVolume))return false;

        if(prevVolumeId==null||prevVolumeId==0)return true;

        return change(bookVolume.getId(),prevVolumeId);
    }

    @Override
    public boolean change(Long volumeId, Long prevVolumeId) {

        BookVolume bookVolume=super.getById(volumeId);
        BookVolume preV=super.getById(prevVolumeId);
        if(bookVolume==null||preV==null)return false;

        if(preV.getNextVolumeId()!=null&&preV.getNextVolumeId()!=0){
            BookVolume next=super.getById(preV.getNextVolumeId());
            //prev->next=p
            preV.setNextVolumeId(bookVolume.getId());
            //p->prev=pre
            bookVolume.setPrevVolumeId(prevVolumeId);
            //p->next=next
            bookVolume.setNextVolumeId(next.getId());
            //next->prev=p
            next.setPrevVolumeId(bookVolume.getId());
            return super.updateBatchById(Arrays.asList(bookVolume,next,preV));
        }else {
            //p->pre=pre
            bookVolume.setPrevVolumeId(prevVolumeId);
            //pre->next=p
            preV.setNextVolumeId(bookVolume.getId());
            return super.updateBatchById(Arrays.asList(bookVolume,preV));
        }
    }
}
