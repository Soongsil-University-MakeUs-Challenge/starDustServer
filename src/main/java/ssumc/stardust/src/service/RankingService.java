package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.GetMapRes;
import ssumc.stardust.src.domain.GetRankRes;
import ssumc.stardust.src.domain.RankerInfoDto;
import ssumc.stardust.src.repository.RankingRepository;
import ssumc.stardust.src.repository.UserRepository;
import ssumc.stardust.utils.JwtService;

import java.util.List;

import static ssumc.stardust.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final JwtService jwtService;

    /**
     * 랭킹 조회
     */
    public GetRankRes getRanking(String univCode, int page, int size) throws BaseException {
        try {
            return new GetRankRes(rankingRepository.getRankerList(univCode, page, size));
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
