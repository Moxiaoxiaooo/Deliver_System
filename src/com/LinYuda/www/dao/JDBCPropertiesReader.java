package com.LinYuda.www.dao;

import java.util.ResourceBundle;

/**
 * SQL配置文件读取类
 */
public class JDBCPropertiesReader {

    public static final ResourceBundle rb = ResourceBundle.getBundle("com/LinYuda/www/dao/JDBC");

    private JDBCPropertiesReader() {
    }

    /**
     * 获取数据库名字
     *
     * @return 数据库名字
     */
    public static String getDriverName() {
        String driverName = rb.getString("DriverName");
        return driverName;
    }

    /**
     * 获取数据库连接的url
     *
     * @return String 数据库的URL
     */
    public static String getUrl() {
        String url = rb.getString("Url");
        return url;
    }

    /**
     * 获取数据库帐号
     *
     * @return 数据库帐号
     */
    public static String getUserName() {
        String userName = rb.getString("UserName");
        return userName;
    }

    /**
     * 获取数据库连接密码
     *
     * @return 数据库连接密码
     */
    public static String getPassword() {
        String password = rb.getString("Password");
        return password;
    }
}
