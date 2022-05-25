package com.albert.godworld.arm.resource.controller.social;

import com.albert.godworld.arm.resource.domain.social.SocialRequest;
import com.albert.godworld.arm.resource.domain.user.User;
import com.albert.godworld.arm.resource.dto.RV;
import com.albert.godworld.arm.resource.dto.RVError;
import com.albert.godworld.arm.resource.service.author.AuthorService;
import com.albert.godworld.arm.resource.service.other.RequestCall;
import com.albert.godworld.arm.resource.service.social.SocialRequestHandlerFactory;
import com.albert.godworld.arm.resource.service.social.SocialRequestService;
import com.albert.godworld.arm.resource.util.PrincipalConvert;
import com.albert.godworld.arm.resource.vo.social.SocialRequestVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("social_request")
@AllArgsConstructor
public class SocialRequestController {

    private final SocialRequestService socialRequestService;
    private final SocialRequestHandlerFactory factory;
    private final RequestCall requestCall;

    @PostMapping
    @PreAuthorize("hasAuthority('AUTHOR_PER')")
    public SocialRequest post(SocialRequest request){
        socialRequestService.save(request);
        return request;
    }

    @GetMapping("/page/{socialId}")
    public Page<SocialRequestVo> pageOf(@PathVariable("socialId") Long socialId, Page<SocialRequestVo> page) {
        return socialRequestService.page(page, socialId);
    }

    @PutMapping("/confirm/{requestId}/{adminMemId}")
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public RV<Boolean> confirm(@PathVariable("adminMemId") Long adminMemId,@PathVariable("requestId") Long requestId, Principal principal) {
        boolean result=socialRequestService.isSocialAdmin(adminMemId,principal);
        if(!result)return requestCall.error().to();

        result = factory.requestConfirm(requestId, adminMemId);
        if (result) {
            return RV.success();
        } else {
            return requestCall.error().to();
        }
    }

    @PutMapping("/refuse/{requestId}/{adminMemId}")
    @PreAuthorize("hasAuthority('SOCIAL_ADMIN_PER')")
    public RV<Boolean> refuse(@PathVariable("adminMemId") Long adminMemId,@PathVariable("requestId") Long requestId, Principal principal) {
        boolean result=socialRequestService.isSocialAdmin(adminMemId,principal);
        if(!result)return requestCall.error().to();

        result = factory.requestRefuse(requestId, adminMemId);
        if (result) {
            return RV.success();
        } else {
            return requestCall.error().to();
        }
    }

}
