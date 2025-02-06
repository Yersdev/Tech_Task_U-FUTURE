package com.javacodeex.u_future.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "DTO для представления количества дождливых дней")
public class RainyDaysCountDTO {

    @Schema(description = "Количество дней с дождем", example = "15")
    private Long rainyDaysCount;
}
