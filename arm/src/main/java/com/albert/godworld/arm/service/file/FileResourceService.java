package com.albert.godworld.arm.service.file;

import com.albert.godworld.arm.domain.file.FileResource;
import com.baomidou.mybatisplus.extension.service.IService;

public interface FileResourceService extends IService<FileResource> {

    FileResource getByLibAndOwnId(String lib,Long ownId);
}
