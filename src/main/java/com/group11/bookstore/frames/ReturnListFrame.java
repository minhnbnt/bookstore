package com.group11.bookstore.frames;

import com.group11.bookstore.models.Customer;
import com.group11.bookstore.models.RentDetail;
import com.group11.bookstore.models.User;
import com.group11.bookstore.repositories.RentDetailRepository;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Component
public class ReturnListFrame extends JFrame {

    private JTable tblRentBooks;
    private JPanel panel;
    private JLabel customerLabel;
    private JButton submitButton;

    private User user;
    private Customer customer;

    private final RentDetailRepository rentDetailRepository;

    public ReturnListFrame(RentDetailRepository rentDetailRepository) {

        super();

        this.rentDetailRepository = rentDetailRepository;

        this.setContentPane(panel);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void display(User user, Customer customer) {

        this.customer = customer;
        this.user = user;

        var labelText = String.format("Customer: %s", customer.getFullName());
        customerLabel.setText(labelText);

        var books = rentDetailRepository.findRentingBooks(this.customer);
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

        var columns = new String[]{
            "ID",
            "Title name",
            "Author",
            "Publisher",
            "Rent date",
            "Choose"
        };

        var tableModel = new DefaultTableModel(data, columns) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                if (columnIndex == 0) {
                    return Integer.class;
                }

                if (columnIndex == 5) {
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
