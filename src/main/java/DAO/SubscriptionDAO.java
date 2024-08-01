package DAO;

import Models.Subscription;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDAO {
    boolean addSubscription(Subscription subscription);
    List<Subscription> getAllSubscriptions() throws SQLException;
    ResultSet getSurplusItems() throws SQLException;
}
