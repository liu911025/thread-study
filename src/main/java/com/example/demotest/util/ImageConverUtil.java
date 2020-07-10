package com.example.demotest.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.List;

/**
 * * @author Lam E-mail: 35081452@qq.com @date 创建时间：2016年3月18日 下午9:25:05 * @version
 * 1.0 * @parameter * @since * @return
 */
public class ImageConverUtil {
    private static final Logger log = LoggerFactory.getLogger(ImageConverUtil.class);

	public static final String SRC = "D:\\test\\hb.png";
	public static final String DEST = "D:\\test\\pdf\\hb.pdf";
	public static final String IMAGE = "C:\\Users\\lam\\Desktop\\test\\517290466034482026.jpg";

	// public static void main(String[] args) throws DocumentException,
	// IOException {
	// File file = new File(DEST);
	// file.getParentFile().mkdirs();
	// new LargeImage1().manipulatePdf(SRC, DEST);
	// }

	public void createPdf(String dest)   {
		Document document = new Document(PageSize.A4);
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document,
					new FileOutputStream(dest));
			document.open();
			document.add(new Paragraph("Berlin!"));
			PdfContentByte canvas = writer.getDirectContentUnder();
			Image image = Image.getInstance(IMAGE);
			// image.scaleToFit(PageSize.A4);
			image.scaleToFit(595, 842);
			// image.scaleAbsoluteWidth(595);
			// image.scaleAbsolute(PageSize.A4);
			image.setAbsolutePosition(0, 0);
			// image.rotate();
			
			canvas.addImage(image);
			document.close();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			if(document!=null)
				document.close();
			if(writer!=null)
				writer.close();
		}
		
	}

	public static boolean imageToPdf(String image, String pdf) {
		// PdfReader reader = new PdfReader(pdf);
		// // // The width and the height of a PDF page may not exceed 14400
		// user units:
		// Rectangle rect = reader.getPageSize(1);
		// if (rect.getWidth() < 14400 && rect.getHeight() < 14400) {
		// System.out.println("The size of the PDF document is within the accepted limits");
		// System.exit(0);
		// }
		// We assume that there's a single large picture on the first page
		// PdfDictionary page = reader.getPageN(1);
		// PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
		// PdfDictionary xobjects = resources.getAsDict(PdfName.XOBJECT);
		// PdfName imgRef = xobjects.getKeys().iterator().next();
		// PRStream imgStream = (PRStream) xobjects.getAsStream(imgRef);
		// We now create a new Image object based on the bytes in the stream
		// PdfImageObject imgObject = new PdfImageObject(imgStream);
		Document document = null;
		boolean result = false;
		try {
			document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(pdf));
			document.open();
			Image img = Image.getInstance(image);
			// img.scalePercent(40);
			// img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
			// // img.scaleAbsoluteHeight(PageSize.A4.getHeight());
			// // img.setScaleToFitHeight(true);
			// // img.setScaleToFitLineWhenOverflow(true);
			// float offsetX = (PageSize.A4.getWidth() - img.getScaledWidth()) /
			// 2;
			// float offsetY = (PageSize.A4.getHeight() - img.getScaledHeight())
			// / 2;
			// img.setAbsolutePosition(10,PageSize.A4.getHeight()/2);
			img.setAlignment(Image.ALIGN_CENTER);
			img.scaleToFit(PageSize.A4.getWidth() - 20,
					PageSize.A4.getHeight() - 20);
			document.add(img);
			document.close();
            result = true;
		} catch (Exception e) {
            log.error("{}", e);

		} finally {
			if (document != null)
				document.close();

		}
		return result;
		// Image img =
		// Image.getInstance(image);//Image.getInstance(imgObject.getImageAsBytes());
		// // img.scaleToFit(595, 842);
		// img.setRotationDegrees(-90);
		// // img.setRotation(90);
		// // img.scaleAbsoluteWidth(595);
		// img.setAbsolutePosition(0, 0);

		// reader.close();

		// img.scaleToFit(770, 523);

		// System.out.println("img.getWidth():"+img.rotate().getWidth()+", img.getHeight():"+
		// img.rotate().getHeight()+",w:"+img.getWidth()+",h:"+img.getHeight());

		// We create a new document with the correct size
		// System.out.println("img.getScaledWidth():"+img.getScaledWidth()+", img.getScaledHeight():"+
		// img.getScaledHeight()+",w:"+img.getWidth()+",h:"+img.getHeight());
		// Document document = new Document(new
		// Rectangle(img.rotate().getWidth(), img.rotate().getHeight()));
		// PdfWriter.getInstance(document, new FileOutputStream(pdf));
		// document.open();
		// document.add(img);
		// document.close();
	}

	public static void imagesToPdf(List<String> images, String pdf) {
		Document document = null;
		try {
			document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(pdf));
			document.open();
			for (String image : images) {
				Image img = Image.getInstance(image);
				img.setAlignment(Image.ALIGN_CENTER);
				img.scaleToFit(PageSize.A4.getWidth() - 20,
						PageSize.A4.getHeight() - 20);
				document.add(img);
			}
			document.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (document != null)
				document.close();
		}
	}

	public static void main(String[] args) throws Exception {

		imageToPdf(SRC, DEST);
	}

}
