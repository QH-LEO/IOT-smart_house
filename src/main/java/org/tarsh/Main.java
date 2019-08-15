package org.tarsh;

/**
 * @Author: Hao Qin
 * @Date: 19-8-8  上午1:41
 * @Version 1.0
 */


import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;
import java.awt.*;

public class Main {

    private CanvasFrame frame;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main window = new Main();
                window.frame.setVisible(true);
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        super();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame =  new CanvasFrame("摄像头");//新建一个窗口
        ((CanvasFrame)frame).setCanvasSize(400, 400);//设置大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭按钮起作用
        frame.setAlwaysOnTop(true); //让你的窗体一直处于屏幕的最前端
        openCamera();//调用摄像头
    }

    /**
     * 摄像头输出到屏幕
     */
    public void openCamera(){
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);//创建OpenCV对象 OpenCVFrameGrabber
        try {
            grabber.start();   //开始获取摄像头数据
            while(true){
                ((CanvasFrame)frame).showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的grabber.grab();是一帧视频图像
                Thread.sleep(50);//50毫秒刷新一次图像
            }
        } catch (Exception e) {
            System.out.println("摄像头初始化失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}