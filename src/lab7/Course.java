package Lab7;

import java.util.ArrayList;
import java.util.List;

public class Course
{
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private List<Lesson> lessons;
    private List<String> students; // List of enrolled student IDs

    public Course(String courseId, String title, String description, String instructorId)
    {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
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

    public void setLessons(List<Lesson> lessons) {
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
    public List<Lesson> getLessons() { return lessons; }
    public List<String> getStudents() { return students; }

    public void addLesson(Lesson lesson) { lessons.add(lesson); }
    public void enrollStudent(String studentId) { students.add(studentId); }
    
    public Lesson getLessonById(String lessonId)
    {
    for (Lesson lesson : lessons)
    {
        if (lesson.getLessonId().equals(lessonId))
        {
            return lesson;
        }
    }
    return null; // not found
}


    public String toJson()
    {
        return String.format(
            "{\"courseId\":\"%s\",\"title\":\"%s\",\"description\":\"%s\",\"instructorId\":\"%s\",\"lessons\":%s,\"students\":%s}",
            courseId, title, description, instructorId, lessons.toString(), students.toString()
        );
    }
}
