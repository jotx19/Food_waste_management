package dto;

//import notificationSystem.NotificationService;

public class userdto {
    private int userId;
    private String name;
    private String email;
    private String password;
    private Types userType;
//    private boolean isSubscribe;
//    private NotificationMethod notificationMethod;

    // Constructor
    public userdto(String name, String email, String password, Types userType) {
        this.name = name;
        this.email = email;
        this.password=password;
        this.userType = userType;
        //this.isSubscribe=isSubscribe;
    }

    public userdto(String name, String email,Types userType) {
    	this.name = name;
        this.email = email;
        this.userType = userType;
	}

    public userdto() {
    	
    }

	// Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Types getUserType() {
        return userType;
    }

    public void setUserType(Types userType) {
        this.userType = userType;
    }
    
}
