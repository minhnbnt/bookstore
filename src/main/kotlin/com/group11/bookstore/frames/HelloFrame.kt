package com.group11.bookstore.frames

import org.springframework.stereotype.Component
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants

@Component
class HelloFrame : JFrame("Hello, world!") {

    init {

        this.size = Dimension(200, 200)
        this.defaultCloseOperation = EXIT_ON_CLOSE

        this.add(JLabel("Hello, world!", SwingConstants.CENTER))
    }
}