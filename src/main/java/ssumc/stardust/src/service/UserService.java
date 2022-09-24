package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.repository.UserRepository;

import static ssumc.stardust.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
}
