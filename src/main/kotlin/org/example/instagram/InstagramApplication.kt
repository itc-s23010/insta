package org.example.instagram

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class InstagramApplication

fun main(args: Array<String>) {
    runApplication<InstagramApplication>(*args)
}