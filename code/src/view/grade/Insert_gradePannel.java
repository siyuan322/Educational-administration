/*
 * Created by JFormDesigner on Thu Nov 12 23:02:40 CST 2020
 */

package view.grade;

import dao.GradeDao;
import model.Study;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;


/**
 * @author 123
 */

public class Insert_gradePannel extends JPanel {
    public Insert_gradePannel() {
        initComponents();
    }

    public static void main(String[] args) {
        new Insert_gradePannel();
    }

    //插入成绩
    private void button1ActionPerformed(ActionEvent e) {
        /* TODO add your code here
         */
        Study st = new Study();
        String sid = textField1.getText();//学号
        st.setSid(sid);

        String temp = textField2.getText();//老师
        int tid = Integer.parseInt(temp);
        st.setTeachID(tid);

        String classno = textField4.getText();//班级
        st.setClid(classno);

        String temp2 = textField5.getText();//课程编号
        int cid = Integer.parseInt(temp2);
        st.setCid(cid);

        String cname = textField3.getText();//课程名称
        st.setCname(cname);

        String temp3 = textField8.getText();//平时成绩
        int normal = Integer.parseInt(temp3);
        st.setNormalGrade(normal);

        String temp4 = textField7.getText();//期末
        int term = Integer.parseInt(temp4);
        st.setTermGrade(term);

        String temp5 = textField6.getText();//最终
        int finalg = Integer.parseInt(temp5);
        st.setFinalGrade(finalg);
        GradeDao dao = new GradeDao();

        setTable(dao.insertGrade(st));
    }

    private void setTable(List<Study> studyList) {
        // 1.先删除表中的所有数据
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        int rowCount = tableModel.getRowCount();
        //从表的末尾逐个删除行
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        // 3.添加到表格中
        // 让单元格文字居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, renderer);
        // 设置行高
        table1.setRowHeight(26);
        for (Study c : studyList) {
            Vector v = new Vector();
            v.add(c.getSid());
            v.add(c.getTeachID());
            v.add(c.getClid());
            v.add(c.getCid());
            v.add(c.getCname());
            v.add(c.getNormalGrade());
            v.add(c.getTermGrade());
            v.add(c.getFinalGrade());
            tableModel.addRow(v);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        textField4 = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        textField5 = new JTextField();
        label5 = new JLabel();
        textField6 = new JTextField();
        label6 = new JLabel();
        textField7 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label7 = new JLabel();
        textField3 = new JTextField();
        label8 = new JLabel();
        textField8 = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(975, 675));
        setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label1 ----
        label1.setText("\u5b66\u751f\u5b66\u53f7");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label2 ----
        label2.setText("\u8001\u5e08\u7f16\u53f7");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label3 ----
        label3.setText("\u73ed\u7ea7\u7f16\u53f7");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label4 ----
        label4.setText("\u8bfe\u7a0b\u7f16\u53f7");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label5 ----
        label5.setText("\u671f\u672b\u6210\u7ee9");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label6 ----
        label6.setText("\u6700\u7ec8\u6210\u7ee9");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- button1 ----
        button1.setText("\u63d2\u5165");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        button1.addActionListener(e -> button1ActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u5b66\u53f7", "\u8001\u5e08\u7f16\u53f7", "\u73ed\u7ea7\u7f16\u53f7", "\u8bfe\u7a0b\u7f16\u53f7", "\u8bfe\u7a0b\u540d\u79f0", "\u5e73\u65f6\u6210\u7ee9", "\u671f\u672b\u6210\u7ee9", "\u6700\u7ec8\u6210\u7ee9"
                    }
            ));
            table1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            scrollPane1.setViewportView(table1);
        }

        //---- label7 ----
        label7.setText("\u8bfe\u7a0b\u540d\u79f0");
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label8 ----
        label8.setText("\u5e73\u65f6\u6210\u7ee9");
        label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(77, 77, 77)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(textField6, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(106, 106, 106)
                                                                .addComponent(button1))
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                                                        .addComponent(textField8, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup()
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                                        .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                                        .addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                                                                                .addComponent(label7, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createParallelGroup()
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                                .addGroup(layout.createParallelGroup()
                                                                                                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                                                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
                                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE))
                                                                                                        .addComponent(textField5, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addGap(10, 10, 10)
                                                                                        .addComponent(textField7, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 878, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textField8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(label6)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(textField6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addComponent(button1)
                                                .addGap(49, 49, 49)))
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addGap(186, 186, 186))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JTextField textField4;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField5;
    private JLabel label5;
    private JTextField textField6;
    private JLabel label6;
    private JTextField textField7;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label7;
    private JTextField textField3;
    private JLabel label8;
    private JTextField textField8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
