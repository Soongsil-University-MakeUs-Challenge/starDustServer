package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {
    private int userId;
    private String userJwt;
    private String phoneNum;
    private String role;
}
