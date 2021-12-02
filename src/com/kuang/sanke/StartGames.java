package com.kuang.sanke;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartGames {
    public static void main(String[] args) {
        // 绘制静态窗口
        JFrame frame = new JFrame("Java贪吃蛇小游戏Bychs");
        // 设置界面大小
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        DO_NOTHING_ON_CLOSE：当窗口关闭时，不做任何处理； 
//        HIDE_ON_CLOSE：当窗口关闭时，隐藏这个窗口； 
//        DISPOSE_ON_CLOSE：当窗口关闭时，隐藏并处理这个窗口；
//        EXIT_ON_CLOSE：当窗口关闭时，退出程序。 
//        默认是HIDE_ON_CLOSE。    EXIT_ON_CLOSE:是直接退出jvm. 
//        注意：默认是HIDE_ON_CLOSE  即没有加setDefaultCloseOperation.  
//            而  DISPOSE_ON_CLOSE: 主要是 隐藏窗口并dispose这个窗口； 只有当他是最后一个窗口，才会退出JVM

//        frame.setJMenuBar();
        frame.add(new GamePanel());

        // 窗口是否展示
        frame.setVisible(true);

//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                int i = JOptionPane.showConfirmDialog(null, "确认退出吗？");
//                if (i == JOptionPane.OK_OPTION) {
//                    System.exit(0);
//                } else {
//
//                }
//            }
//        });

    }
}
