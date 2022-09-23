package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DustInfoDto {

    private int dustId;

    private double longitude; //경도

    private double latitude; //위도

    private boolean isCaught; //잡았는지 여부
}
