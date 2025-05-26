package com.group11.bookstore.frames;

import com.group11.bookstore.models.Book;
import com.group11.bookstore.models.Penalty;
import com.group11.bookstore.repositories.PenaltyRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

    private final PenaltyRepository penaltyRepository;

    private final List<Penalty> newPenalty;

    public BookDetailFrame(
        PenaltyRepository repository,
        @Lazy PenaltyUpdateFrame penaltyUpdateFrame
    ) {

        super();

        this.penaltyRepository = repository;
        this.newPenalty = new ArrayList<>();

        this.setContentPane(panel);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addPenaltyButton.addActionListener(e -> {

            penaltyUpdateFrame.display();

            var selectedPenalty = penaltyUpdateFrame.getSelected();
            selectedPenalty.ifPresent(newPenalty::add);
        });
    }

    public List<Penalty> getNewPenalty() {

        var newPenaltyFuture = new CompletableFuture<List<Penalty>>();

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                newPenaltyFuture.complete(new ArrayList<>());
            }
        });

        okButton.addActionListener(e -> {
            newPenaltyFuture.complete(newPenalty);
        });

        try {
            return newPenaltyFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            this.setVisible(false);
            newPenalty.clear();
        }
    }

    public void display(Book book) {
        initializeLabels(book);
        initializeTablePenalties(book);
    }

    private void initializeLabels(Book book) {
        idLabel.setText(String.format("ID: %d", book.getId()));
        titleLabel.setText(String.format("Title: %s", book.getTitle().getName()));
        authorLabel.setText(String.format("Author: %s", book.getTitle().getAuthor()));
        publisherLabel.setText(String.format("Publisher: %s", book.getTitle().getPublisher()));
    }

    private void initializeTablePenalties(Book book) {

        var columnsName = new Object[]{
            "Penalty name",
            "Fee",
        };

        var penalties = penaltyRepository.findBookPenalties(book);
        var data = penalties.map(penalty -> new Object[]{
                penalty.getName(),
                penalty.getFee(),
            })
            .toArray(Object[][]::new);

        var tableModel = new DefaultTableModel(data, columnsName);
        tblPenalties.setModel(tableModel);
    }
}
