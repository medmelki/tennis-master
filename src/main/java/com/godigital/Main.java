package com.godigital;

import com.godigital.modules.processor.ProcessorService;

public class Main {
    public static void main(String[] args) {
        ProcessorService processorService = new ProcessorService();
        String videoPath = "sample.mp4";
        String outputDirectory = "frames/";

        processorService.processVideo(videoPath, outputDirectory);
    }
}