package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.GetRankRes;
import ssumc.stardust.src.repository.RankingRepository;
import ssumc.stardust.utils.JwtService;
import static ssumc.stardust.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

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
