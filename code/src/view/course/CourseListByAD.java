/*
 * Created by JFormDesigner on Sat Oct 24 17:58:14 CST 2020
 */

package view.course;

import dao.AcdemicDeanDao;
import model.AcdemicDean;
import model.Course;

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
public class CourseListByAD extends JPanel {
    public CourseListByAD(AcdemicDean dean) {
        this.dean = dean;
        initComponents();
        showAllCourse();
    }

    private void searchCourseButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取查询条件
        String cidtmp = cidTextField1.getText().trim();
        String cname = cnameTextField2.getText().trim();
        int cid;
        // 没有输入课程号
        if ("".equals(cidtmp)) {
            cid = 0;
        } else {
            cid = Integer.parseInt(cidtmp);
        }

        // 查找课程
        List<Course> courseList = new AcdemicDeanDao().searchCourse(cid, cname, dean.getInstitude());
        this.setTable(courseList);
    }

    // 选中一个课程，让数据显示在下面的框中
    private void selectOneCourseMouseClicked(MouseEvent e) {
        // TODO add your code here
        DefaultTableModel tableModel = (DefaultTableModel) courseListTable.getModel();
        int row = courseListTable.rowAtPoint(e.getPoint());
        int cid = (Integer) tableModel.getValueAt(row, 0);
        String cname = (String) tableModel.getValueAt(row, 1);
        String chour = (String) tableModel.getValueAt(row, 2);
        float ccredit = (Float) tableModel.getValueAt(row, 3);

        // 把数据显示在下面的文本框中
        editCidTextField3.setText(cid + "");
        editCnameTextField4.setText(cname);
        editChourTextField5.setText(chour);
        editCcreditTextField6.setText(ccredit + "");

    }

    // 确认修改课程信息
    private void submitEditButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        int cid = Integer.parseInt(editCidTextField3.getText());
        String cname = editCnameTextField4.getText().trim();
        String chour = editChourTextField5.getText().trim();
        String ccredittmp = editCcreditTextField6.getText().trim();

        // 用户没有填充完整信息
        if ("".equals(cname) || "".equals(chour) || "".equals(ccredittmp)) {
            JOptionPane.showMessageDialog(null, "请填充完整所有信息");
            return;
        }
        Float ccredit = Float.parseFloat(ccredittmp);
        // 更新数据
        if (new AcdemicDeanDao().updateCourse(cid, cname, chour, ccredit)) {
            JOptionPane.showMessageDialog(null, "修改成功");
            showAllCourse();
        }
    }

    // 删除课程
    private void deleteCourseButton3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取课程号
        int cid = Integer.parseInt(editCidTextField3.getText());
        // 用户是否确认删除
        int option = JOptionPane.showConfirmDialog(this, "确认删除吗?", "删除", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // 确认删除
            boolean flag = new AcdemicDeanDao().deleteCourse(cid);
            if (flag) {
                JOptionPane.showMessageDialog(this, "删除成功！");
                this.showAllCourse();
                // 同时，清空文本框中的数据
                editCidTextField3.setText("");
                editCnameTextField4.setText("");
                editChourTextField5.setText("");
                editCcreditTextField6.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "该课程已有教学班，不能删除");
                return;
            }
        } else if (option == JOptionPane.NO_OPTION) {
            // 否则，不做处理
            return;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        courseListTable = new JTable();
        label1 = new JLabel();
        label2 = new JLabel();
        cidTextField1 = new JTextField();
        label3 = new JLabel();
        cnameTextField2 = new JTextField();
        button1 = new JButton();
        label4 = new JLabel();
        editCidTextField3 = new JTextField();
        label5 = new JLabel();
        editCnameTextField4 = new JTextField();
        label6 = new JLabel();
        editChourTextField5 = new JTextField();
        label7 = new JLabel();
        editCcreditTextField6 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========

        //======== scrollPane1 ========
        {

            //---- courseListTable ----
            courseListTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u5b66\u65f6", "\u5b66\u5206"
                    }
            ) {
                boolean[] columnEditable = new boolean[]{
                        false, false, false, false
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

        //---- label1 ----
        label1.setText("\u6240 \u6709 \u8bfe \u7a0b");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u53f7\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cidTextField1 ----
        cidTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u8bfe\u7a0b\u540d\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameTextField2 ----
        cnameTextField2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> searchCourseButton1ActionPerformed(e));

        //---- label4 ----
        label4.setText("\u8bfe\u7a0b\u53f7\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editCidTextField3 ----
        editCidTextField3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        editCidTextField3.setEditable(false);

        //---- label5 ----
        label5.setText("\u8bfe\u7a0b\u540d\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editCnameTextField4 ----
        editCnameTextField4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label6 ----
        label6.setText("\u5b66\u65f6\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editChourTextField5 ----
        editChourTextField5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label7 ----
        label7.setText("\u5b66\u5206\uff1a");
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- editCcreditTextField6 ----
        editCcreditTextField6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button2 ----
        button2.setText("\u786e \u8ba4 \u4fee \u6539");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> submitEditButton2ActionPerformed(e));

        //---- button3 ----
        button3.setText("\u5220 \u9664");
        button3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button3.addActionListener(e -> deleteCourseButton3ActionPerformed(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                        .addComponent(label4)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(editCidTextField3, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(editChourTextField5, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(230, 230, 230)
                                                .addComponent(cidTextField1, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(cnameTextField2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(497, 497, 497)
                                                .addComponent(button3, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(521, 521, 521)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(editCcreditTextField6, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(editCnameTextField4, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label2)
                                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))))
                                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cnameTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cidTextField1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1))
                                .addGap(49, 49, 49)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editCidTextField3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editCnameTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(editChourTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editCcreditTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button3)
                                        .addComponent(button2))
                                .addContainerGap(35, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // 展示所有课程
    private void showAllCourse() {
        // 获取所有课程
        AcdemicDeanDao dao = new AcdemicDeanDao();
        List<Course> courseList = dao.searchCourse(0, "", dean.getInstitude());
        this.setTable(courseList);
    }

    // 表格展示
    private void setTable(List<Course> courseList) {
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
        for (Course c : courseList) {
            Vector v = new Vector();
            v.add(c.getCid());
            v.add(c.getCname());
            v.add(c.getChour());
            v.add(c.getCcredit());
            tableModel.addRow(v);
        }
    }


    private AcdemicDean dean;   // 教务员
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable courseListTable;
    private JLabel label1;
    private JLabel label2;
    private JTextField cidTextField1;
    private JLabel label3;
    private JTextField cnameTextField2;
    private JButton button1;
    private JLabel label4;
    private JTextField editCidTextField3;
    private JLabel label5;
    private JTextField editCnameTextField4;
    private JLabel label6;
    private JTextField editChourTextField5;
    private JLabel label7;
    private JTextField editCcreditTextField6;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
