package org.example.jst_lab4;

public class Manager {
    private int managerId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String address;
    private String passportNumber;

    public Manager(int managerId, String fullName, String email, String mobileNumber, String address, String passportNumber) {
        this.managerId = managerId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.passportNumber = passportNumber;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
