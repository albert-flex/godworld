package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.mapper.book.BookInfoMapper;
import com.albert.godworld.arm.resource.mapper.book.BookTagBindMapper;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookInfoServiceSPI extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService {

    private final AuthorService authorService;

    private final BookTagBindMapper bookTagBindMapper;

    @Override
    public Page<BookVo> pageOfAuthor(Page<BookVo> page, String authorName) {
        return super.baseMapper.queryByAuthorName(page,authorName);
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
    public BookVo getById(Long id) {
        return super.baseMapper.getById(id);
    }

    @Override
    public Page<BookVo> query(Page<BookVo> page, Integer year, Integer month, List<String> tags, String board) {
        return super.baseMapper.query(page,tags,board,year,month);
    }
}
