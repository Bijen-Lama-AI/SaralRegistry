/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author bijen
 */

import model.Citizen;
import model.VoterSystem;
import java.util.ArrayList;
import java.util.Queue;

public class VoterController {
    
    private VoterSystem voterSystem;
    
    public VoterController(VoterSystem voterSystem) {
        this.voterSystem = voterSystem;
    }
    
    public boolean registerCitizen(Citizen citizen) {
        return voterSystem.registerCitizen(citizen);
    }
    
    // view own application status
    public String viewApplicationStatus(String citizenshipNumber) {
        Citizen citizen = voterSystem.searchByCitizenship(citizenshipNumber);
        
        if (citizen != null) {
            return citizen.getStatus();
        } else {
            return "Application not found";
        }
    }
    
    // verify next pending application
    public Citizen verifyNextApplication(boolean approve) {
        return voterSystem.verifyNextApplication(approve);
    }
    
    // delete an application
    public boolean deleteCitizen(String citizenshipNumber) {
        return voterSystem.deleteCitizen(citizenshipNumber);
    }
    
    // search by cit num
    public Citizen searchCitizen(String citizenshipNumber) {
        return voterSystem.searchByCitizenship(citizenshipNumber);
    }
    
    // sort all citizens by name
    public ArrayList<Citizen> sortCitizensByName() {
        voterSystem.sortCitizensByName();
        return voterSystem.getAllCitizens();
    }
    
    public ArrayList<Citizen> getAllCitizens() {
        return voterSystem.getAllCitizens();
    }
    
    // get pending queue
    public Queue<Citizen> getPendingApplications() {
        return voterSystem.getPendingApplications();
    }
}
