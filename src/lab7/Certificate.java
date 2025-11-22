/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author Eman
 */
import Lab7.Course;
import Lab7.Student;
import java.time.LocalDate;
import java.util.UUID;
import org.json.JSONObject;

public class Certificate {
    private String certificateId; 
    private String studentId;
    private String courseId;
    private LocalDate issueDate;

    public Certificate(String studentId, String courseId) {
        this.certificateId = UUID.randomUUID().toString();
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = LocalDate.now();
    }

    // Getters
    public String getCertificateID() { return certificateId; }
    public String getStudentID() { return studentId; }
    public String getCourseID() { return courseId; }
    public LocalDate getIssueDate() { return issueDate; }

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
    public static Certificate generateCertificate(Student s, Course c) {
        Certificate cert = new Certificate(s.getUserId(), c.getCourseId());
        s.addCertificate(cert);
        return cert;
    }
}

