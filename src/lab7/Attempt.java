package lab7;
 
import org.json.JSONObject;

public class Attempt {

    private int score;

    public Attempt(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("score", score);
        return obj;
    }
}
