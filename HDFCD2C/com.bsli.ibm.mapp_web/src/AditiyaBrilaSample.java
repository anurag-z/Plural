


	import java.io.File;
	import java.io.IOException;
	import java.net.URL;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	import com.google.common.collect.ImmutableMap;

	import io.appium.java_client.AppiumDriver;
	import io.appium.java_client.android.AndroidDriver;
	import io.appium.java_client.ios.IOSDriver;
	import io.appium.java_client.service.local.AppiumDriverLocalService;

	public class AditiyaBrilaSample {
		
		AppiumDriverLocalService service;
		IOSDriver<WebElement> driver;
		String folder_name;
		DateFormat df;
		
		@BeforeTest
		public void setUpSuite() throws Exception {
	}
			
	    @Parameters("deviceName")
		@BeforeMethod
		public void prepareTest() throws IOException, InterruptedException {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			capabilities.setCapability("pCloudy_Username", "pp.petkar@in.ibm.com");
			capabilities.setCapability("pCloudy_ApiKey", "4smrbzwfcyjc7dws9tzt32tp");
			
			capabilities.setCapability("pCloudy_DurationInMinutes", 10);
//			capabilities.setCapability("pCloudy_DeviceManafacturer", "Apple_iPhoneXR_iOS_12.0.1"); //Apple_iPhoneXR_iOS_12.0.1
//			capabilities.setCapability("pCloudy_DeviceVersion", "12.0.1");
			capabilities.setCapability("pCloudy_DeviceFullName", "APPLE_iPad9.7_iOS_12.0.0"); //Apple_iPhone8_iOS_12.2.0
			capabilities.setCapability("platformVersion", "12.0.0");
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setBrowserName("Safari");
			capabilities.setCapability("includeSafariInWebviews", true); 
			capabilities.setCapability("autowebview", true); 
//			capabilities.setCapability("acceptAlerts", true); 
			capabilities.setCapability("automationName", "XCUITest");
			capabilities.setCapability("safariGarbageCollect", true);
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			
			driver = new IOSDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);
			System.out.println("Hi  ");
			
			
	    }
		
	    
		@Test
		public void Test() throws IOException, InterruptedException {
			Set<String>contexts = driver.getContextHandles();
			for(String context : contexts) {
			System.out.println(context);
			if(!context.contains("NATIVE_APP")) {
			driver.context(context);
			}

			}
//			driver.navigate().to("http://www.seleniumframework.com/demo-sites/"); http://103.144.217.11/BSLI/apps/services/www/eapp/desktopbrowser/default/index.html
			driver.navigate().to("http://103.144.217.9:9080/BSLI/apps/services/www/eapp/desktopbrowser/default/index.html#"); //http://103.144.217.11/BSLI/apps/services/www/eapp/desktopbrowser/default/index.html

			
			
			
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;

			WebDriverWait wait=new WebDriverWait(driver, 300);

			Thread.sleep(1500);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username']"))).clear();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username']"))).sendKeys("IN078433");

				    driver.findElement(By.id("j_password")).clear();
				    driver.findElement(By.id("j_password")).sendKeys("str");
				    driver.findElement(By.linkText("Login")).click();
				    Thread.sleep(1500);
				    
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notCSEUSER']/div/div[4]/div/a/span"))).click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='applicationListTable_filter']/label/input"))).click();

				    driver.findElement(By.xpath("//div[@id='applicationListTable_filter']/label/input")).sendKeys("ea105696");
				    
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='applicationListTableBody']/tr/td[1]/a/u"))).click();

				    Thread.sleep(1500);

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'PAN Card')]"))).click();

				    driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/label")).click();
				    
		}

		@AfterMethod
		public void endTest() throws  IOException {

			driver.quit();
		}

		
		public void captureScreenShots() throws IOException {
	        folder_name="screenshot";
	        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        //Date format for screenshot file name
	        df=new  SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
	        //create dir with given folder name
	        new File(folder_name).mkdir();
	        //Setting file name
	        String file_name=df.format(new Date())+".png";
	        //copy screenshot file into screenshot folder.
	        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
	    }
	}


