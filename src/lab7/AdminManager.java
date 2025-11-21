package Lab7;

import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    
    // ONLY need the JsonDatabaseManager instance
    private final JsonDatabaseManager jsonDB; 

    // ===================== Constructor =====================
    // Now only responsible for initializing the database access object
    public AdminManager() {
        this.jsonDB = new JsonDatabaseManager(); 
        // No need to load 'courses' here, as it's loaded in every method call.
    }

    // ===================== Retrieve Pending Courses =====================
    public List<Course> getPendingCourses() {
        // Loads fresh data directly from the JSON file
        List<Course> allCourses = jsonDB.loadCourses(); 

        List<Course> pending = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getStatus() == ApprovalStatus.PENDING) {
                pending.add(course);
            }
        }
        return pending;
    }

    // ===================== Set Course Approval Status =====================
    public boolean setCourseStatus(String courseId, ApprovalStatus newStatus) {
        // Loads fresh data before modifying
        List<Course> allCourses = jsonDB.loadCourses();

        Course courseToUpdate = null;
        for (Course course : allCourses) {
            if (course.getCourseId().equals(courseId)) {
                courseToUpdate = course;
                break;
            }
        }

        if (courseToUpdate != null) {
            courseToUpdate.setStatus(newStatus);
            
            // Saves the entire updated list back to the file
            jsonDB.saveCourses(allCourses);
            
            return true;
        }
        return false;
    }
    
    // ===================== Helper: Get Course by ID =====================
    public Course getCourseById(String courseId) {
        // Loads fresh data from file
        List<Course> allCourses = jsonDB.loadCourses();

        for (Course course : allCourses) {
            if (course.getCourseId().equals(courseId)) return course;
        }
        return null;
    }
}