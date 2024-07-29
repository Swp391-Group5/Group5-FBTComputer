/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author hungp
 */
public class ProductOrder {
    private int productId;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    private String productBrand;
    private String productImage;
    private boolean productStatus;
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
    private int categoryId;
    private int adminID;
    private int productCount;

    public ProductOrder() {
    }

    public ProductOrder(int productId, String productName, String productDescription, float productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, Date createDate, String modifyBy, Date modifyDate, int categoryId, int adminID, int productCount) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.categoryId = categoryId;
        this.adminID = adminID;
        this.productCount = productCount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "ProductTop5{" + "productId=" + productId + ", productName=" + productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productBrand=" + productBrand + ", productImage=" + productImage + ", productStatus=" + productStatus + ", createBy=" + createBy + ", createDate=" + createDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", categoryId=" + categoryId + ", adminID=" + adminID + ", productCount=" + productCount + '}';
    }
    
    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(productPrice);
    }
}
