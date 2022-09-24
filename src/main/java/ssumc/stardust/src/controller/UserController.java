package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.BaseResponse;

import ssumc.stardust.src.service.UserService;
import ssumc.stardust.utils.JwtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    /**
     * 타이머 시작 API
     *
     * @return String
     */
    @PostMapping("/timer")
    @Transactional
    public BaseResponse<String> startGame() {

        try {
            //int userIdByJwt = jwtService.getUserId();
            int userIdByJwt = 6;
            return new BaseResponse<>(userService.startTimer(userIdByJwt));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
