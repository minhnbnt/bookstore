package com.group11.bookstore.frames;

import com.group11.bookstore.models.User;
import com.group11.bookstore.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class LoginFrame extends JFrame {

    private JPanel panel;
    private JButton btnLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginFrame(
        UserService userService,
        @Lazy CashierFrame cashierFrame
    ) {

        super("LoginFrame");

        this.setContentPane(panel);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnLogin.addActionListener((e) -> {

            var username = txtUsername.getText();
            var password = new String(txtPassword.getPassword());

            var user = new User(username, password);

            var isValidLogin = userService.checkLogin(user);
            if (isValidLogin) {
                cashierFrame.show(user);
                this.dispose();
                return;
            }

            JOptionPane.showMessageDialog(
                this,
                "Username or password does not match."
            );
        });
    }
}