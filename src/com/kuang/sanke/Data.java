package com.kuang.sanke;

import javax.swing.*;
import java.net.URL;

// 存放外部数据
public class Data {
    // 注意statics路径类型：resources


    // 头部的图片 URL: 定位图片的地址  ImageIcon：图片
    public static URL headerUrl = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);

    // 身体的图片
    public static URL bodyUrl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    // 头部
    public static URL upUrl = Data.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upUrl);

    public static URL downyUrl = Data.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downyUrl);

    public static URL leftUrl = Data.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(leftUrl);

    public static URL rightUrl = Data.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(rightUrl);

    // 食物
    public static URL foodUrl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);



}
