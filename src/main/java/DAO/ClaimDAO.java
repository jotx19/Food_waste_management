package DAO;

import Models.Claims;

public interface ClaimDAO {
    boolean addClaim(Claims claim);
    boolean addOrUpdateClaim(Claims claim);
    boolean existsClaimForItem(int itemId);
    boolean updateQuantityClaimed(int itemId, int quantityClaimed);
    int getQuantityClaimed(int itemId);
}
