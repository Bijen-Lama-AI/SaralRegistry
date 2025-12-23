/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages citizen registration.
 * Uses a HashMap for fast lookup and prevent duplication or invalid entries.
 * @author bijen
 */
public class CitizenRegistryModel {
    
    // Hashmap for quick access by citizenship number
    private HashMap<String, CitizenModel> citizenMap;
    
    public CitizenRegistryModel() {
        this.citizenMap = new HashMap<>();
    }
    
    /**
     * Adds a citizen to the registry.
     * Prevent null entries and duplicate citizenship number.
     * @param citizen Citizen to add 
     * @throws IllegalArgumentException if citizen is null or already exists
     */
    public void addCitizen(CitizenModel citizen) {
        
        if (citizen == null) {
            throw new IllegalArgumentException("Citizen cannot be null.");
        }
        
        String citizenshipNumber = citizen.getCitizenshipNumber();
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Citizen must have valid citizenship number.");
        }
        
        String key = citizenshipNumber.trim();
        
        if (citizenMap.containsKey(key)) {
            throw new IllegalArgumentException("Citizen with this citizenship number already exist.");
        }
        
        citizenMap.put(key,citizen);
    }
    
    /**
     * Returns a list of all citizens data.
     * @return  ArrayList of citizens
     */
    public ArrayList<CitizenModel> getAllCitizens() {
        return new ArrayList<>(citizenMap.values());
    }
    
    /**
     * Finds a citizen by citizenship number.
     * Return null if not found or if input is invalid.
     * 
     * @param citizenshipNumber Citizenship number to search
     * @return CitizenModel or null
     */
    public CitizenModel findByCitizenshipNumber(String citizenshipNumber) {
        
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            return null;
        }
        
        return citizenMap.get(citizenshipNumber.trim());
    }
    
    /**
     * Updates the registration status of a citizen.
     * Useful for admin actions like approve/reject.
     * 
     * @param citizenshipNumber
     * @param status
     */
    public void updateCitizenStatus(String citizenshipNumber, CitizenModel.RegistrationStatus status) {
        CitizenModel citizen = findByCitizenshipNumber(citizenshipNumber);
        if (citizen == null) {
            throw new IllegalArgumentException("Citizen not found.");
        }
        citizen.setStatus(status);
    }
}
