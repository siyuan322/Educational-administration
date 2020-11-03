/*
 * Created by JFormDesigner on Thu Oct 22 15:58:18 CST 2020
 */

package view;

import dao.AcdemicDeanDao;
import dao.TeacherDao;
import model.AcdemicDean;
import model.Teacher;
import model.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 123
 */
public class LoginFrame extends JFrame {
    public static void main(String[] args) {
        new LoginFrame();
    }

    public LoginFrame() {
        initComponents();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 给用户类型添加选项
        useTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[]{
                UserType.NONE,
                UserType.ADMIN,
                UserType.ACDEMICDEAN,
                UserType.TEACHER,
                UserType.STUDENT
        }));
    }

    // 监听登录事件
    private void loginButton1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        login(e);
    }

    // 登录事件的处理函数
    private void login(ActionEvent e) {
        // 获取数据
        String userId = idTextField.getText().trim();
        String password = new String(passwordField.getPassword());
        UserType userType = (UserType) useTypeComboBox.getSelectedItem();

        // 判断是否输入数据
        if ("".equals(userId)) {
            JOptionPane.showMessageDialog(null, "账号不能为空！");
            return;
        }
        if ("".equals(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return;
        }
        if (userType == UserType.NONE) {
            JOptionPane.showMessageDialog(null, "请选择用户类型");
            return;
        }

        // 教务员登录
        if (userType == UserType.ACDEMICDEAN) {
            // 查询是否存在该用户
            AcdemicDean dean = new AcdemicDeanDao().login(userId, password);
            if (dean == null) {
                // 登录失败
                JOptionPane.showMessageDialog(null, "用户名或密码失败！");
                return;
            } else {
                // 登录成功
                this.dispose();
                new MainFrame(UserType.ACDEMICDEAN, dean).setVisible(true);
                JOptionPane.showMessageDialog(null, "登录成功！");
            }
        } else if (userType == UserType.TEACHER) {
            // 教师登录
            // 查询是否存在该用户
            Teacher teacher = new TeacherDao().login(userId, password);
            if (teacher == null) {
                // 登录失败
                JOptionPane.showMessageDialog(null, "用户名或密码失败！");
                return;
            } else {
                // 登录成功
                this.dispose();
                new TeacherMainFrm(UserType.ACDEMICDEAN, teacher).setVisible(true);
                JOptionPane.showMessageDialog(null, "登录成功！");
            }
        }
    }

    // 监听重置事件
    private void clearAllButton2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        reset();
    }

    // 重置事件的处理函数
    private void reset() {
        idTextField.setText("");
        passwordField.setText("");
        useTypeComboBox.setSelectedIndex(UserType.getIndex("请选择"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        idTextField = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        useTypeComboBox = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();
        passwordField = new JPasswordField();

        //======== this ========
        setTitle("\u6559\u52a1\u7ba1\u7406\u7cfb\u7edf");
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u6559 \u52a1 \u7ba1 \u7406 \u7cfb \u7edf");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));

        //---- label2 ----
        label2.setText("\u8d26\u53f7\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- idTextField ----
        idTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label4 ----
        label4.setText("\u7528\u6237\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- useTypeComboBox ----
        useTypeComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u767b \u5f55");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> loginButton1ActionPerformed(e));

        //---- button2 ----
        button2.setText("\u91cd \u7f6e");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> clearAllButton2ActionPerformed(e));

        //---- passwordField ----
        passwordField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(177, 177, 177)
                                                .addComponent(label1))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(123, 123, 123)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(label3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(passwordField))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(label2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(label4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(useTypeComboBox))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(171, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(label1)
                                .addGap(36, 36, 36)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(useTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1)
                                        .addComponent(button2))
                                .addContainerGap(39, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField idTextField;
    private JLabel label3;
    private JLabel label4;
    private JComboBox useTypeComboBox;
    private JButton button1;
    private JButton button2;
    private JPasswordField passwordField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
