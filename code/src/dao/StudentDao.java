package dao;

import model.CourseByStudent;
import model.Student;
import model.Study;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    // 登录
    public Student login(String id, String password) {
        Connection conn = DbUtil.getConn();
        Student student = null;
        String sql = "select * from student where sid = ? and password = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            pres.setString(2, password);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setSid(rs.getString("sid"));
                student.setPassword(rs.getString("password"));
                student.setSname(rs.getString("sname"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return student;
    }

    // 查找课程
    public List<CourseByStudent> search(int teachID, String cname, int capacity, String institute) {
        Connection conn = DbUtil.getConn();
        List<CourseByStudent> courseList = new ArrayList<>();
        String sql = "select * from courseByStudent where 1 = 1";

        // 拼接sql语句
        if (teachID != 0) {
            sql += " and teachID = '" + teachID + "' ";
        }
        if (!"".equals(cname)) {
            sql += " and cname = '" + cname + "' ";
        }
        if (!"请选择".equals(institute) && !"".equals(institute)) {
            sql += " and institute = '" + institute + "' ";
        } else {

        }

        // 判断是否有余量
        if (capacity == 1) {
            // 有余量
            sql += "and capacity > currentNum ";
        } else if (capacity == 2) {
            // 没有余量
            sql += "and capacity <= currentNum ";
        }

        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                CourseByStudent course = new CourseByStudent();
                course.setTeachID(rs.getInt("teachID"));
                course.setCname(rs.getString("cname"));
                course.setTname(rs.getString("tname"));
                course.setChour(rs.getString("chour"));
                course.setCcredit(rs.getFloat("ccredit"));
                course.setCapacity(rs.getInt("capacity"));
                course.setCurrentNum(rs.getInt("currentNum"));
                course.setStudyTime(rs.getString("studyTime"));
                course.setDescription(rs.getString("description"));
                course.setInstitute(rs.getString("institute"));
                courseList.add(course);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return courseList;
    }

    // 查找是否选择一门课
    public Study ifChooseCourse(String sid, int teachID) {
        Connection conn = DbUtil.getConn();
        Study study = null;
        String sql = "select * from study where sid = ? and teachID = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, sid);
            pres.setInt(2, teachID);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                study = new Study();
                study.setSid(rs.getString("sid"));
                study.setTeachID(rs.getInt("teachID"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return study;

    }

    // 选课
    public boolean chooseCourse(String sid, int teachID) {
        Connection conn = DbUtil.getConn();
        boolean flag = false;
        String sql = "insert into study(sid, teachID) values(?,?)";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, sid);
            pres.setInt(2, teachID);
            // 执行sql语句
            int num = pres.executeUpdate();
            if (num != 0) {
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return flag;
    }

    // 退课
    public boolean cancelCourse(String sid, int teachID) {
        Connection conn = DbUtil.getConn();
        boolean flag = false;
        String sql = "delete from study where sid = ? and teachID = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, sid);
            pres.setInt(2, teachID);
            // 执行sql语句
            int num = pres.executeUpdate();
            if (num != 0) {
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return flag;
    }

}
