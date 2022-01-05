package com.orangehrm.testBase;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.utilities.ConfigDataProvider;
import com.orangehrm.utilities.Helper;
import com.orangehrm.utilities.XLSXDataProvider;

public class TestBase {

	public static WebDriver driver = null;

	public ConfigDataProvider configData;
	public XLSXDataProvider xlsxData;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports report;
	public ExtentTest test;

	@BeforeSuite
	public void setupSuite() {
		configData = new ConfigDataProvider();
		xlsxData = new XLSXDataProvider();
	}

	@BeforeTest

	public void setupExtent() {
		File fs = new File("./Reports/orangehrm"+Helper.customDate()+".html");
		htmlReporter = new ExtentHtmlReporter(fs);

		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		
		report.setSystemInfo("HostName", "LocalHost");
		
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Tester Name", "Sneha");
		report.setSystemInfo("Browser", "Chrome");

	}

	@AfterTest
	public void endReport() {

		report.flush();
	}

	@Parameters({"Browser"})
	@BeforeMethod
	public void setUp(@Optional("Chrome") String browserName) {
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "./Driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		// driver.get("https://opensource-demo.orangehrmlive.com/");

		// driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
		driver.get(configData.getAppUrl());

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, "Test Case failed" + result.getName());
			test.log(Status.FAIL, "Test Case failed" + result.getThrowable());

			String capturedScreen = Helper.captureScreenshot(result.getName(), driver);
			//test.addScreenCaptureFromPath(capturedScreen);
			// test.fail("test case failed",
			// MediaEntityBuilder.createScreenCaptureFromPath(captureScreen).build());
			
		
			System.out.println("cs"+capturedScreen);
			test.fail("Test Case Failed",MediaEntityBuilder.createScreenCaptureFromPath(capturedScreen).build());

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(Status.PASS, "Test Case Passed" + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {

			test.log(Status.SKIP, "Test Case Skipped" + result.getName());
		}

		driver.quit();
	}

}
