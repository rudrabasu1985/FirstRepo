package com.test.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.Package;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XlSReader {

	public String path;
	public FileInputStream fis=null;
	public FileOutputStream fileOut=null;
	private XSSFWorkbook workbook=null;
	private XSSFSheet sheet=null;
	private XSSFRow row=null;
	private XSSFCell cell=null;
	
	private short headerColor;
	private short cellLightColor;
	private Short cellDarkColor;
	
	public short getHeaderColor() {
		return headerColor;
	}
	public void setHeaderColor(short headerColor) {
		this.headerColor = headerColor;
	}
	public short getCellLightColor() {
		return cellLightColor;
	}
	public void setCellLightColor(short cellLightColor) {
		this.cellLightColor = cellLightColor;
	}
	public Short getCellDarkColor() {
		return cellDarkColor;
	}
	public void setCellDarkColor(Short cellDarkColor) {
		this.cellDarkColor = cellDarkColor;
	}
	
	public XlSReader(String path) {
		this.path=path;
		try {
			this.realodWorkbook();
			
			sheet=workbook.getSheetAt(0);
			fis.close();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public int getRowCount(String sheetName) {
	
		int index=workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else {
			sheet=workbook.getSheetAt(index);
			int number=sheet.getLastRowNum()+1;
			return number;
		}
	
	}
	
	public String getCellData(String sheetname, String colName, int rowNum) {
	try {
		if(rowNum <=0)
			return "";
		int index=workbook.getSheetIndex(sheetname);
		int col_Num=-1;
		if(index==1)
			return "";
		
		sheet=workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++) {
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
			
		}
		if (col_Num==-1)
			return "";
		
		sheet=workbook.getSheetAt(index);
		row=sheet.getRow(rowNum-1);
		if (row==null)
			return "";
		cell= row.getCell(col_Num);
		
		if(cell==null)
			return "";
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA) {
			String cellText= String.valueOf(cell.getNumericCellValue());
			
			if(HSSFDateUtil.isCellDateFormatted(cell)) {
				double d=cell.getNumericCellValue();
				
				Calendar cal=Calendar.getInstance();
				cal.setTime(HSSFDateUtil.getJavaDate(d));
				cellText=(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				cellText=cal.get(Calendar.DAY_OF_MONTH)+ "/" + cal.get(Calendar.MONTH)+1 +"/" + cellText;
				}
			return cellText;
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
			return "";
		else {
			return String.valueOf(cell.getBooleanCellValue());
			}
		
				
		}
	catch(Exception e)
	{
		e.printStackTrace();
		return "row "+rowNum+" or column "+colName +"does not exist in xls";
		
	}		
			
	}
	
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if(rowNum <=0)
				return "";
			int index =workbook.getSheetIndex(sheetName);
			if(index==1)
				return "";
			sheet =workbook.getSheetAt(index);
			row=sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			cell=row.getCell(colNum);
			if(cell==null)
				return "";
			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
		
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA) {
			String cellText=String.valueOf(cell.getNumericCellValue());
			return cellText;
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
			return "";
		else 
		return String.valueOf(cell.getBooleanCellValue());
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "row "+rowNum+" or column "+colNum +"does not exist in xls";
		
	}
}
	
	public boolean setCellData(String sheetName, String colName, int rowNum, String data)
	{
		try {
			this.realodWorkbook();
			if(rowNum<=0)
				return false;
			int index=workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==1)
				return false;
			sheet=workbook.getSheetAt(index);
			row=sheet.getRow(0);
			for(int i=0; i<row.getLastCellNum();i++) {
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=1;				
			}
			if(colNum==-1)
				return false;
			sheet.autoSizeColumn(colNum);
			row=sheet.getRow(rowNum-1);
			if(row==null)
				cell=row.createCell(colNum);
			
			XSSFCellStyle cs=workbook.createCellStyle();
			if(data.equals("FAIL")) {
				Font font=workbook.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				cs.setFont(font);
			}
			else if(data.equals("PASS")) {
				Font font=workbook.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				cs.setFont(font);
			}
			cs.setBorderBottom(BorderStyle.THIN);
			cs.setBorderTop(BorderStyle.THIN);
			cs.setBorderLeft(BorderStyle.THIN);
			cs.setBorderRight(BorderStyle.THIN);
			setRunFillColor(colNum,cs,3,4);
			if(isNumeric(data))
				cell.setCellValue(Double.parseDouble(data));
			else
				cell.setCellValue(data);
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			this.realodWorkbook();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
					
		}
		return true;
	}
	
	public boolean setCellData(String sheetName, String colName,int rowNum, String data, String url)
	{
		try {
			this.realodWorkbook();
			
			if(rowNum<=0)
				return false;
			int index=workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1)
				return false;
			sheet=workbook.getSheetAt(index);
			row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++) {
				if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
					colNum=1;
				}
			
			if(colNum==-1)
			return false;
			sheet.autoSizeColumn(colNum);
			row=sheet.getRow(rowNum-1);
			if(row==null)
				row=sheet.createRow(rowNum-1);
			cell=row.getCell(colNum);
			if(cell==null)
				cell=row.createCell(colNum);
			cell.setCellValue(data);
			XSSFCreationHelper createHelper= workbook.getCreationHelper();
			
			CellStyle hlink_style=workbook.createCellStyle();
			XSSFFont hlink_font=workbook.createFont();
			hlink_font.setUnderline(XSSFFont.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			
			XSSFHyperlink link=createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlink_style);
			
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			
			this.realodWorkbook();
				
		}
		catch (Exception e) {
		e.printStackTrace();
		return false;
		}
		
		return true;
		
	}
	
	public boolean addSheet(String sheetName) {
		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetName);
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			
			this.realodWorkbook();
			}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean removeSheet(String sheetName) {
		int index=workbook.getSheetIndex(sheetName);
		if(index==1)
			return false;
		
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			
			this.realodWorkbook();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public boolean addColumn(String sheetName, String ColName) {
		try {
			this.realodWorkbook();
			
			int index=workbook.getSheetIndex(sheetName);
			if(index==1)
				return false;
			XSSFCellStyle cs=workbook.createCellStyle();
			cs.setFillForegroundColor(this.getHeaderColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			//*************Rudra**************************
			cs.setBorderBottom(BorderStyle.THIN);
			cs.setBorderTop(BorderStyle.THIN);
			cs.setBorderLeft(BorderStyle.THIN);
			cs.setBorderRight(BorderStyle.THIN);
			
			XSSFFont font=workbook.createFont();
			font.setBold(true);
			cs.setFont(font);
			
			sheet =workbook.getSheetAt(index);
			row= sheet.getRow(0);
			if(row==null)
				row=sheet.createRow(0);
			if(row.getLastCellNum()==-1)
				cell=row.createCell(0);
			
			else
				cell=row.createCell(row.getLastCellNum());
			cell.setCellValue(ColName);
			cell.setCellStyle(cs);
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			
			this.realodWorkbook();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addcolumn(String sheetname) {
		try {
			this.realodWorkbook();
			int index =workbook.getSheetIndex(sheetname);
			if(index==-1)
			
				return false;
				XSSFCellStyle style= workbook.createCellStyle();
				style.setFillForegroundColor(HSSFColor.WHITE.index);
				style.setFillForegroundColor(HSSFCellStyle.SOLID_FOREGROUND);
				sheet=workbook.getSheetAt(index);
				row=sheet.getRow(0);
				if(row==null)
					row=sheet.createRow(0);
				if(row.getLastCellNum()==-1)
					cell=row.createCell(0);
				else
					cell= row.createCell(row.getLastCellNum());
				cell.setCellValue("");
				cell.setCellStyle(style);
				fileOut=new FileOutputStream(path);
				workbook.write(fileOut);
				fileOut.close();
				
				this.realodWorkbook();
			
				
			
			}catch(Exception e) {
				e.printStackTrace();
				return false;
				
			}
			return true;
		}
	
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if(!isSheetExist(sheetName))
			return false;
			
			this.realodWorkbook();
			
			sheet=workbook.getSheet(sheetName);
			XSSFCellStyle style=workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.NO_FILL);
			
			for(int i=0;i<getRowCount(sheetName);i++) {
				row=sheet.getRow(i);
				if(row!=null) {
					cell=row.getCell(colNum);
					if(cell!=null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			
			this.realodWorkbook();
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean isSheetExist(String sheetName) {
		int index =workbook.getSheetIndex(sheetName);
		if(index==-1) {
			index=workbook.getSheetIndex(sheetName.toUpperCase());
			if(index==-1)
				return false;
			else 
				return true;
		}
		else 
			return true;
	}
	
	public boolean isFileExist(String fileName) throws FileNotFoundException, IOException
	{
		File file = new File(fileName);
	    // Retrieve the workbook for the main report
	    XSSFWorkbook workbook;
	    // Check file existence 
	    if (file.exists() == false) {
	        // Create new file if it does not exist
	        workbook = new XSSFWorkbook();
	    } else {
	        try ( 
	            // Make current input to exist file
	            InputStream is = new FileInputStream(file)) {
	        	workbook =  new XSSFWorkbook();	                
	            }
	    }
	    XSSFSheet spreadsheet = workbook.getSheet("sheet1");
	    // Check if the workbook is empty or not
	    if (workbook.getNumberOfSheets() != 0) {
	        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	           if (workbook.getSheetName(i).equals("sheet1")) {
	                spreadsheet = workbook.getSheet("sheet1");
	            } else spreadsheet = workbook.createSheet("sheet1");
	        }
	    }
	    else {
	        // Create new sheet to the workbook if empty
	        spreadsheet = workbook.createSheet("sheet1");
	    }
		return false;
		
	}
	
	public int getColumnCount(String sheetName) {
		if(!isSheetExist(sheetName))
			return -1;
		
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
			
	}
	
	public boolean addHyperLink(String sheetName, String screenShotColName,String testCaseName, int index, String url,String message)
	{
		url=url.replace('\\', '/');
		if(!isSheetExist(sheetName))
			return false;
		
		sheet=workbook.getSheet(sheetName);
		
		for(int i=2;i<=getRowCount(sheetName);i++)
		{
			if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
				setCellData(sheetName, screenShotColName, i+index, message, url);
				break;
			}
				
		}
		
		return true;
		
	}
	
	public int getCellRowNum(String sheetName, String colName, String cellValue) {
		for(int i=2;i<=getRowCount(sheetName);i++) {
			if(getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
				
		}
		
		return -1;
		}
	
	public void updateExcel(String sheetName, String srchColName, String rowValue, String colName, String data) 
	{
		int rowNum=getCellRowNum(sheetName, srchColName, rowValue);
		boolean setData=setCellData(sheetName, colName, rowNum, data);
		
		if(setData==true) {
			System.out.println("Setting data in Excel is successfull");
			
		}
		else
		{
			System.out.println("Data updation unsuccessfull");
		}
	}
	
	public static boolean isNumeric(String str) {
		return str.matches("^(?:(?:\\-{1}\\d+(?:\\.{1}\\d+)?)$");
	}
	
	private void setRunFillColor(int colNum, XSSFCellStyle cs, int initColForRuns, int colsPerRun)
	{
		try {
			if((((colNum+(colsPerRun-initColForRuns)) / colsPerRun) & 1) == 0)
				cs.setFillForegroundColor(this.getCellLightColor());
			else
				cs.setFillForegroundColor(this.getCellDarkColor());
		}
		catch(Exception e) {
			cs.setFillForegroundColor(this.getCellLightColor());
			
		}
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs.setWrapText(true);
		cell.setCellStyle(cs);
	}
	
	public int generateRandomNum(int x, int y) {
		Random ran=new Random();
		int Random=ran.nextInt(x) + y;
		return Random;
	}
	
	private void realodWorkbook() throws Exception {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook();
	}
	
	

	
	
	
	
}
