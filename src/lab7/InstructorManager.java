package Lab7;

import java.util.List;
import java.util.ArrayList;

public class InstructorManager {

    private List<Course> courses; // all courses in the system
    private JsonDatabaseManager jsonDB; // handles courses.json

    // ===================== Constructor =====================
    public InstructorManager() {
        jsonDB = new JsonDatabaseManager();       // initialize JSON manager
        courses = jsonDB.loadCourses();           // load existing courses from courses.json
    }
    public Course getCourse(String courseID){
        for (Course course : courses){
            if(course.getCourseId().equals(courseID)){
                return course;
            }
        }
        return null;
    }
    public boolean setLessonQuiz(Course course, Lesson1 lesson, Quiz1 quiz) {
        if (course == null || lesson == null || quiz == null) {
            return false;
        }

        lesson.setQuiz(quiz);

        // Save changes to JSON (Quiz is now part of the Course structure)
        jsonDB.saveCourses(courses);

        return true;
    }

    // ===================== Create Course =====================
    public Course createCourse(Instructor instructor, String courseId, String title, String description) {

        if (getCourseById(courseId) != null) {
            return null;
        }

        Course course = new Course(courseId, title, description, instructor.getUserId());
        courses.add(course);
        instructor.addCourse(courseId);

        // 3. Save changes to JSON
        // Note: This only saves the courses.json file.
        // The calling GUI method (in createFrame.java) must save the updated
        // instructor object to users.json using accountManager.saveUser().
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
    public Lesson1 addLessonToCourse(Course course, String lessonTitle, String content) {
        if (course == null) {
            return null;
        }
        String lessonId = "L" + (course.getLessons().size() + 1);
        Lesson1 lesson = new Lesson1(lessonId, lessonTitle, content);
        course.addLesson(lesson);

        // Save changes to JSON
        jsonDB.saveCourses(courses);

        return lesson;
    }

    public Lesson1 createLesson(Course course, String lessonId, String lessonTitle, String content) {
        if (course == null) {
            return null;
        }

        // 1. Create Lesson using the user-provided lessonId
        Lesson1 lesson = new Lesson1(lessonId, lessonTitle, content);

        // 2. Add the lesson to the course's internal list
        course.addLesson(lesson);

        // 3. Save changes to JSON (persisting the course update)
        jsonDB.saveCourses(courses);

        return lesson;
    }

    // ===================== Edit Lesson =====================
    public boolean editLesson(Lesson1 lesson, String newTitle, String newContent) {
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
    public boolean deleteLesson(Course course, Lesson1 lesson) {
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

    private String generateNextCourseId() {
        int maxNum = 0;
        // Iterate over the in-memory list of courses
        for (Course course : courses) {
            String id = course.getCourseId();
            // Check if ID starts with 'C' and is followed by a number
            if (id != null && id.toUpperCase().startsWith("C")) {
                try {
                    // Get the number part (e.g., "1" from "C1")
                    int currentNum = Integer.parseInt(id.substring(1));
                    if (currentNum > maxNum) {
                        maxNum = currentNum;
                    }
                } catch (NumberFormatException e) {
                    // Ignore courses with non-standard IDs (e.g., UUIDs or text)
                }
            }
        }
        return "C" + (maxNum + 1);
    }

// Helper to determine the next available Lesson ID for a given course (e.g., L3 after L1, L2)
    private String generateNextLessonId(Course course) {
        int maxNum = 0;
        for (Lesson1 lesson : course.getLessons()) {
            String id = lesson.getLessonId();
            // Check if ID starts with 'L' and is followed by a number
            if (id != null && id.toUpperCase().startsWith("L")) {
                try {
                    // Get the number part (e.g., "1" from "L1")
                    int currentNum = Integer.parseInt(id.substring(1));
                    if (currentNum > maxNum) {
                        maxNum = currentNum;
                    }
                } catch (NumberFormatException e) {
                    // Ignore lessons with non-standard IDs
                }
            }
        }
        return "L" + (maxNum + 1);
    }

}
