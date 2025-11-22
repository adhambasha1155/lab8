package Lab7;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Question {

    private String questionText;
    private List<String> options;
    private int correctIndex;

    public Question(String questionText, List<String> options, int correctIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("questionText", questionText);
        obj.put("options", new JSONArray(options));
        obj.put("correctIndex", correctIndex);
        return obj;
    }

    // Check if a given answer index is correct
    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctIndex;
    }
}
