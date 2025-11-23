package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import lab7.Certificate;

public class Student extends User {

    private List<String> enrolledCourses;
    private List<String> progress; // store as "courseId:lessonId"
    private List<Result1> quizResults;
    private List<Certificate> certificates;

    public Student(String userId, String username, String email, String passwordHash) {
        super(userId, username, email, passwordHash, "Student");
        enrolledCourses = new ArrayList<>();
        progress = new ArrayList<>();
        quizResults = new ArrayList<>();
        certificates = new ArrayList<>();
    }

    public List<Result1> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(List<Result1> quizResults) {
        this.quizResults = quizResults;
    }

    // NEW: Get result object for a specific lesson
    public Result1 getResultForLesson(String lessonId) {
        for (Result1 r : quizResults) {
            if (r.getLessonId().equals(lessonId)) {
                return r;
            }
        }
        return null;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void addCertificate(Certificate c) {
        certificates.add(c);
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void enrollCourse(String courseId) {
        enrolledCourses.add(courseId);
    }

    // Mark a lesson as completed
    public void markLessonCompleted(String courseId, String lessonId) {
        String entry = courseId + ":" + lessonId;
        if (!progress.contains(entry)) {
            progress.add(entry);
        }
    }

    public boolean isLessonCompleted(String courseId, String lessonId) {
        String entry = courseId + ":" + lessonId;
        return progress.contains(entry);
    }

  
    public List<String> getCompletedLessons(String courseId) {
        List<String> lessons = new ArrayList<>();
        for (String entry : progress) {
            if (entry.startsWith(courseId + ":")) {
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
        for (Result1 r : quizResults) {
            resultsArray.put(r.toJson());
        }
        obj.put("quizResults", resultsArray);
        JSONArray certArray = new JSONArray();
        for (Certificate c : certificates) {
            certArray.put(c.toJson());
        }
        obj.put("certificates", certArray);

        return obj;
    }
    public Result1 addQuizResultEntry(String lessonId, int maxRetries) {
    Result1 existingResult = getResultForLesson(lessonId);
    if (existingResult == null) {
        Result1 newResult = new Result1(getUserId(), lessonId, maxRetries);
        this.quizResults.add(newResult);
        return newResult;
    }
    return existingResult;
}
    public boolean addAttemptToResult(String lessonId, int score) {
    Result1 result = getResultForLesson(lessonId);
    
    if (result == null) {
        System.err.println("Error: Quiz Result entry not found for lesson " + lessonId);
        return false;
    }

    if (!result.hasReachedMaxRetries()) {
        result.loadAttempt(score);
        return true;
    } else {
        System.out.println("Max retries reached for lesson " + lessonId);
        return false;
    }}
}
