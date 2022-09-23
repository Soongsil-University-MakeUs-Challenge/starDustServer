package ssumc.stardust.src.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public class DustLocationDto {

    @DecimalMax(value = "180.0", message = "INVALID_RANGE_LONGITUDE")
    @DecimalMin(value = "-180.0", message = "INVALID_RANGE_LONGITUDE")
    private double longitude; //경도

    @DecimalMax(value = "90.0", message = "INVALID_RANGE_LATITUDE")
    @DecimalMin(value = "-90.0", message = "INVALID_RANGE_LATITUDE")
    private double latitude; //위도
}
