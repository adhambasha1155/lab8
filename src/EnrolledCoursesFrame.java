import Lab7.Course;
import Lab7.Instructor;
import Lab7.Student;
import Lab7.User;
import Lab7.UserAccountManager;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EnrolledCoursesFrame extends javax.swing.JFrame {
    
    private UserAccountManager accountManager;
    private User currentUser;
    private DefaultTableModel model;

    /**
     * Creates new form EnrolledCoursesFrame
     */
    public EnrolledCoursesFrame(UserAccountManager accountManager, User currentUser) {
        this.accountManager = accountManager;
        this.currentUser = currentUser;
        initComponents();

        // Table setup
        String[] columns = {"Course ID", "Title", "Description", "Instructor"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table non-editable
            }
        };
        jTable1.setModel(model);

        loadEnrolledCourses();
    }

       // Load courses that the student is enrolled in
    private void loadEnrolledCourses() {
        model.setRowCount(0); // clear table

        if (!(currentUser instanceof Student)) return;

        Student student = (Student) currentUser;
        List<String> enrolledCourseIds = student.getEnrolledCourses();

        for (String courseId : enrolledCourseIds) {
            Course c = accountManager.getCourseById(courseId);
            if (c != null) {
                String instructorName = getInstructorName(c.getInstructorId());
                model.addRow(new Object[]{c.getCourseId(), c.getTitle(), c.getDescription(), instructorName});
            }
        }
    }

    // Helper to get instructor name
    private String getInstructorName(String instructorId) {
        for (User u : accountManager.getAllUsers()) {
            if (u instanceof Instructor && u.getUserId().equals(instructorId)) {
                return u.getUsername();
            }
        }
        return "Unknown";
    }
                               

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EnrolledTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        viewLessonsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        EnrolledTable.setViewportView(jTable1);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        viewLessonsButton.setText("View Lessons");
        viewLessonsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewLessonsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EnrolledTable, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(EnrolledTable, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // Go back to the Student Dashboard
    StudentDashboardFrame dashboard = new StudentDashboardFrame(accountManager, currentUser);
    dashboard.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void viewLessonsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewLessonsButtonActionPerformed
        // 1. Which row did the user click?
        int row = jTable1.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course from the list!");
            return;
        }

        // 2. Grab the ID from Column 0 of that specific row.
        // If the row is "Biology", this gets "C1". If "Math", this gets "C2".
        String selectedCourseId = (String) model.getValueAt(row, 0);

        // 3. Send ONLY that ID to the lessons frame.
        LessonsFrame lessonsFrame = new LessonsFrame(accountManager, currentUser, selectedCourseId);
        lessonsFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewLessonsButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane EnrolledTable;
    private javax.swing.JButton backButton;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton viewLessonsButton;
    // End of variables declaration//GEN-END:variables
}
