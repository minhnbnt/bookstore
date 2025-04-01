package com.group11.bookstore.frames;

import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class HelloFrame extends JFrame {

    public HelloFrame() {

        super("Hello, world!");

        this.setSize(200,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(new JLabel("Hello, world!", SwingConstants.CENTER));
    }
}
