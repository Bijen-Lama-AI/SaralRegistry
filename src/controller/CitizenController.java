/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.CitizenModel;
import model.CitizenModel.Gender;
import model.CitizenModel.RegistrationStatus;
import model.CitizenRegistryModel;
import view.MainFrame;
import java.time.LocalDate;
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
        view.updateTable(registry.getAllCitizens());
    }
    
    /**
     * Adds a new citizen from UI input.
     */
    private void loadInitialCitizenData() {
        registry.addCitizen(new CitizenModel("1a27", "9824370845", "Bijen Lama",Gender.MALE, "Bagmati", "Kathmandu", "KMC", "Swayambhu", LocalDate.of(2005, 5, 25)));
        registry.addCitizen(new CitizenModel("2br21", "9824370845", "Bijen Lama",Gender.MALE, "Bagmati", "Kathmandu", "KMC", "Swayambhu", LocalDate.of(2000, 5, 25)));
        registry.addCitizen(new CitizenModel("3231a", "9824370845", "Bijen Lama",Gender.MALE, "Bagmati", "Kathmandu", "KMC", "Swayambhu", LocalDate.of(1980, 5, 25)));
        registry.addCitizen(new CitizenModel("4123a", "9824370845", "Bijen Lama",Gender.MALE, "Bagmati", "Kathmandu", "KMC", "Swayambhu", LocalDate.of(1988, 5, 25)));
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
    public void addCitizen(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district, String municipality, String voteCenter, LocalDate dob) {
        try {
            CitizenModel citizen = new CitizenModel(citizenshipNumber, phoneNumber, voterName, gender,
                    province, district, municipality, voteCenter, dob);
            registry.addCitizen(citizen);
            refreshTable();
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
     * Refresh the UI table with current citizens
     */
    public void refreshTable() {
        view.updateTable(registry.getAllCitizens());
    }
}
        