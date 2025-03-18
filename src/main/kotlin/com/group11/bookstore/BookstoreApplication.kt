package com.group11.bookstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class BookstoreApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(BookstoreApplication::class.java)
        .headless(false)
        .run(*args)
}
