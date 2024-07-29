/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author hungp
 */
public class Admin {

    private int adminId;
    private String adminName;
    private int adminAge;
    private String adminEmail;
    private String adminPassword;
    private boolean adminGender;
    private String adminAddress;
    private String adminCity;
    private byte[] adminAvatar; // Changed to byte[] to handle blob
    private String adminPhoneNumber;
    private Date adminDOB;
    private boolean adminStatus;
    private int roleId;

    // Constructors, getters, and setters
    public Admin() {
    }

    public Admin(boolean adminStatus, int roleId) {
        this.adminStatus = adminStatus;
        this.roleId = roleId;
    }
    
    public Admin(int adminId, String adminName, int adminAge, String adminEmail, String adminPassword, boolean adminGender, String adminAddress, String adminCity, byte[] adminAvatar, String adminPhoneNumber, Date adminDOB, boolean adminStatus, int roleId) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminGender = adminGender;
        this.adminAddress = adminAddress;
        this.adminCity = adminCity;
        this.adminAvatar = adminAvatar;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminDOB = adminDOB;
        this.adminStatus = adminStatus;
        this.roleId = roleId;
    }

    public Admin(int adminId, String adminName, int adminAge, String adminEmail, String adminPassword, boolean adminGender, String adminAddress, String adminCity, byte[] adminAvatar, String adminPhoneNumber, Date adminDOB, boolean adminStatus) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminGender = adminGender;
        this.adminAddress = adminAddress;
        this.adminCity = adminCity;
        this.adminAvatar = adminAvatar;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminDOB = adminDOB;
        this.adminStatus = adminStatus;
    }
    
    public Admin(String adminName, int adminAge, String adminEmail, String adminPassword, boolean adminGender, String adminAddress, String adminCity, String adminPhoneNumber, Date adminDOB, boolean adminStatus, int roleId) {
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminGender = adminGender;
        this.adminAddress = adminAddress;
        this.adminCity = adminCity;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminDOB = adminDOB;
        this.adminStatus = adminStatus;
        this.roleId = roleId;
    }
    
    public Admin(String adminName, int adminAge, String adminEmail, String adminPassword, boolean adminGender, String adminAddress, String adminCity, byte[] adminAvatar, String adminPhoneNumber, Date adminDOB, boolean adminStatus, int roleId) {
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminGender = adminGender;
        this.adminAddress = adminAddress;
        this.adminCity = adminCity;
        this.adminAvatar = adminAvatar;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminDOB = adminDOB;
        this.adminStatus = adminStatus;
        this.roleId = roleId;
    }

    public Admin(int adminId, String adminName, int adminAge, String adminEmail, boolean adminGender, String adminAddress, String adminCity, String adminPhoneNumber, Date adminDOB) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminAge = adminAge;
        this.adminEmail = adminEmail;
        this.adminGender = adminGender;
        this.adminAddress = adminAddress;
        this.adminCity = adminCity;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminDOB = adminDOB;
    }

    public Admin(int adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }
    
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(int adminAge) {
        this.adminAge = adminAge;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public boolean isAdminGender() {
        return adminGender;
    }

    public void setAdminGender(boolean adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAdminCity() {
        return adminCity;
    }

    public void setAdminCity(String adminCity) {
        this.adminCity = adminCity;
    }

    public byte[] getAdminAvatar() {
        return adminAvatar;
    }

    public void setAdminAvatar(byte[] adminAvatar) {
        this.adminAvatar = adminAvatar;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public Date getAdminDOB() {
        return adminDOB;
    }

    public void setAdminDOB(Date adminDOB) {
        this.adminDOB = adminDOB;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Admin{" + "adminId=" + adminId + ", adminName=" + adminName + ", adminAge=" + adminAge + ", adminEmail=" + adminEmail + ", adminPassword=" + adminPassword + ", adminGender=" + adminGender + ", adminAddress=" + adminAddress + ", adminCity=" + adminCity + ", adminAvatar=" + adminAvatar + ", adminPhoneNumber=" + adminPhoneNumber + ", adminDOB=" + adminDOB + ", adminStatus=" + adminStatus + ", roleId=" + roleId + '}';
    }

}
