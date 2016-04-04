package datatable;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import runner.Runner;
import utility.MyLibs;

public class Datatable 
{
	public static Workbook wb;
	public static Sheet sh;
	public static Row row;
	public static Cell cell;
	public static FileInputStream fin=null;
	public static FileOutputStream fout=null;
	public static int serialno=1;
	public static void main(String[] args)
	{
	
	}
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public static  int rowCount(String FileName,String SheetName)
	{
		Workbook wb;
		Sheet sh;
		int rc=0;
		try
		{
			fin=new FileInputStream(FileName);
			wb=new HSSFWorkbook(fin);
			sh=wb.getSheet(SheetName);
			rc=sh.getPhysicalNumberOfRows();
			fin.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rc;
	}
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public  int rowCount(String SheetName)
	{
		int rc=0;
		try
		{
			sh=wb.getSheet(SheetName);
			rc=sh.getLastRowNum();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rc;
	}
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public void importExcel(String FileName)
	{
		try
		{
			fin=new FileInputStream(FileName);
			wb=new HSSFWorkbook(fin);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public static  String getCellValue(String FileName,String SheetName,String ColName,int rownum)
	{
		int colNum=0;
		String cellValue=null;
		try
		{
			fin=new FileInputStream(FileName);
			wb=new HSSFWorkbook(fin);
			sh=wb.getSheet(SheetName);
			row=sh.getRow(0);
			if(sh==null)
			{
				sh=wb.createSheet(SheetName);
			}
			for (int c=0;c<row.getLastCellNum();c++)
			{
				cell=row.getCell(c);
				String val=cell.getStringCellValue();
				if (ColName.trim().equalsIgnoreCase(val.trim()))
				{
					colNum=c;
				}
			}
			
			row=sh.getRow(rownum-1);
			cell=row.getCell(colNum);
			if (cell==null || cell.getCellType()==cell.CELL_TYPE_BLANK)
			{
				cellValue="";
			}
			else if (cell.getCellType()==cell.CELL_TYPE_STRING)	
			{
				cellValue=cell.getStringCellValue();
			}
			else if (cell.getCellType()==cell.CELL_TYPE_BOOLEAN)
			{
				cellValue=String.valueOf(cell.getBooleanCellValue());
			}
			else if (cell.getCellType()==cell.CELL_TYPE_FORMULA || cell.getCellType()==cell.CELL_TYPE_NUMERIC)
			{
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					double d=cell.getNumericCellValue();
					Calendar cal=Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellValue=(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.YEAR);
				}
				else
				{
					cellValue=String.valueOf(cell.getNumericCellValue());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return cellValue;
	}
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public String getCellValue(String SheetName,String ColName,int rownum)
	{
		int colNum=0;
		String cellValue=null;
		try
		{
			sh=wb.getSheet(SheetName);
			row=sh.getRow(0);
			for (int c=0;c<row.getLastCellNum();c++)
			{
				cell=row.getCell(c);
				String val=cell.getStringCellValue();
				if (ColName.trim().equalsIgnoreCase(val.trim()))
				{
					colNum=c;
				}
			}
			
			row=sh.getRow(rownum-1);
			cell=row.getCell(colNum);
			if (cell==null || cell.getCellType()==cell.CELL_TYPE_BLANK)
			{
				cellValue="";
			}
			else if (cell.getCellType()==cell.CELL_TYPE_STRING)	
			{
				cellValue=cell.getStringCellValue();
			}
			else if (cell.getCellType()==cell.CELL_TYPE_BOOLEAN)
			{
				cellValue=String.valueOf(cell.getBooleanCellValue());
			}
			else if (cell.getCellType()==cell.CELL_TYPE_FORMULA || cell.getCellType()==cell.CELL_TYPE_NUMERIC)
			{
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					double d=cell.getNumericCellValue();
					Calendar cal=Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellValue=(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.YEAR);
				}
				else
				{
					cellValue=String.valueOf(cell.getNumericCellValue());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return cellValue;
	}

	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public static void setCellValue(String FileName,String SheetName,String ColName,String data,int rownum)
	{
		int colNum=0;
		try
		{
			fin=new FileInputStream(FileName);
			wb=new HSSFWorkbook(fin);
			sh=wb.getSheet(SheetName);
			if (sh==null)
			{
				sh=wb.createSheet(SheetName);
			}
			row=sh.getRow(0);
			for (int c=0;c<row.getLastCellNum();c++)
			{
				cell=row.getCell(c);
				String val=cell.getStringCellValue();
				if (ColName.trim().equalsIgnoreCase(val.trim()))
				{
					colNum=c;
				}
			}
			row=sh.getRow(rownum-1);
			if (row==null)
			{
				row=sh.createRow(rownum-1);
			}
			cell=row.getCell(colNum);
			if (cell==null)
			{
				cell=row.createCell(colNum);
			}
			cell.setCellValue(data);
			fout=new FileOutputStream(FileName);
			wb.write(fout);
			fin.close();
			fout.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public static String createResultExcel() 
	{
		FileOutputStream fout=null;
		Workbook wb=null;
		Sheet sh=null;
		Row row=null;
		Cell cell=null;
		String fileName=null;
		String sDate=null;
		String stime=null;
		try
		{
			sDate=MyLibs.getDateTime("MM-dd-yyyy");
			stime=MyLibs.getDateTime("hh-mm-ss");
			String FolderPath=System.getProperty("user.dir")+"\\Results";
			fileName=FolderPath+"\\dittotv_Results_"+sDate+"_"+stime+".xls";
			wb=new HSSFWorkbook();
			sh=wb.createSheet("Results");
			row=sh.createRow(0);
			cell=row.createCell(0);
			cell.setCellValue("S.No");
			cell=row.createCell(1);
			cell.setCellValue("TestScriptName");
			cell=row.createCell(2);
			cell.setCellValue("Input1");
			cell=row.createCell(3);
			cell.setCellValue("Input2");
			/*cell=row.createCell(4);
			cell.setCellValue("Input3");
			cell=row.createCell(5);
			cell.setCellValue("Input4");*/
			cell=row.createCell(4);
			cell.setCellValue("ExpectedResult");
			cell=row.createCell(5);
			cell.setCellValue("ActualResult");
			cell=row.createCell(6);
			cell.setCellValue("Status");
			cell=row.createCell(7);
			cell.setCellValue("Logged-in-User");
			cell=row.createCell(8);
			cell.setCellValue("ExecutionDate");
		    fout=new FileOutputStream(fileName);
		    wb.write(fout);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				fout.close();
				fout=null;
				wb=null;
				sh=null;
				row=null;
				cell=null;
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/*
	 * Created By:
	 * Created Date:
	 * Reviewed By:
	 * Modified By:
	 * Parameters:
	 * Purpose:
	 */
	public static void writeResults(String testscriptName,ArrayList<String> input,String expected,String actual,String Anonymoususer,String loggedinuser,String executiondate)
	{
		FileInputStream fin=null;
		FileOutputStream fout=null;
		Workbook wb=null;
		Sheet sh=null;
		Row row=null;
		Cell cell=null;
		
		try
		{
			fin=new FileInputStream(Runner.excelResultFile);
			wb=new HSSFWorkbook(fin);
			sh=wb.getSheet("Results");
			int rc=rowCount(Runner.excelResultFile, "Results");
			row=sh.getRow(rc+1);
			if (row==null)
			{
				row=sh.createRow(rc+1);
				cell=row.getCell(0);
				if (cell==null)
				{
					cell=row.createCell(0);
				}
				cell.setCellValue(serialno);
				serialno+=1;
				
				cell=row.getCell(1);
				if (cell==null)
				{
					cell=row.createCell(1);
				}
				cell.setCellValue(testscriptName);
				
				cell=row.getCell(2);
				if (cell==null)
				{
					cell=row.createCell(2);
				}
				cell.setCellValue(input.get(0));
				
				cell=row.getCell(3);
				if (cell==null)
				{
					cell=row.createCell(3);
				}
				cell.setCellValue(input.get(1));
				
				/*cell=row.getCell(4);
				if (cell==null)
				{
					cell=row.createCell(4);
				}
				cell.setCellValue(input.get(2));
				
				cell=row.getCell(5);
				if (cell==null)
				{
					cell=row.createCell(5);
				}
				cell.setCellValue(input.get(3));
				*/
				cell=row.getCell(4);
				if (cell==null)
				{
					cell=row.createCell(4);
				}
				cell.setCellValue(expected);
				
				cell=row.getCell(5);
				if (cell==null)
				{
					cell=row.createCell(5);
				}
				cell.setCellValue(actual);
				
				cell=row.getCell(6);
				if (cell==null)
				{
					cell=row.createCell(6);
				}
				cell.setCellValue(Anonymoususer);
				
				cell=row.getCell(7);
				if (cell==null)
				{
					cell=row.createCell(7);
				}
				cell.setCellValue(loggedinuser);
				
				cell=row.getCell(8);
				if (cell==null)
				{
					cell=row.createCell(8);
				}
				cell.setCellValue(executiondate);
			}
			fout=new FileOutputStream(Runner.excelResultFile);
			wb.write(fout);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fin.close();
				fin=null;
				fout.close();
				fout=null;
				wb=null;
				sh=null;
				row=null;
				cell=null;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
