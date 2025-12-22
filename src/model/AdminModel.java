
package model;

/**
 *Represents an admin user in the system.
 *Includes basic authentication methods.
 * @author bijen
 */
public class AdminModel {
    
    private final String adminId;
    private String adminPassword;
    
    public AdminModel(String adminId, String adminPassword) {
        if (adminId == null || adminPassword == null) {
            throw new IllegalArgumentException("Admin credentials cannot be null");
        }
        
        this.adminId = adminId;
        this.adminPassword = adminPassword;
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
        return adminId.equals(inputId) && adminPassword.equals(inputPassword);
    }
    
    public void changePassword(String oldPassword, String newPassword) {
        if (!adminPassword.equals(oldPassword)) {
            throw new SecurityException("Invalid current password.");
        }
        this.adminPassword = newPassword;
    }
}
