/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.CitizenController;
import model.CitizenModel.Gender;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author bijen
 */
public class AddCitizenDialog extends javax.swing.JDialog {
    
    private CitizenController controller;
    private boolean successful = false;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AddCitizenDialog.class.getName());

    /**
     * Creates new form AddCitizenDialog
     */
    public AddCitizenDialog(java.awt.Frame parent, boolean modal, CitizenController controller) {
        super(parent, modal);
        this.controller = controller;
        initComponents();
        setLocationRelativeTo(parent);
        
        setupComboBoxes();
    }
    
    public boolean wasSuccessful() {
        return successful;
    }
    
    public void setupComboBoxes() {
        
    }
    
    public void updateDistrictComboBox() {
        String selectedProvince = (String) provinceComboBox.getSelectedItem();
        districtComboBox.removeAllItems();
        districtComboBox.addItem("-- Select District --");
        
        if (selectedProvince == null || selectedProvince.equals("-- Select Province --")) {
            return;
        }
        
        // Add districts based on province
        switch (selectedProvince) {
            case "Koshi":
                districtComboBox.addItem("Bhojpur");
                districtComboBox.addItem("Dhankuta");
                districtComboBox.addItem("Ilam");
                districtComboBox.addItem("Jhapa");
                districtComboBox.addItem("Khotang");
                districtComboBox.addItem("Morang");
                districtComboBox.addItem("Okhaldhunga");
                districtComboBox.addItem("Panchthar");
                districtComboBox.addItem("Sankhuwasabha");
                districtComboBox.addItem("Solukhumbu");
                districtComboBox.addItem("Sunsari");
                districtComboBox.addItem("Taplejung");
                districtComboBox.addItem("Terhathum");
                districtComboBox.addItem("Udayapur");
                break;
                
            case "Madhesh":
                districtComboBox.addItem("Bara");
                districtComboBox.addItem("Dhanusha");
                districtComboBox.addItem("Mahottari");
                districtComboBox.addItem("Parsa");
                districtComboBox.addItem("Rautahat");
                districtComboBox.addItem("Saptari");
                districtComboBox.addItem("Sarlahi");
                districtComboBox.addItem("Siraha");
                break;
                
            case "Bagmati":
                districtComboBox.addItem("Bhaktapur");
                districtComboBox.addItem("Chitwan");
                districtComboBox.addItem("Dhading");
                districtComboBox.addItem("Dolakha");
                districtComboBox.addItem("Kathmandu");
                districtComboBox.addItem("Kavrepalanchok");
                districtComboBox.addItem("Lalitpur");
                districtComboBox.addItem("Makwanpur");
                districtComboBox.addItem("Nuwakot");
                districtComboBox.addItem("Ramechhap");
                districtComboBox.addItem("Rasuwa");
                districtComboBox.addItem("Sindhuli");
                districtComboBox.addItem("Sindhupalchok");
                break;
                
            case "Gandaki":
                districtComboBox.addItem("Baglung");
                districtComboBox.addItem("Gorkha");
                districtComboBox.addItem("Kaski");
                districtComboBox.addItem("Lamjung");
                districtComboBox.addItem("Manang");
                districtComboBox.addItem("Mustang");
                districtComboBox.addItem("Myagdi");
                districtComboBox.addItem("Nawalpur");
                districtComboBox.addItem("Parbat");
                districtComboBox.addItem("Syangja");
                districtComboBox.addItem("Tanahun");
                break;
                
            case "Lumbini":
                districtComboBox.addItem("Arghakhanchi");
                districtComboBox.addItem("Banke");
                districtComboBox.addItem("Bardiya");
                districtComboBox.addItem("Dang");
                districtComboBox.addItem("Gulmi");
                districtComboBox.addItem("Kapilvastu");
                districtComboBox.addItem("Palpa");
                districtComboBox.addItem("Parasi");
                districtComboBox.addItem("Pyuthan");
                districtComboBox.addItem("Rolpa");
                districtComboBox.addItem("Rukum East");
                districtComboBox.addItem("Rupandehi");
                break;
                
            case "Karnali":
                districtComboBox.addItem("Dailekh");
                districtComboBox.addItem("Dolpa");
                districtComboBox.addItem("Humla");
                districtComboBox.addItem("Jajarkot");
                districtComboBox.addItem("Jumla");
                districtComboBox.addItem("Kalikot");
                districtComboBox.addItem("Mugu");
                districtComboBox.addItem("Rukum West");
                districtComboBox.addItem("Salyan");
                districtComboBox.addItem("Surkhet");
                break;
                
            case "Sudurpashchim":
                districtComboBox.addItem("Achham");
                districtComboBox.addItem("Baitadi");
                districtComboBox.addItem("Bajhang");
                districtComboBox.addItem("Bajura");
                districtComboBox.addItem("Dadeldhura");
                districtComboBox.addItem("Darchula");
                districtComboBox.addItem("Doti");
                districtComboBox.addItem("Kailali");
                districtComboBox.addItem("Kanchanpur");
                break;
        }
    }
    
    
    private boolean validateFields() {
        // Check all required fields
        if (citizenshipField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Citizenship number is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            citizenshipField.requestFocus();
            return false;
        }
        
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return false;
        }
        
        if (phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneField.requestFocus();
            return false;
        }
        
        String selectedGender = (String) genderComboBox.getSelectedItem();
        if (selectedGender == null || selectedGender.equals("-- Select Gender --")) {
            JOptionPane.showMessageDialog(this, "Please select gender!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            genderComboBox.requestFocus();
            return false;
        }
        
        java.util.Date selectedDate = dobChooser.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Date of birth is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            dobChooser.requestFocus();
            return false;
        }
        LocalDate dob = selectedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
        LocalDate maxAge = LocalDate.now().minusYears(120);
        
        if (!dob.isBefore(eighteenYearsAgo)) {
            JOptionPane.showMessageDialog(this, "Citizen must be at least 18 years old!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dob.isAfter(maxAge)) {
            JOptionPane.showMessageDialog(this, "Age cannot exceed 120 years!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String selectedProvince = (String) provinceComboBox.getSelectedItem();
        if (selectedProvince == null || selectedProvince.equals("-- Select Province --")) {
            JOptionPane.showMessageDialog(this, "Please select province!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            provinceComboBox.requestFocus();
            return false;
        }
        
        String selectedDistrict = (String) districtComboBox.getSelectedItem();
        if (selectedDistrict == null || selectedDistrict.equals("-- Select District --")) {
            JOptionPane.showMessageDialog(this, "Please select district!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            districtComboBox.requestFocus();
            return false;
        }
        
        if (municipalityField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Municipality is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            municipalityField.requestFocus();
            return false;
        }
        
        if (voteCenterField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vote center is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            voteCenterField.requestFocus();
            return false;
        }
        
        return true;   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diagHeaderPanel = new javax.swing.JPanel();
        diagHeaderLabel = new javax.swing.JLabel();
        diagfooterPanel = new javax.swing.JPanel();
        diagSaveBtn = new javax.swing.JButton();
        diadCancelBtn = new javax.swing.JButton();
        diagCenterPanel = new javax.swing.JPanel();
        citizenshipLabel = new javax.swing.JLabel();
        citizenshipField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        genderLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        DOBLabel = new javax.swing.JLabel();
        dobChooser = new com.toedter.calendar.JDateChooser();
        provinceLabel = new javax.swing.JLabel();
        provinceComboBox = new javax.swing.JComboBox<>();
        districtLabel = new javax.swing.JLabel();
        districtComboBox = new javax.swing.JComboBox<>();
        municipalityLabel = new javax.swing.JLabel();
        municipalityField = new javax.swing.JTextField();
        votecenterLabel = new javax.swing.JLabel();
        voteCenterField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Citizen Registration");
        setPreferredSize(new java.awt.Dimension(500, 650));

        diagHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        diagHeaderPanel.setLayout(new java.awt.GridLayout());

        diagHeaderLabel.setBackground(new java.awt.Color(255, 255, 255));
        diagHeaderLabel.setFont(new java.awt.Font("SimSun", 1, 48)); // NOI18N
        diagHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagHeaderLabel.setText("New Citizen Registration");
        diagHeaderPanel.add(diagHeaderLabel);

        getContentPane().add(diagHeaderPanel, java.awt.BorderLayout.PAGE_START);

        diagfooterPanel.setBackground(new java.awt.Color(255, 255, 255));
        diagfooterPanel.setLayout(new java.awt.GridLayout(1, 2, 50, 50));

        diagSaveBtn.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        diagSaveBtn.setText("Save");
        diagSaveBtn.setPreferredSize(new java.awt.Dimension(72, 50));
        diagSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagSaveBtnActionPerformed(evt);
            }
        });
        diagfooterPanel.add(diagSaveBtn);

        diadCancelBtn.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        diadCancelBtn.setText("Cancel");
        diadCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diadCancelBtnActionPerformed(evt);
            }
        });
        diagfooterPanel.add(diadCancelBtn);

        getContentPane().add(diagfooterPanel, java.awt.BorderLayout.PAGE_END);

        diagCenterPanel.setBackground(new java.awt.Color(255, 255, 255));
        diagCenterPanel.setLayout(new java.awt.GridLayout(9, 2, 20, 20));

        citizenshipLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        citizenshipLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        citizenshipLabel.setText("Citizenship Number: *");
        diagCenterPanel.add(citizenshipLabel);
        diagCenterPanel.add(citizenshipField);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel.setText("Full Name: *");
        diagCenterPanel.add(nameLabel);
        diagCenterPanel.add(nameField);

        phoneLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        phoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phoneLabel.setText("Phone Number: *");
        diagCenterPanel.add(phoneLabel);
        diagCenterPanel.add(phoneField);

        genderLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        genderLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        genderLabel.setText("Gender: *");
        diagCenterPanel.add(genderLabel);

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Gender --", "Male", "Female", "Other" }));
        diagCenterPanel.add(genderComboBox);

        DOBLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DOBLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DOBLabel.setText("Date of Birth: *");
        diagCenterPanel.add(DOBLabel);
        diagCenterPanel.add(dobChooser);

        provinceLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        provinceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        provinceLabel.setText("Province: *");
        diagCenterPanel.add(provinceLabel);

        provinceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Province --", "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim" }));
        provinceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceComboBoxActionPerformed(evt);
            }
        });
        diagCenterPanel.add(provinceComboBox);

        districtLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        districtLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        districtLabel.setText("District: *");
        diagCenterPanel.add(districtLabel);

        districtComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select District --" }));
        diagCenterPanel.add(districtComboBox);

        municipalityLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        municipalityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        municipalityLabel.setText("Municipality: *");
        diagCenterPanel.add(municipalityLabel);
        diagCenterPanel.add(municipalityField);

        votecenterLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        votecenterLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        votecenterLabel.setText("Vote Center: *");
        diagCenterPanel.add(votecenterLabel);
        diagCenterPanel.add(voteCenterField);

        getContentPane().add(diagCenterPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void diagSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagSaveBtnActionPerformed
        // TODO add your handling code here:
        // Validate all fields first
        if (!validateFields()) {
            return;
        }
        
        try {
            // Get values from fields
            String citizenship = citizenshipField.getText().trim();
            String phone = phoneField.getText().trim();
            String name = nameField.getText().trim();
            
            // Get gender
            String genderStr = (String) genderComboBox.getSelectedItem();
            Gender gender = Gender.valueOf(genderStr.toUpperCase());
            
            
            // Get location fields
            String province = (String) provinceComboBox.getSelectedItem();
            String district = (String) districtComboBox.getSelectedItem();
            String municipality = municipalityField.getText();
            String voteCenter = voteCenterField.getText();
            
            java.util.Date selectedDate = dobChooser.getDate();
            LocalDate dob = selectedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            // Call controller to add citizen
            successful = controller.addCitizenFromUI(
                citizenship, phone, name, gender,
                province, district, municipality,
                voteCenter, dob
            );
            
            // If successful, close dialog
            if (successful) {
                dispose();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding citizen: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_diagSaveBtnActionPerformed

    private void diadCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diadCancelBtnActionPerformed
        // TODO add your handling code here:
        // Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel? All entered data will be lost.",
            "Confirm Cancel",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            successful = false;
            dispose();
        }
    }//GEN-LAST:event_diadCancelBtnActionPerformed

    private void provinceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceComboBoxActionPerformed
        // TODO add your handling code here:
        updateDistrictComboBox();
    }//GEN-LAST:event_provinceComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DOBLabel;
    private javax.swing.JTextField citizenshipField;
    private javax.swing.JLabel citizenshipLabel;
    private javax.swing.JButton diadCancelBtn;
    private javax.swing.JPanel diagCenterPanel;
    private javax.swing.JLabel diagHeaderLabel;
    private javax.swing.JPanel diagHeaderPanel;
    private javax.swing.JButton diagSaveBtn;
    private javax.swing.JPanel diagfooterPanel;
    private javax.swing.JComboBox<String> districtComboBox;
    private javax.swing.JLabel districtLabel;
    private com.toedter.calendar.JDateChooser dobChooser;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JTextField municipalityField;
    private javax.swing.JLabel municipalityLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JComboBox<String> provinceComboBox;
    private javax.swing.JLabel provinceLabel;
    private javax.swing.JTextField voteCenterField;
    private javax.swing.JLabel votecenterLabel;
    // End of variables declaration//GEN-END:variables
}
