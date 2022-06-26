package com.bsli.ibm.IF;

import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public interface IF_MappController 
{
		
	@BeforeSuite
	void initAppiumDriver() throws Exception;
	@BeforeTest
	void initModClass(String tc_id,String sheetName) throws Exception;
	@Test
	void loadModClass(String tc_id,String sheetName,String modclassName) throws Exception,Throwable;
	@AfterTest
	void closeApp() throws Exception;
	@AfterSuite
	void quitApp()  throws Exception;
	
}
