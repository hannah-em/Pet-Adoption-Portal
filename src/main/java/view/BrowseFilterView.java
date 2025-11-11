package view;

import interface_adapter.browse_filter.BrowseFilterController;
import interface_adapter.browse_filter.BrowseFilterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BrowseFilterView extends JFrame {
    private final BrowseFilterController controller;
    private final BrowseFilterViewModel viewModel;

    public BrowseFilterView(BrowseFilterController controller, BrowseFilterViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;

        setTitle("🐾 Pet Adoption Browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // Center window on screen

        // === INPUT FIELDS ===
        JTextField speciesField = new JTextField(10);
        JTextField genderField = new JTextField(10);

        // === BUTTONS ===
        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear Filters");

        // === RESULTS AREA ===
        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        // Make results scrollable
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        // === INPUT PANEL ===
        JPanel inputPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
        inputPanel.add(new JLabel("Species:"));
        inputPanel.add(speciesField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderField);

        // === BUTTON PANEL ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);

        // === COMBINE INPUT + BUTTON PANELS ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // === MAIN LAYOUT ===
        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // === ACTIONS ===
        searchButton.addActionListener((ActionEvent e) -> {
            controller.onSearch(
                    speciesField.getText(),
                    genderField.getText()
            );

            resultsArea.setText(String.join("\n", viewModel.getPetsList()));
        });

        clearButton.addActionListener((ActionEvent e) -> {
            speciesField.setText("");
            genderField.setText("");
            resultsArea.setText("");
        });

        setVisible(true);
    }
}
