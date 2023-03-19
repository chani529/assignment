package com.kakao.son.service

import com.kakao.son.dto.BlogDTO
import com.kakao.son.model.KeywordVo
import com.kakao.son.openApi.BlogSearchHandler
import com.kakao.son.repository.KeywordRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchService() {
    @Autowired
    lateinit var blogSearchHandler: BlogSearchHandler

    @Autowired
    lateinit var keywordRepository: KeywordRepository

    fun getKeywordTopTenList(): List<KeywordVo?>{
        return keywordRepository.findTop10ByOrderBySearchCountDescKeywordAsc()
    }

    fun getSearch( query : String, page : Int,sort : String ): BlogDTO?{
        upsertKeyword(query)
        return blogSearchHandler.getKakaoBlogSearch(query,page,sort)
    }

    @Transactional
    private fun upsertKeyword( keyword: String ){
        val keywordVo = keywordRepository.findByKeyword( keyword ) ?: KeywordVo( keyword = keyword )
        keywordVo.searchCount = keywordVo.searchCount.plus(1)
        keywordRepository.save(keywordVo)
    }
}