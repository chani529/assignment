package com.kakao.son.openApi

import com.google.gson.Gson
import com.kakao.son.dto.BlogDTO
import org.junit.jupiter.api.Test
import okhttp3.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import java.util.concurrent.TimeUnit

class KakaoBlogTest {
    private val kakaoBlogSearchURL = "https://dapi.kakao.com/v2/search/blog"
    private val kakaoBlogSearchErrorURL = "https://dapi.kakao.com/v2/search/error"

    private var kakaoApiKey: String = "efa774a7cc48088c8c30ecc68645be44"

    private val okHttpClient = OkHttpClient()
        .newBuilder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
        }.build()

    @Test
    @DisplayName("TestCase006 - 카카오 블로그 검색 성공")
    fun `getKakaoBlogSearch - success`() {
        val query = "집짓기"
        val page = 1
        val sort = "accuracy"

        val apiResult = okHttpClient.newCall(
            Request.Builder()
                .url("${kakaoBlogSearchURL}?query=${query}&page=${page}&sort=${sort}")
                .addHeader("Authorization", "KakaoAK $kakaoApiKey")
                .build()
        ).execute().use { it ->
            if(!it.isSuccessful) throw Exception("Kakao API Error")
            it.body?.string()
        }

        val result = Gson().fromJson(apiResult, BlogDTO::class.java)

        assertEquals(false, result.meta.isEnd)
    }

    @Test
    @DisplayName("TestCase007 - 카카오 블로그 검색 실패")
    fun `getKakaoBlogSearch - error`() {
        val query = "집짓기"
        val page = 1
        val sort = "accuracy"

        val exception = Assertions.assertThrows(Exception::class.java) {
            okHttpClient.newCall(
                Request.Builder()
                    .url("${kakaoBlogSearchErrorURL}?query=${query}&page=${page}&sort=${sort}")
                    .addHeader("Authorization", "KakaoAK $kakaoApiKey")
                    .build()
            ).execute().use { it ->
                if(!it.isSuccessful) throw Exception("Kakao API Error")
                it.body?.string()
            }
        }

        assertEquals("Kakao API Error", exception.message)

    }
}

