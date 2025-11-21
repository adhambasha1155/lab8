package Lab7;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAccountManager {

    private List<User> users;       // store all users (Students + Instructors)
    private User currentUser;       // currently logged-in user
    private JsonDatabaseManager jsonDB;


    public UserAccountManager() {
        users = new ArrayList<>();
        currentUser = null;
        jsonDB = new JsonDatabaseManager();
        users = jsonDB.loadUsers(); // load existing users from users.json
    }

    // ===================== SIGNUP =====================
    public User signup(String username, String email, String password, String role) {

        // 1. Validate required fields
        if (username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            role == null || role.isEmpty()) {
            return null;
        }

        // 2. Validate email format
        if (!isValidEmail(email)) {
            return null;
        }

        // 3. Check if email already exists
        if (getUserByEmail(email) != null) {
            return null;
        }

        // 4. Hash password
        String passwordHash = hashPassword(password);

        // 5. Create user based on role
        String userId = "U" + (users.size() + 1);
        User newUser;

        if (role.equalsIgnoreCase("Student")) {
            newUser = new Student(userId, username, email, passwordHash);
        } else if (role.equalsIgnoreCase("Instructor")) {
            newUser = new Instructor(userId, username, email, passwordHash);
        } else if (role.equalsIgnoreCase("Admin")) { 
        newUser = new Admin(userId, username, email, passwordHash);
        } else {
            return null; // invalid role
        }

        // 6. Save user
        users.add(newUser);

        // 7. Optional: automatically log in after signup
        currentUser = newUser;

        // save users to JSON
        jsonDB.saveUsers(users);

        return newUser;
    }

    // ===================== LOGIN =====================
    public User login(String email, String password) {

        // 1. Validate required fields
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        // 2. Validate email format
        if (!isValidEmail(email)) {
            return null;
        }

        // 3. Check if user exists
        User user = getUserByEmail(email);
        if (user == null) {
            return null; // email not found
        }

        // 4. Hash the provided password and compare
        String providedHash = hashPassword(password);
        if (!providedHash.equals(user.getPasswordHash())) {
            return null; // wrong password
        }
        

        // 5. Login successful → set currentUser and return
        currentUser = user;
        return user;
    }

    // ===================== LOGOUT =====================
    public void logout() {
        currentUser = null;
    }

    // ===================== CURRENT USER HELPERS =====================
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String getCurrentUserRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    // ===================== HELPER METHODS =====================
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
 // ===================== COURSE MANAGEMENT =====================

    
    public List<Course> getAllCourses() {
        return jsonDB.loadCourses();  // always load latest data from courses.json
    }
    
    public Course getCourseById(String courseId) {
        List<Course> courses = jsonDB.loadCourses();
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                return c;
            }
        }
        return null;
    }
    
    public List<User> getAllUsers() {
        return users; // users list already loaded in memory
    }
    
    public void saveAllUsers() {
        jsonDB.saveUsers(users);
    }
    
    public void saveAllCourses() {
        List<Course> courses = jsonDB.loadCourses(); 
        jsonDB.saveCourses(courses);
    }
    
    public void saveAllCourses(List<Course> courses) {
        jsonDB.saveCourses(courses);
    }

   
    //----------------------------------------------------------

    private User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
    
    public User loginByUsername(String username, String password) {

        // 1. Validate required fields
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        // 2. Check if user exists by username
        User user = getUserByUsername(username); // you need to implement this
        if (user == null) {
            return null; // username not found
        }

        // 3. Hash the provided password and compare
        String providedHash = hashPassword(password);
        if (!providedHash.equals(user.getPasswordHash())) {
            return null; // wrong password
        }

        // 4. Login successful → set currentUser and return
        currentUser = user;
        return user;
    }
    
    private User getUserByUsername(String username) {
        for (User user : users) { // assuming you have a users list
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public void saveUser(User user) {
    // 1. Find the index of the user in the in-memory list using their unique ID
    int index = -1;
    for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUserId().equals(user.getUserId())) {
            index = i;
            break;
        }
    }

    // 2. If found, replace the old object with the updated one
    if (index != -1) {
        users.set(index, user);
    } else {
        // This case should ideally not happen for a logged-in user
        System.err.println("User not found in UserAccountManager list for saving: " + user.getUserId());
        return; 
    }

    // 3. Save the entire updated list to users.json
    jsonDB.saveUsers(users);
}

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

}
