/*
 * Created by JFormDesigner on Sat Oct 24 21:24:02 CST 2020
 */

package view;

import model.Teacher;
import model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 123
 */
public class TeacherMainFrm extends JFrame {
    public TeacherMainFrm(UserType userType, Object user) {
        this.userType = userType;
        this.user = user;
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Teacher teacher = (Teacher) user;
        JPanel addCourseByTeacher = new AddCourseByTeacher(teacher);
        JPanel courseListByTeacher = new CourseListByTeacher(teacher);
        JPanel testPanel = new TestPanel();
        panel1.add(addCourseByTeacher, "addCourseByTeacher");
        panel1.add(courseListByTeacher, "courseListByTeacher");
        panel1.add(testPanel, "test");
    }

    // 添加课程面板
    private void addCourseMenuItem1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "addCourseByTeacher");
    }

    // 课程列表面板
    private void courseListMenuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "courseListByTeacher");
    }

    // 测试面板
    private void testMenuItem2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "test");
    }

    // 退出系统
    private void exitMenuItem4ActionPerformed(ActionEvent e) {
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
        menuItem2 = new JMenuItem();
        menu2 = new JMenu();
        menuItem4 = new JMenuItem();
        panel1 = new JPanel();
        cardLayout = new CardLayout();

        //======== this ========
        setTitle("\u8001\u5e08");
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
                menuItem1.addActionListener(e -> addCourseMenuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem3 ----
                menuItem3.setText("\u8bfe\u7a0b\u5217\u8868");
                menuItem3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem3.addActionListener(e -> courseListMenuItem3ActionPerformed(e));
                menu1.add(menuItem3);

                //---- menuItem2 ----
                menuItem2.setText("\u6d4b\u8bd5\u9762\u677f");
                menuItem2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem2.addActionListener(e -> testMenuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7cfb\u7edf\u7ba1\u7406");
                menu2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem4 ----
                menuItem4.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem4.addActionListener(e -> exitMenuItem4ActionPerformed(e));
                menu2.add(menuItem4);
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
    CardLayout cardLayout;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem3;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenuItem menuItem4;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
