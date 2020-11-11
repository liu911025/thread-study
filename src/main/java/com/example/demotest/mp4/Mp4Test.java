package com.example.demotest.mp4;

import it.sauronsoftware.jave.*;

import java.io.File;

/**
 * java-1.0.2为所需jar
 * 视频压缩
 */
public class Mp4Test {
    public static void main(String[] args) {
        File source = new File("D:/video.mp4");
        File target1 = new File("D:/111.mp4");
        File target2 = new File("D:/222.mp4");
        File target3 = new File("D:/333.mp4");
        /*try {
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
            encoder.encode(source, target1, attr);
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {

            AudioAttributes audio = new AudioAttributes();// 音频属性
            audio.setCodec("libmp3lame");// libfaac PGM编码
            audio.setBitRate(new Integer(128000));// 音频比特率
            audio.setChannels(new Integer(2));// 声道
            audio.setSamplingRate(new Integer(44100));// 采样率
            VideoAttributes video = new VideoAttributes();// 视频属性
            video.setCodec("mpeg4");// 视频编码
            video.setBitRate(new Integer(22050));// 视频比特率
            video.setFrameRate(new Integer(30));// 帧率
            video.setSize(new VideoSize(1920, 1080));// 视频宽高
            EncodingAttributes attrs = new EncodingAttributes();// 转码属性

            attrs.setFormat("mp4");// 视频格式
            attrs.setAudioAttributes(audio);// 音频属性
            attrs.setVideoAttributes(video);// 视频属性
            Encoder encoder = new Encoder();// 创建解码器
            encoder.encode(source, target1, attrs);
            System.out.println("上传成功！！！");
        } catch (EncoderException e) {
            e.printStackTrace();
            System.out.println("文件格式不正确！");
        }

    }
}
