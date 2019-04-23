package com.example.demotest.mp4;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;

import java.io.File;

/**
 * java-1.0.2为所需jar
 * 视频压缩
 */
public class Mp4Test {
    public static void main(String[] args) {
        File source = new File("D:/video.mp4");
        File target = new File("D:/222.mp4");
        try {
            System.out.println("begin");
            AudioAttributes audio= new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(56000));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(22050));
            VideoAttributes video=new VideoAttributes();
            video.setCodec("mpeg4");
            video.setBitRate(new Integer(800000));
            video.setFrameRate(new Integer(15));
            EncodingAttributes attr=new EncodingAttributes();
            attr.setFormat("mp4");
            attr.setAudioAttributes(audio);
            attr.setVideoAttributes(video);
            Encoder encoder=new Encoder();
            encoder.encode(source, target, attr);
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
