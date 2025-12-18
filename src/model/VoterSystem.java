/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bijen
 */

import java.util.*;

public class VoterSystem {
    
    private ArrayList<Citizen> allCitizens;
    private Queue<Citizen> pendingApplications;
    private HashMap<String, Citizen> citizenLookup;
    
    public VoterSystem() {
        allCitizens = new ArrayList<>();
        pendingApplications = new LinkedList<>();
        citizenLookup = new HashMap<>();
    }
    
    // register citizens
    public boolean registerCitizen(Citizen citizen) {
        String citizenshipNo = citizen.getCitizenshipNumber();
        
        if (citizenLookup.containsKey(citizenshipNo)) {
            return false; // duplicate
        }
        
        allCitizens.add(citizen);
        pendingApplications.add(citizen);
        citizenLookup.put(citizenshipNo, citizen);
        return true;
    }
    
    // admin verifies next application
    public Citizen verifyNextApplication(boolean approve) {
        Citizen citizen = pendingApplications.poll();
        
        if (citizen != null) {
            citizen.setStatus(approve ? "APPROVED" : "REJECTED");
        }
        return citizen;
    }
    
    // admin deletes citizen application
    public boolean deleteCitizen(String citizenshipNumber) {
        Citizen citizen = citizenLookup.remove(citizenshipNumber);
        
        if  (citizen == null) {
            return false;
        }
        
        allCitizens.remove(citizen);
        pendingApplications.remove(citizen);
        return true;
    }
    
    // search citizen by citizenship number
    public Citizen searchByCitizenship(String citizenshipNumber) {
        return citizenLookup.get(citizenshipNumber);
    }
    
    // sort by name
    public void sortCitizensByName() {
        allCitizens.sort(Comparator.comparing(Citizen::getVoterName));
    }
    
    public ArrayList<Citizen> getAllCitizens() {
        return allCitizens;
    }
    
    // get pending queue
    public Queue<Citizen> getPendingApplications() {
        return pendingApplications;
    }
}
