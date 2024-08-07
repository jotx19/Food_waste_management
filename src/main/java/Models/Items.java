package Models;

import java.util.Date;

/**
 * The `Items` class represents an item in the inventory with various attributes
 * including its ID, name, quantity, expiration date, pricing details, and status
 * indicating whether it is for donation or sale.
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
     * Constructs a new `Items` instance with the specified attributes.
     *
     * @param itemId The unique identifier for the item.
     * @param itemName The name of the item.
     * @param quantity The quantity of the item available.
     * @param expirationDate The expiration date of the item.
     * @param retailerPrice The retail price of the item.
     * @param isDonation Indicates whether the item is for donation.
     * @param isSale Indicates whether the item is for sale.
     * @param discountPrice The discounted price of the item, if applicable.
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
     * Default constructor for the `Items` class.
     */
    public Items() {
    }

    /**
     * Gets the unique identifier for the item.
     *
     * @return The item ID.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the unique identifier for the item.
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
     * Gets the quantity of the item available.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item available.
     *
     * @param quantity The quantity to set.
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
     * Gets the retail price of the item.
     *
     * @return The retail price.
     */
    public double getRetailerPrice() {
        return retailerPrice;
    }

    /**
     * Sets the retail price of the item.
     *
     * @param retailerPrice The retail price to set.
     */
    public void setRetailerPrice(double retailerPrice) {
        this.retailerPrice = retailerPrice;
    }

    /**
     * Checks if the item is intended for donation.
     *
     * @return True if the item is for donation, false otherwise.
     */
    public boolean isDonation() {
        return isDonation;
    }

    /**
     * Sets whether the item is intended for donation.
     *
     * @param donation True if the item is for donation, false otherwise.
     */
    public void setDonation(boolean donation) {
        isDonation = donation;
    }

    /**
     * Checks if the item is intended for sale.
     *
     * @return True if the item is for sale, false otherwise.
     */
    public boolean isSale() {
        return isSale;
    }

    /**
     * Sets whether the item is intended for sale.
     *
     * @param sale True if the item is for sale, false otherwise.
     */
    public void setSale(boolean sale) {
        isSale = sale;
    }

    /**
     * Gets the discounted price of the item, if applicable.
     *
     * @return The discounted price.
     */
    public double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Sets the discounted price of the item.
     *
     * @param discountPrice The discounted price to set.
     */
    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
