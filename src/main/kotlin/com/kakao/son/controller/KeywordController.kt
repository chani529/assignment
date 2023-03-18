package com.kakao.son.controller

import com.kakao.son.model.KeywordVo
import com.kakao.son.service.KeywordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/keyword")
class KeywordController {

    @Autowired
    lateinit var keywordService: KeywordService

    @GetMapping
    fun getSearch(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(keywordService.getKeywordList())
    }

    @PostMapping
    fun upsertKeyword(@RequestBody keyword: String): ResponseEntity<Any> {
        return ResponseEntity.ok().body(keywordService.upsertKeyword(keyword))
    }
}