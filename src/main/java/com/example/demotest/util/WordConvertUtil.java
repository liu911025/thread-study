package com.example.demotest.util;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.PdfSaveOptions;

import java.io.InputStream;

/**
 * @author Lam E-mail: 35081452@qq.com @date 创建时间：2016年3月20日 下午9:17:57 * @version 1.0 * @parameter  * @since  * @return
 */
public class WordConvertUtil {

    private static boolean result = false;

    static {
        initLicense();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        wordToPdf("D:\\word.docx", "D:\\test\\pdf\\word.pdf");
    }

    /**
     * word(doc,docx) 转换成 PDF
     *
     * @param src
     * @param dest
     * @throws Exception
     */
    public static void wordToPdf(String src, String dest) throws Exception {
        if (result) {
            long now = System.currentTimeMillis();
            Document doc = new Document(src);
            com.aspose.words.PdfSaveOptions ps = new com.aspose.words.PdfSaveOptions();
            ps.setEmbedFullFonts(true);
            ps.setEmbedStandardWindowsFonts(true);
            ps.setUseCoreFonts(true);
            PdfSaveOptions saveOptions = new PdfSaveOptions();
            String osName = System.getProperty("os.name");
            if (osName != null && osName.toLowerCase().indexOf("linux") != -1) {
                String path = "/storage/data/resource/fonts/";
                FontSettings.getDefaultInstance().setFontsFolder(path, false);
//				com.aspose.words.FontSettings.setFontsFolder(path, false);
            }
            doc.save(dest, saveOptions);
            System.out.println("word to pdf 共耗时：" + ((System.currentTimeMillis() - now) / 1000.0) + "秒\n\n");

        }
        //book.getSaveOptions().setFontPath(new String[]{"/home/usr/test/winfont"});

    }

    /**
     * 获取license
     *
     * @return
     */
    public static synchronized void initLicense() {

        InputStream is = WordConvertUtil.class.getClassLoader().getResourceAsStream("words.license.xml");

        License aposeLic = new License();
        try {
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
