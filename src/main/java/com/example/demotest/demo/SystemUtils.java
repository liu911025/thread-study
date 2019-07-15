package com.example.demotest.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 调用第三方外部命令
 */
public class SystemUtils {
	
	private static final Log log = LogFactory.getLog(SystemUtils.class);

    static final Properties PROPERTIES = new Properties(System.getProperties());

    /**
     * get line separator on current platform
     * @return line separator
     */
    public static String getLineSeparator(){
        return PROPERTIES.getProperty("line.separator");
    }
    
    public static String getPathSeparator(){
        return PROPERTIES.getProperty("path.separator");
    }

    public static String getFileSeparator()
    {
    	return PROPERTIES.getProperty("file.separator");
    }
    
    public static String getFileFullPath(String fileName)
    {
    	return SystemUtils.getFileSeparator() + fileName;
    }
     
    public static boolean runCommand(String command)
    {
    	Runtime runtime = Runtime.getRuntime();
    	 
    	try {
    		long start = System.currentTimeMillis();
			Process process  = runtime.exec(command);
	    	InputStream str = process.getInputStream();
	    	InputStreamReader isr = new InputStreamReader(str, "GBK");
	    	BufferedReader br = new BufferedReader(isr);
	    	String line = null;
	    	String result = "";
	    	while((line = br.readLine())!=null)
	    	{
	    		result += line;
	    	}
	    	int exitValue = process.waitFor();
	    	//ret = 2， ret = 0
	    	long now = System.currentTimeMillis();
	    	System.out.print(" ====== command :" + command+","+((now - start) / 1000.0)
					+ "sec");
	    	System.out.println(",Status:"+exitValue+",readLine:"+result);
	    	if(str!=null)
	    		str.close();
	    	if(isr!=null)
	    		isr.close();
	    	if(br!=null)
	    		br.close();
	    	if(result.equals("ret = 0"))
	    		return true;
	    	if(exitValue==0)//for wkhtmltopdf and other
	    		return true;
	    	return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} 
    }
    
    
    public static boolean isWin()
    {
    	Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if(os.toLowerCase().startsWith("win"))
			return true;
		
		return false;
    }
    
    
    public static void main(String[] args) throws IOException
    {
    	//Runtime.getRuntime().exec("C:\\Users\\lam\\work\\tools\\red_seal\\signle.exe C:\\Users\\lam\\work\\tmp\\seal\\c907d68d263641e8b6f8a64e6c38bd59-sealsrc.jpg  C:\\Users\\lam\\work\\tmp\\seal\\c907d68d263641e8b6f8a64e6c38bd59-sealresult.png");
    	//runCommand("notepad", "C:\\Users\\lam\\Desktop\\test.txt");
    	//runCommand("C:\\Users\\lam\\work\\tools\\red_seal\\signle.exe", "C:\\Users\\lam\\work\\tmp\\seal\\c907d68d263641e8b6f8a64e6c38bd59-sealsrc.jpg  C:\\Users\\lam\\work\\tmp\\seal\\c907d68d263641e8b6f8a64e6c38bd59-sealresult.png");
		String command = "E:/tool/mupdf-1.15.0-windows/mutool  draw -o  D:/img/www_%d.jpg -F png  -r 144 D:/www.pdf";
		runCommand(command);
    }
}
