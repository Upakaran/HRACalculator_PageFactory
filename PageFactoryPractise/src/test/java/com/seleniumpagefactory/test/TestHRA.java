package com.seleniumpagefactory.test;

import org.testng.annotations.Test;

import com.seleniumfagefactory.utilities.CommonFunctions;
import com.seleniumpagefactory.pages.HRAExemptionCalculatorPage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

public class TestHRA {

	CommonFunctions commonFunctions = new CommonFunctions();
	WebDriver driver;
	HRAExemptionCalculatorPage hraExemptionCalculatorPage;

	
	@Parameters({ "basicPay", "dearnessAllowance", "houseRentAllowance", "actualRent", "cityType" })
	@Test(priority = 1)
	public void testHRACalculation(String basicpay, String dearnessAllowance, String houseRentAllowance,
			String actualrent, String cityType) throws Exception {
		hraExemptionCalculatorPage.calculateHRA(basicpay, dearnessAllowance, houseRentAllowance, actualrent, cityType);
	}

	@Test(priority = 2)
	public void testHRAMessage() throws InterruptedException {
		hraExemptionCalculatorPage.getHRAMessage();
	}
	
	@Parameters({ "name" , "financialYear"})
	@Test(priority = 3)
	public void testPdfDownload(String name , String financialYear) throws InterruptedException, Exception {
		hraExemptionCalculatorPage.downloadPDF(name , financialYear);
	}

	
	@Test(priority = 4)
	public void testHTMLTable() throws InterruptedException, Exception {
		hraExemptionCalculatorPage.testWebTable();
	}
	
	@Parameters({ "name" , "financialYear"})
	@Test(priority = 5)
	public void testOpenPDF(String name , String financialYear) throws InterruptedException, Exception {
		hraExemptionCalculatorPage.showPDF(name, financialYear);
	}
	
	
	@Test(priority = 6)
	public void testHRAMessageAfterReset() throws InterruptedException, Exception {
		hraExemptionCalculatorPage.resetAndCheckForMessage();
		
	}

	@Parameters({ "targetURL" })
	@BeforeTest
	public void beforeTest(String url) {
		driver = commonFunctions.doSetUp(url);
		hraExemptionCalculatorPage = PageFactory.initElements(driver, HRAExemptionCalculatorPage.class);
	}

	@AfterTest
	public void afterTest() {
		commonFunctions.exitBrowser();
	}

}
