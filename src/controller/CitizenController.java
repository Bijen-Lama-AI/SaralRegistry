
package controller;

import model.CitizenModel;
import model.CitizenModel.Gender;
import model.CitizenModel.RegistrationStatus;
import model.CitizenRegistryModel;
import view.MainFrame;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Controller for handling citizen operation between model and view.
 * 
 * @author bijen
 */
public class CitizenController {
    
    private CitizenRegistryModel registry;
    private MainFrame view;

    public CitizenController(CitizenRegistryModel registry, MainFrame view) {
        this.registry = registry;
        this.view = view;
        
        // Load some initial citizens for table display.
        loadInitialCitizenData();
        refreshTable();
    }
    
    /**
     * Adds a new citizen from UI input.
     */
    private void loadInitialCitizenData() {
        try {
            registry.addCitizen(new CitizenModel("513625744", "9824370845", "Bijen Lama",Gender.MALE, "Bagmati", "Kathmandu", "KMC", "Swayambhu", LocalDate.of(2005, 5, 25)));
            registry.addCitizen(new CitizenModel("2771004911", "9893418683", "Suresh Poudel",Gender.MALE, "Koshi", "Jhapa", "Kankai", "Arjundhara", LocalDate.of(1990, 5, 25)));
            registry.addCitizen(new CitizenModel("8187748999", "9830244896", "Mina Bhattrai",Gender.FEMALE, "Gandaki", "Kaski", "Annapurna", "Danda", LocalDate.of(1980, 5, 25)));
            registry.addCitizen(new CitizenModel("606763729", "9851231337", "Puja Chaudhary",Gender.FEMALE, "Madhesh", "Bara", "Nijgadh", "Mahagadhimai", LocalDate.of(1988, 5, 25)));
        } catch (IllegalArgumentException e) {
            view.showError("Failed to load initial citizen data: " + e.getMessage());
        }
        
    }
    
    /**
     * Adds a new citizen from UI input.
     * @param citizenshipNumber
     * @param phoneNumber
     * @param voterName
     * @param gender
     * @param province
     * @param district
     * @param municipality
     * @param voteCenter
     * @param dob
     */
    public void addCitizenFromUI(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district, String municipality, String voteCenter, LocalDate dob) {
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty() || voterName == null || voterName.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty()) {
           view.showError("Fields cannot be empty");
           return;
        }
        
        if (!phoneNumber.matches("\\d{10}")) {
            view.showError("Phone number must be 10 digits");
            return;
        }
        
        if (gender == null ||
            province == null || province.trim().equalsIgnoreCase("Select") ||
            district == null || district.trim().equalsIgnoreCase("Select") ||
            voteCenter == null || voteCenter.trim().isEmpty()){
            view.showError("Please select valid dropdown menu");
            return;
        }
        
        if (dob == null) {
            view.showError("Date of birth is required");
            return;
        }
        
        try {
            CitizenModel citizen = new CitizenModel(citizenshipNumber.trim(), phoneNumber.trim(), voterName.trim(), gender, province.trim(), district.trim(), municipality.trim(), voteCenter.trim(), dob);
            registry.addCitizen(citizen);
            refreshTable();
            view.showMessage("Citizen added successfully.");
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
        
    }
    
    
    /**
     * Approves a pending citizen registration (Admin action)
     * @param citizenshipNumber
     * @param status
     */
    public void updateCitizenStatus(String citizenshipNumber, RegistrationStatus status) {
        try {
            registry.updateCitizenStatus(citizenshipNumber, status);
            refreshTable();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    
    /**
     * Deletes a citizen from the registry (Admin action)
     * @param citizenship
     */
    public void deleteCitizen(String citizenshipNumber) {
        try {
            registry.removeCitizen(citizenshipNumber);
            refreshTable();
            view.showMessage("Citizen with Id: " + citizenshipNumber + " has been deleted successfully");
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    
    /**
     * Refresh the UI table with current citizens
     */
    public void refreshTable() {
        view.updateTable(new ArrayList<>(registry.getAllCitizens()));
    }
    
    
}   
        