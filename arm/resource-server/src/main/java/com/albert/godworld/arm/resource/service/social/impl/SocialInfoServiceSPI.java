package com.albert.godworld.arm.resource.service.social.impl;

import com.albert.godworld.arm.resource.domain.author.AuthorInfo;
import com.albert.godworld.arm.resource.domain.social.SocialInfo;
import com.albert.godworld.arm.resource.domain.social.SocialMember;
import com.albert.godworld.arm.resource.domain.social.SocialMemberType;
import com.albert.godworld.arm.resource.domain.user.UGroups;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.mapper.social.SocialInfoMapper;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.social.SocialInfoService;
import com.albert.godworld.arm.resource.service.social.SocialMemberService;
import com.albert.godworld.arm.resource.service.user.UGroupService;
import com.albert.godworld.arm.resource.vo.social.SocialInfoVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewActVo;
import com.albert.godworld.arm.resource.vo.social.SocialNewAnnVo;
import com.albert.godworld.arm.resource.vo.social.SocialReVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocialInfoServiceSPI extends ServiceImpl<SocialInfoMapper, SocialInfo>
    implements SocialInfoService {

    private final UGroupService uGroupService;
    private final AuthorService authorService;
    private final SocialMemberService memberService;

    @Override
    public SocialInfo getByMaster(Long masterId) {
        LambdaQueryWrapper<SocialInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialInfo::getMasterId,masterId).last("limit 1");
        return super.getOne(queryWrapper);
    }

    @Override
    public SocialInfo getByAuthor(Long authorId) {
        return baseMapper.byAuthorId(authorId);
    }

    @Override
    public Page<SocialNewActVo> newActPage(Page<SocialNewActVo> page) {
        return super.baseMapper.newActPage(page);
    }

    @Override
    public Page<SocialNewAnnVo> newAnnPage(Page<SocialNewAnnVo> page) {
        return super.baseMapper.newAnnPage(page);
    }

    @Override
    public Page<SocialReVo> query(Page<SocialReVo> page, String name) {
        return super.baseMapper.query(page,name);
    }

    @Override
    public SocialInfoVo getInfoById(Long id) {
        return super.baseMapper.getById(id);
    }

    private boolean checkMasterIdAvail(Long masterId){
        LambdaQueryWrapper<SocialMember> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialMember::getAuthorId,masterId).last("limit 1");
        SocialMember member=memberService.getOne(queryWrapper);
        return member==null;
    }

    private boolean checkNameAvail(String name){
        LambdaQueryWrapper<SocialInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialInfo::getName,name).last("limit 1");
        SocialInfo info=super.getOne(queryWrapper);
        return info==null;
    }


    @Override
    public RV<SocialInfo> register(SocialInfo social) {

        //Check Master
        //如果已经是某个社团的成员就无法申请
        //Check Social Name
        //
        //Add Social Info
        //Add Member Info
        //Add Social Admin Group To User

        if(!checkMasterIdAvail(social.getMasterId()))return RVError.SOCIAL_ALREADY_HAS.to();
        if(!checkNameAvail(social.getName()))return RVError.SOCIAL_NAME_NOT_AVAIL.to();

        if(!super.save(social))return RVError.DATABASE_ERROR.to();

        AuthorInfo info=authorService.getById(social.getMasterId());
        if(info==null){
            return RVError.USER_HAS_NO_PERMISSION.to();
        }

        SocialMember member=new SocialMember();
        member.setMemberName(info.getName());
        member.setSocialId(social.getId());
        member.setAuthorId(social.getMasterId());
        member.setType(SocialMemberType.MASTER);
        memberService.save(member);

        uGroupService.addToUser(info.getUserId(), UGroups.SOCIAL_ADMIN.getCode());
        return RV.success(social);
    }
}
