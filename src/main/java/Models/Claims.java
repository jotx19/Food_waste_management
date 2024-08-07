package Models;

import java.util.Date;

/**
 * The `Claims` class represents a claim made for a particular item in the system.
 * It includes details about the claim such as the claim ID, item ID, the date of the claim,
 * and the quantity claimed.
 */
public class Claims {
    private int claimId;
    private int itemId;
    private Date claimDate;
    private int quantityClaimed;

    /**
     * Constructs a new `Claims` instance with the specified item ID, claim date, and quantity claimed.
     *
     * @param itemId The ID of the item being claimed.
     * @param claimDate The date when the claim was made.
     * @param quantityClaimed The quantity of the item being claimed.
     */
    public Claims(int itemId, Date claimDate, int quantityClaimed) {
        this.itemId = itemId;
        this.claimDate = claimDate;
        this.quantityClaimed = quantityClaimed;
    }

    /**
     * Gets the claim ID.
     *
     * @return The unique identifier for this claim.
     */
    public int getClaimId() {
        return claimId;
    }

    /**
     * Sets the claim ID.
     *
     * @param claimId The unique identifier to assign to this claim.
     */
    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    /**
     * Gets the item ID associated with this claim.
     *
     * @return The ID of the item being claimed.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the item ID for this claim.
     *
     * @param itemId The ID of the item being claimed.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the date when the claim was made.
     *
     * @return The date of the claim.
     */
    public Date getClaimDate() {
        return claimDate;
    }

    /**
     * Sets the date when the claim was made.
     *
     * @param claimDate The date to set for this claim.
     */
    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    /**
     * Gets the quantity of the item claimed.
     *
     * @return The quantity of the item being claimed.
     */
    public int getQuantityClaimed() {
        return quantityClaimed;
    }

    /**
     * Sets the quantity of the item claimed.
     *
     * @param quantityClaimed The quantity of the item to set.
     */
    public void setQuantityClaimed(int quantityClaimed) {
        this.quantityClaimed = quantityClaimed;
    }
}
