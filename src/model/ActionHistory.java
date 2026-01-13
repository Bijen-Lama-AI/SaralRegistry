/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
/**
 *
 * @author bijen
 */
public class ActionHistory {
    private String actionType;
    private CitizenModel citizenSnapshot;
    private String adminId;
    private LocalDateTime timestamp;
    
    public ActionHistory(String actionType, CitizenModel citizen, String adminId) {
        this.actionType = actionType;
        this.citizenSnapshot = citizen;
        this. adminId = adminId;
        this.timestamp = LocalDateTime.now();
    }

    public String getActionType() {
        return actionType;
    }

    public CitizenModel getCitizenSnapshot() {
        return citizenSnapshot;
    }

    public String getAdminId() {
        return adminId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - %s by %s", timestamp.toLocalTime(), actionType, citizenSnapshot.getVoterName(), adminId);
    }
}
