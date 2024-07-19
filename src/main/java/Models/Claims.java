package Models;
import java.util.Date;

public class Claims {
    private int claimId;
    private int itemId;
    private Date claimDate;
    private int quantityClaimed;

    public Claims(int itemId, Date claimDate, int quantityClaimed) {
        this.itemId = itemId;
        this.claimDate = claimDate;
        this.quantityClaimed = quantityClaimed;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public int getQuantityClaimed() {
        return quantityClaimed;
    }

    public void setQuantityClaimed(int quantityClaimed) {
        this.quantityClaimed = quantityClaimed;
    }
}
