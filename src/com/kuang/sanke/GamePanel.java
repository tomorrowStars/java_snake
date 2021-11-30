package com.kuang.sanke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener , ActionListener {
    // 画面板
    int length;                     // 长度
    int[] snakeX = new int[600];    // 坐标X
    int[] snakeY = new int[600];    // 坐标Y

    // 方向
    String fx = "R";                // R: 右， L:左， U：上， D:下

    // 游戏是否开始
    boolean isStart;
    // 游戏是否暂停
    boolean isSuspend;

    // 定时器：时间：100毫秒
    Timer timer = new Timer(100, this);

    // 食物
    int foodX;  // 食物 横坐标
    int foodY;  // 食物 纵坐标
    //  随机数
    Random random = new Random();



    public GamePanel() {
        this.init();

        // 获取监听事件
        this.setFocusable(true);
        this.addKeyListener(this);

        // 初始时，启动定时器
        timer.start();

    }

    /**
     * 初始化
     */
    public void init() {
        this.length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        this.fx = "R";
        isStart = false;
        isSuspend = false;

        foodX = 25 + 25 * random.nextInt(34);
        foodY = 25 + 25 * random.nextInt(24);

    }

    /**
     * Graphics: 画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 清屏
        this.setBackground(Color.white);// 设置背景颜色

        //绘制头部的广告栏
        Data.header.paintIcon(this, g, 25, 11);

        // 绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        // 画一条静态的小蛇

        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
//        Data.body.paintIcon(this, g, snakeX[1], snakeY[1]);
//        Data.body.paintIcon(this, g, snakeX[2], snakeY[2]);

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 食物
        Data.food.paintIcon(this, g, foodX,foodY);


        // 游戏提示：开始提示
        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 40));
            g.drawString("按空格键开始游戏！", 200, 300);

            g.setColor(Color.ORANGE);
            g.setFont(new Font("宋体", Font.ITALIC, 35));
            g.drawString("按回车结束游戏!", 200, 350);
        }


    }

    // 键盘按下未释放
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isStart == false) {
                super.repaint();    // 刷新画面
                isStart = true;     // 程序开始
//                timer.restart();       // timer再开
//                isSuspend = false;   // 程序暂停
            } else{
                // 暂停处理
                this.setSuspend();
            }
        }
        else if (keyCode == KeyEvent.VK_ENTER) {
            // 暂停游戏
            if (isStart == true) {
                timer.stop();       // 程序正在运行则 timer暂停
                isSuspend = true;
            }

            // 是否关闭游戏
            int rtn = JOptionPane.showConfirmDialog(this, "是否关闭游戏", null, JOptionPane.YES_NO_OPTION);
            if (rtn == 0) {
                //是（ yes：0）  // 否（ NO： 1）
                System.exit(0);  // 关闭游戏
            } else {
                // todo 光标在【No】上时，键盘回车 会关闭窗口

                // 程序正在运行
                if (isStart == true) {
                    // "继续游戏 or 重新开始"
                    Object[] options = {"继续", "重开"};
                    int rtn2 = JOptionPane.showOptionDialog(this,
                            "继续游戏 or 重新开始", null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (rtn2 == 0) {
                        //是
                        // 继续游戏
                        timer.restart();       // 程序已暂停则 timer再开
                        isSuspend = false;
                    } else {
                        // 重新开始
//                        isStart = true;
                        timer.restart();       // 程序已暂停则 timer再开
                        isSuspend = false;
                        this.init();        // 初始化画面
                        super.repaint();    // 刷新画面
                    }
                } else {
                    // 程序还未开始
                    // 什么也不做
                }


            }

        }
        else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        }
        else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        }
        else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }

    /**
     * 暂停处理
     * @param
     */
    private void setSuspend() {
        // 暂停处理
        if (isSuspend == true) {
            timer.restart();       // 程序已暂停则 timer再开
        } else {
            timer.stop();       // 程序正在运行则 timer暂停
        }
        isSuspend = !isSuspend;
    }

    /**
     * 执行定时操作
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart) {
            // 身体移动
            for (int i = length -1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            // 右侧移动
            if (fx.equals("R")) {
                // 头部移动
                if (snakeX[0] > 825) {
                    // todo 到边界时应该报错误信息
                    snakeX[0] = 25;
                } else{
                    snakeX[0] = snakeX[0] + 25;
                }
            }
            else if (fx.equals("L")) {
                // 头部移动
                if (snakeX[0] < 50) {
                    // todo 到边界时应该报错误信息
                    snakeX[0] = 850;
                } else{
                    snakeX[0] = snakeX[0] - 25;
                }
            }
            else if (fx.equals("U")) {
                // 头部移动
                if (snakeY[0] < 90) {
                    // todo 到边界时应该报错误信息
                    snakeY[0] = 650;
                } else{
                    snakeY[0] = snakeY[0] - 25;
                }
            }
            else if (fx.equals("D")) {
                // 头部移动
                if (snakeY[0] > 625) {
                    // todo 到边界时应该报错误信息
                    snakeY[0] = 75;
                } else{
                    snakeY[0] = snakeY[0] + 25;
                }
            }

            // 如果小蛇的头和食物的坐标重合了
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                this.length = this.length + 1;
                // 重新生成食物坐标
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }

            // 刷新画面
            super.repaint();
        }

        // 启动定时器
//        timer.start();

    }

    // 按下弹起
    @Override
    public void keyTyped(KeyEvent e) {

    }


    // 释放某个键
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
