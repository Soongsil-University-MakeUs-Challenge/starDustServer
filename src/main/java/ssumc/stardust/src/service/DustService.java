package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.DustLocationDto;
import ssumc.stardust.src.repository.DustRepository;

import static ssumc.stardust.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class DustService {

    private final DustRepository dustRepository;

    /**
    먼지 위치 정보 업데이트
     */
    public String setLocation (int userId, DustLocationDto dustLocationDto) throws BaseException {
        try{
            int result = dustRepository.setLocation(userId, dustLocationDto);
            if (result == 0) {
                throw new BaseException(DATABASE_ERROR);
            }
            return "성공적으로 업데이트 했습니다.";

        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
