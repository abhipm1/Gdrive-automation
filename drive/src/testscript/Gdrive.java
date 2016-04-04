package testscript;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import utility.MyLibs;
import utility.ObjectMap;


public class Gdrive 

{
	static AndroidDriver adriver;
	public static AppiumDriver<WebElement> driver;	
	public static DesiredCapabilities capabilities;
	public static String device="Android";
	public static String deviceName="FA54JYN03937";
	public static String platformVersion= "5.1.1";
	public static String platformName= "Android";
	public static String appPackage= "com.google.android.apps.docs";
	public static String appActivity= "com.google.android.apps.docs.app.NewMainProxyActivity";
	public static ObjectMap map=new ObjectMap();
    public static Properties val=ObjectMap.property(); 
	
    @BeforeTest
	public void setUp() throws MalformedURLException
	{
		 try
	    	{
		    capabilities = new DesiredCapabilities();
		 	capabilities.setCapability("device",device);
		 	capabilities.setCapability("deviceName",deviceName);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("platformName",platformName);
			capabilities.setCapability("appPackage", appPackage);
			capabilities.setCapability("appActivity", appActivity);
			
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		 	}
	 	catch(Exception e)
		 	{
	 		e.printStackTrace();
		 	}
	}
	
	//Assuming drive app is signed out of all accounts
	@Test
	public static void inValidLogin()
		{
			
		try
		{
			//To perform skip operation in welcome screen
			String skipbutton= driver.findElement(map.getLocator("skip_button")).getText();
			String emailid="abhi.pm2@gmail.com";
			String password="abhishekpm2";
			System.out.println("Text:"+skipbutton);
		    Assert.assertEquals(skipbutton, "Skip");
		    driver.findElement(map.getLocator("skip_button")).click();
		    Thread.sleep(5000);
		    driver.findElement(map.getLocator("email_id")).click();
		    driver.findElement(map.getLocator("email_id")).clear();
		    driver.findElement(map.getLocator("email_id")).sendKeys(emailid);
		    driver.hideKeyboard();
		  
		    boolean nextbutton=driver.findElement(map.getLocator("next_button")).isDisplayed();
		    System.out.println("nextbutton:"+nextbutton);
		    driver.findElement(map.getLocator("next_button")).click(); 
		    Thread.sleep(4000);
		    driver.hideKeyboard();
		    boolean passwordfield=driver.findElement(map.getLocator("password_field")).isDisplayed();
		    System.out.println("passwordfiedl:"+passwordfield);
		    driver.findElement(map.getLocator("password_field")).sendKeys(password);
		    driver.hideKeyboard();
		    driver.findElement(map.getLocator("next_button")).click();
		    Thread.sleep(2000);
		    driver.hideKeyboard();
		    boolean errormsg=driver.findElement(map.getLocator("password_error")).isDisplayed();
		    Assert.assertEquals(errormsg, true, "Error message displayed after invalid login");
		    						
		}
			catch(Exception e)
			{
				e.printStackTrace();
				MyLibs.captureScreenShot(driver, MyLibs.ScreenshotPathName);
				return;
			}
		}
	
	//Assuming Drive app is signed in and has atleast one file
	@Test
	public static void renameFile() throws InterruptedException, MalformedURLException
	{
		String filename="abc.png";
		String filerename="abc2";
		try
		{
		boolean mydrive=driver.findElement(map.getLocator("mydrive_text")).isDisplayed();
		Assert.assertEquals(mydrive, true,"Mydirve is opened");
		
		Thread.sleep(2000);
		String filenamefield=driver.findElement(map.getLocator("filename_field")).getText();
		System.out.println("filenamefield"+filenamefield);
		// List to check all the elements of class and click if equals to filename
		java.util.List textViews = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.google.android.apps.docs:id/title']"));
		int lsize=textViews.size();
		System.out.println("text"+textViews);
		
		for(int i=0;i<lsize;i++)
		{
			String Act=((WebElement) textViews.get(i)).getText();
			System.out.println("Filename of element:"+Act);
			if (Act.equalsIgnoreCase(filename)){
				((WebElement) textViews.get(i)).click();
				break;
			}
		}
		
		Thread.sleep(3000);
		driver.findElement(map.getLocator("moreoptions_button")).click();
		Assert.assertEquals(driver.findElement(map.getLocator("moreoptions_view")).isDisplayed(),true);
	    driver.swipe(700, 2200, 840, 735, 1000);
		driver.findElement(map.getLocator("rename_button")).click();
		driver.findElement(map.getLocator("rename_textfield")).clear();
		driver.findElement(map.getLocator("rename_textfield")).click();
		driver.findElement(map.getLocator("rename_textfield")).sendKeys(filerename);
		driver.findElement(map.getLocator("rename_okbutton")).click();
		Thread.sleep(2000);
		String renamedfile=driver.findElement(map.getLocator("renamed_file")).getText();
		Assert.assertEquals(renamedfile, filerename,"File is renamed successfully");
		((StartsActivity) driver).startActivity("com.android.chrome","com.google.android.apps.chrome.Main", null, null);
		Thread.sleep(3000);
		driver.findElement(map.getLocator("url_bar1")).click();
		driver.findElement(map.getLocator("url_barselected")).sendKeys("https://drive.google.com");
		/*Tried using following to open browser its not working also tried .get() appium says not implemented
		wdriver.navigate().to("https://drive.google.com"); */
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MyLibs.captureScreenShot(driver, MyLibs.ScreenshotPathName);
			return;
		}
	}
	
	@AfterTest
	public static void closeApp()throws Exception
	{
		driver.quit();
		System.out.println("App is closed");
		
	}
			   
}
