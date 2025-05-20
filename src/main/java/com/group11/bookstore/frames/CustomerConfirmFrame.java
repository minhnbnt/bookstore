package com.group11.bookstore.frames;

import com.group11.bookstore.models.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerConfirmFrame {

    private static final String[] COLUMN_LABELS = {
        "Field name", "value"
    };

    private JPanel panel;
    private JTable customerDetail;

    public CustomerConfirmFrame(Customer customer) {

        var data = new Object[][]{
            new Object[] {"ID", customer.getId()},
            new Object[] {"Full name", customer.getFullName()},
            new Object[] {"Address", customer.getAddress()},
            new Object[] {"Email", customer.getEmail()},
            new Object[] {"Phone number", customer.getPhoneNumber()}
        };

        var tableModel = new DefaultTableModel();
        tableModel.setDataVector(data, COLUMN_LABELS);

        customerDetail.setModel(tableModel);
    }

    public int getChosenValue(Frame parentFrame) {
        return JOptionPane.showConfirmDialog(
            parentFrame,
            panel,
            "JOptionPane",
            JOptionPane.YES_NO_OPTION
        );
    }
}
