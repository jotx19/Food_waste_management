package DAO;

import Models.Claims;

/**
 * The ClaimDAO interface provides methods for managing claim data in the Food Waste Reduction Platform.
 * It defines operations for adding, updating, and querying claims made by charitable organizations.
 */
public interface ClaimDAO {

    /**
     * Adds a new claim to the system.
     *
     * @param claim The Claims object representing the new claim to be added.
     * @return true if the claim was successfully added, false otherwise.
     */
    boolean addClaim(Claims claim);

    /**
     * Adds a new claim or updates an existing one in the system.
     *
     * @param claim The Claims object to be added or updated.
     * @return true if the operation was successful, false otherwise.
     */
    boolean addOrUpdate(Claims claim);

    /**
     * Checks if a claim already exists for a specific item.
     *
     * @param itemId The ID of the item to check for existing claims.
     * @return true if a claim exists for the item, false otherwise.
     */
    boolean existingClaim(int itemId);

    /**
     * Updates the quantity of an existing claim for a specific item.
     *
     * @param itemId The ID of the item for which to update the claim quantity.
     * @param quantityClaimed The new quantity to be claimed.
     * @return true if the quantity was successfully updated, false otherwise.
     */
    boolean updateQuantity(int itemId, int quantityClaimed);

    /**
     * Retrieves the quantity claimed for a specific item.
     *
     * @param itemId The ID of the item to check for claimed quantity.
     * @return The quantity claimed for the specified item.
     */
    int getQuantityClaimed(int itemId);
}