package com.albert.godworld.arm.resource.service.social.request;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.social.SocialRequestType;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.RequestHandler;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddRequestHandler implements RequestHandler {

    private final SocialMemberService socialMemberService;
    private final AuthorService authorService;
    private final RequestCall requestCall;

    @Override
    public SocialRequestType type() {
        return SocialRequestType.ADD_TO_SOCIAL;
    }

    /**
     * 先查看是否是成员，如果不是，则可以加入.
     *
     * @param request
     * @param adminMemberId
     * @return
     */
    @Override
    public boolean okHandle(SocialRequest request, Long adminMemberId) {
        LambdaQueryWrapper<SocialMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId, request.getAuthorId()).last("limit 1");
        SocialMember member = socialMemberService.getOne(queryWrapper);

        if (member != null) {
            requestCall.setError(RVError.SOCIAL_ALREADY_HAS);
            return false;
        }

        AuthorInfo authorInfo = authorService.getById(request.getAuthorId());
        if (authorInfo == null) {
            requestCall.setError(RVError.AUTHOR_NOT_FOUND);
            return false;
        }

        member = new SocialMember();
        member.setType(SocialMemberType.NORMAL);
        member.setAuthorId(request.getAuthorId());
        member.setSocialId(request.getSocialId());
        member.setMemberName(authorInfo.getName());
        if (!socialMemberService.save(member)) {
            requestCall.setError(RVError.DATABASE_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public boolean refuseHandle(SocialRequest request, Long adminMemberId) {
        return false;
    }

}
