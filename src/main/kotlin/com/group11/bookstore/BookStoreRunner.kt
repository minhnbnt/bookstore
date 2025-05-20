package com.group11.bookstore

import com.group11.bookstore.frames.LoginFrame
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.awt.EventQueue

@Component
class BookStoreRunner
@Autowired constructor(val loginFrame: LoginFrame) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String) {
        EventQueue.invokeLater {
            loginFrame.isVisible = true
        }
    }
}