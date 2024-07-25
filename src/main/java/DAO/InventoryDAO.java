package DAO;

import Models.Items;
import java.util.Date;
import java.util.List;

public interface InventoryDAO {
    List<Items> getInventory();
    boolean addItem(Items item);
    boolean updateItem(int itemId, String newItemName, int newQuantity, Date newExpirationDate, double newRetailerPrice, boolean newIsDonation, boolean newIsSale, double newDiscountPrice);
    boolean deleteItem(int itemId);
    List<Items> getDonationItems();
    boolean flagSurplusItem(int surplusItemId);
    boolean updateQuantity(int itemId, int newQuantity);
    boolean decreaseQuantity(int itemId);
}
