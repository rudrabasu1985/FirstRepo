package com.test.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.PasswordAuthentication;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.commons.io.FileUtils;

import org.apache.tools.ant.taskdefs.email.Message;
import org.eclipse.jetty.util.MultiPartInputStreamParser.MultiPart;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;

public class CommonLib extends ReportsLib {

	public static WebDriver driver;
	// public static OutputStreamRedirect osr=new OutputStreamRedirect();
	public static JAXBToObject<TestData> jaxbObj = new JAXBToObject<TestData>(TestData.class);
	public static TestData tdObj;
	public static JAXBToObject<FrameworkData> fwdObj = new JAXBToObject<FrameworkData>(FrameworkData.class);
	public static FrameworkData frameprops;
	public static XlSReader xlsWriter;
	public static String SimpleClassName;
	public static Actions action;

	public void click(String locatorkey) {
		getElement(locatorkey).click();
		reportInfo("Clicked successfully on: " + locatorkey);
	}

	/*
	 * public boolean click(String clickLocatorKey, String expObj, String
	 * screenName, double startTime, boolean reload, boolean timed) {
	 * click(clickLocatorKey); return true; }
	 */

	
	
	
	public void refresh()
	{
		
		driver.navigate().refresh();
		
	}
	
	public boolean click(String clickLocatorKey, String expObj, String screenName, double startTime, boolean reload, boolean timed) throws InterruptedException {
        click(clickLocatorKey);
        waitForPageToLoad();
       
        String PageNameActual=driver.getTitle();
        System.out.println("Open Page URL is: " +PageNameActual);       
        if(PageNameActual.contains(screenName))
        {
            System.out.println("Expected and Actual Page title matches");
            if(isElementPresent(expObj)) {
                reportPass("expObj getting displayed");
               
            }
            return true;   
        }
       
        else
        {
            reportFailure("expObj is not getting displayed");
            return false;
        }
       
       
    }
	
	
	
	
	public boolean click(String clickLocatorKey, String expObj, String screenName, boolean reload, boolean timed) {
		click(clickLocatorKey);
		return true;
	}

	public boolean click(String clickLocatorKey, boolean reload) {
		click(clickLocatorKey);
		return (reload);
	}

	public void type(String locatorkey, String data) {
		getElement(locatorkey).sendKeys(data);
		reportInfo("Typed successfully on: " + locatorkey);

	}

	public void cleartext(String locatorkey) {
		getElement(locatorkey).clear();
		reportInfo("Text CLeared successfully in: " + locatorkey);
	}

	public void setDropDown(String locatorkey, String data) {
		System.out.println("Trying to click on dropdown");
		Select drop = new Select(getElement(locatorkey));
		System.out.println("clicked on dropdown");
		try {
			drop.selectByVisibleText(data);
			reportInfo("Selected <" + data + "> on drop down");
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test -" + ex.getMessage());
		}
	}

