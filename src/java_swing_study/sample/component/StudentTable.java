package java_swing_study.sample.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java_swing_study.sample.dto.Student;

@SuppressWarnings("serial")
public class StudentTable extends JTable {
    private ArrayList<Student> items;
    private CustomModel model;
    
    public StudentTable() {
        initComponents();
    }
    
    public StudentTable(ArrayList<Student> items) {
        setItems(items); 
        initComponents();
    }

    public void setItems(ArrayList<Student> items) {
        this.items = items;
        loadData();
    }

    private void loadData() {
        model = new CustomModel(getRows(), getColNames());
        setModel(model);
     // 학생번호, 성명, 국어, 영어, 수학, 총점, 평균   
      //컬럼 별 폭 설정
        tableSetWith(100, 150,100, 100, 100, 120, 120);
        //셀 별 정렬 
        tableCellAlign(SwingConstants.CENTER, 0, 1);
        tableCellAlign(SwingConstants.RIGHT, 2,3,4,5,6);
      /*DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
      dtcr.setHorizontalAlignment(SwingConstants.CENTER);
      
      TableColumnModel cModel = getColumnModel();
      cModel.getColumn(0).setCellRenderer(dtcr);
      cModel.getColumn(1).setCellRenderer(dtcr);
      
      
      DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
      dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
      cModel.getColumn(2).setCellRenderer(dtcr2);
      cModel.getColumn(3).setCellRenderer(dtcr2);
      cModel.getColumn(4).setCellRenderer(dtcr2);
      cModel.getColumn(5).setCellRenderer(dtcr2);
      cModel.getColumn(6).setCellRenderer(dtcr2);
      */    
        }

    private void tableCellAlign(int align, int...idx) {
       DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
       dtcr.setHorizontalAlignment(align);
       //idx[0,1], idx[2,3,4,5,6]
       TableColumnModel cModel = getColumnModel();
       for(int i=0; i<idx.length; i++) {
          cModel.getColumn(i).setCellRenderer(dtcr);
       }
    }
    private void tableSetWith(int...width) {//가변인수 배열로 받음..
       TableColumnModel cModel = getColumnModel();
       for(int i=0; i<width.length; i++) {
          cModel.getColumn(i).setPreferredWidth(width[i]);
       }
    }
    

   //ArrayList<Student> 의 내용을 보여주도록 변경
    private Object[][] getRows() {
        Object[][] rows = new Object[items.size()][];
        for(int i=0; i<rows.length; i++) {
            rows[i] = toArray(items.get(i));
        }
        return rows;
    }
    
    private Object[] toArray(Student std) {
        return new Object[] {std.getNo(), std.getName(), std.getKor(), std.getEng(),
                std.getMath(), std.getTotal(), std.getAverage()};
    }
    
    private String[] getColNames() {
        return new String[] {
            "번호", "성명", "국어", "영어", "수학", "총점", "평균"
        };
    }
    
    private void initComponents() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void removeRow(int idx) {
        model.removeRow(idx);
    }
    
    public void updateRow(int idx, Student item) {
        model.removeRow(idx);
        model.insertRow(idx, toArray(item));
    }
    
    public void addRow(Student item) {
        model.addRow(toArray(item));
    }
    
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
       Component comp = super.prepareRenderer(renderer, row, column);
 //    System.out.printf("row : %d column : %d value : %s%n", row, column, getValueAt(row, column));
       double value = (double) getValueAt(row, 6);
       if(value >= 90) {
          comp.setBackground(Color.CYAN);
       }else {
          comp.setBackground(Color.WHITE);
       }
       // TODO Auto-generated method stub
       super.prepareRenderer(renderer, row, column);
       
       return comp;
    }
    
     
    private class CustomModel extends DefaultTableModel{

        public CustomModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }
        
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        
    }
}