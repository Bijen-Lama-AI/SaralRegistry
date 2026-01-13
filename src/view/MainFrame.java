/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;
import controller.AdminController;
import controller.CitizenController;
import java.util.Collections;
import javax.swing.table.*;
import model.AdminModel;
import model.AdminRegistryModel;
import model.CitizenModel;
import model.CitizenRegistryModel;
import java.time.*;
import javax.swing.Timer;
import java.time.format.*;
import model.ActionHistory;
import controller.CitizenSorter;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import model.CitizenModel.Gender;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author bijen
 */
public class MainFrame extends javax.swing.JFrame {
    
    private AdminController adminController;
    private CitizenController citizenController;
    
    private AdminRegistryModel adminRegistry;
    private CitizenRegistryModel citizenRegistry;
    
    private Timer carouselTimer;
    private int carouselIndex = 0;
    private String[] carouselMessages = {"📊 Total Citizens: 0", "✅ Approved: 0", "⏳ Pending: 0", "🎯 System Active"};
    
    private void loadInitialData() {
        try {
            citizenRegistry.addCitizen(new CitizenModel("5123574412", "9824370845", "Bijen Lama", Gender.MALE, "Bagmati", "Kathmandu", "KMC", "Swayambhu", LocalDate.of(2005, 5, 25)));
            citizenRegistry.addCitizen(new CitizenModel("7348921567", "9841234987", "Sita Sharma", Gender.FEMALE, "Bagmati", "Lalitpur", "Lalitpur Metropolitan City", "Patan Durbar", LocalDate.of(1998, 3, 12)));
            citizenRegistry.addCitizen(new CitizenModel("3298745123", "9852345671", "Rajesh Gurung", Gender.MALE, "Gandaki", "Kaski", "Pokhara Metropolitan City", "Pokhara City Hall", LocalDate.of(1987, 7, 18)));
            citizenRegistry.addCitizen(new CitizenModel("6451238976", "9814567123", "Maya Tamang", Gender.FEMALE, "Bagmati", "Bhaktapur", "Bhaktapur Municipality", "Bhaktapur Square", LocalDate.of(1992, 11, 5)));
            citizenRegistry.addCitizen(new CitizenModel("8912345678", "9825678234", "Krishna Shrestha", Gender.MALE, "Bagmati", "Kathmandu", "Kirtipur Municipality", "Kirtipur Hall", LocalDate.of(1978, 2, 28)));
            citizenRegistry.addCitizen(new CitizenModel("4123567890", "9836789345", "Bimala Rai", Gender.FEMALE, "Province 1", "Jhapa", "Birtamod Municipality", "Birtamod Center", LocalDate.of(1985, 9, 15)));
            citizenRegistry.addCitizen(new CitizenModel("6789123456", "9847890456", "Rajendra Yadav", Gender.MALE, "Madhesh", "Sarlahi", "Malangwa Municipality", "Malangwa School", LocalDate.of(1990, 4, 22)));
            citizenRegistry.addCitizen(new CitizenModel("9234567812", "9858901567", "Sunita Magar", Gender.FEMALE, "Gandaki", "Syangja", "Putalibazar Municipality", "Syangja Office", LocalDate.of(1983, 8, 30)));
            citizenRegistry.addCitizen(new CitizenModel("1567892345", "9869012678", "Mohan Basnet", Gender.MALE, "Koshi", "Sunsari", "Dharan Sub-metropolitan", "Dharan Civic Center", LocalDate.of(1975, 12, 10)));
            citizenRegistry.addCitizen(new CitizenModel("7891234560", "9870123789", "Gita Limbu", Gender.FEMALE, "Province 1", "Ilam", "Ilam Municipality", "Ilam Tea Garden", LocalDate.of(2000, 6, 8)));
            citizenRegistry.addCitizen(new CitizenModel("2345678910", "9881234890", "Kumar Lama", Gender.MALE, "Bagmati", "Nuwakot", "Bidur Municipality", "Nuwakot Office", LocalDate.of(1965, 1, 14)));
            citizenRegistry.addCitizen(new CitizenModel("5678901234", "9892345901", "Sabina Karki", Gender.FEMALE, "Gandaki", "Tanahun", "Damauli Municipality", "Tanahun Hall", LocalDate.of(1995, 10, 3)));
            citizenRegistry.addCitizen(new CitizenModel("8901234567", "9803456012", "Bikash Chhetri", Gender.MALE, "Lumbini", "Rupandehi", "Butwal Sub-metropolitan", "Butwal Center", LocalDate.of(1988, 7, 19)));
            citizenRegistry.addCitizen(new CitizenModel("1234567899", "9814567123", "Anjali Adhikari", Gender.FEMALE, "Bagmati", "Dhading", "Nilkantha Municipality", "Dhading Office", LocalDate.of(1993, 3, 25)));
            citizenRegistry.addCitizen(new CitizenModel("4567890123", "9825678234", "Narendra Rana", Gender.MALE, "Karnali", "Surkhet", "Birendranagar Municipality", "Surkhet Hall", LocalDate.of(1955, 11, 11)));
            citizenRegistry.addCitizen(new CitizenModel("7890123456", "9836789345", "Purnima Shah", Gender.FEMALE, "Bagmati", "Kathmandu", "Budhanilkantha Municipality", "Budhanilkantha School", LocalDate.of(2002, 2, 17)));
            citizenRegistry.addCitizen(new CitizenModel("0123456789", "9847890456", "Santosh Thakuri", Gender.MALE, "Sudurpashchim", "Kailali", "Dhangadhi Sub-metropolitan", "Dhangadhi Stadium", LocalDate.of(1991, 9, 5)));
            citizenRegistry.addCitizen(new CitizenModel("3456789012", "9858901567", "Rekha Bhandari", Gender.FEMALE, "Gandaki", "Lamjung", "Besisahar Municipality", "Lamjung Office", LocalDate.of(1980, 4, 12)));
            citizenRegistry.addCitizen(new CitizenModel("6789012345", "9869012678", "Dipak Poudel", Gender.MALE, "Lumbini", "Palpa", "Tansen Municipality", "Tansen Palace", LocalDate.of(1972, 6, 22)));
            citizenRegistry.addCitizen(new CitizenModel("9012345678", "9870123789", "Sarita Ghimire", Gender.FEMALE, "Bagmati", "Makwanpur", "Hetauda Sub-metropolitan", "Hetauda City Hall", LocalDate.of(1998, 8, 14)));
            citizenRegistry.addCitizen(new CitizenModel("2345678901", "9881234890", "Raj Kumar Mahato", Gender.MALE, "Madhesh", "Siraha", "Siraha Municipality", "Siraha Center", LocalDate.of(1986, 12, 30)));
            citizenRegistry.addCitizen(new CitizenModel("5678901235", "9892345901", "Laxmi Koirala", Gender.FEMALE, "Bagmati", "Chitwan", "Bharatpur Metropolitan", "Bharatpur Hospital", LocalDate.of(1978, 5, 18)));
            citizenRegistry.addCitizen(new CitizenModel("8901234568", "9803456012", "Bishnu Prasad Dahal", Gender.MALE, "Bagmati", "Kathmandu", "Gokarneshwar Municipality", "Gokarna Temple", LocalDate.of(1960, 10, 31)));
            citizenRegistry.addCitizen(new CitizenModel("1234567891", "9814567123", "Saraswoti Gautam", Gender.FEMALE, "Lumbini", "Nawalpur", "Kawasoti Municipality", "Nawalpur Center", LocalDate.of(1994, 1, 9)));
            citizenRegistry.addCitizen(new CitizenModel("4567890124", "9825678234", "Tek Bahadur Bishwakarma", Gender.MALE, "Sudurpashchim", "Kanchanpur", "Bhimdatta Municipality", "Mahendranagar Hall", LocalDate.of(1989, 7, 23)));
            citizenRegistry.addCitizen(new CitizenModel("7890123457", "9836789345", "Mina Pariyar", Gender.FEMALE, "Gandaki", "Gorkha", "Gorkha Municipality", "Gorkha Durbar", LocalDate.of(2003, 3, 16)));
            citizenRegistry.addCitizen(new CitizenModel("0123456790", "9847890456", "Ashok Kumar Singh", Gender.MALE, "Madhesh", "Dhanusha", "Janakpur Sub-metropolitan", "Janaki Temple", LocalDate.of(1992, 11, 8)));
            citizenRegistry.addCitizen(new CitizenModel("3456789013", "9858901567", "Radhika Jha", Gender.FEMALE, "Madhesh", "Mahottari", "Bardibas Municipality", "Bardibas Center", LocalDate.of(1984, 6, 27)));
            citizenRegistry.addCitizen(new CitizenModel("6789012346", "9869012678", "Buddhi Man Tamrakar", Gender.MALE, "Bagmati", "Kathmandu", "Tokha Municipality", "Tokha City Hall", LocalDate.of(1970, 9, 2)));
            citizenRegistry.addCitizen(new CitizenModel("9012345679", "9870123789", "Kamala Siwakoti", Gender.FEMALE, "Bagmati", "Kavrepalanchok", "Dhulikhel Municipality", "Dhulikhel Hospital", LocalDate.of(1996, 4, 19)));
            citizenRegistry.addCitizen(new CitizenModel("2345678902", "9881234890", "Nabin K.C.", Gender.MALE, "Gandaki", "Manang", "Chame Rural Municipality", "Manang Office", LocalDate.of(1981, 12, 24)));
            citizenRegistry.addCitizen(new CitizenModel("5678901236", "9892345901", "Goma Ale", Gender.FEMALE, "Province 1", "Morang", "Biratnagar Metropolitan", "Biratnagar Stadium", LocalDate.of(2001, 8, 7)));
            citizenRegistry.addCitizen(new CitizenModel("8901234569", "9803456012", "Ram Prasad Sharma", Gender.MALE, "Lumbini", "Kapilvastu", "Kapilvastu Municipality", "Lumbini Center", LocalDate.of(1968, 2, 13)));
            citizenRegistry.addCitizen(new CitizenModel("1234567892", "9814567123", "Sujata Khatri", Gender.FEMALE, "Bagmati", "Sindhuli", "Kamalamai Municipality", "Sindhuli Office", LocalDate.of(1997, 10, 21)));
            citizenRegistry.addCitizen(new CitizenModel("4567890125", "9825678234", "Harka Sampang", Gender.MALE, "Karnali", "Humla", "Simkot Rural Municipality", "Humla District Center", LocalDate.of(1983, 5, 4)));
            citizenRegistry.addCitizen(new CitizenModel("7890123458", "9836789345", "Dilmaya Gurung", Gender.FEMALE, "Gandaki", "Mustang", "Jomsom Municipality", "Jomsom Airport", LocalDate.of(1976, 1, 29)));
            citizenRegistry.addCitizen(new CitizenModel("0123456791", "9847890456", "Jitendra Thakur", Gender.MALE, "Madhesh", "Saptari", "Rajbiraj Municipality", "Saptari Hall", LocalDate.of(1990, 7, 15)));
            citizenRegistry.addCitizen(new CitizenModel("3456789014", "9858901567", "Kabita Mandal", Gender.FEMALE, "Madhesh", "Rautahat", "Gaur Municipality", "Rautahat Center", LocalDate.of(1995, 12, 3)));
            citizenRegistry.addCitizen(new CitizenModel("6789012347", "9869012678", "Shyam Kumar Ojha", Gender.MALE, "Sudurpashchim", "Dadeldhura", "Amargadhi Municipality", "Dadeldhura Office", LocalDate.of(1963, 9, 26)));
            citizenRegistry.addCitizen(new CitizenModel("9012345680", "9870123789", "Rina Devi", Gender.FEMALE, "Province 1", "Udayapur", "Triyuga Municipality", "Udayapur Center", LocalDate.of(1988, 3, 14)));
            citizenRegistry.addCitizen(new CitizenModel("2345678903", "9881234890", "Dhan Bahadur Ghale", Gender.MALE, "Bagmati", "Rasuwa", "Dhunche Municipality", "Rasuwa Office", LocalDate.of(1974, 11, 8)));
            citizenRegistry.addCitizen(new CitizenModel("5678901237", "9892345901", "Anita Bhujel", Gender.FEMALE, "Bagmati", "Sindhupalchok", "Chautara Municipality", "Sindhupalchok Hall", LocalDate.of(2004, 6, 30)));
            citizenRegistry.addCitizen(new CitizenModel("8901234570", "9803456012", "Keshav Prasad Neupane", Gender.MALE, "Lumbini", "Arghakhanchi", "Sandhikharka Municipality", "Arghakhanchi Center", LocalDate.of(1958, 4, 17)));
            citizenRegistry.addCitizen(new CitizenModel("1234567893", "9814567123", "Rupa Kumari", Gender.FEMALE, "Karnali", "Jumla", "Chandannath Municipality", "Jumla Office", LocalDate.of(1982, 10, 12)));
            citizenRegistry.addCitizen(new CitizenModel("4567890126", "9825678234", "Surya Bahadur Thapa Magar", Gender.MALE, "Gandaki", "Parbat", "Kusma Municipality", "Parbat Hall", LocalDate.of(1979, 2, 6)));
            citizenRegistry.addCitizen(new CitizenModel("7890123459", "9836789345", "Prabina Chaudhary", Gender.FEMALE, "Lumbini", "Banke", "Nepalgunj Sub-metropolitan", "Nepalgunj City Hall", LocalDate.of(2000, 1, 23)));
            citizenRegistry.addCitizen(new CitizenModel("0123456792", "9847890456", "Arjun Kumar Sah", Gender.MALE, "Madhesh", "Bara", "Kalaiya Sub-metropolitan", "Bara District Office", LocalDate.of(1993, 8, 9)));
            citizenRegistry.addCitizen(new CitizenModel("3456789015", "9858901567", "Sushila Rijal", Gender.FEMALE, "Bagmati", "Kathmandu", "Chandragiri Municipality", "Chandragiri Hills", LocalDate.of(1977, 5, 28)));
            citizenRegistry.addCitizen(new CitizenModel("6789012348", "9869012678", "Yubaraj Khatiwada", Gender.MALE, "Bagmati", "Kathmandu", "Tarakeshwar Municipality", "Tarakeshwar Temple", LocalDate.of(1962, 12, 15)));
            citizenRegistry.addCitizen(new CitizenModel("9012345681", "9870123789", "Bishnu Maya Gharti", Gender.FEMALE, "Lumbini", "Rolpa", "Liwang Municipality", "Rolpa Center", LocalDate.of(1991, 4, 5)));
            
            if (adminController != null) {
                adminController.refreshQueue();
            }
            
        } catch(IllegalArgumentException e) {
            showError("Error loading initial data: " + e.getMessage());
        } catch (Exception e) {
            showError("Unexpected error occured during loadind data: " + e.getMessage());
        }
    }
    
