package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReuseMethod extends BaseClass{
	BaseClass base;
	Properties prop;
	String excel;
	public  static HashMap<String, String>  getTestCaseData(String sheetName , String testCaseName) throws IOException	
	{
	        //C:\Users\Ojha\CRMSite
		FileInputStream fis = new FileInputStream("C:\\Users\\Ojha\\CRMSite\\testData.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		int whichColumn=0;
		int CellNumber =0;
		XSSFCell headerValue;
		String headerValueString;
		HashMap<String, String> mp = new HashMap<String, String>();
		int sheetcount = workbook.getNumberOfSheets(); 
		for (int i=0 ; i<sheetcount ; i++)
		{
			String name = workbook.getSheetName(i);
			System.out.println(name);
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				System.out.println("sheet found");
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows= sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int k=0;
				while(cells.hasNext())
				{
					Cell cellvalue = cells.next();
					if(cellvalue.getStringCellValue().equalsIgnoreCase("TestCaseName"))
					{
						whichColumn = k ;
						System.out.println("Test case column found at column "+ k);
					}	
					k++;
				}
				while(rows.hasNext())
				{
					Row r = rows.next();
					
					if(r.getCell(whichColumn).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						System.out.println("row found");
						Iterator<Cell> c = r.cellIterator();
						while(c.hasNext())
						{
							headerValue = workbook.getSheetAt(i).getRow(0).getCell(CellNumber);
							headerValueString = headerValue.getStringCellValue();
							//c.next().getStringCellValue()
							mp.put(headerValueString , c.next().getStringCellValue());
							CellNumber++;
						}						
					}				
				}			
			}
			else
			{
				System.out.println("Sheet not found in Excel");
			}	
		}
		return mp;
	}
	
}
