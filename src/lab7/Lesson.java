package Lab7;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return String.format("{\"lessonId\":\"%s\",\"title\":\"%s\",\"content\":\"%s\",\"resources\":%s}", 
                              lessonId, title, content, resources.toString());
    }
}
