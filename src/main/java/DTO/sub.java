package DTO;

/**
 * The `sub` class represents a data transfer object for subscription details
 * in the Food Waste Reduction Platform. It encapsulates subscription information
 * including the subscription ID, user ID, email, communication method, location,
 * and food preferences.
 */
public class sub {
    private int subscriptionID;
    private int userID;
    private String email;
    private String moc;
    private String location;
    private String foodPreferences;

    /**
     * Default constructor for the `sub` class.
     * Initializes a new instance of the `sub` class with default values.
     */
    public sub() {
    }

    /**
     * Gets the subscription ID.
     *
     * @return The subscription ID.
     */
    public int getSubscriptionID() {
        return subscriptionID;
    }

    /**
     * Sets the subscription ID.
     *
     * @param subscriptionID The subscription ID to set.
     */
    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    /**
     * Gets the user ID associated with the subscription.
     *
     * @return The user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID associated with the subscription.
     *
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the email address of the user associated with the subscription.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user associated with the subscription.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the communication method for the subscription.
     *
     * @return The communication method.
     */
    public String getmoc() {
        return moc;
    }

    /**
     * Sets the communication method for the subscription.
     *
     * @param communicationMethod The communication method to set.
     */
    public void setmoc(String communicationMethod) {
        this.moc = communicationMethod;
    }

    /**
     * Gets the location associated with the subscription.
     *
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location associated with the subscription.
     *
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the food preferences for the subscription.
     *
     * @return The food preferences.
     */
    public String getFoodPreferences() {
        return foodPreferences;
    }

    /**
     * Sets the food preferences for the subscription.
     *
     * @param foodPreferences The food preferences to set.
     */
    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }
}
