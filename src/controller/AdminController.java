/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.AdminModel;
import model.AdminRegistryModel;
import model.CitizenModel;
import model.CitizenModel.RegistrationStatus;
import model.CitizenRegistryModel;
import view.MainFrame;

/**
 * Controller for admin actions: login, password change, approve/reject citizens. 
 * @author bijen
 */
public class AdminController {
    
    private AdminRegistryModel adminRegistry;
    private CitizenRegistryModel registry;
    private MainFrame view;
    
    // To track if admin is logged in
    private boolean loggedIn;
    private AdminModel currentAdmin;
    
    /**
     * Constructor for AdminController
     * @param adminRegistry
     * @param registry
     * @param view 
     */
    public AdminController(AdminRegistryModel adminRegistry, CitizenRegistryModel registry, MainFrame view) {
        this.adminRegistry = adminRegistry;
        this.registry = registry;
        this.view = view;
        this.loggedIn = false;
        this.currentAdmin = null;
        
        loadInitialAdmins(); // preload some admin
    }
    
    /**
     * Authenticate admin credentials.
     * @param inputId
     * @param inputPassword
     * @return authenticated
     */
    public boolean login(String inputId, String inputPassword) {
        if (inputId == null || inputPassword == null) {
            view.showError("ID and password cannot be null.");
            return false;
        }
        
        AdminModel loggedAdmin = adminRegistry.authenticateAdmin(inputId.trim(), inputPassword.trim());
        if (loggedAdmin == null) {
            loggedIn = false;
            view.showError("Invalid ID or Password.");
            return false;
        }
        currentAdmin = loggedAdmin;
        loggedIn = true;
        return true;
    }
    
    /**
     * Logs out the current logged-in admin
     */
    public void logout() {
        currentAdmin = null;
        loggedIn = false;
    }
    
    /**
     * Approves a citizen registration.
     * @param citizenshipNumber
     */
    public void approveCitizen(String citizenshipNumber) {
        if (!ensureLoggedIn()) {
            return;
        }
        try {
            registry.updateCitizenStatus(citizenshipNumber, RegistrationStatus.APPROVED);
            view.updateTable((ArrayList<CitizenModel>) registry.getAllCitizens());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    
    /**
     * Rejects a citizens registration.
     * @param citizenship
     */
    public void rejectCitizen(String citizenship) {
        if (!ensureLoggedIn()) {
            return;
        }
        
        try {
            registry.updateCitizenStatus(citizenship, RegistrationStatus.REJECTED);
            view.updateTable((ArrayList<CitizenModel>) registry.getAllCitizens());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
    
    /**
     * Allows admin to change password
     * @param oldPassword
     * @param newPassword
     */
    public void changePassword(String oldPassword, String newPassword) {
        if (!ensureLoggedIn()) {
            return;
        }
        
        try {
            currentAdmin.changePassword(oldPassword, newPassword);
        } catch (RuntimeException e) {
            view.showError(e.getMessage());
        }
    }
    
    /**
     * Ensures that admin is logged in before performing log in action
     */
    private boolean ensureLoggedIn() {
        if (!loggedIn || currentAdmin == null) {
            view.showError("Please login as admin first.");
            return false;
        }
        return true;
    }
    
    /**
     * Load up initial admins
     */
    private void loadInitialAdmins() {
        try {
        adminRegistry.addAdmin(new AdminModel("admin1", "password123"));
        adminRegistry.addAdmin(new AdminModel("admin2", "admin456"));
        adminRegistry.addAdmin(new AdminModel("superuser", "superpass"));
        } catch (IllegalArgumentException e) {
            view.showError("Failed to load intial admin:  " + e.getMessage());
        }
    }
}
