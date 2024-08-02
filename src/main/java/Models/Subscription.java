/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 * The `Subscription` class represents a subscription in the system.
 * It encapsulates details related to a user's subscription, including their
 * preferences and contact information.
 */
public class Subscription {
    private int subscriptionID;
    private int userID;
    private String email;
    private String moc;
    private String location;
    private String foodPreferences;

    /**
     * Default constructor for the `Subscription` class.
     */
    public Subscription() {
    }

    /**
     * Gets the unique identifier for the subscription.
     *
     * @return The subscription ID.
     */
    public int getSubscriptionID() {
        return subscriptionID;
    }

    /**
     * Sets the unique identifier for the subscription.
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
     * Gets the email address associated with the subscription.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with the subscription.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the method of communication for the subscription.
     *
     * @return The method of communication (moc).
     */
    public String getmoc() {
        return moc;
    }

    /**
     * Sets the method of communication for the subscription.
     *
     * @param communicationMethod The method of communication to set.
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
     * Gets the food preferences specified in the subscription.
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