	public WebElement getElement(String locatorkey) {
		WebElement e = null;
		try {
			if (locatorkey.startsWith("ID_")) {
				locatorkey = locatorkey.replace("ID_", "");
				e = driver.findElement(By.id(locatorkey));
			} else if (locatorkey.startsWith("NAME_")) {
				locatorkey = locatorkey.replace("NAME_", "");
				e = driver.findElement(By.name(locatorkey));
			} else if (locatorkey.startsWith("XPATH_")) {
				locatorkey = locatorkey.replace("XPATH_", "");
				e = driver.findElement(By.xpath(locatorkey));

			} else {
				scrollAndTakeScreenShot();
				test.log(LogStatus.FAIL, "Locator not correct " + locatorkey);
				Assert.fail("Locator not correct " + locatorkey);
			}

		} catch (Exception ex) {
			scrollAndTakeScreenShot();
			test.log(LogStatus.FAIL, ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test -" + ex.getMessage());
		}
		return e;

	}

	public void typeEnter(String locatorkey) {
		getElement(locatorkey).sendKeys(Keys.ENTER);
		reportInfo("Enter button pressed successfully");

	}

	public List<WebElement> getElements(String locatorkey) {
		List<WebElement> elementList = null;
		if (locatorkey.startsWith("ID_")) {
			locatorkey = locatorkey.replace("ID_", "");
			elementList = driver.findElements(By.id(locatorkey));
		} else if (locatorkey.startsWith("NAME_")) {
			locatorkey = locatorkey.replace("NAME_", "");
			elementList = driver.findElements(By.id(locatorkey));
		} else if (locatorkey.startsWith("XPATH_")) {
			locatorkey = locatorkey.replace("XPATH_", "");
			elementList = driver.findElements(By.id(locatorkey));
		} else {
			scrollAndTakeScreenShot();
			test.log(LogStatus.FAIL, "Locator not correct " + locatorkey);
			Assert.fail("Locator not correct " + locatorkey);
		}
		if (elementList.size() == 0) {
			test.log(LogStatus.FAIL, "No Element found to match Locator: " + locatorkey);
			Assert.fail("Fail the test - No element Found " + locatorkey);
		}
		return elementList;

	}

	public void pressEscape(String locatorkey) {
		getElement(locatorkey).sendKeys(Keys.ESCAPE);
		reportInfo("Escape button pressed successfully");
	}

	public void pressTab(String locatorkey) {
		getElement(locatorkey).sendKeys(Keys.TAB);
		reportInfo("Tab button pressed successfully");
	}

	
	
	public void openNewTab(String URL) {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        
        
        driver.switchTo().window(tabs.get(1));
        wait(2);
        driver.close();
        driver.switchTo().window(tabs.get(0));
        
        driver.get(URL);
        
        waitForPageToLoad();
          
  
    }
	
	
	
	
	public WebElement getRandomElementFromList(List<WebElement> elements) {
		int index = new Random().nextInt(elements.size());
		return elements.get(index);
	}

	public void clickRandom(String locatorkey) {
		WebElement element = getRandomElementFromList(getElements(locatorkey));
		if (!element.isDisplayed())
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		try {
			element.click();
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed The Test : " + ex.getMessage());

		}
		reportInfo("Clicked on the Random element matching : " + locatorkey);
	}

	public boolean verifyTitle(String ExpTitle) {
		String ActualTitle = driver.getTitle();
		System.out.println("The Actual Title is: " + ActualTitle);
		if (ActualTitle.equals(ExpTitle))
			return true;
		else
			return false;
	}

	public boolean isElementPresent(String locatorkey) throws InterruptedException {
		List<WebElement> elementList = null;
		Thread.sleep(2000);
		if (locatorkey.startsWith("ID_")) {
			locatorkey = locatorkey.replace("ID_", "");
			elementList = driver.findElements(By.id(locatorkey));
		} else if (locatorkey.startsWith("NAME_")) {
			locatorkey = locatorkey.replace("NAME_", "");
			elementList = driver.findElements(By.name(locatorkey));
		} else if (locatorkey.startsWith("XPATH_")) {
			locatorkey = locatorkey.replace("XPATH_", "");
			elementList = driver.findElements(By.xpath(locatorkey));
		} else {
			test.log(LogStatus.FAIL, "Locator not correct " + locatorkey);

		}
		if (elementList.size() == 0)
			return false;
		else
			return true;

	}

	public boolean verifyText(String locatorkey, String expectedTextKey) {
		String actualText = getElement(locatorkey).getText().trim();
		System.out.println("Actual Text capture: " + actualText);
		if (actualText.equals(expectedTextKey)) {
			reportPass("Expected and Actual Result matched");
			return true;
		} else {
			reportFailure("Expected and Actual Result not matched");
			return false;
		}
	}

	public void clickAndWait(String locClicked, String locToWaitFor) throws InterruptedException {
		int count = 5;
		for (int i = 0; i < count; i++) {
			getElement(locClicked).click();
			wait(2);
			if (isElementPresent(locToWaitFor))
				break;
			rep.flush();

		}

	}

	public void waitForPageToLoad() {
		wait(1);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String state = (String) js.executeScript("return document.readyState");
		while (!state.equals("complete")) {
			wait(2);
			state = (String) js.executeScript("return document.readyState");
		}
		System.out.println("Page Load Succssfully");
	}

	public boolean trackLoadTime(String ExpObject, String screenName, double startTime, boolean timed)
			throws InterruptedException {
		if (isElementPresent(ExpObject)) {
			if (timed) {
				double endTime = System.currentTimeMillis();
				double loadTime = (endTime - startTime) / 1000;
				reportPass(screenName + ": " + loadTime + "sec");
				// setDataintoExcel(screenName, loadTime, "");

			}
			reportPass("Locator " + ExpObject + " found on the screen: " + screenName);
			return true;
		}

		else {
			String errCode = "Locator not Found";
			// setDataintoExcel(screenName,0.0, errCode);
			reportFailure("Locator " + ExpObject + " not found on the screen: " + screenName);
			return false;
		}
	}

	public void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getText(String locatorKey) {
		test.log(LogStatus.INFO, "Getting text from " + locatorKey);
		return (getElement(locatorKey).getText());

	}

	public String getValue(String locatorKey) {
		test.log(LogStatus.INFO, "Getting vlue from " + locatorKey);
		return (getElement(locatorKey).getAttribute("value"));

	}

	public void openBrowser(String bType) {
		if (bType.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + frameprops.getFirefoxdriver());
			DesiredCapabilities desiredcapabilities = new DesiredCapabilities();
			desiredcapabilities.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(desiredcapabilities);

		} else if (bType.equals("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			System.out.println(frameprops.getChromedriver());
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + frameprops.getChromedriver());
			System.out.println("Driver is inset");
			driver = new ChromeDriver(options);
			System.out.println("Driver is outset");
		} /*else if (bType.equals("IE")) {
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setJavascriptEnabled(true);
			dc.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			dc.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + frameprops.getIedriver());
			driver = new InternetExplorerDriver(dc);
		}*/
		System.out.println("Driver is set");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	
	/*
	 * public void navigate(String urlkey, String ExpObject, String PageName,
	 * boolean Timeout) throws InterruptedException { reportInfo("Navigate to url: "
	 * + urlkey); long startTime = System.currentTimeMillis(); driver.get(urlkey);
	 * waitForPageToLoad(); System.out.println("Current System time is: " +
	 * startTime); trackLoadTime(ExpObject, PageName, startTime, Timeout);
	 * 
	 * }
	 */
	public void navigate(String urlkey,String ExpObject,String PageName, boolean Timeout) throws InterruptedException {
        reportInfo("Navigate to url: " +urlkey);
        long startTime=System.currentTimeMillis();
        driver.get(urlkey);
        waitForPageToLoad();
        System.out.println("Current System time is: " +startTime);
        trackLoadTime(ExpObject, PageName, startTime, Timeout);
        String PageNameActual=driver.getTitle();
        System.out.println("Open Page URL is: " +PageNameActual);       
        if(PageNameActual.contains(PageName))
        {
            System.out.println("Expected and Actual Page title matches");
            if(isElementPresent(ExpObject)) {
                reportPass("URL open Successfully");
            }
        }
       
        else
        {
            reportFailure("URL not loaded");
        }
    }
	public void closeDriver() {
		driver.close();
		test.log(LogStatus.INFO, "Browser closed successfully");
		rep.flush();
	}

	public void takeScreenShot() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String destination = System.getProperty("user.dir") + "\\screenshots\\" + screenshotFile;
			System.out.println("The value of destination file is :" + destination);
			FileUtils.copyFile(scrFile, new File(destination));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void scrollAndTakeScreenShot() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long bodyHeight = (long) js.executeScript("return document.body.scrollHeight;");
		Dimension initial_size = driver.manage().window().getSize();
		long winHeight = (long) initial_size.getHeight();

		if (bodyHeight <= winHeight)
			takeScreenShot();
		else {
			int currentScroll = 0;
			js.executeScript("window.scrollTo(0,0)");

			do {
				takeScreenShot();
				currentScroll += winHeight;
				js.executeScript("window.scrollTo(0," + currentScroll + ")");
				wait(1);
			} while (currentScroll < bodyHeight);
		}

	}

