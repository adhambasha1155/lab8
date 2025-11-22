package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Lesson1 {
    private String lessonId;
    private String title;
    private String content;
    private List<String> resources;
    private Quiz1 quiz;

    public Lesson1(String lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
        this.quiz = null;
    }

    public Lesson1() {
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
    public Quiz1 getQuiz() { return quiz;}
    public void setQuiz(Quiz1 quiz) {this.quiz = quiz; }
    public int getPassingScore() {
        if (quiz != null) {
            // Delegate the call to the Quiz object
            return quiz.getPassingScore();
        }
        // Return 0 or a sensible default if the lesson has no quiz
        return 0; 
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
        if(quiz != null){
            obj.put("quiz",quiz.toJson());
        }
        return obj;
    }

}
