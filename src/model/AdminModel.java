
package model;

/**
 *Represents an admin user in the system.
 *Includes basic authentication methods.
 * @author bijen
 */
public class AdminModel {
    private String adminId;
    private String adminPassword;
    
    public AdminModel(String adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
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
}
