package com.bsli.ibm.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ErrorCodes;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bsli.ibm.utility.Common_Functions;
import com.bsli.ibm.utility.reports.IF.*;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class WrapperMethods {

	private String datasheetPath;
	private String sheetName;
	private String snapshotsPath;
	private String reportPath;
	private String timeout;

	private ITestReporter testReporter;

	public WrapperMethods() {
	}

	public WrapperMethods(String datasheetPath, String sheetName, String snapshotsPath, String reportPath,
			ITestReporter testReporter) {
		this.datasheetPath = datasheetPath;
		this.sheetName = sheetName;
		this.snapshotsPath = snapshotsPath;
		this.reportPath = reportPath;
		this.testReporter = testReporter;

	}

	/*
	 * public WrapperMethods(String datasheetPath,String sheetName,String
	 * snapshotsPath,String reportPath,ITestReporter testReporter) {
	 * this.datasheetPath=datasheetPath; this.sheetName=sheetName;
	 * this.snapshotsPath=snapshotsPath; this.reportPath=reportPath;
	 * this.testReporter=testReporter;
	 * 
	 * }
	 */

	public boolean isElementPresentByXpath(String xpath, WebDriver driver, String Element_Name)
			throws InterruptedException {
		try {
			driver.findElement(By.xpath(xpath));
			testReporter.Log_Pass(Element_Name + " Exist", Element_Name + " Exist");
		} catch (Throwable t) {
			// Log.error("Element not Found -->"+t.getMessage());
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementPresentByLinkText(String linkText, WebDriver driver) throws InterruptedException {
		try {
			driver.findElement(By.linkText(linkText));
			testReporter.Log_Pass(linkText + " Exist", linkText + " Exist");
		} catch (Throwable t) {

			testReporter.Log_Fail(linkText + " does not Exist", linkText + " does not Exist", driver, snapshotsPath);
			t.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementPresentByID(String ID, WebDriver driver, String Element_Name) throws InterruptedException {
		try {
			driver.findElement(By.id(ID));
			testReporter.Log_Pass(Element_Name + " Exist", Element_Name + " Exist");
		} catch (Throwable t) {

			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementPresentByName(String Name, WebDriver driver, String Element_Name)
			throws InterruptedException {
		try {
			driver.findElement(By.name(Name));
			testReporter.Log_Pass(Element_Name + " Exist", Element_Name + " Exist");
		} catch (Throwable t) {

			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementPresentByClassName(String className, WebDriver driver, String Element_Name)
			throws InterruptedException {
		try {
			driver.findElement(By.className(className));
			testReporter.Log_Pass(Element_Name + " Exist", Element_Name + " Exist");
		} catch (Throwable t) {

			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			return false;
		}
		return true;
	}

	public void clickButtonID(WebDriver screenName, String ID, String Element_Name) throws Exception {
		try {

			WebElement webButton = screenName.findElement(By.id(ID));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clickButton(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clickLink(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clickLinkByLinkText(WebDriver screenName, String linkText, String Element_Name) throws Exception {
		try {
			WebElement webButton = screenName.findElement(By.linkText(linkText));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public boolean clickFirst(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		boolean flag = true;
		try {
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");

		}
		return flag;
	}

	public void inputText(WebDriver screenName, String ObjectxPath, String sValue, String Element_Name)
			throws Exception {
		try {
			this.waitForElementVisible(screenName, ObjectxPath);
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			inputText.clear();
			inputText.sendKeys(sValue);
			testReporter.Log_Pass(Element_Name + " Entered", sValue + " entered in " + Element_Name);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			// throw new Exception("Element Not Present");
			throw new CustomException("Element Not Present", t, ErrorCodes.ELEMENT_NOT_VISIBLE);
		}

	}

	public void inputText_keyoperaation(WebDriver driver, String ObjectxPath, String sValue) throws Exception {
		try {

			WebElement inputText = driver.findElement(By.xpath(ObjectxPath));

			switch (sValue) {
			case "DOWN":
				inputText.sendKeys(Keys.ARROW_DOWN);

				break;

			default:
				break;
			}

		} catch (Throwable t) {

			t.printStackTrace();
			// throw new Exception("Element Not Present");
			throw new CustomException("Element Not Present", t, ErrorCodes.ELEMENT_NOT_VISIBLE);
		}

	}

	public void removeAttribute(WebDriver driver) {
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		for (WebElement input : inputs) {
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", input);
		}
	}

	public void typeNonEditable(WebDriver driver, String ObjectxPath, String sValue, String Element_Name)
			throws Exception {
		removeAttribute(driver);
		inputText(driver, ObjectxPath, sValue, Element_Name);
	}

	public void enterText(WebDriver screenName, String ObjectxPath, String sValue, String Element_Name)
			throws Exception {
		Actions actions = new Actions(screenName);
		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			actions.moveToElement(inputText);
			actions.click();
			actions.sendKeys(sValue);
			actions.build().perform();
			testReporter.Log_Pass(Element_Name + " Entered", sValue + " entered in " + Element_Name);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public void inputTextByID(WebDriver screenName, String ID, String sValue, String Element_Name) throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.id(ID));

			inputText.sendKeys(sValue);
			testReporter.Log_Pass(Element_Name + " Entered", sValue + " entered in " + Element_Name);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public void selectCheckBox(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement checkBox = screenName.findElement(By.xpath(ObjectxPath));
			checkBox.click();
			testReporter.Log_Pass(Element_Name + " checkbox clicked", Element_Name + " checkbox clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void selectRadio(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement radioButton = screenName.findElement(By.xpath(ObjectxPath));
			radioButton.click();
			testReporter.Log_Pass(Element_Name + " radiobutton clicked", Element_Name + " radiobutton clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public String getInputTextValue(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			testReporter.Log_Pass(Element_Name + " exist", Element_Name + " has " + inputText.getText());
			return inputText.getText();
		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");

		}
	}

	public String getInputValue(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			testReporter.Log_Pass(Element_Name + " exist", Element_Name + " has " + inputText.getText());
			return inputText.getAttribute("value");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public String getAttributeValue(WebDriver screenName, String ObjectxPath, String attributeName, String Element_Name)
			throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			testReporter.Log_Pass(Element_Name + " exist", Element_Name + " has " + inputText.getText());
			return inputText.getAttribute("attributeName");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clearInputTextValue(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			inputText.clear();
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clearInputTextValueByName(WebDriver screenName, String name, String Element_Name) throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.name(name));
			inputText.clear();
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void clearAndInputTextValue(WebDriver screenName, String ObjectxPath, String value, String Element_Name)
			throws Exception {

		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			Thread.sleep(1000);
			inputText.clear();
			Thread.sleep(1000);
			inputText.sendKeys(value);
			testReporter.Log_Pass(Element_Name + " cleared & Entered with " + value,
					Element_Name + " cleared & Entered with " + value);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void selectDropBoxValue(WebDriver screenName, String ObjectxPath, String value, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			select.selectByValue(value);
			testReporter.Log_Pass(Element_Name + " selected with " + value, Element_Name + " selected with " + value);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public void selectDropBoxByVisibleText(WebDriver screenName, String ObjectxPath, String value, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			select.selectByVisibleText(value);
			testReporter.Log_Pass(Element_Name + " selected with " + value, Element_Name + " selected with " + value);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void selectDropBoxValueByID(WebDriver screenName, String ID, String value, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.id(ID));
			Select select = new Select(selectDropBox);
			select.selectByValue(value);
			testReporter.Log_Pass(Element_Name + " selected with " + value, Element_Name + " selected with " + value);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public void selectDropBoxValueByName(WebDriver screenName, String Name, String value, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.name(Name));
			Select select = new Select(selectDropBox);
			select.selectByValue(value);
			testReporter.Log_Pass(Element_Name + " selected with " + value, Element_Name + " selected with " + value);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public void selectDropBoxValue(WebDriver screenName, String ObjectxPath, int index, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			select.selectByIndex(index);
			testReporter.Log_Pass(Element_Name + " selected with " + index, Element_Name + " selected with " + index);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public void selectDropBoxDefaultValue(WebDriver screenName, String ObjectxPath, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			select.selectByIndex(0);
			testReporter.Log_Pass(Element_Name + " selected with default value ",
					Element_Name + " selected with default value ");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public String getDropBoxDefaultValue(WebDriver screenName, String ObjectxPath, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			testReporter.Log_Pass(Element_Name + " selected value is " + select.getFirstSelectedOption().getText(),
					Element_Name + " selected value is " + select.getFirstSelectedOption().getText());
			return select.getFirstSelectedOption().getText();

		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public String getDropBoxSelectedValue(WebDriver screenName, String ObjectxPath, int index, String Element_Name)
			throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			testReporter.Log_Pass(Element_Name + " selected value is " + select.getFirstSelectedOption().getText(),
					Element_Name + " selected value is " + select.getFirstSelectedOption().getText());
			return select.getOptions().get(index).getText();
		}

		catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public int getDropBoxSize(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			testReporter.Log_Pass(Element_Name + " dropbox size is " + select.getOptions().size(),
					Element_Name + " dropbox size is " + select.getOptions().size());
			return select.getOptions().size();
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public String[] getDropBoxValue(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		try {
			WebElement selectDropBox = screenName.findElement(By.xpath(ObjectxPath));
			Select select = new Select(selectDropBox);
			List<WebElement> optionValue = select.getOptions();
			String[] value = new String[optionValue.size()];
			for (int i = 0; i < optionValue.size(); i++)
				value[i] = optionValue.get(i).getText();
			return value;
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}

	}

	public int getTotalTableCell(WebDriver driver, String ObjectxPath, String Element_Name) throws Exception {
		try {
			testReporter.Log_Pass(Element_Name + " table size is " + driver.findElements(By.xpath(ObjectxPath)).size(),
					Element_Name + " table size is " + driver.findElements(By.xpath(ObjectxPath)).size());
			return driver.findElements(By.xpath(ObjectxPath)).size();
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public int getElementsSize(WebDriver driver, String ObjectxPath, String Element_Name) throws Exception {
		try {
			testReporter.Log_Pass(
					Element_Name + " element size is " + driver.findElements(By.xpath(ObjectxPath)).size(),
					Element_Name + " element size is " + driver.findElements(By.xpath(ObjectxPath)).size());
			return driver.findElements(By.xpath(ObjectxPath)).size();
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public int getElementCount(WebDriver driver, String className, String Element_Name) throws Exception {
		int count = 0;

		try {
			count = driver.findElements(By.className(className)).size();
			testReporter.Log_Pass(Element_Name + " element count " + count, Element_Name + " element size is " + count);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
		return count;
	}

	public boolean isElementDisplayed(WebDriver driver, String xpath, String Element_Name, int t) {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				testReporter.Log_Pass(Element_Name + " is displayed ", Element_Name + " is displayed ");
				flag = true;
			}
		} catch (Throwable e) {
			testReporter.Log_Pass(Element_Name + " is not displayed ", Element_Name + " is not displayed ");
			flag = false;
		}
		return flag;

	}

	public boolean isElementDisplayed(WebDriver driver, String xpath, String Element_Name) {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				testReporter.Log_Pass(Element_Name + " is displayed ", Element_Name + " is displayed ");
				flag = true;
			}
		} catch (Throwable e) {
			testReporter.Log_Pass(Element_Name + " is not displayed ", Element_Name + " is not displayed ");
			flag = false;
		}
		return flag;

	}

	public boolean isElementDisplay(WebDriver driver, String xpath) {
		boolean flag = false;
		try {
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				flag = true;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;

	}

	// Rohit - ETS -- 11 June 2015
	public void mouseHoverAction_3(WebDriver driver, String mainElementXP, String subElementXP, String subSubElementXP,
			String Element_Name) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
			action.moveToElement(mainElement).perform();
			WebElement subElement = driver.findElement(By.xpath(subElementXP));
			action.moveToElement(subElement).perform();
			WebElement subSubElement = driver.findElement(By.xpath(subSubElementXP));
			action.moveToElement(subSubElement);
			action.click();
			action.perform();

			testReporter.Log_Pass("Click action is performed on the selected Product Type" + Element_Name,
					"Click action is performed on the selected Product Type" + Element_Name);

		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void mouseHoverAction_2(WebDriver driver, String mainElementXP, String subElementXP, String Element_Name)
			throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
			action.moveToElement(mainElement).perform();
			WebElement subElement = driver.findElement(By.xpath(subElementXP));
			action.moveToElement(subElement);
			action.click();
			action.perform();

			testReporter.Log_Pass("Click action is performed on the selected Product Type" + Element_Name,
					"Click action is performed on the selected Product Type" + Element_Name);

		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void mouseHoverAction(WebDriver driver, String mainElementXP, String Element_Name) throws Exception {

		try {
			Actions action = new Actions(driver);
			WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
			action.moveToElement(mainElement).clickAndHold().build().perform();
			action.release().perform();
			// action.perform();

			testReporter.Log_Pass("Click action is performed on the selected Product Type" + Element_Name,
					"Click action is performed on the selected Product Type" + Element_Name);

		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void mouseHoverAction(WebDriver driver, String mainElementXP, String subElementXP, String subSubElementXP,
			String subBesideElementXP, String Element_Name) throws Exception {
		try

		{
			Actions action = new Actions(driver);
			WebElement mainElement = driver.findElement(By.xpath(mainElementXP));
			action.moveToElement(mainElement).perform();
			WebElement subElement = driver.findElement(By.xpath(subElementXP));
			action.moveToElement(subElement).perform();
			WebElement subSubElement = driver.findElement(By.xpath(subSubElementXP));
			action.moveToElement(subSubElement).perform();
			WebElement subBesideElement = driver.findElement(By.xpath(subBesideElementXP));
			action.moveToElement(subBesideElement).perform();
			action.click();
			action.perform();

			testReporter.Log_Pass("Click action is performed on the selected Product Type" + Element_Name,
					"Click action is performed on the selected Product Type" + Element_Name);

		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void selectclass(WebDriver driver, WebElement parent, String elementToSelect, String Element_Name)
			throws Exception {

		try {
			Select dropdown = new Select(parent);
			dropdown.selectByVisibleText(elementToSelect);
			testReporter.Log_Pass("selected " + elementToSelect, "selected " + elementToSelect + "in " + parent);

		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");

		}

	}

	public void getWindowHandle(WebDriver driver, String title) {
		Set<String> handles = driver.getWindowHandles();

		String[] browser = handles.toArray(new String[0]);
		System.out.println("Number of browsers opened are" + browser.length);
		for (int i = 0; i < handles.size(); i++) {

			driver.switchTo().window(browser[i]);
			if (driver.getTitle().equals(title)) {
				driver.getWindowHandle();

			}

		}

	}

	public void selectWindowIfElementPresent(WebDriver driver, String element) throws InterruptedException {
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		Object[] win = windows.toArray();
		System.out.println(win.length);
		driver.switchTo().window(win[0].toString());
		if (isElementDisplay(driver, element)) {
			testReporter.Log_Pass("Selected Pop Up : " + driver.switchTo().window(win[0].toString()).getTitle(),
					"Selected Pop Up : " + driver.switchTo().window(win[0].toString()).getTitle());
		} else {
			driver.switchTo().window(win[1].toString());
			System.out.println("Selected Pop Up : " + driver.switchTo().window(win[1].toString()).getTitle());
		}
	}

	public void waitForElementNotPresent(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
		} catch (TimeoutException te) {
		}
	}

	public void waitForElementVisible(WebDriver driver, String element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		} catch (NoSuchWindowException e) {
		} catch (InvalidElementStateException e) {
		} catch (TimeoutException te) {
		} catch (NoSuchElementException e) {
		} catch (WebDriverException we) {
		}
	}

	public void waitForElementClickable(WebDriver driver, String element, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 80);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		} catch (NoSuchWindowException e) {
		} catch (InvalidElementStateException e) {
		} catch (TimeoutException te) {
		} catch (NoSuchElementException e) {
		} catch (WebDriverException we) {
		}
	}

	public void waitForTextPresent(WebDriver driver, String text) throws InterruptedException {
		try {
			Thread.sleep(2000);
			long timer = 0;
			while (isTextPresent(driver, text) == false && timer < Long.valueOf("30000")) {
				Thread.sleep(500);
				timer += 5000;
			}
		} catch (NoSuchWindowException e) {
		}
	}

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	public boolean isTextPresent(WebDriver driver, String text) {
		boolean flag = false;
		try {
			flag = driver.findElement(By.xpath("//*[contains(.,'" + text + "')]")).isDisplayed();
		} catch (NoSuchElementException e) {
			return flag;
		} catch (NoSuchWindowException e) {
			return flag;
		}
		return flag;
	}

	public void getTableData(WebDriver driver, String xpath) {
		// Grab the table
		WebElement table = driver.findElement(By.xpath(xpath));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Print the contents of each cell
			for (WebElement cell : cells) {
				System.out.println(cell.getText());

			}
		}
	}

	public void JavascriptExecutorClick(WebDriver driver, String id) {
		

		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("document.getElementById('"+id+"').click();");
		  
	}
	
	// Function for fetching the value from the object when value attribute is
	// not present.
	public String getObjectValue(WebDriver driver, String xpath) {

		WebElement webElement = driver.findElement(By.xpath(xpath));
		JavascriptExecutor e = (JavascriptExecutor) driver;
		return (String) e.executeScript(String.format("return $('#%s').val();", webElement.getAttribute("id")));
	}

	public String getObjectValueClass(WebDriver driver, String xpathValue) {
		WebElement webElement = driver.findElement(By.xpath(xpathValue));
		JavascriptExecutor e = (JavascriptExecutor) driver;
		return (String) e.executeScript(String.format("return $('#%s').val();", webElement.getAttribute("class")));
	}

	public void doubleClick(WebDriver driver, String string) throws InterruptedException {
		try {

			Actions action = new Actions(driver);
			WebElement webElement = driver.findElement(By.xpath(string));
			action.moveToElement(webElement);
			Thread.sleep(2000);
			action.doubleClick();
			// action.doubleClick(myElemment);
			action.build().perform();
		} catch (Exception e) {

		}
	}

	public int getElementCountXPath(WebDriver driver, String ObjectPath, String Element_Name)
			throws InterruptedException {
		int count = 0;
		if (isElementPresentByXpath(ObjectPath, driver, Element_Name)) {
			count = driver.findElements(By.xpath(ObjectPath)).size();
		}
		return count;
	}

	public void acceptAlert(WebDriver driver) throws InterruptedException {
		try {
			Alert alert = waitforAlertPresent(driver);
			if (!alert.equals(null))
				alert.accept();
		} catch (NoAlertPresentException ex) {
		}
	}

	public Alert waitforAlertPresent(WebDriver driver) throws InterruptedException {
		int i = 0;
		Alert alert = null;
		while (i++ < 30) {
			try {
				alert = driver.switchTo().alert();
				return alert;
			} catch (NoAlertPresentException e) {
				Thread.sleep(1000);
				continue;
			}
		}
		return alert;
	}

	public void waitForPopUp(WebDriver driver, String b) throws InterruptedException {
		Thread.sleep(3000);
		try {
			selectPopUp(driver, b);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (NoSuchWindowException e1) {
		} catch (Exception e) {
		}
	}

	public void selectPopUp(WebDriver driver, String arg) {
		boolean flag = false;
		try {
			for (int i = 0; i < 2 && flag == false; i++) {
				Set<String> pops = driver.getWindowHandles();

				Iterator<String> it = pops.iterator();
				if (pops.size() > 1) {
					System.out.println("No of Windows " + pops.size());
					for (int j = 0; j < pops.size() && flag == false; j++) {
						String popupHandle = it.next().toString();
						if (driver.switchTo().window(popupHandle).getTitle().contains(arg)) {
							driver.switchTo().window(popupHandle);
							flag = true;
						}
					}
					flag = true;

					pops.clear();
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Not able to Navigate to Window " + arg);
		} catch (Exception e) {
		}
	}

	public void checkUsingJavaScript(WebDriver driver, String obj, String ObjectName) throws InterruptedException {
		try {
			WebElement element = null;
			if (obj.startsWith("id")) {
				element = driver.findElement(By.id(obj.split("id:")[1]));
			} else if (obj.startsWith("name")) {
				element = driver.findElement(By.name(obj.split("name:")[1]));

			} else {
				element = driver.findElement(By.xpath(obj));

			}
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			testReporter.Log_Pass(ObjectName + " clicked", ObjectName + " Ciicked");
		} catch (Throwable t) {

			testReporter.Log_Fail(ObjectName + " Not Present", ObjectName + " Not Present", driver, snapshotsPath);
			t.printStackTrace();
			new Exception(ObjectName + " not present");
		}
	}

	public void waitForFrameAndSwitch(WebDriver driver, String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));

	}

	public void waitForFrameAndSwitch(String frameXpath, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frameXpath)));

	}

	public void waitForElementVisible(WebDriver driver, String element, String Element_Name) throws Throwable {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));

		} catch (Throwable we) {
			we.printStackTrace();
			testReporter.Log_Fail(Element_Name + "Not visible", Element_Name + "Not visible", driver, snapshotsPath);
			throw new CustomException("No Element Found visible", we, ErrorCodes.ELEMENT_NOT_VISIBLE);

		}
	}

	public void Javascriptexecutor_forClick(WebDriver driver, String frameName, String XpathObject, String ObjectName)
			throws Throwable {
		waitForPageToLoad(driver);
		waitForFrameAndSwitch(driver, frameName);
		waitForPageToLoad(driver);
		waitForElementVisible(driver, XpathObject, ObjectName);
		try {
			WebElement e = driver.findElement(By.xpath(XpathObject));

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click();", e);
			testReporter.Log_Pass(ObjectName + " clicked", ObjectName + " Ciicked");
			js = null;
			e = null;
		} catch (Throwable t)

		{

			testReporter.Log_Fail(ObjectName + " Not Present", ObjectName + " Not Present", driver, snapshotsPath);
			t.printStackTrace();
			new Exception(ObjectName + " not present");
		}

	}

	public String[] findElementsInArray(WebDriver driver, String ObjectxPath, String ObjectName)
			throws InterruptedException, Exception {
		String[] array = null;
		int i = 0;
		if (isElementPresentByXpath(ObjectxPath, driver, ObjectName)) {
			List<WebElement> list = driver.findElements(By.xpath(ObjectxPath));
			array = new String[list.size()];
			Iterator<WebElement> it = list.iterator();
			while (it.hasNext()) {
				array[i++] = it.next().getText();
			}
		} else {
			testReporter.Log_Fail("findElementsInArray", ObjectxPath + " is not present", driver, snapshotsPath);
			throw new Exception("findElementsInArray() --- >Element Not Present");
		}

		return array;
	}

	public void removeAttribute(WebDriver driver, String ObjectXpath) {
		WebElement input = driver.findElement(By.xpath(ObjectXpath));

		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('disabled','disabled')", input);

	}

	public void clickUsingActions(WebDriver screenName, String ObjXpath, String Element_Name) throws Exception {
		try {
			Actions actions = new Actions(screenName);
			WebElement webButton = screenName.findElement(By.xpath(ObjXpath));
			actions.click(webButton);
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public List<WebElement> findElementsInList(WebDriver driver, String ObjectxPath, String ObjectName)
			throws InterruptedException, Exception {

		List<WebElement> list = null;

		if (isElementPresentByXpath(ObjectxPath, driver, ObjectName)) {
			list = driver.findElements(By.xpath(ObjectxPath));

		} else {
			testReporter.Log_Fail("findElementsInArray", ObjectxPath + " is not present", driver, snapshotsPath);
			throw new Exception("findElementsInArray() --- >Element Not Present");
		}
		return list;
	}

	public boolean selectWindow(String windowname, WebDriver driver) {

		try {

			Thread.sleep(1000);

		} catch (InterruptedException e1) {

			e1.printStackTrace();

		}

		boolean selWindow = false;

		try {

			if (windowname != null) {

				if (windowname.contains("null")) {

					switchToWindow(1, driver);

					selWindow = true;

				} else {

					selectPopUp(windowname, driver);

					selWindow = true;

				}

			} else {

				switchToWindow(1, driver);

				selWindow = true;

			}

		} catch (Exception e) {

			{

				e.getMessage();

				e.printStackTrace();

				switchToWindow(1, driver);

				selWindow = false;

			}

		}

		return selWindow;

	}

	public void switchToWindow(int WindowNumber, WebDriver driver) {

		WindowNumber = WindowNumber - 1;
		try {
			Set<String> windows = driver.getWindowHandles();
			System.out.println(windows.size());
			Object[] win = windows.toArray();
			System.out.println(win.length);
			driver.switchTo().window(win[WindowNumber].toString());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("\nException\n");
		}
	}

	public void selectPopUp(String arg, WebDriver driver) {

		boolean flag = false;

		try {

			for (int i = 0; i < 2 && flag == false; i++) {

				Set<String> pops = driver.getWindowHandles();

				{

					Iterator<String> it = pops.iterator();

					if (pops.size() > 1) {

						System.out.println("No of Windows " + pops.size());

						for (int j = 0; j < pops.size() && flag == false; j++) {

							String popupHandle = it.next().toString();

							if (driver.switchTo().window(popupHandle).getTitle().contains(arg)) {

								driver.switchTo().window(popupHandle);

								flag = true;

							}

						}

						pops.clear();

					}

				}

			}

		} catch (NoSuchWindowException e) {

			System.out.println("Not able to Navigate to Window " + arg);

		} catch (Exception e) {

		}

	}

	public void waitUntilExist(WebDriver driver, String ObjectxPath) throws Exception

	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		int i = 1;
		do {

			i = i + 1;
			Thread.sleep(1000);

			try {
				WebElement webButton = driver.findElement(By.xpath(ObjectxPath));
				System.out.println("X" + i);
				webButton.getText();
			} catch (Throwable t)

			{
				break;

			}
		} while (i < 100);
		waitForPageToLoad(driver);

	}

	public boolean CheckifExist(WebDriver driver, String element, int number) {
		try {
			driver.manage().timeouts().implicitlyWait(number, TimeUnit.MILLISECONDS);
			WebDriverWait wait = new WebDriverWait(driver, number);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			// driver.findElement(By.xpath(element));
			waitForPageToLoad(driver);
			return true;

		} catch (Exception e) {
			System.out.println("not exist");
			waitForPageToLoad(driver);
			return false;

		}
	}

	public void Clickbtn(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception {
		Thread.sleep(500);
		try {
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t)

		{
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void Clickbtn2(WebDriver screenName, String ObjectxPath, String Element_Name) throws Exception

	{
		Thread.sleep(500);
		try {
			WebElement webButton = screenName.findElement(By.xpath(ObjectxPath));
			webButton.click();
			testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		} catch (Throwable t) {
			// testReporter.Log_Fail(Element_Name+" does not
			// Exist",Element_Name+" does not Exist", screenName,snapshotsPath);
			// t.printStackTrace();
			throw new Exception("Element Not Present");
		}
	}

	public void WaitFunction(WebDriver driver, String Message) throws Exception {
		waitUntilExist(driver, "//*[contains(text(),'Loading...')]");
		waitUntilExist(driver, "//*[contains(text(),'Loading...')]");
		if (CheckifExist(driver, "//*[contains(text(),'Attention')]", 3)) {
			testReporter.Log_Fail("Attention Error Occured", "Attention Error Occured" + Message, driver,
					snapshotsPath);
			new Exception("Attention Error Occured");
		}

	}

	public void waituntilDisplayed(WebDriver driver, String xpath) throws InterruptedException

	{

		int i = 1;

		boolean x = false;
		do {
			i = i + 1;
			Thread.sleep(2000);
			try {
				x = driver.findElement(By.xpath(xpath)).isDisplayed();

			} catch (Throwable t) {

			}
			if (i == 60) {
				break;
			}

		} while (x == false);

	}

	public boolean CheckifTextExistAndReport(WebDriver driver, String element, String Element_Name)
			throws InterruptedException {
		try {
			// driver.manage().timeouts().implicitlyWait(0,
			// TimeUnit.MILLISECONDS);
			String Strelement = "//*[contains(text(),'" + element + "')]";
			driver.findElement(By.xpath(Strelement));
			testReporter.Log_Pass(Element_Name + " Exist", Element_Name + " is Exist");
			System.out.println("Element Exist");
			// return true;
			// driver.switchTo().defaultContent();
			// System.out.println(""+driver.getPageSource().toString());
			// System.out.println("");
			// if (driver.getPageSource().contains(element))
			// {
			waitForPageToLoad(driver);
			return true;
			// }
			// else
			// {
			// return false;
			// }

		} catch (Throwable t) {

			waitForPageToLoad(driver);
			System.out.println("not exist");
			t.printStackTrace();
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + "does not Exist", driver,
					snapshotsPath);

			return false;
		}

	}

	public boolean CheckifExistwithWait(WebDriver driver, String element) {

		try {
			// driver.manage().timeouts().implicitlyWait(0,
			// TimeUnit.MILLISECONDS);
			// WebDriverWait wait = new WebDriverWait(driver, 1);
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			driver.findElement(By.xpath(element));
			// waitForPageToLoad(driver);
			return true;

		} catch (Throwable e) {
			// e.printStackTrace();

			return false;
		}
	}

	public void MouseClick(WebDriver driver, String Xpath) throws AWTException {

		Robot bot = new Robot();
		WebElement e = driver.findElement(By.xpath(Xpath));
		int x = e.getLocation().getX();
		int y = e.getLocation().getY();
		System.out.println(x + " " + y);
		bot.mouseMove(x, y);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public int getTableRowCount(WebDriver driver, String xapth) {
		try {
			WebElement htmltable = driver.findElement(By.xpath(xapth));

			List<WebElement> rows = htmltable.findElements(By.tagName("tr"));
			// List<WebElement>
			// columns=rows.get(1).findElements(By.tagName("td"));

			// System.out.println("Number of columns:"+rows.size());
			System.out.println(rows.size());
			return rows.size();
		} catch (Throwable t) {

			t.printStackTrace();
			return 0;
		}

	}

	public boolean CheckifTextExistwithoutReport(WebDriver driver, String element, String Element_Name)
			throws InterruptedException {
		try {
			// driver.manage().timeouts().implicitlyWait(0,
			// TimeUnit.MILLISECONDS);
			String Strelement = "//*[contains(text(),'" + element + "')]";
			driver.findElement(By.xpath(Strelement));
			// testReporter.Log_Pass(Element_Name + " Exist", Element_Name + "
			// is Exist");
			System.out.println("Element Exist");
			// return true;
			// driver.switchTo().defaultContent();
			// System.out.println(""+driver.getPageSource().toString());
			// System.out.println("");
			// if (driver.getPageSource().contains(element))
			// {
			waitForPageToLoad(driver);
			return true;
			// }
			// else
			// {
			// return false;
			// }

		} catch (Throwable t) {
			waitForPageToLoad(driver);
			System.out.println("not exist");
			// t.printStackTrace();
			// testReporter.Log_Fail(Element_Name + " does not Exist",
			// Element_Name + "does not Exist",driver);

			return false;

		}

	}

	public void Javascriptexecutor_forClick(WebDriver driver, String XpathObject, String ObjectName) throws Throwable {
		// waitForPageToLoad(driver);

		// waitForFrameAndSwitch(driver, frameName);
		// 47/*waitForPageToLoad(driver);
		// waitForElementVisible(driver, XpathObject,ObjectName);
		try {
			Thread.sleep(1500);
			// verifyPageLoad(driver);
			WebElement e = driver.findElement(By.xpath(XpathObject));

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click();", e);
			waitForPageToLoad(driver);
			testReporter.Log_Pass(ObjectName + " clicked", ObjectName + " Clicked");

			js = null;
			e = null;
		} catch (Throwable t) {

			testReporter.Log_Fail(ObjectName + " Not Present", ObjectName + " Not Present", driver, snapshotsPath);
			t.printStackTrace();
			// new Exception(ObjectName+" not present");
			throw new CustomException("No Element Found to perform click", t, ErrorCodes.ELEMENT_NOT_VISIBLE);

		}

	}

	public void Click(WebDriver driver, String XpathObject, String ObjectName) throws Throwable {
		// waitForPageToLoad(driver);

		// waitForFrameAndSwitch(driver, frameName);
		// 47/*waitForPageToLoad(driver);
		// waitForElementVisible(driver, XpathObject,ObjectName);
		try {
			Thread.sleep(1500);
			// verifyPageLoad(driver);
			WebElement e = driver.findElement(By.xpath(XpathObject));

			e.click();
			
			waitForPageToLoad(driver);
			testReporter.Log_Pass(ObjectName + " clicked", ObjectName + " Clicked");

			e = null;
		} catch (Throwable t) {

			testReporter.Log_Fail(ObjectName + " Not Present", ObjectName + " Not Present", driver, snapshotsPath);
			t.printStackTrace();
			// new Exception(ObjectName+" not present");
			throw new CustomException("No Element Found to perform click", t, ErrorCodes.ELEMENT_NOT_VISIBLE);

		}

	}

	public void javascriptExecutor_Setvalue(WebDriver driver, String Xpath, String data, String ElementName)
			throws InterruptedException, CustomException {

		try {

			waitForPageToLoad(driver);
			WebElement VVIN = driver.findElement(By.xpath(Xpath));
			JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);

			myExecutor.executeScript("arguments[0].value='" + data + "';", VVIN);
			testReporter.Log_Pass(ElementName + " is set with " + data, ElementName + " is set with " + data);
		} catch (Throwable t) {
			testReporter.Log_Fail(ElementName + " does not Exist", ElementName + " does not Exist", driver,
					snapshotsPath);
			t.printStackTrace();
			// throw new Exception("Element Not Present");
			throw new CustomException("Element Not Present", t, ErrorCodes.ELEMENT_NOT_VISIBLE);
		}
	}

	public void inputTextwithClick(WebDriver screenName, String ObjectxPath, String sValue, String Element_Name)
			throws Exception {
		try {
			WebElement inputText = screenName.findElement(By.xpath(ObjectxPath));
			// elementHighlight(screenName,inputText);
			inputText.click();
			inputText.clear();
			System.out.println(sValue);
			inputText.sendKeys(sValue);
			inputText.sendKeys(Keys.ENTER);
			testReporter.Log_Pass(Element_Name + " Entered", sValue + " entered in " + Element_Name);
		} catch (Throwable t) {
			testReporter.Log_Fail(Element_Name + " does not Exist", Element_Name + " does not Exist", screenName,
					snapshotsPath);
			t.printStackTrace();
			// throw new Exception("Element Not Present");
			throw new CustomException("Element Not Present", t, ErrorCodes.ELEMENT_NOT_VISIBLE);
		}

	}

	/////////////////////////////////////////////////////////////////////////

	public boolean IsEnable(WebElement element) {

		return element.isEnabled();
	}

	public boolean IsElement_Enable(WebDriver screenName, String xpath) throws Throwable {

		WebElement path = screenName.findElement(By.xpath(xpath));
		boolean flag = false;

		if (IsEnable(path)) {
			flag = true;

		} else {

			flag = false;
		}

		return flag;
	}

	public void Upload_documet_Robot(String Path) throws AWTException {

		try {
			StringSelection ss = new StringSelection(Path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			// imitate mouse events like ENTER, CTRL+C, CTRL+V
			Robot robot = new Robot();
			robot.delay(250);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(50);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ScrollToExpectedElement(WebDriver screenName, String xpath) {

		WebElement elem = screenName.findElement(By.xpath(xpath));
		JavascriptExecutor exec = (JavascriptExecutor) screenName;
		exec.executeScript("arguments[0].scrollIntoView();", elem);
		exec.executeScript("window.scrollBy(0,-100)", "");

	}

	public boolean CheckBox_Chekif_Unchecked(WebDriver screenName, String Xpath, String Element_Name) {

		boolean CheckStatus = screenName.findElement(By.xpath(Xpath)).isSelected();
		testReporter.Log_Pass(Element_Name + " Clicked", Element_Name + " Clicked");
		return CheckStatus;

	}

	public void validateData(WebDriver driver, String Xpathvalue, String expected) {
		try {

			WebElement element = driver.findElement(By.xpath(Xpathvalue));
			String compareString = element.getText();
			if (compareString.equalsIgnoreCase(expected)) {
				testReporter.Log_Pass(driver.getTitle(), compareString);
			} else {
				testReporter.Log_Fail(driver.getTitle(), compareString, driver, snapshotsPath);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyPageLoad(WebDriver driver) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 1; i <= 100; i++) {

			try {

				if (js.executeScript("return document.readyState").toString().equals("complete")) {

					System.out.println("Page is loaded!");
					break;

				} else {

					Thread.sleep(1000);
				}

			} catch (Exception e) {
			}

		}

	}

	// public String Update_Database(String Write_query,String Clm_Name){
	//
	// String data = null;
	//
	// try {
	//
	// ResultSet rs1 = DB_Connect.ExecuteQuery(Write_query,"MSSQL");
	// while (rs1.next()) {
	//
	// data=rs1.getString(Clm_Name);
	//
	// //System.out.println(rs1.getString("Contract#"));
	//
	//
	// }
	//
	// DB_Connect.closeConnection(rs1);
	//
	// } catch (NullPointerException e) {
	//
	//
	//
	//
	// e.printStackTrace();
	//
	// } catch(Exception e){
	//
	// e.printStackTrace();
	//
	//
	// }
	// return data;
	//
	//
	// }

	public void SwitchFrame(String framename, WebDriver driver) {
		try {

			switch (framename) {
			case "MSF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("menuserverFrame");
				System.out.println("Switched to MSF");
				break;

			case "PSF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("pageserverFrame");
				System.out.println("Switched to PSF");
				break;

			case "TF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("pageserverFrame");
				List<WebElement> lstf = driver.findElements(By.tagName("frame"));
				for (WebElement elem : lstf) {

					if (elem.getAttribute("id").equals("TitleFrame")) {
						driver.switchTo().frame(elem);
						break;
					}
				}

				System.out.println("Switched to TF");
				break;

			case "CF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("pageserverFrame");
				List<WebElement> lscf = driver.findElements(By.tagName("frame"));

				for (WebElement elem : lscf) {

					if (elem.getAttribute("id").equals("ContentFrame")) {
						driver.switchTo().frame(elem);
						break;
					}
				}

				// driver.switchTo().frame(lscf.get(2));
				System.out.println("Switched to CF");
				break;

			case "WF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("pageserverFrame");
				List<WebElement> lswf = driver.findElements(By.tagName("frame"));
				for (WebElement elem : lswf) {

					if (elem.getAttribute("id").equals("WaitFrame")) {
						driver.switchTo().frame(elem);
						break;
					}
				}
				System.out.println("Switched to WF");
				break;

			case "BF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("pageserverFrame");
				List<WebElement> lsbf = driver.findElements(By.tagName("frame"));
				for (WebElement elem : lsbf) {

					if (elem.getAttribute("id").equals("ButtonFrame")) {
						driver.switchTo().frame(elem);
						break;
					}
				}
				System.out.println("Switched to BF");
				break;

			case "MF":
				driver.switchTo().defaultContent();
				driver.switchTo().frame("pageserverFrame");
				List<WebElement> lsmf = driver.findElements(By.tagName("frame"));
				for (WebElement elem : lsmf) {

					if (elem.getAttribute("id").equals("ButtonFrame")) {
						driver.switchTo().frame(elem);
						break;
					}
				}
				System.out.println("Switched to BF");
				break;

			default:
				System.out.println("Frame not listed above");
			}
		} catch (Exception e) {

			System.out.println("FRAME EXCEPTION");
			e.printStackTrace();
		}

	}

	public void windowScroll(WebDriver driver, int x, int y) throws Exception {
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;

		jse1.executeScript("window.scrollBy(" + x + "," + y + ")", "");

	}

	public void windowScroll1(WebDriver driver, String Xpath) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(Xpath));
		jse.executeScript("window.scrollTo(0," + element.getLocation().y + ")");

	}

	public void mobilescroll(AndroidDriver<AndroidElement> driver, String scrollAction) throws Exception {

		TouchAction action = new TouchAction(driver);
		Dimension size = driver.manage().window().getSize();
		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;

		switch (scrollAction) {

		case "Down":
			startY = (int) (size.height * 0.70);
			endY = (int) (size.height * 0.30);
			startX = (size.width / 2);
			action.press(PointOption.point(startX, startY))
					.waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
					.moveTo(PointOption.point(startX, endY)).release().perform();

			break;
		case "Up":
			endY = (int) (size.height * 0.70);
			startY = (int) (size.height * 0.30);
			startX = (size.width / 2);
			action.press(PointOption.point(startX, startY))
					.waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
					.moveTo(PointOption.point(startX, endY)).release().perform();

			break;

		default:
			break;
		}

	}

	public static String getElementText(String locatorType, String locatorValue, WebDriver driver)
			throws CustomException {
		String text = null;
		try {
			By locator;
			locator = locatorValue(locatorType, locatorValue);
			WebElement element = driver.findElement(locator);

			text = element.getText();
			System.out.println("getText is performed from element");
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to perform getText" + e + "\n");
			throw new CustomException("Element Not Present", e, ErrorCodes.ELEMENT_NOT_VISIBLE);
		}
		return text;

	}

	private static By locatorValue(String locatorTpye, String value) {
		By by;
		switch (locatorTpye.toLowerCase()) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "classname":
			by = By.className(value);
			break;
		case "tagname":
			by = By.tagName(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linktext":
			by = By.linkText(value);
			break;
		case "partiallinktext":
			by = By.partialLinkText(value);
			break;

		default:
			by = null;
			break;
		}
		return by;
	}

	public Boolean isElementPresent(WebDriver driver, By by) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	// doNonMedicalDocsUpload


	public void doDocumentUpload(AndroidDriver<AndroidElement> driver)
			throws Throwable {

		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {

			System.out.println(contextName);
			if (contextName.contains("NATIVE_APP")) {
				driver.context(contextName);

				break;
			}
		}
		Thread.sleep(3000);


		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Files')]")));

		System.out.println("Step 1");
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Files')]")).click();

		// driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
		//
		// // Resent
		//
		// driver.findElement(By.xpath("//android.widget.TextView[@text='Recent'
		// and @index='1']")).click();//
		// xpath("//android.widget.TextView[@text='Recent' and @index='1']")
		Thread.sleep(3000);
		System.out.println("Step 2");

//		driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'MAPP.jpg')]")).click(); // Screenshot_20200408_130802.jpg
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Screenshot_20200408_130802.jpg')]")));

//		driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'MAPP.jpg')]")).click(); // Screenshot_20200408_130802.jpg
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Screenshot_20200408_130802.jpg')]")).click(); // Screenshot_20200408_130802.jpg

		Thread.sleep(3000);

		Set<String> contextNames1 = driver.getContextHandles();
		for (String contextName : contextNames1) {

			System.out.println(contextName);
			if (contextName.contains("CHROMIUM")) {
				driver.context(contextName);
				System.out.println("contextName " + contextName);
				break;
			}
		}

	}
	// Doc_IdentityProof
	

	public void doIdentityProofDocsUpload(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {

		loadingText(driver);

		if(!ObjectName.equalsIgnoreCase("NA"))
		{
		

		AndroidElement selectDropBox = driver.findElement(By.xpath("//select[@id='documentType']"));
		Select select = new Select(selectDropBox);
		select.selectByVisibleText(ObjectName);
		}
		

//		WebElement e118 = driver.findElement(By.xpath("//div[contains(.,' Document Status - DOCUMENT UPLOAD PENDING ')]/child::*/div[1]/a/label"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", e118); // //*[@id="cat_1"]/div[5]/div[1]/a/label
		ScrollToExpectedElement(driver, "//div[contains(.,' Document Status - DOCUMENT UPLOAD PENDING ')]/child::*/div[1]/a/input");
		
		driver.findElement(By.xpath("//div[contains(.,' Document Status - DOCUMENT UPLOAD PENDING ')]/child::*/div[1]/a/input")).sendKeys("//sdcard//file.jpg");;
		
		loadingText(driver);

//		Thread.sleep(4000);
//		doDocumentUpload(driver);
		
		driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();;


		for (int i = 0; i <= 8; i = i++) {
			  System.out.println(i);

				if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

					System.out.println("Element is Visible");
					Thread.sleep(10000);
					}

					else{
						
					System.out.println("Element is InVisible");
					break;
					
					}
		}
		
		for (int i = 0; i <= 4; i = i++) {
			  System.out.println(i);
			
		try {
			Thread.sleep(5000);

			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='WLdialogBody']/button")));


			WebElement e1 = driver.findElement(By.xpath("//*[@id='WLdialogBody']/button"));

			JavascriptExecutor js5 = (JavascriptExecutor) driver;

			js5.executeScript("arguments[0].click();", e1);
			
			Thread.sleep(1000);


			
			if( driver.findElement(By.xpath("//*[@id='uploadImg']/a")).isDisplayed()){

				System.out.println("Element is Visible");
				ScrollToExpectedElement(driver, "//*[@id='uploadImg']/a");

				driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();
				
				}

				else{
					
				System.out.println("Element is InVisible");
				break;
				
				}
			
		} catch (NoSuchElementException e1) {
			
			break;
		}
		}
		
	}

	public void doStdAgeProofProofDocsUpload(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {
		
		loadingText(driver);

		if(!ObjectName.equalsIgnoreCase("NA"))
		{
		
		
		AndroidElement selectDropBox = driver.findElement(By.xpath("//select[@id='documentType']"));
		Select select = new Select(selectDropBox);
		select.selectByVisibleText(ObjectName);
		}
		

//		WebElement e188 = driver.findElement(By.xpath("//div[contains(.,' Document Status - DOCUMENT UPLOAD IN PROGRESS ')]/child::*/div[1]/a/label"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", e188 );
		ScrollToExpectedElement(driver, "//div[contains(.,' Document Status - DOCUMENT UPLOAD IN PROGRESS ')]/child::*/div[1]/a/input");
		
		driver.findElement(By.xpath("//div[contains(.,' Document Status - DOCUMENT UPLOAD IN PROGRESS ')]/child::*/div[1]/a/input")).sendKeys("//sdcard//file.jpg");;
		
		loadingText(driver);

//		Thread.sleep(4000);
//		doDocumentUpload(driver);
		
		driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();;


		
		for (int i = 0; i <= 4; i = i++) {
			  System.out.println(i);
			
		try {

			for (int i1 = 0; i1 <= 8; i1 = i++) {
				  System.out.println(i);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("Element is Visible");
						Thread.sleep(10000);
						}

						else{
							
						System.out.println("Element is InVisible");
						break;
						
						}
			}
			Thread.sleep(5000);

			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='WLdialogBody']/button")));


			WebElement e1 = driver.findElement(By.xpath("//*[@id='WLdialogBody']/button"));

			JavascriptExecutor js44 = (JavascriptExecutor) driver;

			js44.executeScript("arguments[0].click();", e1);
			
			Thread.sleep(1000);


			
			if( driver.findElement(By.xpath("//*[@id='uploadImg']/a")).isDisplayed()){

				System.out.println("Element is Visible");
				ScrollToExpectedElement(driver, "//*[@id='uploadImg']/a");

				driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();
				
				}

				else{
					
				System.out.println("Element is InVisible");
				break;
				
				}
			
		} catch (NoSuchElementException e1) {
			
			break;
		}
		}
		
	}

	

	public void doAddressProofDocsUpload(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {
		
		loadingText(driver);

		if(!XpathObject.equalsIgnoreCase("NA"))
		{
		
		
		AndroidElement selectDropBox = driver.findElement(By.xpath("//select[@id='documentType']"));
		Select select = new Select(selectDropBox);
		select.selectByVisibleText(ObjectName);
		}
		

//		WebElement e1898 = driver.findElement(By.xpath("//div[contains(.,' Document Status - DOCUMENT UPLOAD PENDING ')]/child::*/div[1]/a/label"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", e1898 );
		
		ScrollToExpectedElement(driver, "//div[contains(.,' Document Status - DOCUMENT UPLOAD PENDING ')]/child::*/div[1]/a/input");
		
		driver.findElement(By.xpath("//div[contains(.,' Document Status - DOCUMENT UPLOAD PENDING ')]/child::*/div[1]/a/input")).sendKeys("//sdcard//file.jpg");
		
//		Thread.sleep(4000);
//		doDocumentUpload(driver);
		loadingText(driver);

		driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();;


		
		for (int i = 0; i <= 4; i = i++) {
			  System.out.println(i);
			
		try {

			for (int i1 = 0; i1 <= 8; i1 = i++) {
				  System.out.println(i);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("Element is Visible");
						Thread.sleep(10000);
						}

						else{
							
						System.out.println("Element is InVisible");
						break;
						
						}
			}
			Thread.sleep(5000);

			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='WLdialogBody']/button")));


			WebElement e1 = driver.findElement(By.xpath("//*[@id='WLdialogBody']/button"));

			JavascriptExecutor js27 = (JavascriptExecutor) driver;

			js27.executeScript("arguments[0].click();", e1);
			
			Thread.sleep(1000);

			
			if( driver.findElement(By.xpath("//*[@id='uploadImg']/a")).isDisplayed()){
				
				System.out.println("Element is Visible");
				ScrollToExpectedElement(driver, "//*[@id='uploadImg']/a");

				driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();
				
				}

				else{
					
				System.out.println("Element is InVisible");
				break;
				
				}
			
		} catch (NoSuchElementException e1) {
			
			break;
		}
		}
		
	}

	public void doPanCardDocsUpload(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {
		
		

//		WebElement e18 = driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/label"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", e18);
		
		loadingText(driver);
		ScrollToExpectedElement(driver, "//div[contains(.,'PAN Card')]/child::*/div[1]/a/input");

		driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/input")).sendKeys("//sdcard//file.jpg");
		
		loadingText(driver);

//		Thread.sleep(4000);
//		doDocumentUpload(driver);
		
		driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();;


		
		for (int i = 0; i <= 4; i = i++) {
			  System.out.println(i);
			
		try {

			for (int i1 = 0; i1 <= 8; i1 = i++) {
				  System.out.println(i);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("Element is Visible");
						Thread.sleep(10000);
						}

						else{
							
						System.out.println("Element is InVisible");
						break;
						
						}
			}
			Thread.sleep(5000);

			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='WLdialogBody']/button")));


			WebElement e1 = driver.findElement(By.xpath("//*[@id='WLdialogBody']/button"));

			JavascriptExecutor js6 = (JavascriptExecutor) driver;

			js6.executeScript("arguments[0].click();", e1);
			
			Thread.sleep(1000);


			
			if( driver.findElement(By.xpath("//*[@id='uploadImg']/a")).isDisplayed()){

				System.out.println("Element is Visible");
				ScrollToExpectedElement(driver, "//*[@id='uploadImg']/a");

				driver.findElement(By.xpath("//*[@id='uploadImg']/a")).click();
				
				}

				else{
					
				System.out.println("Element is InVisible");
				break;
				
				}
			
		} catch (NoSuchElementException e1) {
			
			break;
		}
		}
		
	}
	
	public void doNonMedicalDocsUpload(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {

		try {
			
			
			Thread.sleep(1500);
			System.out.println("Step ");

			// //a[contains(.,''Non Medical '')]
			loadingText(driver);
			
			ScrollToExpectedElement(driver, "//a[contains(.,'Non Medical ')]");

			driver.findElement(By.xpath("//a[contains(.,'Non Medical ')]")).click();
			
			Thread.sleep(1000);
			loadingText(driver);

			ScrollToExpectedElement(driver, XpathObject);
			
			
		    driver.findElement(By.xpath(XpathObject)).sendKeys("//sdcard//file.jpg");
			
			
//			WebElement e = driver.findElement(By.xpath(XpathObject));
//			e.click();
//
//			Set<String> contextNames = driver.getContextHandles();
//			for (String contextName : contextNames) {
//
//				System.out.println(contextName);
//				if (contextName.contains("NATIVE_APP")) {
//					driver.context(contextName);
//
//					break;
//				}
//			}
//			Thread.sleep(3000);
//			
//			
//			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Files')]")));
//
//			System.out.println("Step 1");
//			
//			driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Files')]")).click();
//
//			// driver.findElement(By.xpath("//android.widget.ImageButton[@index='0']")).click();
//			//
//			// // Resent
//			//
//			// driver.findElement(By.xpath("//android.widget.TextView[@text='Recent'
//			// and @index='1']")).click();//
//			// xpath("//android.widget.TextView[@text='Recent' and @index='1']")
//			Thread.sleep(3000);
//			System.out.println("Step 2");
//
////			driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'MAPP.jpg')]")).click(); // Screenshot_20200408_130802.jpg
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Screenshot_20200408_130802.jpg')]")));
//
//			driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Screenshot_20200408_130802.jpg')]")).click(); // Screenshot_20200408_130802.jpg
//
//			Thread.sleep(3000);
//
//			Set<String> contextNames1 = driver.getContextHandles();
//			for (String contextName : contextNames1) {
//
//				System.out.println(contextName);
//				if (contextName.contains("CHROMIUM")) {
//					driver.context(contextName);
//					System.out.println("contextName " + contextName);
//					break;
//				}
//			}

			Thread.sleep(3000);
			System.out.println("Step 3");

			ScrollToExpectedElement(driver, "//a[@title='Save & Continue' or @title='Confirm']");

			driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']")).click();

			System.out.println("Step 4");

			loadingText(driver);

//			for (int i = 0; i <= 8; i = i++) {
//				  System.out.println(i);
//
//					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){
//
//						System.out.println("Loading Element is Visible");
//						Thread.sleep(5000);
//						}
//
//						else{
//							
//						System.out.println("Loading Element is InVisible");
//						Thread.sleep(3000);
//						break;
//						
//						}
//			}
			
			for (int i = 0; i <= 4; i = i++) {
				  System.out.println(i);
				
			try {
				Thread.sleep(5000);

				WebDriverWait wait1 = new WebDriverWait(driver, 60);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='WLdialogBody']/button")));


				WebElement e1 = driver.findElement(By.xpath("//*[@id='WLdialogBody']/button"));

				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript("arguments[0].click();", e1);
				
				
//				driver.findElement(By.xpath("//*[@id='WLdialogBody']/button")).click();
				Thread.sleep(1000);


				
				if( driver.findElement(By.xpath("//*[@id='cat_10']/div[1]")).isDisplayed()){

					System.out.println("Element is Visible");
					ScrollToExpectedElement(driver, "//a[@title='Save & Continue' or @title='Confirm']");

					driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']")).click();

//					break;
					
					}

					else{
						
					System.out.println("Element is InVisible");
					break;
					
					}
//				if(driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']"))!= null){
//
//					System.out.println("Element is Present");
//					driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']")).click();
//					
//					}
//
//					else{
//
//					System.out.println("Element is Absent"); 
//					break;
//					}
//				driver.findElement(By.xpath("//a[@title='Save & Continue' or @title='Confirm']")).click();

			} catch (NoSuchElementException e1) {
				
				break;
			}
			}

			// JavascriptExecutor js = (JavascriptExecutor)driver;
			//
			// js.executeScript("arguments[0].click();", e);
			// waitForPageToLoad(driver);
			// testReporter.Log_Pass(ObjectName+" clicked", ObjectName+"
			// Clicked");
			// js=null;

//			e = null;
		} catch (Throwable t) {
			t.printStackTrace();
			testReporter.Log_Fail(ObjectName + " Not Present", ObjectName + " Not Present", driver, snapshotsPath);
			t.printStackTrace();
		}

	}


	public void loadingText(WebDriver driver)
			throws Throwable {
		
		try {
			for (int i = 0; i <= 30; i = i++) {
				  System.out.println(i);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("Element is Visible");
						Thread.sleep(8000);
						}

						else{
						System.out.println("Element is InVisible");
						Thread.sleep(2000);
						break;
						
						}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void Test123(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {

	    File file = new File("C:\\Users\\Admin\\Downloads\\1.jpg");

//		driver.pushFile("/sdcard/file.jpg",file);//_file("/sdcard/file.txt","c:/file.txt")
		
		Thread.sleep(1000);
		loadingText(driver);
	    driver.findElement(By.xpath("//div[@id='notCSEUSER']/div/div[4]/div/a/div")).click();
	    driver.findElement(By.cssSelector("label > input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("label > input[type=\"text\"]")).sendKeys("EA10575538");  // EA10575538  ea105696
	    driver.findElement(By.xpath("//tbody[@id='applicationListTableBody']/tr/td/a/u")).click();
	}
	

	public void Test345(WebDriver driver, String XpathObject, String ObjectName)
			throws Throwable {
		
//		try {
//			WebElement web = driver.findElement(By.xpath("//*[@id='rider-checkbox']/parent::*/label"));
//			
//			web.click();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

		try {
			int i = 0;
			

			  JavascriptExecutor js = (JavascriptExecutor)driver;
			  js.executeScript("document.getElementById('rider-checkbox').click();");
			  
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("document.getElementById('rider-checkbox').type = 'button';");

			WebElement web = driver.findElement(By.xpath("//*[@id='rider-checkbox']/parent::*/label"));
			
			web.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		Thread.sleep(1000);
		loadingText(driver);
		// 
////		String str = "data:image/jpeg;base64,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";
//		String str = "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";
	    driver.findElement(By.xpath("//a[contains(.,'PAN Card')]")).click();
////		driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/label")).sendKeys(str);
//	    File file = new File(str);
	    
//	    yourElement.sendKeys(file.getAbsolutePath());
		
		ScrollToExpectedElement(driver, "//div[contains(.,'PAN Card')]/child::*/div[1]/a/input");
		
//	    driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/input")).sendKeys("/sdcard/file.jpg");
	    driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/input")).sendKeys("//sdcard//file.jpg");

		WebElement web1 = driver.findElement(By.xpath("//div[contains(.,'PAN Card')]/child::*/div[1]/a/input"));
//		web1.sendKeys(str);
		
		JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
		jsExecutor1.executeScript("$(arguments[0]).change();", web1);
		
		
	}
	
	
	public void Keep_the_session_alive(AndroidDriver<AndroidElement> driver)
			throws Throwable {
		
		for (int i = 0; i < 3; i++) {
			System.out.println("Keep_the_session_alive");
		    driver.getOrientation(); // execute some command to keep the session alive
		    Thread.sleep(59000); // wake up before session expired
		}
		
	}
	
	// captcha

	public void captcha(WebDriver driver, String xpath)
			throws Throwable {

	    Scanner cap = new Scanner(System.in);
	    System.out.println("Please enter your captcha value: -");
	    String cap1 = cap.nextLine();
	    System.out.println(cap1);
	    driver.findElement(By.xpath(xpath)).sendKeys(cap1);
		
	}
	
	//Premium_Payable
	public void Premium_Payable(WebDriver driver, String xpath, String datasheetValue)
			throws Throwable {
		System.out.println("Premium_Payable");

		
		WebElement web1 = driver.findElement(By.xpath(xpath));
		
		((JavascriptExecutor)driver).executeScript("$(arguments[0]).val('.01').change()", web1);

			for (int i2 = 0; i2 <= 2; i2++) {

			web1.sendKeys(Keys.RIGHT);
			}
			
				  Thread.sleep(200);
				  
				  
				  try {
//					  driver.findElement(By.xpath("//*[@id='WLdialogBody']/button")).isDisplayed();
//						 driver.findElement(By.xpath("//*[@id='WLdialogBody']/button")).click();

				} catch (Exception e) {
					System.out.println("Close is InVisible");

				}
				  

					for (int i3 = 0; i3 <= 6; i3 = i3++) {
						  System.out.println(i3);

							if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

								System.out.println("loadingText is Visible");
								Thread.sleep(250);
								}

								else{
									
								System.out.println("loadingText is InVisible");
								break;
								
								}
					}
		
		int inum = Integer.parseInt(datasheetValue);

		for (int i = 0; i <= inum-4; i++) {

			web1.sendKeys(Keys.RIGHT);


			for (int i3 = 0; i3 <= 20; i3 = i3++) {
				  System.out.println(i3);
					Thread.sleep(100);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("loadingText is Visible");
						Thread.sleep(50);
						}

						else{
							
						System.out.println("loadingText is InVisible");
						break;
						
						}
			}
			
		}
		}
		
	
	// Policy_Term

	public void Policy_Term(WebDriver driver, String xpath, String datasheetValue)
			throws Throwable {
		System.out.println("Policy_Term");

		
		WebElement web1 = driver.findElement(By.xpath(xpath));
		
		((JavascriptExecutor)driver).executeScript("$(arguments[0]).val('.01').change()", web1);

		
				  Thread.sleep(200);
				  
		
		int inum = Integer.parseInt(datasheetValue);

		for (int i = 0; i <= inum; i++) {

			web1.sendKeys(Keys.RIGHT);


			for (int i3 = 0; i3 <= 6; i3 = i3++) {
				  System.out.println(i3);
					Thread.sleep(100);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("loadingText is Visible");
						Thread.sleep(50);
						}

						else{
							
						System.out.println("loadingText is InVisible");
						break;
						
						}
			}
			
		}
		}
		
	

	public void Sum_Assured(WebDriver driver, String xpath, String datasheetValue)
			throws Throwable {
		System.out.println("Sum_Assured1");

		
		WebElement web1 = driver.findElement(By.xpath(xpath));
		
		((JavascriptExecutor)driver).executeScript("$(arguments[0]).val('.01').change()", web1);

		
				  Thread.sleep(200);
				  
		
		int inum = Integer.parseInt(datasheetValue);

		for (int i = 0; i <= inum; i++) {

			web1.sendKeys(Keys.RIGHT);


			for (int i3 = 0; i3 <= 6; i3 = i3++) {
				  System.out.println(i3);
					Thread.sleep(100);

					if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

						System.out.println("loadingText is Visible");
						Thread.sleep(50);
						}

						else{
							
						System.out.println("loadingText is InVisible");
						break;
						
						}
			}
			
		}
		}
		
	
	public void Loding_Page(WebDriver driver)
			throws Throwable {
		
		for (int i = 0; i <= 15; i = i++) {
			  System.out.println(i);

				if( driver.findElement(By.xpath("//*[@id='loadingText']")).isDisplayed()){

					System.out.println("loadingText is Visible");
					Thread.sleep(4000);
					}

					else{
						
					System.out.println("loadingText is InVisible");
					break;
					
					}
		}
		
	}
}