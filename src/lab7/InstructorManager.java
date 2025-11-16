package Lab7;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class InstructorManager {

    private List<Course> courses; // all courses in the system
    private JsonDatabaseManager jsonDB; // handles courses.json

    // ===================== Constructor =====================
    public InstructorManager() {
        jsonDB = new JsonDatabaseManager();       // initialize JSON manager
        courses = jsonDB.loadCourses();           // load existing courses from courses.json
    }

    // ===================== Create Course =====================
    public Course createCourse(Instructor instructor, String title, String description) {
        String courseId = "C" + (courses.size() + 1); // simple id
        Course course = new Course(courseId, title, description, instructor.getUserId());
        courses.add(course);                          // add to main list
        instructor.addCourse(courseId);               // add to instructor's createdCourses

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return course;
    }

    // ===================== Edit Course =====================
    public boolean editCourse(Course course, String newTitle, String newDescription) {
        if (course == null) {
            return false;
        }
        course.setTitle(newTitle);
        course.setDescription(newDescription);

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return true;
    }

    // ===================== Delete Course =====================
    public boolean deleteCourse(Instructor instructor, Course course) {
        if (course == null) {
            return false;
        }
        courses.remove(course);
        instructor.getCreatedCourses().remove(course.getCourseId());

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return true;
    }

    // ===================== Add Lesson =====================
    public Lesson addLessonToCourse(Course course, String lessonTitle, String content) {
        if (course == null) {
            return null;
        }
        String lessonId = "L" + (course.getLessons().size() + 1);
        Lesson lesson = new Lesson(lessonId, lessonTitle, content);
        course.addLesson(lesson);

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return lesson;
    }

    // ===================== Edit Lesson =====================
    public boolean editLesson(Lesson lesson, String newTitle, String newContent) {
        if (lesson == null) {
            return false;
        }
        lesson.setTitle(newTitle);
        lesson.setContent(newContent);

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return true;
    }

    // ===================== Delete Lesson =====================
    public boolean deleteLesson(Course course, Lesson lesson) {
        if (course == null || lesson == null) {
            return false;
        }
        boolean removed = course.getLessons().remove(lesson);

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return removed;
    }

    // ===================== View Enrolled Students =====================
    public List<String> getEnrolledStudents(Course course) {
        if (course == null) {
            return new ArrayList<>();
        }
        return course.getStudents(); // returns list of student IDs
    }

    // ===================== Get Course by ID =====================
    public Course getCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    // ===================== Get All Courses =====================
    public List<Course> getAllCourses() {
        return courses;
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    public List<Instructor> getinstructor() {
        if (jsonDB.getInstructors()!= null) {
            return null;
        }
        return jsonDB.getInstructors();
    }

}
