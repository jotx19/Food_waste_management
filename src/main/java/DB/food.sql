/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  14375
 * Created: Jul. 19, 2024
 */

DROP DATABASE IF EXISTS fwrp;
CREATE DATABASE fwrp;
USE fwrp;

-- Table for Users
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    UserType ENUM('retailer', 'consumer', 'charity') NOT NULL
);

-- Table for Inventory
CREATE TABLE Inventory (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,
    ItemName VARCHAR(255) NOT NULL,
    Quantity INT NOT NULL,
    ExpirationDate DATE,
    RetailerPrice DOUBLE,
    IsDonation BOOLEAN,
    IsSale BOOLEAN,
    DiscountPrice DOUBLE
);

-- Table for Claims
CREATE TABLE Claims (
    ClaimID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT,
    ClaimDate DATE,
    QuantityClaimed INT,
    FOREIGN KEY (ItemID) REFERENCES Inventory(ItemID)
);

-- Table for Purchases
CREATE TABLE Purchases (
    PurchaseID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT,
    PurchaseDate DATE,
    QuantityClaimed INT,
    FOREIGN KEY (ItemID) REFERENCES Inventory(ItemID)
);

-- Table for Feedback
CREATE TABLE Feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    ItemID INT NOT NULL,
    feedback_text TEXT NOT NULL,
    feedback_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rating INT CHECK (rating <= 5),
    FOREIGN KEY (ItemID) REFERENCES Inventory(ItemID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Table for Subscriptions
CREATE TABLE Subscriptions (
    SubscriptionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    CommunicationMethod ENUM('email', 'phone') NOT NULL,
    Email VARCHAR(255),
    Location VARCHAR(255),
    FoodPreferences VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Select Queries
SELECT * FROM Users;
SELECT * FROM Inventory;
SELECT * FROM Claims;
SELECT * FROM Purchases;
SELECT * FROM Feedback;

