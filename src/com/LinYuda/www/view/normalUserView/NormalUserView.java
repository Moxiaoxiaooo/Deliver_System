package com.LinYuda.www.view.normalUserView;

import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.menu.ShoppingCar;
import com.LinYuda.www.view.errorView.MessageView;
import com.LinYuda.www.view.userView.UpDatePasswordView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 学生和老师用户的主要界面
 * 包括显示各种各样的操作按钮，显示商品等等
 */
public class NormalUserView extends JFrame {

    public NormalUserView(long id) {
        super("WE广工饭堂外卖");

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //获取菜单

        ProductMenu[] productMenus = ProductMenu.getMenu(ProductMenu.ALL);

        //创建菜品栏

        JList<ProductMenu> jList = new JList<>(productMenus);
        GridBagConstraints jListGridBagConstraints = new GridBagConstraints();
        jListGridBagConstraints.gridx = 1;
        jListGridBagConstraints.gridy = 2;
        jListGridBagConstraints.gridheight = 8;
        jListGridBagConstraints.gridwidth = 6;
        jListGridBagConstraints.fill = GridBagConstraints.BOTH;
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane, jListGridBagConstraints);


        //搜索框

        JTextField searchField = new JTextField();
        GridBagConstraints searchFieldGridBagConstraints = new GridBagConstraints();
        searchFieldGridBagConstraints.gridx = 1;
        searchFieldGridBagConstraints.gridy = 1;
        searchFieldGridBagConstraints.gridwidth = 6;
        searchFieldGridBagConstraints.gridheight = 1;
        searchFieldGridBagConstraints.fill = GridBagConstraints.BOTH;
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchField.getText();
                ProductMenu[] productMenuTemp = ProductMenu.getSearchMenu(searchName);
                if (productMenuTemp != null) {
                    jList.removeAll();
                    jList.setListData(productMenuTemp);
                } else {
                    searchField.setText("");
                    new MessageView("查询出错", "查不到包含该名字的菜品");
                }
            }
        });
        container.add(searchField, searchFieldGridBagConstraints);


        //搜索按钮

        createJButton(container, 7, 1, 2, 1, "搜索", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchField.getText();
                ProductMenu[] productMenuTemp = ProductMenu.getSearchMenu(searchName);
                if (productMenuTemp != null) {
                    jList.removeAll();
                    jList.setListData(productMenuTemp);
                } else {
                    searchField.setText("");
                    new MessageView("查询出错", "查不到包含该名字的菜品");
                }
            }
        });


        //查询选择框

        String[] types = {"全部", "粤菜", "川菜", "湘菜"};
        JComboBox jComboBox = new JComboBox(types);
        GridBagConstraints jComboBoxGridBagConstraints = new GridBagConstraints();
        jComboBoxGridBagConstraints.gridx = 7;
        jComboBoxGridBagConstraints.gridy = 7;
        jComboBoxGridBagConstraints.gridwidth = 2;
        jComboBoxGridBagConstraints.gridheight = 1;
        jComboBoxGridBagConstraints.fill = GridBagConstraints.BOTH;
        container.add(jComboBox, jComboBoxGridBagConstraints);


        /**
         * 创建各种按钮
         */

        //创建分类按钮
        createJButton(container, 7, 8, 2, 1, "分类", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int type = jComboBox.getSelectedIndex();
                ProductMenu[] productMenuTemp = ProductMenu.getMenu(type);
                jList.removeAll();
                jList.setListData(productMenuTemp);

            }
        });


        //创建密码修改按钮
        createJButton(container, 0, 0, 2, 1, "修改密码", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpDatePasswordView(id);
            }
        });


        //创建住址修改按钮
        createJButton(container, 2, 0, 2, 1, "修改住址", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改住址，后续扩展");
            }
        });


        //创建联系方式修改按钮
        createJButton(container, 4, 0, 2, 1, "修改用户名", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改联系方式，后续扩展");
            }
        });


        //创建联系方式修改按钮
        createJButton(container, 6, 0, 1, 1, "修改联系方式", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改联系方式，后续扩展");
            }
        });


        //查看我的订单按钮
        createJButton(container, 7, 2, 1, 1, "我的订单", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoryOrderListView(id);
            }
        });


        //加入购物车
        createJButton(container, 7, 3, 1, 1, "加入购物车", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断数量是否为0
                ProductMenu meal = jList.getSelectedValue();
                if (meal != null) {
                    if (meal.amount > 0) {
                        ShoppingCar.getShoppingCarInstance().addInShoppingCar(meal);
                        new MessageView("添加商品", "添加成功");
                    } else {
                        new MessageView("添加商品", "添加失败，该商品已销售完");
                    }
                } else {
                    new MessageView("添加商品", "添加失败，请重新添加");
                }
            }
        });


        //确定订单按钮
        createJButton(container, 7, 4, 1, 1, "确定订单", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckOrderView checkOrderView = new CheckOrderView(id);
                checkOrderView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        ProductMenu[] temp = ProductMenu.getMenu(ProductMenu.ALL);
                        jList.setListData(temp);
                    }
                });
            }
        });


        //查看购物车按钮
        createJButton(container, 7, 5, 1, 1, "查看购物车", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShoppingCarView();
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
     * @param locationX      位置x
     * @param locationY      位置y
     * @param width          宽度
     * @param height         高度
     * @param message        按钮显示内容
     * @param actionListener 监听器
     */

    private void createJButton(Container container, int locationX, int locationY, int width, int height, String message, ActionListener actionListener) {
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

