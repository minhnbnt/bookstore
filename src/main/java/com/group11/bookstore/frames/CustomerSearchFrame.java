package com.group11.bookstore.frames;

import com.group11.bookstore.models.Customer;
import com.group11.bookstore.models.User;
import com.group11.bookstore.repositories.CustomerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Lazy
@Component
public class CustomerSearchFrame extends JFrame {

    private static final String[] columns = new String[]{
        "No.", "FullName", "Address", "Email", "Phone number",
    };
    private final AtomicReference<List<Customer>> customersRef
        = new AtomicReference<>();
    private JPanel panel;
    private JTable tblCustomers;
    private JButton searchButton;
    private JTextField txtCustomerName;

    private User user;
    private final ReturnListFrame returnListFrame;

    public CustomerSearchFrame(
        CustomerRepository repository,
        ReturnListFrame returnListFrame
    ) {

        super("CustomerSearchFrame");

        this.returnListFrame = returnListFrame;

        this.setContentPane(panel);
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.updateTable(List.of());

        searchButton.addActionListener((e) -> {

            var keyword = txtCustomerName.getText();
            var customers = repository.findByFullNameContaining(keyword);

            this.updateTable(customers);
        });

        tblCustomers
            .getSelectionModel()
            .addListSelectionListener(this::onCustomerSelected);
    }

    private void onCustomerSelected(ListSelectionEvent e) {

        if (e.getValueIsAdjusting()) {
            return;
        }

        var selectedIndex = tblCustomers.getSelectedRow();
        if (selectedIndex == -1) {
            return;
        }

        var selected = customersRef.get().get(selectedIndex);

        var confirmFrame = new CustomerConfirmFrame(selected);
        var chosenValue = confirmFrame.getChosenValue(this);

        if (chosenValue != JOptionPane.OK_OPTION) {
            return;
        }

        returnListFrame.display(this.user, selected);

        this.dispose();
    }

    public void display(User user) {
        this.user = user;
        this.setVisible(true);
    }

    public void updateTable(List<Customer> customers) {

        var data = customers.stream()
            .map(customer -> new Object[]{
                customer.getId(),
                customer.getFullName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPhoneNumber()
            })
            .toArray(Object[][]::new);

        var tableModel = new DefaultTableModel();
        tableModel.setDataVector(data, columns);

        tblCustomers.setModel(tableModel);
        customersRef.set(customers);
    }
}
