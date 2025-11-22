package Lab7;

import java.util.*;

public class StudentManager {

    private List<Student> students;   // loaded from JSON
    private List<Course> courses;     // loaded from JSON
    private JsonDatabaseManager jsonDB;

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
       List<Course> approvedCourses = new ArrayList<>();
    for (Course course : courses) { 
        // NEW FILTER: Only return APPROVED courses
        if (course.getStatus() == ApprovalStatus.APPROVED) { 
            approvedCourses.add(course);
        }
    }
    return approvedCourses;
        
    }

    // ===================== Enroll in Course =====================
    public boolean enrollCourse(Student student, String courseId) {
    Course course = getCourseById(courseId);
    if (course == null) return false;

    if (course.getStatus() != ApprovalStatus.APPROVED) {
        return false; // Enrollment fails if not approved
    }
    
    
    if (!student.getEnrolledCourses().contains(courseId)) {
        course.enrollStudent(student.getUserId());
        student.enrollCourse(courseId);
        saveAll();
        return true;
    }
    return false;
}

    // ===================== Access Lessons =====================
    public List<Lesson1> getLessonsForCourse(String courseId) {
        Course course = getCourseById(courseId);
        if (course != null) return course.getLessons();
        return new ArrayList<>();
    }

   public Result1 submitQuiz(Student student, String courseId, String lessonId, List<Integer> studentAnswers) {
        Course course = getCourseById(courseId);
        if (course == null) return null;

        Lesson1 lesson = course.getLessonById(lessonId);
        Quiz1 quiz = (lesson != null) ? lesson.getQuiz() : null;
        if (quiz == null) return null; // No quiz found for this lesson

        // Find or create the Result1 object for this student and lesson
        Result1 result = student.getResultForLesson(lessonId);
        int maxRetries = 3; // Default max retries (e.g.)

        if (result == null) {
            result = new Result1(student.getUserId(), lessonId, maxRetries);
            student.getQuizResults().add(result);
        } else if (result.hasReachedMaxRetries()) {
            return null; // Cannot submit, max retries reached
        }

        // Calculate Score
        int score = quiz.calculateScore(studentAnswers);

        // Add the attempt
        result.addAttempt(score);
        
        // Save student/result changes
        saveAll(); 

        return result;
    }


    // ===================== Mark Lesson Completed (UPDATED LOGIC) =====================
    /**
     * Marks a lesson as completed only if there is no quiz, or if a quiz exists 
     * and the student has scored >= 50.
     */
    public boolean markLessonCompleted(Student student, String courseId, String lessonId) {
        Course course = getCourseById(courseId);
        if (course == null) return false;

        Lesson1 lesson = course.getLessonById(lessonId);
        if (lesson == null) return false;

        // --- NEW QUIZ CHECK LOGIC ---
        if (lesson.getQuiz() != null) {
            Result1 result = student.getResultForLesson(lessonId);
            
            // Check if student has taken the quiz and scored over 50
            if (result == null || result.getHighestScore() < 50) {
                // Lesson completion failed: Quiz not taken or score too low
                return false; 
            }
        }
        // --- END NEW QUIZ CHECK LOGIC ---
        
        // If the code reaches here, either:
        // 1. There was no quiz.
        // 2. There was a quiz, and the highest score was >= 50.
        
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
            Lesson1 lesson = course.getLessonById(lessonId);
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
}
