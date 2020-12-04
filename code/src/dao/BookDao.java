package dao;

import model.Book;
import model.BookItem;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    // 添加课程,返回书籍编号
    public int addBook(Book book) {
        Connection conn = DbUtil.getConn();
        int bookId = 0;
        String sql = "insert into book(cid, bookName, bookAuthor, publishing, imgPath) values(?,?,?,?,?)";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, book.getCid());
            pres.setString(2, book.getBookName());
            pres.setString(3, book.getBookAuthor());
            pres.setString(4, book.getPublishing());
            pres.setString(5, book.getImgPath());
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                // 获取课程号
                bookId = this.getOneBook(book.getCid()).getBookId();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return bookId;
    }

    // 根据课程号获取书本的具体信息
    public Book getOneBook(int cid) {
        Connection conn = DbUtil.getConn();
        Book book = null;
        String sql = "select * from book where cid = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, cid);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setCid(rs.getInt("cid"));
                book.setBookName(rs.getString("bookName"));
                book.setBookAuthor(rs.getString("bookAuthor"));
                book.setPublishing(rs.getString("publishing"));
                book.setImgPath(rs.getString("imgPath"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return book;
    }

    // 更新教材的信息
    public boolean updateBook(Book book) {
        Connection conn = DbUtil.getConn();
        String sql = "update book set bookName=?, bookAuthor=?, publishing=?, imgPath = ? where bookId = ?";
        boolean flag = false;
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, book.getBookName());
            pres.setString(2, book.getBookAuthor());
            pres.setString(3, book.getPublishing());
            pres.setString(4, book.getImgPath());
            pres.setInt(5, book.getBookId());
            // 执行sql语句
            int num = pres.executeUpdate();
            if (num != 0) {
                System.out.println("更新成功！");
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return flag;

    }

    // 获取所有教材的信息
    public List<BookItem> allBooks(String institute) {
        Connection conn = DbUtil.getConn();
        List<BookItem> bookItemList = new ArrayList<BookItem>();
        BookItem item = null;
        String sql = "select * from bookItem where institute = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, institute);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                item = new BookItem();
                item.setBookId(rs.getInt("bookId"));
                item.setCid(rs.getInt("cid"));
                item.setCname(rs.getString("cname"));
                item.setBookName(rs.getString("bookName"));
                item.setBookAuthor(rs.getString("bookAuthor"));
                item.setPublishing(rs.getString("publishing"));
                item.setInstitute(institute);
                bookItemList.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return bookItemList;
    }

}
