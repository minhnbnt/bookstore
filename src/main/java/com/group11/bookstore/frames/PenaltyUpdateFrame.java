package com.group11.bookstore.frames;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component
public class PenaltyUpdateFrame extends JFrame {

    private JTable table1;
    private JPanel panel1;
    private JButton addNewPenaltyTypeButton;

    public PenaltyUpdateFrame() {

        super();

        this.setContentPane(panel1);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeTable();
    }

    private void initializeTable() {

        var columnsName = new Object[]{
            "ID",
            "Penalty name",
            "Fee"
        };

        var data = new Object[][]{
            new Object[]{"1","Bị móp góc", "7000"}
        };

        var tableModel = new DefaultTableModel(data, columnsName);
        table1.setModel(tableModel);
    }
}
