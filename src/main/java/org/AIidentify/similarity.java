package org.AIidentify;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * @Author: Hao Qin
 * @Date: 19-7-26  上午11:53
 * @Version 1.0
 */
public class similarity {
    public static double s;
    public static BufferedImage image;
    public static BufferedImage image1;

    public similarity(String p,String p1) throws IOException {
        File file = new File(p)   ;//本地图片
        this.image=read(file);

        File file1 = new File(p1)   ;//本地图片
        this.image1=read(file1);

    }

    public similarity() {

    }

    public static double caculate () {

        ImageComparer imageCom = new ImageComparer(image, image1);
        s = imageCom.modelMatch();
        //System.out.println(s);
         return s ;
    }
}