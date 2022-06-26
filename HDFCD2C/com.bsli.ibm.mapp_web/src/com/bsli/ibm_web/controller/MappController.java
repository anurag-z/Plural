package com.bsli.ibm_web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bsli.ibm.IF.IF_MappController;
import com.bsli.ibm.IF.IF_TestMod;
import com.bsli.ibm.utility.DriverSetupMapp;
import com.bsli.ibm.utility.ExcelUtil;
import com.bsli.ibm.utility.reports.Reporter;
import com.bsli.ibm.utility.reports.IF.ITestReporter;
import com.bsli.ibm_web.utility.IConstant;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import atu.testrecorder.ATUTestRecorder;

public class MappController implements IF_MappController 
{

	private WebDriver driver;
	
	private DriverSetupMapp driversetup;
	
	private HashMap<String,String> testData;
	private String datasheetPath;
	private String dataSheetResultPath;
	private String reportPath;
	private String snapshotPath;
	private String jobPath;
	public static ITestReporter testReporter;
	public String Url=null;
	private String chromedriverpath;
	private ATUTestRecorder recorder;
	
	/*@Parameters({"jobid"})
	@BeforeSuite(alwaysRun=true)
	public void init(String jobid)  throws Exception
	{
		//System.out.println("@BeforeSuite1");
		
		
		//Create Directories for Job_id
		
		datasheetPath=IConstant.configMap.get("MAPP.datasheetPath");
		jobPath=IConstant.configMap.get("MAPP.resultPath") + "/" + jobid;
		dataSheetResultPath=jobPath + "/" + jobid + ".xlsx";
		reportPath=jobPath + "/" + "reports";
		snapshotPath=reportPath + "/" + "snapshot";		
		
		(new File(jobPath)).mkdirs();
		(new File(reportPath)).mkdirs();
		(new File(snapshotPath)).mkdirs();
		
		Files.copy(Paths.get(datasheetPath), new FileOutputStream(dataSheetResultPath));
		
		
		
	}*/
	
	
	
	public void initAppiumDriver()  throws Exception
	{
		System.out.println("@BeforeSuite2");
		
		  DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		  Date date = new Date();
		  
		  System.out.println(Url);
		
		  chromedriverpath=IConstant.configMap.get("MAPP.chromedriverPath");
		
		  System.setProperty("webdriver.chrome.driver", chromedriverpath);
		  
		 try {
			 driver=new ChromeDriver();

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		driver.navigate().to(Url);
			
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		driver.manage().window().maximize();
		
		Thread.sleep(5000);

	}
	
	
	@Parameters({"tc_id","sheetName","appURL"})
	@BeforeTest
	public void initModClass(String tc_id,String sheetName,String appURL) throws Exception
	{
		System.out.println("@BeforeTest");	
		this.Url=appURL;
		System.out.println(Url);
		datasheetPath=IConstant.configMap.get("MAPP.datasheetPath");
		ExcelUtil excel=new ExcelUtil(IConstant.configMap.get("MAPP.datasheetPath"));
		testData=new HashMap<String, String>();
		testData=excel.getExcelDataByKey(tc_id, sheetName);
		System.out.println(testData);
		//Initialize reporter
  		testReporter=Reporter.getTestReporter();
  		initAppiumDriver();
	}
	
	
	@Parameters({"tc_id","sheetName","modclassName"})
	@Test
	public void loadModClass(String tc_id,String sheetName,String modclassName)  throws Throwable
	{
		System.out.println("@Test");		
		Class cls = Class.forName(modclassName);			
		IF_TestMod obj =(IF_TestMod) cls.newInstance();
		snapshotPath=IConstant.configMap.get("MAPP.resultPath") + "/" + IConstant.configMap.get("jobid") + "/" + "reports"+ "/" + "snapshot"+"/";
		dataSheetResultPath=IConstant.configMap.get("MAPP.resultPath") + "/" + IConstant.configMap.get("jobid") + "/" + IConstant.configMap.get("jobid") +".xlsx";
		obj.execute(tc_id, sheetName, driver, testData,datasheetPath, dataSheetResultPath, reportPath,snapshotPath,testReporter);
	
	}
	

	@AfterTest
	public void closeApp() throws Exception 
	{
		System.out.println("@AfterTest");			
//		driversetup.closeApp(driver);
		driver.close();
		  //To stop video recording.
//		  recorder.stop();;
	}
	


	@AfterSuite
	public void quitApp()  throws Exception
	{
		System.out.println("@AfterSuite");			
//		driversetup.quitApp(driver);
		driver.quit();
		testReporter.endTest();
		Reporter.flushReport();
	}


	@Override
	public void initModClass(String tc_id, String sheetName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
