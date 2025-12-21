package model;

/**
 * Represents a citizen's voting registration details.
 * Optional field can be set later if needed.
 * @author bijen
 */

public class CitizenModel {
    
    // Required Fields
    private String phoneNumber;
    private String citizenshipNumber;
    private String voterName;
    private String gender;
    private int age;
    private String province;
    private String district;
    private String municipality;
    private int wardNumber;
    private String voteCenter;

    public CitizenModel(String phoneNumber, String citizenshipNumber, String voterName, String gender, int age, String province, String district, String municipality, int wardNumber, String voteCenter) {
        this.phoneNumber = phoneNumber;
        this.citizenshipNumber = citizenshipNumber;
        this.voterName = voterName;
        this.gender = gender;
        this.age = age;
        this.province = province;
        this.district = district;
        this.municipality = municipality;
        this.wardNumber = wardNumber;
        this.voteCenter = voteCenter;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or older to register.");
        }
        this.age = age;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(int wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getVoteCenter() {
        return voteCenter;
    }

    public void setVoteCenter(String voteCenter) {
        this.voteCenter = voteCenter;
    }   
}