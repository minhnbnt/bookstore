package com.group11.bookstore.frames;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component
public class BookDetailFrame extends JFrame {

    private JTable tblPenalties;
    private JPanel panel;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel publisherLabel;
    private JLabel authorLabel;
    private JButton addPenaltyButton;
    private JButton okButton;

    public BookDetailFrame() {

        super();

        this.setContentPane(panel);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeTablePenalties();
    }

    private void initializeTablePenalties() {

        var columnsName = new Object[]{
            "Penalty name",
            "Fee",
            "Detected on"
        };

        var data = new Object[][]{
            new Object[]{"Bị móp góc", "7000", "30/4/1975"}
        };

        var tableModel = new DefaultTableModel(data, columnsName);

        tblPenalties.setModel(tableModel);
    }
}
