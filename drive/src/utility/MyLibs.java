package utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import runner.Runner;

public class MyLibs
{
	public static String shpath=System.getProperty("user.dir")+"\\Results\\screenshots";
	public static String ScreenshotPathName;
	 
	public static Properties property(String FileName)
	{
		FileInputStream fin=null;
		Properties prop=null;
		try
		{
			fin=new FileInputStream(FileName);
			prop=new Properties();
			prop.load(fin);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	public static String getDateTime(String DateFormat)
	{
		SimpleDateFormat sdf=null;
		String sDateTime=null;
		try
		{
			Calendar cal=Calendar.getInstance();
			sdf=new SimpleDateFormat(DateFormat);
			sDateTime= sdf.format(cal.getTime());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sDateTime;
	}
	
	public static void captureScreenShot(WebDriver driver,String FileName)
	{
		String stime=MyLibs.getDateTime("hh-mm-ss");
		FileName=shpath+"/"+"Suite1"+(Runner.TestcaseStep)+"_"+Runner.ClassName+"_"+Runner.MethodName+"_"+stime+".png";
		ScreenshotPathName=FileName;
		File destFile=null;
		try
		{
			destFile=new File(FileName);
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, destFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}