package com.bsli.ibm.utility;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class DriverSetupMapp 
{
	
	private DesiredCapabilities capabilities;
	
	

	private DriverSetupMapp()
	{
		
	}
	
	public DriverSetupMapp(HashMap<String,String> configMap,HashMap<String,String> testData)
	{
		setCapabilities(configMap,testData);
	}
	
	public void setCapabilities(HashMap<String,String> configMap,HashMap<String,String> testData)
	{
		capabilities = new DesiredCapabilities();
		
        Map<String,String> result = configMap.entrySet().stream()
                .filter(map -> map.getKey().startsWith("MAPP.capability"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        	String SelectionOfDevices=testData.get("DeviceSelection");	
        switch(SelectionOfDevices)
        {
        case "Chrome" :
        	
        	
        	
        	capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testData.get("DeviceVersion"));
        	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testData.get("DeviceName"));
        	capabilities.setCapability(MobileCapabilityType.UDID, testData.get("MobileUID"));
//            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10000);
    		capabilities.setCapability("newCommandTimeout", 10000);

        	//capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
//        	capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        	
        	break;
        	
        case "pCloudy" :
        	
        	capabilities.setCapability("pCloudy_Username",testData.get("pCloudy_UserName"));
            capabilities.setCapability("pCloudy_ApiKey",testData.get("pCloudy_ApiKey"));
//            capabilities.setCapability("pCloudy_ApplicationName", testData.get("pCloudy_ApplicationName"));
            capabilities.setCapability("pCloudy_DeviceVersion",  testData.get("DeviceVersion"));
//            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testData.get("DeviceVersion"));
        	capabilities.setCapability("pCloudy_DurationInMinutes", Integer.parseInt(testData.get("pCloudy_DurationInMinutes")));
    		capabilities.setCapability("automationName", "uiautomator2");
    		capabilities.setCapability("newCommandTimeout", 600);
    		capabilities.setCapability("launchTimeout", 90000);
    		capabilities.setBrowserName("Chrome");
//        	capabilities.setCapability("pCloudy_DeviceManafacturer",testData.get("DeviceName"));
        	if(!("NA".equalsIgnoreCase(testData.get("Device_FullName"))))
        	{
        		capabilities.setCapability("pCloudy_DeviceFullName",testData.get("Device_FullName"));
        	}

        	
        	
        	
        	break;
        	

        	
        default:     
			 System.out.println("Selection of devices is not set");
        
        }
        	
        

            
        for(String key: result.keySet())
        {

        	if(key.startsWith("MAPP.capability.text."))
        	{

        		capabilities.setCapability(key.replaceAll("MAPP.capability.text.", ""), configMap.get(key));		
        	}
        	else if(key.startsWith("MAPP.capability.boolean."))
        	{
        		
        		capabilities.setCapability(key.replaceAll("MAPP.capability.boolean.", ""),configMap.get(key));
        	}
        	else if(key.startsWith("MAPP.capability.int."))
        	{
        		capabilities.setCapability(key.replaceAll("MAPP.capability.int.", ""),Integer.parseInt(configMap.get(key)));		
        	}
        }
        
        

	}
	
	
	public AndroidDriver getDriver(String url) throws Exception
	{
		AndroidDriver driver = new AndroidDriver<>(new URL(url), capabilities);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    Thread.sleep(1000);
	    return driver;
	}
	
	
	public void closeApp(AndroidDriver driver) throws Exception
	{
		driver.closeApp();
	}
	
	public void quitApp(AndroidDriver driver)
	{
		driver.quit();
	}

}
