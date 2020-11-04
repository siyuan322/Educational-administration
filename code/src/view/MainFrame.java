/*
 * Created by JFormDesigner on Sat Oct 24 15:21:09 CST 2020
 */

package view;

import model.AcdemicDean;
import model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 123
 */
public class MainFrame extends JFrame {

    public MainFrame(UserType userType, Object user) {
        this.userType = userType;
        this.user = user;
        cardLayout = new CardLayout();
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AcdemicDean dean = (AcdemicDean) user;
        JPanel addCourseByADPanel = new AddCourseByAD(dean);
        JPanel courseListByADPanel = new CourseListByAD(dean);
        JPanel auditByAD = new AuditByAD(dean);
        JPanel testPanel = new TestPanel();
        panel1.add(addCourseByADPanel, "addCourseByAD");
        panel1.add(courseListByADPanel, "courseListByAD");
        panel1.add(auditByAD, "auditByAD");
        panel1.add(testPanel, "testPanel");
    }

    // 显示添加课程面板
    private void addCourseByADmenuItem1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "addCourseByAD");
    }

    // 显示测试面板
    private void testmenuItem2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "testPanel");
    }

    // 显示课程列表面板
    private void courseListMenuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "courseListByAD");
    }

    // 审核课程
    private void auditCourseMenuItem4ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "auditByAD");
    }

    // 退出系统
    private void exitMenuItem5ActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
        new LoginFrame();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu2 = new JMenu();
        menuItem5 = new JMenuItem();
        panel1 = new JPanel();
        cardLayout = new CardLayout();

        //======== this ========
        setTitle("\u6559\u52a1\u5458");
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u8bfe\u7a0b\u7ba1\u7406");
                menu1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem1 ----
                menuItem1.setText("\u65b0\u589e\u8bfe\u7a0b");
                menuItem1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem1.addActionListener(e -> addCourseByADmenuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem3 ----
                menuItem3.setText("\u8bfe\u7a0b\u5217\u8868");
                menuItem3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem3.addActionListener(e -> courseListMenuItem3ActionPerformed(e));
                menu1.add(menuItem3);

                //---- menuItem4 ----
                menuItem4.setText("\u8bfe\u7a0b\u5ba1\u6838");
                menuItem4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem4.addActionListener(e -> auditCourseMenuItem4ActionPerformed(e));
                menu1.add(menuItem4);

                //---- menuItem2 ----
                menuItem2.setText("\u6d4b\u8bd5\u9762\u677f");
                menuItem2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem2.addActionListener(e -> testmenuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7cfb\u7edf\u7ba1\u7406");
                menu2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem5 ----
                menuItem5.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem5.addActionListener(e -> exitMenuItem5ActionPerformed(e));
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(cardLayout);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    UserType userType;  // 用户的类型
    Object user;        // 登录的用户
    private CardLayout cardLayout;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenuItem menuItem5;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}