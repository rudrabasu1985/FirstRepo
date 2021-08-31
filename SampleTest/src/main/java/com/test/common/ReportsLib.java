package com.test.common;

import java.io.File;
import java.util.Date;

import org.testng.Reporter;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportsLib {
	
	private static ExtentReports extent;
	public static String resfileName;
	public static ExtentTest test;
	public static ExtentReports rep;
	public static CommonLib CommonLib=new CommonLib();
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			Date d=new Date();
			System.out.println("Current timestamp: "+d.toString());
			resfileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent= new ExtentReports(System.getProperty("user.dir")+"\\Results\\"+resfileName, true, DisplayOrder.NEWEST_FIRST);
			//extent= new ExtentReports("myreport.html", true);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\ReportsConfig.xml"));
			extent.addSystemInfo("Selenium Version", "3.4.0").addSystemInfo("Environment", "QA");
			System.out.println("The file name is : " + resfileName);
			
		}
		return extent;
	}
	
	public static void CreateExtentReport(String TestScenario)  {
		rep=ReportsLib.getInstance();	
		System.out.println("Starting with testing");
		test=rep.startTest(TestScenario);

		System.out.println("Flush the report" + test);
		rep.flush();

		
		
	}
	
	public static void SaveExtentReport() {
		rep.endTest(test);
		rep.flush();
		
	}
	
	
	public void reportPass(String msg) {
		test.log(LogStatus.PASS, msg);
		CommonLib.takeScreenShot(); 
		rep.flush();
		Reporter.log(msg, true);
	}
	
	public void reportFailure(String msg) {
		test.log(LogStatus.FAIL, msg);
		//CommonLib.scrollAndTakeScreenShot(); 
		CommonLib.takeScreenShot();
		rep.flush();
		Reporter.log(msg, true);
	}
	
	public void reportInfo(String msg) {
		test.log(LogStatus.INFO, msg);
		rep.flush();
		Reporter.log(msg, true);
	}
	
	public void printActionLog(String logLevel,String msg) {
		switch(logLevel) {
		case "PASS":
			reportPass(msg);
			break;
		case "FAIL":
			reportFailure(msg);
			break;
		default:
			reportInfo(msg);
			break;
		
		}
		
		System.out.println("  [INFO] "  +logLevel+ ":" + msg);
		
	}

}
