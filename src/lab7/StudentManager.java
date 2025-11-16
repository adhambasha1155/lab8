package Lab7;

import java.util.*;
import java.util.regex.Pattern;

public class StudentManager {

    private List<Student> students;   // loaded from JSON
    private List<Course> courses;     // loaded from JSON
    private JsonDatabaseManager jsonDB;

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public JsonDatabaseManager getJsonDB() {
        return jsonDB;
    }

    // ===================== Constructor =====================
    public StudentManager() {
        jsonDB = new JsonDatabaseManager();
        students = new ArrayList<>();
        courses = new ArrayList<>();

        // Load existing users
        List<User> allUsers = jsonDB.loadUsers();
        for (User u : allUsers) {
            if (u instanceof Student) students.add((Student) u);
        }

        // Load existing courses
        courses = jsonDB.loadCourses();
    }

    // ===================== Save Changes =====================
    public void saveAll() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(students); // instructors will be saved separately
        jsonDB.saveUsers(allUsers);
        jsonDB.saveCourses(courses);
    }

    // ===================== Browse Courses =====================
    public List<Course> getAllCourses() {
        return courses;
    }

    // ===================== Enroll in Course =====================
    public boolean enrollCourse(Student student, String courseId) {
        Course course = getCourseById(courseId);
        if (course == null) return false;

        if (!student.getEnrolledCourses().contains(courseId)) {
            student.enrollCourse(courseId);
            course.enrollStudent(student.getUserId());
            saveAll();
            return true;
        }
        return false; // already enrolled
    }

    // ===================== Access Lessons =====================
    public List<Lesson> getLessonsForCourse(String courseId) {
        Course course = getCourseById(courseId);
        if (course != null) return course.getLessons();
        return new ArrayList<>();
    }

    // ===================== Mark Lesson Completed =====================
    public boolean markLessonCompleted(Student student, String courseId, String lessonId) {
        Course course = getCourseById(courseId);
        if (course == null) return false;

        Lesson lesson = course.getLessonById(lessonId);
        if (lesson == null) return false;

        student.markLessonCompleted(courseId, lessonId);
        saveAll();
        return true;
    }

    // ===================== Get Completed Lessons =====================
    // Returns lesson titles instead of IDs
    public List<String> getCompletedLessons(Student student, String courseId) {
        List<String> completedIds = student.getCompletedLessons(courseId);
        List<String> titles = new ArrayList<>();

        Course course = getCourseById(courseId);
        if (course == null) return titles;

        for (String lessonId : completedIds) {
            Lesson lesson = course.getLessonById(lessonId);
            if (lesson != null) titles.add(lesson.getTitle());
        }

        return titles;
    }

    // ===================== Helper: Get Course by ID =====================
    private Course getCourseById(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) return c;
        }
        return null;
    }

    // ===================== Get Student by ID =====================
    public Student getStudentById(String studentId) {
        for (Student s : students) {
            if (s.getUserId().equals(studentId)) return s;
        }
        return null;
    }
        public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
