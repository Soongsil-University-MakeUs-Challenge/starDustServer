package ssumc.stardust.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 2XX : 요청 성공
     */
    SUCCESS(true, 200, "요청에 성공하였습니다."),

    /**
     * 4XX  : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 400, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 401, "JWT를 입력해주세요."),
    INVALID_JWT(false, 402, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false, 403, "권한이 없는 유저의 접근입니다."),

    INVALID_RANGE_LONGITUDE(false, 404, "유효하지 않은 경도 값의 범위입니다."),
    INVALID_RANGE_LATITUDE(false, 405, "유효하지 않은 위도 값의 범위입니다."),

    EMPTY_PATH_VARIABLE(false, 406, "Path Variable을 입력해주세요."),
    INVALID_UNIVERSITY(false, 407, "올바른 학교 약칭을 입력해주세요."),


    /**
     * 5XX : Database, Server 오류
     */
    SERVER_ERROR(false, 500, "서버와의 연결에 실패하였습니다."),
    DATABASE_ERROR(false, 501, "데이터베이스 연결에 실패하였습니다."),

    PASSWORD_ENCRYPTION_ERROR(false, 502, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 503, "비밀번호 복호화에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    public static BaseResponseStatus of(final String errorName) {
        return BaseResponseStatus.valueOf(errorName);
    }
}
