package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.LocalDateTime;

/**
 * Represents a citizen's voting registration details. Includes validation for
 * all fields and calculates age from date of birth.
 *
 * @author bijen
 */
public class CitizenModel {

    private static final int MINIMUM_VOTING_AGE = 18;
    private static final int MAXIMUM_VOTING_AGE = 120;

    private LocalDateTime registrationDate;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;

    // ENUMS
    /**
     * Enum for gender values
     */
    public enum Gender {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        private final String display;

        Gender(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }

    /**
     * Enum for registration Status
     */
    public enum RegistrationStatus {
        PENDING("Pending"),
        APPROVED("Approved"),
        REJECTED("Rejected");

        private final String display;

        RegistrationStatus(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }

    // Fields
    private String citizenshipNumber; // Unique ID
    private String phoneNumber;
    private String voterName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private int age; // Derived from DOB
    private String province;
    private String district;
    private String municipality;
    private String voteCenter;
    private RegistrationStatus status;

    // Constructor with validation
    public CitizenModel(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district, String municipality,
            String voteCenter, LocalDate dateOfBirth) {

        validateFields(citizenshipNumber, phoneNumber, voterName, gender, province, district, municipality, voteCenter, dateOfBirth);

        this.citizenshipNumber = citizenshipNumber.trim();
        this.phoneNumber = phoneNumber.trim();
        this.voterName = voterName.trim();
        this.gender = gender;
        this.province = province.trim();
        this.district = district.trim();
        this.municipality = municipality.trim();
        this.voteCenter = voteCenter.trim();
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth);

        this.registrationDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
        this.lastModifiedBy = "SELF";

        // Default registration status
        this.status = RegistrationStatus.PENDING;
    }

    // Validation
    private void validateFields(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district,
            String municipality, String voteCenter, LocalDate dateOfBirth) {

        validateString(citizenshipNumber, "Citizenship number", "\\d+");
        validateString(voterName, "Voter name", "[a-zA-Z .'-]+");
        validateString(province, "Province", "[a-zA-Z0-9-' ]+");
        validateString(district, "District", "[a-zA-Z0-9-' ]+");
        validateString(municipality, "Municipality", "[a-zA-Z0-9-' ]+");
        validateString(voteCenter, "Vote center", "[a-zA-Z0-9-' ]+");

        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Citizenship number cannot be null or empty.");
        }

        if (voterName == null || voterName.trim().isEmpty()) {
            throw new IllegalArgumentException("Voter name cannot be null or empty");
        }

        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }

        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be 10 digits or not null.");
        }

        if (province == null || province.trim().isEmpty()) {
            throw new IllegalArgumentException("Province cannot be null or empty");
        }

        if (district == null || district.trim().isEmpty()) {
            throw new IllegalArgumentException("District cannot be null or empty");
        }

        if (municipality == null || municipality.trim().isEmpty()) {
            throw new IllegalArgumentException("Municipality cannot be null or empty");
        }

        if (voteCenter == null || voteCenter.trim().isEmpty()) {
            throw new IllegalArgumentException("Vote center cannot be null or empty");
        }

        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }

        int calculatedAge = calculateAge(dateOfBirth);

        if (calculatedAge < MINIMUM_VOTING_AGE) {
            throw new IllegalArgumentException("Citizen must be at least " + MINIMUM_VOTING_AGE + " years old.");
        }

        if (calculatedAge > MAXIMUM_VOTING_AGE) {
            throw new IllegalArgumentException("Invalid age detected: " + calculatedAge + " years.");
        }

    }

    private void validateString(String value, String fieldName, String pattern) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
        if (!value.trim().matches(pattern)) {
            throw new IllegalArgumentException(fieldName + " must match pattern: " + pattern);
        }
    }

    // Age calculation
    private int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    // Getters
    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getVoterName() {
        return voterName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getVoteCenter() {
        return voteCenter;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    // Setters with validation
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    public void setVoterName(String voterName) {
        if (voterName == null || voterName.trim().isEmpty()) {
            throw new IllegalArgumentException("Voter name cannot be null or empty.");
        }
        this.voterName = voterName;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null.");
        }
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }
        int calculatedAge = calculateAge(dateOfBirth);
        if (calculatedAge < MINIMUM_VOTING_AGE) {
            throw new IllegalArgumentException("Citizen must be at least " + MINIMUM_VOTING_AGE + " years old.");
        }
        if (calculatedAge > MAXIMUM_VOTING_AGE) {
            throw new IllegalArgumentException("Invalid age detected: " + calculatedAge + " years.");
        }
        this.dateOfBirth = dateOfBirth;
        this.age = calculatedAge;
    }

    public void setProvince(String province) {
        validateString(province, "Province", "[a-zA-Z0-9 \\-']+");
        this.province = province.trim();
    }

    public void setDistrict(String district) {
        validateString(district, "District", "[a-zA-Z0-9 \\-']+");
        this.district = district.trim();
    }

    public void setMunicipality(String municipality) {
        validateString(municipality, "Municipality", "[a-zA-Z0-9 \\-']+");

        this.municipality = municipality.trim();
    }

    public void setVoteCenter(String voteCenter) {
        validateString(voteCenter, "Vote center", "[a-zA-Z0-9 \\-']+");

        this.voteCenter = voteCenter.trim();
    }

    /**
     * Registration status should only be changed via Controller (Admin Action)
     *
     * @param status
     */
    public void setStatus(RegistrationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Registration status cannot be null");
        }
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime date) {
        this.lastModifiedDate = date;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String modifiedBy) {
        this.lastModifiedBy = modifiedBy;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Citizenship number cannot be null or empty.");
        }
        if (!citizenshipNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Citizenship number must contain only digits.");
        }
        this.citizenshipNumber = citizenshipNumber.trim();
    }
}
