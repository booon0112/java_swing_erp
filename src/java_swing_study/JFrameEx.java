package java_swing_study;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JFrameEx extends JFrame {

    private JPanel contentPane;

    public JFrameEx() {
	setTitle("ContentPane과 JFrame");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 300, 150);
	contentPane = new JPanel();
	contentPane.setBackground(Color.ORANGE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	JButton btnOk = new JButton("Ok");
	btnOk.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		JOptionPane.showMessageDialog(null, btn.getText());
	    }
	});
	contentPane.add(btnOk);

	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		JOptionPane.showMessageDialog(null, btn.getText());
	    }
	});
	contentPane.add(btnCancel);

	JButton btnIgnore = new JButton("Ignore");
	contentPane.add(btnIgnore);
    }

}
