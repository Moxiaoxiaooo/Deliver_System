package com.LinYuda.www.service;


import com.LinYuda.www.po.user.CookUser;
import com.LinYuda.www.util.jdbcUtil.JDBCUtil;
import com.LinYuda.www.view.cookView.CookView;
import com.LinYuda.www.view.normalUserView.NormalUserView;
import com.LinYuda.www.view.errorView.MessageView;
import com.LinYuda.www.view.errorView.SQLExceptionView;

import java.sql.*;

public class UserMethod {
    private UserMethod() {
    }


    /**
     * 公用登录方法，执行成功新建登录页面，失败弹窗
     *
     * @param account  登录帐号
     * @param password 登录密码
     */
    public static boolean login(String account, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean returnValue = false;
        try {
            connection = JDBCUtil.getConnection();

            String sql = "select * from t_user where user_account= ? and user_password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getInt("id");
                int permission_level = resultSet.getInt("permission_level");
                switch (permission_level) {
                    case 1:
                    case 2: {
                        new NormalUserView(id);
                        returnValue = true;
                        break;
                    }
                    case 3: {
                        new CookView(id);
                        returnValue = true;
                        break;
                    }
                    case 4: {
                        break;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        returnValue = false;
                        new MessageView("登录失败", "账号信息有误，请联系客服修改");
                    }
                }
            } else {
                new MessageView("登录失败", "帐号或密码输入错误，请重新输入");
            }
        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
            return returnValue;
        }

    }

    /**
     * 通用注册方法，如果是普通用户注册，则最后两项参数为0即可
     *
     * @param account         账号
     * @param password        密码
     * @param permissionLevel 权限等级
     * @param userName        用户名字
     * @param location        住址
     * @param contact         联系方式
     * @param superiorId      上级id
     * @param windowNo        负责的窗口编号
     */
    public static void register(String account, String password, int permissionLevel, String userName, String location, String contact, long superiorId, int windowNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            //检索用户信息是否存在
            String checkAccountSql = "select id from t_user where user_account= ? ";
            preparedStatement = connection.prepareStatement(checkAccountSql);
            preparedStatement.setString(1, account);
            if (preparedStatement.executeQuery().next()) {
                new MessageView("注册失败", "帐号已经存在");
            } else {

                //插入到通用的用户表
                connection.setAutoCommit(false);
                String registerSql = "insert into t_user(user_account , user_password , permission_level) values( ? , ? , ? )";
                preparedStatement = connection.prepareStatement(registerSql);
                preparedStatement.setString(1, account);
                preparedStatement.setString(2, password);
                preparedStatement.setInt(3, permissionLevel);
                int influencedRowsCount = preparedStatement.executeUpdate();

                if (influencedRowsCount == 1) {
                    //获取通用表格对应的id
                    String getIdSql = "select id from t_user where user_account = ? ";
                    preparedStatement = connection.prepareStatement(getIdSql);
                    preparedStatement.setString(1, account);
                    resultSet = preparedStatement.executeQuery();

                    switch (permissionLevel) {
                        case 1:
                        case 2: {
                            if (resultSet.next()) {
                                //插入到普通用户
                                long id = resultSet.getLong("id");
                                String registerNormalUserSql = "insert into t_normal_user (id,user_name,location,contact)values(?,?,?,?)";
                                preparedStatement = connection.prepareStatement(registerNormalUserSql);
                                preparedStatement.setLong(1, id);
                                preparedStatement.setString(2, userName);
                                preparedStatement.setString(3, location);
                                preparedStatement.setString(4, contact);
                                influencedRowsCount = preparedStatement.executeUpdate();
                                if (influencedRowsCount == 1) {
                                    connection.commit();
                                    new MessageView("注册成功", "注册成功，现在可以登录了");
                                } else {
                                    connection.rollback();
                                    new MessageView("注册失败", "注册失败，请联系客服");
                                }
                            } else {
                                connection.rollback();
                                new MessageView("注册失败", "注册失败，请联系客服");
                            }
                            break;
                        }

                        case 3: {
                            //完善这里的信息
                            if (resultSet.next()) {
                                //插入到厨师用户
                                //检查上级id是否存在
                                if (CookUser.checkSuperiorId(superiorId)) {
                                    long id = resultSet.getLong("id");
                                    String registerCookUserSql = "insert into t_cook (id, cook_name, superior_id, contact, window_no) values (?,?,?,?,?)";
                                    preparedStatement = connection.prepareStatement(registerCookUserSql);
                                    preparedStatement.setLong(1, id);
                                    preparedStatement.setString(2, userName);
                                    preparedStatement.setLong(3, superiorId);
                                    preparedStatement.setString(4, contact);
                                    preparedStatement.setInt(5, windowNo);
                                    influencedRowsCount = preparedStatement.executeUpdate();
                                    if (influencedRowsCount == 1) {
                                        connection.commit();
                                        new MessageView("注册成功", "注册成功，现在可以登录了");
                                    } else {
                                        connection.rollback();
                                        new MessageView("注册失败", "注册失败，请联系客服");
                                    }
                                } else {
                                    connection.rollback();
                                    new MessageView("注册失败", "注册失败，上级Id不存在");
                                }
                            } else {
                                connection.rollback();
                                new MessageView("注册失败", "注册失败，请联系客服");
                            }
                            break;
                        }
                        case 4: {
                            break;
                        }
                        case 5: {
                            break;
                        }
                        default: {
                            new MessageView("注册过程出现问题", "请向客服反馈该问题");
                        }
                    }

                } else {
                    new MessageView("注册过程出现问题", "请向客服反馈该问题");
                }

            }
        } catch (SQLException e) {
            new SQLExceptionView();
        }
    }

    /**
     * 共用修改密码方法
     *
     * @param id       要被修改密码对应账号的id
     * @param password 要被修改的密码
     */
    public static void updatePassword(long id, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = JDBCUtil.getConnection();
            String sql = "update t_user set user_password = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);

            int count = -1;
            count = preparedStatement.executeUpdate();

            if (count == -1) {
                new MessageView("修改密码出错", "修改密码出错，你不是在乱搞");
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement);
        }

    }


    /**
     * 修改用户名字的方法（该方法未被用上，可留到未来做扩展用）
     *
     * @param id   要被修改名字对应帐号的id
     * @param name 要被修改的用户名
     */
    public static void updateUserName(long id, String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = JDBCUtil.getConnection();
            String sql = "update t_normal_user set user_name = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            int count = -1;
            count = preparedStatement.executeUpdate();

            if (count == -1) {
                new MessageView("修改名字出错", "修改名字出错，你不是在乱搞");
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement);
        }

    }

}