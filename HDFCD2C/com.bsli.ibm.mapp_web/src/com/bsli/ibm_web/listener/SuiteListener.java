package com.bsli.ibm_web.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.bsli.ibm.utility.reports.Reporter;
import com.bsli.ibm_web.utility.IConstant;


public class SuiteListener implements ISuiteListener 
{
	
	@Override
	public void onStart(ISuite arg0) 
	{
		System.out.println("SuiteListener.onStart");
		// Start the report here
        String filename=(new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")).format(new Date());
    	filename="MAPP_Report_"+filename+".html";
		
    	String  reportPath=IConstant.configMap.get("MAPP.resultPath") + "/" + IConstant.configMap.get("jobid") + "/" + "reports/";
    	
		Reporter.createReporter(reportPath + filename, true);
	}
	
	@Override
	public void onFinish(ISuite arg0) 
	{
		System.out.println("SuiteListener.onFinish");
	//	Reporter.closeReport();
	}



}
