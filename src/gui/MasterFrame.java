package gui;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.util.*;
import types.*;
import gui.utils.*;
import gui.types.*;

/**
 * @author Colin
 */
public class MasterFrame extends JFrame {
    private JFrame login_screen;
    private JPanel  top_panel;
    private MSPanel curr_page;
    private JButton logout_button;
    private JButton back_button;
    private JButton settings_button;
    private JLabel  role_label;
    private JLabel  crumbs;  // This won't be a JLabel later.
    private LinkedList<MSPanel> pages;
    private GridBagConstraints c = new GridBagConstraints();

    // This will take an `Account` object and populate itself according to
    // the Account type.
    public MasterFrame(Account a, JFrame ls) {
	// Initialize components.
	login_screen    = ls;
	top_panel       = new JPanel();
	logout_button   = new JButton("Logout");
	back_button     = new JButton("Back");
	settings_button = new JButton("Settings");
	role_label      = new JLabel();  // Changes according to `a`.
	crumbs          = new JLabel("Breadcrumbs here...");
	pages           = new LinkedList<MSPanel>();

	// Various settings.
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setResizable(false);
	setSize(700,700);
	//	setLayout(new GridLayout(2,1));
        back_button.setEnabled(false);  //Grey back button initially
	//Set shark image icon
        setIconImage(new ImageIcon(getClass()
				   .getResource("markshark-1x.png")).getImage());

	// Dynamic display of Landing Page, Role, etc.
	if (a.getAccountType() == Account.Type.ACADEMICADMIN) {
	    role_label.setText("Role: Academic Administrator");
	    curr_page = new LandingPageAdmin((AcademicAdmin)a);
	} else if (a.getAccountType() == Account.Type.ASSISTANTADMIN) {
	    role_label.setText("Role: Assistant Academic Administrator");
	    curr_page = new LandingPageAssistAdmin((AssistantAdmin)a);
	} else if (a.getAccountType() == Account.Type.INSTRUCTOR) {
	    role_label.setText("Role: Instructor");
	    curr_page = new LandingPageInstructor((Instructor)a);
	} else if (a.getAccountType() == Account.Type.SYSTEMADMIN) {
	    role_label.setText("Role: System Administrator");
	    curr_page = new LandingPageSysAdmin((SystemAdmin)a);
	} else if (a.getAccountType() == Account.Type.TATMMARKER) {
	    role_label.setText("Role: TA/TM");
	    curr_page = new LandingPageTA((TATM)a);
	} else {
	    System.out.println("Unknown account type!! Everyone overboard!! Eject!!");
	    curr_page = null;  // omg please no.
	}

	// Set up action listeners
	back_button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    goBackAction(e);
		}
	    });
	
	logout_button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    logoutAction(e);
		}
	    });

	settings_button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    settingsAction(e);
		}
	    });

	// Set up persistent top bar
	top_panel.setLayout(new GridBagLayout());
	//	c.ipadx = 50;
	c.gridx = 0;
	c.gridy = 0;
	c.anchor = GridBagConstraints.LINE_START;
	top_panel.add(crumbs, c);

	c.gridx = 9;
	c.gridy = 0;
	c.weightx = 1.0;
	//	c.weighty = 1.0;
	c.anchor = GridBagConstraints.LINE_END;
	top_panel.add(role_label, c);

	c.gridx = 0;
	c.gridy = 1;
	c.weighty = 1.0;
	c.weightx = 0;
	c.anchor = GridBagConstraints.LINE_START;
	top_panel.add(back_button, c);

	c.gridx = 1;
	top_panel.add(settings_button, c);

	c.gridx = 2;
	c.gridy = 1;
	c.anchor = GridBagConstraints.LINE_END;
	//	c.weightx = 1.0;
	//	c.weighty = 1.0;
	top_panel.add(logout_button, c);

	// MasterFrame Layout Settings
	setLayout(new GridBagLayout());
	c = new GridBagConstraints();
	c.gridx = 0;
	c.gridy = 0;
	c.ipady = 50;
	c.weightx = 1.0;
	add(top_panel, c);

	// Constraints for all bottom panels ever.
	c.gridy = 1;
	//	c.ipady = 100;
	c.weighty = 1.0;
	c.gridwidth = 10;
	add(curr_page, c);

	// Render the Frame
	setTitle("MarkShark Grading System - " + curr_page.getPanelTitle());
	setLocationRelativeTo(null);
	pack();
    }

    public void run() {
	this.setVisible(true);
	GUIUtils.centerFrame(this);  // Doesn't work?
    }

    public void movePage(MSPanel p) {
        //if length of pages is zero 
        if (pages.isEmpty())
            back_button.setEnabled(true);
        
        curr_page.setVisible(false);
	remove(curr_page);
	pages.push(curr_page);
	curr_page = p;
	showPage(curr_page);
    }

    private void showPage(MSPanel p) {
	add(p, c);
	p.setVisible(true);
	setTitle("MarkShark Grading System - " + p.getPanelTitle());
	pack();

	System.out.println("Moving to: " + p.getPanelTitle());
    }

    public void goBackAction(ActionEvent e) {
	System.out.println("Going back.");
	curr_page.setVisible(false);
	remove(curr_page);
	curr_page = pages.pop();
	showPage(curr_page);
        
        //if the page stack zero, then grey back button
        if (pages.isEmpty())
            back_button.setEnabled(false);
    }
    public void settingsAction(ActionEvent e) {
	// Show the settings page.
	GUIUtils.nothing();
    }

    private void logoutAction(ActionEvent e) {
	System.out.println("Logging out...");
	// TODO: Write to the system log?
	//processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	setVisible(false);
	login_screen.setVisible(true);
    }
}
