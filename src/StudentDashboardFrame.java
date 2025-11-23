
import Lab7.Student;
import Lab7.User;
import Lab7.UserAccountManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lab7.Certificate;


public class StudentDashboardFrame extends javax.swing.JFrame {
    
    private UserAccountManager accountManager; // reference to manage users
    private User currentUser; // the logged-in student

    

    
    public StudentDashboardFrame(UserAccountManager accountManager, User currentUser) {
        this.accountManager = accountManager;
        this.currentUser = currentUser;
        initComponents();
        welcomeLabel.setText("Welcome, " + currentUser.getUsername());
    }
private void loadCertificates() {
    try {
        ArrayList<Certificate> certs = Certificate.getCertificates((Student) currentUser);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("student Id");
        model.addColumn("Certificate ID");
        model.addColumn("Course ID");
        model.addColumn("Issue Date");

        for (Certificate cert : certs) {
            model.addRow(new Object[]{
                cert.getCertificateID(),
                cert.getCourseID(),
                cert.getIssueDate().toString()
            });
        }

        certificateTable.setModel(model);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading certificates: " + e.getMessage());
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        enrolledCoursesButton = new javax.swing.JButton();
        viewLessonsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        browseCoursesButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        certificateTable = new javax.swing.JTable();
        downloadButton = new javax.swing.JButton();

        welcomeLabel.setText("Welcome, Student");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        jPanel1.setName("Certificates Earned"); // NOI18N

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        label1.setText("Certificates Earned");

        certificateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        certificateTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        certificateTable.setColumnSelectionAllowed(true);
        certificateTable.setName("certificateTable"); // NOI18N
        jScrollPane1.setViewportView(certificateTable);

        downloadButton.setBackground(new java.awt.Color(0, 204, 51));
        downloadButton.setText("Download");
        downloadButton.setActionCommand("");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(downloadButton)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(browseCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(enrolledCoursesButton)
                        .addGap(53, 53, 53)
                        .addComponent(viewLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enrolledCoursesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Certificates Earned");

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

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadCertificates();
    }//GEN-LAST:event_formWindowOpened
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseCoursesButton;
    private javax.swing.JTable certificateTable;
    private javax.swing.JButton downloadButton;
    private javax.swing.JButton enrolledCoursesButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton viewLessonsButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
