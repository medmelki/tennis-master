package com.godigital.modules.processor;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessorService {
    public void processVideo(String videoPath, String outputDirectory) {
        try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoPath)) {
            frameGrabber.setFormat("mp4");
            frameGrabber.start();

            int frameNumber = 0;
            Frame frame;
            while ((frame = frameGrabber.grabImage()) != null) {
                BufferedImage bufferedImage = convertFrameToBufferedImage(frame);

                String outputImagePath = outputDirectory + "frame_" + frameNumber + ".jpg";
                ImageIO.write(bufferedImage, "jpg", new File(outputImagePath));

                frameNumber++;
            }

        } catch (IOException e) {
            System.out.println("error occurred " + e);
        }
    }

    private BufferedImage convertFrameToBufferedImage(Frame frame) {
        try (Java2DFrameConverter converter = new Java2DFrameConverter()) {
            return converter.getBufferedImage(frame);
        } catch (Exception e) {
            System.out.println("error occurred " + e);
        }
        return null;
    }
}
