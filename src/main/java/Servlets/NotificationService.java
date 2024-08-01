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


public class NotificationService {
    private SubscriptionDAO subscriptionDAO;

    public NotificationService() throws SQLException {
        subscriptionDAO = new SubscriptionDAOImpl(DBconnection.getConnection()); // Initialize with your DAO implementation
    }

    public void TestsendNotifications(Items item) throws SQLException {
        List<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();

        for (Subscription subscription : subscriptions) {
            String message = "New inventory item added: " + item.getItemName() + " at a discount price of " + item.getDiscountPrice();
            if ("email".equals(subscription.getmoc())) {
                sendEmail(subscription.getEmail(), "New Inventory Item", message);
            } else if ("phone".equals(subscription.getmoc())) {
            }
        }
    }

    public void sendNotifications(Items newItem) throws SQLException {
        // Retrieve all subscriptions
        List<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions();

        // Get the current date and the date one week from now
        Date currentDate = new Date();
        Date oneWeekFromNow = new Date(currentDate.getTime() + (7L * 24 * 60 * 60 * 1000));
        boolean isSurplus = !newItem.getExpirationDate().before(currentDate) && !newItem.getExpirationDate().after(oneWeekFromNow);

        // check if new item is surplus or not
        if (isSurplus) {
            // Iterate over each subscription
            for (Subscription subscription : subscriptions) {
                // Check if the subscription's location and food preferences match the new item
                boolean locationMatches = true;
//            boolean locationMatches = subscription.getLocation().equalsIgnoreCase(newItem.getLocation());
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
                            "Location"
//                        newItem.getLocation()
                    );

                    // Call the method to send the email
                    sendEmail(recipient, subject, message);
                }
            }
        }
    }

    public static void sendEmail(String recipient, String subject, String messageText) {
        // SMTP server configuration
        String host = "smtp-mail.outlook.com";
        final String user = "dorriesmorries@outlook.com"; // Your email
        final String password = ""; // Your password

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

