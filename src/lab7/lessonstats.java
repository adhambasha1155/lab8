package Lab7;

import java.util.List;
import java.util.ArrayList;

public class lessonstats {
    private String lessonId;
    private double averageQuizScore;
    private double completionRate; 
    private List<String> studentsCompleted; 

    // Constructor
    public lessonstats(String lessonId) {
        this.lessonId = lessonId;
        this.studentsCompleted = new ArrayList<>();
    }

    // Getters
    public String getLessonId() { return lessonId; }
    public double getAverageQuizScore() { return averageQuizScore; }
    public double getCompletionRate() { return completionRate; }
    public List<String> getStudentsCompleted() { return studentsCompleted; }

    // Setters (Used by Course.generatePerformanceStats to populate the data)
    public void setAverageQuizScore(double averageQuizScore) { this.averageQuizScore = averageQuizScore; }
    public void setCompletionRate(double completionRate) { this.completionRate = completionRate; }
    public void addCompletedStudent(String studentId) { this.studentsCompleted.add(studentId); }
}