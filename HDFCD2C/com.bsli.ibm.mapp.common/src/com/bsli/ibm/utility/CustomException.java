package com.bsli.ibm.utility;



public class CustomException extends Exception

{
	private final int codes;
public CustomException(String errorMsg,Throwable cause,int codes)
{
	super(errorMsg, cause);
	this.codes = codes;
}

}
