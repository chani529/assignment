package com.kakao.son.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class SearchControllerTest {

    @Test
    fun `getSearch - success`(){
        val query1 = "TEST1"
        val page1 = 1
        val sort1 = "accuracy"

        if( query1 == "") throw Exception("검색어가 비어있습니다.")
        if( query1.length > 255) throw Exception("검색어는 최대 255자까지 제한됩니다.")
        if( page1 < 1 || page1 > 50 ) throw Exception("최대 50페이지까지만 검색 가능합니다.")
        if( sort1 != "accuracy" && sort1 != "recency") throw Exception("문서 정렬 방식이 잘못 지정되었습니다.")


        assertEquals(query1, "TEST1")
        assertEquals(page1, 1)
        assertEquals(sort1, "accuracy")

        // 두 번째 검증 부분
        val query2 = "TEST2"
        val page2 = 50
        val sort2 = "recency"

        if( query2 == "") throw Exception("검색어가 비어있습니다.")
        if( query2.length > 255) throw Exception("검색어는 최대 255자까지 제한됩니다.")
        if( page2 < 1 || page2 > 50 ) throw Exception("최대 50페이지까지만 검색 가능합니다.")
        if( sort2 != "accuracy" && sort2 != "recency") throw Exception("문서 정렬 방식이 잘못 지정되었습니다.")

        assertEquals(query2, "TEST2")
        assertEquals(page2, 50)
        assertEquals(sort2, "recency")
    }

    @Test
    fun `getSearch - pageError`(){
        val page = 51
        val exception = assertThrows(Exception::class.java) {
            if( page < 1 || page > 50 ) throw Exception("최대 50페이지까지만 검색 가능합니다.")
        }
        assertEquals("최대 50페이지까지만 검색 가능합니다.", exception.message)
    }

    @Test
    fun `getSearch - emptyQueryError`(){
        val query = ""
        val exception = assertThrows(Exception::class.java) {
            if( query == "") throw Exception("검색어가 비어있습니다.")
        }
        assertEquals("검색어가 비어있습니다.", exception.message)
    }

    @Test
    fun `getSearch - toLongQueryError`(){
        // 256자 만들기
        val query = "a".repeat(256)

        val exception = assertThrows(Exception::class.java) {
            if( query.length > 255) throw Exception("검색어는 최대 255자까지 제한됩니다.")
        }
        assertEquals("검색어는 최대 255자까지 제한됩니다.", exception.message)
    }

    @Test
    fun `getSearch - sortError`(){
        val sort = "sort"
        val exception = assertThrows(Exception::class.java) {
            if( sort != "accuracy" && sort != "recency") throw Exception("문서 정렬 방식이 잘못 지정되었습니다.")
        }
        assertEquals("문서 정렬 방식이 잘못 지정되었습니다.", exception.message)
    }
}