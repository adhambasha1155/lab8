import Lab7.Course;
import Lab7.Instructor;
import Lab7.JsonDatabaseManager;
import Lab7.Student;
import Lab7.User;
import Lab7.UserAccountManager;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BrowseCoursesFrame extends javax.swing.JFrame {

    private User currentUser;
    private DefaultTableModel model;
    private UserAccountManager accountManager;

    
    public BrowseCoursesFrame(UserAccountManager accountManager, User currentUser) {
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
        CourseTable.setModel(model);

        loadCourses(); // load courses from UserAccountManager
    }
    
    
    
    // ---------------------------
    // LOAD COURSES INTO TABLE
    private void loadCourses() {
        model.setRowCount(0); // clear table

        List<Course> courses = accountManager.getAllCourses();
        if (courses == null) return;

        for (Course c : courses) {
            String instructorName = getInstructorName(c.getInstructorId());

            model.addRow(new Object[]{
                c.getCourseId(),
                c.getTitle(),
                c.getDescription(),
                instructorName
            });
        }
    }

    
    // GET INSTRUCTOR NAME BY ID
    private String getInstructorName(String instructorId) {
        for (User u : accountManager.getAllUsers()) {
            if (u instanceof Instructor && u.getUserId().equals(instructorId)) {
                return u.getUsername();
            }
        }
        return "Unknown";
    }
// -----------------------------------------------------------
    
    
    // Auto-generated GUI code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CourseTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        enrollButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setText("Available Courses");

        CourseTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(CourseTable);

        backButton.setText("Back to Dashboard");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        enrollButton.setText("Enroll in Selected Course");
        enrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(428, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(417, Short.MAX_VALUE)
                    .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(45, 45, 45)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // 1. Create the Student Dashboard screen again
        StudentDashboardFrame dashboard = new StudentDashboardFrame(accountManager, currentUser);
        
        // 2. Make it visible
        dashboard.setVisible(true);
        
        // 3. Close the current "Browse Courses" window
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void enrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollButtonActionPerformed
        // 1. Get Selected Row
        int row = CourseTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course first.");
            return;
        }

        // 2. Get Course ID from Column 0
        String courseId = (String) model.getValueAt(row, 0);

        // 3. CHECK FOR DUPLICATES (The Fix)
        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            
            // Check if the list of enrolled courses already contains this ID
            if (student.getEnrolledCourses().contains(courseId)) {
                JOptionPane.showMessageDialog(this, 
                    "You are already enrolled in this course!", 
                    "Duplicate Enrollment", 
                    JOptionPane.WARNING_MESSAGE);
                return; // Stop here, do not save
            }
        }

        // 4. Proceed if not duplicate
        Course course = accountManager.getCourseById(courseId);
        if (course == null) {
            JOptionPane.showMessageDialog(this, "Course not found!");
            return;
        }

        // Add student to course list
        course.enrollStudent(currentUser.getUserId());

        // Add course to student's enrolled list
        ((Student) currentUser).enrollCourse(courseId);

        // Save both JSON files
        accountManager.saveAllUsers();
        accountManager.saveAllCourses();

        JOptionPane.showMessageDialog(this, "Enrolled successfully!");
    }//GEN-LAST:event_enrollButtonActionPerformed

       
      
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CourseTable;
    private javax.swing.JButton backButton;
    private javax.swing.JButton enrollButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
