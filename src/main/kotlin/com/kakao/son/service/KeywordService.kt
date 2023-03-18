package com.kakao.son.service

import com.kakao.son.model.KeywordVo
import com.kakao.son.repository.KeywordRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KeywordService(val okHttpClient : OkHttpClient) {
    @Autowired
    lateinit var keywordRepository: KeywordRepository

    fun getKeywordList(): Any?{
        return keywordRepository.findAll()
    }

    fun upsertKeyword(keyword: String): Any?{
        val keywordVo = keywordRepository.findByKeyword(keyword) ?: KeywordVo(keyword = keyword)
        keywordVo.searchCount = keywordVo.searchCount.plus(1)
        return keywordRepository.save(keywordVo)
    }
}