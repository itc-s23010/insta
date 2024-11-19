package org.example.instagram.model

import jakarta.persistence.*


@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,  // valから変更しない
    var username: String = "",
    var content: String = "",
    var imageUrl: String = ""
)