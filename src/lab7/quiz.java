package Lab7;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class quiz {
    private String quizId;
    private String title;
    private List<Question> questions;

    public quiz(String quizId, String title) {
        this.quizId = quizId;
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public quiz() {
        this.questions = new ArrayList<>();
    }

    // Add a question
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Getters
    public String getQuizId() { return quizId; }
    public String getTitle() { return title; }
    public List<Question> getQuestions() { return questions; }

    // Convert Quiz to JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("quizId", quizId);
        obj.put("title", title);

        JSONArray arr = new JSONArray();
        for (Question q : questions) {
            arr.put(q.toJson());
        }
        obj.put("questions", arr);
        return obj;
    }

    // Inner Question class
    public static class Question {
        private String questionText;
        private List<String> options;
        private int correctIndex; // index of correct option

        public Question(String questionText, List<String> options, int correctIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctIndex = correctIndex;
        }

        public JSONObject toJson() {
            JSONObject obj = new JSONObject();
            obj.put("questionText", questionText);
            obj.put("options", new JSONArray(options));
            obj.put("correctIndex", correctIndex);
            return obj;
        }

        // Getters
        public String getQuestionText() { return questionText; }
        public List<String> getOptions() { return options; }
        public int getCorrectIndex() { return correctIndex; }

        private boolean isCorrect(Integer get) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    public int calculateScore(List<Integer> studentAnswers) {
    int score = 0;
    for (int i = 0; i < questions.size(); i++) {
        if (i < studentAnswers.size() && questions.get(i).isCorrect(studentAnswers.get(i))) {
            score += 100 / questions.size(); // equal weight per question
        }
    }
    return score;
}

}
