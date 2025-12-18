/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bijen
 */

public class Citizen {
    
    private String phoneNumber;
    private String citizenshipNumber;
    private String nationalIdentityNumber; // optional
    private String voterName;
    private String gender;
    private int age;
    private String province;
    private String district;
    private String municipality;
    private int wardNumber;
    private String voteCenter;
    private String status; // pending, approved, optional
    private String motherName; // optional
    private String fatherName; // optional
    private String spouseName; // optioanl
    private String email; // optional
    
    public Citizen(String phoneNumber, String citizenshipNumber, String nationalIdentityNumber, 
            String voterName, String gender, int age, String province,
            String district, String municipality, int wardNumber, String voteCenter) {
        
        this.phoneNumber = phoneNumber;
        this.citizenshipNumber = citizenshipNumber;
        this.nationalIdentityNumber = nationalIdentityNumber; // can be null
        this.voterName = voterName;
        this.gender = gender;
        this.age = age;
        this.province = province;
        this.district = district;
        this.municipality = municipality;
        this.wardNumber = wardNumber;
        this.voteCenter = voteCenter;
        
        this.status = "PENDING"; // default
        
        this.motherName = null;
        this.fatherName = null;
        this.spouseName = null;
        this.email = null;
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

    public String getNationalIdentityNumber() {
        return nationalIdentityNumber;
    }

    public void setNationalIdentityNumber(String nationalIdentityNumber) {
        this.nationalIdentityNumber = nationalIdentityNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("PENDING") || status.equals("APPROVED") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid Status");
        }
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
