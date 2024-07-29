
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quanb
 */
public class Warranty {
    private int warrantyId;
    private String productName;
    private String warrantyImage;
    private String warrantyPeriod;
    private int orderHistoryDetailId;
    private int userID;
    private String isSucsess;
    private String userName;
    private int adminID;
    private String adminName;
    private String PhoneNumber;
    private String email;
    private String serialNumber;
    private String causeError;
    public Warranty() {
    }

    public Warranty(int warrantyId, String productName, String warrantyImage, String warrantyPeriod, int orderHistoryDetailId, int adminID) {
        this.warrantyId = warrantyId;
        this.productName = productName;
        this.warrantyImage = warrantyImage;
        this.warrantyPeriod = warrantyPeriod;
        this.orderHistoryDetailId = orderHistoryDetailId;
        this.adminID = adminID;
    }

    public Warranty(int warrantyId, String productName, String warrantyImage, String warrantyPeriod, int orderHistoryDetailId, int userID, String isSucsess, String userName, int adminID, String adminName, String phoneNumber, String email, String serialNumber, String causeError) {
        this.warrantyId = warrantyId;
        this.productName = productName;
        this.warrantyImage = warrantyImage;
        this.warrantyPeriod = warrantyPeriod;
        this.orderHistoryDetailId = orderHistoryDetailId;
        this.userID = userID;
        this.isSucsess = isSucsess;
        this.userName = userName;
        this.adminID = adminID;
        this.adminName = adminName;
        this.PhoneNumber = phoneNumber;
        this.email = email;
        this.serialNumber = serialNumber;
        this.causeError = causeError;
    }

    public String getCauseError() {
        return causeError;
    }

    public void setCauseError(String causeError) {
        this.causeError = causeError;
    }
    

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

   
    

    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarrantyImage() {
        return warrantyImage;
    }

    public void setWarrantyImage(String warrantyImage) {
        this.warrantyImage = warrantyImage;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getOrderHistoryDetailId() {
        return orderHistoryDetailId;
    }

    public void setOrderHistoryDetailId(int orderHistoryDetailId) {
        this.orderHistoryDetailId = orderHistoryDetailId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getIsSucsess() {
        return isSucsess;
    }

    public void setIsSucsess(String isSucsess) {
        this.isSucsess = isSucsess;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "Warranty{" + "warrantyId=" + warrantyId + ", productName=" + productName + ", warrantyImage=" + warrantyImage + ", warrantyPeriod=" + warrantyPeriod + ", orderHistoryDetailId=" + orderHistoryDetailId + ", userID=" + userID + ", isSucsess=" + isSucsess + ", userName=" + userName + ", adminID=" + adminID + ", adminName=" + adminName + ", PhoneNumber=" + PhoneNumber + ", email=" + email + ", serialNumber=" + serialNumber + ", causeError=" + causeError + '}';
    }

    

   
    

   

    
    
}

