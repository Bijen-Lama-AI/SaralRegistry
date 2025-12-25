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
    
    // getter for adminId
    public String getAdminId() {
        return adminId;
    }
    
    /**
     * Checks if the provided credentials match the administrator's credentials.
     * @param adminId The input admin ID.
     * @param password The input password.
     * @return true if both match otherwise false.
     */
    public boolean authenticate(String adminId, String password) {
        if (adminId == null || password == null) {
            return false;
        }
        return this.adminId.equals(adminId.trim()) && this.adminPassword.equals(password.trim());
    }
    
    /**
     * Changes the admin password
     * @param oldPassword Current password
     * @param newPassword New password to set
     * @throws IllegalArgumentException if new password is null, empty or too short
     * @throws SecurityException if old password is incorrect
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof AdminModel)) {
            return true;
        }
        
        AdminModel other = (AdminModel) obj;
        return adminId.equals(other.adminId);
    }
    
    @Override
    public int hashCode() {
        return adminId.hashCode();
    }
}
