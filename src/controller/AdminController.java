package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.AdminModel;
import model.AdminRegistryModel;
import model.CitizenModel;
import model.CitizenModel.RegistrationStatus;
import model.CitizenRegistryModel;
import model.ActionHistory;
import view.MainFrame;
import java.util.LinkedList;
import java.util.Stack;
import model.CitizenModel.Gender;

/**
 * Controller for admin actions following MVC pattern. Handles all business
 * logic and mediates between Model and View.
 *
 * @author bijen
 */
public class AdminController {

    private AdminRegistryModel adminRegistry;
    private CitizenRegistryModel citizenRegistry;
    private MainFrame view;

    // Data structures for queue and history
    private LinkedList<CitizenModel> pendingQueue;
    private Stack<ActionHistory> actionStack;

    // To track if admin is logged in
    private boolean loggedIn;
    private AdminModel currentAdmin;

    /**
     * Constructor for AdminController
     */
    public AdminController(AdminRegistryModel adminRegistry, CitizenRegistryModel citizenRegistry, MainFrame view) {
        this.adminRegistry = adminRegistry;
        this.citizenRegistry = citizenRegistry;
        this.view = view;
        this.loggedIn = false;
        this.currentAdmin = null;
        this.pendingQueue = new LinkedList<>();
        this.actionStack = new Stack<>();

        loadInitialAdmins();
    }

    // ==================== AUTHENTICATION ====================
    /**
     * Authenticate admin credentials.
     */
    public boolean login(String inputId, String inputPassword) {
        try {
            // Validation
            if (inputId == null || inputId.trim().isEmpty()) {
                if (view != null) {
                    view.showError("Admin ID cannot be empty.");
                }
                return false;
            }

            if (inputPassword == null || inputPassword.trim().isEmpty()) {
                if (view != null) {
                    view.showError("Password cannot be empty.");
                }
                return false;
            }

            // Authenticate
            AdminModel loggedAdmin = adminRegistry.authenticateAdmin(inputId.trim(), inputPassword.trim());

            if (loggedAdmin == null) {
                loggedIn = false;
                if (view != null) {
                    view.showError("Invalid Admin ID or Password.");
                }
                return false;
            }

            currentAdmin = loggedAdmin;
            loggedIn = true;

            // Load pending queue after successful login
            loadPendingQueue();

            return true;

        } catch (Exception e) {
            if (view != null) {
                view.showError("Login error: " + e.getMessage());
            }
            return false;
        }
    }

    /**
     * Logs out the current admin
     */
    public void logout() {
        currentAdmin = null;
        loggedIn = false;
    }

    /**
     * Check if admin is currently logged in
     */
    public boolean isLoggedIn() {
        return loggedIn && currentAdmin != null;
    }

    /**
     * Get current admin ID
     */
    public String getCurrentAdminId() {
        return currentAdmin != null ? currentAdmin.getAdminId() : null;
    }

    /**
     * Change password for current admin
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            // Validation
            if (oldPassword == null || oldPassword.trim().isEmpty()) {
                view.showError("Current password cannot be empty.");
                return false;
            }

            if (newPassword == null || newPassword.trim().isEmpty()) {
                view.showError("New password cannot be empty.");
                return false;
            }

            if (newPassword.trim().length() < 8) {
                view.showError("New password must be at least 8 characters long.");
                return false;
            }

            currentAdmin.changePassword(oldPassword, newPassword);
            view.showMessage("Password changed successfully!");
            return true;

        } catch (SecurityException e) {
            view.showError("Current password is incorrect.");
            return false;
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error changing password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reset password for any admin (requires admin ID verification)
     */
    public boolean resetPassword(String adminId, String newPassword) {
        try {
            // Validation
            if (adminId == null || adminId.trim().isEmpty()) {
                view.showError("Admin ID cannot be empty.");
                return false;
            }

            if (!adminRegistry.adminExists(adminId.trim())) {
                view.showError("Admin ID not found.");
                return false;
            }

            if (newPassword == null || newPassword.trim().isEmpty()) {
                view.showError("New password cannot be empty.");
                return false;
            }

            if (newPassword.trim().length() < 8) {
                view.showError("Password must be at least 8 characters long.");
                return false;
            }

            // Remove old admin and create new one with same ID
            boolean removed = adminRegistry.removeAdmin(adminId.trim());
            if (!removed) {
                view.showError("Failed to reset password.");
                return false;
            }

            AdminModel newAdmin = new AdminModel(adminId.trim(), newPassword.trim());
            adminRegistry.addAdmin(newAdmin);

            view.showMessage("Password reset successfully!");
            return true;

        } catch (Exception e) {
            view.showError("Error resetting password: " + e.getMessage());
            return false;
        }
    }

