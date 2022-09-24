package ssumc.stardust.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.src.domain.CatchDto;
import ssumc.stardust.src.repository.CatchRepository;

import static ssumc.stardust.config.BaseResponseStatus.*;
import static ssumc.stardust.config.Constant.SuccessfulUpdate;

@Service
@RequiredArgsConstructor
public class CatchService {

    private final CatchRepository catchRepository;

    /**
     *  먼지 잡기
     */
    public String setCatch(int userIdByJwt, CatchDto dustNum) throws BaseException {

        //먼지잡기 중복인 경우 처리 벨리데이션
        //System.out.println("isCatch : " + catchRepository.isCatch(userIdByJwt, dustNum));
        if(catchRepository.isCatch(userIdByJwt, dustNum) == 1){
            throw new BaseException(ALREADY_REQUEST);
        }

        try {
            int result = catchRepository.setCatch(userIdByJwt, dustNum);
            if(result==0){
                throw new BaseException(SERVER_ERROR);
            }
            return SuccessfulUpdate;

        } catch (Exception e){
            //System.out.println("**setCatch HERE?");
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /**
     * 잡은 먼지 개수 구하기
     */
    public int countCatch(int userIdByJwt, CatchDto dustNum) throws BaseException {

        try {
            int result = catchRepository.countCatch(userIdByJwt, dustNum);

            //System.out.println("**countCatch : "+ result);
            return result;

        } catch (Exception e){
            //System.out.println("**countCatch HERE?");
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 유저 endTime 수정하기
     */
    public String setEndTime(int userIdByJwt) throws BaseException {

        try {
            int result = catchRepository.setEndTime(userIdByJwt);
            if (result == 0) {
                throw new BaseException(DATABASE_ERROR);
            }
            return SuccessfulUpdate;

        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
