package Lab7;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class coursepreformance {
    private String courseId;
    private double averageCourseScore; 
    private Map<String, lessonstats> lessonStats; 

 
    public coursepreformance(String courseId, double averageCourseScore, List<lessonstats> statsList) {
        this.courseId = courseId;
        this.averageCourseScore = averageCourseScore;
        
        // Convert the list of LessonStats into a Map for easy lookup by lessonId
        this.lessonStats = statsList.stream()
                                     .collect(Collectors.toMap(lessonstats::getLessonId, stat -> stat));
    }

  
    public String getCourseId() { return courseId; }
    public double getAverageCourseScore() { return averageCourseScore; }
    public Map<String, lessonstats> getLessonStats() { return lessonStats; }
}