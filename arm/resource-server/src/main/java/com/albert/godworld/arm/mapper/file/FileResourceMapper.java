package com.albert.godworld.arm.mapper.file;

import com.albert.godworld.arm.domain.file.FileResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileResourceMapper extends BaseMapper<FileResource> {

    @Select("select id,name,format,lib,own_id\n" +
            "from file_resource\n" +
            "where lib=#{lib} and own_id=#{ownId}")
    FileResource getByLibAndOwnOne(@Param("lib") String lib, @Param("ownId") Long ownId);
}
