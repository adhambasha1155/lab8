package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Instructor extends User 
{

   
    private List<String> createdCourses;

    public Instructor(String userId, String username, String email, String passwordHash)
    {
        super(userId, username, email, passwordHash, "Instructor");
        createdCourses = new ArrayList<>();
    }

    public List<String> getCreatedCourses() { return createdCourses; }

    public void addCourse(String courseId) { createdCourses.add(courseId); }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("userId", getUserId());
        obj.put("username", getUsername());
        obj.put("email", getEmail());
        obj.put("passwordHash", getPasswordHash());
        obj.put("role", getRole());
        obj.put("createdCourses", new JSONArray(createdCourses));
        return obj;
    }
    
}
