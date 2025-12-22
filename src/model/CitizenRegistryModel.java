/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bijen
 */
import java.util.ArrayList;

public class CitizenRegistryModel {
    
    private ArrayList<CitizenModel> citizens;
    
    public CitizenRegistryModel() {
        citizens = new ArrayList<>();
    }
    
    public void addCitizen(CitizenModel citizen) {
        citizens.add(citizen);
    }
    
    public ArrayList<CitizenModel> getAllCitizens() {
        return citizens;
    }
    
}
