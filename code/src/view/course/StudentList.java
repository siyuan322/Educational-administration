/*
 * Created by JFormDesigner on Thu Dec 03 22:56:48 CST 2020
 */

package view.course;

import dao.TeacherDao;
import model.CourseByTeacher;
import model.Student;
import model.StudyTimeType;
import model.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

/**
 * @author 123
 */
public class StudentList extends JPanel {
    public StudentList(Teacher teacher) {
        this.teacher = teacher;
        this.teacherDao = new TeacherDao();

        initComponents();
        // 获取老师授课的所有课程
        List<CourseByTeacher> courseByTeachers = teacherDao.searchAllCourse(teacher.getTid());
        Vector<String> cnameList = new Vector<String>();
        cnameList.insertElementAt("请选择", 0);
        for (int i = 1; i <= courseByTeachers.size(); i++) {
            cnameList.insertElementAt(courseByTeachers.get(i - 1).getCname(), i);
        }
        cnameComboBox.setModel(new DefaultComboBoxModel((cnameList)));

        // 上课时间的选择
        studyTimeComboBox.setModel(new DefaultComboBoxModel(new StudyTimeType[]{
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
    }

    // 展示学生信息
    private void showStudentListPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取输入的信息
        String cname = cnameComboBox.getSelectedItem().toString();
        String studyTime = studyTimeComboBox.getSelectedItem().toString();

        // 两个均不能为空
        if ("请选择".equals(cname) || "请选择".equals(studyTime)) {
            JOptionPane.showMessageDialog(null, "请输入完整的查询信息！");
            return;
        }

        // 查询数据库
        int cid = teacherDao.searchCid(cname, teacher.getInstitute());
        List<Student> studentList = teacherDao.showStudents(cid, teacher.getTid(), studyTime);
        setTable(studentList);
    }

    // 表格展示
    private void setTable(List<Student> studentList) {
        // 1.先删除表中的所有数据
        DefaultTableModel tableModel = (DefaultTableModel) studentListTable.getModel();
        int rowCount = tableModel.getRowCount();
        //从表的末尾逐个删除行
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        // 3.添加到表格中
        // 让单元格文字居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        studentListTable.setDefaultRenderer(Object.class, renderer);
        // 设置行高
        studentListTable.setRowHeight(26);
        for (Student s : studentList) {
            Vector v = new Vector();
            v.add(s.getSid());
            v.add(s.getSname());
            tableModel.addRow(v);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        studyTimeComboBox = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        studentListTable = new JTable();
        cnameComboBox = new JComboBox();

        //======== this ========

        //---- label1 ----
        label1.setText("\u5b66 \u751f \u540d \u5355");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u4e0a\u8bfe\u65f6\u95f4\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- studyTimeComboBox ----
        studyTimeComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u67e5  \u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> showStudentListPerformed(e));

        //======== scrollPane1 ========
        {

            //---- studentListTable ----
            studentListTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            studentListTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u5b66\u53f7", "\u59d3\u540d"
                    }
            ) {
                boolean[] columnEditable = new boolean[]{
                        false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = studentListTable.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(1).setResizable(false);
            }
            scrollPane1.setViewportView(studentListTable);
        }

        //---- cnameComboBox ----
        cnameComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 767, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(416, 416, 416)
                                                                .addComponent(label1))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(119, 119, 119)
                                                                .addComponent(label2)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(cnameComboBox, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(label3)))
                                                .addGap(18, 18, 18)
                                                .addComponent(studyTimeComboBox, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                                                .addGap(58, 58, 58)
                                                .addComponent(button1)))
                                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(label1)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(label3)
                                        .addComponent(studyTimeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1)
                                        .addComponent(cnameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private Teacher teacher;
    private TeacherDao teacherDao;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox studyTimeComboBox;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable studentListTable;
    private JComboBox cnameComboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
