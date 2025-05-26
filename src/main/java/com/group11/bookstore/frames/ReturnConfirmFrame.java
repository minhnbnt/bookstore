package com.group11.bookstore.frames;

import com.group11.bookstore.models.ReturnBill;
import com.group11.bookstore.repositories.ReturnBillRepository;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ReturnConfirmFrame {

    private final ReturnBillRepository repository;

    private JPanel panel;

    private JLabel submitterName;
    private JLabel submitterUsername;
    private JLabel submitterEmail;
    private JLabel submitterPhoneNumber;
    private JLabel customerFullName;
    private JLabel customerPhoneNumber;
    private JLabel customerEmail;

    private JTable table1;
    private JTable table2;

    public ReturnConfirmFrame(ReturnBillRepository returnBillRepository) {
        repository = returnBillRepository;
    }

    public void updateLabels(ReturnBill bill) {

        var submitter = bill.getSubmitter();

        submitterEmail.setText(String.format("Email: %s", submitter.getEmail()));
        submitterName.setText(String.format("Full name: %s", submitter.getFullName()));
        submitterUsername.setText(String.format("Username: %s", submitter.getUsername()));
        submitterPhoneNumber.setText(String.format("Phone number: %s", submitter.getPhoneNumber()));

        var customer = bill.getCustomer();

        customerEmail.setText(String.format("Email: %s", customer.getEmail()));
        customerFullName.setText(String.format("Full name: %s", customer.getFullName()));
        customerPhoneNumber.setText(String.format("Phone number: %s", customer.getPhoneNumber()));
    }

    public void display(ReturnBill bill) {

        updateLabels(bill);

        var result = JOptionPane.showConfirmDialog(
            null,
            panel,
            ReturnConfirmFrame.class.getName(),
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            repository.save(bill);
        }
    }
}
