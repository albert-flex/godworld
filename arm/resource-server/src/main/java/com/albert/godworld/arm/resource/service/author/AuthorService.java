package com.albert.godworld.arm.resource.service.author;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.vo.author.AuthorNewestVo;
import com.albert.godworld.arm.resource.vo.author.AuthorUpdatedVo;
import com.albert.godworld.arm.resource.vo.author.AuthorVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;


public interface AuthorService extends IService<AuthorInfo> {

    Long getAuthorIdByUserId(Long userId);
    boolean registerAuthor(AuthorInfo info);
    boolean checkUserIdAndAuthorCorrect(Long userId,Long authorId);
    Page<AuthorUpdatedVo> queryByName(Page<AuthorUpdatedVo> page,String name);
    Page<AuthorUpdatedVo> queryBySocial(Page<AuthorUpdatedVo> page,String social);

    Page<AuthorNewestVo> newestAuthors(Page<AuthorNewestVo> page,Integer day);
    Page<AuthorUpdatedVo> updateAuthors(Page<AuthorUpdatedVo> page,Integer day);

    boolean checkAuthorNameAvailable(String name);

    AuthorVo getAuthorVo(Long id);
}
