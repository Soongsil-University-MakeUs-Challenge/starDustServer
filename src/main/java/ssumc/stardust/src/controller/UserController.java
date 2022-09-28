package ssumc.stardust.src.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.BaseResponse;
import ssumc.stardust.config.BaseResponseStatus;
import ssumc.stardust.src.domain.PostLoginReq;
import ssumc.stardust.src.domain.PostLoginRes;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssumc.stardust.src.service.UserService;
import ssumc.stardust.utils.JwtService;

import javax.validation.Valid;

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

            int userIdByJwt = jwtService.getUserId();
            return new BaseResponse<>(userService.startTimer(userIdByJwt));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    /**
     * 회원가입 및 로그인 API
     */
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> createUser(@Valid @RequestBody PostLoginReq postLoginReq, BindingResult br){
        if(br.hasErrors()){
            String error = br.getAllErrors().get(0).getDefaultMessage();
            return new BaseResponse<>(BaseResponseStatus.of(error));
        }
        try{
            PostLoginRes postLoginRes = userService.createUser(postLoginReq);
            return new BaseResponse<>(postLoginRes);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}