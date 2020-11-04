/*
 * Created by JFormDesigner on Sat Oct 24 21:51:15 CST 2020
 */

package view;

import dao.TeacherDao;
import model.StudyTimeType;
import model.Teach;
import model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * @author 123
 */
public class AddCourseByTeacher extends JPanel {
    public AddCourseByTeacher(Teacher teacher) {
        this.teacher = teacher;
        initComponents();
        descriptionTextArea1.setLineWrap(true);
        // 列出所有的课程名称
        Vector<String> cnameList = new TeacherDao().searchCourseByInstitute(teacher.getInstitute());
        cnameList.insertElementAt("请选择", 0);
        cnameComboBox1.setModel(new DefaultComboBoxModel((cnameList)));
        studyTimeComboBox2.setModel(new DefaultComboBoxModel(new StudyTimeType[]{
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

    // 添加课程
    private void submitCourseButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取课程名
        String cname = (String) cnameComboBox1.getSelectedItem();
        // 获取对应的课程号
        int cid = new TeacherDao().searchCid(cname, teacher.getInstitute());
        // 容量
        String capacitycmp = capacityTextField1.getText().trim();
        // 上课时间
        String studyTime = studyTimeComboBox2.getSelectedItem().toString();
        // 课程介绍
        String description = descriptionTextArea1.getText();

        // 信息完善
        if (cid == 0 || "".equals(capacitycmp) || studyTime.equals(StudyTimeType.None.toString()) || "".equals(description)) {
            JOptionPane.showMessageDialog(null, "请完善所有信息！");
            return;
        }

        int capacity = Integer.parseInt(capacitycmp);

        TeacherDao dao = new TeacherDao();
        // 查找该老师上课时间是否冲突
        boolean flag = dao.isConflict(teacher.getTid(), studyTime);
        if (flag) {
            JOptionPane.showMessageDialog(null, "您在该时间段已开设课程，请选择其余空闲时间！");
            return;
        }

        // 添加课程
        Teach teach = new Teach(cid, teacher.getTid(), capacity, 0, studyTime, description, teacher.getInstitute(), 1);
        int teachID = dao.addCourse(teach);
        if (teachID != 0) {
            JOptionPane.showMessageDialog(null, "添加成功，教学班编号为" + teachID + "，请等待教务员审核");
        } else {
            // 失败
            JOptionPane.showMessageDialog(null, "添加失败");
            return;
        }
        // 清空数据
        button2.doClick();
    }

    // 重置
    private void resetButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 把所有文本框置空
        cnameComboBox1.setSelectedIndex(0);
        capacityTextField1.setText("");
        studyTimeComboBox2.setSelectedIndex(0);
        descriptionTextArea1.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        cnameComboBox1 = new JComboBox();
        label4 = new JLabel();
        capacityTextField1 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        descriptionTextArea1 = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();
        studyTimeComboBox2 = new JComboBox();

        //======== this ========

        //---- label1 ----
        label1.setText("\u65b0 \u589e \u8bfe \u7a0b");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameComboBox1 ----
        cnameComboBox1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label4 ----
        label4.setText("\u5b66\u751f\u5bb9\u91cf\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- capacityTextField1 ----
        capacityTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label5 ----
        label5.setText("\u4e0a\u8bfe\u65f6\u95f4\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label6 ----
        label6.setText("\u8bfe\u7a0b\u4ecb\u7ecd\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //======== scrollPane1 ========
        {
            scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- descriptionTextArea1 ----
            descriptionTextArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            scrollPane1.setViewportView(descriptionTextArea1);
        }

        //---- button1 ----
        button1.setText("\u63d0   \u4ea4");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> submitCourseButton1ActionPerformed(e));

        //---- button2 ----
        button2.setText("\u91cd    \u7f6e");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> resetButton2ActionPerformed(e));

        //---- studyTimeComboBox2 ----
        studyTimeComboBox2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(277, 277, 277)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                                                .addGap(112, 112, 112)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4)
                                                        .addComponent(label5)
                                                        .addComponent(label6))
                                                .addGap(62, 62, 62)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cnameComboBox1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addComponent(label1))
                                                        .addComponent(capacityTextField1, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(studyTimeComboBox2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))))
                                .addContainerGap(268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(label1)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cnameComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(capacityTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studyTimeComboBox2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1)
                                        .addComponent(button2))
                                .addGap(50, 50, 50))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private Teacher teacher;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JComboBox cnameComboBox1;
    private JLabel label4;
    private JTextField capacityTextField1;
    private JLabel label5;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextArea descriptionTextArea1;
    private JButton button1;
    private JButton button2;
    private JComboBox studyTimeComboBox2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
