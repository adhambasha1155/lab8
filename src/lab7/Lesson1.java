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
    private int passingScore;

    public Lesson1(String lessonId, String title, String content, int passingScore) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
        this.quiz = null;
        this.passingScore = passingScore;
    }

   public Lesson1(String lessonId, String title, String content) {
        this(lessonId, title, content, 50); // 🔑 Calls the full constructor with a default passing score of 70
    }
    
    public Lesson1() {
        this.resources = new ArrayList<>();
        // Default constructor should initialize new field as well
        this.passingScore = 50; // 🔑 Default passing score
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
    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }
    public int getPassingScore() {
            if (quiz != null) {
            return quiz.getPassingScore();
            }
        return this.passingScore;
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
        obj.put("passingScore", passingScore);
        if(quiz != null){
            obj.put("quiz",quiz.toJson());
        }
        return obj;
    }

}
