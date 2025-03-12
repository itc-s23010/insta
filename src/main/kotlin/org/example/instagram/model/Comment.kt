package org.example.instagram.model


import jakarta.persistence.*

@Entity
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post? = null, // デフォルト値をnullに設定
    var username: String = "",
    var content: String = ""
) {
    // デフォルトコンストラクタを追加
    constructor() : this(0, null, "", "")
}