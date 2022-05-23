package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookChapter;
import com.albert.godworld.arm.resource.domain.book.BookVolume;
import com.albert.godworld.arm.resource.dto.ChapterDTO;
import com.albert.godworld.arm.resource.mapper.book.BookChapterMapper;
import com.albert.godworld.arm.resource.service.book.BookChapterService;
import com.albert.godworld.arm.resource.vo.book.BookChapterInfoVo;
import com.albert.godworld.arm.resource.vo.book.BookChapterVo;
import com.albert.godworld.arm.resource.vo.book.BookVolumeVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class BookChapterServiceSPI extends ServiceImpl<BookChapterMapper, BookChapter>
    implements BookChapterService {

    @Override
    public Page<BookChapter> chapterOfVolume(Page<BookChapter> page, Long volumeId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getVolumeId, volumeId);
        return super.page(page,queryWrapper);
    }

    @Override
    public boolean modifyChapter(BookChapter bookChapter) {
        LambdaUpdateWrapper<BookChapter> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BookChapter::getId, bookChapter.getId());
        updateWrapper.set(BookChapter::getTitle, bookChapter.getTitle());
        updateWrapper.set(BookChapter::getContent,bookChapter.getContent());
        if (!super.update(updateWrapper)) {
            return false;
        }

        if(bookChapter.getPrevChapterId()==null||bookChapter.getPrevChapterId()==0){
            return true;
        }

        _shift(bookChapter.getId(),bookChapter.getPrevChapterId());
        return true;
    }

    private void _shift(Long chapterId,Long preChapterId){
        BookChapter chapter=super.getById(chapterId);
        BookChapter prevC=super.getById(preChapterId);
        if(chapter==null||prevC==null)return;

        //chapter->prev->next=0
        if(chapter.getPrevChapterId()!=null&&chapter.getPrevChapterId()!=0){
            BookChapter chapterPre=super.getById(chapter.getPrevChapterId());
            chapterPre.setNextChapterId(0L);
            super.updateById(chapterPre);
        }

        if(prevC.getNextChapterId()!=null&&prevC.getNextChapterId()!=0){
            BookChapter next=super.getById(prevC.getNextChapterId());
            //prev->next=p
            prevC.setNextChapterId(chapter.getId());
            //p->prev=pre
            chapter.setPrevChapterId(preChapterId);
            //p->next=next
            chapter.setNextChapterId(next.getId());
            //next->prev=p
            next.setPrevChapterId(chapter.getId());
            super.updateBatchById(Arrays.asList(chapter,next,prevC));
        }else {
            //p->pre=pre
            chapter.setPrevChapterId(preChapterId);
            //pre->next=p
            prevC.setNextChapterId(chapter.getId());
            super.updateBatchById(Arrays.asList(chapter,prevC));
        }
    }

    @Override
    public long countChapter(Long bookId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getBookId,bookId);
        return super.count(queryWrapper);
    }

    @Override
    public BookChapter newestChapter(Long bookId) {
        LambdaQueryWrapper<BookChapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookChapter::getBookId,bookId);
        queryWrapper.orderByDesc(BookChapter::getCreateTime).last("limit 1");
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean insertChapter(ChapterDTO dto) {
        BookChapter chapter=new BookChapter();
        chapter.setBookId(dto.getBookId());
        chapter.setTitle(dto.getTitle());
        chapter.setContent(dto.getContent());
        chapter.setVolumeId(dto.getVolumeId());
        if(!super.save(chapter))return false;

        _shift(chapter.getId(),dto.getPrevChapterId());
        return true;
    }

    @Override
    public List<BookVolumeVo> volumeOfBook(Long bookId) {
        List<BookChapterVo> vos=super.baseMapper.listChaptersOfBook(bookId);
        return BookVolumeVo.parse(vos);
    }

    @Override
    public BookChapterInfoVo getChapter(Long chapterId) {
        return super.baseMapper.getInfoOnChapter(chapterId);
    }
}
