package com.albert.godworld.arm.service.file.impl;

import com.albert.godworld.arm.domain.file.FileResource;
import com.albert.godworld.arm.mapper.file.FileResourceMapper;
import com.albert.godworld.arm.service.file.FileResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileResourceServiceSPI extends ServiceImpl<FileResourceMapper, FileResource>
    implements FileResourceService {

    @Override
    public FileResource getByLibAndOwnId(String lib, Long ownId) {
        return super.baseMapper.getByLibAndOwnOne(lib,ownId);
    }
}
