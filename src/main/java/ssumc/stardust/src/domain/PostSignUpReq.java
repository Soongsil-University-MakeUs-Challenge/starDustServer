package ssumc.stardust.src.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ssumc.stardust.config.Constant.*;

@Data
public class PostSignUpReq {
    @NotBlank(message = "EMPTY_NICKNAME")
    @Pattern(regexp = nicknameRegex, message = "INVALID_NICKNAME")
    private String nickname;

    @NotBlank(message = "EMPTY_PHONENUMBER")
    @Pattern(regexp = phoneNumRegex, message = "INVALID_PHONENUMBER")
    private String phoneNum;

    @NotBlank(message = "EMPTY_UNIVCODE")
    @Pattern(regexp = univCodeRegex, message = "INVALID_UNIV_CODE")
    private String univCode;
}
