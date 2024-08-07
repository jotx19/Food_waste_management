package DAOImpl;

import DAO.SubscriptionDAO;
import Models.Subscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The SubscriptionDAOImpl class implements the SubscriptionDAO interface,
 * providing concrete methods for managing subscription data within the Food Waste Reduction Platform.
 * This class interacts with the database to perform CRUD operations on subscription records.
 */
public class SubscriptionDAOImpl implements SubscriptionDAO {
    private final Connection connection;

    /**
     * Constructs a new SubscriptionDAOImpl object with the given database connection.
     *
     * @param connection The Connection object used for interacting with the database.
     */
    public SubscriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new subscription to the database.
     *
     * @param subscription The Subscription object representing the new subscription to be added.
     * @return true if the subscription was successfully added, false otherwise.
     */
    @Override
    public boolean addSubscription(Subscription subscription) {
        String query = "INSERT INTO Subscriptions (UserID, CommunicationMethod, Location, FoodPreferences, Email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, subscription.getUserID());
            statement.setString(2, subscription.getmoc()); // Ensure method name matches the one in Subscription class
            statement.setString(3, subscription.getLocation());
            statement.setString(4, subscription.getFoodPreferences());
            statement.setString(5, subscription.getEmail()); // Added line for email
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a list of all subscriptions from the database.
     *
     * @return A List of Subscription objects representing all subscriptions.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM Subscriptions";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Subscription subscription = new Subscription();
                subscription.setSubscriptionID(resultSet.getInt("SubscriptionID"));
                subscription.setUserID(resultSet.getInt("UserID"));
                subscription.setmoc(resultSet.getString("CommunicationMethod"));
                subscription.setLocation(resultSet.getString("Location"));
                subscription.setFoodPreferences(resultSet.getString("FoodPreferences"));
                subscription.setEmail(resultSet.getString("Email"));
                subscriptions.add(subscription);
            }
        }
        return subscriptions;
    }

    /**
     * Retrieves a ResultSet of surplus items that may be relevant for subscriptions.
     * This method is typically used for generating notifications about available surplus items.
     *
     * @return A ResultSet containing information about surplus items.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public ResultSet getSurplusItems() throws SQLException {
        String query = "SELECT * FROM Inventory WHERE Quantity > 0";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
}
