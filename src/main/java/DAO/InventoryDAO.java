package DAO;

import Models.Items;
import java.util.Date;
import java.util.List;

/**
 * The InventoryDAO interface defines methods for managing inventory data in the Food Waste Reduction Platform.
 * It provides operations for retrieving, adding, updating, and deleting inventory items,
 * as well as managing donation items and surplus inventory.
 */
public interface InventoryDAO {

    /**
     * Retrieves a list of all items in the inventory.
     *
     * @return A List of Items representing the entire inventory.
     */
    List<Items> getInventory();

    /**
     * Adds a new item to the inventory.
     *
     * @param item The Items object representing the new item to be added.
     * @return true if the item was successfully added, false otherwise.
     */
    boolean addItem(Items item);

    /**
     * Updates an existing item in the inventory.
     *
     * @param itemId The ID of the item to be updated.
     * @param newItemName The new name for the item.
     * @param newQuantity The new quantity of the item.
     * @param newExpirationDate The new expiration date of the item.
     * @param newRetailerPrice The new retailer price of the item.
     * @param newIsDonation Indicates whether the item is now for donation.
     * @param newIsSale Indicates whether the item is now for sale.
     * @param newDiscountPrice The new discounted price of the item (if applicable).
     * @return true if the item was successfully updated, false otherwise.
     */
    boolean updateItem(int itemId, String newItemName, int newQuantity, Date newExpirationDate, double newRetailerPrice, boolean newIsDonation, boolean newIsSale, double newDiscountPrice);

    /**
     * Deletes an item from the inventory.
     *
     * @param itemId The ID of the item to be deleted.
     * @return true if the item was successfully deleted, false otherwise.
     */
    boolean deleteItem(int itemId);

    /**
     * Retrieves a list of all items available for donation.
     *
     * @return A List of Items that are marked for donation.
     */
    List<Items> getDonationItems();

    /**
     * Flags an item as surplus in the inventory.
     *
     * @param surplusItemId The ID of the item to be flagged as surplus.
     * @return true if the item was successfully flagged as surplus, false otherwise.
     */
    boolean flagSurplusItem(int surplusItemId);

    /**
     * Updates the quantity of an existing item in the inventory.
     *
     * @param itemId The ID of the item to update.
     * @param newQuantity The new quantity of the item.
     * @return true if the quantity was successfully updated, false otherwise.
     */
    boolean updateQuantity(int itemId, int newQuantity);

    /**
     * Decreases the quantity of an item in the inventory by one.
     *
     * @param itemId The ID of the item to decrease.
     * @return true if the quantity was successfully decreased, false otherwise.
     */
    boolean decreaseQuantity(int itemId);

    /**
     * Marks an item as claimed in the inventory.
     *
     * @param itemId The ID of the item to be claimed.
     * @return true if the item was successfully claimed, false otherwise.
     */
    boolean claimItem(int itemId);
}