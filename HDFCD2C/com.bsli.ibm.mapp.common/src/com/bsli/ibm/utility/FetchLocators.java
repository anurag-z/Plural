package com.bsli.ibm.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bsli.ibm.bo.DataSheetDto;

public class FetchLocators 
{
	
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	

	public FetchLocators(Connection con) throws Exception
	{
		this.con = con;
	}
	
	
	public List<DataSheetDto>  GetTestCases(String sql) throws Exception
	{
		 BeanListHandler<DataSheetDto> beanListHandler = new BeanListHandler<>(DataSheetDto.class);
		 QueryRunner runner =new QueryRunner();
		 List<DataSheetDto> list= runner.query(con, sql, beanListHandler);
		System.out.println(list);
		System.out.println(sql);
		 return list;
	}
	
	
	public List<DataSheetDto> GetTestCases(String sql,String testCaseType,String... params) throws Exception
	{
    	BeanListHandler<DataSheetDto> beanListHandler = new BeanListHandler<>(DataSheetDto.class);
		QueryRunner runner =new QueryRunner();
		List<DataSheetDto> list= runner.query(con, sql, beanListHandler,params);
		System.out.println(sql);
		return list;
	}	
	

	
	
	
	
	public void destroy() throws Exception
	{
		ConnectionManager.closeConnection();
	}

	
	
}
