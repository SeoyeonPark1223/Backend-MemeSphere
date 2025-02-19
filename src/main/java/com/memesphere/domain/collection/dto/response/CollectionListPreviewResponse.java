package com.memesphere.domain.collection.dto.response;

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
public class CollectionListPreviewResponse {
    @Schema(description = "밈코인 id", example = "1")
    Long coinId;
    @Schema(description = "밈코인 name", example = "도지코인")
    String name;
    @Schema(description = "밈코인 symbol", example = "DOGE")
    String symbol;
    @Schema(description = "차트 데이터의 price", example = "2000")
    Double currentPrice;
    @Schema(description = "차트 데이터의 price_change_rate", example = "+2.4%")
    Double priceChangeRate;
    @Schema(description = "차트 데이터의 weighted average price", example = "10000")
    Double weightedAveragePrice; // market cap 대신 사용
    @Schema(description = "차트 데이터의 volume", example = "5")
    BigDecimal volume;
}

