package view;

import controller.VoterController;
import model.Citizen;
import model.VoterSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView {

    private VoterController controller;
    private JFrame frame;

    public MainView(VoterController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Voter Registration System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        // Panels
        JPanel mainPanel = createMainMenuPanel();
        JPanel citizenPanel = createCitizenPanel();
        JPanel adminPanel = createAdminPanel();

        frame.add(mainPanel, "MainMenu");
        frame.add(citizenPanel, "Citizen");
        frame.add(adminPanel, "Admin");

        frame.setVisible(true);
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton citizenBtn = new JButton("Citizen Login/Register");
        JButton adminBtn = new JButton("Admin Login");
        JButton exitBtn = new JButton("Exit");

        citizenBtn.addActionListener(e -> switchPanel("Citizen"));
        adminBtn.addActionListener(e -> switchPanel("Admin"));
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(citizenBtn);
        panel.add(adminBtn);
        panel.add(exitBtn);

        return panel;
    }

    private JPanel createCitizenPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField citizenshipField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField provinceField = new JTextField();

        JButton registerBtn = new JButton("Register");
        JButton viewStatusBtn = new JButton("View Status");
        JButton backBtn = new JButton("Back");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Citizenship Number:"));
        panel.add(citizenshipField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Province:"));
        panel.add(provinceField);

        panel.add(registerBtn);
        panel.add(viewStatusBtn);
        panel.add(backBtn);

        // Register action
        registerBtn.addActionListener(e -> {
            try {
                Citizen citizen = new Citizen(
                        phoneField.getText(),
                        citizenshipField.getText(),
                        null, // national ID optional
                        nameField.getText(),
                        genderField.getText(),
                        Integer.parseInt(ageField.getText()),
                        provinceField.getText(),
                        "", "", 0, "" // dummy district, municipality, ward, vote center
                );

                boolean success = controller.registerCitizen(citizen);
                JOptionPane.showMessageDialog(frame,
                        success ? "Registered successfully!" : "Citizen already registered.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        // View status action
        viewStatusBtn.addActionListener(e -> {
            String status = controller.viewApplicationStatus(citizenshipField.getText());
            JOptionPane.showMessageDialog(frame, "Application Status: " + status);
        });

        // Back action
        backBtn.addActionListener(e -> switchPanel("MainMenu"));

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        JButton verifyBtn = new JButton("Verify Next Application");
        JButton deleteBtn = new JButton("Delete Citizen");
        JButton searchBtn = new JButton("Search Citizen");
        JButton sortBtn = new JButton("Sort Citizens by Name");
        JButton viewAllBtn = new JButton("View All Citizens");
        JButton backBtn = new JButton("Back");

        panel.add(verifyBtn);
        panel.add(deleteBtn);
        panel.add(searchBtn);
        panel.add(sortBtn);
        panel.add(viewAllBtn);
        panel.add(backBtn);

        // Verify next
        verifyBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(frame,
                    "Approve next application?", "Verify", JOptionPane.YES_NO_OPTION);
            boolean approve = choice == JOptionPane.YES_OPTION;
            Citizen citizen = controller.verifyNextApplication(approve);
            JOptionPane.showMessageDialog(frame,
                    citizen != null ? "Application updated: " + citizen : "No pending applications");
        });

        // Delete
        deleteBtn.addActionListener(e -> {
            String citizenship = JOptionPane.showInputDialog(frame, "Enter citizenship number to delete:");
            boolean success = controller.deleteCitizen(citizenship);
            JOptionPane.showMessageDialog(frame,
                    success ? "Deleted successfully" : "Citizen not found");
        });

        // Search
        searchBtn.addActionListener(e -> {
            String citizenship = JOptionPane.showInputDialog(frame, "Enter citizenship number to search:");
            Citizen citizen = controller.searchCitizen(citizenship);
            JOptionPane.showMessageDialog(frame,
                    citizen != null ? citizen.toString() : "Citizen not found");
        });

        // Sort
        sortBtn.addActionListener(e -> {
            controller.sortCitizensByName();
            JOptionPane.showMessageDialog(frame, "Citizens sorted by name!");
        });

        // View all
        viewAllBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Citizen c : controller.getAllCitizens()) {
                sb.append(c.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "No citizens registered");
        });

        // Back
        backBtn.addActionListener(e -> switchPanel("MainMenu"));

        return panel;
    }

    private void switchPanel(String panelName) {
        CardLayout layout = (CardLayout) frame.getContentPane().getLayout();
        layout.show(frame.getContentPane(), panelName);
    }

    // ------------------------
    // Main method to run app
    // ------------------------
    public static void main(String[] args) {
        VoterSystem system = new VoterSystem();
        VoterController controller = new VoterController(system);
        new MainView(controller);
    }
}
