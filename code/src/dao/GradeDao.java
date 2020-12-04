package dao;

import model.Study;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {
    //查成绩
    public List<Study> searchBystudent(int Sid) {
        Connection conn = DbUtil.getConn();
        List<Study> stuGradeList = new ArrayList<>();
        String sql = "select sid,teachid,classno,cid,cname,FinalGrade from Study where Sid=" + Sid;  //sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                Study stuGrade = new Study();
                stuGrade.setSid(rs.getString("sid"));

                stuGrade.setTeachID(rs.getInt("teachid"));
                stuGrade.setClid(rs.getString("classno"));
                stuGrade.setCid(rs.getInt("cid"));
                stuGrade.setCname(rs.getString("cname"));

                stuGrade.setFinalGrade(rs.getInt("FinalGrade"));

                stuGradeList.add(stuGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return stuGradeList;
    }

    public List<Study> searchByteach(int Tid, int Cid) {
        Connection conn = DbUtil.getConn();
        List<Study> stuGradeList = new ArrayList<>();
        String sql = "select sid,teachid, classno,cid,cname,NormalGrade,TermGrade,FinalGrade from Study where Teachid=" + Tid + "and Cid=" + Cid;  //sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                Study stuGrade = new Study();
                stuGrade.setSid(rs.getString("sid"));
                stuGrade.setTeachID(rs.getInt("teachid"));
                stuGrade.setClid(rs.getString("classno"));
                stuGrade.setCid(rs.getInt("cid"));
                stuGrade.setCname(rs.getString("cname"));
                stuGrade.setNormalGrade(rs.getInt("NormalGrade"));
                stuGrade.setTermGrade(rs.getInt("TermGrade"));
                stuGrade.setFinalGrade(rs.getInt("FinalGrade"));
                stuGradeList.add(stuGrade);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return stuGradeList;

    }

    //增加成绩 返回成绩
    public List insertGrade(Study inByte) {
        Connection conn = DbUtil.getConn();
        List<Study> stuGradeList = new ArrayList<>();
        String sql = "insert into study(sid,teachID,classno,cid,cname,normalGrade,termGrade,finalGrade) values (?,?,?,?,?,?,?,?) ";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, inByte.getSid());
            pres.setInt(2, inByte.getTeachID());
            pres.setString(3, inByte.getClid());
            pres.setInt(4, inByte.getCid());
            pres.setString(5, inByte.getCname());
            pres.setInt(6, inByte.getNormalGrade());
            pres.setInt(7, inByte.getTermGrade());
            pres.setInt(8, inByte.getFinalGrade());

            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                System.out.println("插入成功");
            }
            stuGradeList.add(inByte);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return stuGradeList;
    }

    //修改成绩
    public List updateGrade(Study inByte) {
        List<Study> stuGradeList = new ArrayList<>();
        Connection conn = DbUtil.getConn();
        // update courseByAD set isAudit = ? where teachID = ?
        String sql = "update study set  normalGrade=?, termgrade=?,finalGrade=? where sid=? and cid=? and teachid=? ";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, inByte.getNormalGrade());
            pres.setInt(2, inByte.getTermGrade());
            pres.setInt(3, inByte.getFinalGrade());
            pres.setString(4, inByte.getSid());
            pres.setInt(5, inByte.getCid());
            pres.setInt(6, inByte.getTeachID());
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                stuGradeList.add(inByte);
                System.out.println("修改成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return stuGradeList;
    }


}