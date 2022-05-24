package com.albert.godworld.arm.resource.service.file.impl;

import com.albert.godworld.arm.resource.domain.file.FileResource;
import com.albert.godworld.arm.resource.mapper.file.FileResourceMapper;
import com.albert.godworld.arm.resource.service.file.FileResourceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileResourceServiceSPI extends ServiceImpl<FileResourceMapper, FileResource>
        implements FileResourceService {

    @Override
    public FileResource getByLibAndOwnId(String lib, Long ownId) {
        FileResource resource = super.baseMapper.getByLibAndOwnOne(lib, ownId);
            return resource;
    }

    @Override
    public void clearResource(String lib, Long ownId) {
        LambdaQueryWrapper<FileResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileResource::getLib, lib);
        queryWrapper.eq(FileResource::getOwnId, ownId);
        super.remove(queryWrapper);
    }
}
