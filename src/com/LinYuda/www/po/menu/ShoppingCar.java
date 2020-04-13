package com.LinYuda.www.po.menu;


import com.LinYuda.www.view.errorView.MessageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 定义购物车
 * 购物车为单例模式
 * 内包括：总价格，一个set集合用来存储已选的菜品
 */
public class ShoppingCar {

    private static ShoppingCar shoppingCar = new ShoppingCar();
    private double totalPrice = 0;
    private int amount = 0;
    ArrayList<ProductMenu> selectedMeal = new ArrayList<>();

    private ShoppingCar() {
    }

    /**
     * 获取购物车操作对象
     *
     * @return 购物车对象
     */
    public static ShoppingCar getShoppingCarInstance() {
        return shoppingCar;
    }

    /**
     * 添加选中的商品到购物车
     *
     * @param meal 要添加的商品
     */
    public void addInShoppingCar(ProductMenu meal) {

        shoppingCar.selectedMeal.add(meal);
        shoppingCar.totalPrice += meal.price;
        shoppingCar.amount++;

    }

    /**
     * 从购物车中移除商品
     *
     * @param meal 要移除的商品
     */
    public void removeFromShoppingCar(ProductMenu meal) {
        if (meal != null) {
            shoppingCar.selectedMeal.remove(meal);
            shoppingCar.totalPrice -= meal.price;
            shoppingCar.amount--;
        } else {
            new MessageView("移除失败", "请选中商品再移除");
        }
    }

    /**
     * 获取购物车中的所有Menu对象，也就是所有菜品
     *
     * @return ProductMenu数组，即为所有加入购物车的对象
     */
    public ProductMenu[] getShoppingCar() {
        int size = shoppingCar.selectedMeal.size();
        Iterator iterator = shoppingCar.selectedMeal.iterator();

        ProductMenu[] returnValues = new ProductMenu[size];

        for (int i = 0; i < size; i++) {

            returnValues[i] = new ProductMenu();

            Object temp = iterator.next();
            if (temp instanceof ProductMenu) {
                returnValues[i] = (ProductMenu) temp;
            }

        }

        Arrays.sort(returnValues);
        return returnValues;

    }

    /**
     * 获取总价格
     *
     * @return 购物车的商品总价格
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 获取购物车的商品数量
     *
     * @return 获取购物车的商品数量
     */
    public int getAmount() {
        return amount;
    }

    /**
     * 获取购物车储存对象
     *
     * @return 购物车的存储集合
     */
    public ArrayList<ProductMenu> getSelectedMeal() {
        return selectedMeal;
    }

    /**
     * 设置总价格
     *
     * @param totalPrice 总价格
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 设置总数量
     *
     * @param amount 总数量
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
