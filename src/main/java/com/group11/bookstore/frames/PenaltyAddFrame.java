package com.group11.bookstore.frames;

import com.group11.bookstore.models.Penalty;
import com.group11.bookstore.repositories.PenaltyRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

@Component
public class PenaltyAddFrame extends JFrame {

    private JFormattedTextField txtFee;
    private JTextField txtPenaltyName;
    private JButton addButton;
    private JPanel panel;

    public PenaltyAddFrame(ApplicationContext context) {

        super();

        this.setContentPane(panel);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        var repository = context.getBean(PenaltyRepository.class);

        addButton.addActionListener((e) -> {

            var feeValue = txtFee.getValue();
            var penaltyName = txtPenaltyName.getText();

            var isLong = feeValue instanceof Long;
            if (!isLong) {
                // TODO: handle error
                return;
            }

            var fee = (Long) feeValue;
            repository.save(new Penalty(0, penaltyName, fee));

            context
                .getBean(PenaltyUpdateFrame.class)
                .updateTable();

            this.setVisible(false);
        });
    }

    private void createUIComponents() {

        var numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);

        var formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Long.class);
        formatter.setAllowsInvalid(false);

        txtFee = new JFormattedTextField(formatter);
    }
}
