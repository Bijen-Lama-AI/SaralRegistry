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
import model.CitizenModel.RegistrationStatus;

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

        } catch (IllegalArgumentException e) {
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
        try {
            DefaultTableModel model = (DefaultTableModel) citizensTable.getModel();
            model.setRowCount(0);
            for (CitizenModel c : citizenRegistry.getAllCitizens()) {
                model.addRow(new Object[]{
                    c.getCitizenshipNumber(),
                    c.getVoterName(),
                    c.getAge(),
                    c.getGender().getDisplay(),
                    c.getPhoneNumber(),
                    c.getDistrict(),
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

            updateDashboardPanel(statsPanel1, "📋 Pending",
                    String.valueOf(adminController.countByStatus(CitizenModel.RegistrationStatus.PENDING)),
                    new Color(255, 165, 0));

            updateDashboardPanel(statsPanel2, "✅ Approved",
                    String.valueOf(adminController.countByStatus(CitizenModel.RegistrationStatus.APPROVED)),
                    new Color(40, 167, 69));

            updateDashboardPanel(statsPanel3, "❌ Rejected",
                    String.valueOf(adminController.countByStatus(CitizenModel.RegistrationStatus.REJECTED)),
                    new Color(220, 53, 69));

            updateDashboardPanel(statsPanel4, "👥 Total",
                    String.valueOf(adminController.getTotalCitizenCount()),
                    new Color(0, 123, 255));

            updateDashboardPanel(statsPanel5, "📋 Queue",
                    String.valueOf(adminController.getQueueSize()),
                    new Color(108, 117, 125));

            updateDashboardPanel(statsPanel6, "📚 History",
                    String.valueOf(adminController.getHistorySize()),
                    new Color(23, 162, 184));
        } catch (Exception e) {
            showError("Error refreshing dashboard: " + e.getMessage());
        }
    }

    private CitizenModel binarySearchById(ArrayList<CitizenModel> citizens, String cid) {
        int left = 0, right = citizens.size() - 1;
        long searchId = Long.parseLong(cid);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long midId = Long.parseLong(citizens.get(mid).getCitizenshipNumber());

            if (midId == searchId) {
                return citizens.get(mid);
            }
            if (midId < searchId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    private void selectionSortByName(ArrayList<CitizenModel> citizens) {
        for (int i = 0; i < citizens.size() - 1; i++) {
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
        setupCitizenRegComboBoxes();

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
        nameSuggestionPopup = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        nameSuggestionList = new javax.swing.JList<>();
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
        extSystemBtn = new javax.swing.JButton();
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
        filterLabel = new javax.swing.JTabbedPane();
        dashboardPane = new javax.swing.JPanel();
        statsLabel = new javax.swing.JLabel();
        dashboardFooterPanel = new javax.swing.JPanel();
        searchManageBtn = new javax.swing.JButton();
        dashboardCenterPanel = new javax.swing.JPanel();
        statsPanel1 = new javax.swing.JPanel();
        statsPanel2 = new javax.swing.JPanel();
        statsPanel3 = new javax.swing.JPanel();
        statsPanel4 = new javax.swing.JPanel();
        statsPanel5 = new javax.swing.JPanel();
        statsPanel6 = new javax.swing.JPanel();
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
        districtSearchBtn = new javax.swing.JRadioButton();
        provinceSearchBtn = new javax.swing.JRadioButton();
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
        applyFilterLabel = new javax.swing.JLabel();
        ageGroupFilterCombo = new javax.swing.JComboBox<>();
        provinceFilterCombo = new javax.swing.JComboBox<>();
        districtFilterCombo = new javax.swing.JComboBox<>();
        applyFilterBtn = new javax.swing.JButton();
        sortCenterPanel = new javax.swing.JPanel();
        sortCenterPane = new javax.swing.JScrollPane();
        sortTable = new javax.swing.JTable();
        queuePane = new javax.swing.JPanel();
        queuePaneHeader = new javax.swing.JPanel();
        approveQueueLabel = new javax.swing.JLabel();
        queueFooterPanel = new javax.swing.JPanel();
        processNextBtn = new javax.swing.JButton();
        viewQueueBtn = new javax.swing.JButton();
        queueRefreshBtn = new javax.swing.JButton();
        queueCenterPanel = new javax.swing.JPanel();
        queueCentrePane = new javax.swing.JScrollPane();
        queueTextArea = new javax.swing.JTextArea();
        historyPane = new javax.swing.JPanel();
        historyHeader = new javax.swing.JPanel();
        actionLabel = new javax.swing.JLabel();
        historyFooter = new javax.swing.JPanel();
        historyUndoBtn = new javax.swing.JButton();
        viewHistoryBtn = new javax.swing.JButton();
        historyClearBtn = new javax.swing.JButton();
        historyCenterPanel = new javax.swing.JPanel();
        showHistoryPane = new javax.swing.JScrollPane();
        historyList = new javax.swing.JList<>();
        CitizenDashboardPanel = new javax.swing.JPanel();
        citizenHeaderPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        citizenWelcomePanel = new javax.swing.JPanel();
        citizenLogoutPanel = new javax.swing.JPanel();
        citizenLogoutBtn = new javax.swing.JButton();
        citizenTabbedPane = new javax.swing.JTabbedPane();
        registrationPane = new javax.swing.JPanel();
        registrationHeaderPanel = new javax.swing.JPanel();
        registrationTitleLabel = new javax.swing.JLabel();
        registrationFormPanel = new javax.swing.JPanel();
        cidRegLabel = new javax.swing.JLabel();
        cidRegField = new javax.swing.JTextField();
        nameRegLabel = new javax.swing.JLabel();
        nameRegField = new javax.swing.JTextField();
        phoneRegLabel = new javax.swing.JLabel();
        phoneRegField = new javax.swing.JTextField();
        genderRegLabel = new javax.swing.JLabel();
        genderRegCombo = new javax.swing.JComboBox<>();
        dobRegLabel = new javax.swing.JLabel();
        dobRegChooser = new com.toedter.calendar.JDateChooser();
        provinceRegLabel = new javax.swing.JLabel();
        provinceRegCombo = new javax.swing.JComboBox<>();
        districtRegLabel = new javax.swing.JLabel();
        districtRegCombo = new javax.swing.JComboBox<>();
        municipalityRegLabel = new javax.swing.JLabel();
        municipalityRegField = new javax.swing.JTextField();
        voteCenterLabel = new javax.swing.JLabel();
        voteCenterRegField = new javax.swing.JTextField();
        voidi = new javax.swing.JLabel();
        voidii = new javax.swing.JLabel();
        registrationFooterPanel = new javax.swing.JPanel();
        submitRegBtn = new javax.swing.JButton();
        clearRegBtn = new javax.swing.JButton();
        statusCheckPane = new javax.swing.JPanel();
        statusHeaderPanel = new javax.swing.JPanel();
        statusHeaderLabel = new javax.swing.JLabel();
        statusMainPanel = new javax.swing.JPanel();
        statusInputPanel = new javax.swing.JPanel();
        cidSearchLabel = new javax.swing.JLabel();
        statusCidField = new javax.swing.JTextField();
        checkStatusBtn = new javax.swing.JButton();
        statusResultPanel = new javax.swing.JPanel();
        statusCardPanel = new javax.swing.JPanel();
        statusNameLabel = new javax.swing.JLabel();
        statusCidDisplayLabel = new javax.swing.JLabel();
        statusGenderLabel = new javax.swing.JLabel();
        statusAgeLabel = new javax.swing.JLabel();
        statusProvinceLabel = new javax.swing.JLabel();
        statusDistrictLabel = new javax.swing.JLabel();
        statusCurrentLabel = new javax.swing.JLabel();
        statusProgressBar = new javax.swing.JProgressBar();
        statusMessageLabel = new javax.swing.JLabel();
        editRecordPane = new javax.swing.JPanel();
        editSearchPanel = new javax.swing.JPanel();
        searchCidLabel = new javax.swing.JLabel();
        searchCidField = new javax.swing.JTextField();
        searchMyRecordButton = new javax.swing.JButton();
        clearSearchBtn = new javax.swing.JButton();
        editCenterPanel = new javax.swing.JPanel();
        editFormScrollPane = new javax.swing.JScrollPane();
        editFormPanel = new javax.swing.JPanel();
        cidEditLabel = new javax.swing.JLabel();
        cidEditField = new javax.swing.JTextField();
        nameEditLabel = new javax.swing.JLabel();
        nameEditField = new javax.swing.JTextField();
        phoneEditLabel = new javax.swing.JLabel();
        phoneEditField = new javax.swing.JTextField();
        genderEditLabel = new javax.swing.JLabel();
        genderEditCombo = new javax.swing.JComboBox<>();
        dobEditLabel = new javax.swing.JLabel();
        dobEditChooser = new com.toedter.calendar.JDateChooser();
        provinceEditLabel = new javax.swing.JLabel();
        provinceEditCombo = new javax.swing.JComboBox<>();
        districtEditLabel = new javax.swing.JLabel();
        districtEditCombo = new javax.swing.JComboBox<>();
        municipalityEditLabel = new javax.swing.JLabel();
        municipalityEditField = new javax.swing.JTextField();
        voteCenterEditLabel = new javax.swing.JLabel();
        voteCenterEditField = new javax.swing.JTextField();
        statusEditLabel = new javax.swing.JLabel();
        statusEditDisplay = new javax.swing.JLabel();
        editFooterPanel = new javax.swing.JPanel();
        saveEditBtn = new javax.swing.JButton();
        cancelEditBtn = new javax.swing.JButton();
        deleteMyRecordBtn = new javax.swing.JButton();

        nameSuggestionPopup.setPreferredSize(new java.awt.Dimension(250, 250));

        nameSuggestionList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(nameSuggestionList);

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

        extSystemBtn.setBackground(new java.awt.Color(204, 0, 51));
        extSystemBtn.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        extSystemBtn.setForeground(new java.awt.Color(255, 255, 255));
        extSystemBtn.setText("Exit System");
        extSystemBtn.setPreferredSize(new java.awt.Dimension(200, 39));
        extSystemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extSystemBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        footerPanel.add(extSystemBtn, gridBagConstraints);

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
        citizenChoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citizenChoiceBtnActionPerformed(evt);
            }
        });
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
            .addGap(0, 292, Short.MAX_VALUE)
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

        logoutBtn.setBackground(new java.awt.Color(255, 153, 51));
        logoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.setPreferredSize(new java.awt.Dimension(100, 30));
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

        filterLabel.setBackground(new java.awt.Color(255, 255, 255));

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

        statsPanel1.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(statsPanel1);

        statsPanel2.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(statsPanel2);

        statsPanel3.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(statsPanel3);

        statsPanel4.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(statsPanel4);

        statsPanel5.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(statsPanel5);

        statsPanel6.setLayout(new java.awt.BorderLayout());
        dashboardCenterPanel.add(statsPanel6);

        dashboardPane.add(dashboardCenterPanel, java.awt.BorderLayout.CENTER);

        filterLabel.addTab("Dashboard", dashboardPane);

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
        refreshManageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshManageBtnActionPerformed(evt);
            }
        });
        manageCitizenFooterPanel.add(refreshManageBtn);

        adminManagePane.add(manageCitizenFooterPanel, java.awt.BorderLayout.PAGE_END);

        citizensTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Citizenship No", "Full Name", "Age", "Gender", "Phone", "District", "Province", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        citizenRecordPane.setViewportView(citizensTable);

        adminManagePane.add(citizenRecordPane, java.awt.BorderLayout.CENTER);

        filterLabel.addTab("Manage Citizens", adminManagePane);

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

        searchOptionGroup.add(districtSearchBtn);
        districtSearchBtn.setText("District");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 25);
        searchHeader.add(districtSearchBtn, gridBagConstraints);

        searchOptionGroup.add(provinceSearchBtn);
        provinceSearchBtn.setText("Province");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 25);
        searchHeader.add(provinceSearchBtn, gridBagConstraints);

        searchField.setPreferredSize(new java.awt.Dimension(200, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        searchHeader.add(searchField, gridBagConstraints);

        linearSearchBtn.setText("Linear Search");
        linearSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linearSearchBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        searchHeader.add(linearSearchBtn, gridBagConstraints);

        binarySearchBtn.setText("Binary Search");
        binarySearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarySearchBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        searchHeader.add(binarySearchBtn, gridBagConstraints);

        SearchPane.add(searchHeader, java.awt.BorderLayout.PAGE_START);

        detailsTextarea.setColumns(20);
        detailsTextarea.setRows(5);
        searchScrollPane.setViewportView(detailsTextarea);

        SearchPane.add(searchScrollPane, java.awt.BorderLayout.CENTER);

        filterLabel.addTab("Search", SearchPane);

        sortPane.setLayout(new java.awt.BorderLayout());

        sortHeader.setLayout(new java.awt.GridLayout(2, 0));

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

        applyFilterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        applyFilterLabel.setText("Filter By:");
        sortHeader.add(applyFilterLabel);

        ageGroupFilterCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ages", "18-25 years", "26-40 years", "41-60 years", "61+ years" }));
        sortHeader.add(ageGroupFilterCombo);

        provinceFilterCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Provinces", "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim" }));
        provinceFilterCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceFilterComboActionPerformed(evt);
            }
        });
        sortHeader.add(provinceFilterCombo);

        districtFilterCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All District" }));
        sortHeader.add(districtFilterCombo);

        applyFilterBtn.setText("Apply Filter");
        applyFilterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyFilterBtnActionPerformed(evt);
            }
        });
        sortHeader.add(applyFilterBtn);

        sortPane.add(sortHeader, java.awt.BorderLayout.PAGE_START);

        sortTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Citizenship No", "Full Name", "Age", "Gender", "Phone", "District", "Province", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            .addComponent(sortCenterPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );
        sortCenterPanelLayout.setVerticalGroup(
            sortCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sortCenterPane, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );

        sortPane.add(sortCenterPanel, java.awt.BorderLayout.CENTER);

        filterLabel.addTab("Sort", sortPane);

        queuePane.setLayout(new java.awt.BorderLayout());

        queuePaneHeader.setLayout(new java.awt.GridLayout(1, 0));

        approveQueueLabel.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        approveQueueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        approveQueueLabel.setText("Approval Queue Management");
        queuePaneHeader.add(approveQueueLabel);

        queuePane.add(queuePaneHeader, java.awt.BorderLayout.PAGE_START);

        queueFooterPanel.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        processNextBtn.setText("Process Next");
        processNextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processNextBtnActionPerformed(evt);
            }
        });
        queueFooterPanel.add(processNextBtn);

        viewQueueBtn.setText("View Queue");
        viewQueueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewQueueBtnActionPerformed(evt);
            }
        });
        queueFooterPanel.add(viewQueueBtn);

        queueRefreshBtn.setText("Refresh");
        queueRefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queueRefreshBtnActionPerformed(evt);
            }
        });
        queueFooterPanel.add(queueRefreshBtn);

        queuePane.add(queueFooterPanel, java.awt.BorderLayout.PAGE_END);

        queueTextArea.setEditable(false);
        queueTextArea.setColumns(20);
        queueTextArea.setRows(5);
        queueCentrePane.setViewportView(queueTextArea);

        javax.swing.GroupLayout queueCenterPanelLayout = new javax.swing.GroupLayout(queueCenterPanel);
        queueCenterPanel.setLayout(queueCenterPanelLayout);
        queueCenterPanelLayout.setHorizontalGroup(
            queueCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(queueCentrePane, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );
        queueCenterPanelLayout.setVerticalGroup(
            queueCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(queueCentrePane, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );

        queuePane.add(queueCenterPanel, java.awt.BorderLayout.CENTER);

        filterLabel.addTab("Approval Queue", queuePane);

        historyPane.setLayout(new java.awt.BorderLayout());

        historyHeader.setLayout(new java.awt.GridBagLayout());

        actionLabel.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        actionLabel.setText("Action History");
        historyHeader.add(actionLabel, new java.awt.GridBagConstraints());

        historyPane.add(historyHeader, java.awt.BorderLayout.PAGE_START);

        historyFooter.setLayout(new java.awt.GridBagLayout());

        historyUndoBtn.setText("Undo");
        historyUndoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyUndoBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        historyFooter.add(historyUndoBtn, gridBagConstraints);

        viewHistoryBtn.setText("View Stack");
        viewHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewHistoryBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        historyFooter.add(viewHistoryBtn, gridBagConstraints);

        historyClearBtn.setText("Clear");
        historyClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyClearBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        historyFooter.add(historyClearBtn, gridBagConstraints);

        historyPane.add(historyFooter, java.awt.BorderLayout.PAGE_END);

        showHistoryPane.setViewportView(historyList);

        javax.swing.GroupLayout historyCenterPanelLayout = new javax.swing.GroupLayout(historyCenterPanel);
        historyCenterPanel.setLayout(historyCenterPanelLayout);
        historyCenterPanelLayout.setHorizontalGroup(
            historyCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showHistoryPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );
        historyCenterPanelLayout.setVerticalGroup(
            historyCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showHistoryPane, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );

        historyPane.add(historyCenterPanel, java.awt.BorderLayout.CENTER);

        filterLabel.addTab("History", historyPane);

        adminDashboardPanel.add(filterLabel, java.awt.BorderLayout.CENTER);

        contentPanel.add(adminDashboardPanel, "adminDashboard");

        CitizenDashboardPanel.setLayout(new java.awt.BorderLayout());

        citizenHeaderPanel.setBackground(new java.awt.Color(52, 152, 219));
        citizenHeaderPanel.setPreferredSize(new java.awt.Dimension(0, 100));
        citizenHeaderPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Citzen Registration Portal");
        citizenHeaderPanel.add(jLabel1, new java.awt.GridBagConstraints());

        CitizenDashboardPanel.add(citizenHeaderPanel, java.awt.BorderLayout.PAGE_START);

        citizenWelcomePanel.setBackground(citizenHeaderPanel.getBackground());
        citizenWelcomePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        CitizenDashboardPanel.add(citizenWelcomePanel, java.awt.BorderLayout.LINE_START);

        citizenLogoutPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        citizenLogoutBtn.setBackground(new java.awt.Color(231, 76, 60));
        citizenLogoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        citizenLogoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        citizenLogoutBtn.setText("Logout");
        citizenLogoutBtn.setPreferredSize(new java.awt.Dimension(100, 35));
        citizenLogoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citizenLogoutBtnActionPerformed(evt);
            }
        });
        citizenLogoutPanel.add(citizenLogoutBtn);

        CitizenDashboardPanel.add(citizenLogoutPanel, java.awt.BorderLayout.LINE_END);

        citizenTabbedPane.setBackground(new java.awt.Color(255, 255, 255));

        registrationPane.setLayout(new java.awt.BorderLayout());

        registrationHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        registrationHeaderPanel.setLayout(new java.awt.GridBagLayout());

        registrationTitleLabel.setFont(new java.awt.Font("SimSun", 1, 28)); // NOI18N
        registrationTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registrationTitleLabel.setText("🆕 New Voter Registration");
        registrationHeaderPanel.add(registrationTitleLabel, new java.awt.GridBagConstraints());

        registrationPane.add(registrationHeaderPanel, java.awt.BorderLayout.PAGE_START);

        registrationFormPanel.setBackground(new java.awt.Color(255, 255, 255));
        registrationFormPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));
        registrationFormPanel.setLayout(new java.awt.GridLayout(10, 2, 20, 20));

        cidRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cidRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cidRegLabel.setText("CitizenshipNumber:*");
        registrationFormPanel.add(cidRegLabel);

        cidRegField.setMinimumSize(new java.awt.Dimension(200, 30));
        cidRegField.setPreferredSize(new java.awt.Dimension(200, 50));
        registrationFormPanel.add(cidRegField);

        nameRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nameRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameRegLabel.setText("Full Name:*");
        registrationFormPanel.add(nameRegLabel);

        nameRegField.setPreferredSize(new java.awt.Dimension(64, 30));
        registrationFormPanel.add(nameRegField);

        phoneRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        phoneRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        phoneRegLabel.setText("Phone Number:*");
        registrationFormPanel.add(phoneRegLabel);

        phoneRegField.setPreferredSize(new java.awt.Dimension(64, 30));
        registrationFormPanel.add(phoneRegField);

        genderRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        genderRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        genderRegLabel.setText("Gender:*");
        registrationFormPanel.add(genderRegLabel);

        genderRegCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Gender --", "Male", "Female", "Others", " " }));
        genderRegCombo.setName(""); // NOI18N
        registrationFormPanel.add(genderRegCombo);

        dobRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dobRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dobRegLabel.setText("Date of Birth:*");
        registrationFormPanel.add(dobRegLabel);
        registrationFormPanel.add(dobRegChooser);

        provinceRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        provinceRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        provinceRegLabel.setText("Province:*");
        registrationFormPanel.add(provinceRegLabel);

        provinceRegCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select Province --", "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim" }));
        provinceRegCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceRegComboActionPerformed(evt);
            }
        });
        registrationFormPanel.add(provinceRegCombo);

        districtRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        districtRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        districtRegLabel.setText("District:*");
        registrationFormPanel.add(districtRegLabel);

        districtRegCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SelectDistrict --" }));
        registrationFormPanel.add(districtRegCombo);

        municipalityRegLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        municipalityRegLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        municipalityRegLabel.setText("Municipality:*");
        registrationFormPanel.add(municipalityRegLabel);

        municipalityRegField.setPreferredSize(new java.awt.Dimension(64, 30));
        registrationFormPanel.add(municipalityRegField);

        voteCenterLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        voteCenterLabel.setText("Vote Center:*");
        registrationFormPanel.add(voteCenterLabel);
        registrationFormPanel.add(voteCenterRegField);
        registrationFormPanel.add(voidi);
        registrationFormPanel.add(voidii);

        registrationPane.add(registrationFormPanel, java.awt.BorderLayout.CENTER);

        registrationFooterPanel.setBackground(new java.awt.Color(255, 255, 255));
        registrationFooterPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 40, 20, 40));
        registrationFooterPanel.setLayout(new java.awt.GridLayout(1, 2, 50, 10));

        submitRegBtn.setBackground(new java.awt.Color(46, 204, 113));
        submitRegBtn.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        submitRegBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitRegBtn.setText("Submit Registration");
        submitRegBtn.setPreferredSize(new java.awt.Dimension(189, 50));
        submitRegBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitRegBtnActionPerformed(evt);
            }
        });
        registrationFooterPanel.add(submitRegBtn);

        clearRegBtn.setBackground(new java.awt.Color(241, 196, 15));
        clearRegBtn.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        clearRegBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearRegBtn.setText("Clear Form");
        clearRegBtn.setPreferredSize(new java.awt.Dimension(124, 50));
        clearRegBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearRegBtnActionPerformed(evt);
            }
        });
        registrationFooterPanel.add(clearRegBtn);

        registrationPane.add(registrationFooterPanel, java.awt.BorderLayout.PAGE_END);

        citizenTabbedPane.addTab("Register", registrationPane);

        statusCheckPane.setBackground(new java.awt.Color(255, 255, 255));
        statusCheckPane.setLayout(new java.awt.BorderLayout());

        statusHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        statusHeaderPanel.setPreferredSize(new java.awt.Dimension(505, 80));

        statusHeaderLabel.setFont(new java.awt.Font("SimSun", 1, 28)); // NOI18N
        statusHeaderLabel.setText("Check Application Status");
        statusHeaderPanel.add(statusHeaderLabel);

        statusCheckPane.add(statusHeaderPanel, java.awt.BorderLayout.PAGE_START);

        statusMainPanel.setLayout(new java.awt.BorderLayout());

        statusInputPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 30));

        cidSearchLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cidSearchLabel.setText("Enter Citizenship Number:");
        statusInputPanel.add(cidSearchLabel);

        statusCidField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statusCidField.setPreferredSize(new java.awt.Dimension(200, 35));
        statusInputPanel.add(statusCidField);

        checkStatusBtn.setBackground(new java.awt.Color(52, 152, 219));
        checkStatusBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkStatusBtn.setForeground(new java.awt.Color(255, 255, 255));
        checkStatusBtn.setText("Check Status");
        checkStatusBtn.setPreferredSize(new java.awt.Dimension(150, 35));
        checkStatusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkStatusBtnActionPerformed(evt);
            }
        });
        statusInputPanel.add(checkStatusBtn);

        statusMainPanel.add(statusInputPanel, java.awt.BorderLayout.PAGE_END);

        statusResultPanel.setBackground(new java.awt.Color(236, 240, 241));
        statusResultPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        statusResultPanel.setLayout(new java.awt.GridBagLayout());

        statusCardPanel.setBackground(new java.awt.Color(255, 255, 255));
        statusCardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 152, 219), 2));
        statusCardPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        statusCardPanel.setLayout(new java.awt.GridLayout(10, 1, 10, 15));

        statusNameLabel.setText("Name:");
        statusCardPanel.add(statusNameLabel);

        statusCidDisplayLabel.setText("Citizenship Number:");
        statusCardPanel.add(statusCidDisplayLabel);

        statusGenderLabel.setText("Gender:");
        statusCardPanel.add(statusGenderLabel);

        statusAgeLabel.setText("Age:");
        statusCardPanel.add(statusAgeLabel);

        statusProvinceLabel.setText("Province:");
        statusCardPanel.add(statusProvinceLabel);

        statusDistrictLabel.setText("District:");
        statusCardPanel.add(statusDistrictLabel);

        statusCurrentLabel.setText("Current Status:");
        statusCardPanel.add(statusCurrentLabel);

        statusProgressBar.setStringPainted(true);
        statusCardPanel.add(statusProgressBar);

        statusMessageLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        statusCardPanel.add(statusMessageLabel);

        statusResultPanel.add(statusCardPanel, new java.awt.GridBagConstraints());

        statusMainPanel.add(statusResultPanel, java.awt.BorderLayout.CENTER);

        statusCheckPane.add(statusMainPanel, java.awt.BorderLayout.CENTER);

        citizenTabbedPane.addTab("Status", statusCheckPane);

        editRecordPane.setLayout(new java.awt.BorderLayout());

        searchCidLabel.setText("Enter Citizenship Number:");
        editSearchPanel.add(searchCidLabel);

        searchCidField.setPreferredSize(new java.awt.Dimension(150, 30));
        editSearchPanel.add(searchCidField);

        searchMyRecordButton.setText("Find Record");
        searchMyRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMyRecordButtonActionPerformed(evt);
            }
        });
        editSearchPanel.add(searchMyRecordButton);

        clearSearchBtn.setText("Clear");
        clearSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSearchBtnActionPerformed(evt);
            }
        });
        editSearchPanel.add(clearSearchBtn);

        editRecordPane.add(editSearchPanel, java.awt.BorderLayout.CENTER);

        editCenterPanel.setLayout(new java.awt.BorderLayout());

        editFormPanel.setLayout(new java.awt.GridLayout(11, 2, 20, 20));

        cidEditLabel.setText("Citizenship Number");
        editFormPanel.add(cidEditLabel);
        editFormPanel.add(cidEditField);

        nameEditLabel.setText("Full Name:");
        editFormPanel.add(nameEditLabel);
        editFormPanel.add(nameEditField);

        phoneEditLabel.setText("Phone Number:");
        editFormPanel.add(phoneEditLabel);
        editFormPanel.add(phoneEditField);

        genderEditLabel.setText("Gender");
        editFormPanel.add(genderEditLabel);

        genderEditCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        editFormPanel.add(genderEditCombo);

        dobEditLabel.setText("Date of Birth");
        editFormPanel.add(dobEditLabel);
        editFormPanel.add(dobEditChooser);

        provinceEditLabel.setText("Province");
        editFormPanel.add(provinceEditLabel);

        provinceEditCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim" }));
        provinceEditCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceEditComboActionPerformed(evt);
            }
        });
        editFormPanel.add(provinceEditCombo);

        districtEditLabel.setText("District");
        editFormPanel.add(districtEditLabel);

        districtEditCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        editFormPanel.add(districtEditCombo);

        municipalityEditLabel.setText("Municipality");
        editFormPanel.add(municipalityEditLabel);
        editFormPanel.add(municipalityEditField);

        voteCenterEditLabel.setText("Vote Center");
        editFormPanel.add(voteCenterEditLabel);
        editFormPanel.add(voteCenterEditField);

        statusEditLabel.setText("Current Status:");
        editFormPanel.add(statusEditLabel);
        editFormPanel.add(statusEditDisplay);

        editFooterPanel.setLayout(new java.awt.GridLayout(1, 3, 20, 10));

        saveEditBtn.setText("Save Changes");
        saveEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveEditBtnActionPerformed(evt);
            }
        });
        editFooterPanel.add(saveEditBtn);

        cancelEditBtn.setText("Cancel");
        cancelEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEditBtnActionPerformed(evt);
            }
        });
        editFooterPanel.add(cancelEditBtn);

        deleteMyRecordBtn.setText("Delete Record");
        deleteMyRecordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMyRecordBtnActionPerformed(evt);
            }
        });
        editFooterPanel.add(deleteMyRecordBtn);

        editFormPanel.add(editFooterPanel);

        editFormScrollPane.setViewportView(editFormPanel);

        editCenterPanel.add(editFormScrollPane, java.awt.BorderLayout.CENTER);

        editRecordPane.add(editCenterPanel, java.awt.BorderLayout.PAGE_END);

        citizenTabbedPane.addTab("Edit Record", editRecordPane);

        CitizenDashboardPanel.add(citizenTabbedPane, java.awt.BorderLayout.CENTER);

        contentPanel.add(CitizenDashboardPanel, "citizenDashboard");

        mainContentPanel.add(contentPanel, java.awt.BorderLayout.CENTER);

        cardPanel.add(mainContentPanel, "mainContent");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
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

    private void processNextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processNextBtnActionPerformed
        // TODO add your handling code here:
        handleProcessNext();
    }//GEN-LAST:event_processNextBtnActionPerformed

    private void viewQueueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewQueueBtnActionPerformed
        // TODO add your handling code here:
        handleViewQueue();
    }//GEN-LAST:event_viewQueueBtnActionPerformed

    private void queueRefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queueRefreshBtnActionPerformed
        // TODO add your handling code here:
        adminController.refreshQueue();
        refreshDashboard();
        showMessage("Queue refreshed successfully");
    }//GEN-LAST:event_queueRefreshBtnActionPerformed

    private void historyUndoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyUndoBtnActionPerformed
        // TODO add your handling code here:
        handleUndo();
    }//GEN-LAST:event_historyUndoBtnActionPerformed

    private void viewHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewHistoryBtnActionPerformed
        // TODO add your handling code here:
        refreshHistoryList();
    }//GEN-LAST:event_viewHistoryBtnActionPerformed

    private void historyClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyClearBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Clear history?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            adminController.clearHistory();
            refreshHistoryList();
            refreshDashboard();
        }
    }//GEN-LAST:event_historyClearBtnActionPerformed

    private void extSystemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extSystemBtnActionPerformed
        // TODO add your handling code here:
        try {
            int choice = JOptionPane.showConfirmDialog(this, "Are yousure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } catch (Exception ex) {
            showError("Something went wrong while logging out");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_extSystemBtnActionPerformed

    private void resetSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSortBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) sortTable.getModel();
        model.setRowCount(0);
        showMessage("Table cleared");
    }//GEN-LAST:event_resetSortBtnActionPerformed

    private void sortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortBtnActionPerformed
        // TODO add your handling code here:
        handleSort();
    }//GEN-LAST:event_sortBtnActionPerformed

    private void applyFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyFilterBtnActionPerformed
        // TODO add your handling code here:
        handleApplyFilter();
    }//GEN-LAST:event_applyFilterBtnActionPerformed

    private void provinceFilterComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceFilterComboActionPerformed
        // TODO add your handling code here:
        updateDistrictFilterCombo();
    }//GEN-LAST:event_provinceFilterComboActionPerformed

    private void linearSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linearSearchBtnActionPerformed
        // TODO add your handling code here:
        handleLinearSearch();
    }//GEN-LAST:event_linearSearchBtnActionPerformed

    private void binarySearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarySearchBtnActionPerformed
        // TODO add your handling code here:
        handleBinarySearch();
    }//GEN-LAST:event_binarySearchBtnActionPerformed

    private void citizenChoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citizenChoiceBtnActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, "citizenDashboard");
    }//GEN-LAST:event_citizenChoiceBtnActionPerformed

    private void provinceRegComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceRegComboActionPerformed
        // TODO add your handling code here:
        updateDistrictRegComboBox();
    }//GEN-LAST:event_provinceRegComboActionPerformed

    private void checkStatusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkStatusBtnActionPerformed
        // TODO add your handling code here:
        try {
            String cid = statusCidField.getText().trim();

            if (cid.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter citizenship number.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                statusCidField.requestFocus();
                return;
            }

            CitizenModel citizen = citizenController.checkMyStatus(cid);

            if (citizen != null) {
                displayStatus(citizen);
            } else {
                clearStatusDisplay();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_checkStatusBtnActionPerformed

    private void displayStatus(CitizenModel citizen) {
        statusNameLabel.setText("Name: " + citizen.getVoterName());
        statusCidDisplayLabel.setText("Citizenship: " + citizen.getCitizenshipNumber());
        statusGenderLabel.setText("Gender: " + citizen.getGender().getDisplay());
        statusAgeLabel.setText("Age: " + citizen.getAge() + " years");
        statusProvinceLabel.setText("Province: " + citizen.getProvince());
        statusDistrictLabel.setText("District: " + citizen.getDistrict());

        String status = citizen.getStatus().getDisplay();
        statusCurrentLabel.setText("Status: " + status);

        switch (citizen.getStatus()) {
            case PENDING:
                statusCurrentLabel.setForeground(new Color(255, 165, 0));
                statusProgressBar.setValue(50);
                statusProgressBar.setForeground(new Color(255, 165, 0));
                statusMessageLabel.setText("⏳ Your application is under review.");
                statusMessageLabel.setForeground(new Color(255, 165, 0));
                break;

            case APPROVED:
                statusCurrentLabel.setForeground(new Color(40, 167, 69));
                statusProgressBar.setValue(100);
                statusProgressBar.setForeground(new Color(40, 167, 69));
                statusMessageLabel.setText("✅ Congratulations! Your application has been approved.");
                statusMessageLabel.setForeground(new Color(40, 167, 69));
                break;

            case REJECTED:
                statusCurrentLabel.setForeground(new Color(220, 53, 69));
                statusProgressBar.setValue(0);
                statusProgressBar.setForeground(new Color(220, 53, 69));
                statusMessageLabel.setText("❌ Your application has been rejected. Please contact admin.");
                statusMessageLabel.setForeground(new Color(220, 53, 69));
                break;
        }

        statusCardPanel.setVisible(true);
    }

    private void clearStatusDisplay() {
        statusNameLabel.setText("Name: ");
        statusCidDisplayLabel.setText("Citizenship: ");
        statusGenderLabel.setText("Gender: ");
        statusAgeLabel.setText("Age: ");
        statusProvinceLabel.setText("Province: ");
        statusDistrictLabel.setText("District: ");
        statusCurrentLabel.setText("Status: ");
        statusProgressBar.setValue(0);
        statusMessageLabel.setText("");
        statusCardPanel.setVisible(false);
    }

    private void citizenLogoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citizenLogoutBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Confirm Logout.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "selectRole");

            showMessage("Logged out Successfully!");
        }
    }//GEN-LAST:event_citizenLogoutBtnActionPerformed

    private void searchMyRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMyRecordButtonActionPerformed
        // TODO add your handling code here:
        try {
            String cid = searchCidField.getText().trim();

            if (cid.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter your citizenship number.",
                        "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                searchCidField.requestFocus();
                return;
            }

            CitizenModel citizen = citizenController.findMyRecord(cid);

            if (citizen != null) {
                populateEditForm(citizen);
                checkEditPermission(citizen);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchMyRecordButtonActionPerformed

    private void saveEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveEditBtnActionPerformed
        // TODO add your handling code here:
        try {
            // Validate all fields
            if (!validateEditForm()) {
                return;
            }

            // Get values
            String cid = cidEditField.getText().trim();
            String name = nameEditField.getText().trim();
            String phone = phoneEditField.getText().trim();
            String genderStr = (String) genderEditCombo.getSelectedItem();
            String province = (String) provinceEditCombo.getSelectedItem();
            String district = (String) districtEditCombo.getSelectedItem();
            String municipality = municipalityEditField.getText().trim();
            String voteCenter = voteCenterEditField.getText().trim();

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
                    throw new IllegalArgumentException("Invalid gender");
            }

            java.util.Date selectedDate = dobEditChooser.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this,
                        "Date of birth is required!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            LocalDate dob = selectedDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            // Update record
            boolean success = citizenController.updateMyRecord(cid, phone, name, gender,
                    province, district, municipality,
                    voteCenter, dob);

            if (success) {
                clearEditForm();
                searchCidField.setText("");
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input: " + e.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error saving changes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveEditBtnActionPerformed

    private void deleteMyRecordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMyRecordBtnActionPerformed
        // TODO add your handling code here:
        try {
            String cid = cidEditField.getText().trim();

            if (cid.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No record loaded.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = citizenController.deleteMyRecord(cid);

            if (success) {
                clearEditForm();
                searchCidField.setText("");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error deleting record: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteMyRecordBtnActionPerformed

    private void submitRegBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitRegBtnActionPerformed
        // TODO add your handling code here:
        try {
            // Get all field values
            String cid = cidRegField.getText().trim();
            String name = nameRegField.getText().trim();
            String phone = phoneRegField.getText().trim();
            String genderStr = (String) genderRegCombo.getSelectedItem();
            String province = (String) provinceRegCombo.getSelectedItem();
            String district = (String) districtRegCombo.getSelectedItem();
            String municipality = municipalityRegField.getText().trim();
            String voteCenter = voteCenterRegField.getText().trim();
            java.util.Date selectedDate = dobRegChooser.getDate();

            // Basic validation
            if (cid.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                showError("Please fill all required fields marked with *");
                return;
            }

            if (genderStr == null || genderStr.equals("-- Select Gender --")) {
                showError("Please select gender");
                return;
            }

            if (province == null || province.equals("-- Select Province --")) {
                showError("Please select province");
                return;
            }

            if (district == null || district.equals("-- Select District --")) {
                showError("Please select district");
                return;
            }

            if (selectedDate == null) {
                showError("Please select date of birth");
                return;
            }

            if (municipality.isEmpty() || voteCenter.isEmpty()) {
                showError("Please fill all required fields");
                return;
            }

            // Convert gender string to enum
            Gender gender;
            switch (genderStr) {
                case "Male":
                    gender = Gender.MALE;
                    break;
                case "Female":
                    gender = Gender.FEMALE;
                    break;
                case "Others":
                    gender = Gender.OTHER;
                    break;
                default:
                    showError("Invalid gender selection");
                    return;
            }

            // Convert date
            LocalDate dob = selectedDate.toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            // Submit registration using CitizenController
            boolean success = citizenController.addCitizenFromUI(
                    cid, phone, name, gender,
                    province, district, municipality,
                    voteCenter, dob
            );

            if (success) {
                // Clear form after successful registration
                clearRegistrationForm();

                // Show success message with next steps
                JOptionPane.showMessageDialog(this,
                        "Registration submitted successfully!\n\n"
                        + "Your application status: PENDING\n"
                        + "You can check your status in the 'Status' tab.",
                        "Registration Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            showError("Registration error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_submitRegBtnActionPerformed

    private void clearRegBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearRegBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear all fields?",
                "Confirm Clear",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            clearRegistrationForm();
            showMessage("Form cleared");
        }
    }//GEN-LAST:event_clearRegBtnActionPerformed

    private void refreshManageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshManageBtnActionPerformed
        // TODO add your handling code here:
        try {
            refreshCitizensTable();
            refreshDashboard();
            showMessage("Citizens table refreshed successfully!");
        } catch (Exception e) {
            showError("Error refreshing table: " + e.getMessage());
        }
    }//GEN-LAST:event_refreshManageBtnActionPerformed

    private void clearSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSearchBtnActionPerformed
        // TODO add your handling code here:
        searchCidField.setText("");

        // Clear and disable edit form
        clearEditForm();

        showMessage("Search cleared");
    }//GEN-LAST:event_clearSearchBtnActionPerformed

    private void cancelEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditBtnActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel?\nAll unsaved changes will be lost.",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            clearEditForm();
            searchCidField.setText("");
            showMessage("Edit cancelled");
        }
    }//GEN-LAST:event_cancelEditBtnActionPerformed

    private void provinceEditComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceEditComboActionPerformed
        // TODO add your handling code here:
        updateDistrictEditCombo();
    }//GEN-LAST:event_provinceEditComboActionPerformed

    private void clearRegistrationForm() {
        cidRegField.setText("");
        nameRegField.setText("");
        phoneRegField.setText("");
        genderRegCombo.setSelectedIndex(0);
        dobRegChooser.setDate(null);
        provinceRegCombo.setSelectedIndex(0);
        districtRegCombo.removeAllItems();
        districtRegCombo.addItem("-- Select District --");
        municipalityRegField.setText("");
        voteCenterRegField.setText("");
    }

    private boolean validateEditForm() {
        if (nameEditField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameEditField.requestFocus();
            return false;
        }

        if (!nameEditField.getText().trim().matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(this, "Name should contain only letters!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameEditField.requestFocus();
            return false;
        }

        if (phoneEditField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneEditField.requestFocus();
            return false;
        }

        if (!phoneEditField.getText().trim().matches("9\\d{9}")) {
            JOptionPane.showMessageDialog(this,
                    "Phone must be 10 digits starting with 9!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            phoneEditField.requestFocus();
            return false;
        }

        if (municipalityEditField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Municipality is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            municipalityEditField.requestFocus();
            return false;
        }

        if (voteCenterEditField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vote center is required!",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            voteCenterEditField.requestFocus();
            return false;
        }

        return true;
    }

    private void populateEditForm(CitizenModel citizen) {
        cidEditField.setText(citizen.getCitizenshipNumber());
        nameEditField.setText(citizen.getVoterName());
        phoneEditField.setText(citizen.getPhoneNumber());
        genderEditCombo.setSelectedItem(citizen.getGender().getDisplay());

        LocalDate dob = citizen.getDateOfBirth();
        java.util.Date date = java.util.Date.from(
                dob.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        dobEditChooser.setDate(date);

        provinceEditCombo.setSelectedItem(citizen.getProvince());
        updateDistrictEditCombo();
        districtEditCombo.setSelectedItem(citizen.getDistrict());
        municipalityEditField.setText(citizen.getMunicipality());
        voteCenterEditField.setText(citizen.getVoteCenter());

        // Display status with color
        String status = citizen.getStatus().getDisplay();
        statusEditDisplay.setText(status);

        switch (citizen.getStatus()) {
            case PENDING:
                statusEditDisplay.setForeground(new Color(255, 165, 0));
                break;
            case APPROVED:
                statusEditDisplay.setForeground(new Color(40, 167, 69));
                break;
            case REJECTED:
                statusEditDisplay.setForeground(new Color(220, 53, 69));
                break;
        }

        setFormEnabled(true);
    }

    private void checkEditPermission(CitizenModel citizen) {
        if (citizen.getStatus() == RegistrationStatus.APPROVED) {
            setFormEnabled(false);
            saveEditBtn.setEnabled(false);
            deleteMyRecordBtn.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                    "Your record is APPROVED and cannot be edited.\nPlease contact admin for changes.",
                    "Edit Restricted",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            setFormEnabled(true);
            saveEditBtn.setEnabled(true);
            deleteMyRecordBtn.setEnabled(true);
        }
    }

    private void setFormEnabled(boolean enabled) {
        nameEditField.setEnabled(enabled);
        phoneEditField.setEnabled(enabled);
        genderEditCombo.setEnabled(enabled);
        dobEditChooser.setEnabled(enabled);
        provinceEditCombo.setEnabled(enabled);
        districtEditCombo.setEnabled(enabled);
        municipalityEditField.setEnabled(enabled);
        voteCenterEditField.setEnabled(enabled);
        saveEditBtn.setEnabled(enabled);
        deleteMyRecordBtn.setEnabled(enabled);
    }

    private void clearEditForm() {
        cidEditField.setText("");
        nameEditField.setText("");
        phoneEditField.setText("");
        genderEditCombo.setSelectedIndex(0);
        dobEditChooser.setDate(null);
        provinceEditCombo.setSelectedIndex(0);
        districtEditCombo.removeAllItems();
        municipalityEditField.setText("");
        voteCenterEditField.setText("");
        statusEditDisplay.setText("");
        setFormEnabled(false);
    }

    private void updateDistrictEditCombo() {
        String selectedProvince = (String) provinceEditCombo.getSelectedItem();
        districtEditCombo.removeAllItems();

        if (selectedProvince == null) {
            return;
        }

        // Use same logic as AddCitizenDialog
        switch (selectedProvince) {
            case "Koshi":
                districtEditCombo.addItem("Bhojpur");
                districtEditCombo.addItem("Dhankuta");
                districtEditCombo.addItem("Ilam");
                districtEditCombo.addItem("Jhapa");
                districtEditCombo.addItem("Khotang");
                districtEditCombo.addItem("Morang");
                districtEditCombo.addItem("Okhaldhunga");
                districtEditCombo.addItem("Panchthar");
                districtEditCombo.addItem("Sankhuwasabha");
                districtEditCombo.addItem("Solukhumbu");
                districtEditCombo.addItem("Sunsari");
                districtEditCombo.addItem("Taplejung");
                districtEditCombo.addItem("Terhathum");
                districtEditCombo.addItem("Udayapur");
                break;

            case "Madhesh":
                districtEditCombo.addItem("Bara");
                districtEditCombo.addItem("Dhanusha");
                districtEditCombo.addItem("Mahottari");
                districtEditCombo.addItem("Parsa");
                districtEditCombo.addItem("Rautahat");
                districtEditCombo.addItem("Saptari");
                districtEditCombo.addItem("Sarlahi");
                districtEditCombo.addItem("Siraha");
                break;

            case "Bagmati":
                districtEditCombo.addItem("Bhaktapur");
                districtEditCombo.addItem("Chitwan");
                districtEditCombo.addItem("Dhading");
                districtEditCombo.addItem("Dolakha");
                districtEditCombo.addItem("Kathmandu");
                districtEditCombo.addItem("Kavrepalanchok");
                districtEditCombo.addItem("Lalitpur");
                districtEditCombo.addItem("Makwanpur");
                districtEditCombo.addItem("Nuwakot");
                districtEditCombo.addItem("Ramechhap");
                districtEditCombo.addItem("Rasuwa");
                districtEditCombo.addItem("Sindhuli");
                districtEditCombo.addItem("Sindhupalchok");
                break;

            case "Gandaki":
                districtEditCombo.addItem("Baglung");
                districtEditCombo.addItem("Gorkha");
                districtEditCombo.addItem("Kaski");
                districtEditCombo.addItem("Lamjung");
                districtEditCombo.addItem("Manang");
                districtEditCombo.addItem("Mustang");
                districtEditCombo.addItem("Myagdi");
                districtEditCombo.addItem("Nawalpur");
                districtEditCombo.addItem("Parbat");
                districtEditCombo.addItem("Syangja");
                districtEditCombo.addItem("Tanahun");
                break;

            case "Lumbini":
                districtEditCombo.addItem("Arghakhanchi");
                districtEditCombo.addItem("Banke");
                districtEditCombo.addItem("Bardiya");
                districtEditCombo.addItem("Dang");
                districtEditCombo.addItem("Gulmi");
                districtEditCombo.addItem("Kapilvastu");
                districtEditCombo.addItem("Palpa");
                districtEditCombo.addItem("Parasi");
                districtEditCombo.addItem("Pyuthan");
                districtEditCombo.addItem("Rolpa");
                districtEditCombo.addItem("Rukum East");
                districtEditCombo.addItem("Rupandehi");
                break;

            case "Karnali":
                districtEditCombo.addItem("Dailekh");
                districtEditCombo.addItem("Dolpa");
                districtEditCombo.addItem("Humla");
                districtEditCombo.addItem("Jajarkot");
                districtEditCombo.addItem("Jumla");
                districtEditCombo.addItem("Kalikot");
                districtEditCombo.addItem("Mugu");
                districtEditCombo.addItem("Rukum West");
                districtEditCombo.addItem("Salyan");
                districtEditCombo.addItem("Surkhet");
                break;

            case "Sudurpashchim":
                districtEditCombo.addItem("Achham");
                districtEditCombo.addItem("Baitadi");
                districtEditCombo.addItem("Bajhang");
                districtEditCombo.addItem("Bajura");
                districtEditCombo.addItem("Dadeldhura");
                districtEditCombo.addItem("Darchula");
                districtEditCombo.addItem("Doti");
                districtEditCombo.addItem("Kailali");
                districtEditCombo.addItem("Kanchanpur");
                break;
        }
    }

    private void updateDistrictFilterCombo() {
        String selectedProvince = (String) provinceFilterCombo.getSelectedItem();
        districtFilterCombo.removeAllItems();
        districtFilterCombo.addItem("All Districts");

        if (selectedProvince == null || selectedProvince.equals("All Provinces")) {
            return;
        }

        // Same district logic as in AddCitizenDialog
        switch (selectedProvince) {
            case "Koshi":
                districtFilterCombo.addItem("Bhojpur");
                districtFilterCombo.addItem("Dhankuta");
                districtFilterCombo.addItem("Ilam");
                districtFilterCombo.addItem("Jhapa");
                districtFilterCombo.addItem("Khotang");
                districtFilterCombo.addItem("Morang");
                districtFilterCombo.addItem("Okhaldhunga");
                districtFilterCombo.addItem("Panchthar");
                districtFilterCombo.addItem("Sankhuwasabha");
                districtFilterCombo.addItem("Solukhumbu");
                districtFilterCombo.addItem("Sunsari");
                districtFilterCombo.addItem("Taplejung");
                districtFilterCombo.addItem("Terhathum");
                districtFilterCombo.addItem("Udayapur");
                break;
            case "Madhesh":
                districtFilterCombo.addItem("Bara");
                districtFilterCombo.addItem("Dhanusha");
                districtFilterCombo.addItem("Mahottari");
                districtFilterCombo.addItem("Parsa");
                districtFilterCombo.addItem("Rautahat");
                districtFilterCombo.addItem("Saptari");
                districtFilterCombo.addItem("Sarlahi");
                districtFilterCombo.addItem("Siraha");
                break;
            case "Bagmati":
                districtFilterCombo.addItem("Bhaktapur");
                districtFilterCombo.addItem("Chitwan");
                districtFilterCombo.addItem("Dhading");
                districtFilterCombo.addItem("Dolakha");
                districtFilterCombo.addItem("Kathmandu");
                districtFilterCombo.addItem("Kavrepalanchok");
                districtFilterCombo.addItem("Lalitpur");
                districtFilterCombo.addItem("Makwanpur");
                districtFilterCombo.addItem("Nuwakot");
                districtFilterCombo.addItem("Ramechhap");
                districtFilterCombo.addItem("Rasuwa");
                districtFilterCombo.addItem("Sindhuli");
                districtFilterCombo.addItem("Sindhupalchok");
                break;
            case "Gandaki":
                districtFilterCombo.addItem("Baglung");
                districtFilterCombo.addItem("Gorkha");
                districtFilterCombo.addItem("Kaski");
                districtFilterCombo.addItem("Lamjung");
                districtFilterCombo.addItem("Manang");
                districtFilterCombo.addItem("Mustang");
                districtFilterCombo.addItem("Myagdi");
                districtFilterCombo.addItem("Nawalpur");
                districtFilterCombo.addItem("Parbat");
                districtFilterCombo.addItem("Syangja");
                districtFilterCombo.addItem("Tanahun");
                break;
            case "Lumbini":
                districtFilterCombo.addItem("Arghakhanchi");
                districtFilterCombo.addItem("Banke");
                districtFilterCombo.addItem("Bardiya");
                districtFilterCombo.addItem("Dang");
                districtFilterCombo.addItem("Gulmi");
                districtFilterCombo.addItem("Kapilvastu");
                districtFilterCombo.addItem("Palpa");
                districtFilterCombo.addItem("Parasi");
                districtFilterCombo.addItem("Pyuthan");
                districtFilterCombo.addItem("Rolpa");
                districtFilterCombo.addItem("Rukum East");
                districtFilterCombo.addItem("Rupandehi");
                break;
            case "Karnali":
                districtFilterCombo.addItem("Dailekh");
                districtFilterCombo.addItem("Dolpa");
                districtFilterCombo.addItem("Humla");
                districtFilterCombo.addItem("Jajarkot");
                districtFilterCombo.addItem("Jumla");
                districtFilterCombo.addItem("Kalikot");
                districtFilterCombo.addItem("Mugu");
                districtFilterCombo.addItem("Rukum West");
                districtFilterCombo.addItem("Salyan");
                districtFilterCombo.addItem("Surkhet");
                break;
            case "Sudurpashchim":
                districtFilterCombo.addItem("Achham");
                districtFilterCombo.addItem("Baitadi");
                districtFilterCombo.addItem("Bajhang");
                districtFilterCombo.addItem("Bajura");
                districtFilterCombo.addItem("Dadeldhura");
                districtFilterCombo.addItem("Darchula");
                districtFilterCombo.addItem("Doti");
                districtFilterCombo.addItem("Kailali");
                districtFilterCombo.addItem("Kanchanpur");
                break;
        }

    }

    public void handleApplyFilter() {
        try {
            String ageGroup = (String) ageGroupFilterCombo.getSelectedItem();
            String province = (String) provinceFilterCombo.getSelectedItem();
            String district = (String) districtFilterCombo.getSelectedItem();

            // Get all citizens
            ArrayList<CitizenModel> allCitizens = new ArrayList<>(citizenRegistry.getAllCitizens());
            ArrayList<CitizenModel> filteredCitizens = new ArrayList<>();

            // Apply filters
            for (CitizenModel c : allCitizens) {
                boolean passesFilter = true;

                // Age filter
                if (!ageGroup.equals("All Ages")) {
                    int age = c.getAge();
                    switch (ageGroup) {
                        case "18-25 years":
                            passesFilter = (age >= 18 && age <= 25);
                            break;
                        case "26-40 years":
                            passesFilter = (age >= 26 && age <= 40);
                            break;
                        case "41-60 years":
                            passesFilter = (age >= 41 && age <= 60);
                            break;
                        case "61+ years":
                            passesFilter = (age >= 61);
                            break;
                    }
                }

                // Province filter
                if (passesFilter && !province.equals("All Provinces")) {
                    passesFilter = c.getProvince().equals(province);
                }

                // District filter
                if (passesFilter && !district.equals("All Districts")) {
                    passesFilter = c.getDistrict().equals(district);
                }

                if (passesFilter) {
                    filteredCitizens.add(c);
                }
            }

            // Apply sorting if selected
            if (citizenshipnoSortBtn.isSelected()) {
                CitizenSorter.sortByCitizenshipNumber(filteredCitizens);
            } else if (nameSortBtn.isSelected()) {
                selectionSortByName(filteredCitizens);
            } else if (ageSortBtn.isSelected()) {
                CitizenSorter.sortByAge(filteredCitizens);
            }

            if (sortChoiceBtn2.isSelected()) {
                Collections.reverse(filteredCitizens);
            }

            // Display in table
            DefaultTableModel model = (DefaultTableModel) sortTable.getModel();
            model.setRowCount(0);
            for (CitizenModel c : filteredCitizens) {
                model.addRow(new Object[]{
                    c.getCitizenshipNumber(),
                    c.getVoterName(),
                    c.getAge(),
                    c.getGender().getDisplay(),
                    c.getPhoneNumber(),
                    c.getDistrict(),
                    c.getProvince(),
                    c.getStatus().getDisplay()
                });
            }

            showMessage(String.format("Filter Applied!\nShowing %d of %d citizens",
                    filteredCitizens.size(), allCitizens.size()));

        } catch (Exception e) {
            showError("Filter error: " + e.getMessage());
        }
    }

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
            if (!citizenshipNoBtn.isSelected() && !nameBtn.isSelected()
                    && !phoneNumberBtn.isSelected() && !districtSearchBtn.isSelected()
                    && !provinceSearchBtn.isSelected()) {
                showError("Please select a search option");
                return;
            }

            ArrayList<CitizenModel> all = new ArrayList<>(citizenRegistry.getAllCitizens());
            List<CitizenModel> results = new ArrayList<>();
            long start = System.currentTimeMillis();

            if (citizenshipNoBtn.isSelected()) {
                for (CitizenModel c : all) {
                    if (c.getCitizenshipNumber().contains(term)) {
                        results.add(c);
                    }
                }
            } else if (nameBtn.isSelected()) {
                String searchTerm = term.toLowerCase();
                for (CitizenModel c : all) {
                    if (c.getVoterName().toLowerCase().contains(searchTerm)) {
                        results.add(c);
                    }
                }
            } else if (phoneNumberBtn.isSelected()) {
                for (CitizenModel c : all) {
                    if (c.getPhoneNumber().contains(term)) {
                        results.add(c);
                    }
                }
            } else if (districtSearchBtn.isSelected()) {
                // NEW: Search by district
                String searchTerm = term.toLowerCase();
                for (CitizenModel c : all) {
                    if (c.getDistrict().toLowerCase().contains(searchTerm)) {
                        results.add(c);
                    }
                }
            } else if (provinceSearchBtn.isSelected()) {
                // NEW: Search by province
                String searchTerm = term.toLowerCase();
                for (CitizenModel c : all) {
                    if (c.getProvince().toLowerCase().contains(searchTerm)) {
                        results.add(c);
                    }
                }
            }

            long time = System.currentTimeMillis() - start;
            displaySearchResults(results, "Linear Search (Partial Match)", time);

        } catch (Exception e) {
            showError("Search error: " + e.getMessage());
            e.printStackTrace();
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
                CitizenSorter.sortByCitizenshipNumber(all);
                sortTime = System.currentTimeMillis() - sortStart;

                searchStart = System.currentTimeMillis();
                CitizenModel result = binarySearchByCitizenshipNumber(all, term);
                searchTime = System.currentTimeMillis() - searchStart;

                if (result != null) {
                    results.add(result);
                }

            } else if (nameBtn.isSelected()) {
                sortByName(all);
                sortTime = System.currentTimeMillis() - sortStart;

                searchStart = System.currentTimeMillis();
                CitizenModel result = binarySearchByName(all, term);
                searchTime = System.currentTimeMillis() - searchStart;

                if (result != null) {
                    results.add(result);
                }

            } else if (phoneNumberBtn.isSelected()) {
                sortByPhone(all);
                sortTime = System.currentTimeMillis() - sortStart;

                searchStart = System.currentTimeMillis();
                CitizenModel result = binarySearchByPhone(all, term);
                searchTime = System.currentTimeMillis() - searchStart;

                if (result != null) {
                    results.add(result);
                }

            } else if (districtSearchBtn.isSelected()) {
                // NEW: Binary search by district
                sortByDistrict(all);
                sortTime = System.currentTimeMillis() - sortStart;

                searchStart = System.currentTimeMillis();
                // For district/province, find all matches (not just one)
                results = binarySearchByDistrict(all, term);
                searchTime = System.currentTimeMillis() - searchStart;

            } else if (provinceSearchBtn.isSelected()) {
                // NEW: Binary search by province
                sortByProvince(all);
                sortTime = System.currentTimeMillis() - sortStart;

                searchStart = System.currentTimeMillis();
                results = binarySearchByProvince(all, term);
                searchTime = System.currentTimeMillis() - searchStart;

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

    private void sortByDistrict(ArrayList<CitizenModel> citizens) {
        for (int i = 0; i < citizens.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < citizens.size(); j++) {
                if (citizens.get(j).getDistrict().compareToIgnoreCase(
                        citizens.get(minIdx).getDistrict()) < 0) {
                    minIdx = j;
                }
            }
            CitizenModel temp = citizens.get(i);
            citizens.set(i, citizens.get(minIdx));
            citizens.set(minIdx, temp);
        }
    }

    private void sortByProvince(ArrayList<CitizenModel> citizens) {
        for (int i = 0; i < citizens.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < citizens.size(); j++) {
                if (citizens.get(j).getProvince().compareToIgnoreCase(
                        citizens.get(minIdx).getProvince()) < 0) {
                    minIdx = j;
                }
            }
            CitizenModel temp = citizens.get(i);
            citizens.set(i, citizens.get(minIdx));
            citizens.set(minIdx, temp);
        }
    }

    private List<CitizenModel> binarySearchByDistrict(ArrayList<CitizenModel> citizens, String target) {
        List<CitizenModel> results = new ArrayList<>();
        String searchDistrict = target.toLowerCase();

        // Find first occurrence
        int left = 0, right = citizens.size() - 1;
        int firstMatch = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midDistrict = citizens.get(mid).getDistrict().toLowerCase();

            int comparison = midDistrict.compareTo(searchDistrict);

            if (comparison == 0) {
                firstMatch = mid;
                right = mid - 1; // Continue searching left for first occurrence
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // If found, collect all matching districts
        if (firstMatch != -1) {
            // Add all citizens with matching district
            for (int i = firstMatch; i < citizens.size(); i++) {
                if (citizens.get(i).getDistrict().equalsIgnoreCase(target)) {
                    results.add(citizens.get(i));
                } else {
                    break; // Stop when district no longer matches
                }
            }
        }

        return results;
    }

    private List<CitizenModel> binarySearchByProvince(ArrayList<CitizenModel> citizens, String target) {
        List<CitizenModel> results = new ArrayList<>();
        String searchProvince = target.toLowerCase();

        // Find first occurrence
        int left = 0, right = citizens.size() - 1;
        int firstMatch = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midProvince = citizens.get(mid).getProvince().toLowerCase();

            int comparison = midProvince.compareTo(searchProvince);

            if (comparison == 0) {
                firstMatch = mid;
                right = mid - 1; // Continue searching left
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Collect all matching provinces
        if (firstMatch != -1) {
            for (int i = firstMatch; i < citizens.size(); i++) {
                if (citizens.get(i).getProvince().equalsIgnoreCase(target)) {
                    results.add(citizens.get(i));
                } else {
                    break;
                }
            }
        }

        return results;
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

            if (comparison == 0) {
                return citizens.get(mid);
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
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

            if (comparison == 0) {
                return citizens.get(mid);
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    private CitizenModel binarySearchByPhone(ArrayList<CitizenModel> citizens, String target) {
        int left = 0, right = citizens.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midPhone = citizens.get(mid).getPhoneNumber();

            int comparison = midPhone.compareTo(target);

            if (comparison == 0) {
                return citizens.get(mid);
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
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
                    c.getCitizenshipNumber(), c.getVoterName(), c.getAge(), c.getGender().getDisplay(),
                    c.getPhoneNumber(), c.getDistrict(), c.getProvince(), c.getStatus().getDisplay()
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
                queueTextArea.setText("═══════════════════════════════════\n"
                        + "      QUEUE IS EMPTY\n"
                        + "═══════════════════════════════════");
                return;
            }

            // Display current citizen
            displayCurrentQueueItem(next);

            // Show action dialog
            String[] options = {"Approve", "Reject", "Skip", "Cancel"};
            int choice = JOptionPane.showOptionDialog(this,
                    "What action would you like to take for:\n" + next.getVoterName() + "?",
                    "Process Citizen",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) {
                // Approve
                boolean success = adminController.approveCitizen(next.getCitizenshipNumber());
                if (success) {
                    queueTextArea.append("\n\n✓ APPROVED\n");
                    queueTextArea.append("════════════════════════════════════\n");
                    refreshCitizensTable();
                    refreshDashboard();

                    // Show next item automatically
                    showMessage("Citizen approved! Processing next in queue...");
                    handleProcessNext(); // Recursive call to show next
                }
            } else if (choice == 1) {
                // Reject
                String reason = JOptionPane.showInputDialog(this,
                        "Enter reason for rejection (optional):",
                        "Reject Reason",
                        JOptionPane.QUESTION_MESSAGE);

                if (reason == null) {
                    reason = "Rejected by admin";
                }

                boolean success = adminController.rejectCitizen(next.getCitizenshipNumber(), reason);
                if (success) {
                    queueTextArea.append("\n\n✗ REJECTED\n");
                    queueTextArea.append("Reason: " + reason + "\n");
                    queueTextArea.append("════════════════════════════════════\n");
                    refreshCitizensTable();
                    refreshDashboard();

                    // Show next item automatically
                    showMessage("Citizen rejected! Processing next in queue...");
                    handleProcessNext(); // Recursive call to show next
                }
            } else if (choice == 2) {
                // Skip - move to end of queue and show next
                CitizenModel skippedNext = adminController.skipCurrentInQueue();

                queueTextArea.append("\n\n⊳ SKIPPED - Moved to end of queue\n");
                queueTextArea.append("════════════════════════════════════\n");

                if (skippedNext != null) {
                    // Show the new first item
                    displayCurrentQueueItem(skippedNext);
                    showMessage("Skipped! Showing next citizen in queue...");

                    // Recursively call to allow action on next item
                    handleProcessNext();
                } else {
                    showMessage("No more citizens in queue.");
                }

            } else {
                // Cancel - do nothing
                queueTextArea.append("\n\n○ Action cancelled\n");
            }

        } catch (Exception e) {
            showError("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayCurrentQueueItem(CitizenModel citizen) {
        StringBuilder sb = new StringBuilder();

        // Queue position indicator
        int queueSize = adminController.getQueueSize();
        sb.append("═══════════════ NEXT IN QUEUE ═══════════════\n");
        sb.append(String.format("        Queue Size: %d remaining\n", queueSize));
        sb.append("═════════════════════════════════════════════\n\n");

        sb.append(String.format("📋 Citizenship No: %s\n", citizen.getCitizenshipNumber()));
        sb.append(String.format("👤 Name: %s\n", citizen.getVoterName()));
        sb.append(String.format("🎂 Age: %d years\n", citizen.getAge()));
        sb.append(String.format("⚧ Gender: %s\n", citizen.getGender().getDisplay()));
        sb.append(String.format("📞 Phone: %s\n", citizen.getPhoneNumber()));
        sb.append(String.format("🗺️ Province: %s\n", citizen.getProvince()));
        sb.append(String.format("🏘️ District: %s\n", citizen.getDistrict()));
        sb.append(String.format("🏛️ Municipality: %s\n", citizen.getMunicipality()));
        sb.append(String.format("🗳️ Vote Center: %s\n", citizen.getVoteCenter()));
        sb.append(String.format("📊 Status: %s\n", citizen.getStatus().getDisplay()));
        sb.append("\n═══════════════════════════════════════════════\n");

        queueTextArea.setText(sb.toString());
        queueTextArea.setCaretPosition(0);
    }

    private void handleViewQueue() {
        try {
            LinkedList<CitizenModel> queue = adminController.getPendingQueue();

            if (queue.isEmpty()) {
                queueTextArea.setText("═══════════════════════════════════\n"
                        + "      QUEUE IS EMPTY\n"
                        + "═══════════════════════════════════");
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
                    "Are you sure you want to undo this action?\n\n"
                    + "Action Type: %s\n"
                    + "Citizen: %s\n"
                    + "Citizenship No: %s\n"
                    + "Admin: %s\n"
                    + "Time: %s",
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

        historyList.setModel(model);
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
                    c.getGender().getDisplay(),
                    c.getPhoneNumber(),
                    c.getDistrict(),
                    c.getProvince(),
                    c.getStatus().getDisplay()
                });
            }
        } catch (Exception e) {
            showError("Error loading data: " + e.getMessage());
        }
    }

    private void sortTableInitial() {
        filterLabel.addChangeListener(e -> {
            int selectedIndex = filterLabel.getSelectedIndex();
            String tabTitle = filterLabel.getTitleAt(selectedIndex);

            if ("Sort".equals(tabTitle)) {
                loadUnsortedDataToSortTable();
            }
        });
    }

    private void setupCitizenRegComboBoxes() {
        districtRegCombo.removeAllItems();
        districtRegCombo.addItem("-- Select District --");
    }

    private void updateDistrictRegComboBox() {
        String selectedProvince = (String) provinceRegCombo.getSelectedItem();
        districtRegCombo.removeAllItems();
        districtRegCombo.addItem("-- Select District --");

        if (selectedProvince == null || selectedProvince.equals("-- Select Province --")) {
            return;
        }

        // Add districts based on province (SAME LOGIC AS ADMIN)
        switch (selectedProvince) {
            case "Koshi":
                districtRegCombo.addItem("Bhojpur");
                districtRegCombo.addItem("Dhankuta");
                districtRegCombo.addItem("Ilam");
                districtRegCombo.addItem("Jhapa");
                districtRegCombo.addItem("Khotang");
                districtRegCombo.addItem("Morang");
                districtRegCombo.addItem("Okhaldhunga");
                districtRegCombo.addItem("Panchthar");
                districtRegCombo.addItem("Sankhuwasabha");
                districtRegCombo.addItem("Solukhumbu");
                districtRegCombo.addItem("Sunsari");
                districtRegCombo.addItem("Taplejung");
                districtRegCombo.addItem("Terhathum");
                districtRegCombo.addItem("Udayapur");
                break;

            case "Madhesh":
                districtRegCombo.addItem("Bara");
                districtRegCombo.addItem("Dhanusha");
                districtRegCombo.addItem("Mahottari");
                districtRegCombo.addItem("Parsa");
                districtRegCombo.addItem("Rautahat");
                districtRegCombo.addItem("Saptari");
                districtRegCombo.addItem("Sarlahi");
                districtRegCombo.addItem("Siraha");
                break;

            case "Bagmati":
                districtRegCombo.addItem("Bhaktapur");
                districtRegCombo.addItem("Chitwan");
                districtRegCombo.addItem("Dhading");
                districtRegCombo.addItem("Dolakha");
                districtRegCombo.addItem("Kathmandu");
                districtRegCombo.addItem("Kavrepalanchok");
                districtRegCombo.addItem("Lalitpur");
                districtRegCombo.addItem("Makwanpur");
                districtRegCombo.addItem("Nuwakot");
                districtRegCombo.addItem("Ramechhap");
                districtRegCombo.addItem("Rasuwa");
                districtRegCombo.addItem("Sindhuli");
                districtRegCombo.addItem("Sindhupalchok");
                break;

            case "Gandaki":
                districtRegCombo.addItem("Baglung");
                districtRegCombo.addItem("Gorkha");
                districtRegCombo.addItem("Kaski");
                districtRegCombo.addItem("Lamjung");
                districtRegCombo.addItem("Manang");
                districtRegCombo.addItem("Mustang");
                districtRegCombo.addItem("Myagdi");
                districtRegCombo.addItem("Nawalpur");
                districtRegCombo.addItem("Parbat");
                districtRegCombo.addItem("Syangja");
                districtRegCombo.addItem("Tanahun");
                break;

            case "Lumbini":
                districtRegCombo.addItem("Arghakhanchi");
                districtRegCombo.addItem("Banke");
                districtRegCombo.addItem("Bardiya");
                districtRegCombo.addItem("Dang");
                districtRegCombo.addItem("Gulmi");
                districtRegCombo.addItem("Kapilvastu");
                districtRegCombo.addItem("Palpa");
                districtRegCombo.addItem("Parasi");
                districtRegCombo.addItem("Pyuthan");
                districtRegCombo.addItem("Rolpa");
                districtRegCombo.addItem("Rukum East");
                districtRegCombo.addItem("Rupandehi");
                break;

            case "Karnali":
                districtRegCombo.addItem("Dailekh");
                districtRegCombo.addItem("Dolpa");
                districtRegCombo.addItem("Humla");
                districtRegCombo.addItem("Jajarkot");
                districtRegCombo.addItem("Jumla");
                districtRegCombo.addItem("Kalikot");
                districtRegCombo.addItem("Mugu");
                districtRegCombo.addItem("Rukum West");
                districtRegCombo.addItem("Salyan");
                districtRegCombo.addItem("Surkhet");
                break;

            case "Sudurpashchim":
                districtRegCombo.addItem("Achham");
                districtRegCombo.addItem("Baitadi");
                districtRegCombo.addItem("Bajhang");
                districtRegCombo.addItem("Bajura");
                districtRegCombo.addItem("Dadeldhura");
                districtRegCombo.addItem("Darchula");
                districtRegCombo.addItem("Doti");
                districtRegCombo.addItem("Kailali");
                districtRegCombo.addItem("Kanchanpur");
                break;
        }
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
    private javax.swing.JPanel CitizenDashboardPanel;
    private javax.swing.JPanel SearchPane;
    private javax.swing.JLabel actionLabel;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton adminChoiceBtn;
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
    private javax.swing.JComboBox<String> ageGroupFilterCombo;
    private javax.swing.JRadioButton ageSortBtn;
    private javax.swing.JButton applyFilterBtn;
    private javax.swing.JLabel applyFilterLabel;
    private javax.swing.JButton approveBtn;
    private javax.swing.JLabel approveQueueLabel;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton binarySearchBtn;
    private javax.swing.JButton cancelEditBtn;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JLabel carouselLabel;
    private javax.swing.JButton checkStatusBtn;
    private javax.swing.JTextField cidEditField;
    private javax.swing.JLabel cidEditLabel;
    private javax.swing.JTextField cidRegField;
    private javax.swing.JLabel cidRegLabel;
    private javax.swing.JLabel cidSearchLabel;
    private javax.swing.JButton citizenChoiceBtn;
    private javax.swing.JPanel citizenHeaderPanel;
    private view.BackgroundPanel citizenImg;
    private javax.swing.JButton citizenLogoutBtn;
    private javax.swing.JPanel citizenLogoutPanel;
    private javax.swing.JScrollPane citizenRecordPane;
    private javax.swing.JTabbedPane citizenTabbedPane;
    private javax.swing.JPanel citizenWelcomePanel;
    private javax.swing.JTable citizensTable;
    private javax.swing.JRadioButton citizenshipNoBtn;
    private javax.swing.JRadioButton citizenshipnoSortBtn;
    private javax.swing.JButton clearRegBtn;
    private javax.swing.JButton clearSearchBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel dashboardCenterPanel;
    private javax.swing.JPanel dashboardFooterPanel;
    private javax.swing.JPanel dashboardPane;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton deleteMyRecordBtn;
    private javax.swing.JTextArea detailsTextarea;
    private javax.swing.JComboBox<String> districtEditCombo;
    private javax.swing.JLabel districtEditLabel;
    private javax.swing.JComboBox<String> districtFilterCombo;
    private javax.swing.JComboBox<String> districtRegCombo;
    private javax.swing.JLabel districtRegLabel;
    private javax.swing.JRadioButton districtSearchBtn;
    private com.toedter.calendar.JDateChooser dobEditChooser;
    private javax.swing.JLabel dobEditLabel;
    private com.toedter.calendar.JDateChooser dobRegChooser;
    private javax.swing.JLabel dobRegLabel;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel editCenterPanel;
    private javax.swing.JPanel editFooterPanel;
    private javax.swing.JPanel editFormPanel;
    private javax.swing.JScrollPane editFormScrollPane;
    private javax.swing.JPanel editRecordPane;
    private javax.swing.JPanel editSearchPanel;
    private javax.swing.JPanel exitPanel;
    private javax.swing.JButton extSystemBtn;
    private javax.swing.JTabbedPane filterLabel;
    private javax.swing.JLabel footerLabel1;
    private javax.swing.JLabel footerLabel2;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JButton forgetBtn;
    private javax.swing.JComboBox<String> genderEditCombo;
    private javax.swing.JLabel genderEditLabel;
    private javax.swing.JComboBox<String> genderRegCombo;
    private javax.swing.JLabel genderRegLabel;
    private javax.swing.JPanel headerContainer;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel historyCenterPanel;
    private javax.swing.JButton historyClearBtn;
    private javax.swing.JPanel historyFooter;
    private javax.swing.JPanel historyHeader;
    private javax.swing.JList<String> historyList;
    private javax.swing.JPanel historyPane;
    private javax.swing.JButton historyUndoBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JTextField municipalityEditField;
    private javax.swing.JLabel municipalityEditLabel;
    private javax.swing.JTextField municipalityRegField;
    private javax.swing.JLabel municipalityRegLabel;
    private javax.swing.JRadioButton nameBtn;
    private javax.swing.JTextField nameEditField;
    private javax.swing.JLabel nameEditLabel;
    private javax.swing.JTextField nameRegField;
    private javax.swing.JLabel nameRegLabel;
    private javax.swing.JRadioButton nameSortBtn;
    private javax.swing.JList<String> nameSuggestionList;
    private javax.swing.JPopupMenu nameSuggestionPopup;
    private javax.swing.JTextField phoneEditField;
    private javax.swing.JLabel phoneEditLabel;
    private javax.swing.JRadioButton phoneNumberBtn;
    private javax.swing.JTextField phoneRegField;
    private javax.swing.JLabel phoneRegLabel;
    private javax.swing.JButton processNextBtn;
    private view.BackgroundPanel protectionImg;
    private javax.swing.JComboBox<String> provinceEditCombo;
    private javax.swing.JLabel provinceEditLabel;
    private javax.swing.JComboBox<String> provinceFilterCombo;
    private javax.swing.JComboBox<String> provinceRegCombo;
    private javax.swing.JLabel provinceRegLabel;
    private javax.swing.JRadioButton provinceSearchBtn;
    private javax.swing.JPanel queueCenterPanel;
    private javax.swing.JScrollPane queueCentrePane;
    private javax.swing.JPanel queueFooterPanel;
    private javax.swing.JPanel queuePane;
    private javax.swing.JPanel queuePaneHeader;
    private javax.swing.JButton queueRefreshBtn;
    private javax.swing.JTextArea queueTextArea;
    private javax.swing.JLabel quoteLabel;
    private javax.swing.JButton refreshManageBtn;
    private javax.swing.JPanel registrationFooterPanel;
    private javax.swing.JPanel registrationFormPanel;
    private javax.swing.JPanel registrationHeaderPanel;
    private javax.swing.JPanel registrationPane;
    private javax.swing.JLabel registrationTitleLabel;
    private javax.swing.JButton rejectBtn;
    private javax.swing.JButton resetSortBtn;
    private javax.swing.JButton saveEditBtn;
    private javax.swing.JTextField searchCidField;
    private javax.swing.JLabel searchCidLabel;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchHeader;
    private javax.swing.JButton searchManageBtn;
    private javax.swing.JButton searchMyRecordButton;
    private javax.swing.ButtonGroup searchOptionGroup;
    private javax.swing.JScrollPane searchScrollPane;
    private javax.swing.JLabel selectRoleLabel;
    private javax.swing.JPanel selectRolePanel;
    private javax.swing.JScrollPane showHistoryPane;
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
    private javax.swing.JPanel statsPanel1;
    private javax.swing.JPanel statsPanel2;
    private javax.swing.JPanel statsPanel3;
    private javax.swing.JPanel statsPanel4;
    private javax.swing.JPanel statsPanel5;
    private javax.swing.JPanel statsPanel6;
    private javax.swing.JLabel statusAgeLabel;
    private javax.swing.JPanel statusCardPanel;
    private javax.swing.JPanel statusCheckPane;
    private javax.swing.JLabel statusCidDisplayLabel;
    private javax.swing.JTextField statusCidField;
    private javax.swing.JLabel statusCurrentLabel;
    private javax.swing.JLabel statusDistrictLabel;
    private javax.swing.JLabel statusEditDisplay;
    private javax.swing.JLabel statusEditLabel;
    private javax.swing.JLabel statusGenderLabel;
    private javax.swing.JLabel statusHeaderLabel;
    private javax.swing.JPanel statusHeaderPanel;
    private javax.swing.JPanel statusInputPanel;
    private javax.swing.JPanel statusMainPanel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JLabel statusNameLabel;
    private javax.swing.JProgressBar statusProgressBar;
    private javax.swing.JLabel statusProvinceLabel;
    private javax.swing.JPanel statusResultPanel;
    private javax.swing.JLabel subHeaderLabel;
    private javax.swing.JButton submitRegBtn;
    private javax.swing.JButton viewBtn;
    private javax.swing.JButton viewHistoryBtn;
    private javax.swing.JButton viewQueueBtn;
    private javax.swing.JLabel voidi;
    private javax.swing.JLabel voidii;
    private javax.swing.JTextField voteCenterEditField;
    private javax.swing.JLabel voteCenterEditLabel;
    private javax.swing.JLabel voteCenterLabel;
    private javax.swing.JTextField voteCenterRegField;
    private javax.swing.JLabel welcomeAdmin;
    // End of variables declaration//GEN-END:variables
}
