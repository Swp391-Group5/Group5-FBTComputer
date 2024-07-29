/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL DN
 */
public class WareHouse {
    private String serialNumber;
    private int productId;

    public WareHouse() {
    }

    public WareHouse(String serialNumber, int productId) {
        this.serialNumber = serialNumber;
        this.productId = productId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "WareHouse{" + "serialNumber=" + serialNumber + ", productId=" + productId + '}';
    }
    
}
