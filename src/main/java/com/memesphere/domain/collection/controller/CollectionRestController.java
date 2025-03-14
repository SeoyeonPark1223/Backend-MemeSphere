package com.memesphere.domain.collection.controller;

import com.memesphere.domain.collection.service.CollectionCommandService;
import com.memesphere.domain.search.entity.SortType;
import com.memesphere.domain.search.entity.ViewType;
import com.memesphere.global.apipayload.ApiResponse;
import com.memesphere.domain.collection.entity.Collection;
import com.memesphere.domain.collection.dto.response.CollectionPageResponse;
import com.memesphere.domain.collection.service.CollectionQueryService;
import com.memesphere.global.apipayload.code.status.ErrorStatus;
import com.memesphere.global.apipayload.exception.GeneralException;
import com.memesphere.global.jwt.CustomUserDetails;
import com.memesphere.global.jwt.TokenProvider;
import com.memesphere.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.memesphere.domain.collection.converter.CollectionConverter;

@Tag(name="콜렉션", description = "콜렉션 관련  API")
@RestController
@RequiredArgsConstructor
public class CollectionRestController {
    private final CollectionQueryService collectionQueryService;
    private final CollectionCommandService collectionCommandService;
    private final TokenProvider tokenProvider;

    @GetMapping("/collection")
    @Operation(summary = "사용자의 밈코인 콜렉션 모음 조회 API")
    public ApiResponse<CollectionPageResponse> getCollectionList (
            @RequestParam(name = "viewType", defaultValue = "GRID") ViewType viewType, // 뷰 타입 (grid 또는 list)
            @RequestParam(name = "sortType", defaultValue = "PRICE_CHANGE") SortType sortType, // 정렬 기준 (MKTCap, 24h Volume, Price)
            @AuthenticationPrincipal CustomUserDetails userDetails, // 현재 로그인한 사용자
            @CheckPage @RequestParam(name = "page") Integer page // 페이지 번호
    ) {
        Integer pageNumber = page - 1;
        Long userId = (userDetails == null) ? null : userDetails.getUser().getId();

        // 유저를 찾지 못하면(로그인을 안 했으면) 콜렉션 접근 못하도록 에러 처리
        if (userId == null) throw new GeneralException(ErrorStatus.USER_NOT_FOUND);

        Page<Collection> collectionPage = collectionQueryService.getCollectionPage(userId, pageNumber, viewType, sortType);
        return ApiResponse.onSuccess(CollectionConverter.toCollectionPageDTO(collectionPage, viewType));
    }

    @PostMapping("/collection/{coinId}")
    @Operation(summary = "밈코인 콜렉션 등록 API",
                description = "코인 Id를 입력하면 사용자의 콜렉션에 등록")
    public ApiResponse<String> postCollectCoin (@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                @PathVariable Long coinId) {

        Long userId = customUserDetails.getUser().getId();

        return ApiResponse.onSuccess(collectionCommandService.addCollectCoin(userId, coinId));
    }

    @DeleteMapping("/collection/{coinId}")
    @Operation(summary = "밈코인 콜렉션 삭제 API",
            description = "코인 Id를 입력하면 사용자의 콜렉션에서 삭제")
    public ApiResponse<String> deleteCollectCoin (@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @PathVariable Long coinId) {

        Long userId = customUserDetails.getUser().getId();

        return ApiResponse.onSuccess(collectionCommandService.removeCollectCoin(userId, coinId));
    }

}