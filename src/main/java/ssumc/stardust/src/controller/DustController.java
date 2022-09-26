package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.BaseResponse;
import ssumc.stardust.config.BaseResponseStatus;
import ssumc.stardust.src.domain.DustLocationDto;
import ssumc.stardust.src.service.DustService;
import ssumc.stardust.utils.JwtService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dust")
@Slf4j
public class DustController {

    private final DustService dustService;
    private final JwtService jwtService;

    /**
     * 먼지 위치 정보 업데이트 API
     *
     * @param dustLocationDto
     * @param br
     * @return BaseResponse
     * @throws BaseException
     */

    @PostMapping("/location")
    @Transactional
    public BaseResponse<String> setLocation(@Valid @RequestBody DustLocationDto dustLocationDto, BindingResult br) {

        if (br.hasErrors()) {
            String error = br.getAllErrors().get(0).getDefaultMessage();
            return new BaseResponse<>(BaseResponseStatus.of(error));
        }

        try {
//            int userIdByJwt = jwtService.getUserId();
            int userIdByJwt = 6;
            String resultStr = dustService.setLocation(userIdByJwt, dustLocationDto);

            return new BaseResponse<>(resultStr);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
