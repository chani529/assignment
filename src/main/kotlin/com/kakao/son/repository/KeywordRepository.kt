package com.kakao.son.repository

import com.kakao.son.model.KeywordVo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KeywordRepository : JpaRepository<KeywordVo?, Int> {

    fun findByKeyword( keyword : String ): KeywordVo?

    fun findTop10ByOrderBySearchCountDescKeywordAsc() : List<KeywordVo?>
}