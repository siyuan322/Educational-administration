/*
 * Created by JFormDesigner on Fri Dec 04 09:43:35 CST 2020
 */

package view.book;

import dao.BookDao;
import dao.TeacherDao;
import model.AcdemicDean;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.UUID;
import java.util.Vector;

/**
 * @author 123
 */
public class AddBook extends JPanel {
    public AddBook(AcdemicDean acdemicDean) {
        this.acdemicDean = acdemicDean;
        initComponents();

        // 列出所有的课程名称
        Vector<String> cnameList = new TeacherDao().searchCourseByInstitute(acdemicDean.getInstitude());
        cnameList.insertElementAt("请选择", 0);
        cnameComboBox.setModel(new DefaultComboBoxModel((cnameList)));
    }

    // 添加教材
    private void addBookActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取输入的参数
        String cname = cnameComboBox.getSelectedItem().toString();
        String bookName = bookNameTextField.getText().trim();
        String bookAuthor = bookAuthorTextField.getText().trim();
        String publishing = publishingTextField.getText().trim();
        String srcImgPath = imgPathTextField.getText().trim();

        // 判断是否有空值
        if ("请选择".equals(cname) || "".equals(bookName) || "".equals(bookAuthor) || "".equals(publishing) || "".equals(srcImgPath)) {
            JOptionPane.showMessageDialog(null, "请输入完整信息！");
            return;
        }

        // 判断是否是图片文件
        int suffixIndex = srcImgPath.lastIndexOf(".");
        String suffix = srcImgPath.substring(suffixIndex + 1, srcImgPath.length());   // 后缀
        if (!"jpg".equals(suffix) && !"png".equals(suffix)) {
            JOptionPane.showMessageDialog(null, "图片文件后缀名错误！");
            return;
        }

        // 保存文件到本地
        File srcFile = new File(srcImgPath);
        String targetImgPath = "resource/" + UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        File targetFile = new File(targetImgPath);
        try {
            InputStream in = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "文件不存在！");
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        // 添加到数据库中
        int cid = new TeacherDao().searchCid(cname, acdemicDean.getInstitude());
        Book book = new Book(0, cid, bookName, bookAuthor, publishing, targetImgPath);
        int bookId = new BookDao().addBook(book);

        // 判断是否成功
        if (bookId == 0) {
            JOptionPane.showMessageDialog(null, "插入失败，该门课程已有教材！");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "插入成功，书籍编号为" + bookId);
            button2.doClick();
        }
    }

    // 重置
    private void resetActionPerformed(ActionEvent e) {
        // TODO add your code here
        cnameComboBox.setSelectedIndex(0);
        bookNameTextField.setText("");
        bookAuthorTextField.setText("");
        publishingTextField.setText("");
        imgPathTextField.setText("");
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
        label6 = new JLabel();
        imgPathTextField = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText("\u6dfb \u52a0 \u6559 \u6750");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u8bfe \u7a0b \u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameComboBox ----
        cnameComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label3 ----
        label3.setText("\u4e66     \u540d\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- bookNameTextField ----
        bookNameTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label4 ----
        label4.setText("\u4f5c      \u8005\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- bookAuthorTextField ----
        bookAuthorTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label5 ----
        label5.setText("\u51fa \u7248 \u793e\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- publishingTextField ----
        publishingTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- label6 ----
        label6.setText("\u56fe\u7247\u5730\u5740\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- imgPathTextField ----
        imgPathTextField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u6dfb    \u52a0");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> addBookActionPerformed(e));

        //---- button2 ----
        button2.setText("\u91cd    \u7f6e");
        button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button2.addActionListener(e -> resetActionPerformed(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(431, Short.MAX_VALUE)
                                .addComponent(label1)
                                .addGap(420, 420, 420))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                                .addGap(112, 112, 112)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(bookAuthorTextField, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                        .addComponent(publishingTextField, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                        .addComponent(imgPathTextField, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                        .addComponent(bookNameTextField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                                .addGap(112, 112, 112)
                                                .addComponent(cnameComboBox, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(249, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(label1)
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cnameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bookNameTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bookAuthorTextField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(publishingTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(imgPathTextField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button2)
                                        .addComponent(button1))
                                .addContainerGap(85, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private AcdemicDean acdemicDean;
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
    private JLabel label6;
    private JTextField imgPathTextField;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
