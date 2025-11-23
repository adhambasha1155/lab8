/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Lab7.Course;
import Lab7.Lesson1;
import Lab7.Question1;
import Lab7.Quiz1;
import Lab7.Result1;
import Lab7.Student;
import Lab7.UserAccountManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import Lab7.StudentManager;
import java.io.IOException;
import lab7.Certificate;

public class QuizFrame extends javax.swing.JFrame {

    private Quiz1 quiz;
    private Student student;
    private Lesson1 lesson;
    private UserAccountManager accountManager;
    private String courseId;

    // --- Quiz State Fields ---
    private int currentQuestionIndex = 0;
    private List<Integer> studentAnswers;

    public QuizFrame() {
        initComponents();
    }

    public QuizFrame(UserAccountManager accountManager, Student student, String courseId, Lesson1 lesson) {
        this.accountManager = accountManager;
        this.student = student;
        this.courseId = courseId;
        this.lesson = lesson;
        this.quiz = lesson.getQuiz(); // The Quiz1 object to be taken
        this.studentAnswers = new ArrayList<>();

        initComponents(); // Initialize your GUI components
        this.setLocationRelativeTo(null);
        setTitle("Quiz: " + quiz.getTitle() + " (" + (currentQuestionIndex + 1) + " of " + quiz.getQuestions().size() + ")");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(rbOption1);
        group.add(rbOption2);
        group.add(rbOption3);
        group.add(rbOption4);

        displayQuestion(currentQuestionIndex);
    }

    private void displayQuestion(int index) {
        if (index >= quiz.getQuestions().size()) {
            finishQuiz();
            return;
        }

        // Get the current question
        Question1 currentQuestion = quiz.getQuestions().get(index);
        List<String> options = currentQuestion.getOptions();

        // 1. Update Labels
        lblQuestionNum.setText("Question " + (index + 1));
        lblProgress.setText((index + 1) + " of " + quiz.getQuestions().size());
        txtQuestion.setText(currentQuestion.getQuestionText());

        // 2. Clear selection
        rbOption1.setSelected(true); // Default select the first option

        // 3. Update Radio Buttons
        rbOption1.setText(options.size() > 0 ? options.get(0) : "N/A");
        rbOption2.setText(options.size() > 1 ? options.get(1) : "N/A");
        rbOption3.setText(options.size() > 2 ? options.get(2) : "N/A");
        rbOption4.setText(options.size() > 3 ? options.get(3) : "N/A");

        // 4. Update Button text if last question
        if (index == quiz.getQuestions().size() - 1) {
            btnNext.setText("Finish Quiz");
        } else {
            btnNext.setText("Next Question");
        }
    }

    private int getSelectedAnswerIndex() {
        if (rbOption1.isSelected()) {
            return 0;
        }
        if (rbOption2.isSelected()) {
            return 1;
        }
        if (rbOption3.isSelected()) {
            return 2;
        }
        if (rbOption4.isSelected()) {
            return 3;
        }
        return -1; // No selection, though a default selection prevents this
    }

    private void finishQuiz() {
        // 1. Calculate Score (Assuming Quiz1 has a calculateScore method)
        int finalScore = quiz.calculateScore(studentAnswers);
        int passingScore = lesson.getPassingScore(); // Use the method you implemented!

        // 2. Handle Result Saving
        // Find existing result or create a new one
        Result1 result = student.getResultForLesson(lesson.getLessonId());
        if (result == null) {
            // Assuming max retries is 1 for now, adjust as needed
            result = new Result1(student.getUserId(), lesson.getLessonId(), 3);
            student.getQuizResults().add(result);
        }

        // Add the new attempt
        result.addAttempt(finalScore);

        // 3. Save the Student data
        accountManager.saveUser(student);
        // 3. Check if course is now complete and generate certificate
        try {
            Course course = accountManager.getCourseById(courseId);
            Certificate cert = Certificate.generateCertificate(student, course);
            if (cert != null) {
                System.out.println("Certificate generated: " + cert.getCertificateID());
                JOptionPane.showMessageDialog(null, "Congratulations! You've completed the course and earned a certificate.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating certificate: " + e.getMessage());
        }

        // 4. Show Feedback
        String message;
        if (finalScore >= passingScore) {
            message = "Congratulations! You passed the quiz with a score of " + finalScore + ".\n"
                    + "The passing score was " + passingScore + ".";
        } else {
            message = "You scored " + finalScore + ".\n"
                    + "The passing score was " + passingScore + ".\n"
                    + "You have " + (result.getMaxRetries() - result.getAttempts().size()) + " retries remaining.";
        }

        JOptionPane.showMessageDialog(this, message, "Quiz Completed", JOptionPane.INFORMATION_MESSAGE);

        // 5. Close QuizFrame and refresh the LessonsFrame (or return to it)
        this.dispose();
        // Since LessonsFrame created this, you might need a way to refresh it.
        // For simplicity, we'll just close and let the user re-open if needed.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblQuestionNum = new javax.swing.JLabel();
        lblProgress = new javax.swing.JLabel();
        txtQuestion = new javax.swing.JTextField();
        rbOption1 = new javax.swing.JRadioButton();
        rbOption2 = new javax.swing.JRadioButton();
        rbOption3 = new javax.swing.JRadioButton();
        rbOption4 = new javax.swing.JRadioButton();
        btnNext = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtQuestion.setText("jTextField3");

        rbOption1.setText("jRadioButton1");

        rbOption2.setText("jRadioButton2");

        rbOption3.setText("jRadioButton3");

        rbOption4.setText("jRadioButton4");

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jToggleButton1.setText("back");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbOption3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbOption2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbOption1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbOption4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblQuestionNum, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuestionNum)
                    .addComponent(lblProgress))
                .addGap(65, 65, 65)
                .addComponent(txtQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbOption1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbOption2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbOption3)
                .addGap(18, 18, 18)
                .addComponent(rbOption4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(jToggleButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        studentAnswers.add(getSelectedAnswerIndex());

        // 2. Move to the next question
        currentQuestionIndex++;
        displayQuestion(currentQuestionIndex);
    }//GEN-LAST:event_btnNextActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblQuestionNum;
    private javax.swing.JRadioButton rbOption1;
    private javax.swing.JRadioButton rbOption2;
    private javax.swing.JRadioButton rbOption3;
    private javax.swing.JRadioButton rbOption4;
    private javax.swing.JTextField txtQuestion;
    // End of variables declaration//GEN-END:variables
}
