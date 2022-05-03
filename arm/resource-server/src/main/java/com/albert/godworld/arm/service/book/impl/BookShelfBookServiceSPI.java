package com.albert.godworld.arm.service.book.impl;

import com.albert.godworld.arm.domain.book.BookInfo;
import com.albert.godworld.arm.domain.book.BookShelfBook;
import com.albert.godworld.arm.mapper.book.BookShelfBookMapper;
import com.albert.godworld.arm.service.book.BookInfoService;
import com.albert.godworld.arm.service.book.BookShelfBookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookShelfBookServiceSPI extends ServiceImpl<BookShelfBookMapper, BookShelfBook>
    implements BookShelfBookService {

    private final BookInfoService bookInfoService;

    @Autowired
    public BookShelfBookServiceSPI(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @Override
    public boolean addBook(Long userId, Long bookId) {
        removeBook(userId,bookId);

        BookShelfBook book=new BookShelfBook();
        book.setBookId(bookId);
        book.setUserId(userId);
        return super.save(book);
    }

    @Override
    public boolean removeBook(Long userId, Long bookId) {
        LambdaQueryWrapper<BookShelfBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookShelfBook::getUserId,userId);
        queryWrapper.eq(BookShelfBook::getBookId,bookId);
        return super.remove(queryWrapper);
    }

    @Override
    public long countInShelf(Long bookId) {
        LambdaQueryWrapper<BookShelfBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookShelfBook::getBookId,bookId);
        return super.count(queryWrapper);
    }

    @Override
    public Page<BookInfo> shelfOfUser(Page<BookInfo> page, Long userId) {
        LambdaQueryWrapper<BookShelfBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(BookShelfBook::getUserId,userId);
        Page<BookShelfBook> bookPage=new Page<>();
        bookPage.setSize(page.getSize());
        bookPage.setCurrent(page.getCurrent());
        bookPage.setOrders(page.orders());
        bookPage=super.page(bookPage,queryWrapper);

        List<Long> ids=new LinkedList<>();
        for(int i=0;i!=bookPage.getRecords().size();++i){
            ids.add(bookPage.getRecords().get(0).getBookId());
        }

        List<BookInfo> result=bookInfoService.listByIds(ids);
        page.setTotal(bookPage.getTotal());
        page.setPages(bookPage.getPages());
        page.setRecords(result);
        return page;
    }
}
