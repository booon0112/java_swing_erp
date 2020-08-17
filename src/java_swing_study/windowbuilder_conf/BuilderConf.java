package java_swing_study.windowbuilder_conf;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 윈도우빌더 설정 테스트 1. 생성자 변경 2. 이벤트 핸들러 변경 programed by 영은쓰 2020.07.14
 *
 */

@SuppressWarnings("serial")
public class BuilderConf extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton btn01;
    private JButton btn02;
    private JButton btn03;
    private JButton btn04;

    public BuilderConf() {
	initComponents();
    }

    private void initComponents() {
	setTitle("기본설정 예제 ");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 504, 101);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new GridLayout(0, 5, 0, 0));

	btn01 = new JButton("anonymous");
	btn01.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Anonymous class");
	    }
	});
	contentPane.add(btn01);

	btn02 = new JButton("inner class");
	btn02.addActionListener(new Btn02ActionListener());
	contentPane.add(btn02);

	btn03 = new JButton("parent class");
	btn03.addActionListener(this);
	contentPane.add(btn03);

	btn04 = new JButton("parent stop method");
	btn04.addActionListener(this);
	contentPane.add(btn04);
    }

    private class Btn02ActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JOptionPane.showMessageDialog(null, "inner class");
	}
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btn04) {
	    actionPerformedBtn04(e);
	}if(e.getSource()==btn03) {
	JOptionPane.showMessageDialog(null, btn03.getText());
	}
    }

    protected void actionPerformedBtn04(ActionEvent e) {
	JOptionPane.showMessageDialog(null, "parent class-> stub method");
    }
}
