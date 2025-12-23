/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashSet;

/**
 * Manages admin in the system.
 * Ensures unique admin ID
 * @author bijen
 */
public class AdminRegistryModel {
    
    private HashSet<AdminModel> adminSet;
    
    /**
     * Constructor for AdminRegistry
     */
    public AdminRegistryModel() {
        adminSet = new HashSet<>();
    }
    
    /**
     * Adds a new admin 
     * @param admin
     * @throws IllegalArgumentException if admin is null or already exists
     */
    public void addAdmin(AdminModel admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin cannot be null.");
        }
        if (adminExists(admin.getAdminId())) {
            throw new IllegalArgumentException("Admin with this ID already exists.");
        }
        adminSet.add(admin);
    }
    
    /**
     * Checks if an admin ID already exist
     * @param adminId
     * @return true if admin id exist 
     */
    public boolean adminExists(String adminId) {
        if (adminId == null || adminId.trim().isEmpty()) {
            return false;
        }
        for (AdminModel a : adminSet) {
            if (a.getAdminId().equals(adminId.trim())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Authenticates an admin login
     * @param adminId
     * @param password
     * @return AdminModel if credential match
     */
    public AdminModel authenticateAdmin(String adminId, String password) {
        if (adminId == null || password == null) {
            return null;
        }
        
        for (AdminModel a : adminSet) {
            if (a.authenticate(adminId, password)) {
                return a;
            }
        }
        return null;
    }
    
    /**
     * Removes an admin from the registry
     * @param adminId
     * @return 
     */
    public boolean removeAdmin(String adminId) {
        if (adminId == null) {
            return false;
        }
        
        AdminModel toRemove = null;
        for (AdminModel a : adminSet) {
            if (a.getAdminId().equals(adminId.trim())) {
                toRemove = a;
                break;
            }
        }
        
        if (toRemove != null) {
            adminSet.remove(toRemove);
            return true;
        }
        return false;
    }
    
    /**
     * Returns the total number of admins
     * @return number of admin
     */
    public int getAdminCount() {
        return adminSet.size();
    }
}
