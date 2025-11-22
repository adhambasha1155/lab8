package Lab7;

import java.io.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonDatabaseManager {

    private final String USERS_FILE = "users.json";
    private final String COURSES_FILE = "courses.json";

    // =========================================================================================
    // NEW HELPER METHODS FOR DESERIALIZING QUIZ AND RESULT STRUCTURES
    // =========================================================================================
    /**
     * Helper method to parse a Question object from JSON.
     */
    private Question1 loadQuestionFromJson(JSONObject qObj) {
        // Helper to convert JSONArray of options to List<String>
        List<String> options = new ArrayList<>();
        JSONArray optionsArr = qObj.getJSONArray("options");
        for (int l = 0; l < optionsArr.length(); l++) {
            options.add(optionsArr.getString(l));
        }

        // Assuming your question class is named 'Question'
        return new Question1(
                qObj.getString("questionText"),
                options,
                qObj.getInt("correctIndex")
        );
    }

    /**
     * Helper method to parse a Quiz object from JSON.
     */
    private Quiz1 loadQuizFromJson(JSONObject quizObj) {
        Quiz1 quiz = new Quiz1(
                quizObj.getString("quizId"),
                quizObj.getString("title")
        );

        JSONArray questionsArr = quizObj.optJSONArray("questions");
        if (questionsArr != null) {
            for (int k = 0; k < questionsArr.length(); k++) {
                JSONObject qObj = questionsArr.getJSONObject(k);
                Question1 question = loadQuestionFromJson(qObj);
                quiz.addQuestion(question);
            }
        }
        return quiz;
    }

    /**
     * Helper method to parse a Result1 object from JSON.
     */
    public Result1 loadResultFromJson(JSONObject rObj) {
        Result1 result = new Result1(
                rObj.getString("studentId"),
                rObj.getString("lessonId"),
                rObj.getInt("maxRetries")
        );

        JSONArray attemptsArr = rObj.getJSONArray("attempts");
        for (int k = 0; k < attemptsArr.length(); k++) {
            JSONObject attemptObj = attemptsArr.getJSONObject(k);
            int score = attemptObj.getInt("score");
            // FIX: Use the new unconditional loadAttempt() method
            result.loadAttempt(score); 
        }
        return result;
    }
     
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try {
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write("[]");
                fw.close();
            }

            // Read the file content
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String content = sb.toString().trim();
            if (content.equals("[]") || content.isEmpty()) {
                return users;
            }

            // Parse JSON
            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String userId = obj.getString("userId");
                String username = obj.getString("username");
                String email = obj.getString("email");
                String passwordHash = obj.getString("passwordHash");
                String role = obj.getString("role");

                User user = null;

                if ("Student".equals(role)) {
                    Student student = new Student(userId, username, email, passwordHash);

                    // Load enrolled courses
                    JSONArray enrolledCoursesArr = obj.optJSONArray("enrolledCourses");
                    if (enrolledCoursesArr != null) {
                        for (int j = 0; j < enrolledCoursesArr.length(); j++) {
                            student.enrollCourse(enrolledCoursesArr.getString(j));
                        }
                    }

                    // Load progress (completed lessons)
                    JSONArray progressArr = obj.optJSONArray("progress");
                    if (progressArr != null) {
                        for (int j = 0; j < progressArr.length(); j++) {
                            String entry = progressArr.getString(j); // e.g., "C001:L01"
                            String[] parts = entry.split(":");
                            if (parts.length == 2) {
                                student.markLessonCompleted(parts[0], parts[1]);
                            }
                        }
                    }

                    // ✨ NEW: Load quiz results for the student
                    JSONArray resultsArr = obj.optJSONArray("quizResults");
                    if (resultsArr != null) {
                        for (int j = 0; j < resultsArr.length(); j++) {
                            JSONObject resultObj = resultsArr.getJSONObject(j);
                            Result1 result = loadResultFromJson(resultObj);
                            student.getQuizResults().add(result);
                        }
                    }

                    user = student;

                } else if ("Instructor".equals(role)) {
                    Instructor instructor = new Instructor(userId, username, email, passwordHash);

                    // Load created courses
                    JSONArray createdCoursesArr = obj.optJSONArray("createdCourses");
                    if (createdCoursesArr != null) {
                        for (int j = 0; j < createdCoursesArr.length(); j++) {
                            instructor.addCourse(createdCoursesArr.getString(j));
                        }
                    }
                    user = instructor;

                } else if ("Admin".equals(role)) {
                    user = new Admin(userId, username, email, passwordHash);
                }

                if (user != null) {
                    users.add(user);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();

        try {
            File file = new File(COURSES_FILE);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write("[]");
                fw.close();
            }

            // Read the file content
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            String content = sb.toString().trim();
            if (content.equals("[]") || content.isEmpty()) {
                return courses;
            }

            // Parse JSON
            JSONArray arr = new JSONArray(content);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                Course course = new Course(
                        obj.getString("courseId"),
                        obj.getString("title"),
                        obj.getString("description"),
                        obj.getString("instructorId")
                );

                // Load status
                String statusStr = obj.optString("status", ApprovalStatus.PENDING.name());
                course.setStatus(ApprovalStatus.valueOf(statusStr));

                // Load lessons
                JSONArray lArr = obj.optJSONArray("lessons");
                if (lArr != null) {
                    for (int j = 0; j < lArr.length(); j++) {
                        JSONObject lObj = lArr.getJSONObject(j);
                        Lesson1 lesson = new Lesson1(
                                lObj.getString("lessonId"),
                                lObj.getString("title"),
                                lObj.getString("content")
                        );

                        // Load resources
                        JSONArray resArr = lObj.optJSONArray("resources");
                        if (resArr != null) {
                            for (int k = 0; k < resArr.length(); k++) {
                                lesson.addResource(resArr.getString(k));
                            }
                        }

                        // ✨ NEW: Load Quiz for the lesson
                        JSONObject quizObj = lObj.optJSONObject("quiz");
                        if (quizObj != null) {
                            Quiz1 quiz = loadQuizFromJson(quizObj);
                            lesson.setQuiz(quiz);
                        }

                        course.addLesson(lesson);
                    }
                }

                // Load enrolled student IDs
                JSONArray studentsArr = obj.optJSONArray("students");
                if (studentsArr != null) {
                    for (int j = 0; j < studentsArr.length(); j++) {
                        course.enrollStudent(studentsArr.getString(j));
                    }
                }

                courses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

   
    public void saveUsers(List<User> users) {
        try {
            JSONArray arr = new JSONArray();

            for (User user : users) {
                arr.put(user.toJson()); // toJson() now handles quizResults for Student
            }

            FileWriter fw = new FileWriter(USERS_FILE);
            fw.write(arr.toString(2)); // pretty print with indentation
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCourses(List<Course> courses) {
        try {
            JSONArray arr = new JSONArray();

            for (Course course : courses) {
                arr.put(course.toJson()); // toJson() now handles Quiz for Lesson
            }

            FileWriter fw = new FileWriter(COURSES_FILE);
            fw.write(arr.toString(2)); // pretty print with indentation
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
