/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manajemenkeuanganpribadi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Advie Rifaldy
 */
public class ManajemenKeuanganPribadi extends JFrame {

    public static void main(String[] args) {
        Form1();
    }

    private static int balance = 0;

    private static Object[][] data;

    public static void Form1() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Manajemen Keuangan Pribadi");
        frame.setLayout(new GridLayout(2, 1)); // Menggunakan GridLayout untuk panel dan tabel
        frame.setLocation(1130, 150);
        frame.setSize(720, 780);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 0, 16));
        panel.setBorder(new EmptyBorder(50, 32, 50, 32));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(24);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(24);

        JLabel typeLabel = new JLabel("Type:");
        JRadioButton maleRadioButton = new JRadioButton("Income");
        JRadioButton femaleRadioButton = new JRadioButton("Outcome");
        ButtonGroup typeButtonGroup = new ButtonGroup();
        typeButtonGroup.add(maleRadioButton);
        typeButtonGroup.add(femaleRadioButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(maleRadioButton);
        buttonPanel.add(femaleRadioButton);

        JButton submitButton = new JButton("Submit");

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(480, 400));

        JLabel balanceLabel = new JLabel("Current Balance :");
        JTextField balanceField = new JTextField(24);
        balanceField.setEditable(false);

        // Nama kolom
        String[] columnNames = {"Name", "Amount", "Type"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String amountText = amountField.getText();
                int amount = Integer.parseInt(amountText);
                String type = maleRadioButton.isSelected() ? "Income" : "Outcome";

                if (name != null && amountText != null) {

                    // Lakukan tindakan sesuai dengan data yang diinput
                    System.out.println("Name: " + name);
                    System.out.println("Amount: " + amount);
                    System.out.println("Type: " + type);

                    // Menambahkan data baru ke dalam array
                    Object[] newData = {name, type, amount};

                    if (data == null) {
                        data = new Object[1][];
                        data[0] = newData;
                        DefaultTableModel model = new DefaultTableModel(data, columnNames);
                        table.setModel(model);
                    } else {
                        Object[][] newDataArray = new Object[data.length + 1][data.length];
                        for (int i = 0; i < data.length; i++) {
                            newDataArray[i] = data[i];
                        }
                        newDataArray[data.length] = newData;

                        data = newDataArray;

                        DefaultTableModel model = new DefaultTableModel(newDataArray, columnNames);
                        table.setModel(model);
                    }

                    if (type.equals("Income")) {
                        balance = balance + amount;
                    } else {
                        balance = balance - amount;
                    }
                    balanceField.setText("Rp." + Integer.toString(balance));
                    nameField.setText((""));
                    amountField.setText((""));
                    frame.revalidate();
                }
            }
        }
        );

        panel.add(balanceLabel);

        panel.add(balanceField);

        panel.add(nameLabel);

        panel.add(nameField);

        panel.add(amountLabel);

        panel.add(amountField);

        panel.add(typeLabel);

        panel.add(buttonPanel);

        panel.add(
                new JLabel());
        panel.add(submitButton);

        frame.add(panel);

        frame.add(tablePanel);

        frame.setVisible(
                true);
    }
}
