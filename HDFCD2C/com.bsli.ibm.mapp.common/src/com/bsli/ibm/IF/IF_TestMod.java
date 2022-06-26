package com.bsli.ibm.IF;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.bsli.ibm.utility.reports.IF.ITestReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public interface IF_TestMod 
{
	void execute(String tc_id,String sheetName,WebDriver driver,HashMap<String,String> testData,String datasheetPath, String dataSheetResultPath, String reportPath,String snapshotPath,ITestReporter testReporter)throws Throwable;
}
