/*
 * Created by JFormDesigner on Fri Dec 04 15:13:18 CST 2020
 */

package view.book;

import dao.BookDao;
import model.BookItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

/**
 * @author 123
 */
public class AllBooks extends JPanel {
    public AllBooks() {
        initComponents();
        this.resizeTable();
    }

    // 查找某个学院所有的教材信息
    private void allBooksActionPerformed(ActionEvent e) {
        // TODO add your code here
        String institute = instituteComboBox.getSelectedItem().toString();
        if ("请选择".equals(institute)) {
            // 没有输入查询信息
            JOptionPane.showMessageDialog(null, "请输入查询信息！");
            return;
        }

        BookDao bookDao = new BookDao();
        List<BookItem> bookItemList = bookDao.allBooks(institute);
        setTable(bookItemList);
    }

    // 表格展示
    private void setTable(List<BookItem> bookItemList) {
        // 1.先删除表中的所有数据
        DefaultTableModel tableModel = (DefaultTableModel) allBooksTable.getModel();
        int rowCount = tableModel.getRowCount();
        //从表的末尾逐个删除行
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        // 3.添加到表格中
        // 让单元格文字居中
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        allBooksTable.setDefaultRenderer(Object.class, renderer);
        // 设置行高
        allBooksTable.setRowHeight(26);
        for (BookItem item : bookItemList) {
            Vector v = new Vector();
            v.add(item.getBookId());
            v.add(item.getCid());
            v.add(item.getCname());
            v.add(item.getBookName());
            v.add(item.getBookAuthor());
            v.add(item.getPublishing());
            tableModel.addRow(v);
        }
    }

    // 设置列宽
    public void resizeTable() {
        allBooksTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        allBooksTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        allBooksTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        allBooksTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        allBooksTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        allBooksTable.getColumnModel().getColumn(5).setPreferredWidth(130);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        allBooksTable = new JTable();
        instituteComboBox = new JComboBox<>();

        //======== this ========

        //---- label1 ----
        label1.setText("\u6240 \u6709 \u6559 \u6750");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));

        //---- label2 ----
        label2.setText("\u5f00\u8bfe\u5b66\u9662\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

        //---- button1 ----
        button1.setText("\u67e5    \u8be2");
        button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        button1.addActionListener(e -> allBooksActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- allBooksTable ----
            allBooksTable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            allBooksTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "\u6559\u6750\u7f16\u53f7", "\u8bfe\u7a0b\u53f7", "\u8bfe\u7a0b\u540d", "\u4e66\u540d", "\u4f5c\u8005", "\u51fa\u7248\u793e"
                    }
            ) {
                boolean[] columnEditable = new boolean[]{
                        false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = allBooksTable.getColumnModel();
                cm.getColumn(0).setResizable(false);
                cm.getColumn(1).setResizable(false);
                cm.getColumn(2).setResizable(false);
                cm.getColumn(3).setResizable(false);
                cm.getColumn(4).setResizable(false);
                cm.getColumn(5).setResizable(false);
            }
            scrollPane1.setViewportView(allBooksTable);
        }

        //---- instituteComboBox ----
        instituteComboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        instituteComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u8bf7\u9009\u62e9",
                "\u8ba1\u7b97\u673a\u79d1\u5b66\u4e0e\u5de5\u7a0b\u5b66\u9662",
                "\u6570\u5b66\u5b66\u9662",
                "\u9a6c\u514b\u601d\u5b66\u9662"
        }));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(149, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label1)
                                                .addGap(118, 118, 118))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addGap(71, 71, 71)
                                                .addComponent(instituteComboBox, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                                                .addGap(106, 106, 106)))
                                .addComponent(button1)
                                .addGap(217, 217, 217))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(label1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(instituteComboBox))
                                .addGap(52, 52, 52)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable allBooksTable;
    private JComboBox<String> instituteComboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
