package com.LinYuda.www.util.jdbcUtil;

import com.LinYuda.www.dao.JDBCPropertiesReader;
import com.LinYuda.www.view.errorView.ClassNotFoundExceptionView;
import com.LinYuda.www.view.errorView.SQLExceptionView;

import java.sql.*;

/**
 * JDBC工具类
 */
public class JDBCUtil {

    /**
     * 注册驱动
     */
    private JDBCUtil() {
    }

    /**
     * 获取数据库连接的方法
     * 该方法通过调用JDBCPropertiesReader类里面的方法读取配置文件信息
     *
     * @return 数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(JDBCPropertiesReader.getDriverName());
        } catch (ClassNotFoundException e) {
            new ClassNotFoundExceptionView();
        }
        String sqlUserName = JDBCPropertiesReader.getUserName();
        String sqlPassword = JDBCPropertiesReader.getPassword();
        String sqlUrl = JDBCPropertiesReader.getUrl();

        Connection connection = DriverManager.getConnection(sqlUrl, sqlUserName, sqlPassword);
        return connection;

    }

    /**
     * 关闭数据库读取资源
     *
     * @param connection 要被关闭的连接
     * @param statement 要被关闭的操作对象
     * @param resultSet 要被关闭的结果集
     */
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                new SQLExceptionView();
            }
        }
        closeConnection(connection, statement);
    }

    /**
     * 关闭数据库读取资源
     *
     * @param connection 要被关闭的连接
     * @param statement 要被关闭的操作对象
     */
    public static void closeConnection(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                new SQLExceptionView();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                new SQLExceptionView();
            }
        }
    }

}
