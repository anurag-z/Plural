package com.bsli.ibm_web.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class IConstant 
{
	
	
	final public static ResourceBundle rsBundle = ResourceBundle.getBundle("com.bsli.ibm_web.config.app");
	
	public static HashMap<String,String> configMap=new HashMap<String, String>();
	private static String datasheetPath;
	private static String dataSheetResultPath;
	private static String reportPath;
	private static String snapshotPath;
	private static String jobPath;
	private static String chromedriverpath;


	static
	{
		try
		{
			
			//Create JobID
			SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDDhhmmss");
			String jobid=sdf.format(new Date());

			
			System.out.println("Loading config");
			configMap=convertResourceBundleToMap(rsBundle);
			configMap.put("jobid", jobid);
			System.out.println("Loading config done...");
			
			//Create Directories for Job_id
			
			datasheetPath=IConstant.configMap.get("MAPP.datasheetPath");
			jobPath=IConstant.configMap.get("MAPP.resultPath") + "/" + jobid;
			dataSheetResultPath=jobPath + "/" + jobid + ".xlsx";
			reportPath=jobPath + "/" + "reports";
			snapshotPath=reportPath + "/" + "snapshot";		
			chromedriverpath=IConstant.configMap.get("MAPP.chromedriverPath");
			
			(new File(jobPath)).mkdirs();
			(new File(reportPath)).mkdirs();
			(new File(snapshotPath)).mkdirs();
			
			Files.copy(Paths.get(datasheetPath), new FileOutputStream(dataSheetResultPath));
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	private static HashMap<String, String> propertiesToMap(Properties props) 
	{
		   HashMap<String, String> hm = new HashMap<String,String>();
		   Enumeration<Object> e = props.keys();
		   while (e.hasMoreElements()) {
		     String s = (String)e.nextElement();
		     hm.put(s, props.getProperty(s));
		   }
		   return hm;
	}
	
	
   private static HashMap<String, String> convertResourceBundleToMap(ResourceBundle resource) {
	  HashMap<String, String> hm = new HashMap<String,String>();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            hm.put(key, resource.getString(key));
        }
        return hm;
    }
	

}
