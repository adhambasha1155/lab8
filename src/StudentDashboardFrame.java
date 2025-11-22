
import Lab7.User;
import Lab7.UserAccountManager;
import javax.swing.JTable;


public class StudentDashboardFrame extends javax.swing.JFrame {
    
    private UserAccountManager accountManager; // reference to manage users
    private User currentUser; // the logged-in student

    

    
    public StudentDashboardFrame(UserAccountManager accountManager, User currentUser) {
        this.accountManager = accountManager;
        this.currentUser = currentUser;
        initComponents();
        welcomeLabel.setText("Welcome, " + currentUser.getUsername());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        enrolledCoursesButton = new javax.swing.JButton();
        viewLessonsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        browseCoursesButton = new javax.swing.JButton();
        enrolledCoursesButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        welcomeLabel.setText("Welcome, Student");

        enrolledCoursesButton.setText("My Enrolled Courses");
        enrolledCoursesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrolledCoursesActionPerformed(evt);
            }
        });

        viewLessonsButton.setText("View Lessons");
        viewLessonsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewLessonsActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        browseCoursesButton.setText("Browse Courses");
        browseCoursesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseCoursesActionPerformed(evt);
            }
        });

        enrolledCoursesButton1.setText("quiz");
        enrolledCoursesButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrolledCoursesButton1enrolledCoursesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(browseCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enrolledCoursesButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enrolledCoursesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(viewLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrolledCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(enrolledCoursesButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseCoursesActionPerformed
        BrowseCoursesFrame browseFrame = new BrowseCoursesFrame(accountManager, currentUser);
        browseFrame.setVisible(true);
        this.dispose(); // optional: close dashboard
    }//GEN-LAST:event_browseCoursesActionPerformed

    private void enrolledCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrolledCoursesActionPerformed
        EnrolledCoursesFrame enrolledFrame = new EnrolledCoursesFrame(accountManager, currentUser);
        enrolledFrame.setVisible(true);
        this.dispose(); // optional: close dashboard
    }//GEN-LAST:event_enrolledCoursesActionPerformed

    private void viewLessonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewLessonsActionPerformed
       // Instead of opening lessons directly, open the list of courses
    // so the user can SELECT which course to view.
    EnrolledCoursesFrame enrolledFrame = new EnrolledCoursesFrame(accountManager, currentUser);
    enrolledFrame.setVisible(true);
    this.dispose(); // Close the dashboard
    }//GEN-LAST:event_viewLessonsActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        this.dispose(); // close current dashboard
        new LoginFrame(accountManager).setVisible(true); // go back to login
    }//GEN-LAST:event_logoutActionPerformed

    private void enrolledCoursesButton1enrolledCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrolledCoursesButton1enrolledCoursesActionPerformed
        // TODO add your handling code here:
        quiz q=new quiz();
        q.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_enrolledCoursesButton1enrolledCoursesActionPerformed
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseCoursesButton;
    private javax.swing.JButton enrolledCoursesButton;
    private javax.swing.JButton enrolledCoursesButton1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton viewLessonsButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
