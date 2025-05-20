package com.group11.bookstore.frames;

import com.group11.bookstore.models.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Lazy
@Component
public class CashierFrame extends JFrame {

    private JPanel panel;
    private JLabel usernameLabel;
    private JButton returnBooksButton;

    private User user;

    public CashierFrame(CustomerSearchFrame customerSearchFrame) {

        super("CashierFrame");

        this.setContentPane(panel);
        this.setSize(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        returnBooksButton.addActionListener((e) -> {
            customerSearchFrame.display(this.user);
            this.dispose();
        });
    }

    public void show(User user) {

        this.user = user;

        var label = String.format("Username: %s", user.getUsername());
        usernameLabel.setText(label);

        this.setVisible(true);
    }
}
