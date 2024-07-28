package DAOImpl;

import DAO.InventoryDAO;
import DB.DBconnection;
import Models.Items;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    // SQL queries
    private static final String SELECT_INVERYID = "SELECT * FROM Inventory WHERE ItemID = ?";
    private static final String INSERT = "INSERT INTO Inventory (ItemName, Quantity, ExpirationDate, RetailerPrice, IsDonation, IsSale, DiscountPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Inventory SET ItemName = ?, Quantity = ?, ExpirationDate = ?, RetailerPrice = ?, IsDonation = ?, IsSale = ?, DiscountPrice = ? WHERE ItemID = ?";
    private static final String DELETE = "DELETE FROM Inventory WHERE ItemID = ?";
    private static final String SURPLUS_ITEM = "SELECT * FROM Inventory WHERE IsDonation = 1 OR IsSale = 1";
    private static final String DONATION_ITEM = "SELECT * FROM Inventory WHERE IsDonation = 1";
    private static final String UPDATE_QUANTITY = "UPDATE Inventory SET Quantity = ? WHERE ItemID = ?";
    private static final String DECREASE_QUANTITY = "UPDATE Inventory SET Quantity = Quantity - 1 WHERE ItemID = ? AND Quantity > 0";

    @Override
    public List<Items> getInventory() {
        return executeQuery(SELECT_INVERYID);
    }

    @Override
    public boolean addItem(Items item) {
        return executeUpdate(INSERT, ps -> {
            setItemPreparedStatement(ps, item);
        });
    }

    @Override
    public boolean updateItem(int itemId, String newItemName, int newQuantity, Date newExpirationDate, double newRetailerPrice, boolean newIsDonation, boolean newIsSale, double newDiscountPrice) {
        return executeUpdate(UPDATE, ps -> {
            ps.setString(1, newItemName);
            ps.setInt(2, newQuantity);
            ps.setDate(3, new java.sql.Date(newExpirationDate.getTime()));
            ps.setDouble(4, newRetailerPrice);
            ps.setBoolean(5, newIsDonation);
            ps.setBoolean(6, newIsSale);
            ps.setDouble(7, newDiscountPrice);
            ps.setInt(8, itemId);
        });
    }

    @Override
    public boolean deleteItem(int itemId) {
        return executeUpdate(DELETE, ps -> ps.setInt(1, itemId));
    }

    @Override
    public List<Items> getDonationItems() {
        return executeQuery(DONATION_ITEM);
    }

    @Override
    public boolean flagSurplusItem(int surplusItemId) {
        return executeUpdate(SURPLUS_ITEM, ps -> ps.setInt(1, surplusItemId));
    }

    @Override
    public boolean updateQuantity(int itemId, int newQuantity) {
        return executeUpdate(UPDATE_QUANTITY, ps -> {
            ps.setInt(1, newQuantity);
            ps.setInt(2, itemId);
        });
    }

    @Override
    public boolean decreaseQuantity(int itemId) {
        return executeUpdate(DECREASE_QUANTITY, ps -> ps.setInt(1, itemId));
    }

    private List<Items> executeQuery(String query) {
        List<Items> items = new ArrayList<>();
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                items.add(mapResultSetToItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private boolean executeUpdate(String query, PreparedStatementSetter pss) {
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            pss.setValues(preparedStatement);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Items mapResultSetToItem(ResultSet resultSet) throws SQLException {
        Items item = new Items();
        item.setItemId(resultSet.getInt("ItemID"));
        item.setItemName(resultSet.getString("ItemName"));
        item.setQuantity(resultSet.getInt("Quantity"));
        item.setExpirationDate(resultSet.getDate("ExpirationDate"));
        item.setRetailerPrice(resultSet.getDouble("RetailerPrice"));
        item.setDonation(resultSet.getBoolean("IsDonation"));
        item.setSale(resultSet.getBoolean("IsSale"));
        item.setDiscountPrice(resultSet.getDouble("DiscountPrice"));
        return item;
    }

    private void setItemPreparedStatement(PreparedStatement stat, Items item) throws SQLException {
        stat.setString(1, item.getItemName());
        stat.setInt(2, item.getQuantity());
        stat.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));
        stat.setDouble(4, item.getRetailerPrice());
        stat.setBoolean(5, item.isDonation());
        stat.setBoolean(6, item.isSale());
        stat.setDouble(7, item.getDiscountPrice());
    }

    @FunctionalInterface
    private interface PreparedStatementSetter {
        void setValues(PreparedStatement preparedStatement) throws SQLException;
    }
    
    @Override
    public boolean claimItem(int itemId){
        Connection connection = null;
        PreparedStatment prepareStatment = null;
    }
}