/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab7;

/**
 *
 * @author adham
 */

import org.json.JSONObject;

public class Admin extends User
{
    public Admin(String userId, String username, String email, String passwordHash) {
        super(userId, username, email, passwordHash, "Admin");
    }

    // Admin has no specific fields (like createdCourses or progress), 
    // so the toJson() method only serializes the base User fields.
    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("userId", getUserId());
        obj.put("username", getUsername());
        obj.put("email", getEmail());
        obj.put("passwordHash", getPasswordHash());
        obj.put("role", getRole());
        return obj;
    }
}
