package com.bsli.ibm_web.run;

import com.bsli.ibm_web.helper.TestNGXmlHelper;
import com.bsli.ibm_web.utility.IConstant;

public class mappRunner 
{
	private String jobid;

	private void run(String args[]) throws Exception
	{
		
		if(args.length>0)
		{
		
		//Create TestNG XML
		System.out.println(IConstant.configMap.get("jobid"));
		
		TestNGXmlHelper xmlHelper=new TestNGXmlHelper(IConstant.configMap.get("jobid"),args[0]);
		
		xmlHelper.createTestNGXml();
		}
		else
		{
			System.out.println("Invalid arguments....testcaseFlag(yes|no|fail)");
		}
	}
	
	public static void main(String args[])
	{
		mappRunner obj= new mappRunner();
		try 
		{	System.out.println(args[0]);
			obj.run(args);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
