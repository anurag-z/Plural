package com.bsli.ibm.IF;

import java.util.HashMap;

public interface IF_TestModHelper 
{
	
	public void processTestCase();
	void init();
	void connectToLocatorDB();
	void fetchLocators(HashMap<String, String> testdata);
	HashMap<String,String> getExcelDataByTCID(String tcid);
	HashMap<String,String> executeUITestAutomation(HashMap<String,String> testdata,HashMap<String,String> configData);
}
