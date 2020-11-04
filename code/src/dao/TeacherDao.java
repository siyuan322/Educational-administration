package dao;

import model.CourseByTeacher;
import model.Teach;
import model.Teacher;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TeacherDao {
    // 登录
    public Teacher login(String id, String password) {
        Connection conn = DbUtil.getConn();
        Teacher teacher = null;
        String sql = "select * from teacher where tid = ? and password = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            pres.setString(2, password);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setTid(rs.getString("tid"));
                teacher.setPassword(rs.getString("password"));
                teacher.setInstitute(rs.getString("institute"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return teacher;
    }

    // 获取数据库中所有的该学院的课程名
    public Vector<String> searchCourseByInstitute(String institute) {
        Connection conn = DbUtil.getConn();
        Vector<String> courseNames = new Vector<>(); // 所有结果
        String cname = null;           // 暂时存放一个课程
        String sql = "select cname from course where institute = ? ";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, institute);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                cname = rs.getString("cname");
                courseNames.add(cname);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return courseNames;
    }

    // 根据课程名和学院获取课程号
    public int searchCid(String cname, String institute) {
        Connection conn = DbUtil.getConn();
        int cid = 0;
        String sql = "select * from course where cname = ? and institute = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, cname);
            pres.setString(2, institute);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                cid = rs.getInt("cid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return cid;
    }

    // 获取教学班编号，根据课程号，老师id，上课时间
    public int getTeachID(int cid, String tid, String studyTime) {
        Connection conn = DbUtil.getConn();
        int teachID = 0;
        String sql = "select teachID from teach where cid = ? and tid = ? and studyTime = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, cid);
            pres.setString(2, tid);
            pres.setString(3, studyTime);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                teachID = rs.getInt("teachID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return teachID;

    }

    // 新增课程,返回教学班号
    public int addCourse(Teach teach) {
        Connection conn = DbUtil.getConn();
        int teachID = 0;
        String sql = "insert into teach(cid, tid, capacity, studyTime, description, institute) values(?,?,?,?,?,?)";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, teach.getCid());
            pres.setString(2, teach.getTid());
            pres.setInt(3, teach.getCapacity());
            pres.setString(4, teach.getStudyTime());
            pres.setString(5, teach.getDescription());
            pres.setString(6, teach.getInstitute());
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
                System.out.println("插入成功");
            }
            // 获取课程号
            teachID = this.getTeachID(teach.getCid(), teach.getTid(), teach.getStudyTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return teachID;

    }

    // 上课时间是否冲突
    public boolean isConflict(String tid, String studyTime) {
        Connection conn = DbUtil.getConn();
        boolean flag = false;
        String sql = "select * from teach where tid = ? and studyTime = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, tid);
            pres.setString(2, studyTime);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return flag;

    }

    // 获取所有课程
    public List<CourseByTeacher> searchAllCourse(String tid) {
        Connection conn = DbUtil.getConn();
        List<CourseByTeacher> courseList = new ArrayList<>(); // 所有结果
        CourseByTeacher course = null;           // 暂时存放一个课程
        String sql = "select * from CourseByTeacher where tid = ? ";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, tid);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                course = new CourseByTeacher();
                course.setTeachID(rs.getInt("teachID"));
                course.setCname(rs.getString("cname"));
                course.setChour(rs.getString("chour"));
                course.setCcredit(rs.getFloat("ccredit"));
                course.setCapacity(rs.getInt("capacity"));
                course.setCurrentNum(rs.getInt("currentNum"));
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

    // 根据条件查找课程
    public List<CourseByTeacher> searchSomeCourse(String tid, String cname, int isAudit) {
        Connection conn = DbUtil.getConn();
        List<CourseByTeacher> courseList = new ArrayList<>(); // 所有结果
        CourseByTeacher course = null;           // 暂时存放一个课程
        String sql = "select * from CourseByTeacher where tid = ?";
        if (isAudit != 0) {
            sql += " and isAudit = " + isAudit;
        }
        if (!"".equals(cname)) {
            sql += " and cname = '" + cname + "'";
        }
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setString(1, tid);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                course = new CourseByTeacher();
                course.setTeachID(rs.getInt("teachID"));
                course.setCname(rs.getString("cname"));
                course.setChour(rs.getString("chour"));
                course.setCcredit(rs.getFloat("ccredit"));
                course.setCapacity(rs.getInt("capacity"));
                course.setCurrentNum(rs.getInt("currentNum"));
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

    // 根据教学班编号查找课程
    public CourseByTeacher searchByTeachID(int teachID) {
        Connection conn = DbUtil.getConn();
        CourseByTeacher course = null; // 所有结果
        String sql = "select * from CourseByTeacher where teachID = ?";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, teachID);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                course = new CourseByTeacher();
                course.setTeachID(rs.getInt("teachID"));
                course.setCname(rs.getString("cname"));
                course.setChour(rs.getString("chour"));
                course.setCcredit(rs.getFloat("ccredit"));
                course.setCapacity(rs.getInt("capacity"));
                course.setCurrentNum(rs.getInt("currentNum"));
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

    // 根据课程号和老师id号查找课程
    public Teach searchCourseByCTid(int cid, String tid, String studyTime) {
        Connection conn = DbUtil.getConn();
        Teach teach = null;
        String sql = "select * from teach where cid = ? and tid = ? and studyTime = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, cid);
            pres.setString(2, tid);
            pres.setString(3, studyTime);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            if (rs.next()) {
                teach = new Teach();
                teach.setCid(cid);
                teach.setCapacity(rs.getInt("capacity"));
                teach.setStudyTime(rs.getString("studyTime"));
                teach.setDescription(rs.getString("description"));
                teach.setAudit(rs.getInt("isAudit"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtil.closeConn(conn);
        }
        return teach;
    }

    // 更新课程
    public boolean updateCourse(int teachID, int capacity, String studyTime, String description, int isAudit) {
        Connection conn = DbUtil.getConn();
        String sql = "update teach set capacity=?, studyTime=?, description=?, isAudit = ? where teachID = ?";
        boolean flag = false;
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, capacity);
            pres.setString(2, studyTime);
            pres.setString(3, description);
            pres.setInt(4, isAudit);
            pres.setInt(5, teachID);
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

    // 删除课程
    public boolean deleteCourse(int teachID) {
        Connection conn = DbUtil.getConn();
        boolean flag = false;
        String sql = "delete from teach where teachID = ?";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            pres.setInt(1, teachID);
            // 执行sql语句
            int number = pres.executeUpdate();
            if (number != 0) {
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
