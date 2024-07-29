/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Feedback {

    private int feedbackId;
    private String userName;
    private int userId;
    private int roleId;
    private int starRate;
    private String feedbackDescription;
    private byte [] feedbackImage;
    private Date feedbackDate;
    private boolean feedbackStatus;
    private int orderId;
    private int productId;

    public Feedback() {
    }

    public Feedback(int feedbackId, String userName, int userId, int roleId, int starRate, String feedbackDescription, byte[] feedbackImage, Date feedbackDate, boolean feedbackStatus, int orderId, int productId) {
        this.feedbackId = feedbackId;
        this.userName = userName;
        this.userId = userId;
        this.roleId = roleId;
        this.starRate = starRate;
        this.feedbackDescription = feedbackDescription;
        this.feedbackImage = feedbackImage;
        this.feedbackDate = feedbackDate;
        this.feedbackStatus = feedbackStatus;
        this.orderId = orderId;
        this.productId = productId;
    }

    
    

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public byte[] getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(byte[] feedbackImage) {
        this.feedbackImage = feedbackImage;
    }
    
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public boolean isFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(boolean feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    

}
