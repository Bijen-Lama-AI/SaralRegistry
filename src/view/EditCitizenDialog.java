/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.CitizenController;
import controller.AdminController;
import model.CitizenModel;
import model.CitizenModel.Gender;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 *
 * @author bijen
 */
public class EditCitizenDialog extends javax.swing.JDialog {

    private CitizenController citizenController;
    private AdminController adminController;
    private CitizenModel citizenToEdit;
    private String originalCitizenshipNumber;
    private boolean successful = false;
    private boolean isAdminEdit = false;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditCitizenDialog.class.getName());

    /**
     * Creates new form EditCitizenDialog
     */
    public EditCitizenDialog(java.awt.Frame parent, boolean modal, AdminController adminController, CitizenController citizenController, CitizenModel citizen) {
        super(parent, modal);
        this.adminController = adminController;
        this.citizenController = citizenController;
        this.citizenToEdit = citizen;
        this.originalCitizenshipNumber = citizen.getCitizenshipNumber();
        this.isAdminEdit = true;
        initComponents();
        setLocationRelativeTo(parent);
        populateFields();
        setTitle("Edit Citizen - ADMIN");
    }

    public EditCitizenDialog(java.awt.Frame parent, boolean modal,
            CitizenController citizenController,
            CitizenModel citizen) {
        super(parent, modal);
        this.citizenController = citizenController;
        this.citizenToEdit = citizen;
        this.originalCitizenshipNumber = citizen.getCitizenshipNumber();
        this.isAdminEdit = false;
        initComponents();
        setLocationRelativeTo(parent);
        populateFields();
        setTitle("Edit Citizen - " + citizen.getCitizenshipNumber());
    }

    public boolean wasSuccessful() {
        return successful;
    }

    private void populateFields() {
        cidField.setText(citizenToEdit.getCitizenshipNumber());
        nameField.setText(citizenToEdit.getVoterName());
        phoneField.setText(citizenToEdit.getPhoneNumber());
        genderComboBox.setSelectedItem(citizenToEdit.getGender().getDisplay());

        LocalDate dob = citizenToEdit.getDateOfBirth();
        java.util.Date date = java.util.Date.from(
                dob.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        dobChooser.setDate(date);

        provinceComboBox.setSelectedItem(citizenToEdit.getProvince());
        updateDistrictComboBox();
        districtComboBox.setSelectedItem(citizenToEdit.getDistrict());
        municipalityField.setText(citizenToEdit.getMunicipality());
        voteCenterField.setText(citizenToEdit.getVoteCenter());

        // Display status with color
        String status = citizenToEdit.getStatus().getDisplay();
        statusDisplay.setText(status);
        statusDisplay.setFont(new Font("Segoe UI", Font.BOLD, 14));

        switch (citizenToEdit.getStatus()) {
            case PENDING:
                statusDisplay.setForeground(new Color(255, 165, 0));
                break;
            case APPROVED:
                statusDisplay.setForeground(new Color(40, 167, 69));
                break;
            case REJECTED:
                statusDisplay.setForeground(new Color(220, 53, 69));
                break;
        }
    }

    private void updateDistrictComboBox() {
        String selectedProvince = (String) provinceComboBox.getSelectedItem();
        districtComboBox.removeAllItems();

        if (selectedProvince == null) {
            return;
        }

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
        // Citizenship number validation
        String cid = cidField.getText().trim();
        if (cid.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Citizenship number is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            cidField.requestFocus();
            return false;
        }

        if (!cid.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "Citizenship number must contain only digits!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            cidField.requestFocus();
            return false;
        }

        if (cid.length() < 8 || cid.length() > 12) {
            JOptionPane.showMessageDialog(this,
                    "Citizenship number must be between 8 and 12 digits!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            cidField.requestFocus();
            return false;
        }

        // Name validation
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Name is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return false;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this,
                    "Name should contain only letters and spaces!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return false;
        }

        // Phone validation
        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Phone number is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneField.requestFocus();
            return false;
        }

        if (!phone.matches("9\\d{9}")) {
            JOptionPane.showMessageDialog(this,
                    "Phone must be 10 digits starting with 9!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneField.requestFocus();
            return false;
        }

        // Municipality validation
        if (municipalityField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Municipality is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            municipalityField.requestFocus();
            return false;
        }

        // Vote center validation
        if (voteCenterField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vote center is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            voteCenterField.requestFocus();
            return false;
        }

        // DOB validation
        java.util.Date selectedDate = dobChooser.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this,
                    "Date of birth is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Age validation
        LocalDate dob = selectedDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate eighteenYearsAgo = today.minusYears(18);
        LocalDate maxAge = today.minusYears(120);

        if (dob.isAfter(today)) {
            JOptionPane.showMessageDialog(this,
                    "Date of birth cannot be in the future!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (dob.isAfter(eighteenYearsAgo) || dob.isEqual(eighteenYearsAgo)) {
            JOptionPane.showMessageDialog(this,
                    "Citizen must be at least 18 years old!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (dob.isBefore(maxAge)) {
            JOptionPane.showMessageDialog(this,
                    "Age cannot exceed 120 years!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
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
        diagFooterPanel = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        diagCenterPanel = new javax.swing.JScrollPane();
        formPanel = new javax.swing.JPanel();
        cidLabel = new javax.swing.JLabel();
        cidField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        genderLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        dobLabel = new javax.swing.JLabel();
        dobChooser = new com.toedter.calendar.JDateChooser();
        provinceLabel = new javax.swing.JLabel();
        provinceComboBox = new javax.swing.JComboBox<>();
        districtLabel = new javax.swing.JLabel();
        districtComboBox = new javax.swing.JComboBox<>();
        municipalityLabel = new javax.swing.JLabel();
        municipalityField = new javax.swing.JTextField();
        voteCenterLabel = new javax.swing.JLabel();
        voteCenterField = new javax.swing.JTextField();
        statusLabel = new javax.swing.JLabel();
        statusDisplay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Citizen");

        diagHeaderPanel.setLayout(new java.awt.GridLayout(1, 1));

        diagHeaderLabel.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        diagHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagHeaderLabel.setText("Edit Citizen Information");
        diagHeaderPanel.add(diagHeaderLabel);

        getContentPane().add(diagHeaderPanel, java.awt.BorderLayout.PAGE_START);

        diagFooterPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 40, 10, 40));
        diagFooterPanel.setLayout(new java.awt.GridLayout(1, 2));

        saveBtn.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        saveBtn.setText("Save Changes");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        diagFooterPanel.add(saveBtn);

        cancelBtn.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        diagFooterPanel.add(cancelBtn);

        getContentPane().add(diagFooterPanel, java.awt.BorderLayout.PAGE_END);

        formPanel.setLayout(new java.awt.GridLayout(10, 2, 20, 20));

        cidLabel.setText("Citizenship Number:*");
        formPanel.add(cidLabel);
        formPanel.add(cidField);

        nameLabel.setText("Full Name:*");
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        phoneLabel.setText("Phone Number:*");
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        genderLabel.setText("Gender:*");
        formPanel.add(genderLabel);

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        formPanel.add(genderComboBox);

        dobLabel.setText("Date of Birth:*");
        formPanel.add(dobLabel);
        formPanel.add(dobChooser);

        provinceLabel.setText("Province:*");
        formPanel.add(provinceLabel);

        provinceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim" }));
        provinceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceComboBoxActionPerformed(evt);
            }
        });
        formPanel.add(provinceComboBox);

        districtLabel.setText("District:*");
        formPanel.add(districtLabel);

        formPanel.add(districtComboBox);

        municipalityLabel.setText("Municipality:*");
        formPanel.add(municipalityLabel);
        formPanel.add(municipalityField);

        voteCenterLabel.setText("Vote Center:*");
        formPanel.add(voteCenterLabel);
        formPanel.add(voteCenterField);

        statusLabel.setText("Current Status:*");
        formPanel.add(statusLabel);
        formPanel.add(statusDisplay);

        diagCenterPanel.setViewportView(formPanel);

        getContentPane().add(diagCenterPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if (!validateFields()) {
            return;
        }

        try {
            // Get the original citizenship number
            String originalCid = originalCitizenshipNumber;

            // Get current values from fields
            String newCid = cidField.getText().trim();
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String genderStr = (String) genderComboBox.getSelectedItem();
            String province = (String) provinceComboBox.getSelectedItem();
            String district = (String) districtComboBox.getSelectedItem();
            String municipality = municipalityField.getText().trim();
            String voteCenter = voteCenterField.getText().trim();

            // Convert gender string to enum
            Gender gender;
            switch (genderStr) {
                case "Male":
                    gender = Gender.MALE;
                    break;
                case "Female":
                    gender = Gender.FEMALE;
                    break;
                case "Other":
                    gender = Gender.OTHER;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid gender selected");
            }

            // Get date of birth
            java.util.Date selectedDate = dobChooser.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this,
                        "Date of birth is required!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            LocalDate dob = selectedDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            // Determine which controller to use based on how dialog was created
            boolean success;
            if (isAdminEdit && adminController != null) {
                // Admin is editing - use AdminController
                success = adminController.updateCitizenWithCidChange(
                        originalCid, newCid, phone, name, gender,
                        province, district, municipality, voteCenter, dob
                );
            } else {
                // Citizen is editing their own record - use CitizenController
                success = citizenController.updateMyRecordWithCidChange(
                        originalCid, newCid, phone, name, gender,
                        province, district, municipality, voteCenter, dob
                );
            }

            if (success) {
                successful = true;
                JOptionPane.showMessageDialog(this,
                        "Citizen information updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input: " + e.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            logger.warning("Validation error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error updating citizen: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel?\nAll unsaved changes will be lost.",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            successful = false;
            dispose();
        }
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void provinceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceComboBoxActionPerformed
        // TODO add your handling code here:
        updateDistrictComboBox();
    }//GEN-LAST:event_provinceComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField cidField;
    private javax.swing.JLabel cidLabel;
    private javax.swing.JScrollPane diagCenterPanel;
    private javax.swing.JPanel diagFooterPanel;
    private javax.swing.JLabel diagHeaderLabel;
    private javax.swing.JPanel diagHeaderPanel;
    private javax.swing.JComboBox<String> districtComboBox;
    private javax.swing.JLabel districtLabel;
    private com.toedter.calendar.JDateChooser dobChooser;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JPanel formPanel;
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
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel statusDisplay;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField voteCenterField;
    private javax.swing.JLabel voteCenterLabel;
    // End of variables declaration//GEN-END:variables
}
