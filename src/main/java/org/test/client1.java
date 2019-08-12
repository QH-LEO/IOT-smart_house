package org.test;

import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Stack;

/**
 * @Author: Hao Qin
 * @Date: 19-8-5  下午2:27
 * @Version 1.0
 */

    public class client1 {

    public  byte[] getB(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

        /**
         * Socket客户端
         */
        public static void main(String[] args) throws Exception {
            try {
                int count=0;
                while(true){
                    client1 c=new client1();
// "GET /ipcam/jpeg.cgi HTTP/1.1\r\n\r\nAuthorization: Basic YWRtaW46OTk5OQ====\r\n\r\n"
// 创建Socket对象
                    Socket socket = new Socket("192.168.0.80", 8080);
// 根据输入输出流和服务端连接
                   OutputStream outputStream = socket.getOutputStream();// 获取一个输出流，向服务端发送信息
                   OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
                   // byte[] b=new byte[grabber.start()];
//                    Stack s = new Stack();
//                    s.push(1);
                    Stack<String> stack = new Stack<>();

                    grabber.start();   //开始获取摄像头数据
// "GET /ipcam/jpeg.cgi HTTP/1.1\r\nAuthorization: Basic YWRtaW46OTk5OQ====\r\n\r\n"
                    outputStream
                            .write(c.getB("/home/qhwd/1.wav"));

                    socket.shutdownOutput();// 关闭输出流
                    InputStream inputStream = socket.getInputStream();// 获取一个输入流，接收服务端的信息
                    String outputFile = "rtmp://localhost:1935//home/v";
                    Player.frameRecord(inputStream, outputFile, 1);
                    count++;
                    System.out.println("================================"+count+"次数");
                    socket.close();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


