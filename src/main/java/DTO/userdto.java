package DTO;

/**
 * The `userdto` class represents a Data Transfer Object (DTO) for user details
 * in the Food Waste Reduction Platform. It encapsulates information about the user,
 * including their ID, name, email, password, and user type.
 */
public class userdto {
    private int userId;
    private String name;
    private String email;
    private String password;
    private Types userType;

    /**
     * Constructs a new `userdto` instance with the specified name, email, password, and user type.
     *
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param userType The type of user (e.g., admin, regular user).
     */
    public userdto(String name, String email, String password, Types userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Constructs a new `userdto` instance with the specified name, email, and user type.
     * This constructor does not require a password.
     *
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param userType The type of user (e.g., admin, regular user).
     */
    public userdto(String name, String email, Types userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    /**
     * Default constructor for the `userdto` class.
     * Initializes a new instance of the `userdto` class with default values.
     */
    public userdto() {
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user type.
     *
     * @return The type of the user.
     */
    public Types getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     *
     * @param userType The user type to set.
     */
    public void setUserType(Types userType) {
        this.userType = userType;
    }
}
