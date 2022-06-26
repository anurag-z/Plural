package com.bsli.ibm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class ExcelUtil 
{
	private static Sheet sheet;
	private static Cell cell;
	private static Row row;
	private String excelfilePath;
	
	private ExcelUtil()
	{
		
	}
	
	public ExcelUtil(String path)
	{
		excelfilePath=path;
	}


	public HashMap<String,String> getExcelDataByKey(String key,String sheetName) throws Exception
	{
		return getExcelDataByKey(key,sheetName,excelfilePath);
	}
	
	public HashMap<String,String> getExcelDataByKey(String key,String sheetName,String excelfilePath) throws Exception
	{
		HashMap<String,String> excelData=new HashMap<String, String>();
		FileInputStream fis = new FileInputStream(excelfilePath);
		 
		Workbook workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		
		//keyIndex=findColumnIndex(key);
		int rownumtoFetch=0;
		int lastRow = sheet.getLastRowNum();
		for (int j = 0; j < lastRow; j++)
		{
		
			
			if(cellToString(sheet.getRow(j).getCell(0)).equalsIgnoreCase(key.split("~")[0]))
			{
				rownumtoFetch=j;
				break;
			}
		}		


		LinkedHashMap<String, String> testdata = new LinkedHashMap<String, String>();
				
				for (int j = 0; j < sheet.getRow(rownumtoFetch).getLastCellNum(); j++)
				{	
					try
					{

						sheet.getRow(0).getCell(j).setCellType(Cell.CELL_TYPE_STRING);
						sheet.getRow(rownumtoFetch).getCell(j).setCellType(Cell.CELL_TYPE_STRING);
						testdata.put(sheet.getRow(0).getCell(j).getStringCellValue(),sheet.getRow(rownumtoFetch).getCell(j).getStringCellValue() );
						// System.out.println(sheet.getRow(0).getCell(j).getStringCellValue()+" " +sheet.getRow(i).getCell(j).getStringCellValue());
					}
					catch(Throwable e)
					{
						//System.out.println(sheet.getRow(0).getCell(j).getRichStringCellValue()+" "+sheet.getRow(i).getCell(j).getCellType());
						// System.out.println(sheet.getRow(0).getCell(j).getStringCellValue()+" " +sheet.getRow(i).getCell(j).getStringCellValue());
						//e.printStackTrace();
					}
				}	
				/*try
				{
					result.put(key,testdata);

				}
				catch(Throwable e)
				{

				}*/

			
		
		excelData=testdata;
		return excelData;
		
	}
	public static String getCellData(int rowNum, int colNum) throws Exception {
		try {
			cell = sheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			return "";
		}
	}

	public static int findColumnIndex(String ColumnHeader)
	{  int ColumnCount,CurrentColumn;
	CurrentColumn=-1;
	Row fr=sheet.getRow(0);
	ColumnCount=fr.getLastCellNum()-fr.getFirstCellNum();



	for (int i=0;i<=ColumnCount-1;i++)
	{
		if(fr.getCell(i).getStringCellValue().contains(ColumnHeader)) 
		{

			CurrentColumn=i;

			break;		
		}
	}
	return CurrentColumn;

	}
	
	public ArrayList<HashMap<String,String>> getExcelDataBySheet(String sheetName) throws Exception
	{
		return getExcelDataBySheet(sheetName,excelfilePath);
	}
	
	
	public ArrayList<HashMap<String,String>> getExcelDataBySheet(String sheetName,String excelfilePath) throws Exception
	{
		ArrayList<HashMap<String,String>> excelData=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> excelRow=new HashMap<String,String>();
		
		FileInputStream fis = new FileInputStream(excelfilePath);
		 
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		
		
		for (int i = 1; i < sheet.getLastRowNum();i++)
		{
			excelRow=new HashMap<String,String>();
			for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++)
			{
				excelRow.put(cellToString(sheet.getRow(0).getCell(j)), cellToString(sheet.getRow(i).getCell(j)));
//				System.out.println(cellToString(sheet.getRow(0).getCell(j)) + " - " + cellToString(sheet.getRow(i).getCell(j)));
			}
			excelData.add(excelRow);
		}

		return excelData;
		
	}	
	
	
	
	
	
	
	
	
	public void copyworkbook(String fileFullPath, String resultPath) throws IOException, InterruptedException
	{

		File originalWb = new File(fileFullPath);

		// The output file, you don't need to call clonedWb.createNewFile()
		File clonedWb = new File(resultPath);

		if(clonedWb.exists()){

			clonedWb.delete();
			Thread.sleep(1000);
		}

		Files.copy(originalWb.toPath(), clonedWb.toPath());
	}
	
	
	public String cellToString(Cell cell) 
	{
		
		Object result;
		if(cell !=null)
		{
			switch (cell.getCellTypeEnum()) 
			{
			case NUMERIC: // 0
				result = cell.getNumericCellValue();
				break;
			case STRING: // 1
				result = cell.getStringCellValue();
				break;
			case FORMULA: // 2
				result="";
//				throw new RuntimeException("We can't evaluate formulas in Java");
			case BLANK: // 3
				result = "-";
				break;
			case BOOLEAN: // 4
				result = cell.getBooleanCellValue();
				break;
			case ERROR: // 5
				result="";
//				throw new RuntimeException("This cell has an error");
			default:
				throw new RuntimeException("We don't support this cell type: " + cell.getCellTypeEnum());
			}
		}
		else
		{
			result = "";
		}
		return result.toString();
	}
	
	
//	public static void main(String args[])
//	{
//		ExcelUtil obj=new ExcelUtil();
//		try {
//			System.out.println(obj.getExcelDataByKey("tc_id~004","CritiSheild-Maj-Diff", "C:\\Users\\PrashantPetkar\\Box Sync\\MyTP\\Clients\\BSLI\\TestAutomation\\MAPP\\TestData.xlsx"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	

}