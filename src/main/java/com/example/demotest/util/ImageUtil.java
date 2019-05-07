package com.example.demotest.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageUtil {

	/**
	 * 去除图片背景
	 * @param file 需要去除背景的图片
	 * @param path 保存地址
	 */
	//去掉图片背景
	public static void transApla(File file,String path){
	    InputStream is=null;
	    try {
	        is = new FileInputStream(file);
	        // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
	        BufferedImage bi = ImageIO.read(is);
	        Image image = (Image) bi;
	        ImageIcon imageIcon = new ImageIcon(image);
	        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
	                BufferedImage.TYPE_4BYTE_ABGR);
	        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
	        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
	        int alpha = 0;
	        for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
	            for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
	                int rgb = bufferedImage.getRGB(j2, j1);
	                int R = (rgb & 0xff0000) >> 16;
	                int G = (rgb & 0xff00) >> 8;
	                int B = (rgb & 0xff);
	                if (((255 - R) < 25) && ((255 - G) < 25) && ((255 - B) < 25)) {
	                    rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
	                }
	                bufferedImage.setRGB(j2, j1, rgb);
	            }
	        }
	        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
	        ImageIO.write(bufferedImage, "png", new File(path));// 直接输出文件
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if(is!=null){
	           try{
	            is.close();
	           }catch (Exception e){}
	        }
	    }
	}

}
