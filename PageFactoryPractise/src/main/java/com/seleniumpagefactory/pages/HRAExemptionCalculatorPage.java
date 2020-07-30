package com.seleniumpagefactory.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HRAExemptionCalculatorPage {

	WebDriver driver;

	@FindBy(how = How.ID, using = "basic")
	@CacheLookup
	public WebElement basicPayElement;

	@FindBy(how = How.ID, using = "DA")
	@CacheLookup
	public WebElement dearnessAllowanceElement;

	@FindBy(how = How.ID, using = "HRA")
	@CacheLookup
	public WebElement houseRentAllowanceElement;

	@FindBy(how = How.ID, using = "Rent")
	@CacheLookup
	public WebElement actualRentElement;

	@FindBy(how = How.XPATH, using = "//label[@for='Metro']/span")
	@CacheLookup
	public WebElement cityTypeMetroElement;

	@FindBy(how = How.XPATH, using = "//label[@for='Non-Metro']/span")
	@CacheLookup
	public WebElement cityTypeNonMetroElement;

	@FindBy(how = How.XPATH, using = "//input[@value=' Calculate ']")
	@CacheLookup
	public WebElement calculateBtnElement;

	@FindBy(how = How.XPATH, using = "//input[@value='   Reset   ']")
	@CacheLookup
	public WebElement resetBtnElement;

	@FindBy(how = How.XPATH, using = "//div[@id='dispMsg']/child::div")
	@CacheLookup
	public WebElement HRAMessageElement;

	@FindBy(how = How.XPATH, using = "//div[@id='dispMsg']")
	@CacheLookup
	public WebElement HRAMessageDiv;

	@FindBy(how = How.XPATH, using = "//u[contains(text(),'claiming tips')]")
	@CacheLookup
	public WebElement HRATipsHeading;

	@FindBy(how = How.ID, using = "YourName")
	@CacheLookup
	public WebElement yourNameElement;

	@FindBy(how = How.NAME, using = "FY")
	@CacheLookup
	public WebElement financialYearElement;

	@FindBy(how = How.NAME, using = "generatePDF")
	@CacheLookup
	public WebElement pdfBtnElement;

	@FindBy(how = How.ID, using = "warningMessageSpan")
	@CacheLookup
	public WebElement successMessageElement;

	@FindBy(how = How.XPATH, using = "//input[@value='  OKAY  ']")
	@CacheLookup
	public WebElement okayElement;

	public HRAExemptionCalculatorPage(WebDriver driver) {
		this.driver = driver;
	}

	public void calculateHRA(String basicpay, String dearnessAllowance, String houseRentAllowance, String actualrent,
			String cityType) throws InterruptedException, Exception {

		Thread.sleep(2000);
		basicPayElement.sendKeys(basicpay);
		Thread.sleep(2000);
		dearnessAllowanceElement.sendKeys(dearnessAllowance);
		Thread.sleep(2000);
		houseRentAllowanceElement.sendKeys(houseRentAllowance);
		Thread.sleep(2000);
		actualRentElement.sendKeys(actualrent);
		Thread.sleep(2000);

		if (cityType.equalsIgnoreCase("metro")) {
			cityTypeMetroElement.click();
			Thread.sleep(2000);
		}

		else if (cityType.equalsIgnoreCase("non-metro")) {
			cityTypeNonMetroElement.click();
			Thread.sleep(2000);
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", calculateBtnElement);

		Thread.sleep(2000);

		calculateBtnElement.click();

		Thread.sleep(2000);

		String attribute = HRAMessageDiv.getAttribute("style");
		if (attribute.contains("display: none")) {
			Thread.sleep(2000);
			System.out.println("HRA message not displayed after calculate.");
			throw new Exception("HRA message not displayed after calculate.");

		} else
			System.out.println("\n\nHRA message is now displayed after calculate.");
		Thread.sleep(2000);
	}

	public void getHRAMessage() throws InterruptedException {

		String message = HRAMessageElement.getText().toString();

		Thread.sleep(2000);

		System.out.println(message);

		Thread.sleep(2000);

	}

	public void resetAndCheckForMessage() throws InterruptedException, Exception {

		Thread.sleep(2000);

		resetBtnElement.click();

		Thread.sleep(2000);

		String attribute = HRAMessageDiv.getAttribute("style");
		if (attribute.contains("display: block")) {
			Thread.sleep(2000);
			System.out.println("HRA message is still displayed after reset");
			throw new Exception("HRA message is still displayed after reset");

		} else
			System.out.println("\n\nHRA message is not displayed after reset");
		Thread.sleep(2000);

	}

	public void downloadPDF(String name, String financialYear) throws InterruptedException, Exception {

		Thread.sleep(2000);
		yourNameElement.sendKeys(name);
		Thread.sleep(2000);
		financialYearElement.sendKeys(financialYear);
		Thread.sleep(2000);
		pdfBtnElement.click();
		Thread.sleep(2000);

		if (successMessageElement.isDisplayed()) {
			Thread.sleep(2000);

			if (successMessageElement.getText().toLowerCase().contains("yipiee"))

				System.out.println("PDF message successful displayed.");

			okayElement.click();
			Thread.sleep(2000);
		}

		// Create Robot class
		Robot rb = new Robot();

		// Press control keyboard key
		rb.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		// Press A keyboard key
		rb.keyPress(KeyEvent.VK_J);
		Thread.sleep(2000);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(500);
		rb.keyRelease(KeyEvent.VK_J);
		Thread.sleep(2000);

		// Actions action = new Actions(driver);
		// action.keyDown(Keys.CONTROL).sendKeys("j").build().perform();
		//
		// action.keyUp(Keys.CONTROL).build().perform();

		/*Actions action = new Actions(driver);
		String openNewTab = Keys.chord(Keys.CONTROL, "j");
		action.keyDown(openNewTab).build().perform();
		Thread.sleep(3000);*/
		
		
		
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(500);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		Thread.sleep(500);

	}

	public void showPDF(String name, String financialYear) throws InterruptedException {
		Thread.sleep(2000);
		String filePath = "C:\\Users\\Kaushik\\Downloads\\" + "HRAExemption_" + name + "_" + financialYear + ".pdf";
		// File file = new File(filePath);
		System.out.println(filePath);
		((JavascriptExecutor) driver).executeScript("window.open()");
		// window.open('https://upakaran-hraexemptioncalculator.github.io/','_blank');
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		System.out.println(driver.getWindowHandle());
		Thread.sleep(2000);
		driver.get(filePath);
		Thread.sleep(8000);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(2000);
	}

	public void testWebTable() {

		WebElement table = driver.findElement(By.id("table1"));

		List<WebElement> list1 = table.findElements(By.tagName("tr"));

		// System.out.println("Tr size : "+list1.size());

		for (WebElement webElement : list1) {

			if (webElement == list1.get(list1.size() - 1))
				continue;

			else {

				List<WebElement> list2 = webElement.findElements(By.tagName("td"));

				for (WebElement webElement2 : list2) {

					if (!(webElement2.getText().toString().toLowerCase().equalsIgnoreCase("non-metro")
							|| webElement2.getText().toString().toLowerCase().equalsIgnoreCase("metro"))
							&& (webElement2 != list2.get(list2.size() - 1))) {
						System.out.print(webElement2.getText() + "  ");
					}

					try {

						String text = "";

						if (!(webElement2.findElement(By.tagName("input")).getAttribute("value").toLowerCase()
								.equals("metro")
								|| webElement2.findElement(By.tagName("input")).getAttribute("value").toLowerCase()
										.equals("non-metro"))) {
							text = webElement2.findElement(By.tagName("input")).getAttribute("value").toString();
						}

						else {
							if (driver.findElement(By.id("Metro")).isSelected())
								text = "Metro";
							else if (driver.findElement(By.id("Non-Metro")).isSelected())
								text = "Non-Metro";
						}

						System.out.println(text);

					} catch (Exception e) {
						e.getMessage();
					}

				}

				System.out.println();

			}
		}
	}
}
