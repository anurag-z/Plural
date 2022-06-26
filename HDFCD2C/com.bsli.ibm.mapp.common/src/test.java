import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {

	static WebDriver driver;
	
	public static void main(String[] args) throws IOException {
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

//driver.navigate().to("https://www.javatpoint.com/selenium-webdriver-navigation-commands");  
		
		
		
		driver.get("http://www.google.com");
		WebElement ele = driver.findElement(By.id("hplogo"));

		// Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);

		// Get the location of element on the page
		Point point = ele.getLocation();

		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
		    eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);

		// Copy the element screenshot to disk
		File screenshotLocation = new File("E:\\GoogleLogo_screenshot.png");
		FileUtils.copyFile(screenshot, screenshotLocation);
	}

}
