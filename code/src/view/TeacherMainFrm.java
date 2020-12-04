/*
 * Created by JFormDesigner on Sat Oct 24 21:24:02 CST 2020
 */

package view;

import model.Teacher;
import model.UserType;
import view.book.AllBooks;
import view.book.ShowABook;
import view.course.AddCourseByTeacher;
import view.course.CourseListByTeacher;
import view.course.StudentList;
import view.grade.Insert_gradePannel;
import view.grade.searchGradeByte;
import view.grade.sortGrade;
import view.grade.statisticGrade;

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
        this.cardLayout = new CardLayout();

        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Teacher teacher = (Teacher) user;

        // 老师添加课程
        JPanel addCourseByTeacher = new AddCourseByTeacher(teacher);
        panel1.add(addCourseByTeacher, "addCourseByTeacher");

        // 老师的课程列表
        JPanel courseListByTeacher = new CourseListByTeacher(teacher);
        panel1.add(courseListByTeacher, "courseListByTeacher");

        // 老师查看学生名单
        JPanel studentList = new StudentList(teacher);
        panel1.add(studentList, "studentList");

        // 所有教材
        JPanel allBooks = new AllBooks();
        panel1.add(allBooks, "allBooks");

        // 具体条目
        JPanel showABook = new ShowABook();
        panel1.add(showABook, "showABook");

        Insert_gradePannel insert_gradePannel = new Insert_gradePannel();
        searchGradeByte searchGradeByte0 = new searchGradeByte();
        statisticGrade statisticGrade0 = new statisticGrade();
        sortGrade sortGrade = new sortGrade();

        panel1.add(insert_gradePannel, "insertGrade");
        panel1.add(searchGradeByte0, "searchGradeByte");
        panel1.add(statisticGrade0, "statisticGrade");
        panel1.add(sortGrade, "sortGrade");

        JPanel testPanel = new TestPanel();
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

    // 显示学生名单
    private void showStudentsActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "studentList");
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

    // 录入成绩
    private void menuItem8ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "insertGrade");

    }

    // 查询成绩
    private void menuItem9ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "searchGradeByte");
    }

    // 平均成绩
    private void menuItem10ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "statisticGrade");
    }

    // 成绩排名
    private void menuItem11ActionPerformed(ActionEvent e) {
        // TODO add your code here
        cardLayout.show(panel1, "sortGrade");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu3 = new JMenu();
        menuItem6 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu4 = new JMenu();
        menuItem8 = new JMenuItem();
        menuItem9 = new JMenuItem();
        menuItem10 = new JMenuItem();
        menuItem11 = new JMenuItem();
        menu2 = new JMenu();
        menuItem4 = new JMenuItem();
        panel1 = new JPanel();

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

                //---- menuItem5 ----
                menuItem5.setText("\u5b66\u751f\u540d\u5355");
                menuItem5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem5.addActionListener(e -> showStudentsActionPerformed(e));
                menu1.add(menuItem5);

                //---- menuItem2 ----
                menuItem2.setText("\u6d4b\u8bd5\u9762\u677f");
                menuItem2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem2.addActionListener(e -> testMenuItem2ActionPerformed(e));
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu3 ========
            {
                menu3.setText("\u6559\u6750\u7ba1\u7406");
                menu3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem6 ----
                menuItem6.setText("\u6240\u6709\u6559\u6750");
                menuItem6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem6.addActionListener(e -> allBooksActionPerformed(e));
                menu3.add(menuItem6);

                //---- menuItem7 ----
                menuItem7.setText("\u5177\u4f53\u6761\u76ee");
                menuItem7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem7.addActionListener(e -> oneBookActionPerformed(e));
                menu3.add(menuItem7);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("\u6210\u7ee9\u7ba1\u7406");
                menu4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem8 ----
                menuItem8.setText("\u5f55\u5165\u6210\u7ee9");
                menuItem8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem8.addActionListener(e -> menuItem8ActionPerformed(e));
                menu4.add(menuItem8);

                //---- menuItem9 ----
                menuItem9.setText("\u67e5\u8be2\u6210\u7ee9");
                menuItem9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem9.addActionListener(e -> menuItem9ActionPerformed(e));
                menu4.add(menuItem9);

                //---- menuItem10 ----
                menuItem10.setText("\u5e73\u5747\u6210\u7ee9");
                menuItem10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem10.addActionListener(e -> menuItem10ActionPerformed(e));
                menu4.add(menuItem10);

                //---- menuItem11 ----
                menuItem11.setText("\u6210\u7ee9\u6392\u540d");
                menuItem11.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                menuItem11.addActionListener(e -> menuItem11ActionPerformed(e));
                menu4.add(menuItem11);
            }
            menuBar1.add(menu4);

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
    private JMenuItem menuItem5;
    private JMenuItem menuItem2;
    private JMenu menu3;
    private JMenuItem menuItem6;
    private JMenuItem menuItem7;
    private JMenu menu4;
    private JMenuItem menuItem8;
    private JMenuItem menuItem9;
    private JMenuItem menuItem10;
    private JMenuItem menuItem11;
    private JMenu menu2;
    private JMenuItem menuItem4;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
