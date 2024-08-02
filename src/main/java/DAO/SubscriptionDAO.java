package DAO;

import Models.Subscription;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The SubscriptionDAO interface defines methods for managing subscription data
 * in the Food Waste Reduction Platform. It provides operations for adding subscriptions,
 * retrieving all subscriptions, and getting surplus items for notifications.
 */
public interface SubscriptionDAO {

    /**
     * Adds a new subscription to the system.
     *
     * @param subscription The Subscription object representing the new subscription to be added.
     * @return true if the subscription was successfully added, false otherwise.
     */
    boolean addSubscription(Subscription subscription);

    /**
     * Retrieves a list of all subscriptions in the system.
     *
     * @return A List of Subscription objects representing all subscriptions.
     * @throws SQLException if a database access error occurs.
     */
    List<Subscription> getAllSubscriptions() throws SQLException;

    /**
     * Retrieves a ResultSet of surplus items that may be relevant for subscriptions.
     * This method is typically used for generating notifications about available surplus items.
     *
     * @return A ResultSet containing information about surplus items.
     * @throws SQLException if a database access error occurs.
     */
    ResultSet getSurplusItems() throws SQLException;
}