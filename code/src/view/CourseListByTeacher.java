/*
 * Created by JFormDesigner on Sun Oct 25 09:28:07 CST 2020
 */

package view;

import dao.TeacherDao;
import model.AuditType;
import model.CourseByTeacher;
import model.StudyTimeType;
import model.Teacher;

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
public class CourseListByTeacher extends JPanel {
    public CourseListByTeacher(Teacher teacher) {
        this.teacher = teacher;
        initComponents();
        resizeTable();
        isAuditcomboBox1.setModel(new DefaultComboBoxModel(new AuditType[]{
                AuditType.NONE,
                AuditType.UNDO,
                AuditType.PASSED,
                AuditType.UNPASSED,
        }));
        editStudyTimeComboBox2.setModel(new DefaultComboBoxModel(new StudyTimeType[]{
                StudyTimeType.None,
                StudyTimeType.MonMor,
                StudyTimeType.MonAfter,
                StudyTimeType.TueMor,
                StudyTimeType.TueAfter,
                StudyTimeType.WedMor,
                StudyTimeType.WedAfter,
                StudyTimeType.ThurMor,
                StudyTimeType.ThurAfter,
                StudyTimeType.FriMor,
                StudyTimeType.FriAfter,
        }));
        showAllCourse();
    }

    // 按条件查询
    private void searchCourseButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取用户输入的数据
        String cname = cnameTextField1.getText().trim();
        int isAudit = isAuditcomboBox1.getSelectedIndex();

