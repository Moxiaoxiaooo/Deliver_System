package com.LinYuda.www.po.user;


import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.order.Order;
import com.LinYuda.www.util.jdbcUtil.JDBCUtil;
import com.LinYuda.www.view.errorView.MessageView;
import com.LinYuda.www.view.errorView.SQLExceptionView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 在这里定义
 * 厨师等级会需要用到的方法
 * 包括：
 * 获取订单
 * 提交选中的订单（设置状态）
 */
public class CookUser {

    /**
     * 定义一些修改方法中的修改对象对应的参数
     */
    public static final byte UPDATE_PRODUCT_AMOUNT = 1;
    public static final byte UPDATE_PRODUCT_NAME = 2;
    public static final byte UPDATE_PRODUCT_TYPE = 3;
    public static final byte UPDATE_PRODUCT_PRICE = 4;
    public static final byte UPDATE_PRODUCT_WINDOW_NO = 5;
    public static final byte UPDATE_PRODUCT_COOK_NO = 6;

    /**
     * 获取订单方法
     *
     * @param cookId 厨师id
     * @return 当前厨师id对应的所有订单，包括完成与未完成
     */
    public static Order[] getFullOrders(long cookId) {
        Order[] returnValues = null;
        int size = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String selectSql = "select a.id,a.order_meal_no,a.order_meal_amount,a.order_person_id,a.order_time,a.send_time,a.status,b.meal_name,c.location,c.contact,c.user_name from t_order a left join t_menu b on a.order_meal_no=b.id left join t_normal_user c on c.id = a.order_person_id where b.cook_no = ?";
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setLong(1, cookId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                size++;
            }
            returnValues = new Order[size];
            for (int i = 0; i < size; i++) {
                returnValues[i] = new Order();
            }

            //循环获取基本信息与额外信息
            resultSet = preparedStatement.executeQuery();
            for (int i = 0; resultSet.next(); i++) {

                returnValues[i].id = resultSet.getLong("id");
                returnValues[i].mealName = resultSet.getString("meal_name");
                returnValues[i].orderMealAmount = resultSet.getInt("order_meal_amount");
                returnValues[i].orderMealNo = resultSet.getLong("order_meal_no");
                returnValues[i].orderPersonId = resultSet.getLong("order_person_id");
                returnValues[i].orderTime = resultSet.getString("order_time");
                returnValues[i].sendTime = resultSet.getString("send_time");
                returnValues[i].status = resultSet.getInt("status");
                returnValues[i].orderPersonLocation = resultSet.getString("location");
                returnValues[i].orderPersonContact = resultSet.getString("contact");
                returnValues[i].orderPersonName = resultSet.getString("user_name");

            }


        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
            return returnValues;
        }


    }

    /**
     * 通过完整的订单列表得出未完成的订单列表
     *
     * @param orders 完整的订单列表
     * @return 未完成的订单列表
     */
    public static Order[] getUnfinishedOrders(Order[] orders) {
        Order[] returnValues = null;
        int size = 0;

        //获取长度
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].status == Order.UNFINISHED) {
                size++;
            }
        }

        //创建返回值对象
        returnValues = new Order[size];
        for (int i = 0; i < size; i++) {
            returnValues[i] = new Order();
        }

        //遍历，并塞入返回的对象
        int j = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].status == Order.UNFINISHED) {
                returnValues[j] = orders[i];
                j++;
            }
        }

        return returnValues;

    }


    /**
     * 完成订单方法，需要传入被选中的订单
     * 如果提交成功弹出成功窗口
     * 否则弹出失败窗口
     *
     * @param order 要被提交的订单
     */
    public static void finishOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            String updateSql = "update t_order set status = 1 where id = ? ";
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setLong(1, order.id);
            int influencedRows = preparedStatement.executeUpdate();
            if (influencedRows == 1) {
                new MessageView("提交订单", "提交订单成功");
                connection.commit();
            } else {
                new MessageView("提交订单", "提交订单失败，请确定订单是否已被提交");
                connection.rollback();
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement);
        }

    }

    /**
     * 获取数据库中的菜单数据
     *
     * @param cookId 厨师ID，获取到的是厨师对应的Menu集
     * @return 查询的Menu结果集
     */
    public static ProductMenu[] getCookMenu(long cookId) {

        ProductMenu[] returnValues = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = JDBCUtil.getConnection();
            String sql = "select id,meal_name,price,cook_no,amount,window_no,meal_type from t_menu where cook_no = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, cookId);//BUG HERE!!!!!!!!!!!!!!!!!!!!!!!!
            resultSet = preparedStatement.executeQuery();

            int size = 0;
            while (resultSet.next()) {
                size++;
            }
            returnValues = new ProductMenu[size];
            for (int i = 0; i < size; i++) {
                returnValues[i] = new ProductMenu();
            }

            resultSet = preparedStatement.executeQuery();

            int i = 0;
            while (resultSet.next()) {

                returnValues[i].id = resultSet.getLong("id");
                returnValues[i].mealName = resultSet.getString("meal_name");
                returnValues[i].price = resultSet.getDouble("price");
                returnValues[i].cookNo = resultSet.getLong("cook_no");
                returnValues[i].amount = resultSet.getLong("amount");
                returnValues[i].windowNo = resultSet.getLong("window_no");
                returnValues[i].mealType = resultSet.getString("meal_type");

                i++;
            }
        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
            return returnValues;
        }

    }


    /**
     * 修改商品属性的通用方法(但不能修改商品类型)
     * 修改库存1
     * 修改名字2
     * 修改类型3
     * 修改价格4
     * 修改窗口5
     * 修改厨师编号6
     *
     * @param target      要被修改的对象
     * @param targetField 要被修改的字段（调用类下的常量）
     * @param value       要被修改成的值
     */
    public static void updateProduct(ProductMenu target, byte targetField, Object value) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String updateSql = "";
        try {
            //获取要修改的字段
            switch (targetField) {
                case 1: {
                    updateSql = "update t_menu set amount = ? where id = ? ";
                    break;
                }
                case 2: {
                    updateSql = "update t_menu set meal_name = ? where id = ? ";
                    break;
                }
                case 3: {
                    updateSql = "update t_menu set meal_type = ? where id = ? ";
                    break;
                }
                case 4: {
                    updateSql = "update t_menu set price = ? where id = ? ";
                    break;
                }
                case 5: {
                    updateSql = "update t_menu set window_no = ? where id = ? ";
                    break;
                }
                case 6: {
                    updateSql = "update t_menu set cook_no = ? where id = ? ";
                    break;
                }
                default: {
                    throw new SQLException();
                }
            }

            connection = JDBCUtil.getConnection();

            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setObject(1, value);
            preparedStatement.setObject(2, target.id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 1) {
                connection.commit();
                new MessageView("修改属性", "修改成功");
            } else {
                connection.rollback();
                new MessageView("修改属性", "修改失败，请重启程序");
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement);
        }

    }


    /**
     * 一个方法用来查询厨师编号是否存在
     * 主要用在修改商品属性中厨师编号
     *
     * @param value 要被检索的值
     */
    public static boolean checkCookNo(long value) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean returnValue = false;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id from t_cook where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, value);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                returnValue = true;
            } else {
                returnValue = false;
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
            return returnValue;
        }


    }

    /**
     * 一个方法用来删除在数据库中的目标商品
     *
     * @param target 要被删除的商品对象
     */
    public static void deleteProductMenu(ProductMenu target) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            //先删除外键的信息
            String deleteSql = "delete from t_order where order_meal_no = ?";
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, target.id);
            int rows = preparedStatement.executeUpdate();
            if (rows != 0) {
                //再删除本体
                deleteSql = "delete from t_menu where id = ?";
                preparedStatement = connection.prepareStatement(deleteSql);
                preparedStatement.setLong(1, target.id);
                rows = preparedStatement.executeUpdate();

                if (rows == 1) {
                    connection.commit();
                    new MessageView("删除商品", "删除成功");
                } else {
                    connection.rollback();
                    new MessageView("删除商品", "删除商品失败");
                }

            } else {
                connection.rollback();
                new MessageView("删除商品", "删除商品失败");
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement);
        }


    }


    /**
     * 一个方法用来添加商品
     *
     * @param mealName 名字
     * @param price    价格
     * @param cookNo   对应厨师编号
     * @param mealType 类型
     * @param amount   数量
     * @param windowNo 窗口编号
     */
    public static void insertProductMenu(String mealName, double price, long cookNo, String mealType, long amount, int windowNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            String insertSql = "insert into t_menu(meal_name, price, cook_no, meal_type, amount, window_no) values (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);

            preparedStatement.setString(1, mealName);
            preparedStatement.setDouble(2, price);
            preparedStatement.setLong(3, cookNo);
            preparedStatement.setString(4, mealType);
            preparedStatement.setLong(5, amount);
            preparedStatement.setInt(6, windowNo);

            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                connection.commit();
                new MessageView("添加商品", "添加成功");
            } else {
                connection.rollback();
                new MessageView("添加商品", "添加失败，或许商品已存在");
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement);
        }
    }


    /**
     * 一个方法用来检查上级的id是否存在
     * 存在返回true
     * 不存在返回false
     *
     * @param id 要查阅的id
     */
    public static boolean checkSuperiorId(long id) {
        boolean returnValue = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String selectSql = "select id from t_assistant_manager where id=?";
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                returnValue = true;
            } else {
                returnValue = false;
            }
        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
            return returnValue;
        }

    }

}
