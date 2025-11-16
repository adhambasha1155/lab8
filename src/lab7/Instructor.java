package Lab7;

import java.util.ArrayList;
import java.util.List;

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
    public String toJson()
    {
        return String.format(
            "{\"userId\":\"%s\",\"username\":\"%s\",\"email\":\"%s\",\"passwordHash\":\"%s\",\"role\":\"%s\",\"createdCourses\":%s}",
            getUserId(), getUsername(), getEmail(), getPasswordHash(), getRole(), getCreatedCourses().toString()
        );
    }
}
