/*
 * Created by JFormDesigner on Wed Nov 04 09:33:49 CST 2020
 */

package view.course;

import dao.StudentDao;
import model.CourseByStudent;
import model.Student;
import model.Study;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

/**
 * @author 123
 */
public class ChooseCourse extends JPanel {
    public ChooseCourse(Student student) {
        this.student = student;
        initComponents();
        this.resizeTable();
        showAllCourse();
    }

    // 搜索课程
    private void searchButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String cname = cnameTextField1.getText().trim();
        int capacity = capacityComboBox1.getSelectedIndex();
        System.out.println(capacity);
        String institute = (String) instituteComboBox2.getSelectedItem();

        List<CourseByStudent> courseList = new StudentDao().search(0, cname, capacity, institute);
        this.setTable(courseList);
    }

    // 选中一门课程
    private void selectCourseMouseClicked(MouseEvent e) {
        // TODO add your code here
        DefaultTableModel tableModel = (DefaultTableModel) courseListTable.getModel();
        StudentDao dao = new StudentDao();
        int row = courseListTable.rowAtPoint(e.getPoint());
        // 获取教学班编号
        int teachID = (int) tableModel.getValueAt(row, 0);
        CourseByStudent course = dao.search(teachID, "", 0, "").get(0);

        // 把数据显示在下面的文本框中
        teachIDTextField2.setText(teachID + "");
        editInstituteTextField3.setText(course.getInstitute());
        chourTextField4.setText(course.getChour());
        ccreditTextField5.setText(course.getCcredit() + "");
        descriptionTextArea1.setText(course.getDescription());

        Study study = dao.ifChooseCourse(student.getSid(), teachID);
        if (study != null) {
            // 学生已选择这门课
            button2.setEnabled(false);
            button3.setEnabled(true);
        } else {
            // 学生还没有选择这门课
            button3.setEnabled(false);
            button2.setEnabled(true);
        }
    }

    // 选择一门课
    private void chooseCourseActionPerformed(ActionEvent e) {
        // TODO add your code here
        int teachID = Integer.parseInt(teachIDTextField2.getText());
        if (new StudentDao().chooseCourse(student.getSid(), teachID)) {
            JOptionPane.showMessageDialog(null, "选课成功");
        } else {
            JOptionPane.showMessageDialog(null, "已满员，选课失败！");
        }
    }

    // 退选一门课
    private void cancelCourseActionPerformed(ActionEvent e) {
        // TODO add your code here
        int teachID = Integer.parseInt(teachIDTextField2.getText());
        if (new StudentDao().cancelCourse(student.getSid(), teachID)) {
            JOptionPane.showMessageDialog(null, "退课成功！");
        } else {
            JOptionPane.showMessageDialog(null, "退课失败！");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        cnameTextField1 = new JTextField();
        label3 = new JLabel();
        capacityComboBox1 = new JComboBox<>();
        label4 = new JLabel();
        instituteComboBox2 = new JComboBox<>();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        courseListTable = new JTable();
        label5 = new JLabel();
        teachIDTextField2 = new JTextField();
        label6 = new JLabel();
        editInstituteTextField3 = new JTextField();
        label7 = new JLabel();
        chourTextField4 = new JTextField();
        label8 = new JLabel();
        ccreditTextField5 = new JTextField();
        label9 = new JLabel();
        scrollPane2 = new JScrollPane();
        descriptionTextArea1 = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText("\u5b66  \u751f  \u9009  \u8bfe");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameTextField1 ----
        cnameTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u662f\u5426\u6709\u4f59\u91cf\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- capacityComboBox1 ----
        capacityComboBox1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        capacityComboBox1.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u8bf7\u9009\u62e9",
                "\u662f",
                "\u5426"
        }));

        //---- label4 ----
        label4.setText("\u5b66\u9662\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- instituteComboBox2 ----
        instituteComboBox2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        instituteComboBox2.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u8bf7\u9009\u62e9",
                "\u8ba1\u7b97\u673a\u79d1\u5b66\u4e0e\u5de5\u7a0b\u5b66\u9662",
                "\u6570\u5b66\u5b66\u9662",
                "\u9a6c\u514b\u601d\u4e3b\u4e49\u5b66\u9662"
        }));

        //---- button1 ----
        button1.setText("\u67e5 \u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> searchButton1ActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- courseListTable ----
            courseListTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u6559\u5b66\u73ed\u7f16\u53f7", "\u8bfe\u7a0b\u540d", "\u6559\u5e08\u540d", "\u5bb9\u91cf", "\u73b0\u6709\u4eba\u6570", "\u4e0a\u8bfe\u65f6\u95f4", "\u5f00\u8bfe\u5b66\u9662"
                    }
            ) {
                boolean[] columnEditable = new boolean[]{
                        false, false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = courseListTable.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(1).setResizable(false);
                cm.getColumn(2).setResizable(false);
                cm.getColumn(3).setResizable(false);
                cm.getColumn(4).setResizable(false);
                cm.getColumn(5).setResizable(false);
                cm.getColumn(6).setResizable(false);
            }
            courseListTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            courseListTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectCourseMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(courseListTable);
        }

        //---- label5 ----
        label5.setText("\u6559\u5b66\u73ed\u7f16\u53f7\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- teachIDTextField2 ----
        teachIDTextField2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        teachIDTextField2.setEditable(false);

        //---- label6 ----
        label6.setText("\u5f00\u8bfe\u5b66\u9662\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editInstituteTextField3 ----
        editInstituteTextField3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        editInstituteTextField3.setEditable(false);

        //---- label7 ----
        label7.setText("\u5b66\u65f6\uff1a");
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- chourTextField4 ----
        chourTextField4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        chourTextField4.setEditable(false);

        //---- label8 ----
        label8.setText("\u5b66\u5206\uff1a");
        label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- ccreditTextField5 ----
        ccreditTextField5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        ccreditTextField5.setEditable(false);

        //---- label9 ----
        label9.setText("\u8bfe\u7a0b\u4ecb\u7ecd\uff1a");
        label9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //======== scrollPane2 ========
        {

            //---- descriptionTextArea1 ----
            descriptionTextArea1.setEditable(false);
            descriptionTextArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            scrollPane2.setViewportView(descriptionTextArea1);
        }

        //---- button2 ----
        button2.setText("\u9009    \u8bfe");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> chooseCourseActionPerformed(e));

        //---- button3 ----
        button3.setText("\u9000    \u9009");
        button3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button3.addActionListener(e -> cancelCourseActionPerformed(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 887, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addGap(18, 18, 18)
                                                .addComponent(cnameTextField1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label3)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(capacityComboBox1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(41, 41, 41)
                                                                .addComponent(label4))
                                                        .addComponent(label1))
                                                .addGap(14, 14, 14)
                                                .addComponent(instituteComboBox2, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label5)
                                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                                                .addGap(47, 47, 47)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(teachIDTextField2, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(chourTextField4, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(ccreditTextField5, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(65, 65, 65)
                                                                                .addGroup(layout.createParallelGroup()
                                                                                        .addComponent(label6)
                                                                                        .addComponent(label9)))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(81, 81, 81)
                                                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(28, 28, 28)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(scrollPane2)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup()
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(editInstituteTextField3, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(label7))
                                                                                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(0, 0, Short.MAX_VALUE)))))))
                                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(label1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(cnameTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(capacityComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(instituteComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label7)
                                        .addComponent(label6)
                                        .addComponent(label5)
                                        .addComponent(editInstituteTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chourTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(teachIDTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label8)
                                        .addComponent(label9)
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ccreditTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button3)
                                        .addComponent(button2))
                                .addGap(26, 26, 26))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // 展示所有课程
    private void showAllCourse() {
        // 获取所有课程
        StudentDao dao = new StudentDao();
        List<CourseByStudent> courseList = dao.search(0, "", 0, "请选择");
        this.setTable(courseList);
    }

    // 表格展示
    private void setTable(List<CourseByStudent> courseList) {
        // 1.先删除表中的所有数据
        DefaultTableModel tableModel = (DefaultTableModel) courseListTable.getModel();
        int rowCount = tableModel.getRowCount();
        //从表的末尾逐个删除行
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        // 3.添加到表格中
        // 让单元格文字居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        courseListTable.setDefaultRenderer(Object.class, renderer);
        // 设置行高
        courseListTable.setRowHeight(26);
        for (CourseByStudent c : courseList) {
            Vector v = new Vector();
            v.add(c.getTeachID());
            v.add(c.getCname());
            v.add(c.getTname());
            v.add(c.getCapacity());
            v.add(c.getCurrentNum());
            v.add(c.getStudyTime());
            v.add(c.getInstitute());
            tableModel.addRow(v);
        }
    }

    // 设置列宽
    public void resizeTable() {
        courseListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        courseListTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        courseListTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        courseListTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        courseListTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(6).setPreferredWidth(300);
    }


    private Student student;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField cnameTextField1;
    private JLabel label3;
    private JComboBox<String> capacityComboBox1;
    private JLabel label4;
    private JComboBox<String> instituteComboBox2;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable courseListTable;
    private JLabel label5;
    private JTextField teachIDTextField2;
    private JLabel label6;
    private JTextField editInstituteTextField3;
    private JLabel label7;
    private JTextField chourTextField4;
    private JLabel label8;
    private JTextField ccreditTextField5;
    private JLabel label9;
    private JScrollPane scrollPane2;
    private JTextArea descriptionTextArea1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
