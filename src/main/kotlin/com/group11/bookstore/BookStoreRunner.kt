package com.group11.bookstore

import com.group11.bookstore.frames.HelloFrame
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.awt.EventQueue

@Component
class BookStoreRunner
@Autowired constructor(val frame: HelloFrame) : CommandLineRunner {

    override fun run(vararg args: String) {
        EventQueue.invokeLater { frame.isVisible = true }
    }
}