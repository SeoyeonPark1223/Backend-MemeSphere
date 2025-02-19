package com.memesphere.domain.search.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchListPreviewResponse {
    @Schema(description = "밈코인 id", example = "1")
    Long coinId;

    @Schema(description = "밈코인 name", example = "도지코인")
    String name;

    @Schema(description = "밈코인 symbol", example = "DOGE")
    String symbol;

    @Schema(description = "차트 데이터의 price", example = "2000")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00000000")
    Double currentPrice;

    @Schema(description = "차트 데이터의 weighted average price", example = "10000")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00000000")
    Double weightedAveragePrice;

    @Schema(description = "차트 데이터의 volume", example = "5")
    BigDecimal volume;

    @Schema(description = "차트 데이터의 price_change_rate", example = "+2.4%")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00000000")
    Double priceChangeRate;

    @Schema(description = "collection에 해당 밈코인 유무", example = "true / false")
    Boolean isCollected;
}
