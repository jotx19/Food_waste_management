package model;

import java.util.Date;

/**
 * Represents an item in the inventory.
 */
public class Items {
    private int itemId;
    private String itemName;
    private int quantity;
    private Date expirationDate;
    private double retailerPrice;
    private boolean isDonation;
    private boolean isSale;
    private double discountPrice;

    /**
     * Constructs an InventoryItem object with specified attributes.
     *
     * @param itemId         The ID of the item.
     * @param itemName       The name of the item.
     * @param quantity       The quantity of the item.
     * @param expirationDate The expiration date of the item.
     * @param retailerPrice  The retailer price of the item.
     * @param isDonation     Indicates if the item is a donation.
     * @param isSale         Indicates if the item is on sale.
     * @param discountPrice  The discount price of the item.
     */
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

    /**
     * Constructs an empty InventoryItem object.
     */
    public Items() {
    }

    /**
     * Gets the ID of the item.
     *
     * @return The item ID.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the ID of the item.
     *
     * @param itemId The item ID to set.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the name of the item.
     *
     * @return The item name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the item.
     *
     * @param itemName The item name to set.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The item quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity The item quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the expiration date of the item.
     *
     * @return The expiration date.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the item.
     *
     * @param expirationDate The expiration date to set.
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the retailer price of the item.
     *
     * @return The retailer price.
     */
    public double getRetailerPrice() {
        return retailerPrice;
    }

    /**
     * Sets the retailer price of the item.
     *
     * @param retailerPrice The retailer price to set.
     */
    public void setRetailerPrice(double retailerPrice) {
        this.retailerPrice = retailerPrice;
    }

    /**
     * Checks if the item is a donation.
     *
     * @return true if the item is a donation, false otherwise.
     */
    public boolean isDonation() {
        return isDonation;
    }

    /**
     * Sets whether the item is a donation.
     *
     * @param donation true if the item is a donation, false otherwise.
     */
    public void setDonation(boolean donation) {
        isDonation = donation;
    }

    /**
     * Checks if the item is on sale.
     *
     * @return true if the item is on sale, false otherwise.
     */
    public boolean isSale() {
        return isSale;
    }

    /**
     * Sets whether the item is on sale.
     *
     * @param sale true if the item is on sale, false otherwise.
     */
    public void setSale(boolean sale) {
        isSale = sale;
    }

    /**
     * Gets the discount price of the item.
     *
     * @return The discount price.
     */
    public double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Sets the discount price of the item.
     *
     * @param discountPrice The discount price to set.
     */
    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
