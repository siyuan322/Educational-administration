/*
 * Created by JFormDesigner on Sun Oct 25 18:51:37 CST 2020
 */

package view.course;

import dao.AcdemicDeanDao;
import model.AcdemicDean;
import model.AuditType;
import model.CourseByAD;

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
public class AuditByAD extends JPanel {
    public AuditByAD(AcdemicDean dean) {
        this.dean = dean;
        initComponents();
        isAuditComboBox1.setModel(new DefaultComboBoxModel(new AuditType[]{
                AuditType.NONE,
                AuditType.UNDO,
                AuditType.PASSED,
                AuditType.UNPASSED,
        }));
        this.resizeTable();
        this.showAllCourse();
    }

    // 根据条件查询课程
    private void selectCourseButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        int isAudit = isAuditComboBox1.getSelectedIndex();
        List<CourseByAD> courseList = new AcdemicDeanDao().searchAllCourse(isAudit, dean.getInstitude());

        setTable(courseList);
    }

    // 选择一门课程
    private void selectOneCourseMouseClicked(MouseEvent e) {
        DefaultTableModel tableModel = (DefaultTableModel) courseListTable.getModel();
        AcdemicDeanDao dao = new AcdemicDeanDao();
        int row = courseListTable.rowAtPoint(e.getPoint());
        int teachID = (int) tableModel.getValueAt(row, 0);

        CourseByAD course = new AcdemicDeanDao().searchByTeachID(teachID);
        // 把数据显示在下面的文本框中
        teachIDTextField1.setText(course.getTeachID() + "");
        tnameTextField2.setText(course.getTname());
        cnameTextField3.setText(course.getCname());
        descriptionTextArea1.setText(course.getDescription());
    }

    // 审核通过
    private void passButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (changeAudit(2)) {
            JOptionPane.showMessageDialog(null, "审核通过！");
            this.showAllCourse();
        } else {
            JOptionPane.showMessageDialog(null, "该课程已审核通过，不能再进行修改！");
        }
    }

    // 审核不通过
    private void UnpassButton3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (changeAudit(3)) {
            JOptionPane.showMessageDialog(null, "不通过！");
            this.showAllCourse();
        } else {
            JOptionPane.showMessageDialog(null, "该课程已审核通过，不能再进行修改！");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        isAuditComboBox1 = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        courseListTable = new JTable();
        label6 = new JLabel();
        scrollPane2 = new JScrollPane();
        descriptionTextArea1 = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();
        label3 = new JLabel();
        teachIDTextField1 = new JTextField();
        label4 = new JLabel();
        tnameTextField2 = new JTextField();
        label5 = new JLabel();
        cnameTextField3 = new JTextField();

        //======== this ========

        //---- label1 ----
        label1.setText("\u8bfe \u7a0b \u5ba1 \u6838");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u5ba1\u6838\u72b6\u6001\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- isAuditComboBox1 ----
        isAuditComboBox1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u67e5    \u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> selectCourseButton1ActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- courseListTable ----
            courseListTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u6559\u5b66\u73ed\u7f16\u53f7", "\u804c\u5de5\u53f7", "\u8001\u5e08\u59d3\u540d", "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u5b66\u751f\u5bb9\u91cf", "\u4e0a\u8bfe\u65f6\u95f4", "\u5ba1\u6838\u72b6\u6001"
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

        //---- label6 ----
        label6.setText("\u8bfe\u7a0b\u63cf\u8ff0\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //======== scrollPane2 ========
        {

            //---- descriptionTextArea1 ----
            descriptionTextArea1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            descriptionTextArea1.setEditable(false);
            scrollPane2.setViewportView(descriptionTextArea1);
        }

        //---- button2 ----
        button2.setText("\u901a    \u8fc7");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> passButton2ActionPerformed(e));

        //---- button3 ----
        button3.setText("\u4e0d  \u901a  \u8fc7");
        button3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button3.addActionListener(e -> UnpassButton3ActionPerformed(e));

        //---- label3 ----
        label3.setText("\u6559\u5b66\u73ed\u7f16\u53f7\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- teachIDTextField1 ----
        teachIDTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        teachIDTextField1.setEditable(false);

        //---- label4 ----
        label4.setText("\u6559\u5e08\u59d3\u540d\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- tnameTextField2 ----
        tnameTextField2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        tnameTextField2.setEditable(false);

        //---- label5 ----
        label5.setText("\u8bfe\u7a0b\u540d\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameTextField3 ----
        cnameTextField3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        cnameTextField3.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 911, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label3)
                                                        .addComponent(label5))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup()
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(teachIDTextField1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                                                        .addComponent(cnameTextField3, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(label4)
                                                                        .addComponent(label6))
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(tnameTextField2, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(121, 121, 121)
                                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(91, 91, 91)
                                                                .addComponent(button3, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(label2)
                                .addGap(50, 50, 50)
                                .addComponent(isAuditComboBox1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(474, Short.MAX_VALUE)
                                .addComponent(label1)
                                .addGap(414, 414, 414))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(label1)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(isAuditComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(teachIDTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label4)
                                        .addComponent(tnameTextField2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(label5)
                                                .addComponent(label6)
                                                .addComponent(cnameTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(button2))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(button3)))
                                .addGap(22, 22, 22))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // 审核状态的改变
    private boolean changeAudit(int isAudit) {

        int teachID = Integer.parseInt(teachIDTextField1.getText());
        AcdemicDeanDao dao = new AcdemicDeanDao();
        CourseByAD course = dao.searchByTeachID(teachID);
        // 已审核通过，则不能再进行修改
        if (course.getIsAudit() == 2) {
            return false;
        }

        return new AcdemicDeanDao().updateAudit(teachID, isAudit);
    }

    // 展示所有数据
    private void showAllCourse() {
        // 获取所有课程
        AcdemicDeanDao dao = new AcdemicDeanDao();
        List<CourseByAD> courseList = dao.searchAllCourse(0, dean.getInstitude());
        this.setTable(courseList);
    }


    // 表格展示
    private void setTable(List<CourseByAD> courseList) {
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
        for (CourseByAD c : courseList) {
            Vector v = new Vector();
            v.add(c.getTeachID());
            v.add(c.getTid());
            v.add(c.getTname());
            v.add(c.getCid());
            v.add(c.getCname());
            v.add(c.getCapacity());
            v.add(c.getStudyTime());
            v.add(AuditType.getName(c.getIsAudit()));
            tableModel.addRow(v);
        }
    }

    // 设置列宽
    public void resizeTable() {
        courseListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(4).setPreferredWidth(300);
        courseListTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        courseListTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        courseListTable.getColumnModel().getColumn(7).setPreferredWidth(150);
    }

    AcdemicDean dean;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JComboBox isAuditComboBox1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable courseListTable;
    private JLabel label6;
    private JScrollPane scrollPane2;
    private JTextArea descriptionTextArea1;
    private JButton button2;
    private JButton button3;
    private JLabel label3;
    private JTextField teachIDTextField1;
    private JLabel label4;
    private JTextField tnameTextField2;
    private JLabel label5;
    private JTextField cnameTextField3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
