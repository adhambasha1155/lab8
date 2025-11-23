package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
public class Course
{
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private List<Lesson1> lessons;
    private List<String> students; // List of enrolled student IDs
    private ApprovalStatus status;

    public Course(String courseId, String title, String description, String instructorId)
    {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
        this.status = ApprovalStatus.PENDING;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public void setLessons(List<Lesson1> lessons) {
        this.lessons = lessons;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    // Getters and setters
    public String getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getInstructorId() { return instructorId; }
    public List<Lesson1> getLessons() { return lessons; }
    public List<String> getStudents() { return students; }
    public ApprovalStatus getStatus() { return status; }
    public void setStatus(ApprovalStatus status) { this.status = status;}

    public void addLesson(Lesson1 lesson) { lessons.add(lesson); }
    public void enrollStudent(String studentId) { students.add(studentId); }
    
    public Lesson1 getLessonById(String lessonId)
    {
    for (Lesson1 lesson : lessons)
    {
        if (lesson.getLessonId().equals(lessonId))
        {
            return lesson;
        }
    }
    return null; // not found
}


    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("courseId", courseId);
        obj.put("title", title);
        obj.put("description", description);
        obj.put("instructorId", instructorId);
        obj.put("status", status.name());
        JSONArray lessonArray = new JSONArray();
        for (Lesson1 lesson : lessons) {
            lessonArray.put(lesson.toJson());
        }
        obj.put("lessons", lessonArray);

        obj.put("students", new JSONArray(students));
        return obj;
    }
  
public coursepreformance generatePerformanceStats(List<Student> allStudents) {
    
    // 1. Filter the entire student list down to only those enrolled in THIS course.
    List<Student> enrolledStudents = allStudents.stream()
        .filter(s -> getStudents().contains(s.getUserId()))
        .toList();
    
    final int totalEnrolledStudents = enrolledStudents.size();
    List<lessonstats> lessonStatsList = new ArrayList<>();

    // 2. Iterate over each lesson in the course to calculate stats per lesson.
    for (Lesson1 lesson : lessons) {
        String lessonId = lesson.getLessonId();
        lessonstats stats = new lessonstats(lessonId);
        
        int completedCount = 0;
        List<Integer> validHighestScores = new ArrayList<>();
        
        // Iterate over enrolled students ONCE per lesson to gather all required data.
        for (Student student : enrolledStudents) {
            
            // Check for lesson completion
            if (student.isLessonCompleted(courseId, lessonId)) {
                completedCount++;
                stats.addCompletedStudent(student.getUserId());
            }

            // Collect the student's best score for the quiz
            Result1 result = student.getResultForLesson(lessonId);
            if (result != null && !result.getAttempts().isEmpty()) {
                validHighestScores.add(result.getHighestScore());
            }
        }
        
        // Calculate Completion Rate
        double completionRate = (totalEnrolledStudents == 0) ? 0.0 : ((double) completedCount / totalEnrolledStudents) * 100;
        stats.setCompletionRate(completionRate);

        // Calculate Average Quiz Score using streams for simplicity
        double avgLessonScore = validHighestScores.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
        
        stats.setAverageQuizScore(avgLessonScore);
        
        lessonStatsList.add(stats);
    }

    // 3. Calculate Final Course Average Score from the lesson statistics we just computed.
    double avgCourseScore = lessonStatsList.stream()
        .mapToDouble(lessonstats::getAverageQuizScore)
        .average()
        .orElse(0.0);

    // 4. Return the aggregated insights object.
    return new coursepreformance(courseId, avgCourseScore, lessonStatsList);
}
}