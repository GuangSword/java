/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;


import database.*;
import gui.types.*;
import gui.utils.GUIUtils;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;

import types.Activity;

/**
 *
 * @author Jordan Toering, Graeme Smith
 */


public class MarkingPDF extends MSPanel {

    private static final String COLUMN_NAMES[]={"Description", "Grade", "Max Grade"};
    private Object[][] table;
    private int studentID;
    private String courseID, actName;
	
     public MarkingPDF(final String courseID, final Activity act, final int stud_id) {
        super(act.getName());
        initComponents();
        float max = 0;
        
        this.courseID = courseID;
        this.actName = act.getName();
        this.studentID = stud_id;
        //Some PDF Solution Implemented Below
        
        //Populate the Rubric Table code below this:
        grade_field.setText("");
        Object[][] temp = CourseAccess.accessRubricItems(courseID, act.getName());
		if (temp.length != 0) {
			table = new Object[temp.length][3];
			for(int i=0; i<table.length; i++) {
				table[i][0] = temp[i][0];
				table[i][1] = 0;
				table[i][2] = temp[i][1];
				max += (float) temp[i][1];
 			}
			DefaultTableModel tm = new DefaultTableModel(table,COLUMN_NAMES) {
	            public boolean isCellEditable(int row, int column) {
	            	if(column == 0 || column == 2) 
	            		return false;
	            	return true;
	            }
			};;
			tm.addTableModelListener(new javax.swing.event.TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					table_change_actionPerformed(e);
				}
	        });;
			rubric_table.setModel(tm);
		}
		Object[] grades = GradeAccess.accessGrades(courseID, act.getName(), stud_id);
		System.out.println(grades.length);
		if(grades.length != 0) {
			for(int i=0; i<grades.length; i++) {
				table[i][1] = grades[i];
				System.out.println(grades[i]);
			}
			DefaultTableModel tm = new DefaultTableModel(table,COLUMN_NAMES) {
	            public boolean isCellEditable(int row, int column) {
	            	if(column == 0 || column == 2) 
	            		return false;
	            	return true;
	            }
			};;
			tm.addTableModelListener(new javax.swing.event.TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					table_change_actionPerformed(e);
				}
	        });;
			rubric_table.setModel(tm);
			float gradeTotal = 0;
			for (int i = 0; i < rubric_table.getRowCount(); i++)
				gradeTotal += Float.parseFloat(rubric_table.getModel()
						.getValueAt(i, 1).toString());
	    	String currentGrade = "" + gradeTotal;
	    	grade_field.setText(currentGrade);
		}
		String maxField = "" + max;
		max_grade_field.setText(maxField);
		rubric_table.getColumnModel().getColumn(0).setPreferredWidth(500);
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rubric_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rubric_table = new javax.swing.JTable();
        max_grade_field = new javax.swing.JTextField();
        grade_field = new javax.swing.JTextField();
        slash_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        submitted_panel = new javax.swing.JPanel();
        open_submission_button = new javax.swing.JButton();
        solution_panel = new javax.swing.JPanel();
        open_solution_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        save_button = new javax.swing.JButton();

        rubric_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Rubric"));
        rubric_panel.setMinimumSize(new java.awt.Dimension(400, 500));
        rubric_panel.setPreferredSize(new java.awt.Dimension(400, 500));

        rubric_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Description", "Grade", "Max Grade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rubric_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(rubric_table);
        if (rubric_table.getColumnModel().getColumnCount() > 0) {
            rubric_table.getColumnModel().getColumn(1).setResizable(false);
            rubric_table.getColumnModel().getColumn(1).setPreferredWidth(8);
            rubric_table.getColumnModel().getColumn(2).setResizable(false);
            rubric_table.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        max_grade_field.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        max_grade_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        max_grade_field.setText("Max");

        grade_field.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        grade_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grade_field.setText("Grade");

        slash_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slash_label.setText("/");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Total:");

        javax.swing.GroupLayout rubric_panelLayout = new javax.swing.GroupLayout(rubric_panel);
        rubric_panel.setLayout(rubric_panelLayout);
        rubric_panelLayout.setHorizontalGroup(
            rubric_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addGroup(rubric_panelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grade_field, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slash_label, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(max_grade_field, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rubric_panelLayout.setVerticalGroup(
            rubric_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rubric_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rubric_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(max_grade_field, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(slash_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grade_field, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        submitted_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Submission PDF"));
        submitted_panel.setMinimumSize(new java.awt.Dimension(400, 500));
        submitted_panel.setPreferredSize(new java.awt.Dimension(400, 500));

        open_submission_button.setText("Open Submisson");

        javax.swing.GroupLayout submitted_panelLayout = new javax.swing.GroupLayout(submitted_panel);
        submitted_panel.setLayout(submitted_panelLayout);
        submitted_panelLayout.setHorizontalGroup(
            submitted_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submitted_panelLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(open_submission_button, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        submitted_panelLayout.setVerticalGroup(
            submitted_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(submitted_panelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(open_submission_button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        solution_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Solution PDF"));
        solution_panel.setMinimumSize(new java.awt.Dimension(400, 500));
        solution_panel.setPreferredSize(new java.awt.Dimension(400, 500));

        open_solution_button.setText("Open Solution");

        javax.swing.GroupLayout solution_panelLayout = new javax.swing.GroupLayout(solution_panel);
        solution_panel.setLayout(solution_panelLayout);
        solution_panelLayout.setHorizontalGroup(
            solution_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solution_panelLayout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(open_solution_button, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        solution_panelLayout.setVerticalGroup(
            solution_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solution_panelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(open_solution_button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        next_button.setText("Next");
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });

        save_button.setText("Save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next_button, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rubric_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitted_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solution_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rubric_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(next_button, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(save_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitted_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(solution_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void next_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_next_buttonActionPerformed

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
        for (int i = 0; i < rubric_table.getColumnCount(); i++) {
			try {
				GradeAccess.enterGrade(studentID, courseID, actName,
						rubric_table.getModel().getValueAt(i, 0).toString(),
						Float.parseFloat(rubric_table.getModel().getValueAt(i, 1)
								.toString()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				GradeAccess.updateGrade(studentID, courseID, actName,
						rubric_table.getModel().getValueAt(i, 0).toString(),
						Float.parseFloat(rubric_table.getModel().getValueAt(i, 1)
								.toString()));
			}
		}
		JOptionPane.showMessageDialog(this,"Grade saved.");
		GUIUtils.getMasterFrame(this).goBack();
    }//GEN-LAST:event_save_buttonActionPerformed
    
    private void table_change_actionPerformed(TableModelEvent e) {
    	float grades = 0;
    	for(int i=0; i<rubric_table.getRowCount(); i++)
			grades += Float.parseFloat(rubric_table.getModel()
					.getValueAt(i, e.getColumn()).toString());
    	String currentGrade = "" + grades;
    	grade_field.setText(currentGrade);
	}
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField grade_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField max_grade_field;
    private javax.swing.JButton next_button;
    private javax.swing.JButton open_solution_button;
    private javax.swing.JButton open_submission_button;
    private javax.swing.JPanel rubric_panel;
    private javax.swing.JTable rubric_table;
    private javax.swing.JButton save_button;
    private javax.swing.JLabel slash_label;
    private javax.swing.JPanel solution_panel;
    private javax.swing.JPanel submitted_panel;
    // End of variables declaration//GEN-END:variables
}
