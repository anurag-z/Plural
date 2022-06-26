import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Destop {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		
System.setProperty("webdriver.chrome.driver", "E:\\IBM_DEV\\Chetan\\HDFCD2CNew\\temp\\com.bsli.ibm.mapp\\driver\\chromedriver.exe");
//WebDriver driver;
// Initialize browser
try {
	 driver=new ChromeDriver();

} catch (Exception e) {
e.printStackTrace();
}

//driver.get(" http://mpowerateaseuat.birlasunlife.com:8080/BSLID2C/apps/services/www/bslid2c/desktopbrowser/default/index.html?SSO_STR=vzSOY6BgjmTpKl99P49cOjTQFvBH8hd3M0f8GLHeIXGsNBHUqsnFGrZOe42rnKXB5DUqWMgUgo1h5rr8j+Zu1sb+KWrzQqtQi02ybNUmksNGfwMQBdOtvyd/zSDgCkGvf278wXWfXys0FkERkjnugGnr+z1/wVW2Uv9SJznGutq2xt3T5AhyEqFWyKTIElRJiqruHdpZp0UPWJ7TppE68PwbgQVfETZgrmc27eS6xFdzOl2QrJNO/9BJGtsmLiuUofaRygMKVxSArvZkHcpANfy7SgJFhB1N53n1bQaRX4BYktrI/ZX4yQEqIkdBBFZxGl5bfs5BGJR4XqpuUj+++pAL7Mlcuzo4gPYcKXORFGmdzH9Z2RIj6U1JERZvFWisYPishh+/0/KOazoakSLLk6aCq723A68z/MSO/9jSfGw85hM08mDAu0IQHcAJDSHC");

//driver.navigate().refresh();  

//driver.get("www.javatpoint.com");  

driver.navigate().to("https://www.javatpoint.com/selenium-webdriver-navigation-commands");  
driver.navigate().to("https://www.javatpoint.com/selenium-webdriver-navigation-commands");  

driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;

WebDriverWait wait=new WebDriverWait(driver, 300);

// Maximize browser
Thread.sleep(1500);




// //label[contains(.,'Premium Payable (excl. of GST)')]/parent::*/input

//driver.findElement(By.id("//*[@id='premium-payable']")).click();
//List <WebElement> framesList = driver.findElements(By.xpath("//iframe"));
//int numOfFrames = framesList.size();
//System.out.println(numOfFrames);
WebElement web1 = driver.findElement(By.xpath("//label[contains(.,'Premium Payable (excl. of GST)')]/parent::*/input"));

web1.sendKeys(Keys.RIGHT);

WebElement slider=driver.findElement(By.xpath("//*[@id='policy-term']"));
slider.sendKeys(Keys.RIGHT);


// //*[@id="rider-sumassured"]

WebElement slider1=driver.findElement(By.xpath("//*[@id='rider-sumassured']"));
slider1.sendKeys(Keys.RIGHT);


((JavascriptExecutor)driver).executeScript("$(arguments[0]).val('.01').change()", slider);

web1.sendKeys(Keys.RIGHT);
((JavascriptExecutor)driver).executeScript("$(arguments[0]).val('.01').change()", slider);

System.out.println(driver.findElement(By.xpath("//label[contains(.,'Premium Payable (excl. of GST)')]/parent::*/div/b")).getAttribute("b"));
web1.sendKeys(Keys.RIGHT);
web1.sendKeys(Keys.RIGHT);
web1.sendKeys(Keys.RIGHT);

driver.switchTo().defaultContent();
web1.sendKeys(Keys.RIGHT);
web1.sendKeys(Keys.RIGHT);// 555000 565000
web1.sendKeys(Keys.RIGHT);


try {
	WebElement web = driver.findElement(By.xpath("//label[contains(.,'I have gone through the ')]"));
} catch (Exception e) {
e.printStackTrace();
}

driver.switchTo().parentFrame();
try {
	WebElement web = driver.findElement(By.xpath("//label[contains(.,'I have gone through the ')]"));
} catch (Exception e) {
	e.printStackTrace();
}

WebElement web = driver.findElement(By.xpath("//label[contains(.,'I have gone through the ')]"));

JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", web);
web.sendKeys(Keys.RIGHT);



//driver.manage().window().maximize();// 

wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username']"))).sendKeys("IN078433");

//driver.findElement(By.xpath("//input[@id='j_username']")).sendKeys("IN078433");

	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("str");
	    driver.findElement(By.linkText("Login")).click();
	    Thread.sleep(1500);
	    
	    
//		WebElement guru99seleniumlink;
//		guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "/html/body/div[1]/section/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/a/i")));
//		guru99seleniumlink.click();
	    
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notCSEUSER']/div/div[4]/div/a/span"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='applicationListTable_filter']/label/input"))).click();

//	    driver.findElement(By.xpath("//div[@id='applicationListTable_filter']/label/input")).clear();
	    driver.findElement(By.xpath("//div[@id='applicationListTable_filter']/label/input")).sendKeys("ea105696");
	    
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='applicationListTableBody']/tr/td[1]/a/u"))).click();

//	    driver.findElement(By.xpath("//*[@id='applicationListTableBody']/tr/td[1]/a/u")).click();
	    Thread.sleep(1500);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'PAN Card')]"))).click();

//	    driver.findElement(By.xpath("//a[contains(.,'PAN Card')]")).click();
	    driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/label")).click();
	    
	    driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/input")).sendKeys("https://image.shutterstock.com/image-vector/high-quality-image-web-icon-600w-1683829528.jpg");

	    
	    
	    // //*[@id="selectImgDevice"]
	    
	    
	    // Eapp Module 
	    
	    driver.findElement(By.xpath("//div[@id='trackEappStatus']/a/span/img")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("ea10580946");
	    driver.findElement(By.xpath("//tbody[@id='applicationListTableBody']/tr/td/a")).click();
	    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
	    driver.findElement(By.xpath("//div[@id='pagePort']/div[3]/div/div[7]/div/ul/li[5]/a")).click();
	    driver.findElement(By.xpath("//input[@name='paymentMode']/following::label[contains(.,'Cheque')]")).click();
//	    driver.findElement(By.xpath("//div[@id='payCheque']/div/div/label")).click();// 
//	    driver.findElement(By.xpath("//div[@id='selectImgDesktop']/a/label")).click();
	    // 
	    
	    driver.findElement(By.xpath("//div[@id='selectImgDesktop']/a/input")).sendKeys("C:\\Users\\Admin\\Downloads\\1.jpg");
	    
	    driver.findElement(By.id("bnuDesktop")).click();
	    
	    
	    // https://image.shutterstock.com/image-vector/high-quality-image-web-icon-600w-1683829528.jpg
	}

	private static void send_keys(Keys right) {
		// TODO Auto-generated method stub
		
	}

}
