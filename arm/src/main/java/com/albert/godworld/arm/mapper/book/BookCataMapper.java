package com.albert.godworld.arm.mapper.book;

import com.albert.godworld.arm.domain.book.BookCata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookCataMapper extends BaseMapper<BookCata> {
}
