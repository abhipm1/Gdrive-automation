package runner;
import java.util.*;
import java.lang.reflect.Method;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;

//import testscript.App_launch;
import testscript.Gdrive;
import datatable.Datatable;
import org.testng.annotations.*;

public class Runner
{

	
	public static String excelResultFile;
	public static String ScenarioName;
	public static String TestcaseStep;
	public static String MethodName;
	public static String ClassName;
	public static  void main(String[] args) 
	{
		excelResultFile=Datatable.createResultExcel();
		String FileName=System.getProperty("user.dir")+"/DataFiles/Controller1.xls";
		int rc1=Datatable.rowCount(FileName, "Scenarios");
		System.out.println(rc1);
		
		for(int i=2;i<=rc1;i++)
		{
			String TestcaseID=Datatable.getCellValue(FileName, "Scenarios", "Scenario ID", i);
			ScenarioName=Datatable.getCellValue(FileName, "Scenarios", "ScenarioName", i);
			String runStatus=Datatable.getCellValue(FileName, "Scenarios", "RunStatus", i);
			Class driver1[]=new Class[1];
			driver1[0]=WebDriver.class;
			
			if (runStatus.equalsIgnoreCase("yes"))
			{
				//Navigate_Launch.setUp();
				Gdrive.inValidLogin();
				//int rc2=Datatable.rowCount(FileName, TestcaseID);
				//for (int j=2;j<=rc2;j++)
				//{
					TestcaseStep=Datatable.getCellValue(FileName, "Scenarios", "ScenarioID", i);
					MethodName=Datatable.getCellValue(FileName, "Scenarios", "MethodName", i);
					ClassName=Datatable.getCellValue(FileName, "Scenarios", "ClassName", i);
					System.out.println(TestcaseStep);
					System.out.println(MethodName);
					System.out.println(ClassName);
					try
					{
					Class cls=Class.forName(ClassName);
				 	Object obj=cls.newInstance();
					Method M=obj.getClass().getMethod(MethodName);
					System.out.println(M);
					String result=(String) M.invoke(obj);
					//Navigate_Launch.closeApp();
					} 
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
			//}
		}
		

	}
}
