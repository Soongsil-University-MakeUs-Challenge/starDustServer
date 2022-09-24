package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class PostSignUpRes {
    private int userId;
    private String userJwt;

}
