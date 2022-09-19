package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.GetMapRes;
import ssumc.stardust.src.repository.MapRepository;

import static ssumc.stardust.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    /**
     * 먼지들 위치 조회
     */
    public GetMapRes getDustInfo(int userId, String university) throws BaseException {
        try{
            return new GetMapRes(mapRepository.getDustInfo(userId, university));

        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
