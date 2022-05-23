package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.social.SocialRequestService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.social.SocialRequestVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("social_request")
@AllArgsConstructor
public class SocialRequestController {

    private final SocialRequestService socialRequestService;
    private final AuthorService authorService;
    private final PrincipalConvert principalConvert;

    @GetMapping("/page/{socialId}")
    public Page<SocialRequestVo> pageOf(@PathVariable("socialId") Long socialId, Page<SocialRequestVo> page) {
        return socialRequestService.page(page, socialId);
    }

    @PutMapping("/confirm/{requestId}/{adminMemId}")
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public RV<Boolean> confirm(@RequestParam("adminMemId") Long adminMemId, Long requestId, Principal principal) {
        boolean result=socialRequestService.isSocialAdmin(adminMemId,principal);
        if(!result)return RVError.AUTHOR_USER_NOT_SAME.to();

        result = socialRequestService.requestConfirm(requestId, adminMemId);
        if (result) {
            return RV.success();
        } else {
            return RVError.SOCIAL_REQUEST_HANDLE_FAIL.to();
        }
    }

    @PutMapping("/refuse/{requestId}/{adminMemId}")
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public RV<Boolean> refuse(@RequestParam("adminMemId") Long adminMemId, Long requestId, Principal principal) {
        boolean result=socialRequestService.isSocialAdmin(adminMemId,principal);
        if(!result)return RVError.AUTHOR_USER_NOT_SAME.to();

        result = socialRequestService.requestRefuse(requestId, adminMemId);
        if (result) {
            return RV.success();
        } else {
            return RVError.SOCIAL_REQUEST_HANDLE_FAIL.to();
        }
    }

}
