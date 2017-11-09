package org.gradle;


import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;  


public class rbbTest {  

	public String baseUrl = "http://rbb24.de";
	String driverPath = "libs/geckodriver"; 
	public WebDriver driver;
	
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.firefox.marionette", driverPath);
		driver = new FirefoxDriver();
		
		// Load homepage
		driver.get(baseUrl);
	}
	
	
	/*
	 * priority 0    	(lowest, is going to be executed first)
	 * priority 7		(highest, is going to be executed last)
	 * */
	@Test(priority = 0)
	public void testcase01() {  
			
		String actualTitle = driver.getTitle();
		//String expectedTitle = "rbb|24 – Nachrichten aus Berlin und Brandenburg | rbb Rundfunk Berlin-Brandenburg";
		String expectedTitle = "rbb|24 | rbb|24 - Nachrichten aus Berlin und Brandenburg";
		
		// Vergleiche Titel mit zu erwartenden Titel	
		if (actualTitle.contentEquals(expectedTitle)){
			System.out.println("+++++++++++++++++++++++++++");
			System.out.println("actualTitle: " + actualTitle);
			System.out.println("---------------------------");
			System.out.println("expectedTitle: " + expectedTitle);
			System.out.println("---------------------------");
			System.out.println("Test Passed!");
			System.out.println("+++++++++++++++++++++++++++");
		} else {
			System.out.println("+++++++++++++++++++++++++++");
			System.out.println("actualTitle: " + actualTitle);
			System.out.println("---------------------------");
			System.out.println("expectedTitle: " + expectedTitle);
			System.out.println("---------------------------");
			System.out.println("Test Failed");
			System.out.println("+++++++++++++++++++++++++++");
		}	
		Assert.assertEquals(actualTitle, expectedTitle);
		// -----------------------------------          
	}  
	
	@Test(priority = 7)
	public void testcase02() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "aa";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	
	@AfterTest
    public void terminateBrowser(){
		driver.close();
        //driver.quit();
	}
}
