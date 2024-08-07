/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

public class Items {
    private int itemId;
    private String itemName;
    private int quantity;
    private Date expirationDate;
    private double retailerPrice;
    private boolean isDonation;
    private boolean isSale;
    private double discountPrice;
    
    public Items(int itemId, String itemName, int quantity, Date expirationDate, double retailerPrice, boolean isDonation, boolean isSale, double discountPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.retailerPrice = retailerPrice;
        this.isDonation = isDonation;
        this.isSale = isSale;
        this.discountPrice = discountPrice;
    }

    public Items() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getRetailerPrice() {
        return retailerPrice;
    }

    public void setRetailerPrice(double retailerPrice) {
        this.retailerPrice = retailerPrice;
    }

    public boolean isDonation() {
        return isDonation;
    }

    public void setDonation(boolean donation) {
        isDonation = donation;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
