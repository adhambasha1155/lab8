
import Lab7.Admin;
import Lab7.AdminManager;
import Lab7.User;
import Lab7.UserAccountManager;
import Lab7.Instructor; 
import Lab7.InstructorManager; 
import Lab7.Student;
import Lab7.StudentManager;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {
    
    private UserAccountManager accountManager;
    private InstructorManager instructorManager;
    private StudentManager studentManager;
    private AdminManager adminManager;
    


    
    public LoginFrame() {
        initComponents();
        accountManager = new UserAccountManager(); // load users from users.json
        instructorManager = new InstructorManager();
        studentManager = new StudentManager();
        adminManager = new AdminManager();
    }
    
    public LoginFrame(UserAccountManager accountManager) {
        initComponents();
        this.accountManager = accountManager;
        instructorManager = new InstructorManager();
        studentManager = new StudentManager();
        adminManager = new AdminManager();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        signupButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        loginTypeComboBox = new javax.swing.JComboBox<>();
        exit = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        emailLabel.setText("Email / Username:");

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        passwordLabel.setText("Password:");

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        signupButton.setText("Sign Up");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Log in By: ");

        loginTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Email", "Username" }));

        exit.setText("exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(signupButton)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField)
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(loginTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(loginTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(signupButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exit)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
      
    String input = emailField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();
    String loginType = (String) loginTypeComboBox.getSelectedItem(); // Email or Username

    if (input.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Please fill in all fields!",
            "Warning",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    User user = null;

    if ("Email".equals(loginType)) {
        if (!accountManager.isValidEmail(input)) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid email!",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        // Assuming accountManager.login() handles both student/instructor login by email
        user = accountManager.login(input, password); 
    } else if ("Username".equals(loginType)) {
        // Assuming accountManager.loginByUsername() handles both student/instructor login by username
        user = accountManager.loginByUsername(input, password);
    }

    if (user == null) {
        JOptionPane.showMessageDialog(this,
            "Login failed! Check your " + loginType.toLowerCase() + " and password.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    // Login successful
    JOptionPane.showMessageDialog(this,
        "Login successful! Welcome " + user.getUsername() +
        " (" + user.getRole() + ")",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
    );

    // TODO: open dashboard <------------------ IMPLEMENTATION START
    if ("Instructor".equals(user.getRole())) {
        
        // Cast the successful user object to an Instructor
        Instructor instructor = (Instructor) user; 
        
        // Open the Instructor Dashboard
        InstructorDashboardFrame dashboard = new InstructorDashboardFrame(
            instructor, 
            accountManager, 
            instructorManager,
            studentManager
        );
        dashboard.setVisible(true);
        
        // Close the current Login Frame
        this.dispose(); 
        
    } else if ("Student".equals(user.getRole())) {
        // TODO: Implement Student Dashboard opening here
         this.dispose();
        new StudentDashboardFrame(accountManager, (Student) user).setVisible(true);
    }else if ("Admin".equals(user.getRole())) { // ADD THIS BLOCK
        
        Admin admin = (Admin) user;
        
        // Open the Admin Dashboard
        // Assuming you have an AdminDashboardFrame class that takes necessary managers
        AdminDashboardFrame dashboard = new AdminDashboardFrame(
            admin,
            accountManager,
            adminManager,
            instructorManager,
            studentManager
        );
        dashboard.setVisible(true);
        
        // Close the current Login Frame
        this.dispose();
    }

    }//GEN-LAST:event_loginButtonActionPerformed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        // Open SignupFrame and pass the account manager
        SignupFrame signupFrame = new SignupFrame(accountManager);
        this.dispose();
        signupFrame.setVisible(true);
    }//GEN-LAST:event_signupButtonActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JToggleButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JComboBox<String> loginTypeComboBox;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signupButton;
    // End of variables declaration//GEN-END:variables
}
