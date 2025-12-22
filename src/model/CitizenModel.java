package model;

/**
 * Represents a citizen's voting registration details.
 * 
 * @author bijen
 */

public class CitizenModel {
    private static final int MINIMUM_VOTING_AGE = 18;
    
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
    
    // Fields
    private final String citizenshipNumber;
    private String phoneNumber;
    private String voterName;
    private Gender gender;
    private int age;
    private String province;
    private String district;
    private String municipality;
    private String voteCenter;
    
    // Constructor with validation

    public CitizenModel(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district, String municipality, String voteCenter) {
        
        validateFields(citizenshipNumber, phoneNumber, voterName, gender, province, district, municipality, voteCenter);
        
        this.citizenshipNumber = citizenshipNumber;
        this.phoneNumber = phoneNumber;
        this.voterName = voterName;
        this.gender = gender;
        this.province = province;
        this.district = district;
        this.municipality = municipality;
        this.voteCenter = voteCenter;
    }
    
    private void validateFields(String citizenshipNumber, String phoneNumber, String voterName, Gender gender, String province, String district, String municipality, String voteCenter) {
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Citizenship number cannot be null or empty.");
        }
        
        if (voterName == null || voterName.trim().isEmpty()) {
            throw new IllegalArgumentException("Voter name cannot be null or empty");
        }
        
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        
        if (age < MINIMUM_VOTING_AGE) {
            throw new IllegalArgumentException("Citizen must be " + MINIMUM_VOTING_AGE + " or older");
        }
        
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
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
    
    // Setters with validation

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setVoteCenter(String voteCenter) {
        this.voteCenter = voteCenter;
    }  
}