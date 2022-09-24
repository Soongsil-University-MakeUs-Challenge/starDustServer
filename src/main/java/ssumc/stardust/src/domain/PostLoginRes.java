package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PostLoginRes {
    private String userJwt;
    private boolean isUser;
}
