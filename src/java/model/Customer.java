/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL DN
 */
public class Customer {
    private int customerID;
    private String customerName;
    private int customerAge;
    private String customerEmail;
    private String customerPassword;
    private boolean customerGender;
    private String customerAddress;
    private String customerCity;
    private String customerAvatar;
    private String customerPhoneNumber;
    private java.sql.Date customerDOB;
    private boolean customerStatus;
    private int roleId;

    // Constructor không tham số
    public Customer() {}

    // Constructor có tham số
    public Customer(int customerID, String customerName, int customerAge, String customerEmail, String customerPassword, boolean customerGender, String customerAddress, String customerCity, String customerAvatar, String customerPhoneNumber, java.sql.Date customerDOB, boolean customerStatus, int roleId) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerAvatar = customerAvatar;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerDOB = customerDOB;
        this.customerStatus = customerStatus;
        this.roleId = roleId;
    }

    // Getter và Setter
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public boolean isCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(boolean customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerAvatar() {
        return customerAvatar;
    }

    public void setCustomerAvatar(String customerAvatar) {
        this.customerAvatar = customerAvatar;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public java.sql.Date getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(java.sql.Date customerDOB) {
        this.customerDOB = customerDOB;
    }

    public boolean isCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(boolean customerStatus) {
        this.customerStatus = customerStatus;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    // Phương thức toString()
    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerName='" + customerName + '\'' +
                ", customerAge=" + customerAge +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerGender=" + customerGender +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerAvatar='" + customerAvatar + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerDOB=" + customerDOB +
                ", customerStatus=" + customerStatus +
                ", roleId=" + roleId +
                '}';
    }
}

