package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.PostLoginRes;
import ssumc.stardust.src.domain.PostSignUpReq;
import ssumc.stardust.src.domain.PostSignUpRes;
import ssumc.stardust.src.repository.UserRepository;
import ssumc.stardust.utils.JwtService;

import static ssumc.stardust.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * 회원가입
     */
//    @Transactional(rollbackFor = Exception.class)
    public PostSignUpRes createUser(PostSignUpReq postSignupReq) throws BaseException {
//        //닉네임 중복검사
//        if(userRepository.checkNickname(postSignupReq.getNickname()) == 1){
//            throw new BaseException(DUPLICATED_NICKNAME);
//        }
        try{
            int userId = userRepository.createUser(postSignupReq);
            String userJwt = jwtService.createJwt(userId);
            PostSignUpRes response = new PostSignUpRes();
            response.setUserId(userId);
            response.setUserJwt(userJwt);
            return response;
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
