package org.gradle;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		/*System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(); 
		driver.get(baseUrl);	
		*/
		/* Headless Chrome Browser */
		System.setProperty("webdriver.chrome.driver", driverPath);
		// Add options to Google Chrome. The window-size is important for responsive sites
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
		driver.get(baseUrl);
	}
	
	
	
	/* priority 0    	(lowest, is going to be executed first)
	 * priority 7		(highest, is going to be executed last)
	 * */
	@Test(priority = 0)
	public void sendNiceComment() { 
				
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String sText =  js.executeScript("return document.documentElement.innerText;").toString();
		//System.out.println("AAA" + sText);
		
		/* Fill name into form */
		driver.findElement(By.id("mailpar_1")).click();
		//driver.findElement(By.id("mailpar_1")).sendKeys("RBB_Goodboy");
		(new Actions(driver)).sendKeys("RBB_Goodboy").perform();
		
		/* Fill e-mail into form */
		driver.findElement(By.id("mailpar_2")).click();
		(new Actions(driver)).sendKeys("manuelgeyer@web.de").perform();
		
		/* Fill comment into form */
		driver.findElement(By.id("mailpar_4")).click();
		(new Actions(driver)).sendKeys("NiceComment: Bitte ein Bild von der heiligen Magdalena (vor-, nach-, während der Sünde?).").perform();
		

		driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		
		AssertJUnit.assertEquals("AAA", "AAA");
	}  
	
	
	
	@Test(priority = 1)
	public void sendBadComment() { 
		
		driver.get(baseUrl);	
		
		/* Fill name into form */
		driver.findElement(By.id("mailpar_1")).click();
		(new Actions(driver)).sendKeys("RBB_Badboy").perform();
		
		/* Fill e-mail into form */
		driver.findElement(By.id("mailpar_2")).click();
		(new Actions(driver)).sendKeys("manuelgeyer@web.de").perform();
		
		/* Fill comment into form */
		driver.findElement(By.id("mailpar_4")).click();
		(new Actions(driver)).sendKeys("BadComment: Das ist ein bescheuerter Kommentar gegen den Rundfunk. Mit Schimpfwörtern wie damisch depp depperter oder Doldi mit einer Drudschen an der Spitzen. Blöder Scheiß funktioniert wieder net.").perform();
		
		driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		AssertJUnit.assertEquals("AAA", "AAA");
	}  
	
	

	
	
	@AfterTest
    public void terminateBrowser(){
		driver.close();
        //driver.quit();
	}
}
