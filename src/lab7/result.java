package Lab7;

import java.util.ArrayList;
import java.util.List;
import lab7.Attempt;
import org.json.JSONArray;
import org.json.JSONObject;

public class Result {
    private String studentId;       // ID of the student
    private String lessonId;        // ID of the lesson/quiz
    private int maxRetries;         // Max allowed retries
    private List<Attempt> attempts; // Stores each attempt

    public Result(String studentId, String lessonId, int maxRetries) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.maxRetries = maxRetries;
        this.attempts = new ArrayList<>();
    }

    public Result() {
        this.attempts = new ArrayList<>();
    }

    // Add an attempt
    public void addAttempt(int score) {
        if (attempts.size() < maxRetries) {
            attempts.add(new Attempt(score));
        } else {
            System.out.println("Max retries reached for this student.");
        }
    }

    // Getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getLessonId() { return lessonId; }
    public void setLessonId(String lessonId) { this.lessonId = lessonId; }

    public int getMaxRetries() { return maxRetries; }
    public void setMaxRetries(int maxRetries) { this.maxRetries = maxRetries; }

    public List<Attempt> getAttempts() { return attempts; }

    // Get highest score
    public int getHighestScore() {
        int max = 0;
        for (Attempt a : attempts) {
            if (a.getScore() > max) max = a.getScore();
        }
        return max;
    }

    // Convert to JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("studentId", studentId);
        obj.put("lessonId", lessonId);
        obj.put("maxRetries", maxRetries);

        JSONArray arr = new JSONArray();
        for (Attempt a : attempts) {
            arr.put(a.toJson());
        }
        obj.put("attempts", arr);

        return obj;
    }


    public boolean isLessonCompleted(Lesson lesson) {
    Quiz q = lesson.getquiz();
    if (q == null) return false;

    int highestScore = getHighestScore();
    return highestScore >= lesson.getPassingScore();
}

}
