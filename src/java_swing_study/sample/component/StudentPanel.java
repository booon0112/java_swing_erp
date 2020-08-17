package java_swing_study.sample.component;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java_swing_study.sample.dto.Student;
import java_swing_study.sample.exception.EmptyTfException;

@SuppressWarnings("serial")
public class StudentPanel extends JPanel {
    private JLabel lblNo;
    private JTextField tfNo;
    private JLabel lblName;
    private JTextField tfName;
    private JLabel lblKor;
    private JTextField tfKor;
    private JLabel lblEng;
    private JTextField tfEng;
    private JLabel lblMath;
    private JTextField tfMath;
    private JLabel lblTotal;
    private JLabel lblTotal2;
    private JLabel lblAvg;
    private JLabel lblAvg2;

    public StudentPanel() {

	initComponents();
    }

    private void initComponents() {
	setLayout(new GridLayout(0, 2, 20, 10));

	lblNo = new JLabel("학생번호 ");
	lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblNo);

	tfNo = new JTextField();
	add(tfNo);
	tfNo.setColumns(10);

	lblName = new JLabel("학생성명");
	lblName.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblName);

	tfName = new JTextField();
	tfName.setColumns(10);
	add(tfName);

	lblKor = new JLabel("국어점수");
	lblKor.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblKor);

	MyDocumentListener docListener =new MyDocumentListener();
	
	tfKor = new JTextField();
	tfKor.getDocument().addDocumentListener(docListener);
	tfKor.setColumns(10);
	add(tfKor);

	lblEng = new JLabel("영어점수");
	lblEng.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblEng);

	tfEng = new JTextField();
	tfEng.getDocument().addDocumentListener(docListener);
	tfEng.setColumns(10);
	add(tfEng);

	lblMath = new JLabel("수학점수");
	lblMath.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblMath);

	tfMath = new JTextField();
	tfMath.getDocument().addDocumentListener(docListener);
	tfMath.setColumns(10);
	add(tfMath);

	lblTotal = new JLabel("총점");
	lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblTotal);

	lblTotal2 = new JLabel("");
	lblTotal2.setForeground(Color.RED);
	lblTotal2.setHorizontalAlignment(SwingConstants.CENTER);
	add(lblTotal2);

	lblAvg = new JLabel("평균");
	lblAvg.setHorizontalAlignment(SwingConstants.RIGHT);
	add(lblAvg);

	lblAvg2 = new JLabel("");
	lblAvg2.setHorizontalAlignment(SwingConstants.CENTER);
	lblAvg2.setForeground(Color.RED);
	add(lblAvg2);
    }

    //getter setter clear
    public Student getStudent() {
	if(isEmpty()) {
	    throw new EmptyTfException("공란이 존재");
	}
	int no = Integer.parseInt(tfNo.getText().trim());
	String name = tfName.getText().trim();
	int kor = Integer.parseInt(tfKor.getText().trim());
	int eng = Integer.parseInt(tfEng.getText().trim());
	int math = Integer.parseInt(tfMath.getText().trim());
	return new Student(no, name, kor, eng, math);
    }

    private boolean isEmpty() {
	// TODO Auto-generated method stub
	return tfNo.getText().isEmpty() || 
		tfName.getText().isEmpty() ||
		tfKor.getText().isEmpty() ||
		tfMath.getText().isEmpty() ||
		tfEng.getText().isEmpty();
    }

    public void setStudent(Student std) {
	tfNo.setText(String.valueOf(std.getNo()));
	tfName.setText(std.getName());
	tfKor.setText(String.valueOf(std.getKor()));
	tfEng.setText(String.valueOf(std.getEng()));
	tfMath.setText(String.valueOf(std.getMath()));
	lblTotal2.setText(String.valueOf(std.getTotal()));
	lblAvg2.setText(String.format("%.2f", std.getAverage()));
    }

    public void clearTf() {
	tfNo.setText("");
	tfName.setText("");
	tfKor.setText("");
	tfEng.setText("");
	tfMath.setText("");
	lblTotal2.setText("");
	lblAvg2.setText("");
    }
    
    //국어, 영어, 수학 field의 값이 변경될 때 감지하는 listener생성 예정 -> 국어 영어 수학에 달 것. 
    class MyDocumentListener implements DocumentListener{

	@Override
	public void insertUpdate(DocumentEvent e) {
//	    System.out.printf("%s%n","insertUpdate");
	    changeTotalAndAverage();
	}

	
	@Override
	public void removeUpdate(DocumentEvent e) {
//	    System.out.printf("%s%n","removeUpdate");	    
	    changeTotalAndAverage();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
//	    System.out.printf("%s%n","changedUpdate");	    
	    changeTotalAndAverage();
	}
	private void changeTotalAndAverage() {
	    int kor=tfKor.getText().isEmpty() ? 0 : Integer.parseInt(tfKor.getText().trim());
	    int eng=tfEng.getText().isEmpty() ? 0 : Integer.parseInt(tfEng.getText().trim());
	    int math=tfMath.getText().isEmpty() ? 0 : Integer.parseInt(tfMath.getText().trim());
	    
	    int total= kor+eng+ math;
	    double average = total/3D;
	    lblTotal2.setText(String.valueOf(total));
	    lblAvg2.setText(String.format("%.2f",average));
	}

	
    }

    //수정 시 학생번호 수정 불가능으로 만들기 
    public JTextField getTfNo() {
        return tfNo;
    }
    
    //학생 세부정보 확인시 모든 정보를 수정불가능하게 
    
    public void setEditableTf() {
	tfNo.setEditable(false);
	tfName.setEditable(false);
	tfKor.setEditable(false);
	tfEng.setEditable(false);
	tfMath.setEditable(false);
    }
}
