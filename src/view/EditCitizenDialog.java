package view;

import controller.CitizenController;
import model.CitizenModel;
import model.CitizenModel.Gender;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Dialog for editing existing citizen (all fields except status)
 * @author bijen
 */
public class EditCitizenDialog extends javax.swing.JDialog {
    
    private CitizenController controller;
    private CitizenModel citizenToEdit;
    private boolean successful = false;
    
    private JTextField citizenshipField;
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> genderComboBox;
    private com.toedter.calendar.JDateChooser dobChooser;
    private JComboBox<String> provinceComboBox;
    private JComboBox<String> districtComboBox;
    private JTextField municipalityField;
    private JTextField voteCenterField;
    private JButton saveBtn;
    private JButton cancelBtn;

    public EditCitizenDialog(java.awt.Frame parent, boolean modal, CitizenController controller, CitizenModel citizen) {
        super(parent, modal);
        this.controller = controller;
        this.citizenToEdit = citizen;
        initComponents();
        setLocationRelativeTo(parent);
        populateFields();
    }
    
    public boolean wasSuccessful() {
        return successful;
    }
    
    private void initComponents() {
        setTitle("Edit Citizen - " + citizenToEdit.getCitizenshipNumber());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 650));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        JLabel headerLabel = new JLabel("Edit Citizen Information");
        headerLabel.setFont(new Font("SimSun", Font.BOLD, 36));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);
        
        // Center Panel with form
        JPanel centerPanel = new JPanel(new GridLayout(9, 2, 20, 20));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Citizenship Number (Read-only)
        JLabel cidLabel = new JLabel("Citizenship Number:", SwingConstants.RIGHT);
        cidLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        citizenshipField = new JTextField();
        citizenshipField.setEditable(false);
        citizenshipField.setBackground(new Color(240, 240, 240));
        centerPanel.add(cidLabel);
        centerPanel.add(citizenshipField);
        
        // Name
        JLabel nameLabel = new JLabel("Full Name: *", SwingConstants.RIGHT);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField = new JTextField();
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        
        // Phone
        JLabel phoneLabel = new JLabel("Phone Number: *", SwingConstants.RIGHT);
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneField = new JTextField();
        centerPanel.add(phoneLabel);
        centerPanel.add(phoneField);
        
        // Gender
        JLabel genderLabel = new JLabel("Gender: *", SwingConstants.RIGHT);
        genderLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        centerPanel.add(genderLabel);
        centerPanel.add(genderComboBox);
        
        // DOB
        JLabel dobLabel = new JLabel("Date of Birth: *", SwingConstants.RIGHT);
        dobLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        dobChooser = new com.toedter.calendar.JDateChooser();
        centerPanel.add(dobLabel);
        centerPanel.add(dobChooser);
        
        // Province
        JLabel provinceLabel = new JLabel("Province: *", SwingConstants.RIGHT);
        provinceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        provinceComboBox = new JComboBox<>(new String[]{
            "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim"
        });
        provinceComboBox.addActionListener(e -> updateDistrictComboBox());
        centerPanel.add(provinceLabel);
        centerPanel.add(provinceComboBox);
        
        // District
        JLabel districtLabel = new JLabel("District: *", SwingConstants.RIGHT);
        districtLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        districtComboBox = new JComboBox<>();
        districtComboBox.addItem("-- Select District --");
        centerPanel.add(districtLabel);
        centerPanel.add(districtComboBox);
        
        // Municipality
        JLabel municipalityLabel = new JLabel("Municipality: *", SwingConstants.RIGHT);
        municipalityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        municipalityField = new JTextField();
        centerPanel.add(municipalityLabel);
        centerPanel.add(municipalityField);
        
        // Vote Center
        JLabel voteCenterLabel = new JLabel("Vote Center: *", SwingConstants.RIGHT);
        voteCenterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        voteCenterField = new JTextField();
        centerPanel.add(voteCenterLabel);
        centerPanel.add(voteCenterField);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Footer with buttons
        JPanel footerPanel = new JPanel(new GridLayout(1, 2, 50, 50));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        saveBtn = new JButton("Save Changes");
        saveBtn.setFont(new Font("Serif", Font.BOLD, 18));
        saveBtn.addActionListener(e -> handleSave());
        
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Serif", Font.BOLD, 18));
        cancelBtn.addActionListener(e -> handleCancel());
        
        footerPanel.add(saveBtn);
        footerPanel.add(cancelBtn);
        add(footerPanel, BorderLayout.SOUTH);
        
        pack();
    }
    
    private void populateFields() {
        citizenshipField.setText(citizenToEdit.getCitizenshipNumber());
        nameField.setText(citizenToEdit.getVoterName());
        phoneField.setText(citizenToEdit.getPhoneNumber());
        genderComboBox.setSelectedItem(citizenToEdit.getGender().getDisplay());
        
        // Set DOB
        LocalDate dob = citizenToEdit.getDateOfBirth();
        java.util.Date date = java.util.Date.from(dob.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        dobChooser.setDate(date);
        
        provinceComboBox.setSelectedItem(citizenToEdit.getProvince());
        updateDistrictComboBox();
        districtComboBox.setSelectedItem(citizenToEdit.getDistrict());
        municipalityField.setText(citizenToEdit.getMunicipality());
        voteCenterField.setText(citizenToEdit.getVoteCenter());
    }
    
    private void updateDistrictComboBox() {
        String selectedProvince = (String) provinceComboBox.getSelectedItem();
        districtComboBox.removeAllItems();
        districtComboBox.addItem("-- Select District --");
        
        if (selectedProvince == null) return;
        
        // Add districts based on province (same logic as AddCitizenDialog)
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
    
    private void handleSave() {
        // Validate and save
        try {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String genderStr = (String) genderComboBox.getSelectedItem();
            String province = (String) provinceComboBox.getSelectedItem();
            String district = (String) districtComboBox.getSelectedItem();
            String municipality = municipalityField.getText().trim();
            String voteCenter = voteCenterField.getText().trim();
            
            // Validation
            if (name.isEmpty() || phone.isEmpty() || municipality.isEmpty() || voteCenter.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!phone.matches("9\\d{9}")) {
                JOptionPane.showMessageDialog(this, "Phone must be 10 digits starting with 9!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Gender gender;
            switch (genderStr) {
                case "Male": gender = Gender.MALE; break;
                case "Female": gender = Gender.FEMALE; break;
                case "Other": gender = Gender.OTHER; break;
                default: throw new IllegalArgumentException("Invalid gender");
            }
            
            java.util.Date selectedDate = dobChooser.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Date of birth is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LocalDate dob = selectedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            // Update citizen object
            citizenToEdit.setVoterName(name);
            citizenToEdit.setPhoneNumber(phone);
            citizenToEdit.setGender(gender);
            citizenToEdit.setDateOfBirth(dob);
            citizenToEdit.setProvince(province);
            citizenToEdit.setDistrict(district);
            citizenToEdit.setMunicipality(municipality);
            citizenToEdit.setVoteCenter(voteCenter);
            
            successful = controller.updateCitizenFromUI(
                citizenToEdit.getCitizenshipNumber(),
                phone, name, gender, province, district, municipality, voteCenter, dob
            );
            
            if (successful) {
                dispose();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleCancel() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Discard changes?", "Confirm", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            successful = false;
            dispose();
        }
    }
}