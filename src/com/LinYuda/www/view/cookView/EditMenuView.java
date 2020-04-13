package com.LinYuda.www.view.cookView;


import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.user.CookUser;
import com.LinYuda.www.view.errorView.MessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 厨师修改商品界面
 * 包括的操作需要有：
 * 修改库存
 * 修改名字
 * 修改类型(这个用独立的窗口)
 * 修改价格
 * 修改窗口
 * 修改厨师编号
 * (用一个通用的修改方法搞定上面的)
 * 下架商品
 * 新建商品：
 * 库存，名字，价格，窗口号，类型，数量
 * <p>
 * 这些仅仅只是按钮，实现方法应该在CookUser里面实现方法
 */
public class EditMenuView extends JFrame {
    public EditMenuView(long id) {

        super("设置商品");
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //创建一个列表显示当前厨师下的商品
        ProductMenu[] productMenus = CookUser.getCookMenu(id);
        JList<ProductMenu> jList = new JList<>(productMenus);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GridBagConstraints jListGridBagConstraints = createGridBagConstraints(0, 0, 8, 8);
        jListGridBagConstraints.fill = GridBagConstraints.BOTH;

        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane, jListGridBagConstraints);


        //修改库存按钮
        createJButton(container, 8, 0, 1, 1, "修改库存", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductMenu target = jList.getSelectedValue();
                if (target != null) {
                    EditFieldView editFieldView = new EditFieldView("修改数量", target, CookUser.UPDATE_PRODUCT_AMOUNT);
                    //添加窗口关闭事件：刷新编辑窗口的商品信息
                    editFieldView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            ProductMenu[] temp = CookUser.getCookMenu(id);
                            jList.setListData(temp);
                        }
                    });
                } else {
                    new MessageView("修改数量", "请选中商品");
                }

            }
        });


        //修改名字按钮
        createJButton(container, 8, 1, 1, 1, "修改名字", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductMenu target = jList.getSelectedValue();
                if (target != null) {
                    EditFieldView editFieldView = new EditFieldView("修改名字", target, CookUser.UPDATE_PRODUCT_NAME);
                    editFieldView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            ProductMenu[] temp = CookUser.getCookMenu(id);
                            jList.setListData(temp);
                        }
                    });
                } else {
                    new MessageView("修改名字", "请选中商品");
                }
            }
        });


        //修改类型按钮
        createJButton(container, 8, 2, 1, 1, "修改类型", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("修改类型，该方法尚未实现，可以后续扩展");
            }
        });


        //修改价格按钮
        createJButton(container, 8, 3, 1, 1, "修改价格", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductMenu target = jList.getSelectedValue();
                if (target != null) {
                    EditFieldView editFieldView = new EditFieldView("修改价格", target, CookUser.UPDATE_PRODUCT_PRICE);
                    editFieldView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            ProductMenu[] temp = CookUser.getCookMenu(id);
                            jList.setListData(temp);

                        }
                    });
                } else {
                    new MessageView("修改价格", "请选中商品");
                }
            }
        });


        //修改窗口号按钮
        createJButton(container, 8, 4, 1, 1, "修改窗口号", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductMenu target = jList.getSelectedValue();
                if (target != null) {
                    EditFieldView editFieldView = new EditFieldView("修改窗口号", target, CookUser.UPDATE_PRODUCT_WINDOW_NO);
                    editFieldView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            ProductMenu[] temp = CookUser.getCookMenu(id);
                            jList.setListData(temp);
                        }
                    });
                } else {
                    new MessageView("修改窗口号", "请选中商品");
                }
            }
        });


        //修改厨师编号
        createJButton(container, 8, 5, 1, 1, "修改厨师编号", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductMenu target = jList.getSelectedValue();
                if (target != null) {
                    EditFieldView editFieldView = new EditFieldView("修改厨师编号", target, CookUser.UPDATE_PRODUCT_COOK_NO);
                    editFieldView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            ProductMenu[] temp = CookUser.getCookMenu(id);
                            jList.setListData(temp);
                        }
                    });
                } else {
                    new MessageView("修改厨师编号", "请选中商品");
                }
            }
        });


        //添加商品按钮
        createJButton(container, 8, 6, 1, 1, "添加商品", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductView addProductView = new AddProductView();
                addProductView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        ProductMenu[] temp = CookUser.getCookMenu(id);
                        jList.setListData(temp);
                    }
                });
            }
        });


        //删除选中商品
        createJButton(container, 8, 7, 1, 1, "删除选中商品", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductMenu target = jList.getSelectedValue();
                if (target != null) {
                    CookUser.deleteProductMenu(target);
                } else {
                    new MessageView("删除商品", "请选中商品");
                }
            }
        });

        setResizable(false);
        setVisible(true);
    }

    /**
     * 创建GridBag约束对象的方法
     *
     * @param x      位置x
     * @param y      位置y
     * @param width  宽度
     * @param height 高度
     * @return 该条件下的约束对象
     */
    private static GridBagConstraints createGridBagConstraints(int x, int y, int width, int height) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = height;

        return gridBagConstraints;
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
        GridBagConstraints gridBagConstraints = createGridBagConstraints(locationX, locationY, width, height);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        JButton jButton = new JButton(message);
        jButton.addActionListener(actionListener);

        container.add(jButton, gridBagConstraints);

    }


}
