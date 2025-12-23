package model;

/**
 * Represents an admin user in the system.
 * Includes basic authentication and password management.
 * @author bijen
 */
public class AdminModel {
    
    // Admin ID is constant once created.
    private final String adminId;
    
    // Admin password can be changed.
    private String adminPassword;
    
    /**
     * Constructor for AdminModel.
     * Ensures that ID and password are not null or empty.
     * 
     * @param adminId Amin ID (unique)
     * @param adminPassword Initial password
     * @throws IllegalArgumentException if ID or password is null/empty
     */
    public AdminModel(String adminId, String adminPassword) {
        if (adminId == null || adminId.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin ID cannot be null or empty");
        }
        
        if (adminPassword == null || adminPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin password cannot be null or empty");
        }

        this.adminId = adminId.trim();
        this.adminPassword = adminPassword.trim();
    }

    public String getAdminId() {
        return adminId;
    }
    
    /**
     * Checks if the provided credentials match the administrator's credentials.
     * @param inputId The input admin ID.
     * @param inputPassword The input password.
     * @return true if both match otherwise false.
     */
    public boolean authenticate(String inputId, String inputPassword) {
        if (inputId == null || inputPassword == null) {
            return false;
        }
        return adminId.equals(inputId.trim()) && adminPassword.equals(inputPassword.trim());
    }
    
    /**
     * Changes the admin password
     * @param oldPassword Current password
     * @param newPassword New password to set
     * @throws IllegalArgumentException if new password is null, empty or too short
     */
    public void changePassword(String oldPassword, String newPassword) {
        if (oldPassword == null || !adminPassword.equals(oldPassword.trim())) {
            throw new SecurityException("Invalid current password.");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty.");
        } 
        if (newPassword.trim().length() < 8) {
            throw new IllegalArgumentException("New password must be at least 8 characters long.");
        }
        
        this.adminPassword = newPassword.trim();
    }
}
