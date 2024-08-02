/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 * The `Passwordvalidator` class provides a method for validating passwords
 * based on a predefined pattern. This pattern ensures that passwords meet
 * certain criteria, including a minimum length and the presence of uppercase
 * letters, lowercase letters, digits, and special characters.
 */
public class Passwordvalidator {
    // Regular expression pattern for validating passwords.
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    /**
     * Validates the given password against the predefined pattern.
     *
     * @param password The password string to be validated.
     * @return true if the password is valid according to the pattern; false otherwise.
     */
    public static boolean validate(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}
