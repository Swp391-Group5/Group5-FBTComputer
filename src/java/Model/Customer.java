/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author hungp
 */
public class Customer {

    private int customerId;
    private String customerName;
    private int customerAge;
    private String customerEmail;
    private String customerPassword;
    private boolean customerGender;
    private String customerAddress;
    private String customerCity;
    private String customerAvatar;
    private String customerPhoneNumber;
    private Date customerDOB;
    private boolean customerStatus;
    private int roleId;

    public Customer() {
    }

    public Customer(int customerId, String customerName, int customerAge, String customerEmail, String customerPassword, boolean customerGender, String customerCity, String customerAddress, String customerAvatar, String customerPhoneNumber, Date customerDOB, boolean customerStatus, int roleId) {
        this.customerId = customerId;
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

    public Customer(int customerId, String customerName, int customerAge, String customerEmail, boolean customerGender, String customerAddress, String customerCity, String customerPhoneNumber, Date customerDOB) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerEmail = customerEmail;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerDOB = customerDOB;
    }

    public Customer(int customerId, String customerPassword) {
        this.customerId = customerId;
        this.customerPassword = customerPassword;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public Date getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(Date customerDOB) {
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

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName=" + customerName + ", customerAge=" + customerAge + ", customerEmail=" + customerEmail + ", customerPassword=" + customerPassword + ", customerGender=" + customerGender + ", customerAddress=" + customerAddress + ", customerAvatar=" + customerAvatar + ", customerPhoneNumber=" + customerPhoneNumber + ", customerDOB=" + customerDOB + ", customerStatus=" + customerStatus + '}';
    }

}
