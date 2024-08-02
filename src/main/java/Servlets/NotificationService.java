/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Servlets;

import DB.DBconnection;
import Models.Items;
import Models.Subscription;
import DAO.SubscriptionDAO;
import DAOImpl.SubscriptionDAOImpl;

import java.sql.SQLException;
import java.util.List;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Date;

/**
 * The `NotificationService` class handles sending notifications to users based on their subscriptions.
 * Notifications are sent via email when a new inventory item matches a user's preferences and location.
 *
 * <p>This class uses the `SubscriptionDAO` to retrieve all subscriptions and sends email notifications based on item details.
 * It supports sending notifications for surplus items (those nearing their expiration date) and can handle multiple modes of communication (though currently only email is implemented).</p>
 *
 * <p>The `sendEmail` method is used to send emails via an SMTP server.</p>
 *
 * @author YourName
 */
public class NotificationService {
    private SubscriptionDAO subscriptionDAO;

    /**
     * Initializes the `NotificationService` with a `SubscriptionDAO` instance.
     *
     * @throws SQLException If there is an error obtaining the database connection.
     */
    public NotificationService() throws SQLException {
        subscriptionDAO = new SubscriptionDAOImpl(DBconnection.getConnection()); // Initialize with your DAO implementation
    }

    /**
     * Sends notifications for a new inventory item based on user subscriptions.
     *
     * @param item The `Items` object containing details of the new inventory item.
     * @throws SQLException If there is an error retrieving subscriptions from the database.
     */
    public void sendNotifications(Items newItem) throws SQLException {
        // Retrieve all subscriptions
        List<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();

        // Get the current date and the date one week from now
        Date currentDate = new Date();
        Date oneWeekFromNow = new Date(currentDate.getTime() + (7L * 24 * 60 * 60 * 1000));
        boolean isSurplus = !newItem.getExpirationDate().before(currentDate) && !newItem.getExpirationDate().after(oneWeekFromNow);

        // Check if the new item is surplus
        if (isSurplus) {
            // Iterate over each subscription
            for (Subscription subscription : subscriptions) {
                // Check if the subscription's location and food preferences match the new item
                boolean locationMatches = true; // Placeholder for location check
                boolean foodPreferencesMatch = subscription.getFoodPreferences().contains(newItem.getItemName());

                // If the subscription's location and food preferences match the new item, send the notification
                if (locationMatches && foodPreferencesMatch) {
                    String recipient = subscription.getEmail();
                    String subject = "New Inventory Item Available";
                    String message = String.format(
                            "Dear User,\n\nWe have a new item available in your area that matches your preferences:\n\n" +
                                    "Item Name: %s\nQuantity: %d\nExpiration Date: %s\nRetailer Price: %.2f\n" +
                                    "Discount Price: %.2f\nLocation: %s\n\n" +
                                    "Best Regards,\nThe Team",
                            newItem.getItemName(),
                            newItem.getQuantity(),
                            newItem.getExpirationDate(),
                            newItem.getRetailerPrice(),
                            newItem.getDiscountPrice(),
                            "Location" // Placeholder for actual location
                    );

                    // Call the method to send the email
                    sendEmail(recipient, subject, message);
                }
            }
        }
    }

    /**
     * Sends an email notification using SMTP.
     *
     * @param recipient The email address of the recipient.
     * @param subject The subject of the email.
     * @param messageText The body of the email.
     */
    public static void sendEmail(String recipient, String subject, String messageText) {
        // SMTP server configuration
        String host = "smtp-mail.outlook.com";
        final String user = "dorriesmorries@outlook.com"; // Your email
        final String password = "X?4_Q:LQb2xuDGj"; // Your password

        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the default Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(user));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject(subject);

            // Set the actual message
            message.setText(messageText);

            // Send message
            Transport.send(message);
            System.out.println("Message sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
