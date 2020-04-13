package com.LinYuda.www.po.order;


/**
 * 订单类
 * 包含订单的一些信息
 * 以及一些关于获取订单或者设定订单的方法
 */
public class Order {

    /**
     * 定义订单状态常量
     * 1为已完成订单
     * 0为未完成订单
     */
    public static int FINISHED = 1;
    public static int UNFINISHED = 0;

    /**
     * 订单的各种属性
     * id 订单编号
     * orderMealNo 商品编号
     * orderMealAmount 商品数量
     * orderPersonId 订单人编号
     * orderTime 下单时间
     * sendTime  送餐时间
     * status 订单状态（用常量修改该值，1代表已完成订单，0代表未完成订单）
     */
    public long id;
    public long orderMealNo;
    public int orderMealAmount;
    public long orderPersonId;
    public String orderTime;
    public String sendTime;
    public int status;

    /**
     * 定义一些订单中的外部信息，也就是表中的外部信息
     */
    public String mealName;
    public String orderPersonName;
    public String orderPersonLocation;
    public String orderPersonContact;


    /**
     * 重写toString，用于正常显示订单消息
     *
     * @return 订单的消息
     */
    @Override
    public String toString() {

        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("定单人: ").append(orderPersonName);
        stringBuffer.append("   商品：").append(mealName);
        stringBuffer.append("   数量：").append(orderMealAmount);
        stringBuffer.append("   下单时间").append(orderTime);
        stringBuffer.append("   送餐时间").append(sendTime);
        stringBuffer.append("   地址：").append(orderPersonLocation);
        stringBuffer.append("   联系方式：").append(orderPersonContact);
        stringBuffer.append("   当前状态： ").append(status == Order.FINISHED ? "已完成" : "未完成");

        return stringBuffer.toString();
    }
}
