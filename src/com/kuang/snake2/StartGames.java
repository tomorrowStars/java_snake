package com.kuang.snake2;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        // 绘制静态窗口
        JFrame jFrame = new JFrame("Java贪吃蛇小游戏2.0——ByChenhs");
        jFrame.setBounds(100,10, 530,410);      // 设置窗口位置，大小
        jFrame.setResizable(false);                                 // 是否可以调整大小
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // 设置关闭按钮的动作
        // 调用游戏组件
        jFrame.add(new GamePanel());
        // 放到最后， 否则debgu无法调用
        jFrame.setVisible(true);// 窗口是否显示
    }
}
