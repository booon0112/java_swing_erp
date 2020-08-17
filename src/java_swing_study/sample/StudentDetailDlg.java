package java_swing_study.sample;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java_swing_study.sample.component.StudentPanel;
import java_swing_study.sample.dto.Student;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDetailDlg extends JDialog implements ActionListener {

    private final JPanel contentPanel = new JPanel();
    private StudentPanel pStudent;
    private JButton okButton;

    /**
     * Launch the application.
     */
    //    public static void main(String[] args) {
    //	try {
    //	    StudentDetailDlg dialog = new StudentDetailDlg();
    //	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    //	    dialog.setVisible(true);
    //	} catch (Exception e) {
    //	    e.printStackTrace();
    //	}
    //    }

    /**
     * Create the dialog.
     */
    public StudentDetailDlg() {
	initComponents();
    }

    public void setStudent(Student student) {
	pStudent.setStudent(student);
    }

    public void setTfNotEditable() {
	pStudent.setEditableTf();
    }

    private void initComponents() {
	setTitle("학생 세부정보");
	setBounds(100, 100, 450, 370);
	getContentPane().setLayout(new BorderLayout());
	contentPanel.setBorder(new TitledBorder(null, "\uD559\uC0DD \uC815\uBCF4", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	getContentPane().add(contentPanel, BorderLayout.CENTER);
	contentPanel.setLayout(new BorderLayout(0, 0));
	{
	    pStudent = new StudentPanel();
	    contentPanel.add(pStudent, BorderLayout.CENTER);
	}
	{
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    {
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	    }
	}
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == okButton) {
	    actionPerformedOkButton(e);
	}
    }

    protected void actionPerformedOkButton(ActionEvent e) {

	dispose();
    }
}