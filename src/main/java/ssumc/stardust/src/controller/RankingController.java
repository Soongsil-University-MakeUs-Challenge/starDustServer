package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.BaseResponse;
import ssumc.stardust.src.domain.GetRankRes;
import ssumc.stardust.src.service.RankingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
@Transactional(readOnly = true)
@Slf4j
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/{univCode}")
    public BaseResponse<GetRankRes> rank(@PathVariable("univCode") String univCode, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        try{
            GetRankRes rankerList = rankingService.getRanking(univCode, page, size);
            return new BaseResponse<>(rankerList);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
