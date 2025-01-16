package com.memesphere.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserInfoResponse {

    @Schema(description = "계정 아이디", example = "1")
    private Long socialId;

    @Schema(description = "계정 닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "계정 이메일", example = "abc123@gmail.com")
    private String email;
}