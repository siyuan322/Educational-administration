package dao;

import model.AcdemicDean;
import model.Course;
import model.CourseByAD;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 与教务员有关的和数据库连接
public class AcdemicDeanDao {
    // 获取和数据库的连接
    private Connection conn;

    // 登录功能
    public AcdemicDean login(String id, String password) {
        conn = DbUtil.getConn();
        AcdemicDean acdemicDean = null;
        String sql = "select * from acdemicDean where id = ? and password = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            pres.setString(2, password);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                acdemicDean = new AcdemicDean();
                acdemicDean.setId(rs.getString("id"));
                acdemicDean.setPassword(rs.getString("password"));
                acdemicDean.setInstitude(rs.getString("institude"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return acdemicDean;
    }

    // 根据课程名和学院查询课程信息
    public Course searchByName(String cname, String institute) {
        conn = DbUtil.getConn();
        Course course = null;
        String sql = "select * from course where cname = ? and institute = ?";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, cname);
            pres.setString(2, institute);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setCid(rs.getInt("cid"));
                course.setCname(rs.getString("cname"));
                course.setChour(rs.getString("chour"));
                course.setCcredit(rs.getFloat("ccredit"));
                course.setInstitute(rs.getString("institute"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return course;
    }

    // 查询所有课程
    public List<Course> searchCourse(int cid, String cname, String institute) {
        conn = DbUtil.getConn();
        List<Course> courseList = new ArrayList<Course>(); // 所有结果
        Course course = null;           // 暂时存放一个课程
        String sql = "select * from course where institute = ? ";
        if (cid != 0) {
            sql += " and cid = " + cid;
        }
        if (!"".equals(cname)) {
            sql += " and cname = '" + cname + "'";
        }
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, institute);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                course = new Course();
                course.setCid(rs.getInt("cid"));
                course.setCname(rs.getString("cname"));
                course.setChour(rs.getString("chour"));
                course.setCcredit(rs.getFloat("ccredit"));
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

    // 添加课程
    public int addCourse(String cname, String chour, float ccredit, String institute) {
        conn = DbUtil.getConn();
        int cid = 0;
        String sql = "insert into course(cname, chour, ccredit, institute) values(?,?,?,?) ";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, cname);
            pres.setString(2, chour);
            pres.setFloat(3, ccredit);
            pres.setString(4, institute);
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                // 插入成功
                System.out.println("插入成功");
                // 给用户返回课程号
                Course course = searchByName(cname, institute);
                cid = course.getCid();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return cid;
    }

    // 更新课程
    public boolean updateCourse(int cid, String cname, String chour, Float ccredit) {
        conn = DbUtil.getConn();
        String sql = "update course set cname = ?, chour = ?, ccredit = ? where cid = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, cname);
            pres.setString(2, chour);
            pres.setFloat(3, ccredit);
            pres.setInt(4, cid);
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                // 插入成功
                System.out.println("更新成功");
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return false;
    }

    // 删除课程
    public boolean deleteCourse(int cid) {
        conn = DbUtil.getConn();
        // 用于存放目标对象
        String sql = "delete from course where cid = ?";
        // 把sql语句传给数据库对象
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, cid);
            // 执行sql语句
            pres.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            DbUtil.closeConn(conn);
        }
        return true;
    }

    // 查找所有课程（审核）
    public List<CourseByAD> searchAllCourse(int isAudit, String institute) {
        conn = DbUtil.getConn();
        List<CourseByAD> courseList = new ArrayList<>(); // 所有结果
        CourseByAD course = null;           // 暂时存放一个课程
        String sql = "select * from CourseByAD where institute = ? ";
        if (isAudit != 0) {
            sql += "and isAudit = " + isAudit;
        }
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, institute);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                course = new CourseByAD();
                course.setTeachID(rs.getInt("teachID"));
                course.setTid(rs.getString("tid"));
                course.setTname(rs.getString("tname"));
                course.setCid(rs.getInt("cid"));
                course.setCname(rs.getString("cname"));
                course.setCapacity(rs.getInt("capacity"));
                course.setStudyTime(rs.getString("studyTime"));
                course.setIsAudit(rs.getInt("isAudit"));
                courseList.add(course);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return courseList;

    }

    // 查找一条记录
    public CourseByAD searchByTeachID(int teachID) {
        conn = DbUtil.getConn();
        CourseByAD course = null;           // 暂时存放一个课程
        String sql = "select * from CourseByAD where teachID = ?";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, teachID);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                course = new CourseByAD();
                course.setTeachID(rs.getInt("teachID"));
                course.setTid(rs.getString("tid"));
                course.setTname(rs.getString("tname"));
                course.setCid(rs.getInt("cid"));
                course.setCname(rs.getString("cname"));
                course.setCapacity(rs.getInt("capacity"));
                course.setStudyTime(rs.getString("studyTime"));
                course.setDescription(rs.getString("description"));
                course.setIsAudit(rs.getInt("isAudit"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return course;

    }

    // 修改审核状态（审核）
    public boolean updateAudit(int teachID, int isAudit) {
        conn = DbUtil.getConn();
        boolean flag = false;
        String sql = "update courseByAD set isAudit = ? where teachID = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, isAudit);
            pres.setInt(2, teachID);
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                // 插入成功
                flag = true;
                System.out.println("更新成功");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return flag;

    }
}
