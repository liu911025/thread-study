package com.example.demotest.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

	public static String genTimestamp(String datePattern, int width, int height) throws IOException {
		height = 30;
		//得到图片缓冲区
		BufferedImage bi = new BufferedImage

				(width,height,BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150

		//得到它的绘制环境(这张图片的笔)
		Graphics2D g2 = (Graphics2D) bi.getGraphics();

		g2.fillRect(0,0,width,height);//填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
		//设置颜色
		g2.setColor(Color.WHITE);
		g2.fillRect(0,0,width,height);//填充整张图片(其实就是设置背景颜色)

		//g2.setColor(Color.RED);
		//g2.drawRect(0,0,150-1,70-1); //画边框
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		LocalDateTime date = LocalDateTime.now();
		String format = formatter.format(date);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String format2 = formatter2.format(date);

		g2.setFont(new Font("宋体",Font.BOLD,18)); //设置字体:字体、字号、大小
		g2.setColor(Color.BLACK);//设置背景颜色
		int i = width / 3;
		g2.drawString(format, i,20); //向图片上写字符串
		String imagePath = "E:\\upload\\image\\date\\" + format2 + ".jpg";
		ImageIO.write(bi,"JPEG",new FileOutputStream(imagePath));//保存图片 JPEG表示保存格式
		return imagePath;
	}


	/**
	 * @Description:截图
	 * @author:liuyc
	 * @time:2016年5月27日 上午10:18:23
	 * @param srcFile、targetFile截好后图片全名、startAcross 开始截取位置横坐标、StartEndlong开始截图位置纵坐标、width截取的长，hight截取的高
	 */
	public static void cutImage(String srcFile, String targetFile, int startAcross, int StartEndlong, int width,
								int hight) throws Exception {
		// 取得图片读入器
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		// 取得图片读入流
		InputStream source = new FileInputStream(srcFile);
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		// 图片参数对象
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(startAcross, StartEndlong, width, hight);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, targetFile.split("\\.")[1], new File(targetFile));
	}

	/**
	 * @Description:图片拼接 （注意：必须两张图片长宽一致哦）
	 * @author:liuyc
	 * @time:2016年5月27日 下午5:52:24
	 * @param files 要拼接的文件列表
	 * @param type  1 横向拼接， 2 纵向拼接
	 */
	public static void mergeImage(List<String> files, int type, String targetFile) {
		int len = files.size();
		if (len < 1) {
			throw new RuntimeException("图片数量小于1");
		}
		File[] src = new File[len];
		BufferedImage[] images = new BufferedImage[len];
		int[][] ImageArrays = new int[len][];
		for (int i = 0; i < len; i++) {
			try {
				src[i] = new File(files.get(i));
				images[i] = ImageIO.read(src[i]);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			ImageArrays[i] = new int[width * height];
			ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);
		}
		int newHeight = 0;
		int newWidth = 0;
		for (int i = 0; i < images.length; i++) {
			// 横向
			if (type == 1) {
				newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();
				newWidth += images[i].getWidth();
			} else if (type == 2) {// 纵向
				newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();
				newHeight += images[i].getHeight();
			}
		}
		if (type == 1 && newWidth < 1) {
			return;
		}
		if (type == 2 && newHeight < 1) {
			return;
		}

		// 生成新图片
		try {
			BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			int width_i = 0;
			for (int i = 0; i < images.length; i++) {
				if (type == 1) {
					ImageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, ImageArrays[i], 0,
							images[i].getWidth());
					width_i += images[i].getWidth();
				} else if (type == 2) {
					ImageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), ImageArrays[i], 0, newWidth);
					height_i += images[i].getHeight();
				}
			}
			//输出想要的图片
			ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:小图片贴到大图片形成一张图(合成)
	 * @author:liuyc
	 * @time:2016年5月27日 下午5:51:20
	 */
	public static final void overlapImage(String bigPath, String smallPath, String outFile) {
		try {
			BufferedImage big = ImageIO.read(new File(bigPath));
			BufferedImage small = ImageIO.read(new File(smallPath));
			Graphics2D g = big.createGraphics();
			int x = (big.getWidth() - small.getWidth()) / 2;
			int y = (big.getHeight() - small.getHeight()) / 2;
			g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
			g.dispose();
			ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String path = "D:\\zw.png";

		BufferedImage image = ImageIO.read(new FileInputStream(new File(path)));

		String timestamp = ImageUtil.genTimestamp("yyyy-MM-dd HH:mm", image.getWidth(), image.getHeight());

		System.out.println(timestamp);

		List<String> imgs = Arrays.asList(path, timestamp);
		String imagePath = "E:\\upload\\image\\date\\" + UUID.randomUUID().toString().replace("-","") + ".jpg";
		ImageUtil.mergeImage(imgs, 2, imagePath);

	}
}
