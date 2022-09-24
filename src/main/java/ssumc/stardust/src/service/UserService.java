package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.PostLoginReq;
import ssumc.stardust.src.domain.PostLoginRes;
import ssumc.stardust.src.domain.UserInfoDto;
import ssumc.stardust.src.repository.UserRepository;
import ssumc.stardust.utils.JwtService;

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
        if (userRepository.checkDuplication(userId) == 1) { // 이미 타이머가 시작된 경우 => 데이터가 이미 존재하는 경우
           throw new BaseException(DUPLICATED_DATA);
        }

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
     * 회원가입 및 로그인
     */
    @Transactional(rollbackFor = Exception.class)
    public PostLoginRes createUser(PostLoginReq postSignupReq) throws BaseException {
        boolean isUser = true;

        //존재하는 유저라면 로그인
        if(userRepository.existUser(postSignupReq) > 0) {
            UserInfoDto user = userRepository.getUser(postSignupReq);
            if(user.getRole().equals("STAFF")) isUser = false;
            return new PostLoginRes(jwtService.createJwt(user.getUserId()), isUser);
        }

        //존재하지 않으면 회원가입
        try {
            int userId = userRepository.createUser(postSignupReq);
            return new PostLoginRes(jwtService.createJwt(userId), true);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}