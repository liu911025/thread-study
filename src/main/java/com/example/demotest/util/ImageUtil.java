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
import java.util.*;
import java.util.List;

public class ImageUtil {

	private static double width = 435.0;
	//private int height

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

	/**
	 * 生成时间图片
	 * @param datePattern
	 * @param width
	 * @param imagePath
	 * @return
	 * @throws IOException
	 */
	public static String genTimestamp(String datePattern, int width, String imagePath) throws IOException {
		int height = 35;
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

		g2.setFont(new Font("宋体",Font.BOLD,32)); //设置字体:字体、字号、大小
		g2.setColor(Color.BLACK);//设置背景颜色
		int i = width / 3;
		g2.drawString(format, 0,30); //向图片上写字符串
		//String imagePath = "E:\\upload\\image\\date\\" + format2 + ".jpg";
		ImageIO.write(bi,"JPEG",new FileOutputStream(imagePath));//保存图片 JPEG表示保存格式
		return imagePath;
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



	/**
	 * 导入本地图片到缓冲区
	 */
	public static BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {
		Graphics2D g        = null;
		try {
			int w = b.getWidth();
			int h = b.getHeight();

			g = d.createGraphics();
			g.drawImage(b, 300, -800, w, h, null);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return d;
	}

	/**
	 * 生成新图片到本地
	 */
	public static void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "jpg", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 指定长和宽对图片进行缩放
	 * @throws IOException
	 */
	public static String zoomBySize(BufferedImage img, String srcPath, String destPath) throws IOException {
		//BufferedImage image = ImageIO.read(new FileInputStream(new File(srcPath)));

		double scale = width / img.getWidth();

		double height = img.getHeight() * scale;

		//与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
		Image _img = img.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
		BufferedImage image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, (int) width, (int) height);
		graphics.drawImage(_img, 0, 0, null);
		graphics.dispose();

		String ext = srcPath.substring(srcPath.lastIndexOf(".") + 1);
		OutputStream out = new FileOutputStream(destPath);
		ImageIO.write(image, ext, out);
		out.close();
		return destPath;
	}

	/**
	 * 图片去白色的背景，并裁切
	 *
	 * @param image 图片
	 * @param range 范围 1-255 越大 容错越高 去掉的背景越多
	 * @return 图片
	 * @throws Exception 异常
	 */
	public static byte[] transferAlpha(Image image, InputStream in, int range) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ImageIcon imageIcon = new ImageIcon(image);
			BufferedImage bufferedImage = new BufferedImage(imageIcon
					.getIconWidth(), imageIcon.getIconHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
			g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon
					.getImageObserver());
			int alpha = 0;
			int minX = bufferedImage.getWidth();
			int minY = bufferedImage.getHeight();
			int maxX = 0;
			int maxY = 0;

			for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
					.getHeight(); j1++) {
				for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
						.getWidth(); j2++) {
					int rgb = bufferedImage.getRGB(j2, j1);

					int R = (rgb & 0xff0000) >> 16;
					int G = (rgb & 0xff00) >> 8;
					int B = (rgb & 0xff);
					if (((255 - R) < range) && ((255 - G) < range) && ((255 - B) < range)) { //去除白色背景；
						rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
					} else {
						minX = minX <= j2 ? minX : j2;
						minY = minY <= j1 ? minY : j1;
						maxX = maxX >= j2 ? maxX : j2;
						maxY = maxY >= j1 ? maxY : j1;
					}
					bufferedImage.setRGB(j2, j1, rgb);
				}
			}
			int width = maxX - minX+3;
			int height = maxY - minY+3;
			System.out.println(height+"==="+minY);
			BufferedImage sub = bufferedImage.getSubimage(minX, minY, width, height);
			sub = rotateImage(sub,0);
			ImageIO.write(sub, "png", byteArrayOutputStream);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 图片旋转
	 * @param bufferedimage bufferedimage
	 * @param degree 旋转的角度
	 * @return BufferedImage
	 */
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,
											final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
				w, h)), degree);
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(rect_des.width, rect_des.height, type))
				.createGraphics()).setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.translate((rect_des.width - w) / 2,
				(rect_des.height - h) / 2);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}

	/**
	 * 计算旋转后图像的大小
	 * @param src  Rectangle
	 * @param degree 旋转的角度
	 * @return Rectangle
	 */
	public static Rectangle CalcRotatedSize(Rectangle src, int degree) {
		if (degree >= 90) {
			if(degree / 90 % 2 == 1){
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			degree = degree % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(degree) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(degree)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}

	public static void main(String[] args) throws Exception {

		String path = "E:\\upload\\image\\wx.png";

		BufferedImage image1 = ImageIO.read(new FileInputStream(new File(path)));
		byte[] imgbyte = transferAlpha(image1, null, 255);
		OutputStream os = new FileOutputStream("E:\\321.jpg");
		os.write(imgbyte, 0, imgbyte.length);
		os.flush();
		os.close();

		/*BufferedImage scaleImage = ImageIO.read(new FileInputStream(new File("E:\\321.jpg")));
		String destPath = path.substring(0 , path.lastIndexOf(".")) + "_scale" + path.substring(path.lastIndexOf("."));
		String scalePath = zoomBySize(scaleImage, path, destPath);

		BufferedImage image = ImageIO.read(new FileInputStream(new File(scalePath)));

		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String format2 = formatter2.format(date);
		String dateImagePath = "E:\\upload\\image\\date\\" + format2 + ".jpg";
		String timestamp = ImageUtil.genTimestamp("yyyy年MM月dd日 HH时mm分", image.getWidth(), dateImagePath);

		System.out.println(timestamp);

		List<String> imgs = Arrays.asList(scalePath, timestamp);
		String imagePath = "E:\\upload\\image\\date\\" + UUID.randomUUID().toString().replace("-","") + ".jpg";
		ImageUtil.mergeImage(imgs, 2, imagePath);

		System.out.println("success");

		File file = new File(imagePath);
		String resultPath = "E:\\123.jpg";
		transApla(file, resultPath);*/

		/*String imagePath = "E:\\123.jpg";
		BufferedImage image = ImageIO.read(new FileInputStream(new File(imagePath)));
		byte[] imgbyte = transferAlpha(image, null, 80);
		OutputStream os = new FileOutputStream("E:\\321.jpg");
		os.write(imgbyte, 0, imgbyte.length);
		os.flush();
		os.close();*/
	}


	/**
	 * 图片格式转换
	 * @param srcImageFile
	 * @param formatName
	 * @param destImageFile
	 */
	public static void convert(String srcImageFile, String formatName, String destImageFile) {
		try {
			File f = new File(srcImageFile);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, formatName, new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
