package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PostSignUpRes {
    private int userId;
    private String userJwt;
}
