/*
 * Created by JFormDesigner on Fri Nov 06 20:02:01 CST 2020
 */

package view;

import model.Student;
import model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 123
 */
public class StudentMainFrame extends JFrame {
    public StudentMainFrame(UserType userType, Object user) {
        this.userType = userType;
        this.user = user;
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Student student = (Student) user;
        JPanel chooseCourse = new ChooseCourse(student);
        JPanel testPanel = new TestPanel();
        panel1.add(chooseCourse, "chooseCourse");
        panel1.add(testPanel, "test");
    }

    private void chooseStudyActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "chooseCourse");
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "test");
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
        new LoginFrame();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        panel1 = new JPanel();
        cardLayout = new CardLayout();

        //======== this ========
        setTitle("\u5b66\u751f");
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u8bfe\u7a0b\u7ba1\u7406");
                menu1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem1 ----
                menuItem1.setText("\u9009\u8bfe");
                menuItem1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem1.addActionListener(e -> chooseStudyActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u6d4b\u8bd5");
                menuItem2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7cfb\u7edf\u7ba1\u7406");
                menu2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem3 ----
                menuItem3.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem3.addActionListener(e -> menuItem3ActionPerformed(e));
                menu2.add(menuItem3);
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
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private CardLayout cardLayout;
}
