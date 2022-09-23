package ssumc.stardust.src.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class GetMapRes {

    private List<DustInfoDto> dustInfo;

    public GetMapRes(List<DustInfoDto> dustInfo) {
        this.dustInfo = dustInfo;
    }
}
