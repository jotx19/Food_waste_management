package DAO;

import Models.Claims;

public interface ClaimDAO {
    boolean addClaim(Claims claim);
    boolean addOrUpdate(Claims claim);
    boolean existingClaim(int itemId);
    boolean updateQuantity(int itemId, int quantityClaimed);
    int getQuantityClaimed(int itemId);
}