    // ==================== CITIZEN MANAGEMENT ====================
    /**
     * Add a new citizen to registry
     */
    public boolean addCitizen(CitizenModel citizen) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            if (citizen == null) {
                view.showError("Citizen data cannot be null.");
                return false;
            }

            citizenRegistry.addCitizen(citizen);

            // Add to pending queue
            if (citizen.getStatus() == RegistrationStatus.PENDING) {
                pendingQueue.addLast(citizen);
            }

            // Record action
            recordAction("ADD", citizen);

            view.showMessage("Citizen added successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error adding citizen: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all citizens
     */
    public List<CitizenModel> getAllCitizens() {
        try {
            return citizenRegistry.getAllCitizens();
        } catch (Exception e) {
            if (view != null) {
                view.showError("Error retrieving citizens: " + e.getMessage());
            }
            return new ArrayList<>();
        }
    }

    /**
     * Find citizen by citizenship number
     */
    public CitizenModel findCitizen(String citizenshipNumber) {
        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Citizenship number cannot be empty.");
                return null;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("Citizen not found.");
            }

            return citizen;

        } catch (Exception e) {
            view.showError("Error finding citizen: " + e.getMessage());
            return null;
        }
    }

    /**
     * Search citizens by name
     */
    public List<CitizenModel> searchByName(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                view.showError("Search name cannot be empty.");
                return new ArrayList<>();
            }

            List<CitizenModel> results = citizenRegistry.findByName(name);

            if (results.isEmpty()) {
                view.showMessage("No citizens found with name: " + name);
            }

            return results;

        } catch (Exception e) {
            view.showError("Error searching citizens: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Update citizen information
     */
    public boolean updateCitizen(CitizenModel citizen) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            if (citizen == null) {
                view.showError("Citizen data cannot be null.");
                return false;
            }

            citizenRegistry.updateCitizen(citizen);

            // Record action
            recordAction("UPDATE", citizen);

            view.showMessage("Citizen updated successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error updating citizen: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete citizen from registry
     */
    public boolean deleteCitizen(String citizenshipNumber) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Citizenship number cannot be empty.");
                return false;
            }

            // Get citizen before deletion for history
            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("Citizen not found.");
                return false;
            }

            boolean deleted = citizenRegistry.removeCitizen(citizenshipNumber.trim());

            if (deleted) {
                // Remove from queue if present
                pendingQueue.remove(citizen);

                // Record action
                recordAction("DELETE", citizen);

                view.showMessage("Citizen deleted successfully!");
                return true;
            } else {
                view.showError("Failed to delete citizen.");
                return false;
            }

        } catch (Exception e) {
            view.showError("Error deleting citizen: " + e.getMessage());
            return false;
        }
    }

    // ==================== APPROVAL SYSTEM ====================
    /**
     * Approve a citizen's registration
     */
    public boolean approveCitizen(String citizenshipNumber) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Citizenship number cannot be empty.");
                return false;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("Citizen not found.");
                return false;
            }

            if (citizen.getStatus() == RegistrationStatus.APPROVED) {
                view.showError("Citizen is already approved.");
                return false;
            }

            // Update status
            citizenRegistry.updateCitizenStatus(citizenshipNumber.trim(), RegistrationStatus.APPROVED);

            // Remove from pending queue
            pendingQueue.remove(citizen);

            // Record action
            recordAction("APPROVE", citizen);

            view.showMessage("Citizen approved successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error approving citizen: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reject a citizen's registration
     */
    public boolean rejectCitizen(String citizenshipNumber, String reason) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Citizenship number cannot be empty.");
                return false;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("Citizen not found.");
                return false;
            }

            if (citizen.getStatus() == RegistrationStatus.REJECTED) {
                view.showError("Citizen is already rejected.");
                return false;
            }

            // Update status
            citizenRegistry.updateCitizenStatus(citizenshipNumber.trim(), RegistrationStatus.REJECTED);

            // Remove from pending queue
            pendingQueue.remove(citizen);

            // Record action
            recordAction("REJECT", citizen);

            view.showMessage("Citizen rejected successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error rejecting citizen: " + e.getMessage());
            return false;
        }
    }

    // ==================== QUEUE MANAGEMENT ====================
    /**
     * Process next citizen in queue (approve/reject)
     */
    public CitizenModel processNextInQueue() {
        if (!ensureLoggedIn()) {
            return null;
        }

        try {
            if (pendingQueue.isEmpty()) {
                view.showMessage("Queue is empty.");
                return null;
            }

            return pendingQueue.peekFirst();

        } catch (Exception e) {
            view.showError("Error processing queue: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get pending queue
     */
    public LinkedList<CitizenModel> getPendingQueue() {
        return new LinkedList<>(pendingQueue);
    }

    /**
     * Get queue size
     */
    public int getQueueSize() {
        return pendingQueue.size();
    }

    /**
     * Load pending citizens into queue
     */
    private void loadPendingQueue() {
        pendingQueue.clear();
        for (CitizenModel citizen : citizenRegistry.getAllCitizens()) {
            if (citizen.getStatus() == RegistrationStatus.PENDING) {
                pendingQueue.addLast(citizen);
            }
        }
    }

    /**
     * Refresh queue (reload from registry)
     */
    public void refreshQueue() {
        loadPendingQueue();
    }

    // ==================== HISTORY/UNDO SYSTEM ====================
    /**
     * Record an action in history
     */
    private void recordAction(String actionType, CitizenModel citizen) {
        if (currentAdmin != null && citizen != null) {
            ActionHistory action = new ActionHistory(actionType, citizen, currentAdmin.getAdminId());
            actionStack.push(action);
        }
    }

    /**
     * Get action history
     */
    public Stack<ActionHistory> getActionHistory() {
        return new Stack<ActionHistory>() {
            {
                addAll(actionStack);
            }
        };
    }

    /**
     * Get history size
     */
    public int getHistorySize() {
        return actionStack.size();
    }

    /**
     * Clear action history
     */
    public void clearHistory() {
        if (!ensureLoggedIn()) {
            return;
        }

        actionStack.clear();
        view.showMessage("History cleared!");
    }

    /**
     * Undo last action
     */
    public boolean undoLastAction() {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            if (actionStack.isEmpty()) {
                view.showMessage("No actions to undo.");
                return false;
            }

            ActionHistory lastAction = actionStack.pop();
            String actionType = lastAction.getActionType();
            CitizenModel citizen = lastAction.getCitizenSnapshot();

            switch (actionType) {
                case "ADD":
                    // Undo add by removing
                    citizenRegistry.removeCitizen(citizen.getCitizenshipNumber());
                    pendingQueue.remove(citizen);
                    break;

                case "DELETE":
                    // Undo delete by re-adding
                    citizenRegistry.addCitizen(citizen);
                    if (citizen.getStatus() == RegistrationStatus.PENDING) {
                        pendingQueue.addLast(citizen);
                    }
                    break;

                case "APPROVE":
                case "REJECT":
                    // Undo status change back to pending
                    citizenRegistry.updateCitizenStatus(
                            citizen.getCitizenshipNumber(),
                            RegistrationStatus.PENDING
                    );
                    pendingQueue.addLast(citizen);
                    break;

                case "UPDATE":
                    // Restore previous version
                    citizenRegistry.updateCitizen(citizen);
                    break;

                default:
                    view.showError("Unknown action type: " + actionType);
                    return false;
            }

            view.showMessage("Action undone: " + actionType);
            return true;

        } catch (Exception e) {
            view.showError("Error undoing action: " + e.getMessage());
            return false;
        }
    }

    // ==================== STATISTICS ====================
    /**
     * Get count by status
     */
    public int countByStatus(RegistrationStatus status) {
        int count = 0;
        for (CitizenModel citizen : citizenRegistry.getAllCitizens()) {
            if (citizen.getStatus() == status) {
                count++;
            }
        }
        return count;
    }

    public CitizenModel skipCurrentInQueue() {
        if (!ensureLoggedIn()) {
            return null;
        }

        try {
            if (pendingQueue.isEmpty()) {
                view.showMessage("Queue is empty.");
                return null;
            }

            // Remove first item and add it to the end of queue
            CitizenModel skipped = pendingQueue.removeFirst();
            pendingQueue.addLast(skipped);

            // Return the new first item
            if (!pendingQueue.isEmpty()) {
                return pendingQueue.peekFirst();
            }

            return null;

        } catch (Exception e) {
            view.showError("Error skipping queue: " + e.getMessage());
            return null;
        }
    }

    public boolean updateCitizenWithCidChange(String oldCid, String newCid, String phone,
            String name, Gender gender, String province,
            String district, String municipality,
            String voteCenter, LocalDate dob) {
        if (!ensureLoggedIn()) {
            return false;
        }

        try {
            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(oldCid);

            if (citizen == null) {
                view.showError("Citizen not found.");
                return false;
            }

            // Validate new citizenship number
            if (newCid == null || newCid.trim().isEmpty()) {
                view.showError("Citizenship number cannot be empty.");
                return false;
            }

            if (!newCid.matches("\\d+")) {
                view.showError("Citizenship number must contain only digits.");
                return false;
            }

            // Check if new CID already exists (and it's different from old)
            if (!oldCid.equals(newCid)
                    && citizenRegistry.findByCitizenshipNumber(newCid) != null) {
                view.showError("Citizenship number " + newCid + " already exists.");
                return false;
            }

            // Update all fields
            citizen.setVoterName(name.trim());
            citizen.setPhoneNumber(phone.trim());
            citizen.setGender(gender);
            citizen.setDateOfBirth(dob);
            citizen.setProvince(province.trim());
            citizen.setDistrict(district.trim());
            citizen.setMunicipality(municipality.trim());
            citizen.setVoteCenter(voteCenter.trim());
            citizen.setLastModifiedDate(LocalDateTime.now());
            citizen.setLastModifiedBy(currentAdmin.getAdminId());

            // Update citizenship number if changed
            if (!oldCid.equals(newCid)) {
                citizenRegistry.updateCitizenshipNumber(oldCid, newCid, citizen);
            } else {
                citizenRegistry.updateCitizen(citizen);
            }

            // Record action
            recordAction("UPDATE", citizen);

            view.showMessage("Citizen updated successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError("Validation Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error updating citizen: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get total citizen count
     */
    public int getTotalCitizenCount() {
        return citizenRegistry.count();
    }

    // ==================== HELPER METHODS ====================
    /**
     * Ensures that admin is logged in before performing action
     */
    private boolean ensureLoggedIn() {
        if (!loggedIn || currentAdmin == null) {
            if (view != null) {
                view.showError("Please login as admin first.");
            }
            return false;
        }
        return true;
    }

    /**
     * Load initial admins
     */
    private void loadInitialAdmins() {
        try {
            adminRegistry.addAdmin(new AdminModel("admin1", "password123"));
            adminRegistry.addAdmin(new AdminModel("admin2", "admin456"));
            adminRegistry.addAdmin(new AdminModel("superuser", "superpass"));
        } catch (IllegalArgumentException e) {
            if (view != null) {
                view.showError("Failed to load initial admins: " + e.getMessage());
            }
        }
    }
}
