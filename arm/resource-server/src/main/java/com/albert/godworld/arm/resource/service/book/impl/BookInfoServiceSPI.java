package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.book.BookTagBind;
import com.albert.godworld.arm.resource.dto.BookDTO;
import com.albert.godworld.arm.resource.mapper.book.BookInfoMapper;
import com.albert.godworld.arm.resource.mapper.book.BookTagBindMapper;
import com.albert.godworld.arm.resource.mapper.book.BookTagMapper;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.service.book.BookTagBindService;
import com.albert.godworld.arm.resource.service.book.BookTagService;
import com.albert.godworld.arm.resource.vo.book.BookVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class BookInfoServiceSPI extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService {

    private final AuthorService authorService;

    private final BookTagBindService bookTagBindService;
    private final BookTagService bookTagService;

    @Override
    @Transactional
    public boolean create(BookDTO bookDTO) {
        //Add BookInfo
        bookDTO.setId(null);
        super.baseMapper.create(bookDTO);
        if(bookDTO.getId()==null)return false;

        //Add TagBind
        bookTagService.reAttachTags(bookDTO.getId(),Arrays.asList(bookDTO.getTags()));
        return true;
    }

    @Override
    public boolean modify(BookDTO bookDTO) {
        LambdaUpdateWrapper<BookInfo> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(BookInfo::getId,bookDTO.getId());
        updateWrapper.set(BookInfo::getDescription,bookDTO.getDescription());
        if(!super.update(updateWrapper)) return  false;

        bookTagService.reAttachTags(bookDTO.getId(),Arrays.asList(bookDTO.getTags()));
        return true;
    }

    @Override
    public Page<BookVo> pageOfAuthor(Page<BookVo> page, String authorName) {
        return super.baseMapper.queryByAuthorName(page,authorName);
    }

    @Override
    public Page<BookVo> pageOfAuthorId(Page<BookVo> page, Long authorId) {
        return super.baseMapper.queryByAuthorId(page,authorId);
    }

    @Override
    public boolean setPresentBook(Long authorId, Long bookId) {
        AuthorInfo authorInfo=authorService.getById(authorId);
        if(authorInfo==null)return false;

        BookInfo info=super.getById(bookId);
        if(!info.getAuthorId().equals(authorId)){
            return false;
        }

        authorInfo.setPresentBookId(bookId);
        return authorService.updateById(authorInfo);
    }

    @Override
    public boolean erasePresentBook(Long authorId) {
        LambdaUpdateWrapper<AuthorInfo> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(AuthorInfo::getId,authorId);
        updateWrapper.set(AuthorInfo::getPresentBookId,0);
        return authorService.update(updateWrapper);
    }

    @Override
    public Page<BookVo> pageOfUpdate(Page<BookVo> page) {
        return super.baseMapper.getUpdatePage(page);
    }

    @Override
    public List<BookVo> OfPointBoard(String board) {
        return super.baseMapper.queryOfPointAtBoard(board);
    }

    @Override
    public List<BookVo> OfPoint() {
        return super.baseMapper.queryOfPoint();
    }

    @Override
    public Page<BookVo> queryByName(Page<BookVo> page, String bookName) {
        return super.baseMapper.queryByName(page,bookName);
    }

    @Override
    public Page<BookVo> queryByBoard(Page<BookVo> page, String board) {
        return super.baseMapper.queryByBoard(page,board);
    }

    @Override
    public Page<BookVo> queryByTags(Page<BookVo> page, List<String> tag) {
        return super.baseMapper.queryByTags(page,tag);
    }

    @Override
    public BookVo getOnId(Long id) {
        return super.baseMapper.getById(id);
    }

    @Override
    public Page<BookVo> query(Page<BookVo> page, Integer year, Integer month, List<String> tags, String board) {
        return super.baseMapper.query(page,tags,board,year,month);
    }
}
