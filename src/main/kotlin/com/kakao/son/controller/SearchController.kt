package com.kakao.son.controller

import com.kakao.son.dto.BlogDTO
import com.kakao.son.model.KeywordVo
import com.kakao.son.service.SearchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/search")
class SearchController {

    @Autowired
    lateinit var searchService: SearchService

    /**
     * 가장 많이 검색된 Keyword 10개를 가져옵니다.
     *
     * @return Keyword객체 리스트 반환
     */
    @GetMapping("/top-ten-keyword")
    fun getKeywordTopTenList(): ResponseEntity<List<KeywordVo?>> {

        return ResponseEntity.ok().body(searchService.getKeywordTopTenList())
    }

    /**
     * 블로그 검색 기능. 검색된 데이터 insert
     *
     * @return BlogDTO 반환
     */
    @GetMapping
    fun getSearch(
            @RequestParam("query") query: String,
            @RequestParam("page", defaultValue = "1") page: Int,
            @RequestParam("sort", defaultValue = "accuracy") sort: String,
    ): ResponseEntity<BlogDTO> {

        if( query == "") throw Exception("검색어가 비어있습니다.")
        if( query.length > 255) throw Exception("검색어는 최대 255자까지 제한됩니다.")
        if( page < 1 || page > 50 ) throw Exception("최대 50페이지까지만 검색 가능합니다.")
        if( sort != "accuracy" && sort != "recency") throw Exception("문서 정렬 방식이 잘못 지정되었습니다.")

        return ResponseEntity.ok().body( searchService.getSearch( query, page, sort ) )

    }
}