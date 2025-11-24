/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Dell
 */
import Lab7.Course;
import Lab7.Lesson1;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import Lab7.Student;
import java.util.List;
import Lab7.StudentManager;
import Lab7.coursepreformance;
import Lab7.lessonstats;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent; // Import the necessary event class
import javax.swing.event.ListSelectionListener;

public class Chart1 extends javax.swing.JFrame {

    private StudentManager manager;
    private List<Student> students;
    private Course selectedCourse;
    private managecourses parentFrame;

    public Chart1(Course course, StudentManager studentManager, managecourses parentFrame) {
        initComponents();
        this.selectedCourse = course;
        this.manager = studentManager;
        this.parentFrame = parentFrame;

        setTitle("Course Performance Insights: " + course.getTitle());

        populateStudentList();

        // Display the overall course average immediately upon loading (THE FIX)
        displayOverallCourseStats();

        List1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                List1ValueChanged(evt);
            }
        });
    }

    private void displayOverallCourseStats() {
        // Get all students (or just the enrolled ones) for accurate performance calculation
        List<Student> allStudents = this.manager.getAllStudents();

        // Generate stats for the specific course using all students
        // ASSUMPTION: course.generatePerformanceStats handles filtering for enrollment.
        coursepreformance stats = selectedCourse.generatePerformanceStats(allStudents);

        // This is the overall course average quiz score (aggregated across all students)
        double overallCourseAvgScore = stats.getAverageCourseScore();

        // Set the overall course average into the 'quizaverage' field
        quizaverage.setText(String.format("%.2f%%", overallCourseAvgScore));

        // Clear the student-specific field
        percent.setText("");
    }

    private void populateStudentList() {
        try {
            // Fetch all students (assuming we need all of them to check enrollment later)
            this.students = this.manager.getAllStudents();

            DefaultListModel<String> listModel = new DefaultListModel<>();

            if (this.students != null) {
                for (Student student : this.students) {
                    // Only add students enrolled in the current course to the list
                    if (student.getEnrolledCourses().contains(selectedCourse.getCourseId())) {
                         listModel.addElement(student.getUsername() + " (" + student.getUserId() + ")");
                    }
                }
            }

            List1.setModel(listModel);

        } catch (Exception e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }

    private void List1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return; // Only process the final selection event
        }

        int selectedIndex = List1.getSelectedIndex();
        
        // Find the selected student by matching the username/ID string in the list
        if (selectedIndex != -1) {
            // Get the display string, e.g., "student1 (1001)"
            String selectedItemText = List1.getModel().getElementAt(selectedIndex);
            // Extract the user ID from the string (assuming ID is in parentheses)
            String userId = selectedItemText.substring(
                selectedItemText.lastIndexOf("(") + 1, 
                selectedItemText.lastIndexOf(")")
            );
            
            // Find the actual Student object
            Student selectedStudent = this.manager.getStudentById(userId); // Assuming StudentManager has this method
            
            if (selectedStudent != null) {
                // Correct: Call the method that updates the student-specific field (percent)
                displayStudentCompletion(selectedStudent); 
            } else {
                 percent.setText("Error");
            }

        } else if (selectedIndex == -1) {
            // Correct: When deselected, only clear the student-specific field (percent).
            // The quizaverage field should retain the overall course average.
            percent.setText(""); 
            // Optional: Re-display the overall average just in case, though it shouldn't be needed
            // displayOverallCourseStats(); 
        }
    }

    // This method correctly calculates the student's completion rate for the selected course
    private void displayStudentCompletion(Student student) {

        // The quizaverage field is deliberately left alone to display the Overall Course Average.
        
        // --- B. COMPLETION PERCENTAGE PER COURSE (Student Specific) ---
        List<Lesson1> lessons = selectedCourse.getLessons();
        int completedLessons = 0;
        int totalLessonsWithQuizzes = 0;

        for (Lesson1 lesson : lessons) {
            if (lesson.getQuiz() != null) {
                totalLessonsWithQuizzes++;
                // Use isLessonCompleted which checks for a passing score
                if (student.isLessonCompleted(selectedCourse.getCourseId(), lesson.getLessonId())) {
                    completedLessons++;
                }
            }
        }

        double completionRate = (totalLessonsWithQuizzes > 0)
                ? ((double) completedLessons / totalLessonsWithQuizzes) * 100
                : 0.0;

        // Display the student's completion rate in the 'percent' field
        percent.setText(String.format("%.2f%%", completionRate));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        List1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        quizaverage = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        percent = new javax.swing.JTextField();
        back = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        List1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(List1);

        jLabel1.setText("Quiz averages per lesson.");

        quizaverage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quizaverageActionPerformed(evt);
            }
        });

        jLabel2.setText("Completion percentages.");

        percent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentActionPerformed(evt);
            }
        });

        back.setText("back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quizaverage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percent)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quizaverage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(percent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(back)
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quizaverageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quizaverageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quizaverageActionPerformed

    private void percentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_percentActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        if (parentFrame != null) {
            parentFrame.setVisible(true); // Show the managecourses frame
            this.dispose(); // Close the current Chart1 frame
        } else {
            // Fallback error, though this shouldn't happen if the calling code is updated.
            JOptionPane.showMessageDialog(this, "Parent window reference missing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> List1;
    private javax.swing.JToggleButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField percent;
    private javax.swing.JTextField quizaverage;
    // End of variables declaration//GEN-END:variables
}
