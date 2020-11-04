package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 连接数据库
public class DbUtil {
    private static String dbUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Student";    // 数据库连接地址
    private static String user = "sa";
    private static String password = "123456";
    private static String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    // 驱动名称

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(jdbcName);
            conn = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConn(Connection conn) {
        try {
            if (conn!=null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