    private void startCarousel() {
        updateCarouselMessages();
        carouselTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carouselIndex++;
                
                if (carouselIndex >= carouselMessages.length) {
                    carouselIndex = 0;
                }
                
                if (carouselLabel != null) {
                    carouselLabel.setText(carouselMessages[carouselIndex]);
                }
                
                updateCarouselMessages();
            }
        });
        
        carouselTimer.start();
    }
    
    private void updateCarouselMessages() {
        try {
            int total = adminController.getTotalCitizenCount();
            int approved = adminController.countByStatus(CitizenModel.RegistrationStatus.APPROVED);
            int pending = adminController.countByStatus(CitizenModel.RegistrationStatus.PENDING);
            
            carouselMessages[0] = "📊 Total: " + total;
            carouselMessages[1] = "✅ Approved: " + approved;
            carouselMessages[2] = "⏳ Pending: " + pending;
            carouselMessages[3] = "🎯 Queue: " + adminController.getQueueSize();
        } catch (Exception e) {
            showError("Unexpected Error Occured" + e.getMessage());
        }  
    }
    
    private void refreshCitizensTable() {
        try{
           DefaultTableModel model = (DefaultTableModel) citizensTable.getModel();
            model.setRowCount(0);
            for (CitizenModel c : citizenRegistry.getAllCitizens()) {
                model.addRow(new Object[]{
                    c.getCitizenshipNumber(),
                    c.getVoterName(),
                    c.getAge(),
                    c.getPhoneNumber(),
                    c.getProvince(),
                    c.getStatus().getDisplay()
                });
            } 
        } catch (Exception e) {
            showError("Error refreshing table: " + e.getMessage());
        }
    }
    
    private void refreshDashboard() {
        try {
            updateCarouselMessages();
        
            updateDashboardPanel(dashboardPanel1, "📋 Pending", 
            String.valueOf(adminController.countByStatus(CitizenModel.RegistrationStatus.PENDING)),
            new Color(255, 165, 0));
            
            updateDashboardPanel(dashboardPanel2, "✅ Approved",
            String.valueOf(adminController.countByStatus(CitizenModel.RegistrationStatus.APPROVED)),
            new Color(40, 167, 69));
            
            updateDashboardPanel(dashboardPanel3, "❌ Rejected",
            String.valueOf(adminController.countByStatus(CitizenModel.RegistrationStatus.REJECTED)),
            new Color(220, 53, 69));
            
            updateDashboardPanel(dashboardPanel4, "👥 Total",
            String.valueOf(adminController.getTotalCitizenCount()),
            new Color(0, 123, 255));
            
            updateDashboardPanel(dashboardPanel5, "📋 Queue",
            String.valueOf(adminController.getQueueSize()),
            new Color(108, 117, 125));
            
            updateDashboardPanel(dashboardPanel6, "📚 History",
            String.valueOf(adminController.getHistorySize()),
            new Color(23, 162, 184));
        } catch (Exception e) {
            showError("Error refreshing dashboard: " + e.getMessage());
        }      
    }
    
    private CitizenModel binarySearchById(ArrayList<CitizenModel> citizens, String cid) {
        int left = 0, right = citizens.size() -1;
        long searchId = Long.parseLong(cid);
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long midId = Long.parseLong(citizens.get(mid).getCitizenshipNumber());
            
            if (midId == searchId) return citizens.get(mid);
            if (midId < searchId) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
    
    private void selectionSortByName(ArrayList<CitizenModel> citizens) {
        for (int i = 0; i < citizens.size() -1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < citizens.size(); j++) {
                if (citizens.get(j).getVoterName().compareToIgnoreCase(citizens.get(minIdx).getVoterName()) < 0) {
                    minIdx = j;
                }
            }
            CitizenModel temp = citizens.get(i);
            citizens.set(i, citizens.get(minIdx));
            citizens.set(minIdx, temp);
        }
    }
    private void loadPendingQueue() {
        try {
            adminController.refreshQueue();
        } catch (Exception e) {
            showError("Error loading queue: " + e.getMessage());
        }
    }
    
    private void updateDashboardPanel(JPanel panel, String title, String value, Color color) {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.setBackground(color);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SimSum", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("SimSun", Font.BOLD, 36));
        valueLabel.setForeground(Color.WHITE);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
    
    private void clearLoginFields() {
        if (adminIdField != null) {
            adminIdField.setText("");
        }
        if (adminPasswordField != null) {
            adminPasswordField.setText("");
        }
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    /**
     * Creates new form MainMainFrame
     */
    public MainFrame() {
        adminRegistry = new AdminRegistryModel();
        citizenRegistry = new CitizenRegistryModel();
        
        adminController = new AdminController(adminRegistry, citizenRegistry, this);
        citizenController = new CitizenController(citizenRegistry, this);
                
        initComponents();
        
        loadInitialData();
        refreshCitizensTable();
        refreshDashboard();
        startCarousel();
        sortTableInitial();
        
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "loading");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        searchOptionGroup = new javax.swing.ButtonGroup();
        sortSelectionGroup = new javax.swing.ButtonGroup();
        sortChoiceGroup = new javax.swing.ButtonGroup();
        cardPanel = new javax.swing.JPanel();
        loadingPanel = new view.BackgroundPanel("/assets/images/BackgroundImage.jpg", view.BackgroundPanel.ScaleMode.FILL);
        quoteLabel = new javax.swing.JLabel();
        loadingBar = new javax.swing.JProgressBar();
        authorLabel = new javax.swing.JLabel();
        loadingLabel = new javax.swing.JLabel();
        loadingValue = new javax.swing.JLabel();
        mainContentPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        logoPanel = new view.BackgroundPanel("/assets/images/Logo.png", view.BackgroundPanel.ScaleMode.FILL);
        headerContainer = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        subHeaderLabel = new javax.swing.JLabel();
        footerPanel = new javax.swing.JPanel();
        footerLabel1 = new javax.swing.JLabel();
        footerLabel2 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        selectRolePanel = new javax.swing.JPanel();
        selectRoleLabel = new javax.swing.JLabel();
        adminImg = new view.BackgroundPanel("/assets/images/admin.png", view.BackgroundPanel.ScaleMode.FILL);
        citizenImg = new view.BackgroundPanel("/assets/images/register.png", view.BackgroundPanel.ScaleMode.FILL);
        adminChoiceBtn = new javax.swing.JButton();
        citizenChoiceBtn = new javax.swing.JButton();
        adminLoginPanel = new javax.swing.JPanel();
        protectionImg = new view.BackgroundPanel("/assets/images/protection.png", view.BackgroundPanel.ScaleMode.FILL);
        adminIdLabel = new javax.swing.JLabel();
        adminIdField = new javax.swing.JTextField();
        adminPasswordLabel = new javax.swing.JLabel();
        adminPasswordField = new javax.swing.JPasswordField();
        adminLoginLabel = new javax.swing.JLabel();
        forgetBtn = new javax.swing.JButton();
        logInBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        adminDashboardPanel = new javax.swing.JPanel();
        adminHeaderPanel = new javax.swing.JPanel();
        adminheaderLeft = new javax.swing.JPanel();
        welcomeAdmin = new javax.swing.JLabel();
        exitPanel = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();
        adminHeaderCenter = new javax.swing.JPanel();
        carouselLabel = new javax.swing.JLabel();
        adminDashboardPane = new javax.swing.JTabbedPane();
        dashboardPane = new javax.swing.JPanel();
        statsLabel = new javax.swing.JLabel();
        dashboardFooterPanel = new javax.swing.JPanel();
        searchManageBtn = new javax.swing.JButton();
        dashboardCenterPanel = new javax.swing.JPanel();
        dashboardPanel1 = new javax.swing.JPanel();
        dashboardPanel2 = new javax.swing.JPanel();
        dashboardPanel3 = new javax.swing.JPanel();
        dashboardPanel4 = new javax.swing.JPanel();
        dashboardPanel5 = new javax.swing.JPanel();
        dashboardPanel6 = new javax.swing.JPanel();
        adminManagePane = new javax.swing.JPanel();
        manageCitizenLabel = new javax.swing.JLabel();
        manageCitizenFooterPanel = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        approveBtn = new javax.swing.JButton();
        rejectBtn = new javax.swing.JButton();
        refreshManageBtn = new javax.swing.JButton();
        citizenRecordPane = new javax.swing.JScrollPane();
        citizensTable = new javax.swing.JTable();
        SearchPane = new javax.swing.JPanel();
        searchHeader = new javax.swing.JPanel();
        citizenshipNoBtn = new javax.swing.JRadioButton();
        nameBtn = new javax.swing.JRadioButton();
        phoneNumberBtn = new javax.swing.JRadioButton();
        searchField = new javax.swing.JTextField();
        linearSearchBtn = new javax.swing.JButton();
        binarySearchBtn = new javax.swing.JButton();
        searchScrollPane = new javax.swing.JScrollPane();
        detailsTextarea = new javax.swing.JTextArea();
        sortPane = new javax.swing.JPanel();
        sortHeader = new javax.swing.JPanel();
        citizenshipnoSortBtn = new javax.swing.JRadioButton();
        nameSortBtn = new javax.swing.JRadioButton();
        ageSortBtn = new javax.swing.JRadioButton();
        sortChoiceBtn1 = new javax.swing.JRadioButton();
        sortChoiceBtn2 = new javax.swing.JRadioButton();
        sortBtn = new javax.swing.JButton();
        resetSortBtn = new javax.swing.JButton();
        sortCenterPanel = new javax.swing.JPanel();
        sortCenterPane = new javax.swing.JScrollPane();
        sortTable = new javax.swing.JTable();
        queuePane = new javax.swing.JPanel();
        queuePaneHeader = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        queueTextArea = new javax.swing.JTextArea();
        historyPane = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Saral Registry");

        cardPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        cardPanel.setLayout(new java.awt.CardLayout());

        loadingPanel.setLayout(new java.awt.GridBagLayout());

        quoteLabel.setFont(new java.awt.Font("Viner Hand ITC", 1, 50)); // NOI18N
        quoteLabel.setText("Be The Nation's Voice");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        loadingPanel.add(quoteLabel, gridBagConstraints);

        loadingBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 255), 2, true));
        loadingBar.setMinimumSize(new java.awt.Dimension(200, 10));
        loadingBar.setName(""); // NOI18N
        loadingBar.setPreferredSize(new java.awt.Dimension(200, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        loadingPanel.add(loadingBar, gridBagConstraints);

        authorLabel.setFont(new java.awt.Font("Viner Hand ITC", 2, 48)); // NOI18N
        authorLabel.setText("- Saral Registry");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        loadingPanel.add(authorLabel, gridBagConstraints);

        loadingLabel.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        loadingLabel.setText("Loading...........");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        loadingPanel.add(loadingLabel, gridBagConstraints);

        loadingValue.setFont(new java.awt.Font("Vivaldi", 0, 18)); // NOI18N
        loadingValue.setText("0%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        loadingPanel.add(loadingValue, gridBagConstraints);

        cardPanel.add(loadingPanel, "loading");

        mainContentPanel.setMinimumSize(new java.awt.Dimension(900, 500));
        mainContentPanel.setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(220, 20, 20));
        headerPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        headerPanel.add(logoPanel);

        headerContainer.setBackground(new java.awt.Color(220, 20, 20));
        headerContainer.setLayout(new java.awt.GridBagLayout());

        headerLabel.setBackground(new java.awt.Color(220, 20, 20));
        headerLabel.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setText("National Voter Registration Portal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        headerContainer.add(headerLabel, gridBagConstraints);

        subHeaderLabel.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        subHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        subHeaderLabel.setText("Election Commission");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        headerContainer.add(subHeaderLabel, gridBagConstraints);

        headerPanel.add(headerContainer);

        mainContentPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        footerPanel.setBackground(new java.awt.Color(0, 56, 147));
        footerPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        footerPanel.setLayout(new java.awt.GridBagLayout());

        footerLabel1.setFont(new java.awt.Font("Serif", 1, 30)); // NOI18N
        footerLabel1.setForeground(new java.awt.Color(255, 255, 255));
        footerLabel1.setText("Email: system@electioncommission.gov");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        footerPanel.add(footerLabel1, gridBagConstraints);

        footerLabel2.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        footerLabel2.setForeground(new java.awt.Color(255, 255, 255));
        footerLabel2.setText("Contact: +977 01 5553000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        footerPanel.add(footerLabel2, gridBagConstraints);

        mainContentPanel.add(footerPanel, java.awt.BorderLayout.PAGE_END);

        contentPanel.setLayout(new java.awt.CardLayout());

        selectRolePanel.setBackground(new java.awt.Color(255, 255, 255));
        selectRolePanel.setPreferredSize(new java.awt.Dimension(900, 500));
        selectRolePanel.setLayout(new java.awt.GridBagLayout());

        selectRoleLabel.setFont(new java.awt.Font("SimSun", 1, 48)); // NOI18N
        selectRoleLabel.setText("Select Your Role");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        selectRolePanel.add(selectRoleLabel, gridBagConstraints);

        adminImg.setBackground(new java.awt.Color(255, 255, 255));
        adminImg.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        adminImg.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout adminImgLayout = new javax.swing.GroupLayout(adminImg);
        adminImg.setLayout(adminImgLayout);
        adminImgLayout.setHorizontalGroup(
            adminImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        adminImgLayout.setVerticalGroup(
            adminImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        selectRolePanel.add(adminImg, gridBagConstraints);

        citizenImg.setBackground(new java.awt.Color(255, 255, 255));
        citizenImg.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        citizenImg.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout citizenImgLayout = new javax.swing.GroupLayout(citizenImg);
        citizenImg.setLayout(citizenImgLayout);
        citizenImgLayout.setHorizontalGroup(
            citizenImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        citizenImgLayout.setVerticalGroup(
            citizenImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        selectRolePanel.add(citizenImg, gridBagConstraints);

        adminChoiceBtn.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        adminChoiceBtn.setText("Admin ");
        adminChoiceBtn.setName(""); // NOI18N
        adminChoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminChoiceBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        selectRolePanel.add(adminChoiceBtn, gridBagConstraints);

        citizenChoiceBtn.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        citizenChoiceBtn.setText("Citizen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        selectRolePanel.add(citizenChoiceBtn, gridBagConstraints);

        contentPanel.add(selectRolePanel, "selectRole");

        adminLoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminLoginPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        adminLoginPanel.setLayout(new java.awt.GridBagLayout());

        protectionImg.setBackground(new java.awt.Color(255, 255, 255));
        protectionImg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        protectionImg.setPreferredSize(new java.awt.Dimension(250, 200));

        javax.swing.GroupLayout protectionImgLayout = new javax.swing.GroupLayout(protectionImg);
        protectionImg.setLayout(protectionImgLayout);
        protectionImgLayout.setHorizontalGroup(
            protectionImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        protectionImgLayout.setVerticalGroup(
            protectionImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 64);
        adminLoginPanel.add(protectionImg, gridBagConstraints);

        adminIdLabel.setFont(new java.awt.Font("Thames", 0, 36)); // NOI18N
        adminIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminIdLabel.setText("Admin Id");
        adminIdLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        adminLoginPanel.add(adminIdLabel, gridBagConstraints);

        adminIdField.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        adminLoginPanel.add(adminIdField, gridBagConstraints);

        adminPasswordLabel.setFont(new java.awt.Font("Thames", 0, 36)); // NOI18N
        adminPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminPasswordLabel.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        adminLoginPanel.add(adminPasswordLabel, gridBagConstraints);

        adminPasswordField.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        adminLoginPanel.add(adminPasswordField, gridBagConstraints);

        adminLoginLabel.setFont(new java.awt.Font("Thames", 1, 100)); // NOI18N
        adminLoginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLoginLabel.setText("Admin Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 25, 0);
        adminLoginPanel.add(adminLoginLabel, gridBagConstraints);

        forgetBtn.setBackground(new java.awt.Color(242, 242, 242));
        forgetBtn.setText("Forget Password? Click Here");
        forgetBtn.setBorder(null);
        forgetBtn.setBorderPainted(false);
        forgetBtn.setContentAreaFilled(false);
        forgetBtn.setPreferredSize(new java.awt.Dimension(165, 30));
        forgetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgetBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        adminLoginPanel.add(forgetBtn, gridBagConstraints);

        logInBtn.setText("Log In");
        logInBtn.setPreferredSize(new java.awt.Dimension(150, 30));
        logInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        adminLoginPanel.add(logInBtn, gridBagConstraints);

        backBtn.setText("Back");
        backBtn.setPreferredSize(new java.awt.Dimension(150, 30));
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        adminLoginPanel.add(backBtn, gridBagConstraints);

        contentPanel.add(adminLoginPanel, "adminLogin");

        adminDashboardPanel.setLayout(new java.awt.BorderLayout());

        adminHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        adminHeaderPanel.setPreferredSize(new java.awt.Dimension(0, 120));
        adminHeaderPanel.setLayout(new java.awt.BorderLayout());

        adminheaderLeft.setBackground(new java.awt.Color(255, 255, 255));

        welcomeAdmin.setFont(new java.awt.Font("SimSun", 1, 28)); // NOI18N
        welcomeAdmin.setText("Welcome, Admin!");
        adminheaderLeft.add(welcomeAdmin);

        adminHeaderPanel.add(adminheaderLeft, java.awt.BorderLayout.LINE_START);

        exitPanel.setBackground(new java.awt.Color(255, 255, 255));

        logoutBtn.setBackground(new java.awt.Color(255, 51, 51));
        logoutBtn.setText("Logout");
        logoutBtn.setPreferredSize(new java.awt.Dimension(72, 30));
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        exitPanel.add(logoutBtn);

        adminHeaderPanel.add(exitPanel, java.awt.BorderLayout.LINE_END);

        adminHeaderCenter.setBackground(new java.awt.Color(255, 255, 255));
        adminHeaderCenter.setLayout(new java.awt.GridBagLayout());

        carouselLabel.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        carouselLabel.setText("Loading.......");
        adminHeaderCenter.add(carouselLabel, new java.awt.GridBagConstraints());

        adminHeaderPanel.add(adminHeaderCenter, java.awt.BorderLayout.CENTER);

        adminDashboardPanel.add(adminHeaderPanel, java.awt.BorderLayout.PAGE_START);

        adminDashboardPane.setBackground(new java.awt.Color(255, 255, 255));

        dashboardPane.setLayout(new java.awt.BorderLayout());

        statsLabel.setBackground(new java.awt.Color(255, 255, 255));
        statsLabel.setFont(new java.awt.Font("SimSun", 1, 28)); // NOI18N
        statsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsLabel.setText("Statistics Overview");
        dashboardPane.add(statsLabel, java.awt.BorderLayout.PAGE_START);

        dashboardFooterPanel.setBackground(new java.awt.Color(255, 255, 255));
        dashboardFooterPanel.setLayout(new java.awt.GridBagLayout());

        searchManageBtn.setText("Refresh");
        searchManageBtn.setPreferredSize(new java.awt.Dimension(150, 30));
        searchManageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchManageBtnActionPerformed(evt);
            }
        });
        dashboardFooterPanel.add(searchManageBtn, new java.awt.GridBagConstraints());

        dashboardPane.add(dashboardFooterPanel, java.awt.BorderLayout.PAGE_END);

        dashboardCenterPanel.setLayout(new java.awt.GridLayout(2, 3, 15, 15));

        dashboardPanel1.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(dashboardPanel1);

        dashboardPanel2.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(dashboardPanel2);

        dashboardPanel3.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(dashboardPanel3);

        dashboardPanel4.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(dashboardPanel4);

        dashboardPanel5.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(dashboardPanel5);

        dashboardPanel6.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(dashboardPanel6);

        dashboardPane.add(dashboardCenterPanel, java.awt.BorderLayout.CENTER);

        adminDashboardPane.addTab("Dashboard", dashboardPane);

        adminManagePane.setLayout(new java.awt.BorderLayout());

        manageCitizenLabel.setBackground(new java.awt.Color(255, 255, 255));
        manageCitizenLabel.setFont(new java.awt.Font("SimSun", 1, 36)); // NOI18N
        manageCitizenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageCitizenLabel.setText("Manage Citizen Records");
        adminManagePane.add(manageCitizenLabel, java.awt.BorderLayout.PAGE_START);

        manageCitizenFooterPanel.setLayout(new java.awt.GridLayout(1, 0));

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(addBtn);

        viewBtn.setText("View");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(viewBtn);

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(editBtn);

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(deleteBtn);

        approveBtn.setText("Approve");
        approveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(approveBtn);

        rejectBtn.setText("Reject");
        rejectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(rejectBtn);

        refreshManageBtn.setText("Refresh");
        manageCitizenFooterPanel.add(refreshManageBtn);

        adminManagePane.add(manageCitizenFooterPanel, java.awt.BorderLayout.PAGE_END);

        citizensTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Citizenship No", "Full Name", "Age", "Phone", "Province", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        citizenRecordPane.setViewportView(citizensTable);

        adminManagePane.add(citizenRecordPane, java.awt.BorderLayout.CENTER);

        adminDashboardPane.addTab("Manage Citizens", adminManagePane);

        SearchPane.setLayout(new java.awt.BorderLayout());

        searchHeader.setLayout(new java.awt.GridBagLayout());

        searchOptionGroup.add(citizenshipNoBtn);
        citizenshipNoBtn.setText("Citizenship Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        searchHeader.add(citizenshipNoBtn, gridBagConstraints);

        searchOptionGroup.add(nameBtn);
        nameBtn.setText("Voter Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        searchHeader.add(nameBtn, gridBagConstraints);

        searchOptionGroup.add(phoneNumberBtn);
        phoneNumberBtn.setText("Phone Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        searchHeader.add(phoneNumberBtn, gridBagConstraints);

        searchField.setToolTipText("Enter Details");
        searchField.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        searchHeader.add(searchField, gridBagConstraints);

        linearSearchBtn.setText("Linear Search");
        linearSearchBtn.setPreferredSize(new java.awt.Dimension(150, 30));
        linearSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linearSearchBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        searchHeader.add(linearSearchBtn, gridBagConstraints);

        binarySearchBtn.setText("Binary Search");
        binarySearchBtn.setPreferredSize(new java.awt.Dimension(150, 30));
        binarySearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarySearchBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        searchHeader.add(binarySearchBtn, gridBagConstraints);

        SearchPane.add(searchHeader, java.awt.BorderLayout.PAGE_START);

        detailsTextarea.setColumns(20);
        detailsTextarea.setRows(5);
        searchScrollPane.setViewportView(detailsTextarea);

        SearchPane.add(searchScrollPane, java.awt.BorderLayout.CENTER);

        adminDashboardPane.addTab("Search", SearchPane);

        sortPane.setLayout(new java.awt.BorderLayout());

        sortSelectionGroup.add(citizenshipnoSortBtn);
        citizenshipnoSortBtn.setText("Citizenship Number");
        sortHeader.add(citizenshipnoSortBtn);

        sortSelectionGroup.add(nameSortBtn);
        nameSortBtn.setText("Name");
        sortHeader.add(nameSortBtn);

        sortSelectionGroup.add(ageSortBtn);
        ageSortBtn.setText("Age");
        sortHeader.add(ageSortBtn);

        sortChoiceGroup.add(sortChoiceBtn1);
        sortChoiceBtn1.setText("Ascending");
        sortHeader.add(sortChoiceBtn1);

        sortChoiceGroup.add(sortChoiceBtn2);
        sortChoiceBtn2.setText("Descending");
        sortHeader.add(sortChoiceBtn2);

        sortBtn.setText("Sort ");
        sortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortBtnActionPerformed(evt);
            }
        });
        sortHeader.add(sortBtn);

        resetSortBtn.setText("Reset");
        resetSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSortBtnActionPerformed(evt);
            }
        });
        sortHeader.add(resetSortBtn);

        sortPane.add(sortHeader, java.awt.BorderLayout.PAGE_START);

        sortTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Citizenship No", "Full Name", "Age", "Phone", "Province", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sortCenterPane.setViewportView(sortTable);

        javax.swing.GroupLayout sortCenterPanelLayout = new javax.swing.GroupLayout(sortCenterPanel);
        sortCenterPanel.setLayout(sortCenterPanelLayout);
        sortCenterPanelLayout.setHorizontalGroup(
            sortCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sortCenterPane, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        sortCenterPanelLayout.setVerticalGroup(
            sortCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sortCenterPane, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );

        sortPane.add(sortCenterPanel, java.awt.BorderLayout.CENTER);

        adminDashboardPane.addTab("Sort", sortPane);

        queuePane.setLayout(new java.awt.BorderLayout());

        queuePaneHeader.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Approval Queue Management");
        queuePaneHeader.add(jLabel3);

        queuePane.add(queuePaneHeader, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        jButton1.setText("Process Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("View Queue");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        queuePane.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        queueTextArea.setEditable(false);
        queueTextArea.setColumns(20);
        queueTextArea.setRows(5);
        jScrollPane2.setViewportView(queueTextArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );

        queuePane.add(jPanel3, java.awt.BorderLayout.CENTER);

        adminDashboardPane.addTab("Approval Queue", queuePane);

        historyPane.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel5.setText("Action History");
        jPanel4.add(jLabel5, new java.awt.GridBagConstraints());

        historyPane.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButton4.setText("Undo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel5.add(jButton4, gridBagConstraints);

        jButton5.setText("View Stack");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel5.add(jButton5, gridBagConstraints);

        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel5.add(jButton6, gridBagConstraints);

        historyPane.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
        );

        historyPane.add(jPanel6, java.awt.BorderLayout.CENTER);

        adminDashboardPane.addTab("History", historyPane);

        adminDashboardPanel.add(adminDashboardPane, java.awt.BorderLayout.CENTER);

        contentPanel.add(adminDashboardPanel, "adminDashboard");

        mainContentPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        cardPanel.add(mainContentPanel, "mainContent");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void adminChoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminChoiceBtnActionPerformed
        // TODO add your handling code here:
       CardLayout cl = (CardLayout) contentPanel.getLayout();
       cl.show(contentPanel, "adminLogin");     
    }//GEN-LAST:event_adminChoiceBtnActionPerformed

    private void forgetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgetBtnActionPerformed
        // TODO add your handling code here:
        forgetBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgetBtn.setForeground(new Color(0, 102, 204));
        
        try {
           String adminId = JOptionPane.showInputDialog(this, "Enter your Admin ID: ", "Password Reset - Verify Identity", JOptionPane.QUESTION_MESSAGE);
           
           if (adminId == null) {
               return;
           }
           
           if (adminId.trim().isEmpty()) {
               JOptionPane.showMessageDialog(this, "Admin ID cannot be empty.", "Input Required", JOptionPane.WARNING_MESSAGE);
               return;
           }
           
           if (!adminRegistry.adminExists(adminId.trim())) {
               JOptionPane.showMessageDialog(this, "Admin ID not Found. \nPlease check your Admin ID and try again.", "Admin Not Found", JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           JPasswordField newPasswordField = new JPasswordField(20);
           Object[] message1 = {"Enter your new password:", "(Must be at least 8 characters)", newPasswordField};
           
           int option1 = JOptionPane.showConfirmDialog(this, message1, "Password Reset - New Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
           
           if (option1 != JOptionPane.OK_OPTION) {
               return;
           }
           
           String newPassword = new String(newPasswordField.getPassword());
           
           if (newPassword.trim().isEmpty()) {
               JOptionPane.showMessageDialog(this, "Password cannot b empty.", "Input Required", JOptionPane.WARNING_MESSAGE);
               return;
           }
           
           if (newPassword.trim().length() < 8) {
               JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.", "Password too short", JOptionPane.WARNING_MESSAGE);
               return;
           }
           
           JPasswordField confirmPasswordField = new JPasswordField(20);
           Object[] message2 = {"Confirm your nw password:", confirmPasswordField};
           
           int option2 = JOptionPane.showConfirmDialog(this, message2, "Password Reset - Confirm Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
           
           if (option2 != JOptionPane.OK_OPTION) {
               return;
            }
           String confirmPassword = new String(confirmPasswordField.getPassword());
           
           if (!newPassword.equals(confirmPassword)) {
               JOptionPane.showMessageDialog(this, "Passwords do not match.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           boolean removed = adminRegistry.removeAdmin(adminId.trim());
           
           if (!removed) {
               JOptionPane.showMessageDialog(this, "Failed to reset password.", "Reset Failed", JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           AdminModel newAdmin = new AdminModel(adminId.trim(), newPassword.trim());
           adminRegistry.addAdmin(newAdmin);
           
           JOptionPane.showMessageDialog(this, "Password reset successful", "Success", JOptionPane.INFORMATION_MESSAGE);
       } catch (IllegalArgumentException e) {
           JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Reset Failed", JOptionPane.ERROR_MESSAGE);
       } catch (NullPointerException e) {
           JOptionPane.showMessageDialog(this, "System Error: Admin Registry not initialized", "System Error", JOptionPane.ERROR_MESSAGE);
       } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "An unexpected error occured. \nPlease try again.", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_forgetBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "selectRole");
    }//GEN-LAST:event_backBtnActionPerformed

    private void logInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInBtnActionPerformed
        // TODO add your handling code here:
        try {
            String adminId = adminIdField.getText();
            String password = new String(adminPasswordField.getPassword());
            
            boolean loginSuccess = adminController.login(adminId, password);
            
            if (loginSuccess) {
                clearLoginFields();
                
                welcomeAdmin.setText("Welcome: " + adminController.getCurrentAdminId() + "!");
                
                refreshDashboard();
                refreshCitizensTable();
                
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "adminDashboard");
                
                showMessage("Login Successful!");
            } else {
                adminPasswordField.setText("");
                adminPasswordField.requestFocus();
            }
        } catch (Exception e) {
            showError("Error during login. Please try again.");
            e.printStackTrace();
            
            clearLoginFields();
        }
    }//GEN-LAST:event_logInBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        AddCitizenDialog dialog = new AddCitizenDialog(this, true, citizenController);
        dialog.setVisible(true);
        
        if (dialog.wasSuccessful()) {
            refreshCitizensTable();
            refreshDashboard();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = citizensTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showError("Please select a citizen to view: ");
            return;
        }
        
        try {
            String citizenshipNumber = (String) citizensTable.getValueAt(selectedRow, 0);
            
            CitizenModel citizen = adminController.findCitizen(citizenshipNumber);
            
            if (citizen != null) {
                
                String details = citizenController.formatCitizenDetails(citizen);
                
                JTextArea textArea = new JTextArea(details);
                textArea.setEditable(false);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 400));
                
                JOptionPane.showMessageDialog(this, scrollPane, "Citizens Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            showError("Error viewing Citizen:" + e.getMessage());
        }
    }//GEN-LAST:event_viewBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            adminController.logout();
            
            adminIdField.setText("");
            adminPasswordField.setText("");
            
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "selectRole");
            
            showMessage("Logged out successfully!");
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        handleEditCitizen();
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        handleDeleteCitizen();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        // TODO add your handling code here:
        handleApproveCitizen();
    }//GEN-LAST:event_approveBtnActionPerformed

    private void rejectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectBtnActionPerformed
        // TODO add your handling code here:
        handleRejectCitizen();
    }//GEN-LAST:event_rejectBtnActionPerformed

    private void searchManageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchManageBtnActionPerformed
        // TODO add your handling code here:
        refreshDashboard();
        showMessage("DashBoard Refreshed");
    }//GEN-LAST:event_searchManageBtnActionPerformed

    private void linearSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linearSearchBtnActionPerformed
        // TODO add your handling code here:
        handleLinearSearch();
    }//GEN-LAST:event_linearSearchBtnActionPerformed

    private void binarySearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarySearchBtnActionPerformed
        // TODO add your handling code here:
        handleBinarySearch();
    }//GEN-LAST:event_binarySearchBtnActionPerformed

    private void sortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortBtnActionPerformed
        // TODO add your handling code here:
        handleSort();
    }//GEN-LAST:event_sortBtnActionPerformed

    private void resetSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSortBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) sortTable.getModel();
        model.setRowCount(0);
        showMessage("Table cleared");
    }//GEN-LAST:event_resetSortBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        handleProcessNext();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        handleViewQueue();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    adminController.refreshQueue();
    refreshDashboard();
    showMessage("Queue refreshed successfully");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        handleUndo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        refreshHistoryList();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Clear history?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            adminController.clearHistory();
            refreshHistoryList();
            refreshDashboard();
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void handleEditCitizen() {
        try {
        int row = citizensTable.getSelectedRow();
        if (row < 0) {
            showError("Please select a citizen to edit");
            return;
        }
        
        String cid = (String) citizensTable.getValueAt(row, 0);
        CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(cid);
        
        if (citizen == null) {
            showError("Citizen not found");
            return;
        }
        
        // Create an edit dialog similar to AddCitizenDialog
        EditCitizenDialog dialog = new EditCitizenDialog(this, true, citizenController, citizen);
        dialog.setVisible(true);
        
        if (dialog.wasSuccessful()) {
            refreshCitizensTable();
            refreshDashboard();
            }
        } catch (Exception e) {
            showError("Error editing: " + e.getMessage());
        }
    }
    
    private void handleDeleteCitizen() {
        try {
            int row = citizensTable.getSelectedRow();
            if (row < 0) {
                showError("Please select a citizen to delete");
                return;
            }
            
            String cid = (String) citizensTable.getValueAt(row, 0);
            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(cid);
            
            if (citizen == null) {
                showError("Citizen not found");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, "Delete " + citizen.getVoterName() + "?", "Confirm ", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = adminController.deleteCitizen(cid);
                if (success) {
                    refreshCitizensTable();
                    refreshDashboard();
                }
            }
        } catch (Exception e) {
            showError("error deleting: " + e.getMessage());
        }
    }
    
    private void handleApproveCitizen() {
        try {
            int row = citizensTable.getSelectedRow();
            if (row < 0) {
                showError("Please select a citizen");
                return;
            }
            
            String cid = (String) citizensTable.getValueAt(row, 0);
            boolean success = adminController.approveCitizen(cid);
            
            if (success) {
                refreshCitizensTable();
                refreshDashboard();
            }
        } catch (Exception e) {
            showError("Error approving: " + e.getMessage());
        }
    }
    
    private void handleRejectCitizen() {
        try {
        int row = citizensTable.getSelectedRow();
        if (row < 0) {
            showError("Please select a citizen");
            return;
        }
        
        String cid = (String) citizensTable.getValueAt(row, 0);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Reject this citizen?", "Confirm", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = adminController.rejectCitizen(cid, "Admin rejected");
            if (success) {
                refreshCitizensTable();
                refreshDashboard();
            }
        }
        
        } catch (Exception e) {
            showError("Error rejecting: " + e.getMessage());
        }
    }
    
    private void handleLinearSearch() {
        try {
        String term = searchField.getText().trim();
        if (term.isEmpty()) {
            showError("Enter search term");
            return;
        }
        
        // Check if search option is selected
        if (!citizenshipNoBtn.isSelected() && !nameBtn.isSelected() && !phoneNumberBtn.isSelected()) {
            showError("Please select a search option (Citizenship Number, Name, or Phone Number)");
            return;
        }
        
        ArrayList<CitizenModel> all = new ArrayList<>(citizenRegistry.getAllCitizens());
        List<CitizenModel> results = new ArrayList<>();
        long start = System.currentTimeMillis();
        
        if (citizenshipNoBtn.isSelected()) {
            // Partial match for citizenship number (string comparison)
            // No need to validate if it's all digits - partial search should work with any input
            for (CitizenModel c : all) {
                // Convert both to string and check if citizenship contains the search term
                String citizenship = c.getCitizenshipNumber();
                if (citizenship.contains(term)) {
                    results.add(c);
                }
            }
        } else if (nameBtn.isSelected()) {
            // Partial match for name (case insensitive)
            String searchTerm = term.toLowerCase();
            for (CitizenModel c : all) {
                String name = c.getVoterName().toLowerCase();
                if (name.contains(searchTerm)) {
                    results.add(c);
                }
            }
        } else if (phoneNumberBtn.isSelected()) {
            // Partial match for phone number (string comparison)
            for (CitizenModel c : all) {
                String phone = c.getPhoneNumber();
                if (phone.contains(term)) {
                    results.add(c);
                }
            }
        }
        
        long time = System.currentTimeMillis() - start;
        displaySearchResults(results, "Linear Search (Partial Match)", time);
        
    } catch (Exception e) {
        showError("Search error: " + e.getMessage());
        e.printStackTrace(); // This will help debug
    }
    }
    
    private void handleBinarySearch() {
        try {
        String term = searchField.getText().trim();
        if (term.isEmpty()) {
            showError("Enter search term");
            return;
        }
        
        ArrayList<CitizenModel> all = new ArrayList<>(citizenRegistry.getAllCitizens());
        List<CitizenModel> results = new ArrayList<>();
        
        long sortStart = System.currentTimeMillis();
        long sortTime = 0;
        long searchStart = 0;
        long searchTime = 0;
        
        if (citizenshipNoBtn.isSelected()) {
            // Sort by citizenship number
            CitizenSorter.sortByCitizenshipNumber(all);
            sortTime = System.currentTimeMillis() - sortStart;
            
            // Binary search
            searchStart = System.currentTimeMillis();
            CitizenModel result = binarySearchByCitizenshipNumber(all, term);
            searchTime = System.currentTimeMillis() - searchStart;
            
            if (result != null) results.add(result);
            
        } else if (nameBtn.isSelected()) {
            // Sort by name
            sortByName(all);
            sortTime = System.currentTimeMillis() - sortStart;
            
            // Binary search by name
            searchStart = System.currentTimeMillis();
            CitizenModel result = binarySearchByName(all, term);
            searchTime = System.currentTimeMillis() - searchStart;
            
            if (result != null) results.add(result);
            
        } else if (phoneNumberBtn.isSelected()) {
            // Sort by phone
            sortByPhone(all);
            sortTime = System.currentTimeMillis() - sortStart;
            
            // Binary search by phone
            searchStart = System.currentTimeMillis();
            CitizenModel result = binarySearchByPhone(all, term);
            searchTime = System.currentTimeMillis() - searchStart;
            
            if (result != null) results.add(result);
            
        } else {
            showError("Please select a search option");
            return;
        }
        
        String info = String.format("Binary Search\nSort Time: %dms | Search Time: %dms\nTotal: %dms\n\n", 
                                   sortTime, searchTime, sortTime + searchTime);
        displaySearchResults(results, info, sortTime + searchTime);
        
    } catch (Exception e) {
        showError("Search error: " + e.getMessage());
    }
    }
    
    private CitizenModel binarySearchByCitizenshipNumber(ArrayList<CitizenModel> citizens, String target) {
        int left = 0, right = citizens.size() - 1;
    
    // Validate that target contains only digits for binary search
    if (!target.matches("\\d+")) {
        showError("For binary search by citizenship number, please enter only digits");
        return null;
    }
    
    // Pad target to match typical citizenship number length for comparison
    String searchId = target.trim();
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        String midId = citizens.get(mid).getCitizenshipNumber();
        
        // Use string comparison for exact match
        int comparison = midId.compareTo(searchId);
        
        if (comparison == 0) return citizens.get(mid);
        if (comparison < 0) left = mid + 1;
        else right = mid - 1;
    }
    return null;
    }
    
    private CitizenModel binarySearchByName(ArrayList<CitizenModel> citizens, String target) {
        int left = 0, right = citizens.size() - 1;
    String searchName = target.toLowerCase();
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        String midName = citizens.get(mid).getVoterName().toLowerCase();
        
        int comparison = midName.compareTo(searchName);
        
        if (comparison == 0) return citizens.get(mid);
        if (comparison < 0) left = mid + 1;
        else right = mid - 1;
    }
    return null;
    }
    
    private CitizenModel binarySearchByPhone(ArrayList<CitizenModel> citizens, String target) {
        int left = 0, right = citizens.size() - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        String midPhone = citizens.get(mid).getPhoneNumber();
        
        int comparison = midPhone.compareTo(target);
        
        if (comparison == 0) return citizens.get(mid);
        if (comparison < 0) left = mid + 1;
        else right = mid - 1;
    }
    return null;
    }
    
    private void sortByName(ArrayList<CitizenModel> citizens) {
        for (int i = 0; i < citizens.size() - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < citizens.size(); j++) {
            if (citizens.get(j).getVoterName().compareToIgnoreCase(
                    citizens.get(minIdx).getVoterName()) < 0) {
                minIdx = j;
            }
        }
        CitizenModel temp = citizens.get(i);
        citizens.set(i, citizens.get(minIdx));
        citizens.set(minIdx, temp);
    }
    }
    
    private void sortByPhone(ArrayList<CitizenModel> citizens) {
        int n = citizens.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (citizens.get(j).getPhoneNumber().compareTo(
                    citizens.get(j + 1).getPhoneNumber()) > 0) {
                CitizenModel temp = citizens.get(j);
                citizens.set(j, citizens.get(j + 1));
                citizens.set(j + 1, temp);
            }
        }
    }
    }

    
    private void displaySearchResults(List<CitizenModel> results, String algo, long time) {
        StringBuilder sb = new StringBuilder();
    sb.append("═══════════════════════════════════════\n");
    sb.append("  ").append(algo).append("\n");
    sb.append("═══════════════════════════════════════\n");
    sb.append("Time: ").append(time).append("ms\n");
    sb.append("Results Found: ").append(results.size()).append("\n");
    sb.append("═══════════════════════════════════════\n\n");
    
    if (results.isEmpty()) {
        sb.append("No citizens found matching your search.\n");
    } else {
        int count = 1;
        for (CitizenModel c : results) {
            sb.append("Result #").append(count++).append("\n");
            sb.append("─────────────────────────────────────\n");
            sb.append(String.format("ID: %s\n", c.getCitizenshipNumber()));
            sb.append(String.format("Name: %s\n", c.getVoterName()));
            sb.append(String.format("Age: %d years\n", c.getAge()));
            sb.append(String.format("Phone: %s\n", c.getPhoneNumber()));
            sb.append(String.format("Gender: %s\n", c.getGender().getDisplay()));
            sb.append(String.format("Province: %s\n", c.getProvince()));
            sb.append(String.format("District: %s\n", c.getDistrict()));
            sb.append(String.format("Status: %s\n", c.getStatus().getDisplay()));
            sb.append("\n");
        }
    }
    
    detailsTextarea.setText(sb.toString());
    detailsTextarea.setCaretPosition(0);
    }
    
    private void handleSort() {
        try {
        ArrayList<CitizenModel> citizens = new ArrayList<>(citizenRegistry.getAllCitizens());
        if (citizens.isEmpty()) {
            showError("No data");
            return;
        }
        
        long start = System.currentTimeMillis();
        String algo = "";
        
        if (citizenshipnoSortBtn.isSelected()) {
            CitizenSorter.sortByCitizenshipNumber(citizens);
            algo = "Insertion Sort";
        } else if (nameSortBtn.isSelected()) {
            selectionSortByName(citizens);
            algo = "Selection Sort";
        } else if (ageSortBtn.isSelected()) {
            CitizenSorter.sortByAge(citizens);
            algo = "Merge Sort";
        }
        
        if (sortChoiceBtn2.isSelected()) {
            Collections.reverse(citizens);
        }
        
        long time = System.currentTimeMillis() - start;
        
        DefaultTableModel model = (DefaultTableModel) sortTable.getModel();
        model.setRowCount(0);
        for (CitizenModel c : citizens) {
            model.addRow(new Object[]{
                c.getCitizenshipNumber(), c.getVoterName(), c.getAge(),
                c.getPhoneNumber(), c.getProvince(), c.getStatus().getDisplay()
            });
        }
        
        showMessage(algo + "\nTime: " + time + "ms\nRecords: " + citizens.size());
        
        } catch (Exception e) {
            showError("Sort error: " + e.getMessage());
        }
    }
    
    private void handleProcessNext() {
        try {
        CitizenModel next = adminController.processNextInQueue();
        
        if (next == null) {
            showMessage("Queue is empty");
            return;
        }
        
        // Show in text area
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════ NEXT IN QUEUE ═══════════\n\n");
        sb.append(String.format("Name: %s\n", next.getVoterName()));
        sb.append(String.format("ID: %s\n", next.getCitizenshipNumber()));
        sb.append(String.format("Age: %d\n", next.getAge()));
        sb.append(String.format("Phone: %s\n", next.getPhoneNumber()));
        sb.append(String.format("Province: %s\n", next.getProvince()));
        sb.append("\n═══════════════════════════════════\n");
        queueTextArea.setText(sb.toString());
        
        String[] options = {"Approve", "Reject", "Skip", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, 
            "What action would you like to take?", 
            "Process Citizen",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, options, options[0]);
            
        if (choice == 0) {
            adminController.approveCitizen(next.getCitizenshipNumber());
            queueTextArea.append("\n✓ APPROVED");
        } else if (choice == 1) {
            adminController.rejectCitizen(next.getCitizenshipNumber(), "Rejected by admin");
            queueTextArea.append("\n✗ REJECTED");
        }
        
        refreshCitizensTable();
        refreshDashboard();
        
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }
    
    private void handleViewQueue() {
        try {
        LinkedList<CitizenModel> queue = adminController.getPendingQueue();
        
        if (queue.isEmpty()) {
            queueTextArea.setText("═══════════════════════════════════\n" +
                                 "      QUEUE IS EMPTY\n" +
                                 "═══════════════════════════════════");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════ APPROVAL QUEUE (FIFO) ═══════════\n\n");
        sb.append(String.format("Total in Queue: %d\n\n", queue.size()));
        sb.append("Position | Name              | ID          | Age\n");
        sb.append("─────────────────────────────────────────────────\n");
        
        int pos = 1;
        for (CitizenModel c : queue) {
            sb.append(String.format("%-8d | %-17s | %-11s | %d\n", 
                pos++, 
                truncate(c.getVoterName(), 17),
                c.getCitizenshipNumber(),
                c.getAge()));
        }
        
        queueTextArea.setText(sb.toString());
        queueTextArea.setCaretPosition(0);
        
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }

    private String truncate(String str, int length) {
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }
    
    private void handleUndo() {
        try {
        // Check if history is empty first
        if (adminController.getActionHistory().isEmpty()) {
            showMessage("No actions to undo.");
            return;
        }
        
        // Get the last action to show in confirmation
        Stack<ActionHistory> history = adminController.getActionHistory();
        ActionHistory lastAction = history.peek(); // Just peek, don't pop yet
        
        // Create detailed confirmation message
        String message = String.format(
            "Are you sure you want to undo this action?\n\n" +
            "Action Type: %s\n" +
            "Citizen: %s\n" +
            "Citizenship No: %s\n" +
            "Admin: %s\n" +
            "Time: %s",
            lastAction.getActionType(),
            lastAction.getCitizenSnapshot().getVoterName(),
            lastAction.getCitizenSnapshot().getCitizenshipNumber(),
            lastAction.getAdminId(),
            lastAction.getTimestamp().toString()
        );
        
        // Show confirmation dialog with YES/NO options
        int confirm = JOptionPane.showConfirmDialog(
            this,
            message,
            "Confirm Undo Action",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Only proceed if YES was clicked
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = adminController.undoLastAction();
            
            if (success) {
                refreshCitizensTable();
                refreshDashboard();
                refreshHistoryList();
                showMessage("Action undone successfully!");
            }
        } else {
            // User clicked NO or closed the dialog
            showMessage("Undo cancelled.");
        }
        
        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }
    
    private void refreshHistoryList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Stack<ActionHistory> history = adminController.getActionHistory();
    
    if (history.isEmpty()) {
        model.addElement("No history");
    } else {
        List<ActionHistory> temp = new ArrayList<>(history);
        Collections.reverse(temp);
        
        int count = Math.min(20, temp.size());
        for (int i = 0; i < count; i++) {
            model.addElement(temp.get(i).toString());
        }
    }
    
    jList1.setModel(model);
    }
    
    private void loadUnsortedDataToSortTable() {
        try {
        DefaultTableModel model = (DefaultTableModel) sortTable.getModel();
        model.setRowCount(0);
        
        ArrayList<CitizenModel> citizens = new ArrayList<>(citizenRegistry.getAllCitizens());
        
        for (CitizenModel c : citizens) {
            model.addRow(new Object[]{
                c.getCitizenshipNumber(),
                c.getVoterName(),
                c.getAge(),
                c.getPhoneNumber(),
                c.getProvince(),
                c.getStatus().getDisplay()
            });
        }
        } catch (Exception e) {
            showError("Error loading data: " + e.getMessage());
        }
    }
    
    private void sortTableInitial() {
        adminDashboardPane.addChangeListener(e -> {
            int selectedIndex = adminDashboardPane.getSelectedIndex();
            String tabTitle = adminDashboardPane.getTitleAt(selectedIndex);
            
            if ("Sort".equals(tabTitle)) {
                loadUnsortedDataToSortTable();
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        MainFrame mmf = new MainFrame();
        mmf.setVisible(true);
        
        // Start loading in background thread
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(20); // Adjust speed here (lower = faster)
                    
                    final int progress = i;
                    String message;
                    
                    if (i < 20) {
                        message = "Loading Modules...";
                    } else if (i < 50) {
                        message = "Initializing System...";
                    } else if (i < 80) {
                        message = "Preparing Interface...";
                    } else {
                        message = "Almost Ready...";
                    }
                    
                    final String finalMessage = message;
                    
                    // Update UI
                    java.awt.EventQueue.invokeLater(() -> {
                        mmf.loadingBar.setValue(progress);
                        mmf.loadingValue.setText(finalMessage + " " + progress + "%");
                    });
                }
                
                // Switch to main panel after loading
                java.awt.EventQueue.invokeLater(() -> {
                    mmf.showMainContent();
                });
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    });
}
    
    public void showMainContent() {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "mainContent");
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SearchPane;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton adminChoiceBtn;
    private javax.swing.JTabbedPane adminDashboardPane;
    private javax.swing.JPanel adminDashboardPanel;
    private javax.swing.JPanel adminHeaderCenter;
    private javax.swing.JPanel adminHeaderPanel;
    private javax.swing.JTextField adminIdField;
    private javax.swing.JLabel adminIdLabel;
    private view.BackgroundPanel adminImg;
    private javax.swing.JLabel adminLoginLabel;
    private javax.swing.JPanel adminLoginPanel;
    private javax.swing.JPanel adminManagePane;
    private javax.swing.JPasswordField adminPasswordField;
    private javax.swing.JLabel adminPasswordLabel;
    private javax.swing.JPanel adminheaderLeft;
    private javax.swing.JRadioButton ageSortBtn;
    private javax.swing.JButton approveBtn;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton binarySearchBtn;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JLabel carouselLabel;
    private javax.swing.JButton citizenChoiceBtn;
    private view.BackgroundPanel citizenImg;
    private javax.swing.JScrollPane citizenRecordPane;
    private javax.swing.JTable citizensTable;
    private javax.swing.JRadioButton citizenshipNoBtn;
    private javax.swing.JRadioButton citizenshipnoSortBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel dashboardCenterPanel;
    private javax.swing.JPanel dashboardFooterPanel;
    private javax.swing.JPanel dashboardPane;
    private javax.swing.JPanel dashboardPanel1;
    private javax.swing.JPanel dashboardPanel2;
    private javax.swing.JPanel dashboardPanel3;
    private javax.swing.JPanel dashboardPanel4;
    private javax.swing.JPanel dashboardPanel5;
    private javax.swing.JPanel dashboardPanel6;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextArea detailsTextarea;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel exitPanel;
    private javax.swing.JLabel footerLabel1;
    private javax.swing.JLabel footerLabel2;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JButton forgetBtn;
    private javax.swing.JPanel headerContainer;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel historyPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton linearSearchBtn;
    private javax.swing.JProgressBar loadingBar;
    private javax.swing.JLabel loadingLabel;
    private view.BackgroundPanel loadingPanel;
    private javax.swing.JLabel loadingValue;
    private javax.swing.JButton logInBtn;
    private view.BackgroundPanel logoPanel;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel mainContentPanel;
    private javax.swing.JPanel manageCitizenFooterPanel;
    private javax.swing.JLabel manageCitizenLabel;
    private javax.swing.JRadioButton nameBtn;
    private javax.swing.JRadioButton nameSortBtn;
    private javax.swing.JRadioButton phoneNumberBtn;
    private view.BackgroundPanel protectionImg;
    private javax.swing.JPanel queuePane;
    private javax.swing.JPanel queuePaneHeader;
    private javax.swing.JTextArea queueTextArea;
    private javax.swing.JLabel quoteLabel;
    private javax.swing.JButton refreshManageBtn;
    private javax.swing.JButton rejectBtn;
    private javax.swing.JButton resetSortBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchHeader;
    private javax.swing.JButton searchManageBtn;
    private javax.swing.ButtonGroup searchOptionGroup;
    private javax.swing.JScrollPane searchScrollPane;
    private javax.swing.JLabel selectRoleLabel;
    private javax.swing.JPanel selectRolePanel;
    private javax.swing.JButton sortBtn;
    private javax.swing.JScrollPane sortCenterPane;
    private javax.swing.JPanel sortCenterPanel;
    private javax.swing.JRadioButton sortChoiceBtn1;
    private javax.swing.JRadioButton sortChoiceBtn2;
    private javax.swing.ButtonGroup sortChoiceGroup;
    private javax.swing.JPanel sortHeader;
    private javax.swing.JPanel sortPane;
    private javax.swing.ButtonGroup sortSelectionGroup;
    private javax.swing.JTable sortTable;
    private javax.swing.JLabel statsLabel;
    private javax.swing.JLabel subHeaderLabel;
    private javax.swing.JButton viewBtn;
    private javax.swing.JLabel welcomeAdmin;
    // End of variables declaration//GEN-END:variables
}