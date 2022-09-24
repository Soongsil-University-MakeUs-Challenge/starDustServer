package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.PostLoginRes;
import ssumc.stardust.src.domain.PostSignUpReq;
import ssumc.stardust.src.domain.PostSignUpRes;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.repository.UserRepository;
import ssumc.stardust.utils.JwtService;

import static ssumc.stardust.config.BaseResponseStatus.DATABASE_ERROR;
import static ssumc.stardust.config.BaseResponseStatus.INVALID_USER_JWT;

import static ssumc.stardust.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * 타이머 시작
     */
    public String startTimer(int userId) throws BaseException {
        if (userRepository.checkUserRole(userId) == 0) { // 유저 역할이 USER인 경우만 타이머 시작
            throw new BaseException(INVALID_USER_JWT);
        }
        try {
            if (userRepository.insertStartTime(userId) == 0) {
                throw new BaseException(DATABASE_ERROR);
            }
            return "카운드가 시작되었습니다.";
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 회원가입
     */
    @Transactional(rollbackFor = Exception.class)
    public PostSignUpRes createUser(PostSignUpReq postSignupReq) throws BaseException {
//        //닉네임 중복검사
//        if(userRepository.checkNickname(postSignupReq.getNickname()) == 1){
//            throw new BaseException(DUPLICATED_NICKNAME);
//        }
        try{
            int userId = userRepository.createUser(postSignupReq);
            String userJwt = jwtService.createJwt(userId);
            return new PostSignUpRes(userId, userJwt);
        } catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    //자동 로그인
//    public PostLoginRes autoLogIn(int userId, String jwt) throws BaseException{
//        try{
//            User user = userRepository.getUser(userId);
//            return new PostLoginRes(user.getUserId(), jwt, user.getProfileUrl());
//        } catch (Exception e) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
}