package com.albert.godworld.arm.controller.point;

import com.albert.godworld.arm.domain.point.PointScore;
import com.albert.godworld.arm.service.point.PointScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point_score")
public class PointScoreController {

    private final PointScoreService pointScoreService;

    @Autowired
    public PointScoreController(PointScoreService pointScoreService) {
        this.pointScoreService = pointScoreService;
    }

    @GetMapping("/score_of/{userId}")
    public PointScore scoreOf(@PathVariable("userId") Long userId){
        return pointScoreService.scoreOf(userId);
    }

    @GetMapping("/check/{userId}/{outCome}")
    boolean checkBalance( @PathVariable("userId") Long userId,
                          @PathVariable("outCome") Long outCome){
        return pointScoreService.checkBalance(userId,outCome);
    }
}
