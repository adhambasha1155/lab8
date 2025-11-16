package Lab7;

import org.json.JSONObject;

public class TestStudentMain {
    public static void main(String[] args) {
        // Create a Student
        Student student = new Student("S1", "Bob", "bob@example.com", "hashedPass123");

        // Enroll in courses
        student.enrollCourse("C101");
        student.enrollCourse("C102");

        // Mark some lessons as completed
        student.markLessonCompleted("C101", "L1");
        student.markLessonCompleted("C101", "L2");
        student.markLessonCompleted("C102", "L1");

        // Print enrolled courses
        System.out.println("Enrolled Courses: " + student.getEnrolledCourses());

        // Print completed lessons for C101
        System.out.println("Completed lessons in C101: " + student.getCompletedLessons("C101"));

        // Print JSON output
        JSONObject json = student.toJson();
        System.out.println("Student JSON:\n" + json.toString(2));
    }
}
