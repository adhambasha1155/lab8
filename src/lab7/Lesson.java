package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Lesson {
    private String lessonId;
    private String title;
    private String content;
    private List<String> resources;

    public Lesson(String lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
    }

    public Lesson() {
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public void addResource(String resource) { resources.add(resource); }

    public String getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public List<String> getResources() { return resources; }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("lessonId", lessonId);
        obj.put("title", title);
        obj.put("content", content);
        obj.put("resources", new JSONArray(resources));
        return obj;
    }

    quiz getquiz() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getPassingScore() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