        List<CourseByTeacher> courseList = new TeacherDao().searchSomeCourse(teacher.getTid(), cname, isAudit);
        setTable(courseList);
    }

    // 选中其中一门课
    private void selectOneCourseMouseClicked(MouseEvent e) {
        // TODO add your code here
        DefaultTableModel tableModel = (DefaultTableModel) courseListTable.getModel();
        TeacherDao dao = new TeacherDao();
        int row = courseListTable.rowAtPoint(e.getPoint());
        // 获取教学班编号
        int teachID = (int) tableModel.getValueAt(row, 0);
        CourseByTeacher teach = dao.searchByTeachID(teachID);

        // 把数据显示在下面的文本框中
        teachIDTextField1.setText(teachID + "");
        editCnameTextField2.setText(teach.getCname());
        editCapacityTextField3.setText(teach.getCapacity() + "");
        editStudyTimeComboBox2.setSelectedIndex(StudyTimeType.getIndex(teach.getStudyTime()));
        editDescriptionTextArea1.setText(teach.getDescription());
    }

    // 修改课程
    private void updateCourseButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取输入的所有值
        int teachID = Integer.parseInt(teachIDTextField1.getText());
        String cname = editCnameTextField2.getText();
        String capacityTmp = editCapacityTextField3.getText().trim();
        String studyTime = editStudyTimeComboBox2.getSelectedItem().toString();
        String description = editDescriptionTextArea1.getText().trim();

        // 信息完善
        if ("".equals(capacityTmp) || "".equals(description)) {
            JOptionPane.showMessageDialog(null, "请输入完整的信息进行修改！");
            return;
        }
        int capacity = Integer.parseInt(capacityTmp);

        // 获取课程信息
        TeacherDao dao = new TeacherDao();
        CourseByTeacher teach = dao.searchByTeachID(teachID);

        // 审核通过则不允许进行操作
        if (teach.getIsAudit() == 2) {
            JOptionPane.showMessageDialog(null, "该课程已审核通过，不能再进行修改");
            return;
        }

        // 时间是否冲突
        boolean flag = dao.isConflict(teacher.getTid(), studyTime);
        if (flag) {
            JOptionPane.showMessageDialog(null, "修改失败！您在该时间段已开设其他课程，请更改其他时间！");
            return;
        }

        // 更新课程
        if (dao.updateCourse(teachID, capacity, studyTime, description, 1)) {
            JOptionPane.showMessageDialog(null, "修改成功！");
            this.showAllCourse();
            return;
        } else {
            JOptionPane.showMessageDialog(null, "修改失败！");
        }

    }

    // 删除课程
    private void deleteCourseButton3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取教学班编号
        int teachID = Integer.parseInt(teachIDTextField1.getText());
        // 查找该课程
        TeacherDao dao = new TeacherDao();
        CourseByTeacher teach = dao.searchByTeachID(teachID);
        // 审核通过则不允许进行操作
        if (teach.getIsAudit() == 2) {
            JOptionPane.showMessageDialog(null, "该课程已审核通过，不能再进行修改");
            return;
        }

        // 删除课程
        if (dao.deleteCourse(teachID)) {
            JOptionPane.showMessageDialog(null, "删除成功！");
            this.showAllCourse();
            editCnameTextField2.setText("");
            editCapacityTextField3.setText("");
            editStudyTimeComboBox2.setSelectedIndex(0);
            editDescriptionTextArea1.setText("");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "删除失败！");
            return;
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        cnameTextField1 = new JTextField();
        label3 = new JLabel();
        isAuditcomboBox1 = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        courseListTable = new JTable();
        label4 = new JLabel();
        editCnameTextField2 = new JTextField();
        label5 = new JLabel();
        editCapacityTextField3 = new JTextField();
        label6 = new JLabel();
        editStudyTimeComboBox2 = new JComboBox();
        label7 = new JLabel();
        scrollPane2 = new JScrollPane();
        editDescriptionTextArea1 = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();
        label8 = new JLabel();
        teachIDTextField1 = new JTextField();

        //======== this ========

        //---- label1 ----
        label1.setText("\u6240 \u6709 \u8bfe \u7a0b");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameTextField1 ----
        cnameTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u5ba1\u6838\u72b6\u6001\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- isAuditcomboBox1 ----
        isAuditcomboBox1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u67e5    \u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> searchCourseButton1ActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- courseListTable ----
            courseListTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u6559\u5b66\u73ed\u7f16\u53f7", "\u8bfe\u7a0b\u540d", "\u5b66\u65f6", "\u5b66\u5206", "\u5b66\u751f\u5bb9\u91cf", "\u73b0\u6709\u4eba\u6570", "\u4e0a\u8bfe\u65f6\u95f4", "\u5ba1\u6838\u72b6\u6001"
                    }
            ) {
                boolean[] columnEditable = new boolean[]{
                        false, false, false, false, false, false, false, false
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
                cm.getColumn(7).setResizable(false);
            }
            courseListTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            courseListTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectOneCourseMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(courseListTable);
        }

        //---- label4 ----
        label4.setText("\u8bfe\u7a0b\u540d\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editCnameTextField2 ----
        editCnameTextField2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        editCnameTextField2.setEditable(false);

        //---- label5 ----
        label5.setText("\u5b66\u751f\u5bb9\u91cf\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editCapacityTextField3 ----
        editCapacityTextField3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label6 ----
        label6.setText("\u4e0a\u8bfe\u65f6\u95f4\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editStudyTimeComboBox2 ----
        editStudyTimeComboBox2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setText("\u8bfe\u7a0b\u4ecb\u7ecd\uff1a");
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //======== scrollPane2 ========
        {

            //---- editDescriptionTextArea1 ----
            editDescriptionTextArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            scrollPane2.setViewportView(editDescriptionTextArea1);
        }

        //---- button2 ----
        button2.setText("\u786e \u8ba4 \u4fee \u6539");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> updateCourseButton2ActionPerformed(e));

        //---- button3 ----
        button3.setText("\u5220   \u9664");
        button3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button3.addActionListener(e -> deleteCourseButton3ActionPerformed(e));

        //---- label8 ----
        label8.setText("\u6559\u5b66\u73ed\u7f16\u53f7\uff1a");
        label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- teachIDTextField1 ----
        teachIDTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        teachIDTextField1.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(label8)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(teachIDTextField1, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(editStudyTimeComboBox2, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label4)
                                                        .addComponent(label7)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(289, Short.MAX_VALUE)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(button3, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(scrollPane2)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(editCnameTextField2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(52, 52, 52)
                                                                .addComponent(label5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(editCapacityTextField3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(58, 58, 58))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(425, 425, 425)
                                                .addComponent(label1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(128, 128, 128)
                                                .addComponent(label2)
                                                .addGap(18, 18, 18)
                                                .addComponent(cnameTextField1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(label3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(isAuditcomboBox1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                .addGap(55, 55, 55)
                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 912, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(label1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(cnameTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(isAuditcomboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label8)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(teachIDTextField1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editCapacityTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label5)
                                        .addComponent(editCnameTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(editStudyTimeComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label7))
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button3)
                                        .addComponent(button2))
                                .addGap(31, 31, 31))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // 展示所有课程
    private void showAllCourse() {
        // 获取所有课程
        TeacherDao dao = new TeacherDao();
        List<CourseByTeacher> courseList = dao.searchAllCourse(teacher.getTid());
        this.setTable(courseList);
    }

    // 表格展示
    private void setTable(List<CourseByTeacher> courseList) {
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
        for (CourseByTeacher c : courseList) {
            Vector v = new Vector();
            v.add(c.getTeachID());
            v.add(c.getCname());
            v.add(c.getChour());
            v.add(c.getCcredit());
            v.add(c.getCapacity());
            v.add(c.getCurrentNum());
            v.add(c.getStudyTime());
            v.add(AuditType.getName(c.getIsAudit()));
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
        courseListTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        courseListTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(7).setPreferredWidth(150);
    }

    private Teacher teacher;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField cnameTextField1;
    private JLabel label3;
    private JComboBox isAuditcomboBox1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable courseListTable;
    private JLabel label4;
    private JTextField editCnameTextField2;
    private JLabel label5;
    private JTextField editCapacityTextField3;
    private JLabel label6;
    private JComboBox editStudyTimeComboBox2;
    private JLabel label7;
    private JScrollPane scrollPane2;
    private JTextArea editDescriptionTextArea1;
    private JButton button2;
    private JButton button3;
    private JLabel label8;
    private JTextField teachIDTextField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
