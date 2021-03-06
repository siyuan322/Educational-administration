/*
 * Created by JFormDesigner on Thu Dec 03 22:35:58 CST 2020
 */

package view.grade;

import dao.statisticGradeDao;
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
public class sortGrade extends JPanel {
    public sortGrade() {
        initComponents();
    }

    //排序
    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String temp = textField1.getText();
        int tid = Integer.parseInt(temp);
        String temp2 = textField2.getText();
        int cid = Integer.parseInt(temp2);
        statisticGradeDao dao = new statisticGradeDao();
        setTable(dao.sortGrade(tid, cid));
    }

    // 表格展示
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
            v.add(c.getCid());
            v.add(c.getCname());
            v.add(c.getClid());
            v.add(c.getFinalGrade());
            tableModel.addRow(v);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setPreferredSize(new Dimension(975, 675));

        //---- label1 ----
        label1.setText("\u6559\u5e08\u7f16\u53f7");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- label2 ----
        label2.setText("\u8bfe\u7a0b\u7f16\u53f7");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        button1.addActionListener(e -> button1ActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u5b66\u751f\u5b66\u53f7", "\u8bfe\u7a0b\u7f16\u53f7", "\u8bfe\u7a0b\u540d\u79f0", "\u73ed\u7ea7\u7f16\u53f7", "\u6210\u7ee9"
                    }
            ));
            table1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            scrollPane1.setViewportView(table1);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(344, 344, 344)
                                                .addComponent(button1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(381, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(button1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
