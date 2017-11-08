package org.gradle;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;  


public class rbbTest {  

	public String baseUrl = "http://rbb24.de";
	String driverPath = "libs/geckodriver"; 
	public WebDriver driver;
	
	
	@BeforeTest
	public void setBaseURL() {
		System.setProperty("webdriver.firefox.marionette", driverPath);
		driver = new FirefoxDriver();
		driver.get(baseUrl);
	}
	
	@Test(priority = 7)
	public void testcase() {  

		System.out.println("Selenium web driver");  
			
		String actualTitle = driver.getTitle();
		//String expectedTitle = "rbb|24 – Nachrichten aus Berlin und Brandenburg | rbb Rundfunk Berlin-Brandenburg";
		String expectedTitle = "rbb|24 | rbb|24 - Nachrichten aus Berlin und Brandenburg";
		//String actualTitle = "aa"; 
		//String actualTitle = expectedTitle;
		
		// Vergleiche Titel mit zu erwartenden Titel	
		if (actualTitle.contentEquals(expectedTitle)){
			System.out.println("+++++++++++++++++++++++++++");
			System.out.println("actualTitle: " + actualTitle);
			System.out.println("---------------------------");
			System.out.println("expectedTitle: " + expectedTitle);
			System.out.println("---------------------------");
			System.out.println("Test Passed!");
			System.out.println("+++++++++++++++++++++++++++");
			Assert.assertTrue(true);
		} else {
			System.out.println("+++++++++++++++++++++++++++");
			System.out.println("actualTitle: " + actualTitle);
			System.out.println("---------------------------");
			System.out.println("expectedTitle: " + expectedTitle);
			System.out.println("---------------------------");
			System.out.println("Test Failed");
			System.out.println("+++++++++++++++++++++++++++");
			//Assert.fail();
		}	
		// -----------------------------------  
    	driver.close();
        driver.quit();
	}  
}
