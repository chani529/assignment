package com.kakao.son.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


data class BlogDTO (
    val documents : List<Documents>,
    val meta : Meta,
){
    data class Documents (
        val blogname : String,
        val contents : String,
        val datetime : String,
        val thumbnail : String,
        val title : String,
        val url : String
    )

    data class Meta(
        @get:JsonProperty("isEnd") // 객체 직렬화 시 "is" 사라지는 이슈 처리를 위함
        @SerializedName("is_end")
        val isEnd: Boolean,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}

