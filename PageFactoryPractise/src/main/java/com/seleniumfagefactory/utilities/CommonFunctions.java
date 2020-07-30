package com.seleniumfagefactory.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonFunctions {
	
	WebDriver driver;
	
	public WebDriver doSetUp(String URL) {

		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");

		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\Kaushik\\Downloads\\chromedriver_win32\\chromedriver.exe");

		// Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		prefs.put("credentials_enable_service", false);

		prefs.put("profile.password_manager_enabled", false);

		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-notifications");
		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation", "load-extension" });

		// pass the options object in Chrome driver
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.navigate().to(URL);
		
		return driver;

	}
	
	
	public void exitBrowser() {
		try {
			Thread.sleep(3000);
			driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
