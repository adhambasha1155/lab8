package Lab7;

import java.io.*;
import java.util.*;
import org.json.*;

public class JsonDatabaseManager {

    private final String USERS_FILE = "users.json";
    private final String COURSES_FILE = "courses.json";

    private List<Student> students;
    private List<Instructor> instructors;
    private List<User> users;

    public JsonDatabaseManager() {
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        users = new ArrayList<>();

        loadUsers();
    }

    // ===================== Load Users =====================
    public List<User> loadUsers() {
        try {
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                writeFile(file, "[]");
                return users;
            }

            String content = readFile(file);
            if (content.isEmpty()) content = "[]";

            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                String role = obj.getString("role");

                if (role.equalsIgnoreCase("Student")) {
                    Student s = Student.fromJson(obj);
                    students.add(s);
                    users.add(s);
                } else if (role.equalsIgnoreCase("Instructor")) {
                    Instructor ins = Instructor.fromJson(obj);
                    instructors.add(ins);
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
        JSONArray arr = new JSONArray();

        for (User u : users) {
            arr.put(u.toJson());
        }

        writeFile(new File(USERS_FILE), arr.toString(4));
    }

    // ===================== Load Courses =====================
    public List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();

        try {
            File file = new File(COURSES_FILE);
            if (!file.exists()) {
                file.createNewFile();
                writeFile(file, "[]");
                return courses;
            }

            String content = readFile(file);
            if (content.isEmpty()) content = "[]";

            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Course c = Course.fromJson(obj);
                courses.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    // ===================== Save Courses =====================
    public void saveCourses(List<Course> courses) {
        JSONArray arr = new JSONArray();

        for (Course c : courses) {
            arr.put(c.toJson());
        }

        writeFile(new File(COURSES_FILE), arr.toString(4));
    }

    // ===================== File Helpers =====================
    private String readFile(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null)
            sb.append(line);

        br.close();
        return sb.toString().trim();
    }

    private void writeFile(File file, String text) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
}
