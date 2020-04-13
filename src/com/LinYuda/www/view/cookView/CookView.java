package com.LinYuda.www.view.cookView;

import com.LinYuda.www.po.order.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.LinYuda.www.po.user.CookUser;
import com.LinYuda.www.view.errorView.MessageView;
import com.LinYuda.www.view.userView.UpDatePasswordView;

/**
 * 厨师级的用户界面
 * 有关订单处理的方法都会在这个界面
 * 包括：提交订单，查询所有订单
 */
public class CookView extends JFrame {
    public CookView(long id) {

        super("厨师管理端");

        setSize(860, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //获取订单
        Order[] orders = CookUser.getUnfinishedOrders(CookUser.getFullOrders(id));
        //创建当前订单列表
        JList<Order> jList = new JList<>(orders);
        GridBagConstraints jListGridBagConstraints = new GridBagConstraints();
        jListGridBagConstraints.gridx = 1;
        jListGridBagConstraints.gridy = 2;
        jListGridBagConstraints.gridheight = 8;
        jListGridBagConstraints.gridwidth = 6;
        jListGridBagConstraints.weighty = 8;
        jListGridBagConstraints.weightx = 6;
        jListGridBagConstraints.fill = GridBagConstraints.BOTH;
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane, jListGridBagConstraints);

        //订单数量
        JLabel orderAmountNoticeLabel = new JLabel("当前订单数量" + orders.length);
        GridBagConstraints orderAmountNoticeLabelGridBagConstraints = new GridBagConstraints();
        orderAmountNoticeLabelGridBagConstraints.gridx = 1;
        orderAmountNoticeLabelGridBagConstraints.gridy = 0;
        orderAmountNoticeLabelGridBagConstraints.gridwidth = 6;
        orderAmountNoticeLabelGridBagConstraints.gridheight = 1;
        orderAmountNoticeLabelGridBagConstraints.fill = GridBagConstraints.BOTH;
        container.add(orderAmountNoticeLabel, orderAmountNoticeLabelGridBagConstraints);


        /*
         * 创建各种按钮
         **/

        //刷新按钮
        createJButton(container, 7, 2, 2, 1, "刷新", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order[] temp = CookUser.getUnfinishedOrders(CookUser.getFullOrders(id));
                jList.setListData(temp);
                orderAmountNoticeLabel.setText("当前订单数量" + temp.length);
                new MessageView("刷新", "刷新成功");
            }
        });


        //创建密码修改按钮
        createJButton(container, 0, 1, 2, 1, "修改密码", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpDatePasswordView(id);
            }
        });


        //创建住址修改按钮
        createJButton(container, 2, 1, 2, 1, "修改住址", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改住址，该方法尚未实现，可作为扩展");
            }
        });


        //创建联系方式修改按钮
        createJButton(container, 4, 1, 2, 1, "修改用户名", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改联系方式，该方法尚未实现，可作为扩展");
            }
        });


        //创建联系方式修改按钮
        createJButton(container, 6, 1, 1, 1, "修改联系方式", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改联系方式，该方法尚未实现，可作为扩展");
            }
        });


        //提交选中订单按钮
        createJButton(container, 7, 3, 1, 1, "提交选中订单", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = jList.getSelectedValue();
                if (order != null) {
                    CookUser.finishOrder(order);
                    Order[] temp = CookUser.getUnfinishedOrders(CookUser.getFullOrders(id));
                    orderAmountNoticeLabel.setText("当前订单数量" + temp.length);
                    jList.setListData(temp);
                } else {
                    new MessageView("提交订单错误", "请选中要提交的订单");
                }
            }
        });


        //修改商品属性按钮
        createJButton(container, 7, 4, 1, 1, "修改商品属性", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditMenuView(id);
            }
        });


        //查看订单按钮
        createJButton(container, 7, 5, 1, 1, "查看所有订单", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderListView(id);
            }
        });


        setResizable(false);
        setVisible(true);
    }


    /**
     * 创建按钮的方法
     * 传入相对应的条件与容器，监听器即可
     *
     * @param container      容器
     * @param locationX      按钮x轴
     * @param locationY      按钮y轴
     * @param width          按钮宽度
     * @param height         按钮高度
     * @param message        按钮要显示的信息
     * @param actionListener 监听器
     */

    void createJButton(Container container, int locationX, int locationY, int width, int height, String message, ActionListener actionListener) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = locationX;
        gridBagConstraints.gridy = locationY;
        gridBagConstraints.gridheight = height;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        JButton jButton = new JButton(message);
        jButton.addActionListener(actionListener);

        container.add(jButton, gridBagConstraints);

    }

}

