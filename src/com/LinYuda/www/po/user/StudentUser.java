package com.LinYuda.www.po.user;


import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.menu.ShoppingCar;
import com.LinYuda.www.util.jdbcUtil.JDBCUtil;
import com.LinYuda.www.view.errorView.MessageView;
import com.LinYuda.www.view.errorView.SQLExceptionView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在此定义了一些只有 学生，老师 才会用到 的方法，其他等级的无法使用
 */
public class StudentUser {


    /**
     * 一般用户的下单方法，在确定订单界面调用
     *
     * @param id 用户id
     */
    public static void takeOrder(long id) {
        //获取购物车
        ProductMenu[] productMenus = ShoppingCar.getShoppingCarInstance().getShoppingCar();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            int count1 = 0;
            int count2 = 0;
            for (int i = 0; i < productMenus.length; i++) {

                String date = new SimpleDateFormat("MM-dd hh:mm").format(new Date());
                String insertOrder = "insert into t_order(order_meal_no, order_meal_amount, order_person_id, order_time, send_time, status) values (?,?,?,?,?,?);";
                preparedStatement = connection.prepareStatement(insertOrder);
                preparedStatement.setLong(1, productMenus[i].id);
                preparedStatement.setInt(2, 1);
                preparedStatement.setLong(3, id);
                preparedStatement.setString(4, date);
                preparedStatement.setString(5, date);
                preparedStatement.setInt(6, 0);
                count1 += preparedStatement.executeUpdate();

                productMenus[i].amount--;
                String updateAmountSql = "update t_menu set amount = ? where id = ?";
                preparedStatement = connection.prepareStatement(updateAmountSql);
                preparedStatement.setLong(1, productMenus[i].amount);
                preparedStatement.setLong(2, productMenus[i].id);
                count2 += preparedStatement.executeUpdate();

            }
            if (count1 == productMenus.length && count2 == productMenus.length) {
                connection.commit();
                new MessageView("提交订单", "下单成功");
            } else {
                connection.rollback();
                new MessageView("订单提交失败", "订单提交失败，请联系客服");
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            //清空购物车并重新获取购物车
            ShoppingCar.getShoppingCarInstance().getSelectedMeal().clear();
            ShoppingCar.getShoppingCarInstance().setAmount(0);
            ShoppingCar.getShoppingCarInstance().setTotalPrice(0);
            JDBCUtil.closeConnection(connection, preparedStatement);
        }

    }


    /**
     * 获取历史记录订单，并按照一定格式换成String，
     *
     * @param id 用户的id
     * @return 订单信息的String[]
     */

    public static String[] getHistoryOrder(long id) {
        String[] returnValues = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int size = 0;
        try {
            connection = JDBCUtil.getConnection();
            //language=MySQL
            String selectSql = " select menu_table.meal_name,order_table.order_meal_amount,menu_table.cook_no,order_table.order_time,order_table.status from t_order order_table join t_menu menu_table where order_table.order_meal_no = menu_table.id and order_table.order_person_id = ?";
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setLong(1, id);

            //执行sql语句以获取长度，创建数组
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                size++;
            }
            returnValues = new String[size];


            //执行sql语句以获取对象并存入数组

            resultSet = preparedStatement.executeQuery();
            for (int i = 0; i < size && resultSet.next(); i++) {

                String mealName = resultSet.getString("meal_name");
                String orderMealAmount = resultSet.getString("order_meal_amount");
                long cookNo = resultSet.getLong("cook_no");
                String orderTime = resultSet.getString("order_time");
                String status = resultSet.getInt("status") == 1 ? "以送出" : "未送出";

                StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append("菜名：").append(mealName);
                stringBuffer.append("  数量：").append(orderMealAmount);
                stringBuffer.append("  厨师编号：").append(cookNo);
                stringBuffer.append("  下单时间：").append(orderTime);
                stringBuffer.append("  状态：").append(status);

                returnValues[i] = stringBuffer.toString();
            }

        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
            return returnValues;
        }


    }

}
