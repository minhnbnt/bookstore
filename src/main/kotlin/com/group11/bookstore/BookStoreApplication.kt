package com.group11.bookstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class BookStoreApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(BookStoreApplication::class.java)
        .headless(false)
        .run(*args)
}
