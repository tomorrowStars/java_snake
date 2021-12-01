package com.kuang.snake2;

import com.kuang.sanke.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private int length; // 小蛇长度
    private String fx;      //  方向 【R:右】【L:左】【U:上】【D:下】
    private int[] snakeX = new int[400];   // 横向坐标
    private int[] snakeY = new int[400];   // 纵向坐标
    private boolean isStart;               // 是否开始游戏
    // 食物
    int foodX;  // 食物 横坐标
    int foodY;  // 食物 纵坐标
    //  随机数
    Random random = new Random();

    // 定时器
    Timer timer = new Timer(100, this);

    public GamePanel() {
        // 初始化
        this.init();

        // 获取监听事件
        this.setFocusable(true);
        this.addKeyListener(this);

        // 计时器开始
        timer.start();
    }

    // 初始化
    public void init() {
        // 静态小蛇, 方向：向右,长度：3
        this.length = 3;
        this.fx = "R";
        // 头部
        this.snakeX[0] = 100;
        this.snakeY[0] = 100;
        // 身体
        this.snakeX[1] = 75;
        this.snakeY[1] = 100;
        this.snakeX[2] = 50;
        this.snakeY[2] = 100;

        // 重新生成食物坐标
        this.foodX = 25 + 25 * random.nextInt(20);
        this.foodY = 75 + 25 * random.nextInt(12);

        this.isStart = false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent(g);
        // 设置整体背景色
//        super.setBackground(Color.white);
        super.setBackground(new Color(175, 238, 238));
        // 设置展示栏
        ImageData.head.paintIcon(this, g, 10, 10);
        // 设置游戏栏
//        super.setBackground(Color.ORANGE);
        g.fillRect(10, 70, 500, 300);

        // 画一个静态小蛇,
        // 头部
        switch (this.fx) {
            case "R":
                ImageData.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                ImageData.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                ImageData.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                ImageData.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            default:
                // 什么也不做
                break;
        }

//        // 身体
//        ImageData.body.paintIcon(this,g,75,100);    // 第一节身体
//        ImageData.body.paintIcon(this,g,50,100);    // 第二节身体
        // 身体
        for (int i = 1; i < length; i++) {
            ImageData.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 食物
        Data.food.paintIcon(this, g, foodX, foodY);

        // 游戏提示：开始提示
        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 20));
            g.drawString("按空格键开始游戏！", 150, 150);

            g.setColor(Color.ORANGE);
            g.setFont(new Font("宋体", Font.ITALIC, 20));
            g.drawString("按回车结束游戏!", 150, 180);
        }


    }


    /**
     * 按下某个键
     *
     * @param e 键盘事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //  空格键按下
        if (keyCode == KeyEvent.VK_SPACE) {
            super.repaint();    //刷新画面
            this.isStart = !this.isStart;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }

    // 按下并释放某个键
    @Override
    public void keyTyped(KeyEvent e) {

    }


    // 释放某个键
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * 执行定时操作
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 游戏已开始则执行以下动作
        if (isStart == true) {
            // 身体移动
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            // 右侧移动
            if (fx.equals("R")) {
                // 头部移动
                if (snakeX[0] > 500) {
                    // todo 到边界时应该报错误信息
                    snakeX[0] = 25;
                } else {
                    snakeX[0] = snakeX[0] + 25;
                }
            } else if (fx.equals("L")) {
                // 头部移动
                if (snakeX[0] < 50) {
                    // todo 到边界时应该报错误信息
                    snakeX[0] = 500;
                } else {
                    snakeX[0] = snakeX[0] - 25;
                }
            } else if (fx.equals("U")) {
                // 头部移动
                if (snakeY[0] < 90) {
                    // todo 到边界时应该报错误信息
                    snakeY[0] = 300;
                } else {
                    snakeY[0] = snakeY[0] - 25;
                }
            } else if (fx.equals("D")) {
                // 头部移动
                if (snakeY[0] > 300) {
                    // todo 到边界时应该报错误信息
                    snakeY[0] = 75;
                } else {
                    snakeY[0] = snakeY[0] + 25;
                }
            }

            // 如果小蛇的头和食物的坐标重合了
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                this.length = this.length + 1;
                // 重新生成食物坐标
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + (25 * random.nextInt(24));
            }

            // 刷新画面
            super.repaint();
        }

    }
}
