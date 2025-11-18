package Lab7;

import java.io.*;
import java.util.*;

public class JsonDatabaseManager {

    private final String USERS_FILE = "users.json";
    private final String COURSES_FILE = "courses.json";

    // ===================== Load Users =====================
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try {
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write("[]");
                fw.close();
            }

            // Read the file content
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            String content = sb.toString().trim();
            if (content.equals("[]") || content.isEmpty()) return users;

            // Parse JSON
            org.json.JSONArray arr = new org.json.JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                org.json.JSONObject obj = arr.getJSONObject(i);
                String userId = obj.getString("userId");
                String username = obj.getString("username");
                String email = obj.getString("email");
                String passwordHash = obj.getString("passwordHash");
                String role = obj.getString("role");

                if (role.equalsIgnoreCase("Student")) {
                    Student s = new Student(userId, username, email, passwordHash);

                    // Load enrolled courses
                    org.json.JSONArray enrolledArr = obj.optJSONArray("enrolledCourses");
                    if (enrolledArr != null) {
                        for (int j = 0; j < enrolledArr.length(); j++) {
                            s.enrollCourse(enrolledArr.getString(j));
                        }
                    }

                    // Load progress
                    org.json.JSONArray progressArr = obj.optJSONArray("progress");
                    if (progressArr != null) {
                        for (int j = 0; j < progressArr.length(); j++) {
                            String entry = progressArr.getString(j);
                            String[] parts = entry.split(":");
                            if (parts.length == 2) s.markLessonCompleted(parts[0], parts[1]);
                        }
                    }

                    users.add(s);

                } else if (role.equalsIgnoreCase("Instructor")) {
                    Instructor ins = new Instructor(userId, username, email, passwordHash);

                    // Load created courses
                    org.json.JSONArray createdArr = obj.optJSONArray("createdCourses");
                    if (createdArr != null) {
                        for (int j = 0; j < createdArr.length(); j++) {
                            ins.addCourse(createdArr.getString(j));
                        }
                    }

                    users.add(ins);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }


    // ===================== Save Users =====================
    public void saveUsers(List<User> users) {
        try {
            org.json.JSONArray arr = new org.json.JSONArray();

            for (User u : users) {
                arr.put(u.toJson()); // toJson() now returns JSONObject
            }

            FileWriter fw = new FileWriter(USERS_FILE);
            fw.write(arr.toString(2)); // pretty print with indentation
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ===================== Load Courses =====================
    public List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();

        try {
            File file = new File(COURSES_FILE);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write("[]");
                fw.close();
            }

            // Read the file content
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            String content = sb.toString().trim();
            if (content.equals("[]") || content.isEmpty()) return courses;

            // Parse JSON
            org.json.JSONArray arr = new org.json.JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                org.json.JSONObject obj = arr.getJSONObject(i);

                String courseId = obj.getString("courseId");
                String title = obj.getString("title");
                String description = obj.getString("description");
                String instructorId = obj.getString("instructorId");

                Course course = new Course(courseId, title, description, instructorId);

                // Load lessons
                org.json.JSONArray lessonsArr = obj.optJSONArray("lessons");
                if (lessonsArr != null) {
                    for (int j = 0; j < lessonsArr.length(); j++) {
                        org.json.JSONObject lObj = lessonsArr.getJSONObject(j);
                        String lessonId = lObj.getString("lessonId");
                        String lessonTitle = lObj.getString("title");
                        String lessonContent = lObj.getString("content");

                        Lesson lesson = new Lesson(lessonId, lessonTitle, lessonContent);

                        // Load resources
                        org.json.JSONArray resArr = lObj.optJSONArray("resources");
                        if (resArr != null) {
                            for (int k = 0; k < resArr.length(); k++) {
                                lesson.addResource(resArr.getString(k));
                            }
                        }

                        course.addLesson(lesson);
                    }
                }

                // Load students
                org.json.JSONArray studentsArr = obj.optJSONArray("students");
                if (studentsArr != null) {
                    for (int j = 0; j < studentsArr.length(); j++) {
                        course.enrollStudent(studentsArr.getString(j));
                    }
                }

                courses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }


    // ===================== Save Courses =====================
    public void saveCourses(List<Course> courses) {
        try {
            org.json.JSONArray arr = new org.json.JSONArray();

            for (Course course : courses) {
                arr.put(course.toJson()); // toJson() now returns JSONObject
            }

            FileWriter fw = new FileWriter(COURSES_FILE);
            fw.write(arr.toString(2)); // pretty print with indentation
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
