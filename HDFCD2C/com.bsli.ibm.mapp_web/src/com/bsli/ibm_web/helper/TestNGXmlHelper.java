package com.bsli.ibm_web.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.xml.XmlSuite;

import com.bsli.ibm.utility.CreateTestNGXml;
import com.bsli.ibm.utility.ExcelUtil;
import com.bsli.ibm_web.utility.IConstant;

import io.appium.java_client.remote.MobileCapabilityType;

public class TestNGXmlHelper 
{

	private String jobid;
	private String testCaseFlag="Yes";
	
	private TestNGXmlHelper()
	{
		
	}
	
	public TestNGXmlHelper(String jobid)
	{
		this.jobid=jobid;
	}
	
	public TestNGXmlHelper(String jobid,String testCaseFlag)
	{
		this.jobid=jobid;
		this.testCaseFlag=testCaseFlag;
	}
	
	public void createTestNGXml() throws Exception
	{

		//Create TestNG Path
		String createTestNGDirPath=IConstant.configMap.get("MAPP.resultPath") + "/" + jobid;
		(new File(createTestNGDirPath)).mkdirs();
		String createTestNGFilePath="";	
		
		
		//Read Control Sheet
		ExcelUtil excel=new ExcelUtil(IConstant.configMap.get("MAPP.datasheetPath"));
		ArrayList<HashMap<String, String>> excelData=new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> excelTestData=new ArrayList<HashMap<String, String>>();
		excelData=excel.getExcelDataBySheet(IConstant.configMap.get("MAPP.controlSheetName"));
		//excel sheet Channel is filtered
		
		//initiate test NG XML
		CreateTestNGXml testNGXML=new CreateTestNGXml();
		HashMap<String,String> suiteParams=new HashMap<String, String>();
		suiteParams.put("jobid", jobid);
		
		
		//suites
		List<String> suiteList= new ArrayList<String>();
		
		//for main sheet(channel) iteration
		for(HashMap<String, String> controlSheetList:excelData)
		{
			if(null!=controlSheetList.get("Execute") && controlSheetList.get("Execute").equalsIgnoreCase("Yes"))
			{
				String sheetName=controlSheetList.get("TC_Name");
				
				//Fetch Sheetdata
				excelTestData=excel.getExcelDataBySheet(sheetName);
				
				//Create TestNG File path
				createTestNGFilePath= createTestNGDirPath + "/" + sheetName + ".xml";
				
				//Create TestSuite
				XmlSuite suite=testNGXML.initSuite(sheetName, IConstant.configMap.get("suiteListener"), IConstant.configMap.get("suiteListenerClass"),suiteParams);
				
				System.out.println("Suite Name: " +  sheetName);
				
				for(HashMap<String, String> testData:excelTestData)
				{
					if(testData.get("flag").equalsIgnoreCase(testCaseFlag))
					{
						String mod_class_name=testData.get("mod_class_name");
						String test_name="Test_" + testData.get("tc_id");
						String testClassName=IConstant.configMap.get("controllerClass");
						
						HashMap<String,String> xmlParams=new HashMap<String, String>();
						xmlParams.put("tc_id", testData.get("tc_id"));
						xmlParams.put("sheetName", sheetName);
						xmlParams.put("modclassName", mod_class_name);
						xmlParams.put("appURL", testData.get("URL"));
						testNGXML.addTestClass(suite, test_name, testClassName, xmlParams);
						

						
					}
				}
				
				//Add suite to SuitesList
				testNGXML.createTestSuiteXML(suite,suiteList,createTestNGFilePath);
				
			}
			

			
			
		}
		
		//Run TestNG suites
		testNGXML.runTestNG(suiteList);

	}
	
}
