package com.bsli.ibm_web.mod;

import java.awt.AWTException;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.ErrorCodes;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bsli.ibm.IF.IF_TestMod;
import com.bsli.ibm.utility.CustomException;
import com.bsli.ibm.utility.MappAutomationWrapper;
import com.bsli.ibm.utility.ReadExcelByMapping;
import com.bsli.ibm.utility.WrapperMethods;
import com.bsli.ibm.utility.reports.IF.ITestReporter;
import com.bsli.ibm_web.controller.MappController;
import com.bsli.ibm_web.utility.IConstant;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;


public class eappMod extends MappController implements IF_TestMod
{
	

//    int startX = 0;
//    int endX = 0;
//    int startY = 0;
//    int endY = 0;
    public String eapp_sql,sheetName;
    private HashMap<String, String> dataMap;
	private HashMap dataSheetMap;
	public WebDriver driver;
	public String datasheetPath,dataSheetResultPath;
	public String tc_id;
	public MappAutomationWrapper mapp;
	 public String medical_sql;
	 public WrapperMethods method;

	
	
	
	@Test
	public void execute(String tc_id,String sheetName,WebDriver driver,HashMap<String,String> testData,String datasheetPath, String dataSheetResultPath, String reportPath,String snapshotPath, ITestReporter testReporter) throws Throwable
	{
		
		try
		{
			
			
			
			
			this.driver=driver;
			this.tc_id=tc_id;
			this.sheetName=sheetName;
			method=new WrapperMethods();
			
			
			mapp=new MappAutomationWrapper(tc_id, sheetName, driver, IConstant.configMap, testData, datasheetPath, dataSheetResultPath, reportPath, snapshotPath,testReporter);
			
			this.datasheetPath=datasheetPath;
			this.dataSheetResultPath=dataSheetResultPath;
			this.dataSheetMap=testData;
			eapp_sql=IConstant.configMap.get("Mapp.eappMod.sql");
			eapp_sql=eapp_sql.replaceAll("CODE_01", sheetName);
			
			String Scenario3=testData.get("ChannelName").toString();
			eapp_sql=eapp_sql.replaceAll("CODE_02", Scenario3);
			
			testReporter.startTest(tc_id + "_" + sheetName + "_" + Scenario3);
			
			dataMap = new HashMap<String, String>();
			
			mapp.execute(eapp_sql, dataMap, this);
			
			ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake","Success");
			//Thread.sleep(6000);
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void doCheckPOI(String... params) throws Throwable 
	{
		try
		{
			System.out.println(params[0]);
			String POI=dataSheetMap.get(params[0]).toString();
			
			dataMap.put("POI1", "NA");
			dataMap.put("POI2", "NA");
			dataMap.put("POI3","NA" );
			dataMap.put("POI4","NA" );
			System.out.println(">>PP>>" + dataMap);
			
			int i=1;
			for(String paramValue:POI.split(","))
			{
				i++;
				dataMap.put("POI" + i, paramValue);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(">>Datamap>>" + dataMap);
	}	

	public void getAPPNO() {
		try {
			System.out.println(dataMap.get("APP_NO"));
			String App_No = dataMap.get("APP_NO").replaceAll("Primary App Number : ", "");
			//logger.info("App_No " + App_No);
			dataMap.put("APP_NO", App_No);
			System.out.println(App_No);
			//testReporter.Log_Pass("Application No is " +App_No, App_No+" fetched");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alertAction(String...params)
	{
		boolean isAlert=false;
	try
		{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			Thread.sleep(4000);
			
			if(isElementPresent(driver, By.xpath("//button[contains(.,'Close')]")))
			{	
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(.,'Close')]")));
			
			
			
			isAlert = true;
			}
			
			        
		
	}catch(Exception e)
	{
		e.printStackTrace();
		
		isAlert=false;
	}
	}
	public void alertAction1(String...params)
	{
		boolean isAlert=false;
	try
		{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		
			
			if(isElementPresent(driver, By.xpath("//button[contains(.,'Close')]")))
			{	
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(.,'Close')]")));
			
			
			
			isAlert = true;
			
			
			 WebDriverWait wait=new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Send')]")));
	
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(.,'Send')]")));
			
			isAlert = true;
			}
			
			        
		}
	catch(Exception e)
	{
		e.printStackTrace();
		
		isAlert=false;
	}
	}
	public void doMedicalCheckboxes(String... params) throws Throwable 
	{
		try
		{
			System.out.println(params[0]);
			String med=dataSheetMap.get(params[0]).toString();
			
			dataMap.put("check1", "NA");
			dataMap.put("check2", "NA");
			dataMap.put("check3","NA" );
			dataMap.put("check4","NA" );
			dataMap.put("check5","NA" );
			dataMap.put("check6","NA" );
			dataMap.put("check7","NA" );
			System.out.println(">>medical checks>>" + dataMap);
			
			int i=1;
			if(!med.equalsIgnoreCase("NA"))
			{
			for(String paramValue:med.split(","))
			{
				
				dataMap.put("check" + i, paramValue);
				i++;
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(">>PP>>" + dataMap);
	}	
	public void updateMAPP()
	{
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(isElementPresent(driver, By.xpath("//android.widget.Button[@text='Update']")))
		{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
//			driver.findElementByXPath("//android.widget.Button[@text='Update']").click();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

public Boolean isElementPresent(WebDriver driver, By by) {  
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  
	    try {  
	        driver.findElement(by);  
	        return true;  
	    } catch (NoSuchElementException e) {  
	        return false;  
	    } finally {  
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	    }  
	}


public void doMedicalDataset(String... params) throws Exception
{
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	String DataBlock=dataSheetMap.get(params[0]).toString();
	String SetData=dataSheetMap.get(params[1]).toString();
	
	String[] splitDataSet = DataBlock.split(",");
	try {
		for (int j = 0; j < splitDataSet.length; j++) {

			String[] splits = splitDataSet[j].split(":");

			switch (splits[0].toString().trim()) {

			case "CurrentSymptoms":
				String dataSet = splits[1].toString();
				System.out.println(dataSet);

				String g = "//input[@id='" + SetData
						+ "']/following::div/div[2]/div/div/div/input[@id='exectDiag']";

				WebElement elem = driver.findElement(By.xpath(g));
				JavascriptExecutor exec = (JavascriptExecutor) driver;
				exec.executeScript("arguments[0].scrollIntoView();", elem);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-200)", "");

				WebElement inputText = driver.findElement(By.xpath(g));
				inputText.clear();
				inputText.sendKeys(dataSet);
				break;
				
			case "DoctorsDetail":
				String dataSet1 = splits[1].toString();
				System.out.println(dataSet1);
				String g1 = "//input[@id='" + SetData
						+ "']/following::div/div[2]/div/div/div/input[@id='dtlTreatDoct']";
				WebElement inputText2 = driver.findElement(By.xpath(g1));
				inputText2.clear();
				inputText2.sendKeys(dataSet1);
				break;
			case "DiagnosisDate":
				String dataSet2 = splits[1].toString();
				System.out.println(dataSet2);
				String g2 = "//input[@id='" + SetData
						+ "']/following::div/div[2]/div/div/div/input[@class='dateDiag']";
				WebElement inputText3 = driver.findElement(By.xpath(g2));
				inputText3.clear();
				inputText3.sendKeys(dataSet2);
				break;
			case "ConsultationLastDate":
				String dataSet3 = splits[1].toString();
				System.out.println(dataSet3);
				String g3 = "//input[@id='" + SetData
						+ "']/following::div/div[2]/div/div/div/input[@class='dateLastDiag']";
				WebElement inputText4 = driver.findElement(By.xpath(g3));
				inputText4.clear();
				inputText4.sendKeys(dataSet3);
				break;
			case "DiagnosisDetail":
				String dataSet4 = splits[1].toString();
				System.out.println(dataSet4);

				String g4 = "//input[@id='" + SetData
						+ "']/following::div/div[2]/div/div/div/input[@id='detailDiag']";
				WebElement inputText5 = driver.findElement(By.xpath(g4));
				inputText5.clear();
				inputText5.sendKeys(dataSet4);
				break;

			}

		}
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println(e);
	}
	
	
}
	
	
	//Custom Method configured in Locator Sheet
	public void doMedcialForm(String... params) throws Throwable,Exception
	{
		String[] strMedicalOptions=dataSheetMap.get(params[0]).toString().split(",");
		
		for(String strMedicalOptionVal:strMedicalOptions)
		{
			
			medical_sql=IConstant.configMap.get("Mapp.Medical.sql");
			medical_sql=medical_sql.replaceAll("CODE_01", strMedicalOptionVal);
			mapp.execute(medical_sql, dataMap, this);
			
		}
		
	}
	
	
	//Document upload section
	
	
//public void doDocsUpload(int i) throws CustomException,AWTException,InterruptedException
//	
//	{
//		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//
//		
//		String docPath=dataSheetMap.get("Path").toString();
//		try{
//		
//			Set<String> contextNames = driver.getContextHandles();
//		  	for (String contextName : contextNames) {
//
//		  		      System.out.println(contextName);
//		  		      if (contextName.contains("NATIVE_APP")){
//		  		          driver.context(contextName);
//		  		          break;
//		  		        }
//		  		  }
//		
//
//		  	
//
//		  	if(i==1)
//		  	{ 	
//		  		Thread.sleep(3000);
//		  		
//
//		  		try{
//		  		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Show roots']")).click();
//		  		}catch (Exception e) {
//					// TODO: handle exception
//				}
//
//		  		
//		  		Thread.sleep(2000);
//		  		List<WebElement>lst=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='android:id/title']"));
//		  		for(WebElement a:lst)
//		  		{
//		  			if(a.getText().contains("Recent"))
//		  			{
//		  				
//		  				a.click();
//		  				break;
//		  			}
//		  		}
//		  		
//		  		
//		  		
//		  	}
//		  	Thread.sleep(1000);
//		  	
//			WebDriverWait wait = new WebDriverWait(driver, 180);
//	  		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//android.widget.TextView[@resource-id='android:id/title']")));
//			
//			
//		  	List<WebElement>lst=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='android:id/title']"));
//	  		for(int k=0;k<=lst.size()-1;k++)
//	  		{
//	  			System.out.println("Element to be uploaded :" +lst.get(k).getText());
//	  			if(lst.get(k).getText().contains(docPath))
//	  			{
//	  				//lst.get(k).click();
////	  				TouchAction action=new TouchAction(driver);
////					action.tap(new TapOptions().withElement(new ElementOption().withElement(lst.get(k)))).perform();
//	  				break;
//	  			}
//	  		}
//	  		
//	  		
//	  		
//	  	
//		//driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title']")).click();
//			
//
//		  	for (String contextName : contextNames) {
//
//		  		      System.out.println(contextName);
//		  		      if (contextName.contains("WEBVIEW_com.bsli.eapp")){
//		  		          driver.context(contextName);
//		  		          break;
//		  		        }
//		  		  }
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new CustomException("Docs_upload browse element not visible", e, ErrorCodes.TIMEOUT);
//		}
//		
//		
//	}
//	
//public void doDocsUpload(String... params) throws CustomException, InterruptedException
//
//{
//	
//	String docPath=dataSheetMap.get(params[0]).toString();
//	try{
//		//driver.pushFile(docPath,new File(IConstant.configMap.get("DocumentUploadpath")));
//
//		Set<String> contextNames = driver.getContextHandles();
//		for (String contextName : contextNames) {
//
//			System.out.println(contextName);
//			if (contextName.contains("NATIVE_APP")){
//				driver.context(contextName);
//				break;
//			}
//		}
//		
//		Thread.sleep(1000);
//  		List<WebElement>lst1=driver.findElements(By.xpath("//android.widget.TextView[@resource-id='android:id/title']"));
//  		for(WebElement a:lst1)
//  		{
//  			if(a.getText().contains(docPath))
//  			{
//  				a.click();
//  				break;
//  			}
//  		}
//  		
//
//		for (String contextName : contextNames) {
//
//			System.out.println(contextName);
//			if (contextName.contains("WEBVIEW_com.bsli.eapp")){
//				driver.context(contextName);
//				break;
//			}
//		}
//	}catch(Exception e){
//		e.printStackTrace();
//		throw new CustomException("Docs_upload browse element not visible", e, ErrorCodes.TIMEOUT);
//	}
//
//
//}
//
//public void doMedicalDocsUpload() throws Exception
//	
//	{
//	try{
//		
//	
//
//		
//		
//		
//	Thread.sleep(3000);
//	
//	int i =1;
//
//		
//
//	List<WebElement> lst = driver.findElements(By.xpath("//a[@title='Browse']"));
//
//	for (WebElement ele : lst) {
//		
//		System.out.println(ele);
//		
//		
//		if(i>=3){
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("window.scrollBy(0,150)", "");
//			
//		}
//		
//
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", ele);
//	
//		Thread.sleep(2000);
//		doDocsUpload(i);
//		Thread.sleep(2000);
//					
//		i++;
//		
//		
//		
//	}
//	WebElement e1 = driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']"));
//
//	JavascriptExecutor js=(JavascriptExecutor)driver;
//	js.executeScript("arguments[0].click();", e1);
//	
//	System.out.println(driver.findElement(By.xpath("//div[@id='loadingText']")).getText());
//	
//	WebDriverWait wait = new WebDriverWait(driver, 180);
//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='parentSpinner']/div")));
//	
//	do
//	{
//		Thread.sleep(1000);
//		System.out.println(driver.findElement(By.xpath("//div[@id='loadingText']")).getText());
//	}
//	while(driver.findElement(By.xpath("//div[@id='loadingText']")).getText()==null);
//	
//		
//	
//	
//	
//	
//	alertAction_action("Alert");
//
//
//				
//	
//	
//	}catch(Exception e)
//	{
//		
//		e.printStackTrace();
//		throw new CustomException("Docs_upload browse element not visible", e, ErrorCodes.TIMEOUT);
//	}
//	
//	
//	}
//
//
//public void doDocSubmit(String... params) throws Exception
//{
//	try{
//		
//		
//
//
//		String docType=dataSheetMap.get(params[0]).toString();
//		
//		if(!docType.equalsIgnoreCase("NA"))
//		{
//		
//
//		WebElement selectDropBox = driver.findElement(By.xpath("//select[@id='documentType']"));
//		Select select = new Select(selectDropBox);
//		select.selectByVisibleText(docType);
//		}
//		
//	
//	
//			
//		WebElement e = driver.findElement(By.xpath("//div[@class='btnMarun bnuDevice']/a[@title='Browse & Upload']"));
//
//		
//
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", e);
//			
//				
//			
//			Thread.sleep(2000);
//
//			doDocsUpload(2);
//			Thread.sleep(2000);
//			
//			WebElement e1 = driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']"));
//		
//			//JavascriptExecutor js=(JavascriptExecutor)driver;
//			js.executeScript("arguments[0].click();", e1);
//			
//			WebDriverWait wait = new WebDriverWait(driver, 180);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='parentSpinner']/div")));
//
//			
//			do
//			{
//				Thread.sleep(1000);
//			}
//			while(driver.findElement(By.xpath("//div[@id='loadingText']")).getText()==null);
//			alertAction_action("Alert");
//			
//		
//		
//		}catch(Exception e)
//		{
//			throw new CustomException("Docs_upload browse element not visible", e, ErrorCodes.TIMEOUT);
//		}
//	
//}
//
//public void doDocSubmit() throws Exception
//{
//	try{
//		
//		
//
//		
//			
//		WebElement e = driver.findElement(By.xpath("//a[@title='Browse']"));
//
//			
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", e);
//		
//		Thread.sleep(1000);
//
//			doDocsUpload(2);
//			Thread.sleep(2000);
//			
//			WebElement e1 = driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']"));
//			
//			
//			js.executeScript("arguments[0].click();", e1);
//			System.out.println("Pan card"+driver.findElement(By.xpath("//div[@id='loadingText']")).getText());
//			
//			WebDriverWait wait = new WebDriverWait(driver, 180);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='parentSpinner']/div")));
//			
//			do
//			{
//				Thread.sleep(1000);
//			}
//			while(driver.findElement(By.xpath("//div[@id='loadingText']")).getText()==null);
//			 System.out.println("calling pop up method");
//			alertAction_action("Alert");
//
//			
//		
//		}catch(Exception e)
//		{
//			throw new CustomException("Docs_upload browse element not visible", e, ErrorCodes.TIMEOUT);
//		}
//	
//}


//Negative Validations Custom method
//
//@SuppressWarnings("finally")
//public boolean negativeValidations(String ...params) throws Exception
//{
//	
//	
//	String TypeOfValidation=params[0].toString().trim();
//	
//	String UI_FieldName=params[1].toString().trim();
//	String errormsg = null;
//	boolean negative_Flag=false;
//	
//	
//	try
//	
//	{
//		switch(TypeOfValidation)
//	
//	{
//
//	case "TextFieldLength" :
//	
//		String locator_key=params[2].toString().trim();
//		int stringlength=0;
//		String TextField= driver.findElement(By.xpath(locator_key)).getAttribute("value");
//		try{
//				 stringlength=Integer.parseInt(driver.findElement(By.xpath(locator_key)).getAttribute("maxlength")) ;
//		}catch (Exception e) {
//			stringlength=Integer.parseInt(params[3].toString().trim());
//		}
//	finally
//	{boolean length= TextField.length()<=stringlength;
//	try{
//	Assert.assertTrue(length, "Length of the" + UI_FieldName + "is as per standard and the value is " +TextField.length());
//	break;
//	}
//	catch (Exception e) {
//		ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+"invalid length");
//		throw new Exception();
//	}
//	}
//		
//	
//	case "TextPattern" :
//		String datacheck = dataSheetMap.get(UI_FieldName).toString().trim(); 
//		if (datacheck == null || datacheck.trim().isEmpty()) {
//	         System.out.println("Incorrect format of string");
//	         
//	     }
//	     Pattern p = Pattern.compile("[A-Za-z]");
//	     Matcher m = p.matcher(datacheck);
//	   
//	     boolean b = m.find();
//	     try{
//	    	 Assert.assertTrue(b);
//	     }
//	     catch (AssertionError e) {
//				WebDriverWait w=new WebDriverWait(driver, 20);
//				
//				driver.context("NATIVE_APP");
//		
//				
//				 method.mobilescroll(driver,"Down");
//				 
//				String locator_key2=params[2].toString().trim();
//				w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator_key2))));
//				driver.findElement(By.xpath(locator_key2)).click();
//			
//				errormsg=driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/message']").getText();
//				 String expectederrormsg =params[3];
//				 Assert.assertEquals(errormsg, expectederrormsg);
//				 
//				 ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+errormsg);
//				 throw new Exception(errormsg);
//				 
//				 
//			}
//	    
//	     break;
//	     
//	     
//	case "NumberPattern" :
//		String datacheck_No = dataSheetMap.get(UI_FieldName).toString().trim(); 
//		if (datacheck_No == null || datacheck_No.trim().isEmpty()) {
//	         System.out.println("Incorrect format of string");
//	         
//	     }
//	    // Pattern p1 = Pattern.compile("[^0-9]");
//		 Pattern p1 = Pattern.compile("\\d+");
//	     Matcher m1 = p1.matcher(datacheck_No);
//	   
//	     boolean b1 = m1.matches();
//	     try{
//	      Assert.assertTrue(b1);
//	    
//	     }
//	     catch (AssertionError e) {
//				WebDriverWait w=new WebDriverWait(driver, 20);
//				
//				driver.context("NATIVE_APP");
//		
//				
//				// method.mobilescrolldown(driver);
//				 
//				String locator_key2=params[2].toString().trim();
//				w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator_key2))));
//				driver.findElement(By.xpath(locator_key2)).click();
//			
//				errormsg=driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/message']").getText();
//				 String expectederrormsg =params[3];
//				 Assert.assertEquals(errormsg, expectederrormsg);
//				 
//				 ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+errormsg);
//				 throw new Exception(errormsg);
//				 
//				 
//			}
//	     break;
//	     
//	     
//	case "PanRegularExpression" :
//		
//		try
//		{
//			String s = dataSheetMap.get(UI_FieldName).toString().trim(); 
//		
//		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
//
//		Matcher matcher = pattern.matcher(s);
//		// Check if pattern matches 
//		Boolean b2= matcher.matches();		
//				
//		 Assert.assertTrue(b2);
//		}catch (AssertionError e) {
//			WebDriverWait w=new WebDriverWait(driver, 20);
//			String locator_key1=params[2].toString().trim();
//			w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator_key1))));
//			
//			driver.findElement(By.xpath(locator_key1)).click();
//			driver.context("NATIVE_APP");
//			errormsg=driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/message']").getText();
//			 String expectederrormsg =params[3];
//			 Assert.assertEquals(errormsg, expectederrormsg);
//			 ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+errormsg);
//			 throw new Exception();
//		}
//		
//		
//		break;
//		
//		
//	case "IFSCRegularExpression" :
//		
//		String ifsc = dataSheetMap.get(UI_FieldName).toString().trim(); 
//		Pattern pattern1 = Pattern.compile("^[A-Za-z]{4}[a-zA-Z0-9]{7}$");
//
//		Matcher matcher1 = pattern1.matcher(ifsc);
//		// Check if pattern matches 
//		Boolean b3= matcher1.matches();		
//		try{		
//		 Assert.assertTrue(b3);
//		}
//		 catch (AssertionError e) {
//				WebDriverWait w=new WebDriverWait(driver, 20);
//				String locator_key1=params[2].toString().trim();
//				w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator_key1))));
//				driver.findElement(By.xpath(locator_key1)).click();
//				driver.context("NATIVE_APP");
//				if(isElementPresent(driver, By.id("android:id/message")))
//				
//					{
//						errormsg=driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/message']").getText();
//						String expectederrormsg =params[3];
//						Assert.assertEquals(errormsg, expectederrormsg);
//					}
//				
//				 ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+errormsg);
//				 throw new Exception();
//			}
//		
//		break;
//		
//		
//		
//	case "DateFormat" :
//		
//		String date=dataSheetMap.get(UI_FieldName).toString();
//
//		
//		try{
//			
//			String regex= "^[0-3]?[0-9]-[0-3]?[0-9]?-(?:[0-9]{2})?[0-9]{2}$";
//			String regex1="^(([0-9])|([0-2][0-9])|([3][0-1]))-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-(?:[0-9]{2})?[0-9]{2}$";
//		
//			 
//				Pattern pattern = Pattern.compile(regex );
//				Pattern newpattern = Pattern.compile( regex1);	
//				
//			 
//				boolean datematch=pattern.matcher(date).matches() || newpattern.matcher(date).matches();
//				Assert.assertTrue(datematch);
//						
//				
//			
//		}
//		catch (AssertionError e) {
//			WebDriverWait w=new WebDriverWait(driver, 20);
//			
//			driver.context("NATIVE_APP");
//	
//			
//			 method.mobilescroll(driver,"Down");
//			 
//			String locator_key2=params[2].toString().trim();
//			w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator_key2))));
//			driver.findElement(By.xpath(locator_key2)).click();
//		
//			errormsg=driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/message']").getText();
//			 String expectederrormsg =params[3];
//			 Assert.assertEquals(errormsg, expectederrormsg);
//			 ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+errormsg);
//			 
//			 throw new Exception(errormsg);
//			 
//			 
//		}
//		
//		
//		break;
//		
//		default :
//			System.out.println("The Negative case is not avaiable");
//	
//	
//	}
//		
//	negative_Flag=true;
//	
//	
//}
//catch (Exception e)
//	
//	{
//	
//	
//	ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "flag", "FAIL");
//	if(errormsg==null || errormsg=="")
//	{
//		 ReadExcelByMapping.writeExcel1(dataSheetResultPath,sheetName,tc_id, "Testcase_Remake", UI_FieldName+" "+TypeOfValidation+" is not valid");
//	}
//
//	 //System.out.println(errormsg);
//		negative_Flag=false;
//		
//		//throw new Exception();
//		
//		
//}
//	return negative_Flag;
//	
//		
//		
//	
//}


	public void underWriting(String...params)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if(isElementPresent(driver, By.xpath("//select[@id='uwPremClass']")))
		{	
			Select sel=new Select(driver.findElement(By.xpath("//select[@id='uwPremClass']")));
			sel.selectByVisibleText((String) dataSheetMap.get(params[0]));
			
		
		}

	}

//	public void alertAction_action(String...params)
//	{
//		boolean isAlert=false;
//	try
//		{
//			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			
//			
//			  Set<String> contextNames = driver.getContextHandles();
//			  	for (String contextName : contextNames) {
//
//			  		      System.out.println(contextName);
//			  		      if (contextName.contains("NATIVE_APP")){
//			  		          driver.context(contextName);
//			  		        
//			  		          break;
//			  		        }
//			  		  }
//			
//		//	driver.context("NATIVE_APP");
//			
//			//Thread.sleep(2000);
//			  	try
//			  	{
//			  		driver.getPageSource();
//			  	}catch (Exception e) {
//					// TODO: handle exception
//				}
//			if(isElementPresent_action(driver, By.xpath("//android.widget.Button[@resource-id='android:id/button2']")))
//			{	
//			
//			//driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button2']")).click();
//				WebElement alert=driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button2']"));
////				TouchAction action=new TouchAction(driver);
////				action.tap(new TapOptions().withElement(new ElementOption().withElement(alert))).perform();
////			driver.context("WEBVIEW_com.bsli.eapp");
//			isAlert = true;
//			}
//			else
//			{
//				for (String contextName : contextNames) {
//
//		  		     
//		  		      if (contextName.contains("WEBVIEW_com.bsli.eapp")){
//		  		          driver.context(contextName);
//		  		          break;
//		  		        }
//		  		  }
//
//			}
//			        
//		
//	}catch(Exception e)
//	{
//		e.printStackTrace();
//		driver.context("WEBVIEW_com.bsli.eapp");
//		isAlert=false;
//	}
//	}

	public Boolean isElementPresent_action(WebDriver driver, By by) throws InterruptedException {  
	    driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);  
	    try {  
	    	Thread.sleep(2000);
	    	driver.findElement(by).isDisplayed();
	        System.out.println("POP_UP Present");
	        return true;  
	    } catch (NoSuchElementException e) {  
	    	 System.out.println("POP_UP Absent");
	        return false;  
	    } finally {  
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	    }  
	}

	
}
