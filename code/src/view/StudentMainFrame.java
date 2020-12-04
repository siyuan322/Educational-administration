/*
 * Created by JFormDesigner on Fri Nov 06 20:02:01 CST 2020
 */

package view;

import model.Student;
import model.UserType;
import view.book.AllBooks;
import view.book.ShowABook;
import view.course.ChooseCourse;

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
        this.cardLayout = new CardLayout();
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Student student = (Student) user;
        // 选课
        JPanel chooseCourse = new ChooseCourse(student);
        panel1.add(chooseCourse, "chooseCourse");

        // 所有教材
        JPanel allBooks = new AllBooks();
        panel1.add(allBooks, "allBooks");

        // 具体条目
        JPanel showABook = new ShowABook();
        panel1.add(showABook, "showABook");

        // 成绩查询
        JPanel chooseCourse = new ChooseCourse(student);
        panel1.add(searchGrade, "searchGradeBystu")

        // 测试面板
        JPanel testPanel = new TestPanel();
        panel1.add(testPanel, "test");
    }

    // 选课
    private void chooseStudyActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "chooseCourse");
    }

    // 测试面板
    private void menuItem2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "test");
    }

    // 退出系统
    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
        new LoginFrame();
    }

    // 所有教材
    private void allBooksActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "allBooks");
    }

    // 具体条目
    private void oneBookActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "showABook");
    }

    // 成绩查询
    private void gradeSearchActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "searchGradeBystu");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu3 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu4 = new JMenu();
        menuItem6 = new JMenuItem();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        panel1 = new JPanel();

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

            //======== menu3 ========
            {
                menu3.setText("\u6559\u6750\u7ba1\u7406");
                menu3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem4 ----
                menuItem4.setText("\u6240\u6709\u6559\u6750");
                menuItem4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem4.addActionListener(e -> allBooksActionPerformed(e));
                menu3.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("\u5177\u4f53\u6761\u76ee");
                menuItem5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem5.addActionListener(e -> oneBookActionPerformed(e));
                menu3.add(menuItem5);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("\u6210\u7ee9\u7ba1\u7406");
                menu4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem6 ----
                menuItem6.setText("\u6210\u7ee9\u67e5\u8be2");
                menuItem6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem6.addActionListener(e -> gradeSearchActionPerformed(e));
                menu4.add(menuItem6);
            }
            menuBar1.add(menu4);

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

    private UserType userType;  // 用户的类型
    private Object user;        // 登录的用户
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menu3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu4;
    private JMenuItem menuItem6;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private CardLayout cardLayout;
}
