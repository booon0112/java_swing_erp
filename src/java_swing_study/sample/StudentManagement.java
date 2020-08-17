package java_swing_study.sample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java_swing_study.sample.component.StudentPanel;
import java_swing_study.sample.component.StudentTable;
import java_swing_study.sample.dto.Student;
import java_swing_study.sample.exception.EmptyTfException;
import java_swing_study.sample.exception.NotSelectedRowException;

@SuppressWarnings("serial")
public class StudentManagement extends JFrame implements ActionListener {

    private JPanel contentPane;
    private StudentPanel pStudent;
    private JPanel pBtns;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel pList;
    private JScrollPane scrollPane;
    private StudentTable table;
//    private DefaultTableModel model;

    private ArrayList<Student> stdList = new ArrayList<Student>();
    
    public StudentManagement() {
        stdList.add(new Student(1, "김대현", 90, 80, 71));
        stdList.add(new Student(2, "문중희", 91, 84, 72));
        stdList.add(new Student(3, "윤한석", 92, 84, 73));
        stdList.add(new Student(4, "김창동", 93, 85, 74));
        
        initComponents();
        //StudentPanel 테스트
        Student testStd = new Student(5, "이지수", 90, 80, 70);
        pStudent.setStudent(testStd);
        
    }
    
    private void initComponents() {
        setTitle("학생관리프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 455);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        pStudent = new StudentPanel();
        contentPane.add(pStudent);
        
        pBtns = new JPanel();
        contentPane.add(pBtns);
        
        btnAdd = new JButton("추가");
        btnAdd.addActionListener(this);
        pBtns.add(btnAdd);
        
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(this);
        pBtns.add(btnCancel);
        
        pList = new JPanel();
        contentPane.add(pList);
        pList.setLayout(new BorderLayout(0, 0));
        
        scrollPane = new JScrollPane();
        pList.add(scrollPane, BorderLayout.CENTER);
        
        table = new StudentTable();
        table.setComponentPopupMenu(createPopupMenu());
        table.setItems(stdList);
        scrollPane.setViewportView(table);
    }//end of initComponent
    
    //jPopUpMenu 바로가기 메뉴생성
    private JPopupMenu createPopupMenu() {
        JPopupMenu popMenu = new JPopupMenu();
        
        JMenuItem updateMenu = new JMenuItem("수정");
        updateMenu.addActionListener(menuListener);
        JMenuItem deleteMenu = new JMenuItem("삭제");
        deleteMenu.addActionListener(menuListener);
        JMenuItem showDetailMenu = new JMenuItem("학생 정보");
        showDetailMenu.addActionListener(menuListener);
        
        popMenu.add(updateMenu);
        popMenu.add(deleteMenu);
        popMenu.add(showDetailMenu);
        return popMenu;
    }
   
    ActionListener menuListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedMenu = e.getActionCommand();
            try {
                if (selectedMenu.equals("수정")) {
                    updateStudent();
                }
                if (selectedMenu.equals("삭제")) {
                    deleteStudent();
                }
                if (selectedMenu.equals("학생 정보")) {
                    showStudentDetail();
                }
            }catch (NotSelectedRowException ee) {
                JOptionPane.showMessageDialog(null, ee.getMessage());
                ee.printStackTrace();
            }
        }


    };


    
    
    protected void showStudentDetail() {
        int selectedRowIndex = table.getSelectedRow();
        
        if (selectedRowIndex == -1) {
            throw new NotSelectedRowException("해당 학생을 선택하세요.");
        }
        Student showStd = stdList.get(selectedRowIndex);
        
        StudentDetailDlg dlg = new StudentDetailDlg();
        dlg.setStudent(showStd);
        dlg.setTfNotEditable();
        dlg.setVisible(true);
    }

    private void deleteStudent() {
        int selectedRowIndex = table.getSelectedRow();
        
        if (selectedRowIndex == -1) {
            throw new NotSelectedRowException("해당 학생을 선택하세요.");
        }
        
        //1 strList 삭제
        stdList.remove(selectedRowIndex);
        //2.model 선택된 학생 삭제
        table.removeRow(selectedRowIndex);
//        model.removeRow(selectedRowIndex);
        //3. 메시지띄우기
        JOptionPane.showMessageDialog(null, "삭제 되었습니다");
    }
    
    protected void updateStudent() {
        //1. table에서 몇번째 선택했는지를 검색
        int selectedRowIndex = table.getSelectedRow();
        System.out.println("selectedRowIndex = " + selectedRowIndex);
        
        if (selectedRowIndex == -1) {
            throw new NotSelectedRowException("해당 학생을 선택하세요.");
            /*            System.out.println("해당 학생을 선택하세요.");
            return;*/
        }
        //2. Arrylist해당 위치에 있는 내용을 참조
//        System.out.println(stdList.get(selectedRowIndex));
        Student updateStd = stdList.get(selectedRowIndex);
        //3. 참조된 학생정보를 pStudent.setStudent();
        pStudent.setStudent(updateStd);
        pStudent.getTfNo().setEditable(false);
        btnAdd.setText("수정");
    }


    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnCancel) {
                actionPerformedBtnCancel(e);
            }
            if (e.getSource() == btnAdd) {
                if (e.getActionCommand().contentEquals("추가")) {
                    actionPerformedBtnAdd(e);
                }else {
                    actionPerformedBtnUpdate();
                }
            }
        }catch (EmptyTfException ee) {
            JOptionPane.showMessageDialog(null, ee.getMessage());
            ee.printStackTrace();
        }
    }
    
    private void actionPerformedBtnUpdate() {
        //1. pStudent의 수정된 내용을 getStudent() 가져옴
        Student updatedStd = pStudent.getStudent();
//        System.out.println(updatedStd);
        //2. ArrayList에 존재하고 있는 수정된 Student로 변경
//        System.out.println(stdList);
        int selIdx = stdList.indexOf(updatedStd);
        stdList.set(selIdx, updatedStd);
//        System.out.println(stdList);
        //3. model 변경
        table.updateRow(selIdx, updatedStd);
//        System.out.println(model.getValueAt(0, 1));
//        model.removeRow(selIdx);
//        model.insertRow(selIdx, toArray(updatedStd));
        //4. pStudent clearTf()
        pStudent.clearTf();
        //4.5 tfNo를 수정가능하게 변경
        pStudent.getTfNo().setEditable(true);
        //5. btnAdd 텍스트를 "추가"로 변경
        btnAdd.setText("추가");
        //6. 메시지 띄우기
        JOptionPane.showMessageDialog(null, "수정 완료");
    }

    protected void actionPerformedBtnAdd(ActionEvent e) {
        Student newStd = pStudent.getStudent();
        //1. stdList추가
        //2. model 추가
        //3. 추가완료 알려
        stdList.add(newStd);
        table.addRow(newStd);
        
        JOptionPane.showMessageDialog(null, newStd + "를 추가 완료");
        pStudent.clearTf();
    }
    
    protected void actionPerformedBtnCancel(ActionEvent e) {
        pStudent.clearTf();
        pStudent.getTfNo().setEditable(true);
        if (btnAdd.getText().equals("수정")) {
            btnAdd.setText("추가");
        }
    }
}





