/*
 * package com.test.common;
 * 
 * import java.io.File; import java.io.FileInputStream; import
 * java.io.FileNotFoundException; import java.io.FileOutputStream; import
 * java.io.IOException; import java.io.OutputStream;
 * 
 * import java.io.PrintStream; import java.util.ArrayList; import
 * java.util.HashMap;
 * 
 * import org.apache.poi.hssf.usermodel.HSSFSheet; import
 * org.apache.poi.hssf.usermodel.HSSFWorkbook; import
 * org.apache.poi.ss.usermodel.Cell; import org.apache.poi.ss.usermodel.Row;
 * import org.apache.poi.ss.usermodel.Sheet; import
 * org.apache.poi.ss.usermodel.Workbook;
 * 
 * import org.apache.poi.xssf.usermodel.XSSFCell; import
 * org.apache.poi.xssf.usermodel.XSSFRow; import
 * org.apache.poi.xssf.usermodel.XSSFSheet; import
 * org.apache.poi.xssf.usermodel.XSSFWorkbook;
 * 
 * import com.test.common.CommonLib;
 * 
 * 
 * public class OutputStreamRedirect extends CommonLib{
 * 
 * 
 * static CommonLib cdC=new CommonLib();
 * 
 * 
 * 
 * 
 * public static void writeExcelKey() throws FileNotFoundException, IOException
 * {
 * 
 * 
 * // Create a workbook instances Workbook wb = new HSSFWorkbook();
 * 
 * OutputStream os = new
 * FileOutputStream("C://Rudra-Work//ExcelFile//SystemInformationDetails.xls");
 * 
 * // Creating a sheet using predefined class provided by Apache POI Sheet sheet
 * = wb.createSheet("SystemEntity");
 * 
 * Row row = sheet.createRow(0); // Specific cell number Cell cell =
 * row.createCell(0); // putting value at specific position
 * cell.setCellValue("Display Name");
 * 
 * 
 * //Row row1 = sheet.createRow(0); // Specific cell number Cell cell1 =
 * row.createCell(1); // putting value at specific position
 * cell1.setCellValue("Security System");
 * 
 * Cell cell2 = row.createCell(2); // putting value at specific position
 * cell2.setCellValue("Parent Account Name");
 * 
 * Cell cell3 = row.createCell(3); cell3.setCellValue("Parent End Name");
 * 
 * Cell cell4 = row.createCell(4); cell4.setCellValue("Description");
 * 
 * Cell cell5 = row.createCell(5); cell5.setCellValue("Owner Type");
 * 
 * Cell cell6 = row.createCell(6); cell6.setCellValue("Owner");
 * 
 * Cell cell7 = row.createCell(7); cell7.setCellValue("Resource Owner Type");
 * 
 * Cell cell8 = row.createCell(8); cell8.setCellValue("Resource Owner");
 * 
 * Cell cell9 = row.createCell(9); cell9.setCellValue("Authoritative Source");
 * 
 * Cell cell10 = row.createCell(10); cell10.setCellValue("Initials");
 * 
 * Cell cell11 = row.createCell(11); cell11.setCellValue("Identity Status");
 * 
 * Cell cell12 = row.createCell(12); cell12.setCellValue("AD Company Code");
 * 
 * Cell cell13 = row.createCell(13);
 * cell13.setCellValue("Information Sharing Indicator");
 * 
 * Cell cell14 = row.createCell(14); cell14.setCellValue("Access Query");
 * 
 * Cell cell15 = row.createCell(15);
 * cell15.setCellValue("Entitilement Filter Query for Request Start/End Date");
 * 
 * Cell cell16 = row.createCell(16); cell16.
 * setCellValue("The Account Type for which Deprovisioning Task not Get Created"
 * );
 * 
 * Cell cell17 = row.createCell(17);
 * cell17.setCellValue("Account Type for which Password should not get Changed"
 * );
 * 
 * Cell cell18 = row.createCell(18); cell18.setCellValue("Enable Copy Access");
 * 
 * sECURITY sYSTEM dETAILS Sheet sheet1 =
 * wb.createSheet("Security System Deatils"); Row row1 = sheet1.createRow(0);
 * 
 * 
 * wb.write(os);
 * 
 * 
 * }
 * 
 * public static void updateExcel(String value1, String value2, String value3,
 * String value4, String value5, String value6, String value7, String value8,
 * String value9, String value10, String value11, String value12, String
 * value13, String value14, String value15, String value16, String value17,
 * String value18) throws IOException {
 * 
 * 
 * try { FileInputStream file = new FileInputStream(new
 * File("C://Rudra-Work//ExcelFile//SystemInformationDetails.xls"));
 * HSSFWorkbook wb = new HSSFWorkbook(file);
 * 
 * HSSFSheet sheet = wb.getSheetAt(0); Cell cell = null;
 * 
 * 
 * //Update the value of cell // cell = sheet.getRow(1).createCell(0); cell =
 * sheet.createRow(1).createCell(0); cell = sheet.getRow(1).getCell(0);
 * 
 * //cell.setCellValue(cell.getNumericCellValue() * 2);
 * cell.setCellValue(value1); //cell.setCellValue(cell.getNumericCellValue() *
 * 2);
 * 
 * cell = sheet.getRow(1).createCell(1); cell = sheet.getRow(1).getCell(1);
 * cell.setCellValue(value2);
 * 
 * cell = sheet.getRow(1).createCell(2); cell = sheet.getRow(1).getCell(2);
 * cell.setCellValue(value3);
 * 
 * cell = sheet.getRow(1).createCell(3); cell = sheet.getRow(1).getCell(3);
 * cell.setCellValue(value4);
 * 
 * cell = sheet.getRow(1).createCell(4); cell = sheet.getRow(1).getCell(4);
 * cell.setCellValue(value5);
 * 
 * cell = sheet.getRow(1).createCell(5); cell = sheet.getRow(1).getCell(5);
 * cell.setCellValue(value6);
 * 
 * cell = sheet.getRow(1).createCell(6); cell = sheet.getRow(1).getCell(6);
 * cell.setCellValue(value7);
 * 
 * cell = sheet.getRow(1).createCell(7); cell = sheet.getRow(1).getCell(7);
 * cell.setCellValue(value8);
 * 
 * cell = sheet.getRow(1).createCell(8); cell = sheet.getRow(1).getCell(8);
 * cell.setCellValue(value9);
 * 
 * cell = sheet.getRow(1).createCell(9); cell = sheet.getRow(1).getCell(9);
 * cell.setCellValue(value10);
 * 
 * cell = sheet.getRow(1).createCell(10); cell = sheet.getRow(1).getCell(10);
 * cell.setCellValue(value11);
 * 
 * cell = sheet.getRow(1).createCell(11); cell = sheet.getRow(1).getCell(11);
 * cell.setCellValue(value12);
 * 
 * cell = sheet.getRow(1).createCell(12); cell = sheet.getRow(1).getCell(12);
 * cell.setCellValue(value13);
 * 
 * cell = sheet.getRow(1).createCell(13); cell = sheet.getRow(1).getCell(13);
 * cell.setCellValue(value14);
 * 
 * cell = sheet.getRow(1).createCell(14); cell = sheet.getRow(1).getCell(14);
 * cell.setCellValue(value15);
 * 
 * cell = sheet.getRow(1).createCell(15); cell = sheet.getRow(1).getCell(15);
 * cell.setCellValue(value16);
 * 
 * cell = sheet.getRow(1).createCell(16); cell = sheet.getRow(1).getCell(16);
 * cell.setCellValue(value17);
 * 
 * cell = sheet.getRow(1).createCell(17); cell = sheet.getRow(1).getCell(17);
 * cell.setCellValue(value18);
 * 
 * file.close();
 * 
 * FileOutputStream outFile =new FileOutputStream(new
 * File("C://Rudra-Work//ExcelFile//SystemInformationDetails.xls"));
 * wb.write(outFile); outFile.close();
 * 
 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * 
 * 
 * }
 * 
 * public void setExcelKey() { try { FileInputStream fStream = new
 * FileInputStream(new
 * File("C://Rudra-Work//ExcelFile//SystemInformationDetails.xls")); //Enter the
 * path to your excel here
 * 
 * // Create workbook instance referencing the file created above XSSFWorkbook
 * workbook = new XSSFWorkbook();
 * 
 * // Get your first or desired sheet from the workbook XSSFSheet sheet =
 * workbook.getSheetAt(0); // getting first sheet
 * 
 * XSSFRow row = sheet.getRow(1); XSSFCell cell1 = row.getCell(0); XSSFCell
 * cell2 = row.getCell(1); XSSFCell cell3 = row.getCell(2);
 * 
 * String systemName = cell1.toString();
 * System.out.println("Need to know the value: " +systemName); String
 * displayName = cell1.toString(); String hostName = cell2.toString(); String
 * port = cell3.toString();
 * 
 * 
 * 
 * fStream.close(); } catch (Exception e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * }
 * 
 * }
 */