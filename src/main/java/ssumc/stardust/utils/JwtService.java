package ssumc.stardust.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ssumc.stardust.config.BaseException;
import ssumc.stardust.config.secret.Secret;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static ssumc.stardust.config.BaseResponseStatus.EMPTY_JWT;
import static ssumc.stardust.config.BaseResponseStatus.INVALID_JWT;

@Service
public class JwtService {
    /*
    JWT 생성
    @param userIdx
    @return String
     */
    public String createJwt(int userId){ //암호화할 때 넣을 값 정의
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")//헤더에 type과 jwt를 넣어줌
                .claim("userId",userId)//그 안의 내용으로 userId를 넣어줌
                .setIssuedAt(now)//발급된 기간을 넣어줌
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*7)))//토큰 만료기간(일주일)
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)//서명할 때 HS256알고리즘 사용, 시크릿키 담아줌
                .compact();
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");//해당 name의 key값의 value값 가져옴
    }

    /*
    JWT에서 userIdx 추출
    @return int
    @throws BaseException
     */
    public int getUserId() throws BaseException {
        //1. JWT 추출
        String accessToken = getJwt();
        if(accessToken == null || accessToken.length() == 0){
            throw new BaseException(EMPTY_JWT);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
        try{//이 안의 바디 값들이 유효한지 안한지 판단
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(INVALID_JWT);
        }

        // 3. userIdx 추출
        return claims.getBody().get("userId",Integer.class);  // jwt 에서 userIdx를 추출합니다.
    }
}
