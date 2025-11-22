package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class Student extends User
{
    private List<String> enrolledCourses;
    private List<String> progress; // store as "courseId:lessonId"
    private List<Result> quizResults;
    
    public Student(String userId, String username, String email, String passwordHash)
    {
        super(userId, username, email, passwordHash, "Student");
        enrolledCourses = new ArrayList<>();
        progress = new ArrayList<>();
        quizResults = new ArrayList<>();
    }
    public List<Result> getQuizResults() { return quizResults; }
    public void setQuizResults(List<Result> quizResults) { this.quizResults = quizResults; }
    
    // NEW: Get result object for a specific lesson
    public Result getResultForLesson(String lessonId) {
        for (Result r : quizResults) {
            if (r.getLessonId().equals(lessonId)) {
                return r;
            }
        }
        return null;
    }

    public List<String> getEnrolledCourses() { return enrolledCourses; }

    public void enrollCourse(String courseId) { enrolledCourses.add(courseId); }
    
    // Mark a lesson as completed
    public void markLessonCompleted(String courseId, String lessonId)
    {
        String entry = courseId + ":" + lessonId;
        if (!progress.contains(entry))
        {
            progress.add(entry);
        }
    }
        public boolean isLessonCompleted(String courseId, String lessonId)
    {
        String entry = courseId + ":" + lessonId;
        return progress.contains(entry);
    }
    // Get completed lessons for a specific courses
    public List<String> getCompletedLessons(String courseId)
    {
        List<String> lessons = new ArrayList<>();
        for (String entry : progress)
        {
            if (entry.startsWith(courseId + ":"))
            {
                lessons.add(entry.split(":")[1]);
            }
        }
        return lessons;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("userId", getUserId());
        obj.put("username", getUsername());
        obj.put("email", getEmail());
        obj.put("passwordHash", getPasswordHash());
        obj.put("role", getRole());
        obj.put("enrolledCourses", new JSONArray(enrolledCourses));
        obj.put("progress", new JSONArray(progress));
        JSONArray resultsArray = new JSONArray();
        for (Result r : quizResults) {
            resultsArray.put(r.toJson());
        }
        obj.put("quizResults", resultsArray);
        return obj;
    }
}
