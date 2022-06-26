package com.bsli.ibm.utility;

import java.lang.reflect.Method;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bsli.ibm.bo.DataSheetDto;
import com.bsli.ibm.utility.reports.IF.ITestReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;

public class MappAutomationWrapper {

	private String tc_id;
	private String sheetName;
	private WebDriver driver;
	private HashMap<String, String> testData;
	private String datasheetPath;
	private String dataSheetResultPath;
	private String reportPath;
	private String snapshotPath;
	private FetchLocators fetchLocators;
	private List<DataSheetDto> DataSheetDtoList;
	private ITestReporter testReporter;
	private WrapperMethods method;
	private DataSheetDto datasheet_var;
	private String amount;

	private ConnectionManager conMgr;
	private String newURL;

	private MappAutomationWrapper() {
		// TODO Auto-generated constructor stub
	}

	public MappAutomationWrapper(String tc_id, String sheetName, WebDriver driver,
			HashMap<String, String> configMap, HashMap<String, String> testData, String datasheetPath,
			String dataSheetResultPath, String reportPath, String snapshotPath, ITestReporter testReporter)
			throws Exception {

		this.tc_id = tc_id;
		this.driver = driver;
		this.testData = testData;
		this.sheetName = sheetName;
		this.datasheetPath = datasheetPath;
		this.dataSheetResultPath = dataSheetResultPath;
		this.reportPath = reportPath;
		this.snapshotPath = snapshotPath;
		this.testReporter = testReporter;

		conMgr = new ConnectionManager(configMap.get("DB_SQL_URL"), configMap.get("DB_SQL_DRV"),
				configMap.get("DB_SQL_USR"), configMap.get("DB_SQL_PWD"));
		method = new WrapperMethods(datasheetPath, sheetName, snapshotPath, reportPath, testReporter);

		fetchLocators = new FetchLocators(conMgr.getConnection());

	}

	public void execute(String sql, HashMap<String, String> dataMap, Object callingClass) throws Throwable {

		// testReporter.startTest(tc_id);

		try {

			DataSheetDtoList = fetchLocators.GetTestCases(sql);

			for (DataSheetDto datasheet : DataSheetDtoList) {
//				Thread.sleep(1000);

				System.out.println(">>" + datasheet.getDATASHEET_KEY());

				String datasheetValue = "";

				if (datasheet.getDATASHEET_KEY().equalsIgnoreCase("Doc_NonMedicalProof")) {

					System.out.println("Debug Start Here");
					// driver.context("WEBVIEW_com.bsli.eapp");
					/*
					 * driver.findElement(By.xpath(
					 * "//*[@id='menuTabBar']/div[2]/div[1]")).click(); String
					 * s=driver.findElement(By.xpath(
					 * "//div[@class='clip-content']/b")).getText();
					 * System.out.println(s);
					 */
				}

				System.out.println("Value is: ----->" + dataMap.get(datasheet.getLOCATOR_PARAM1()));
				System.out.println("Value is: ----->" + testData.get(datasheet.getDATASHEET_KEY()));

				datasheet_var = datasheet;
				datasheetValue = "".equals(datasheet.getDATASHEET_KEY()) ? dataMap.get(datasheet.getLOCATOR_PARAM1())
						: testData.get(datasheet.getDATASHEET_KEY()).toString();

				datasheetValue = "".equals(datasheetValue) ? dataMap.get(datasheet.getLOCATOR_PARAM2())
						: datasheetValue;

				if (!("NA".equalsIgnoreCase(datasheetValue))) {
					doEvents(datasheet, datasheetValue, driver, tc_id, dataMap, testData, callingClass);
					System.out.println("Y");
				} else {
					// logger.info("Not eligible for Action " +
					// datasheet.getDATASHEET_KEY());
				}

			}

		} catch (Exception e) {
			// e.printStackTrace();

			if (null != datasheet_var.getDATASHEET_KEY() && !("".equals(datasheet_var.getDATASHEET_KEY())))

			{
				// testReporter.Log_Fail("Failed
				// Events",datasheet_var.getDATASHEET_KEY()+" Failed while
				// processing Events :Unknown Exception check trace logs");
				testReporter.Log_Fail("Failed Events",
						datasheet_var.getDATASHEET_KEY()
								+ " Failed while processing Events :Unknown Exception check trace logs",
						driver, snapshotPath);
			} else

			{
				// testReporter.Log_Fail("Failed
				// Events",datasheet_var.getREMARKS()+" Failed while processing
				// Events :Unknown Exception check trace logs");
				testReporter.Log_Fail("Failed Events",
						datasheet_var.getREMARKS()
								+ " Failed while processing Events :Unknown Exception check trace logs",
						driver, snapshotPath);
			}
			throw new Exception();

		} finally {
			fetchLocators.destroy();
		}

	}

