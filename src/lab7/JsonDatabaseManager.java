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

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            String content = sb.toString().trim();
            if (content.equals("[]") || content.isEmpty()) return users;

            content = content.substring(1, content.length() - 1);
            String[] userEntries = content.split("\\},\\{");

            for (int i = 0; i < userEntries.length; i++) {
                String u = userEntries[i];
                if (!u.startsWith("{")) u = "{" + u;
                if (!u.endsWith("}")) u = u + "}";

                String userId = getValue(u, "userId");
                String username = getValue(u, "username");
                String email = getValue(u, "email");
                String passwordHash = getValue(u, "passwordHash");
                String role = getValue(u, "role");

                if (role.equalsIgnoreCase("Student")) {
                    Student s = new Student(userId, username, email, passwordHash);
                    String enrolledCourses = getArray(u, "enrolledCourses");
                    for (String c : enrolledCourses.split(",")) {
                        if (!c.trim().isEmpty()) s.enrollCourse(c.trim());
                    }
                    users.add(s);
                } else if (role.equalsIgnoreCase("Instructor")) {
                    Instructor ins = new Instructor(userId, username, email, passwordHash);
                    String createdCourses = getArray(u, "createdCourses");
                    for (String c : createdCourses.split(",")) {
                        if (!c.trim().isEmpty()) ins.addCourse(c.trim());
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
            FileWriter fw = new FileWriter(USERS_FILE);
            fw.write("[\n");

            for (int i = 0; i < users.size(); i++) {
                fw.write(users.get(i).toJson());
                if (i != users.size() - 1) fw.write(",\n");
            }

            fw.write("\n]");
            fw.close();
        } catch (IOException e) {
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

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();

            String content = sb.toString().trim();
            if (content.equals("[]") || content.isEmpty()) return courses;

            content = content.substring(1, content.length() - 1);
            String[] courseEntries = content.split("\\},\\{");

            for (int i = 0; i < courseEntries.length; i++) {
                String c = courseEntries[i];
                if (!c.startsWith("{")) c = "{" + c;
                if (!c.endsWith("}")) c = c + "}";

                String courseId = getValue(c, "courseId");
                String title = getValue(c, "title");
                String description = getValue(c, "description");
                String instructorId = getValue(c, "instructorId");

                Course course = new Course(courseId, title, description, instructorId);

                // Load lessons
                String lessonsArray = getArray(c, "lessons");
                if (!lessonsArray.isEmpty()) {
                    String[] lessonEntries = lessonsArray.split("\\},\\{");
                    for (String l : lessonEntries) {
                        if (!l.startsWith("{")) l = "{" + l;
                        if (!l.endsWith("}")) l = l + "}";
                        String lessonId = getValue(l, "lessonId");
                        String lessonTitle = getValue(l, "title");
                        String lessonContent = getValue(l, "content");
                        Lesson lesson = new Lesson(lessonId, lessonTitle, lessonContent);
                        course.addLesson(lesson);
                    }
                }

                // Load students
                String studentsArray = getArray(c, "students");
                if (!studentsArray.isEmpty()) {
                    for (String sId : studentsArray.split(",")) {
                        if (!sId.trim().isEmpty()) course.getStudents().add(sId.trim());
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
            FileWriter fw = new FileWriter(COURSES_FILE);
            fw.write("[\n");

            for (int i = 0; i < courses.size(); i++) {
                fw.write(courses.get(i).toJson());
                if (i != courses.size() - 1) fw.write(",\n");
            }

            fw.write("\n]");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===================== Helper Methods =====================
    // Extract value from JSON-like string
    private String getValue(String json, String key) {
        String pattern = "\"" + key + "\":\"";
        int start = json.indexOf(pattern);
        if (start == -1) return "";
        start += pattern.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }

    // Extract array from JSON-like string: ["C1","C2"] → C1,C2
    private String getArray(String json, String key) {
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern);
        if (start == -1) return "";
        start += pattern.length();
        int end = json.indexOf("]", start);
        String arr = json.substring(start, end + 1).trim();
        arr = arr.replace("[", "").replace("]", "").replace("\"", "");
        return arr;
    }
}
