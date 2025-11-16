package Lab7;

import org.json.JSONObject;
import java.util.Arrays;

public class TestLessonMain {
    public static void main(String[] args) {
        // Create a lesson
        Lesson lesson = new Lesson("L1", "Introduction to Java", "This lesson covers basics of Java.");

        // Add resources
        lesson.addResource("slides.pdf");
        lesson.addResource("example_code.zip");

        // Print lesson info
        System.out.println("Lesson ID: " + lesson.getLessonId());
        System.out.println("Title: " + lesson.getTitle());
        System.out.println("Content: " + lesson.getContent());
        System.out.println("Resources: " + lesson.getResources());

        // Print JSON output
        JSONObject json = lesson.toJson();
        System.out.println("Lesson JSON:\n" + json.toString(2));
    }
}
