package dao;

import model.Study;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class statisticGradeDao {
    //统计课程平均成绩
    public List countGrade(int Tid, int Cid) {
        Connection conn = DbUtil.getConn();
        List<Study> stuGradeList = new ArrayList<>();

        String sql = "select cid,cname,classno,avg(finalgrade) as allscore from study  where cid=" + Cid + "and teachid=" + Tid + "group by cid,cname,classno  order by allscore";
        // 传sql语句
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                Study stuGrade = new Study();
                stuGrade.setCid((rs.getInt("cid")));
                stuGrade.setCname(rs.getString("cname"));
                stuGrade.setClid(rs.getString("Classno"));
                stuGrade.setFinalGrade(rs.getInt(4));

                stuGradeList.add(stuGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stuGradeList;
    }

    //学生成绩排名
    public List sortGrade(int Tid, int Cid) {
        Connection conn = DbUtil.getConn();
        List<Study> stuGradeList = new ArrayList<>();

        String sql = "select sid,finalGrade,cid,cname,classno from study where cid=" + Cid + " and teachID=" + Tid + " order  by finalgrade desc";
        try {
            PreparedStatement pres = conn.prepareStatement(sql);
            // 执行sql语句
            ResultSet rs = pres.executeQuery();
            while (rs.next()) {
                Study stuGrade = new Study();
                stuGrade.setSid((rs.getString("sid")));

                stuGrade.setFinalGrade(rs.getInt("finalgrade"));
                stuGrade.setCid((rs.getInt("cid")));
                stuGrade.setCname((rs.getString("cname")));
                stuGrade.setClid(rs.getString("classno"));

                stuGradeList.add(stuGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stuGradeList;
    }
}