	public void setDataintoExcel(String screenname, Double loadTime, String errCode) {

		Date date = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		df.setTimeZone(TimeZone.getDefault());
		String strDate = df.format(date);
		xlsWriter.updateExcel(SimpleClassName, "TestCase", screenname, "Date of Run", strDate);
		xlsWriter.updateExcel(SimpleClassName, "TestCase", screenname, "Response Time", Double.toString(loadTime));
		int row = xlsWriter.getCellRowNum(SimpleClassName, "TestCase", screenname);
		String threshold = xlsWriter.getCellData(SimpleClassName, "TestCase", row);
		if (loadTime <= Double.parseDouble(threshold) && loadTime != 0)
			xlsWriter.updateExcel(SimpleClassName, "TestCase", screenname, "Status", "Pass");
		else {
			xlsWriter.updateExcel(SimpleClassName, "TestCase", screenname, "Status", "Fail");
			if (errCode.equals(""))
				errCode = "Test Case threasold vreached";
		}
		xlsWriter.updateExcel(SimpleClassName, "TestCase", screenname, "Error Code", errCode);

	}

	public String copyTemplateOnNewMonth() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("_yyyy_MM");
		String strYearMonth = df.format(date);
		String[] fileNameParts = frameprops.getOptexcel().split("\\.");
		String currentFilePath = System.getProperty("user.dir") + "\\Results\\" + fileNameParts[0] + strYearMonth + "."
				+ fileNameParts[1];
		File file = new File(currentFilePath);
		if (!file.exists()) {
			String templatePath = System.getProperty("user.dir") + "\\Results\\" + fileNameParts[0] + "."
					+ fileNameParts[1];
			File tempFile = new File(templatePath);
			try {
				Files.copy(tempFile, file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return currentFilePath;
	}

	public void noCol(String locatorKey) {
		locatorKey = locatorKey.replace("XPATH_", "");
		List<WebElement> allHeadersOfTable = driver.findElements((By.xpath(locatorKey)));
		System.out.println("Headeres in Tbale below");
		System.out.println("Total Headers found: " + allHeadersOfTable.size());
		for (WebElement header : allHeadersOfTable) {
			System.out.println(header.getText());
			reportPass("Header are matched: " + header.getText());
		}

	}

	public void isSelected(String locatorKey) {
		WebElement element = getRandomElementFromList(getElements(locatorKey));
		String isChecked;
		isChecked = element.getAttribute("Checked");
		if (isChecked == "True") {
			System.out.println("Button is checked");
			reportInfo("Button is checked");
		} else {
			System.out.println("Button is unchecked");
			reportInfo("Button is unchecked");
		}

	}

	public void waitforAlert() throws InterruptedException {
		{
			int i = 0;
			while (i++ < 150) {
				try {
					Alert alert = driver.switchTo().alert();
					String alrt = alert.getText();
					System.out.print("the text it capture is:" + alrt);
					alert.accept();
					break;
				} catch (NoAlertPresentException e) {
					Thread.sleep(10000);
					System.out.println("waiting for pop up to get Displayed");
					continue;
				}
			}
		}
	}

	public void waitforelement(String locToWaitFor) throws InterruptedException {
		int count = 5000;
		for (int i = 0; i < count; i++) {
			wait(2);
			System.out.println("waiting for " + locToWaitFor + "to get displayed");
			if (isElementPresent(locToWaitFor))
				break;
			rep.flush();

		}

	}

	public void isReadOnly(String locator) {
		locator = locator.replace("XPATH_", "");
		WebElement element = driver.findElement(By.xpath(locator));
		String readOnly;
		readOnly = element.getAttribute("readOnly");
		if (readOnly == "True") {
			System.out.println("Element is editable");
			reportFailure("Element is Editable");

		} else {
			System.out.println("Element is readOnly");
			reportPass("Element is readOnly");
		}

	}

	public void ByVisibleElement(String locator) {
		locator = locator.replace("XPATH_", "");
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public void setDatatoExcel(String display_Name, String security_System, String parant_AccountName,
			String parant_EndPoint) {

		Date date = Calendar.getInstance().getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		df.setTimeZone(TimeZone.getDefault());
		String strDate = df.format(date);
		// xlsWriter.updateExcel(SimpleClassName, "Display Name", display_Name,
		// "Security System Name", security_System, "Parent Account
		// Name",parant_AccountName, "pparent End Point", parant_EndPoint );
		// xlsWriter.updateExcel(SimpleClassName, "TestCase", screenname, "Response
		// Time", Double.toString(loadTime));
		xlsWriter.updateExcel(SimpleClassName, display_Name, security_System, parant_AccountName, parant_EndPoint);
		int row = xlsWriter.getCellRowNum(SimpleClassName, "Display Name", display_Name);
		String threshold = xlsWriter.getCellData(SimpleClassName, "Display Name", row);

	}

	public void writeValueToExcel() {

		try {
			System.out.println("Entered into try block");
			PrintStream myconsole = new PrintStream(new File("C://Rudra-Work//ExcelFile//java5.xls"));
			System.setOut(myconsole);

		} catch (FileNotFoundException e) {

		}
	}

	public void switchonOff(String Locator) {
		List<WebElement> switchElement = driver.findElements(By.xpath(Locator));
		System.out.println(switchElement.size() + " : Switch Size");
		// Check its on, if its on then switch it off
		if (switchElement.size() != 0) {

			System.out.println("Switch  is On");
			reportInfo("Switch  is On");

		} else
			System.out.println("Switch  is already off");
		reportInfo("Switch  is Off");
	}
	/* Author: jroy */

	public void setListBox(String locatorkey) {
		System.out.println("Trying to click on List Box");
		try {
			Actions action = new Actions(driver);
			action.moveToElement(getElement(locatorkey)).click().build().perform();
			System.out.println("clicked on dropdown");
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test -" + ex.getMessage());
		}

	}

	public void noRow(String locatorKey) {
		locatorKey = locatorKey.replace("XPATH_", "");
		List<WebElement> allRowsOfTable = driver.findElements((By.xpath(locatorKey)));
		System.out.println("Rows in Table below");
		System.out.println("Total Rows found: " + allRowsOfTable.size());

		if (allRowsOfTable.size() > 0) {
			System.out.println("Row created Successfully");
			for (WebElement row : allRowsOfTable) {
				System.out.println(row.getText());
				reportPass("Rows are: " + row.getText());
			}
		} else {
			System.out.println("Row Creation Unsuccessful");
		}
	}

	public void waitfordownload(String dfile) {
		File f = new File(dfile);

		boolean exists = false;
		long wait = 2 * 1000 * 60 * 60;
		long counter = wait;

		System.out.println(wait);
		while (exists == false && counter >= 0) {
			exists = f.exists();
			counter--;
		}

		System.out.println("Waited for " + (wait - counter) + " milliseconds... file exists = " + f.exists());
		reportPass("File download successfull");
	}

	/************ Send Email ****************/

	/*
	 * public void EmailTestResult(String subject, String message, String mailTo,
	 * String extentFile, String TestResultFileName) throws MessagingException {
	 * final int CHARACTERS_FOR_FILENAME=24; Properties propeprties=new
	 * Properties(); propeprties.put("mail.smtp.host", "");inetmail.simeio.com
	 * propeprties.put("mail.smtp.host",25); create a new session with an
	 * authenticator Authenticator auth=new Authenticator() { public
	 * PasswordAuthentication getPasswordAuthentication() { return new
	 * PasswordAuthentication("rbasu@simeiosolutions.com", "October@2018"); } };
	 * Session session=Session.getInstance(propeprties,auth); Create a new e-mail
	 * message Message msg=new MimeMessage(session); msg.setFrom(new
	 * InternetAddress("rbasu@simeiosolutions.com"));
	 * msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
	 * msg.setSubject(subject); Create object to add multimedia type content
	 * BodyPart messageBodyPart1=new MimeBodyPart(); Set the body of email
	 * messageBodyPart1.setText(message); Create another object to add another
	 * content MimeBodyPart messageBodyPart2=new MimeBodyPart(); MimeBodyPart
	 * messageBodyPart3=new MimeBodyPart(); Mention the file which you want to send
	 * Create data source and pass the filename DataSource source = new
	 * FileDataSource(TestResultFileName); DataSource source2 = new
	 * FileDataSource(TestResultFileName); Set the handler
	 * messageBodyPart2.setDataHandler(new DataHandler(source));
	 * messageBodyPart3.setDataHandler(new DataHandler(source2)); Set the File
	 * messageBodyPart2.setFileName(StringUtils.right(TestResultFileName,
	 * CHARACTERS_FOR_FILENAME));
	 * messageBodyPart3.setFileName(StringUtils.right(extentFile,
	 * CHARACTERS_FOR_FILENAME)); Create object of MimeMultipart class MultiPart
	 * multipart=new MultiPart(); add body part1
	 * multipart.addBodyPart(messageBodyPart2); add body part2
	 * multipart.addBodyPart(messageBodyPart1);
	 * multipart.addBodyPart(messageBodyPart3); add body part2
	 * msg.setContent(multipart); Send the email Transport.send(msg); }
	 */

	public void moveToelement(String locatorkey) {

		Actions action = new Actions(driver);

		action.moveToElement(getElement(locatorkey)).build().perform();

	}

	public void waitforElementtodisappear(String locator) throws InterruptedException {

		new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(getElement(locator)));

	}

	public void scrollByPixel(String locator) {
		locator = locator.replace("XPATH_", "");
		// WebElement element= driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public void scrollHorizontally(String locator) {
		locator = locator.replace("XPATH_", "");
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(-500,0)");
	}

	public void ScrollHorizontally(String locator) {
		locator = locator.replace("XPATH_", "");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.xpath(locator));
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	/*
	 * public void ByVisibleElement(String locator) { locator
	 * =locator.replace("XPATH_", ""); WebElement element=
	 * driver.findElement(By.xpath(locator)); JavascriptExecutor js =
	 * (JavascriptExecutor) driver; //This will scroll the page till the element is
	 * found js.executeScript("arguments[0].scrollIntoView();", element); }
	 */

}
