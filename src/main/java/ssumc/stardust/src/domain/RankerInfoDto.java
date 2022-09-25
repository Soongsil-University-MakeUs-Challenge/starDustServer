package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class RankerInfoDto {
    private String nickname;
    private String lastNum;
    private String playTime;
}
