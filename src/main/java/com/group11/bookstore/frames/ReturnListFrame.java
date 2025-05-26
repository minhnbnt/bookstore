package com.group11.bookstore.frames;

import com.group11.bookstore.models.Customer;
import com.group11.bookstore.models.Penalty;
import com.group11.bookstore.models.RentDetail;
import com.group11.bookstore.models.User;
import com.group11.bookstore.repositories.RentDetailRepository;
import com.group11.bookstore.repositories.ReturnBillRepository;
import com.group11.bookstore.services.ReturnBillService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

@Component
public class ReturnListFrame extends JFrame {

    private static final int CHECKBOX_COLUMN_INDEX = 5;
    private static final String[] COLUMN_LABELS = new String[]{
        "ID", "Title name", "Author", "Publisher", "Rent date", "Choose"
    };

    private final List<RentDetail> rentDetails = new ArrayList<>();
    private final RentDetailRepository rentDetailRepository;

    private User user;
    private Customer customer;
    private JPanel panel;
    private JTable tblRentBooks;
    private JLabel customerLabel;
    private JButton submitButton;

    public ReturnListFrame(
        @Lazy BookDetailFrame bookDetailFrame,
        @Lazy ReturnConfirmFrame returnConfirmFrame,
        ReturnBillService returnBillService,
        RentDetailRepository rentDetailRepository
    ) {

        super();

        this.rentDetailRepository = rentDetailRepository;

        this.setContentPane(panel);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        var newPenaltyMap = new TreeMap<RentDetail, List<Penalty>>(
            Comparator.comparing(RentDetail::getId)
        );

        tblRentBooks.getSelectionModel()
            .addListSelectionListener(e -> {

                if (e.getValueIsAdjusting()) {
                    return;
                }

                var selectedRow = tblRentBooks.getSelectedRow();
                var selectedRent = rentDetails.get(selectedRow);

                bookDetailFrame.display(selectedRent.getBook());
                var newPenalties = bookDetailFrame.getNewPenalty();

                newPenaltyMap
                    .computeIfAbsent(selectedRent, r -> new ArrayList<>())
                    .addAll(newPenalties);
            });

        submitButton.addActionListener(e -> {

            var selectedDetails = getSelectedRows();

            var newBill = returnBillService.getBill(
                user, customer,
                selectedDetails,
                newPenaltyMap
            );

            returnConfirmFrame.display(newBill);
        });
    }

    private List<RentDetail> getSelectedRows() {

        var rows = tblRentBooks.getModel();

        var selectedRows = new ArrayList<RentDetail>();

        for (int i = 0; i < rentDetails.size(); i++) {
            boolean selected = (Boolean) rows.getValueAt(i, CHECKBOX_COLUMN_INDEX);
            if (selected) {
                selectedRows.add(rentDetails.get(i));
            }
        }

        return selectedRows;
    }

    public void display(User user, Customer customer) {

        this.customer = customer;
        this.user = user;

        var labelText = String.format(
            "Customer: %s",
            customer.getFullName()
        );

        customerLabel.setText(labelText);

        var books = rentDetailRepository
            .findRentingBooks(this.customer);

        rentDetails.clear();
        rentDetails.addAll(books);

        initializeRentTable(books);

        this.setVisible(true);
    }

    private void initializeRentTable(List<RentDetail> rentDetails) {

        var data = rentDetails.stream()
            .map(rentDetail -> {

                var book = rentDetail.getBook();
                var bookTitle = book.getTitle();

                return new Object[]{
                    book.getId(),
                    bookTitle.getName(),
                    bookTitle.getAuthor(),
                    bookTitle.getPublisher(),
                    rentDetail.getBill().getDate(),
                    false
                };
            })
            .toArray(Object[][]::new);

        var tableModel = new DefaultTableModel(data, COLUMN_LABELS) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                if (columnIndex == 0) {
                    return Integer.class;
                }

                if (columnIndex == CHECKBOX_COLUMN_INDEX) {
                    return Boolean.class;
                }

                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        tblRentBooks.setModel(tableModel);
    }
}
