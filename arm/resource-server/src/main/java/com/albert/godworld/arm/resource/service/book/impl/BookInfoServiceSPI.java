package com.albert.godworld.arm.resource.service.book.impl;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.book.BookInfo;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.mapper.book.BookInfoMapper;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.book.BookInfoService;
import com.albert.godworld.arm.resource.service.user.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookInfoServiceSPI extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService {

    private final AuthorService authorService;

    @Override
    public Page<BookInfo> pageOfAuthor(Page<BookInfo> page, String authorName) {
        LambdaQueryWrapper<AuthorInfo> authQ=new LambdaQueryWrapper<>();
        authQ.eq(AuthorInfo::getName,authorName);
        AuthorInfo authorInfo=authorService.getOne(authQ);
        if(authorInfo==null)return page;

        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookInfo::getAuthorId,authorInfo.getId());
        return super.page(page,queryWrapper);
    }

    @Override
    public List<BookInfo> OfPointBoard(Long boardId) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        if(boardId!=null){
            queryWrapper.orderByDesc(BookInfo::getPoint);
        }
        queryWrapper.eq(BookInfo::getBoardId,boardId);
        Page<BookInfo> page=new Page<>();
        page.setSize(20);
        page.setCurrent(1);

        page=super.page(page,queryWrapper);
        return page.getRecords();
    }

    @Override
    public List<BookInfo> OfPoint() {
        return OfPointBoard(null);
    }

    @Override
    public Page<BookInfo> pageOfBoard(Page<BookInfo> page, String boardName) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookInfo::getBoardId,boardName);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookInfo> pageOfUpdate(Page<BookInfo> page) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(BookInfo::getUpdateTime);
        return super.page(page,queryWrapper);
    }

    @Override
    public Page<BookInfo> queryByName(Page<BookInfo> page, String bookName) {
        LambdaQueryWrapper<BookInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(BookInfo::getName,bookName);
        return super.page(page,queryWrapper);
    }
}
