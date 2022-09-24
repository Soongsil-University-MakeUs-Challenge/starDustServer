package ssumc.stardust.src.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ssumc.stardust.config.Constant.nicknameRegex;
import static ssumc.stardust.config.Constant.phoneNumRegex;

@Data
public class PostLoginReq {
    @NotBlank(message = "EMPTY_NICKNAME")
    @Pattern(regexp = nicknameRegex, message = "INVALID_NICKNAME")
    private String nickname;

    @NotBlank(message = "EMPTY_PHONENUMBER")
    @Pattern(regexp = phoneNumRegex, message = "INVALID_PHONENUMBER")
    private String phoneNum;
}
