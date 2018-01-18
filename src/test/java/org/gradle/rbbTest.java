package org.gradle;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.google.common.collect.ImmutableMap;
//import org.openqa.selenium.firefox.FirefoxDriver;  


// kuck mal
// Driver.findElement(By.cssSelector("button.radius")).click();


public class rbbTest {  

	WebDriver driver;
	private String driverPath = "libs/chromedriver"; 
	//private String driverPath = "libs/geckodriver"; 

	private WebClient webClient;
	private WebWindow currentWindow;
	
	private String baseUrl = "http://rbb24.de";

	
	@BeforeTest
	public void launchBrowser() {
		
		/* Chrome Browser */
		//System.setProperty("webdriver.chrome.driver", driverPath);
		//driver = new ChromeDriver(); 
		//driver.get(baseUrl);	
		
		/* Headless Chrome Browser */
		System.setProperty("webdriver.chrome.driver", driverPath);
		// Add options to Google Chrome. The window-size is important for responsive sites
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
		driver.get(baseUrl);
		
		
		/* Firefox Browser */
		//System.setProperty("webdriver.chrome.driver", driverPath);
		//driver= new FirefoxDriver();
		//driver.get(baseUrl);	
		/* Headless Firefox Browser */
		
		/* HTMLunitDriver*/
		//driver = new HtmlUnitDriver();
		//WebClient webClient = (WebClient) get(driver, "webClient");
		//System.out.println(webClient.getBrowserVersion());
		//System.out.println(webClient.getBrowserVersion().isIE());
	}
	
	
	/* priority 0    	(lowest, is going to be executed first)
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
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
		// -----------------------------------          
	}  
	
	
	@Test
	public void testcase02() throws InterruptedException {
		System.out.println("+++++++++++++ Serach Input +++++++++++++");
		
		String res = "ERGEBNISSE";
		int i = 0;
		
		// Send a search 
		WebElement searchBox = driver.findElement(By.id("search_input"));
		
		searchBox.sendKeys("tps");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
		
		// Get popup result and print it
		WebElement searchBoxRes = driver.findElement(By.id("searchbox_result"));
		
		while (searchBoxRes.getText().toLowerCase().contains(res.toLowerCase()) == false) {
			if (i >= 20) {
				System.out.println("Test Failed: timeout: No Popup with \"" + res + "\" as result");
				break;
			}
			i+=1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
		if ( searchBoxRes.getText().toLowerCase().contains(res.toLowerCase()) == true ) {
			System.out.println(searchBoxRes.getText());
		}

		// Test fail criteria 
		AssertJUnit.assertEquals(searchBoxRes.getText().toLowerCase().contains(res.toLowerCase()), true);
		
		// --- Switch between popup windows
		//String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		//String subWindowHandler = null;
		
		//Set<String> handles = driver.getWindowHandles(); // get all window handles
		//Iterator<String> iterator = handles.iterator();
		
		//while (iterator.hasNext()){
		//    subWindowHandler = iterator.next();
		//}
		//driver.switchTo().window(subWindowHandler); // switch to popup window
		//driver.switchTo().window(parentWindowHandler);  // switch back to parent window
		// ---
		
		//searchBox.submit();
		
		System.out.println("+++++++++++++ END Serach Input +++++++++++++");

		//Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	
	@AfterTest
    public void terminateBrowser(){
		driver.close();
        //driver.quit();
	}
}
