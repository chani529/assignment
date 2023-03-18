package com.kakao.son.controller

import com.kakao.son.service.SearchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/search")
class SearchController {

    @Autowired
    lateinit var searchService: SearchService

    @GetMapping
    fun getSearch(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(searchService.getSearch())
    }
}