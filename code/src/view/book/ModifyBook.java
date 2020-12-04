/*
 * Created by JFormDesigner on Fri Dec 04 14:22:33 CST 2020
 */

package view.book;

import dao.BookDao;
import dao.TeacherDao;
import model.AcdemicDean;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.UUID;
import java.util.Vector;

/**
 * @author 123
 */
public class ModifyBook extends JPanel {
    public ModifyBook(AcdemicDean acdemicDean) {
        this.acdemicDean = acdemicDean;
        this.book = new Book();
        initComponents();

        // 列出所有的已录入教材的课程名
        Vector<String> cnameList = new TeacherDao().searchCourseByInstitute(acdemicDean.getInstitude());
        cnameList.insertElementAt("请选择", 0);
        cnameComboBox.setModel(new DefaultComboBoxModel((cnameList)));
    }

    // 修改教材信息
    private void modifyBookActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 获取书本的id
        int bookId = book.getBookId();
        // 获取修改后的数据
        String bookName = bookNameTextField.getText().trim();
        String bookAuthor = bookAuthorTextField.getText().trim();
        String publishing = publishingTextField.getText().trim();
        String srcImgPath = imgPathTextField.getText().trim();

        // 判断是否有空值
        if ("".equals(bookName) || "".equals(bookAuthor) || "".equals(publishing) || "".equals(srcImgPath)) {
            JOptionPane.showMessageDialog(null, "请输入完整信息！");
            return;
        }

        String targetImgPath = "";
        // 判断图片文件是否有修改
        if (!book.getImgPath().equals(srcImgPath)) {
            // 图片文件已经修改
            // 判断是否是图片文件
            int suffixIndex = srcImgPath.lastIndexOf(".");
            String suffix = srcImgPath.substring(suffixIndex + 1, srcImgPath.length());   // 后缀
            if (!"jpg".equals(suffix) && !"png".equals(suffix)) {
                JOptionPane.showMessageDialog(null, "图片文件后缀名错误！");
                return;
            }

            // 保存文件到本地
            File srcFile = new File(srcImgPath);
            targetImgPath = "resource/" + UUID.randomUUID().toString().replace("-", "") + "." + suffix;
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
        } else {
            // 图片文件没有修改
            targetImgPath = srcImgPath;
        }


        // 将修改后的数据保存到数据库中
        Book newBook = new Book(bookId, book.getCid(), bookName, bookAuthor, publishing, targetImgPath);
        boolean flag = new BookDao().updateBook(newBook);

        // 判断是否成功
        if (flag) {
            JOptionPane.showMessageDialog(null, "修改成功！");
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
            return;
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

    // 选中一门课
    private void selectOneBookChanged(ItemEvent e) {
        // TODO add your code here
        String cname = cnameComboBox.getSelectedItem().toString();
        // 获取课程id
        int cid = new TeacherDao().searchCid(cname, acdemicDean.getInstitude());
        // 在book表中查找该信息
        BookDao bookDao = new BookDao();
        book = bookDao.getOneBook(cid);

        if (book != null) {
            bookNameTextField.setText(book.getBookName());
            bookAuthorTextField.setText(book.getBookAuthor());
            publishingTextField.setText(book.getPublishing());
            imgPathTextField.setText(book.getImgPath());
            modify.setEnabled(true);
        } else {
            bookNameTextField.setText("");
            bookAuthorTextField.setText("");
            publishingTextField.setText("");
            imgPathTextField.setText("");
            modify.setEnabled(false);
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
        label6 = new JLabel();
        imgPathTextField = new JTextField();
        modify = new JButton();
        button2 = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText("\u4fee \u6539 \u6559 \u6750");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u8bfe \u7a0b \u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- cnameComboBox ----
        cnameComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        cnameComboBox.addItemListener(e -> selectOneBookChanged(e));

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

        //---- modify ----
        modify.setText("\u4fee    \u6539");
        modify.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        modify.addActionListener(e -> modifyBookActionPerformed(e));

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
                                                .addComponent(modify, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(modify))
                                .addContainerGap(85, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private AcdemicDean acdemicDean;
    private Book book;
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
    private JButton modify;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
