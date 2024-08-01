/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LEGION
 */
public class ProductQuality {
    
    private String productName;
    private String userName;
    private int warrantyCount;
    private float warrantyRatio;
    private String status;

    public ProductQuality() {
    }

    public ProductQuality(String productName, String userName, int warrantyCount, float warrantyRatio, String status) {
        this.productName = productName;
        this.userName = userName;
        this.warrantyCount = warrantyCount;
        this.warrantyRatio = warrantyRatio;
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWarrantyCount() {
        return warrantyCount;
    }

    public void setWarrantyCount(int warrantyCount) {
        this.warrantyCount = warrantyCount;
    }

    public float getWarrantyRatio() {
        return warrantyRatio;
    }

    public void setWarrantyRatio(float warrantyRatio) {
        this.warrantyRatio = warrantyRatio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductQuality{" + "productName=" + productName + ", userName=" + userName + ", warrantyCount=" + warrantyCount + ", warrantyRatio=" + warrantyRatio + ", status=" + status + '}';
    }
    
    
    
    
}