	public void doEvents(DataSheetDto datasheet, String datasheetValue, WebDriver driver,
			String tc_id, HashMap<String, String> dataMap, HashMap<String, String> testData, Object callingClass)
			throws Throwable {

		System.out.println("getLOCATOR_TYP " + datasheet.getLOCATOR_TYP());
		System.out.println("getLOCATOR_KEY " + datasheet.getLOCATOR_KEY());
		System.out.println("getLOCATOR_KEY " + datasheet.getEVENT_SEQ());
		System.out.println("datasheetValue " + datasheetValue);
		Object value = null;

		try {
			switch (datasheet.getEVENT_CODE()) {
			
			case "Loding_Page":
				method.Loding_Page(driver);

				break;
				
			case "CLICK_ON_BUTTON":
				method.Clickbtn2(driver, datasheet.getLOCATOR_KEY(), datasheet.getREMARKS());
				
				break;

			case "ENTER_TEXT":

				method.inputText(driver, datasheet.getLOCATOR_KEY(), datasheetValue, datasheet.getDATASHEET_KEY());

				break;

			case "SWITCH_CONTEXT":

//				driver.context(datasheet.getLOCATOR_PARAM1());

				break;

			case "CUSTOM_METHOD":

				// datasheet.getLOCATOR_PARAM1() will be Custom Method Name of
				// Calling class
				// datasheet.getLOCATOR_PARAM2() we are referring as data sheet
				// column name instead of getDATASHEET_KEY,
				// this column will be used to store Mulitple Datasheet column
				// name.

				// if(null!=datasheet.getDATASHEET_KEY() ||
				// !"".equals(datasheet.getDATASHEET_KEY()))
				boolean flag;

				if (null != datasheet.getLOCATOR_PARAM2() && !"".equals(datasheet.getLOCATOR_PARAM2())) {

					Method customMethod = callingClass.getClass().getMethod(datasheet.getLOCATOR_PARAM1(),
							java.lang.String[].class);
					value = customMethod.invoke(callingClass,
							new Object[] { datasheet.getLOCATOR_PARAM2().split("~") });
					if (value != null) {
						flag = (boolean) value;
						if (flag == false)
							throw new Exception();
					}
				} else {
					Method customMethod = callingClass.getClass().getMethod(datasheet.getLOCATOR_PARAM1());
					value = customMethod.invoke(callingClass);
					if (value != null) {
						flag = (boolean) value;
						if (flag == false)
							throw new Exception();
					}
				}

				break;

			case "WRITEEXCEL":
				// ReadExcelByMapping.writeExcel(datasheetPath,sheetName,Integer.parseInt(TC_ID),
				// datasheet.getLOCATOR_PARAM1(),
				// dataMap.get(datasheet.getLOCATOR_PARAM1()));
				String excelPath = "";
				if (datasheet.getLOCATOR_PARAM2() == null || datasheet.getLOCATOR_PARAM2().equals("")) {
					excelPath = datasheetPath;
				} else if (datasheet.getLOCATOR_PARAM2().equals("datasheetResultPath")) {
					excelPath = dataSheetResultPath;
				}
				ReadExcelByMapping.writeExcel1(excelPath, sheetName, tc_id, datasheet.getLOCATOR_PARAM1(),
						dataMap.get(datasheet.getLOCATOR_PARAM1()));
				break;

			case "SCROLLING":
//				driver.context("NATIVE_APP");
//				method.mobilescroll(driver, datasheet.getLOCATOR_PARAM1());

				break;

			case "GETELEMENTTEXT":
				dataMap.put(datasheet.getLOCATOR_PARAM1(),
						WrapperMethods.getElementText(datasheet.getLOCATOR_TYP(), datasheet.getLOCATOR_KEY(), driver));
				break;

			// To Write Cancer and Critishield Application number in datasheet -
			// by Varsha

			case "GETELEMENTTEXTADDON":
				if (datasheet.getDATASHEET_KEY().equalsIgnoreCase("YES")) {
					dataMap.put(datasheet.getLOCATOR_PARAM1(), WrapperMethods.getElementText(datasheet.getLOCATOR_TYP(),
							datasheet.getLOCATOR_KEY(), driver));
				}
				break;

			case "PAGE_BACK":

				// driver.pressKey(new
				// KeyEvent(AndroidKey.BACK).withMetaModifier(KeyEventMetaModifier.SHIFT_ON));
//				try {
//					driver.pressKey(new KeyEvent(AndroidKey.BACK));
//				} catch (Exception e) {
//					driver.context("NATIVE_APP");
//					// driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate
//					// up' or @content-desc='Save and Close']")).click();
//					driver.findElement(By.xpath("//*[@content-desc='Navigate up' or @content-desc='Save and Close']"))
//							.click();
//					driver.context("WEBVIEW_com.bsli.eapp");
//				}

				break;

			case "ENTER_TEXT_VALUE":
				method.javascriptExecutor_Setvalue(driver,
						datasheet.getLOCATOR_KEY().replaceAll("~VALUE~", testData.get(datasheet.getLOCATOR_PARAM1())),
						datasheetValue, datasheet.getDATASHEET_KEY());
				break;

			case "METHOD.SET_VALUE_BY_XPATH_SCRIPT":

				method.javascriptExecutor_Setvalue(driver, datasheet.getLOCATOR_KEY(), datasheetValue,
						datasheet.getDATASHEET_KEY());

				break;
				
				// selectDropBoxValue

			case "METHOD.SelectDropBoxValue":

				method.selectDropBoxValue(driver, datasheet.getLOCATOR_KEY(), datasheetValue,
						datasheet.getDATASHEET_KEY());
				
				break;
				
			case "SELECTDROPDOWNACTION":
				method.selectDropBoxByVisibleText(driver, datasheet.getLOCATOR_KEY(), datasheetValue,
						datasheet.getDATASHEET_KEY());

				break;

			case "SELECTDROPDOWNACTION_VALUE":

				method.selectDropBoxByVisibleText(driver,
						datasheet.getLOCATOR_KEY().replaceAll("~VALUE~", testData.get(datasheet.getLOCATOR_PARAM2())),
						datasheetValue, datasheet.getDATASHEET_KEY());
				// ActionUtils.selectDropDownAction(datasheet.getLOCATOR_TYP(),datasheet.getLOCATOR_KEY().replaceAll("~VALUE~",testData.get(datasheet.getLOCATOR_PARAM2())),
				// datasheet.getLOCATOR_PARAM1(), datasheetValue, driver);
				break;

			case "METHOD.CLICKBUTTONBYXPATH":

				method.Javascriptexecutor_forClick(driver, datasheet.getLOCATOR_KEY(), datasheet.getREMARKS());

				break;

			case "METHOD.CLICKMULTIPLECHECKS":
				// method.Javascriptexecutor_forClick(driver, "//input[@id='" +
				// datasheetValue + "']", datasheetValue);
				if (!dataMap.get(datasheet.getLOCATOR_PARAM1()).equalsIgnoreCase("NA")) {

					method.Javascriptexecutor_forClick(driver, datasheet.getLOCATOR_KEY().replaceAll("~VALUE~",
							dataMap.get(datasheet.getLOCATOR_PARAM1())), datasheet.getREMARKS());

				}
				break;

			case "METHOD.CLICKRADIOBUTTONBYXPATH":
				// method.Javascriptexecutor_forClick(driver, "//input[@id='" +
				// datasheetValue + "']", datasheetValue);

				method.Javascriptexecutor_forClick(driver,
						datasheet.getLOCATOR_KEY().replaceAll("~VALUE~", datasheetValue), datasheet.getREMARKS());

				break;

			case "METHOD.CLICK":
				// method.Javascriptexecutor_forClick(driver, "//input[@id='" +
				// datasheetValue + "']", datasheetValue);

				method.Click(driver,
						datasheet.getLOCATOR_KEY().replaceAll("~VALUE~", datasheetValue), datasheet.getREMARKS());

				break;

			case "WAIT":
				Thread.sleep(Integer.parseInt(datasheet.getLOCATOR_PARAM1()));
				break;

			case "Alert_POP_UP":

				WebDriverWait wait = new WebDriverWait(driver, 20);
				Alert alertMessage = wait.until(ExpectedConditions.alertIsPresent());

				if (datasheet.getLOCATOR_PARAM1().equalsIgnoreCase("Accept")) {
					alertMessage.accept();
				} else if (datasheet.getLOCATOR_PARAM1().equalsIgnoreCase("Dismiss"))
					alertMessage.dismiss();

				break;

			case "METHOD.WAIT_PAGE_LOAD":

				method.waitForPageToLoad(driver);
				
				break;

				// JavascriptExecutorClick
				

			case "JavascriptExecutorClickID":

				method.JavascriptExecutorClick(driver, datasheet.getLOCATOR_KEY());
				
				break;

			case "METHOD.WAIT_ELEMENT_VISIBLE":

				if (null != datasheet.getDATASHEET_KEY() && !("".equals(datasheet.getDATASHEET_KEY())))

				{
					method.waitForElementVisible(driver, datasheet.getLOCATOR_KEY(), datasheet.getDATASHEET_KEY());
				} else

					method.waitForElementVisible(driver, datasheet.getLOCATOR_KEY(), datasheet.getREMARKS());

				break;

			case "KEYOPERATION":

				method.inputText_keyoperaation(driver, datasheet.getLOCATOR_KEY(), datasheet.getLOCATOR_PARAM1());

				break;

			case "WAIT_For_Element_Invisible":

				method.waitForElementNotPresent(driver, datasheet.getLOCATOR_KEY());
				do {
					Thread.sleep(1000);
					System.out.println(driver.findElement(By.xpath("//div[@id='loadingText']")).getText().isEmpty());
				} while (!driver.findElement(By.xpath("//div[@id='loadingText']")).getText().isEmpty());

				break;

			case "METHOD.IS_Element_Present":

				method.isElementPresent(driver, By.xpath("datasheet.getLOCATOR_KEY()"));

				break;

			case "METHOD.CopyAmount_text": // This method was added by Varsha as
											// part of CR990 to copy amount for
											// winback mode DM channel

				if (datasheet.getDATASHEET_KEY().equalsIgnoreCase("click")) {
					String amount = driver.findElement(By.xpath("datasheet.getLOCATOR_KEY()")).getText();
				}
				break;

			case "METHOD.PasteAmount_text":
				if (datasheet.getDATASHEET_KEY().equalsIgnoreCase("click")) {
					driver.findElement(By.xpath("datasheet.getLOCATOR_KEY()")).sendKeys(amount);
				}
				break;

			case "BUTTON_DROPDOWN":

				break;

			case "Andorid_Back":
				// driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

				break;
				
			case "Beake_Point":
				Thread.sleep(1000);
				System.out.println("Beake_Point");

				break;

			case "CUSTOM_METHOD.Doc_NonMedicalProof":

				method.doNonMedicalDocsUpload(driver, datasheet.getLOCATOR_KEY(), datasheet.getREMARKS());
				
				break;
				
			case "CUSTOM_METHOD.doIdentityProofDocsUpload":

				method.doIdentityProofDocsUpload(driver, datasheet.getLOCATOR_KEY(), datasheetValue);

				break;

			case "CUSTOM_METHOD.doStdAgeProofProofDocsUpload":

				method.doStdAgeProofProofDocsUpload(driver, datasheet.getLOCATOR_KEY(), datasheetValue);

				break;

			case "CUSTOM_METHOD.doAddressProofDocsUpload":

				method.doAddressProofDocsUpload(driver, datasheet.getLOCATOR_KEY(), datasheetValue);

				break;

			case "CUSTOM_METHOD.doPanCardDocsUpload":

				method.doPanCardDocsUpload(driver, datasheet.getLOCATOR_KEY(), datasheetValue);

				break;
				
			case "Test123":
				
				method.Test123(driver, datasheet.getLOCATOR_KEY(), datasheet.getREMARKS());
				
				break; 
				// Test345

			case "Test345":
				
				method.Test345(driver, datasheet.getLOCATOR_KEY(), datasheet.getREMARKS());
				
				break; 
				
			case "Keep_the_session_alive":
				
//				method.Keep_the_session_alive(driver);
				
				break;
				
			case "Premium_Payable1":
				
				method.Premium_Payable(driver, datasheet.getLOCATOR_KEY(), datasheetValue);
				
				break;
				
			case "Policy_Term1":
				
				method.Policy_Term(driver, datasheet.getLOCATOR_KEY(), datasheetValue);
				
				break;
				// Sum_Assured1

			case "Sum_Assured1":
				
				method.Sum_Assured(driver, datasheet.getLOCATOR_KEY(), datasheetValue);
				
				break;

			case "NavigateTo":
				
				driver.navigate().to(newURL);
				
				break;

			case "getNewURL":
				
				newURL = driver.findElement(By.xpath(datasheet.getLOCATOR_KEY())).getText();
				
				break;

			case "captcha":

				method.captcha(driver, datasheet.getLOCATOR_KEY());
				
				break;
			default:
				System.out.println("Region not available");
			}

			/*
			 * catch(Exception e) { e.printStackTrace(); }
			 */

		}

		catch (Exception e) {
			e.printStackTrace();
			if (value == null || "".equals(value)) {
				ReadExcelByMapping.writeExcel1(dataSheetResultPath, sheetName, tc_id, "Testcase_Remake",
						datasheet_var.getDATASHEET_KEY() + " Runtime exception");
				ReadExcelByMapping.writeExcel1(dataSheetResultPath, sheetName, tc_id, "flag", "FAIL");
			}
			throw new Exception();

		}

	}

}
