/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author bijen
 */

import model.CitizenModel;
import java.util.ArrayList;
import java.util.LinkedList;

public class CitizenController {
    // Using ArrayList for registered citizens
    private ArrayList<CitizenModel> registeredCitizens;
    
    public CitizenController() {
        registeredCitizens = new ArrayList<>();
    }
    
    public String validateCitizenData(String phoneNumber, String citizenshipNumber,
            String voterName, int age, int wardNumber) {
        
        if (!isValidPhoneNumber(phoneNumber)) {
            return "Invalid phone number. Must be 10 digits.";
        }
        
        if (!isValidCitizenshipNumber(citizenshipNumber)) {
            return "Invalid citizenship number format.";
        }
        
        if (!isValidName(voterName)) {
            return "Invalid name. Only letter and spaces allowed.";
        }
    }
}
