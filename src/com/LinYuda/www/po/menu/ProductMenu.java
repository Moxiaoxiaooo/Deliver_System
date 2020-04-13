package com.LinYuda.www.po.menu;

import com.LinYuda.www.util.jdbcUtil.JDBCUtil;
import com.LinYuda.www.view.errorView.SQLExceptionView;

import java.sql.*;

/**
 * 菜单类
 * 主要是获取数据库中的菜单的信息
 * 包括所有与菜单相关的操作方法
 */
public class ProductMenu implements Comparable {
    /**
     * 定义各种类型的菜品对应的编号
     */
    public static final int ALL = 0;
    public static final int YUE = 1;
    public static final int CHUAN = 2;
    public static final int XIANG = 3;

    /**
     * 定义menu里面对应的字段
     * id
     * 菜名
     * 价格
     * 对应厨师编号
     * 菜系
     * 剩余数量
     * 窗口编号
     */
    public long id;
    public String mealName;
    public double price;
    public long cookNo;
    public String mealType;
    public long amount;
    public long windowNo;


    /**
     * 获取数据库中的菜单数据
     *
     * @param type 菜品类型，Menu类中有对应的常量可以调用
     * @return 查询的Menu结果集
     */
    public static ProductMenu[] getMenu(int type) {

        ProductMenu[] returnValues = null;
        String typeName = getTypeName(type);
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            connection = JDBCUtil.getConnection();
            statement = connection.createStatement();

            if (typeName != null) {

                String sql = "select * from t_menu where meal_type = '" + typeName + "'";
                resultSet = statement.executeQuery(sql);
                int size = 0;
                while (resultSet.next()) {
                    size++;
                }
                returnValues = new ProductMenu[size];
                for (int i = 0; i < size; i++) {
                    returnValues[i] = new ProductMenu();
                }
                resultSet = statement.executeQuery(sql);

            } else {

                String sql = "select id,meal_name,price,cook_no,amount,window_no,meal_type from t_menu";
                resultSet = statement.executeQuery(sql);
                int size = 0;
                while (resultSet.next()) {
                    size++;
                }
                returnValues = new ProductMenu[size];
                for (int i = 0; i < size; i++) {
                    returnValues[i] = new ProductMenu();
                }
                resultSet = statement.executeQuery(sql);

            }

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
            return returnValues;
        } catch (SQLException e) {
            new SQLExceptionView();
        } finally {
            JDBCUtil.closeConnection(connection, statement, resultSet);
            return returnValues;
        }

    }


    /**
     * 获取菜单类型的名字
     * 将数字类型转换为字符串类型
     *
     * @param type 要查询名字的类型常量
     * @return 对应的名字
     */
    public static String getTypeName(int type) {
        switch (type) {
            case ProductMenu.YUE:
                return "粤菜";
            case ProductMenu.CHUAN:
                return "川菜";
            case ProductMenu.XIANG:
                return "湘菜";
            default:
                return null;
        }
    }


    /**
     * 用于查询菜品的方法
     * 传入要查询的菜品名字，返回包含的Menu数组
     * 如果查不则返回null
     *
     * @param searchName 要搜索的名字
     * @return 包含查询结果的所有菜品
     */
    public static ProductMenu[] getSearchMenu(String searchName) {

        ProductMenu[] productMenus = ProductMenu.getMenu(ProductMenu.ALL);
        ProductMenu[] returnValues = null;
        boolean[] check = new boolean[productMenus.length];

        int size = 0;
        for (int i = 0; i < productMenus.length; i++) {
            if (productMenus[i].mealName.contains(searchName)) {
                size++;
                check[i] = true;
            } else {
                check[i] = false;
            }
        }

        returnValues = new ProductMenu[size];

        for (int i = 0; i < returnValues.length; i++) {
            returnValues[i] = new ProductMenu();
        }

        int count = 0;
        for (int i = 0; i < productMenus.length; i++) {

            if (check[i]) {
                returnValues[count] = productMenus[i];
                count++;
            }

        }

        if (size != 0) {
            return returnValues;
        } else {
            return null;
        }

    }


    /**
     * 菜品对象的toString()方法
     * 显示了菜系，菜名，价格，剩余数量，窗口号
     *
     * @return 菜品对象的显示格式
     */
    @Override
    public String toString() {
        return "菜系：" + mealType + "  菜名：" + this.mealName + "  价格：" + this.price + "  剩余数量：" + this.amount + "  窗口编号：" + this.windowNo;
    }

    /**
     * Menu对象的compareTo方法
     * 主要是用于排序显示内容
     *
     * @param o 一个用于比较的对象
     * @return 一个比较值
     */
    @Override
    public int compareTo(Object o) {
        ProductMenu temp = null;
        if (o instanceof ProductMenu) {
            temp = (ProductMenu) o;
        }

        return this.mealName.compareTo(temp.mealName);

    }
}
