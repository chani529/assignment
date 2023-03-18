package com.kakao.son.model

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*


@Entity
@Table(name = "tb_keyword")
@DynamicInsert
@DynamicUpdate
data class KeywordVo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="keyword_id")
    var keywordId: Int? = null,

    @Column(name="keyword")
    var keyword: String? = null,

    @Column(name="search_count")
    var searchCount: Int = 0
)