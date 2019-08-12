package org.videostream;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;
import org.bytedeco.opencv.opencv_core.IplImage;

public class JavavcCamera {
    public JavavcCamera() {
    }

    public static void main(String[] args) throws Exception, InterruptedException, org.bytedeco.javacv.FrameRecorder.Exception {
        recordCamera("rtmp://127.0.0.1/live/", 25.0D);
    }

    public static void recordCamera(String outputFile, double frameRate) throws Exception, InterruptedException, org.bytedeco.javacv.FrameRecorder.Exception {
        FrameGrabber grabber = FrameGrabber.createDefault(0);
        grabber.start();
        ToIplImage converter = new ToIplImage();
        IplImage grabbedImage = converter.convert(grabber.grab());
        int width = grabbedImage.width();
        int height = grabbedImage.height();
        FrameRecorder recorder = FrameRecorder.createDefault(outputFile, width, height);
        //recorder.setVideoCodec(28);
        recorder.setFormat("flv");
        recorder.setFrameRate(frameRate);
        recorder.start();
        long startTime = 0L;
        long videoTS = 0L;
        CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma() / grabber.getGamma());
        frame.setDefaultCloseOperation(3);
        frame.setAlwaysOnTop(true);
        converter.convert(grabbedImage);

        while(frame.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
            Frame rotatedFrame = converter.convert(grabbedImage);
            frame.showImage(rotatedFrame);
            if (startTime == 0L) {
                startTime = System.currentTimeMillis();
            }

            videoTS = 1000L * (System.currentTimeMillis() - startTime);
            recorder.setTimestamp(videoTS);
            recorder.record(rotatedFrame);
            Thread.sleep(40L);
        }

        frame.dispose();
        recorder.stop();
        recorder.release();
        grabber.stop();
    }
}
