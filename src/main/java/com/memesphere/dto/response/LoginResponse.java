package com.memesphere.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class LoginResponse {

    @Schema(description = "액세스 토큰", example = "adfijgkdfgklefk12345678912345648")
    private String accessToken;

    @Schema(description = "리프레시 토큰", example = "dklksdfsdklkfdslk9876542316468645")
    private String refreshToken;
}