package com.albert.godworld.arm.service.file;

import com.albert.godworld.arm.domain.file.FileResource;
import com.albert.godworld.arm.mapper.file.FileResourceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileResourceServiceSPI extends ServiceImpl<FileResourceMapper, FileResource>
    implements FileResourceService{

}
