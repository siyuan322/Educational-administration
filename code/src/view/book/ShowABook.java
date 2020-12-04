/*
 * Created by JFormDesigner on Fri Dec 04 16:11:55 CST 2020
 */

package view.book;

import dao.BookDao;
import dao.TeacherDao;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;

/**
 * @author 123
 */
public class ShowABook extends JPanel {
    public ShowABook() {
        initComponents();
        cnameComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "请选择"
        }));
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("resource/none.jpg").getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT));
        bookImg.setIcon(imageIcon);

    }

    // 根据学院的名称来修改课程的选项
    private void instituteComboBoxItemStateChanged(ItemEvent e) {
        // TODO add your code here
        // 获取学院名称
        String institute = instituteComboBox.getSelectedItem().toString();
        if (!"请选择".equals(institute)) {
            Vector<String> cnames = new TeacherDao().searchCourseByInstitute(institute);
            cnames.insertElementAt("请选择", 0);
            cnameComboBox.setModel(new DefaultComboBoxModel((cnames)));
        } else {
            cnameComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                    "请选择"
            }));
        }
    }

    // 显示教材的具体信息
    private void showBookItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        String institute = instituteComboBox.getSelectedItem().toString();
        String cname = cnameComboBox.getSelectedItem().toString();

        if ("请选择".equals(institute) || "请选择".equals(cname)) {
            JOptionPane.showMessageDialog(null, "请输入具体的查询信息！");
            return;
        }

        int cid = new TeacherDao().searchCid(cname, institute);
        Book book = new BookDao().getOneBook(cid);

        if (book != null) {
            bookIdTextField.setText(book.getBookId() + "");
            bookNameTextField.setText(book.getBookName());
            bookAuthorTextField.setText(book.getBookAuthor());
            publishingTextField.setText(book.getPublishing());

            // 图片展示
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(book.getImgPath()).getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT));
            bookImg.setIcon(imageIcon);
        } else {
            JOptionPane.showMessageDialog(null, "暂无详细信息！");
            bookIdTextField.setText("");
            bookNameTextField.setText("");
            bookAuthorTextField.setText("");
            publishingTextField.setText("");

            ImageIcon imageIcon = new ImageIcon(new ImageIcon("resource/none.jpg").getImage().getScaledInstance(300, 350, Image.SCALE_DEFAULT));
            bookImg.setIcon(imageIcon);
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        cnameComboBox = new JComboBox();
        label3 = new JLabel();
        bookNameTextField = new JTextField();
        label4 = new JLabel();
        bookAuthorTextField = new JTextField();
        label5 = new JLabel();
        publishingTextField = new JTextField();
        instituteComboBox = new JComboBox<>();
        label7 = new JLabel();
        button1 = new JButton();
        bookImg = new JLabel();
        label6 = new JLabel();
        bookIdTextField = new JTextField();

        //======== this ========

        //---- label1 ----
        label1.setText("\u5177 \u4f53 \u6761 \u76ee");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u5f00 \u8bfe \u5b66 \u9662 \uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameComboBox ----
        cnameComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u4e66     \u540d\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- bookNameTextField ----
        bookNameTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        bookNameTextField.setEditable(false);

        //---- label4 ----
        label4.setText("\u4f5c      \u8005\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- bookAuthorTextField ----
        bookAuthorTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        bookAuthorTextField.setEditable(false);

        //---- label5 ----
        label5.setText("\u51fa \u7248 \u793e\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- publishingTextField ----
        publishingTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        publishingTextField.setEditable(false);

        //---- instituteComboBox ----
        instituteComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u8bf7\u9009\u62e9",
                "\u8ba1\u7b97\u673a\u79d1\u5b66\u4e0e\u5de5\u7a0b\u5b66\u9662",
                "\u6570\u5b66\u5b66\u9662"
        }));
        instituteComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        instituteComboBox.addItemListener(e -> instituteComboBoxItemStateChanged(e));

        //---- label7 ----
        label7.setText("\u8bfe \u7a0b \u540d\uff1a");
        label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u67e5  \u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> showBookItemActionPerformed(e));

        //---- label6 ----
        label6.setText("\u6559\u6750\u7f16\u53f7\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- bookIdTextField ----
        bookIdTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        bookIdTextField.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(instituteComboBox, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(bookImg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(label1)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(label4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                .addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                .addComponent(label6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                .addComponent(label7, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cnameComboBox, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                                .addGap(41, 41, 41)
                                                .addComponent(button1))
                                        .addComponent(bookIdTextField)
                                        .addComponent(bookNameTextField)
                                        .addComponent(bookAuthorTextField)
                                        .addComponent(publishingTextField))
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(250, 250, 250)
                                                .addComponent(bookImg, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(label1)
                                                .addGap(46, 46, 46)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(instituteComboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cnameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(button1)
                                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                                .addGap(93, 93, 93)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bookIdTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                                .addGap(56, 56, 56)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bookNameTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                                .addGap(82, 82, 82)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bookAuthorTextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                                .addGap(90, 90, 90)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(publishingTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(71, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JComboBox cnameComboBox;
    private JLabel label3;
    private JTextField bookNameTextField;
    private JLabel label4;
    private JTextField bookAuthorTextField;
    private JLabel label5;
    private JTextField publishingTextField;
    private JComboBox<String> instituteComboBox;
    private JLabel label7;
    private JButton button1;
    private JLabel bookImg;
    private JLabel label6;
    private JTextField bookIdTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
