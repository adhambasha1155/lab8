package Lab7;

import org.json.JSONObject;

public abstract class User {
    private String userId;
    private String username;
    private String email;
    private String passwordHash; // Store hashed password
    private String role; // "Student" or "Instructor"

    public User(String userId, String username, String email, String passwordHash, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }

    // Abstract method for JSON representation (returns JSONObject)
    public abstract JSONObject toJson();
}
