package org.gradle;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;


// kuck mal
// Driver.findElement(By.cssSelector("button.radius")).click();


public class conversario {  

	WebDriver driver;
	private String driverPath = "libs/chromedriver"; 
	//private String driverPath = "libs/geckodriver"; 

	private WebClient webClient;
	private WebWindow currentWindow;
	
	//private String baseUrl = "https://www.rbb-online.de/test/abnahme/kommentarfunktion/kommentare-eingeschaltet/mge/conversation.html";
	private String baseUrl = "https://www.rbb-online.de/test/abnahme/kommentarfunktion/kommentare-eingeschaltet/mge/conversation.htm/writecomment=true.html#new_comment_box";

	
	@BeforeTest
	public void launchBrowser() {
		
		/* Chrome Browser */
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(); 
		driver.get(baseUrl);	
		
		/* Headless Chrome Browser */
		/*System.setProperty("webdriver.chrome.driver", driverPath);
		// Add options to Google Chrome. The window-size is important for responsive sites
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
		driver.get(baseUrl);*/
	}
	
	
	/* priority 0    	(lowest, is going to be executed first)
	 * priority 7		(highest, is going to be executed last)
	 * */
	@Test(priority = 0)
	public void sendNiceComment() { 
		

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sText =  js.executeScript("return document.documentElement.innerText;").toString();
		
		System.out.println("AAA" + sText);
		
		driver.findElement(By.id("mailpar_1")).click();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}/*
		driver.findElement(By.id("mailpar_1")).sendKeys("AAA");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}*/
		(new Actions(driver)).sendKeys("AAA").perform();

		
		
		//System.out.println("AAA" + driver.findElement(By.id("comment_form")).findElements(By.className("type-text")).toString());
	
		//By.xpath("//td[contains(text(),'Ort')]/following-sibling::td//input[@type='text']");

		
		//js.executeScript("document.getElementById('Ort')");
		//.setAttribute('value', 'new value for element')
		
		
		//WebElement kommentarButton = driver.findElement(By.id("new_comment_link"));
		
		/*driver.findElement(By.id("new_comment_link")).click();
		try {
			Thread.sleep(1400);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}*/

		
		/*By hiddenObj = By.xpath("//*[@id='mailpar_1' and @type='hidden']");
		hiddenObj.findElement(driver.findElement(By.id("mailpar_1"))).sendKeys("GoodBoy");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}*/
		
		//WebElement elem = driver.findElement(By.xpath("//*[@class='js_toggle'"));
		//String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
 
		//((JavascriptExecutor) driver).executeScript(js, elem);
		
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}/*
		//driver.findElement(By.id("mailpar_1")).sendKeys("GoodBoy");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}*/
		
		
		AssertJUnit.assertEquals("AAA", "AAA");

	}  
	
	

	
	
	@AfterTest
    public void terminateBrowser(){
		driver.close();
        //driver.quit();
	}
}
