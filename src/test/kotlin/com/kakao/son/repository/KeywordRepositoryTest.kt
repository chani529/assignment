package com.kakao.son.repository

import com.kakao.son.model.KeywordVo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName

@DataJpaTest
class KeywordRepositoryTest {

    @Autowired
    lateinit var keywordRepository: KeywordRepository

    @Test
    @DisplayName("TestCase008 - 많이 검색된 검색어 top10 가져오기")
    fun `getKeywordTopTenList - success`(){
        val result = keywordRepository.findTop10ByOrderBySearchCountDescKeywordAsc()

        // init_database.sql 기본 데이터 체크
        assertThat(result,contains(
                KeywordVo(1,"kakao10",10),
                KeywordVo(2,"kakao9",9),
                KeywordVo(3,"kakao8",8),
                KeywordVo(4,"kakao7",7),
                KeywordVo(5,"kakao6",6),
                KeywordVo(6,"kakao5",5),
                KeywordVo(7,"kakao4",4),
                KeywordVo(8,"kakao3",3),
                KeywordVo(9,"kakao2",2),
                KeywordVo(10,"kakao1",1)
        ))
    }

    @Test
    @DisplayName("TestCase009 - 검색어 추가 기능")
    fun `upsertKeyword - update - success`(){
        val keyword = "kakao10"

        val keywordVo = keywordRepository.findByKeyword( keyword ) ?: KeywordVo( keyword = keyword )
        keywordVo.searchCount = keywordVo.searchCount.plus(1)

        val result = keywordRepository.save(keywordVo)

        assertEquals(result, KeywordVo(1,"kakao10", 11))
    }

    @Test
    @DisplayName("TestCase010 - 검색어 업데이트 기능")
    fun `upsertKeyword - insert - success`(){
        val keyword = "daum1"

        val keywordVo = keywordRepository.findByKeyword( keyword ) ?: KeywordVo( keyword = keyword )
        keywordVo.searchCount = keywordVo.searchCount.plus(1)

        val result = keywordRepository.save(keywordVo)

        assertEquals(result, KeywordVo(11,"daum1", 1))
    }
}