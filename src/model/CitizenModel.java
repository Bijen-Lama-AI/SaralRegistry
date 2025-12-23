package model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a citizen's voting registration details.
 * Includes validation for all fields and calculates age from date of birth.
 * @author bijen
 */
public class CitizenModel {
    private static final int MINIMUM_VOTING_AGE = 18;
    private static final int MAXIMUM_VOTING_AGE = 120;
    
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
    private final String citizenshipNumber; // Unique ID
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

    public CitizenModel(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district, String municipality, String voteCenter, LocalDate dateOfBirth) {
        
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
        
        // Default registration status
        this.status = RegistrationStatus.PENDING;
    }
    
    // Validation
    private void validateFields(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district,
            String municipality, String voteCenter, LocalDate dateOfBirth) {
        
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
        
        if (dateOfBirth == null) 
            throw new IllegalArgumentException("Date of birth cannot be null.");
        
        int calculatedAge = calculateAge(dateOfBirth);
        
        if (calculatedAge < MINIMUM_VOTING_AGE)
            throw new IllegalArgumentException("Citizen must be at least" + MINIMUM_VOTING_AGE + " years old.");
        
        if (calculatedAge > MAXIMUM_VOTING_AGE)
            throw new IllegalArgumentException("Invalid age detected: " + calculatedAge + " years.");
            
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
    
    public LocalDate getDateOfBith() {
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
        if (calculatedAge < MINIMUM_VOTING_AGE)
            throw new IllegalArgumentException("Citizen must be at least " + MINIMUM_VOTING_AGE + " years old.");
        if (calculatedAge > MAXIMUM_VOTING_AGE) 
            throw new IllegalArgumentException("Invalid age detected: " + calculatedAge + " years.");
        this.dateOfBirth = dateOfBirth;
        this.age = calculatedAge;
    }

    public void setProvince(String province) {
        if (province == null || province.trim().isEmpty() || !province.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Province cannot be null or empty.");
        }
        this.province = province.trim();
    }

    public void setDistrict(String district) {
        if (district == null || district.trim().isEmpty() || !district.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("District cannot be null or empty.");
        }
        this.district = district.trim();
    }

    public void setMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty() || !municipality.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Municipality cannot be null or empty.");
        }
        this.municipality = municipality.trim();
    }

    public void setVoteCenter(String voteCenter) {
        if (voteCenter == null || voteCenter.trim().isEmpty() || !voteCenter.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Vote center cannot be null or empty.");
        }
        this.voteCenter = voteCenter.trim();
    }  
    
    /**
     * Registration status should only be changed via Controller (Admin Action)
     * @param status
     */
    public void setStatus(RegistrationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Registration status cannot be null");
        }
        this.status = status;
    }
}