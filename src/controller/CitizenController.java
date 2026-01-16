package controller;

import model.CitizenModel;
import model.CitizenModel.Gender;
import model.CitizenRegistryModel;
import view.MainFrame;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import javax.swing.JOptionPane;

/**
 * Controller for handling citizen operations between model and view. Validates
 * input and manages citizen registration workflow.
 *
 * @author bijen
 */
public class CitizenController {

    private CitizenRegistryModel citizenRegistry;
    private MainFrame view;

    private static final int MINIMUM_VOTING_AGE = 18;
    private static final int MAXIMUM_VOTING_AGE = 120;

    /**
     * Constructor
     * @param citizenRegistry
     * @param view
     */
    public CitizenController(CitizenRegistryModel citizenRegistry, MainFrame view) {
        this.citizenRegistry = citizenRegistry;
        this.view = view;
    }

    /**
     * Adds a new citizen from UI input with comprehensive validation.
     * @param citizenshipNumber
     * @param phoneNumber
     * @param voterName
     * @param gender
     * @param province
     * @param district
     * @param municipality
     * @param voteCenter
     * @param dob
     * @return 
     */
    public boolean addCitizenFromUI(String citizenshipNumber, String phoneNumber, String voterName,
            Gender gender, String province, String district,
            String municipality, String voteCenter, LocalDate dob) {
        try {
            // Step 1: Validate all inputs
            if (!validateInputs(citizenshipNumber, phoneNumber, voterName, gender, province,
                    district, municipality, voteCenter, dob)) {
                return false;
            }

            // Step 2: Check for duplicates
            if (citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim()) != null) {
                view.showError("Citizen with this citizenship number already exists.");
                return false;
            }

            // Step 3: Create citizen object
            CitizenModel citizen = new CitizenModel(
                    citizenshipNumber.trim(), phoneNumber.trim(),voterName.trim(),gender,province.trim(),
                    district.trim(),
                    municipality.trim(),
                    voteCenter.trim(),
                    dob
            );

            // Step 4: Add to registry
            citizenRegistry.addCitizen(citizen);

            view.showMessage("Citizen registered successfully!\nStatus: Pending Approval");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError("Validation Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error adding citizen: " + e.getMessage());
            return false;
        }
    }

    /**
     * Comprehensive input validation
     */
    private boolean validateInputs(String citizenshipNumber, String phoneNumber, String voterName,
            Gender gender, String province, String district,
            String municipality, String voteCenter, LocalDate dob) {

        // Validate citizenship number
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            view.showError("Citizenship number cannot be empty.");
            return false;
        }

        if (!citizenshipNumber.trim().matches("\\d+")) {
            view.showError("Citizenship number must contain only digits.");
            return false;
        }

        if (citizenshipNumber.trim().length() < 8 || citizenshipNumber.trim().length() > 12) {
            view.showError("Citizenship number must be between 8 and 12 digits.");
            return false;
        }

        // Validate phone number
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            view.showError("Phone number cannot be empty.");
            return false;
        }

        if (!phoneNumber.trim().matches("\\d{10}")) {
            view.showError("Phone number must be exactly 10 digits.");
            return false;
        }

        // Validate voter name
        if (voterName == null || voterName.trim().isEmpty()) {
            view.showError("Voter name cannot be empty.");
            return false;
        }

        if (!voterName.trim().matches("[a-zA-Z .'-]+")) {
            view.showError("Voter name can only contain letters, spaces, dots, hyphens, and apostrophes.");
            return false;
        }

        if (voterName.trim().length() < 2) {
            view.showError("Voter name must be at least 2 characters long.");
            return false;
        }

        // Validate gender
        if (gender == null) {
            view.showError("Please select a gender.");
            return false;
        }

        // Validate province
        if (province == null || province.trim().isEmpty()
                || province.trim().equalsIgnoreCase("Select")
                || province.trim().equalsIgnoreCase("-- Select Province --")) {
            view.showError("Please select a valid province.");
            return false;
        }

        // Validate district
        if (district == null || district.trim().isEmpty()
                || district.trim().equalsIgnoreCase("Select")
                || district.trim().equalsIgnoreCase("-- Select District --")) {
            view.showError("Please select a valid district.");
            return false;
        }

        // Validate municipality
        if (municipality == null || municipality.trim().isEmpty()) {
            view.showError("Municipality cannot be empty.");
            return false;
        }

        // Validate vote center
        if (voteCenter == null || voteCenter.trim().isEmpty()) {
            view.showError("Vote center cannot be empty.");
            return false;
        }

        // Validate date of birth
        if (dob == null) {
            view.showError("Date of birth is required.");
            return false;
        }

        // Validate age
        int age = calculateAge(dob);

        if (dob.isAfter(LocalDate.now())) {
            view.showError("Date of birth cannot be in the future.");
            return false;
        }

        if (age < MINIMUM_VOTING_AGE) {
            view.showError("Citizen must be at least " + MINIMUM_VOTING_AGE + " years old to register.\n"
                    + "Current age: " + age + " years.");
            return false;
        }

        if (age > MAXIMUM_VOTING_AGE) {
            view.showError("Invalid date of birth. Age cannot exceed " + MAXIMUM_VOTING_AGE + " years.\n"
                    + "Calculated age: " + age + " years.");
            return false;
        }

        return true;
    }

    /**
     * Calculate age from date of birth
     */
    private int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    /**
     * Update citizen information from UI
     */
    public boolean updateCitizenFromUI(String citizenshipNumber, String phoneNumber, String voterName,
            Gender gender, String province, String district,
            String municipality, String voteCenter, LocalDate dob) {
        try {
            // Find existing citizen
            CitizenModel existingCitizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber);

            if (existingCitizen == null) {
                view.showError("Citizen not found.");
                return false;
            }

            // Validate new inputs
            if (!validateInputs(citizenshipNumber, phoneNumber, voterName, gender, province,
                    district, municipality, voteCenter, dob)) {
                return false;
            }

            // Update citizen fields
            existingCitizen.setPhoneNumber(phoneNumber.trim());
            existingCitizen.setVoterName(voterName.trim());
            existingCitizen.setGender(gender);
            existingCitizen.setProvince(province.trim());
            existingCitizen.setDistrict(district.trim());
            existingCitizen.setMunicipality(municipality.trim());
            existingCitizen.setVoteCenter(voteCenter.trim());
            existingCitizen.setDateOfBirth(dob);

            // Update in registry
            citizenRegistry.updateCitizen(existingCitizen);

            view.showMessage("Citizen information updated successfully!");
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
     * Get citizen details for display
     */
    public CitizenModel getCitizenDetails(String citizenshipNumber) {
        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Citizenship number cannot be empty.");
                return null;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("Citizen not found with citizenship number: " + citizenshipNumber);
                return null;
            }

            return citizen;

        } catch (Exception e) {
            view.showError("Error retrieving citizen details: " + e.getMessage());
            return null;
        }
    }

    /**
     * Format citizen details for display
     */
    public String formatCitizenDetails(CitizenModel citizen) {
        if (citizen == null) {
            return "No citizen data available.";
        }

        StringBuilder details = new StringBuilder();
        details.append("========== CITIZEN DETAILS ==========\n\n");
        details.append("Citizenship Number: ").append(citizen.getCitizenshipNumber()).append("\n");
        details.append("Full Name: ").append(citizen.getVoterName()).append("\n");
        details.append("Gender: ").append(citizen.getGender().getDisplay()).append("\n");
        details.append("Date of Birth: ").append(citizen.getDateOfBirth()).append("\n");
        details.append("Age: ").append(citizen.getAge()).append(" years\n");
        details.append("Phone Number: ").append(citizen.getPhoneNumber()).append("\n\n");
        details.append("--- Location Details ---\n");
        details.append("Province: ").append(citizen.getProvince()).append("\n");
        details.append("District: ").append(citizen.getDistrict()).append("\n");
        details.append("Municipality: ").append(citizen.getMunicipality()).append("\n");
        details.append("Vote Center: ").append(citizen.getVoteCenter()).append("\n\n");
        details.append("--- Registration Status ---\n");
        details.append("Status: ").append(citizen.getStatus().getDisplay()).append("\n");
        details.append("\n=====================================");

        return details.toString();
    }

    /**
     * Validate single field (for real-time validation in UI)
     */
    public String validateField(String fieldName, String value) {
        try {
            switch (fieldName.toLowerCase()) {
                case "citizenship":
                case "citizenshipnumber":
                    if (value == null || value.trim().isEmpty()) {
                        return "Citizenship number cannot be empty";
                    }
                    if (!value.trim().matches("\\d+")) {
                        return "Citizenship number must contain only digits";
                    }
                    if (value.trim().length() < 8 || value.trim().length() > 12) {
                        return "Citizenship number must be between 8 and 12 digits";
                    }
                    break;

                case "phone":
                case "phonenumber":
                    if (value == null || value.trim().isEmpty()) {
                        return "Phone number cannot be empty";
                    }
                    if (!value.trim().matches("\\d{10}")) {
                        return "Phone number must be exactly 10 digits";
                    }
                    break;

                case "name":
                case "votername":
                    if (value == null || value.trim().isEmpty()) {
                        return "Name cannot be empty";
                    }
                    if (!value.trim().matches("[a-zA-Z .'-]+")) {
                        return "Name can only contain letters, spaces, dots, hyphens, and apostrophes";
                    }
                    if (value.trim().length() < 2) {
                        return "Name must be at least 2 characters long";
                    }
                    break;

                default:
                    return null;
            }

            return null; // Valid

        } catch (Exception e) {
            return "Validation error: " + e.getMessage();
        }
    }

    public CitizenModel findMyRecord(String citizenshipNumber) {
        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Please enter your citizenship number.");
                return null;
            }

            if (!citizenshipNumber.matches("\\d+")) {
                view.showError("Citizenship number must contain only digits.");
                return null;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("No record found with citizenship number: " + citizenshipNumber);
                return null;
            }

            return citizen;

        } catch (Exception e) {
            view.showError("Error finding record: " + e.getMessage());
            return null;
        }
    }

    public boolean updateMyRecord(String citizenshipNumber, String phone, String name,
            Gender gender, String province, String district,
            String municipality, String voteCenter, LocalDate dob) {
        try {
            // Find existing record
            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber);

            if (citizen == null) {
                view.showError("Record not found.");
                return false;
            }

            // Check if editing is allowed
            if (citizen.getStatus() == CitizenModel.RegistrationStatus.APPROVED) {
                view.showError("Cannot edit approved records. Please contact admin.");
                return false;
            }

            if (citizen.getStatus() == CitizenModel.RegistrationStatus.REJECTED) {
                view.showError("Cannot edit rejected records. Please contact admin or submit a new application.");
                return false;
            }

            // Validate all inputs
            if (!validateInputs(citizenshipNumber, phone, name, gender, province,
                    district, municipality, voteCenter, dob)) {
                return false;
            }

            // Update fields
            citizen.setVoterName(name.trim());
            citizen.setPhoneNumber(phone.trim());
            citizen.setGender(gender);
            citizen.setDateOfBirth(dob);
            citizen.setProvince(province.trim());
            citizen.setDistrict(district.trim());
            citizen.setMunicipality(municipality.trim());
            citizen.setVoteCenter(voteCenter.trim());

            // Update in registry
            citizenRegistry.updateCitizen(citizen);

            view.showMessage("Your record has been updated successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError("Validation Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error updating record: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteMyRecord(String citizenshipNumber) {
        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Invalid citizenship number.");
                return false;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("Record not found.");
                return false;
            }

            // Check if deletion is allowed
            if (citizen.getStatus() == CitizenModel.RegistrationStatus.APPROVED) {
                view.showError("Cannot delete approved records. Please contact admin.");
                return false;
            }

            if (citizen.getStatus() == CitizenModel.RegistrationStatus.REJECTED) {
                view.showError("Cannot delete rejected records. Please contact admin.");
                return false;
            }

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete your registration?\nThis action cannot be undone.",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return false;
            }

            boolean deleted = citizenRegistry.removeCitizen(citizenshipNumber.trim());

            if (deleted) {
                view.showMessage("Your record has been deleted successfully.");
                return true;
            } else {
                view.showError("Failed to delete record.");
                return false;
            }

        } catch (Exception e) {
            view.showError("Error deleting record: " + e.getMessage());
            return false;
        }
    }

    public CitizenModel checkMyStatus(String citizenshipNumber) {
        try {
            if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
                view.showError("Please enter citizenship number.");
                return null;
            }

            if (!citizenshipNumber.matches("\\d+")) {
                view.showError("Citizenship number must contain only digits.");
                return null;
            }

            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(citizenshipNumber.trim());

            if (citizen == null) {
                view.showError("No application found with this citizenship number.");
                return null;
            }

            return citizen;

        } catch (Exception e) {
            view.showError("Error checking status: " + e.getMessage());
            return null;
        }
    }

    public boolean updateMyRecordWithCidChange(String oldCid, String newCid, String phone,
            String name, Gender gender, String province,
            String district, String municipality,
            String voteCenter, LocalDate dob) {
        try {
            CitizenModel citizen = citizenRegistry.findByCitizenshipNumber(oldCid);

            if (citizen == null) {
                view.showError("Record not found.");
                return false;
            }

            // Check if editing is allowed
            if (citizen.getStatus() == CitizenModel.RegistrationStatus.APPROVED) {
                view.showError("Cannot edit approved records. Please contact admin.");
                return false;
            }

            if (citizen.getStatus() == CitizenModel.RegistrationStatus.REJECTED) {
                view.showError("Cannot edit rejected records. Please contact admin.");
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

            if (newCid.trim().length() < 8 || newCid.trim().length() > 12) {
                view.showError("Citizenship number must be between 8 and 12 digits.");
                return false;
            }

            // Check if new CID already exists (and it's different from old)
            if (!oldCid.equals(newCid)
                    && citizenRegistry.findByCitizenshipNumber(newCid) != null) {
                view.showError("Citizenship number " + newCid + " already exists.");
                return false;
            }

            // Validate all other inputs
            if (!validateInputs(newCid, phone, name, gender, province,
                    district, municipality, voteCenter, dob)) {
                return false;
            }

            // Update fields
            citizen.setVoterName(name.trim());
            citizen.setPhoneNumber(phone.trim());
            citizen.setGender(gender);
            citizen.setDateOfBirth(dob);
            citizen.setProvince(province.trim());
            citizen.setDistrict(district.trim());
            citizen.setMunicipality(municipality.trim());
            citizen.setVoteCenter(voteCenter.trim());
            citizen.setLastModifiedDate(LocalDateTime.now());
            citizen.setLastModifiedBy("SELF");

            // Update citizenship number if changed
            if (!oldCid.equals(newCid)) {
                citizenRegistry.updateCitizenshipNumber(oldCid, newCid, citizen);
            } else {
                citizenRegistry.updateCitizen(citizen);
            }

            view.showMessage("Your record has been updated successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            view.showError("Validation Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            view.showError("Error updating record: " + e.getMessage());
            return false;
        }
    }
}
