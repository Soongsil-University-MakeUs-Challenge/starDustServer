package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.repository.UserRepository;

import static ssumc.stardust.config.BaseResponseStatus.DATABASE_ERROR;
import static ssumc.stardust.config.BaseResponseStatus.INVALID_USER_JWT;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
}
