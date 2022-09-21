package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.BaseResponse;
import ssumc.stardust.src.domain.GetMapRes;
import ssumc.stardust.src.service.MapService;
import ssumc.stardust.utils.JwtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
@Transactional(readOnly = true)
@Slf4j
public class MapController {

    private final MapService mapService;
    private final JwtService jwtService;


    /**
     * 먼지들 조회 API
     * @return GetMapRes
     */
    @GetMapping("/{university}")
    public BaseResponse<GetMapRes> find(@PathVariable("university") String university) {

        try {
            int userIdByJwt = jwtService.getUserId();
            GetMapRes getMapRes = mapService.getDustInfo(userIdByJwt, university);
            return new BaseResponse<>(getMapRes);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
