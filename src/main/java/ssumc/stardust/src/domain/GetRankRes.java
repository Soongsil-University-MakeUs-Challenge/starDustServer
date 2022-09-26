package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetRankRes {
    private List<RankerInfoDto> rankerList;
}
