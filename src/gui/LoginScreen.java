/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;


import javax.swing.*;
import java.awt.event.*;

import gui.utils.*;
import login.Login;
import types.*;

/**
 *
 * @author Pun
 */
public class LoginScreen extends JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPasswordField pass_field;
    private JButton forgot_pwd;
    private JButton login_button;
    private JTextField username_field;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private MasterFrame master;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form LoginScreen
     */
    public LoginScreen() {
        //Add the shark image
        setIconImage(new ImageIcon(getClass()
				   .getResource("markshark-1x.png")).getImage());
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1        = new JPanel();
        jLabel1        = new JLabel();
        jLabel2        = new JLabel();
        pass_field     = new JPasswordField();
        username_field = new JTextField();
        login_button   = new JButton();
        forgot_pwd     = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MarkShark - Login");

        jPanel1.setBorder(BorderFactory
			  .createTitledBorder("Enter Login Information"));

        jLabel1.setText("Username:");
        jLabel2.setText("Password:");

        pass_field.setToolTipText("");

        forgot_pwd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    forgotPassActionPerformed(e);
		}
	    });

	login_button.setText("Log In");
	login_button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    loginButtonActionPerformed(e);
		}
	    });

	// Flurry of generated code
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pass_field))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(username_field, GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(login_button, GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(username_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pass_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addComponent(login_button)
                .addGap(25, 25, 25))
        );

        forgot_pwd.setText("Forgot Password");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(forgot_pwd)
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forgot_pwd)
                .addGap(21, 21, 21))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

	// Render the Login Screen
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void loginButtonActionPerformed(ActionEvent evt) {
	/*
	 * Pass the user name and password to the login system.
	 * The login system will indicate whether the login succeeded.
	 * If it did, hide the login screen and show the splash page
	 * according to the user's Role.
	 */
	String name = username_field.getText();
	String pass = new String(pass_field.getPassword());
        Account test;

        //	 FOR TESTING PURPOSES ONLY
        if (name.equalsIgnoreCase("sysadmin")){
            test = new SystemAdmin("Joey", "Tester", 9999, "password", "sysadmin");
        } else if (name.equalsIgnoreCase("admin")){
            test = new AcademicAdmin("Joey", "Tester", 9999, "password", "admin");
        } else if (name.equalsIgnoreCase("assist")){
            test = new AssistantAdmin("Joey", "Tester", 9999, "password", "assist");
        } else if (name.equalsIgnoreCase("instructor")){
            test = new Instructor("Joey", "Tester", 9999, "password", "instructor");
        } else if (name.equalsIgnoreCase("tatm") ){// ta marker
            test = new TATM("Joey", "Tester", 9999, "password", "ta");
        } else {
	    test = Login.login(name, pass);
        }

	if(pass.length() < 0) {  // Temporary! Should be 5 or so.
	    JOptionPane
		.showMessageDialog(this, "The password given is too short.");
	} else if(name.isEmpty()) {
	    JOptionPane
		.showMessageDialog(this, "No user name given.");
	} else if(test == null) {
		JOptionPane.showMessageDialog(this, "Invalid username/password combo");
	} else {
	    System.out.println("Logging in as " + name +
			       " with password `" + pass + "`");
	    // TODO: This needs to be passed a legit Account object.
	    master = new MasterFrame(test, this);
	    this.setVisible(false);
	    master.run();
	}
    }

    private void forgotPassActionPerformed(ActionEvent evt) {
	/*
	 * Doesn't have to be dealt with yet.
	 */
	GUIUtils.nothing();
    }

    public void clearFields() {
	username_field.setText("");
	pass_field.setText("");
    }
}
