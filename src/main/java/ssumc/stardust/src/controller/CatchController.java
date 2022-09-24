package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.BaseResponse;
import ssumc.stardust.src.domain.CatchDto;
import ssumc.stardust.src.domain.GetMapRes;
import ssumc.stardust.src.service.CatchService;
import ssumc.stardust.src.service.DustService;
import ssumc.stardust.utils.JwtService;

import javax.validation.Valid;

import static ssumc.stardust.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catch")
@Slf4j
public class CatchController {

    private final CatchService catchService;
    private final JwtService jwtService;

    /**
     * 먼지 잡기 API
     * @param dustNum
     * @return BaseResponse
     * @throws BaseException
     */

    @PostMapping("/dust")
    public BaseResponse<String> createCatch(@Valid @RequestBody CatchDto dustNum){

        //dustNum 없는 경우 처리 벨리데이션
        if(dustNum.getDustNum()==0){
            return new BaseResponse<>(EMPTY_DUSTNUM);
        }

        //유저 역할이 USER인 경우만 먼지 잡는 걸로 ???
//        if (userRepository.checkUserRole(userId) == 0) { // 유저 역할이 USER인 경우만 타이머 시작
//            throw new BaseException(INVALID_USER_JWT);
//        }

        try {
            //int userIdByJwt = jwtService.getUserId();
            int userIdByJwt = 1;
            String resultStr = catchService.setCatch(userIdByJwt, dustNum);

            //userId로 dust 몇 개인지 체크해야함
            if(catchService.countCatch(userIdByJwt, dustNum) == 5){
                //catchService.setEndTime(userIdByJwt)

            }
            return new BaseResponse<>(resultStr);

        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

    }

}
