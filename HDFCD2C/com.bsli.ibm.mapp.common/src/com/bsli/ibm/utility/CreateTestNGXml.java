package com.bsli.ibm.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class CreateTestNGXml 
{
	
	public CreateTestNGXml(HashMap<String,String> xmlparams)
	{
		
	}
	
	public CreateTestNGXml()
	{
		
	}
	
	
	
	
	public XmlSuite initSuite(String  suiteName, String suiteListener, String suiteListenerClass, HashMap<String,String> xmlParams) throws Exception
	{
		XmlSuite suite = new XmlSuite();

		suite.setName (suiteName);
		
		suite.setParameters(xmlParams);

        if(null!=suiteListener && suiteListener.equalsIgnoreCase("Y"))
        {
        	List<String> listeners=new ArrayList<String>();
        	listeners.add(suiteListenerClass);
        	suite.setListeners(listeners);
        }
        return suite;
	}
	
	
	public void addTestClass(XmlSuite suite,String testName,String testClassName,HashMap<String,String> xmlParams)
	{
	    XmlTest test = new XmlTest(suite);	
	    test.setName (testName);
		for(String paramName:xmlParams.keySet())
    {

				test.addParameter(paramName, xmlParams.get(paramName));	
    }
		
		XmlClass testClass = new XmlClass();
		testClass.setName (testClassName);
	
		    
		
		test.setXmlClasses (Arrays.asList (new XmlClass[] {testClass}));

		//suite.addTest(test);

	}
	
	
	
	//====================================================
	public void createTestSuiteXML(XmlSuite suite,List<String> suites,String createTestNGPath) throws Exception
	{
   
        File f =FileUtils.getFile(createTestNGPath);
        if(f.exists()) f.delete();
        f.createNewFile();
        FileUtils.writeByteArrayToFile(f, suite.toXml().getBytes());
        suites.add(f.getPath());

	}
	//====================================================

	
	//====================================================	
	public void runTestNG(List<String> suites) throws Exception
	{
		TestNG testNG = new TestNG();
        testNG.setTestSuites(suites);
        testNG.run();
	}
	//====================================================	
}
