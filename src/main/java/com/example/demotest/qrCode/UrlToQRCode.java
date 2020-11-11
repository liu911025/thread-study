package com.example.demotest.qrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UrlToQRCode {

    public static void main(String[] args) throws Exception {
        String url = "www.baidu.com";
        String qrCodePath = getErWeiCode(url);
        System.out.println(qrCodePath);
    }

    /**
     * 生成二维码，返回到页面上
     *
     * @param url
     */
    public static String getErWeiCode(String url) throws Exception {
        if (StringUtils.isNotBlank(url)) {
            try {
                int width = 200;
                int height = 200;
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
                String imgPath = "E:\\qr\\" + UUID.randomUUID().toString() + ".png";
                File file = new File(imgPath);
                if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile())) {
                    writeToFile(bitMatrix, "png", file);
                    return file.getPath();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new Exception("H5二维码生成失败!");
    }

    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
}

