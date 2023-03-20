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
        @get:JsonProperty("isEnd")
        @SerializedName("is_end")
        val isEnd: Boolean,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}

