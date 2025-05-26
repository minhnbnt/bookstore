package com.group11.bookstore.frames;

import com.group11.bookstore.models.Penalty;
import com.group11.bookstore.repositories.PenaltyRepository;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class PenaltyUpdateFrame extends JFrame {

    private JTable table1;
    private JPanel panel1;
    private JButton addNewPenaltyTypeButton;

    private final AtomicReference<List<Penalty>> penaltiesRef
        = new AtomicReference<>(List.of());

    private final PenaltyRepository penaltyRepository;

    public PenaltyUpdateFrame(
        PenaltyRepository repository,
        PenaltyAddFrame penaltyAddFrame
    ) {

        super();

        this.penaltyRepository = repository;

        this.setContentPane(panel1);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addNewPenaltyTypeButton.addActionListener(e -> {
            penaltyAddFrame.setVisible(true);
        });
    }

    public void display() {

        if (penaltiesRef.get().isEmpty()) {
            updateTable();
        }

        this.setVisible(true);
    }

    public void updateTable() {

        var penalties = penaltyRepository.findAll();
        penaltiesRef.set(penalties);

        var columnsName = new Object[]{
            "ID",
            "Penalty name",
            "Fee",
            "Choose"
        };

        var data = penalties.stream()
            .map(penalty -> new Object[]{
                penalty.getId(),
                penalty.getName(),
                penalty.getFee(),
                false
            })
            .toArray(Object[][]::new);

        var tableModel = new DefaultTableModel(data, columnsName) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                if (columnIndex == 0 || columnIndex == 2) {
                    return Long.class;
                }

                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1.setModel(tableModel);
    }

    public Optional<Penalty> getSelected() {

        var selectedPenalty = new CompletableFuture<Optional<Penalty>>();

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                selectedPenalty.complete(Optional.empty());
            }
        });

        try {
            return selectedPenalty.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            this.setVisible(false);
        }
    }
}
