package utility;
import java.io.FileInputStream;
import java.util.*;

import org.openqa.selenium.By;
public class ObjectMap 
{
	
	/*public static void main(String a[])
	{ 

		  ObjectMap map=new ObjectMap();
	 Properties val=ObjectMap.property();
	 System.out.println(val);
	
	}*/
	public static Properties prop=null;
	public static Properties property()
	{
		FileInputStream fin=null;
		
		try
		{
			//fin=new FileInputStream("D:\\yogiraj\\Hotstar_Android_Sanity\\Objects\\Object_repository.properties");
			fin=new FileInputStream(System.getProperty("user.dir")+"/Objects/Object_repository.properties");
			prop=new Properties();
			prop.load(fin);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	
	public By getLocator(String ElementName)
	{
	By by=null;
	try
	{
	String locator=prop.getProperty(ElementName);
	String arr[]=locator.split(">");
	String sName= arr[0];
	String sValue=arr[1];
	System.out.println("xpath name...."+sName);
	System.out.println("xpath value...."+sValue);
	
	if (sName.equalsIgnoreCase("id"))
	{
	by= By.id(sValue);
	}
	else if (sName.equalsIgnoreCase("name"))
	{
	by= By.name(sValue);
	}
	else if (sName.equalsIgnoreCase("xpath"))
	{
	by= By.xpath(sValue);
	}
	else if (sName.equalsIgnoreCase("linkText"))
	{
	by= By.linkText(sValue);
	}
	else if (sName.equalsIgnoreCase("cssSelector"))
	{
	by= By.cssSelector(sValue);
	}
	else
	{
	System.out.println("Invalid locator is passed as a parameter");
	}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
		return by;
	}
	
	/*public static String getLocator(String ElementName)
	{
		String locatorname=null;
		try
		{
			String locator=prop.getProperty(ElementName);
			System.out.println(locator);
			String arr[]=locator.split(">");
			String sName= arr[0];
			//System.out.println(sName);
			//System.out.println(sName);
			
			String sValue=arr[1];
			System.out.println(sValue);
			if(sName.equalsIgnoreCase("xpath"))
			{
				locatorname=sValue;
			}
			
			if (sName.equalsIgnoreCase("id"))
			{
				locatorname=sValue;
			}
			else if (sName.equalsIgnoreCase("name"))
			{
				by= By.name(sValue);
			}
			else if (sName.equalsIgnoreCase("xpath"))
			{
				by= By.xpath(sValue);
			}
			else if (sName.equalsIgnoreCase("linkText"))
			{
				by= By.linkText(sValue);
			}
			else if (sName.equalsIgnoreCase("cssSelector"))
			{
				by= By.cssSelector(sValue);
			}
			else
			{
				System.out.println("Invalid locator is passedasa parameter");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return locatorname;
	}
*/
}
