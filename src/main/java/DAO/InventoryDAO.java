package DAO;

import Models.Items;
import java.util.Date;
import java.util.List;

public interface InventoryDAO {
    List<Items> getInventory();
    boolean addItemToInventory(Items item);
    boolean updateInventoryItem(int itemId, String newItemName, int newQuantity, Date newExpirationDate, double newRetailerPrice, boolean newIsDonation, boolean newIsSale, double newDiscountPrice);
    boolean deleteInventoryItem(int itemId);
    List<Items> getDonationItems();
    boolean flagSurplusItem(int surplusItemId);
    boolean updateInventoryItemQuantity(int itemId, int newQuantity);
    boolean decreaseInventoryItemQuantity(int itemId);
}
