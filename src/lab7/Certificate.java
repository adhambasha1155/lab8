/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author Eman
 */
import Lab7.User;
import Lab7.JsonDatabaseManager;
import Lab7.Course;
import Lab7.Student;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import org.json.JSONObject;
import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import Lab7.Lesson1;
import Lab7.Result1;
import Lab7.Quiz1;

public class Certificate {

    private String certificateId;
    private String studentId;
    private String courseId;
    private LocalDate issueDate;
    private static final String USERS_FILE = "users.json";

    public Certificate(String studentId, String courseId) {
        this.certificateId = UUID.randomUUID().toString();
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = LocalDate.now();
    }

    // Getters
    public String getCertificateID() {
        return certificateId;
    }

    public String getStudentID() {
        return studentId;
    }

    public String getCourseID() {
        return courseId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("certificateId", certificateId);
        obj.put("studentId", studentId);
        obj.put("courseId", courseId);
        obj.put("issueDate", issueDate.toString());  // Stored as String
        return obj;
    }

    public static Certificate fromJson(JSONObject obj) {
        Certificate c = new Certificate(
                obj.getString("studentId"),
                obj.getString("courseId")
        );
        c.issueDate = LocalDate.parse(obj.getString("issueDate"));
        c.certificateId = obj.getString("certificateId");

        return c;
    }

    public static Certificate generateCertificate(Student s, Course c) throws IOException {
        if (isCourseCompleted(s, c)) {
            Certificate cert = new Certificate(s.getUserId(), c.getCourseId());
            s.addCertificate(cert);
            String content = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
            JSONArray usersArr = new JSONArray(content);
            for (int i = 0; i < usersArr.length(); i++) {
                JSONObject u = usersArr.getJSONObject(i);
                if (u.getString("userId").equals(cert.getStudentID())) {
                    if (!u.has("certificates")) {
                        u.put("certificates", new JSONArray());
                    }
                    JSONObject certObj = cert.toJson();
                    u.getJSONArray("certificates").put(certObj);
                    break;
                }
            }
            Files.write(Paths.get(USERS_FILE), usersArr.toString(4).getBytes());
            return cert;
        } else {
            return null;
        }
    }

    public static ArrayList<Certificate> getCertificates(Student student) throws IOException {
        ArrayList<Certificate> result = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
        JSONArray usersArr = new JSONArray(content);

        for (int i = 0; i < usersArr.length(); i++) {
            JSONObject u = usersArr.getJSONObject(i);

            if (u.getString("userId").equals(student.getUserId())) {

                if (u.has("certificates")) {
                    JSONArray certsArr = u.getJSONArray("certificates");

                    for (int j = 0; j < certsArr.length(); j++) {
                        JSONObject certObj = certsArr.getJSONObject(j);
                        Certificate cert = Certificate.fromJson(certObj);
                        result.add(cert);
                    }
                }
                break;
            }
        }

        return result;
    }

    public static boolean isCourseCompleted(Student student, Course course) {

        int totalQuizzes = course.getLessons().size();
        int passedQuizzes = 0;

        for (Lesson1 lesson : course.getLessons()) {

            Quiz1 q = lesson.getQuiz();

            // Student result:
            Result1 result = student.getResultForLesson(lesson.getLessonId());

            if (result != null && result.isLessonCompleted(lesson)) {
                passedQuizzes++;
            }
        }

        return passedQuizzes == totalQuizzes;
    }
}