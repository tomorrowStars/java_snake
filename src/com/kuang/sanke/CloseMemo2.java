package com.kuang.sanke;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CloseMemo2 {
    private JFrame frame;

    public static void main(String[] args) {
        CloseMemo2 window = new CloseMemo2();
        window.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int i= JOptionPane.showConfirmDialog(null, "确认退出吗？");
                if(i==JOptionPane.OK_OPTION){
                    System.exit(0);
                }else{

                }
            }
            //            public void windowClosing(WindowEvent e) {
//                int i= JOptionPane.showConfirmDialog(null, "确认退出吗？");
//                if(i==JOptionPane.OK_OPTION){
//                    System.exit(0);
//                }else{
//
//                }
//            }
        });
    }

    public CloseMemo2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认点击关闭
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}