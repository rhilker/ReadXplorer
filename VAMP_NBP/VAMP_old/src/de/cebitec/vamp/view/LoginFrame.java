package de.cebitec.vamp.view;

import de.cebitec.vamp.GestureListenerI;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ddoppmeier
 */
public class LoginFrame extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private String defaultDatabaseMySQL;
    private String defaultdatabaseh2;
    private String defaultuser;
    private String defaulthostname;
    private List<GestureListenerI> gestureListener;
    private static String LOGIN_DATABASE_MYSQL = "login.database.mysql";
    private static String LOGIN_USER = "login.user";
    private static String LOGIN_HOSTNAME = "login.hostname";
    private static String LOGIN_DATABASE_H2= "login.database.h2";

    /** Creates new form Login */
    public LoginFrame() {
        gestureListener = new ArrayList<GestureListenerI>();
        initComponents();
        setLoginData();
        dbChooseButton.setVisible(false);
    }

    public void addGestureListener(GestureListenerI listener) {
        gestureListener.add(listener);
    }

    public void removeGestureListener(GestureListenerI listener) {
        gestureListener.remove(listener);
    }

    private void alertListenersOfShutdown() {
        for (GestureListenerI i : gestureListener) {
            i.shutDownApplication();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        urlLabel = new javax.swing.JLabel();
        databaseLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        databaseField = new javax.swing.JTextField();
        userField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        urlField = new javax.swing.JTextField();
        dbTypeLabel = new javax.swing.JLabel();
        dbTypeBox = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        dbChooseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        urlLabel.setText("Server URL:");

        databaseLabel.setText("Database");

        userLabel.setText("User:");

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

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        dbTypeLabel.setText("Database type:");

        dbTypeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySQL", "h2" }));
        dbTypeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbTypeBoxActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Save data");

        dbChooseButton.setText("Choose");
        dbChooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbChooseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userLabel)
                                    .addComponent(databaseLabel)
                                    .addComponent(passwordLabel)
                                    .addComponent(urlLabel)))
                            .addComponent(dbTypeLabel)
                            .addComponent(closeButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(userField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(loginButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dbTypeBox, 0, 205, Short.MAX_VALUE)
                            .addComponent(urlField, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(databaseField, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dbChooseButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(urlLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseLabel)
                    .addComponent(databaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbChooseButton))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(closeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        alertListenersOfShutdown();
}//GEN-LAST:event_closeButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        passwordFieldActionPerformed(evt);

}//GEN-LAST:event_loginButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        alertListenersOfShutdown();
    }//GEN-LAST:event_formWindowClosing

    private void setLoginData() {
        Preferences prefs = Preferences.userNodeForPackage(LoginFrame.class);
        defaultuser = prefs.get(LOGIN_USER , null);
        defaultDatabaseMySQL = prefs.get(LOGIN_DATABASE_MYSQL, null);
        defaulthostname = prefs.get(LOGIN_HOSTNAME, null);
        defaultdatabaseh2 = prefs.get(LOGIN_DATABASE_H2, null);
        userField.setText(defaultuser);
        urlField.setText(defaulthostname);
        databaseField.setText(defaultDatabaseMySQL);
    }
    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed

        String adapter = dbTypeBox.getSelectedItem().toString();
        if (adapter.equalsIgnoreCase("mysql")) {
            adapter = "mysql";

            String hostname = urlField.getText();

            String database = databaseField.getText();

            String user = userField.getText();

            String password = new String(passwordField.getPassword());
            //if there are new LoginData

            if (jCheckBox1.isSelected()) {
                if (!hostname.equals(defaulthostname) || !database.equals(defaultDatabaseMySQL) || !user.equals(defaultuser)) {

                    Preferences prefs = Preferences.userNodeForPackage(LoginFrame.class);
                    prefs.put(LOGIN_HOSTNAME, hostname);
                    prefs.put(LOGIN_DATABASE_MYSQL, database);
                    prefs.put(LOGIN_USER , user);
                    try {
                        prefs.flush();
                    } catch (BackingStoreException ex) {
                        Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {

                Preferences prefs = Preferences.userNodeForPackage(LoginFrame.class);
                prefs.put(LOGIN_HOSTNAME, "");
                prefs.put(LOGIN_DATABASE_MYSQL, "");
                prefs.put(LOGIN_USER , "");
                try {
                    prefs.flush();
                } catch (BackingStoreException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // let all listeners now, they should log in now
            for (GestureListenerI i : gestureListener) {
                i.login(adapter, hostname, database, user, password);
            }
        } else {

            adapter = "h2";

            String hostname = null;

            String database = databaseField.getText();
            if (database.endsWith(".h2.db")) {
                database = database.replace(".h2.db", "");
            }

            String user = null;

            String password = null;
            //if there are new LoginData

            if (jCheckBox1.isSelected()) {
                if (!database.equals(defaultdatabaseh2)) {

                    Preferences prefs = Preferences.userNodeForPackage(LoginFrame.class);
                    prefs.put(LOGIN_DATABASE_H2, database);
                    try {
                        prefs.flush();
                    } catch (BackingStoreException ex) {
                        Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {

                Preferences prefs = Preferences.userNodeForPackage(LoginFrame.class);
                prefs.put(LOGIN_DATABASE_H2, "");
                try {
                    prefs.flush();
                } catch (BackingStoreException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


            // let all listeners now, they should log in now
            for (GestureListenerI i : gestureListener) {
                i.login(adapter, hostname, database, user, password);
            }
        }
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void dbTypeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbTypeBoxActionPerformed
        String db = dbTypeBox.getSelectedItem().toString();
        if (db.equalsIgnoreCase("h2")) {
            userField.setVisible(false);
            urlField.setVisible(false);
            passwordField.setVisible(false);
            userLabel.setVisible(false);
            passwordLabel.setVisible(false);
            urlLabel.setVisible(false);
            dbChooseButton.setVisible(true);
            databaseField.setText(defaultdatabaseh2);
        } else {
            userField.setVisible(true);
            urlField.setVisible(true);
            passwordField.setVisible(true);
            passwordLabel.setVisible(true);
            urlLabel.setVisible(true);
            userLabel.setVisible(true);
            dbChooseButton.setVisible(false);
            databaseField.setText(defaultDatabaseMySQL);
        }
    }//GEN-LAST:event_dbTypeBoxActionPerformed

    private void dbChooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbChooseButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);

        Preferences prefs2 = Preferences.userNodeForPackage(LoginFrame.class);
        String db = dbTypeBox.getSelectedItem().toString();
        if (db.equalsIgnoreCase("h2")) {
            String path = prefs2.get(LOGIN_DATABASE_H2, null);
            if (path != null) {
                fc.setCurrentDirectory(new File(path));
            }
        } else {
            String path = prefs2.get(LOGIN_DATABASE_MYSQL, null);
            if (path != null) {
                fc.setCurrentDirectory(new File(path));
            }
        }
        File file = null;

        if (result == 0) {
            // file chosen
            file = fc.getSelectedFile();
            if(!file.exists()){
                JOptionPane.showMessageDialog(this,
                        "The Database "+file.getAbsolutePath()+"\nyou have choosen does not exsist."
                        + "\nWhen you login this database\n will be created automatically!",
                        "Database not exist",
                        JOptionPane.WARNING_MESSAGE);
            }
            databaseField.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_dbChooseButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField databaseField;
    private javax.swing.JLabel databaseLabel;
    private javax.swing.JButton dbChooseButton;
    private javax.swing.JComboBox dbTypeBox;
    private javax.swing.JLabel dbTypeLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField urlField;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
