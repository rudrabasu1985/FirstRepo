package com.test.common;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class CommonBaseTest extends  CommonLib{
	
	@BeforeSuite
	public void loadConfig() {
		System.out.println("load the Config file @Before Suite");
		frameprops=(FrameworkData) fwdObj.unmarshal(System.getProperty("user.dir")+"\\Automation");
		System.out.println("Value of Framepros is :" +frameprops);
		
	}
	
	@BeforeMethod
	public void LoadTestSetup(Method method) throws InterruptedException {
		SimpleClassName=method.getName();

		System.out.println("Into Load Test Step");
//		CreateExtentReport(SimpleClassName);
		
		
	}
	
	@AfterMethod
	public void cleanup() {
		System.out.println("@After Test");
		
		closeDriver();
		SaveExtentReport();
	}
		
	
	  @AfterSuite public void closeSuiteSetup() {
	  //EmailTestResult("Test Result","Hi Team Find the Result","email@address.com")
	  
	  }
	 
}

		
		
	
	
	
	
	


