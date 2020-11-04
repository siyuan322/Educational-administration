/*
 * Created by JFormDesigner on Sat Oct 24 15:32:31 CST 2020
 */

package view;

import dao.AcdemicDeanDao;
import model.AcdemicDean;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 123
 */
public class AddCourseByAD extends JPanel {
    public AddCourseByAD(AcdemicDean dean) {
        this.dean = dean;
        initComponents();
    }

    // 重置
    private void resetButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cnameTextField1.setText("");
        chourTextField2.setText("");
        ccreditTextField3.setText("");
    }

    // 添加课程
    private void submitButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        String cname = cnameTextField1.getText().trim();
        String chour = chourTextField2.getText().trim();
        String ccredittmp = ccreditTextField3.getText().trim();
        String institude = dean.getInstitude();

        // 判断是否全部输入数据
        if ("".equals(cname) || "".equals(chour) || "".equals(ccredittmp)) {
            JOptionPane.showMessageDialog(null, "请输入所有数据！");
            return;
        }

        Float ccredit = Float.parseFloat(ccredittmp);

        // 一个学院中不能有重名的课程名
        AcdemicDeanDao dao = new AcdemicDeanDao();
        Course course = dao.searchByName(cname, institude);

        // 已有该课程名
        if (course != null) {
            JOptionPane.showMessageDialog(null, "该学院已有这门课程，请更换课程名！");
            return;
        }

        int cid = dao.addCourse(cname, chour, ccredit, institude);
        JOptionPane.showMessageDialog(null, "插入成功，课程号为：" + cid);

        // 清空表中数据
        button2.doClick();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        cnameTextField1 = new JTextField();
        label2 = new JLabel();
        chourTextField2 = new JTextField();
        label3 = new JLabel();
        ccreditTextField3 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        label4 = new JLabel();

        //======== this ========

        //---- label1 ----
        label1.setText("\u8bfe\u7a0b\u540d\uff1a");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameTextField1 ----
        cnameTextField1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label2 ----
        label2.setText("\u5b66\u65f6\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- chourTextField2 ----
        chourTextField2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u5b66\u5206\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- ccreditTextField3 ----
        ccreditTextField3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> submitButton1ActionPerformed(e));

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> resetButton2ActionPerformed(e));

        //---- label4 ----
        label4.setText("\u6dfb\u52a0\u8bfe\u7a0b");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(266, 266, 266)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                                        .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                                        .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                                .addGap(64, 64, 64)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cnameTextField1, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                                        .addComponent(chourTextField2, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                                        .addComponent(ccreditTextField3, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))))
                                .addContainerGap(310, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cnameTextField1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(chourTextField2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ccreditTextField3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                .addGap(208, 208, 208))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private AcdemicDean dean;   // 教务员

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField cnameTextField1;
    private JLabel label2;
    private JTextField chourTextField2;
    private JLabel label3;
    private JTextField ccreditTextField3;
    private JButton button1;
    private JButton button2;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
