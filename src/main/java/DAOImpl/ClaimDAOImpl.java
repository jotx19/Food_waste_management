package DAOImpl;

import DAO.ClaimDAO;
import Models.Claims;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ClaimDAOImpl implements ClaimDAO {
    private final Connection connection;

    public ClaimDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addClaim(Claims claim) {
        String query = "INSERT INTO Claims (ItemID, ClaimDate, QuantityClaimed) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, claim.getItemId());
            statement.setTimestamp(2, new Timestamp(claim.getClaimDate().getTime()));
            statement.setInt(3, claim.getQuantityClaimed());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addOrUpdateClaim(Claims claim) {
        if (existsClaimForItem(claim.getItemId())) {
            int currentQuantityClaimed = getQuantityClaimed(claim.getItemId());
            int newQuantityClaimed = currentQuantityClaimed + claim.getQuantityClaimed();
            return updateQuantityClaimed(claim.getItemId(), newQuantityClaimed);
        } else {
            return addClaim(claim);
        }
    }

    @Override
    public boolean existsClaimForItem(int itemId) {
        String query = "SELECT COUNT(*) AS count FROM Claims WHERE ItemID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, itemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateQuantityClaimed(int itemId, int quantityClaimed) {
        String query = "UPDATE Claims SET QuantityClaimed = ? WHERE ItemID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, quantityClaimed);
            preparedStatement.setInt(2, itemId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getQuantityClaimed(int itemId) {
        int quantityClaimed = 0;
        String query = "SELECT QuantityClaimed FROM Claims WHERE ItemID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, itemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    quantityClaimed = resultSet.getInt("QuantityClaimed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantityClaimed;
    }
}
