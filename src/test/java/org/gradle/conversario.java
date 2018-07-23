package org.gradle;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.SkipException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.ThreadLocalRandom;


public class conversario {  

	WebDriver driver;
	private String driverPath = "libs/chromedriver"; 
	//private String driverPath = "libs/geckodriver"; 
	
	
	/*** Base Urls for Live ***/
	private String baseUrl = "https://www.rbb-online.de/test/abnahme/kommentarfunktion/kommentare-eingeschaltet/mge/conversation.html";
	private String baseFormUrl = "https://www.rbb-online.de/test/abnahme/kommentarfunktion/kommentare-eingeschaltet/mge/conversation.htm/writecomment=true.html#new_comment_box";	
	
	/*** Base Urls for Stage0 ***/
	//private String baseUrl = "https://rbb-s0.w3.rbb-online.de/content/rbb/r24/test/mge/conversation.html";
	//private String baseFormUrl = "https://rbb-s0.w3.rbb-online.de/content/rbb/r24/test/mge/conversation.htm/writecomment=true.html#top";	
	

	private int GoodNr;
	private int BadNr;
	
	@BeforeTest
	public void launchBrowser() {
		
		/* VISUALIZE Chrome Browser */
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(); 
		driver.get(baseUrl);	
		
		/* HEADLESS Chrome Browser */
		/*System.setProperty("webdriver.chrome.driver", driverPath);
		// Add options to Google Chrome. The window-size is important for responsive sites
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
		driver.get(baseFormUrl);*/
	}
	
	
	
	/* priority 0    	(lowest, is going to be executed first)
	 * priority 7		(highest, is going to be executed last)
	 * */
	@Test(priority = 0)
	public void sendNiceComment() { 
		driver.get(baseFormUrl);	
		
		this.GoodNr = ThreadLocalRandom.current().nextInt(100, 998 + 1);
	
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//String sText =  js.executeScript("return document.documentElement.innerText;").toString();
		
		// Fill name into form 
		driver.findElement(By.id("mailpar_1")).click();
		//driver.findElement(By.id("mailpar_1")).sendKeys("RBB_Goodboy");
		(new Actions(driver)).sendKeys("RBB_Goodboy").perform();
		
		// Fill e-mail into form 
		driver.findElement(By.id("mailpar_2")).click();
		(new Actions(driver)).sendKeys("manuelgeyer@web.de").perform();
		
		// Fill comment into form 
		driver.findElement(By.id("mailpar_4")).click();
		(new Actions(driver)).sendKeys("NiceComment-"+ GoodNr +": Bitte ein Bild von der heiligen Magdalena (vor-, nach-, während der Sünde?).").perform();
		
		//driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}	
		AssertJUnit.assertEquals("AAA", "AAA");
	}  
	
	
	@Test(priority = 0)
	public void sendBadComment() { 	
		driver.get(baseFormUrl);	
		
		this.BadNr = ThreadLocalRandom.current().nextInt(100, 998 + 1);
		
		// Fill name into form 
		driver.findElement(By.id("mailpar_1")).click();
		(new Actions(driver)).sendKeys("RBB_Badboy").perform();
		
		// Fill e-mail into form 
		driver.findElement(By.id("mailpar_2")).click();
		(new Actions(driver)).sendKeys("manuelgeyer@web.de").perform();
		
		// Fill comment into form 
		driver.findElement(By.id("mailpar_4")).click();
		(new Actions(driver)).sendKeys("BadComment-"+ BadNr +": Das ist ein bescheuerter Kommentar gegen den Rundfunk. Mit Schimpfwörtern wie damisch depp depperter oder Doldi mit einer Drudschen an der Spitzen. Blöder Scheiß funktioniert wieder net.").perform();
		
		//driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		AssertJUnit.assertEquals("AAA", "AAA");
	}
	
	
	@Test(priority = 1)
	public void checkGoodComment() { 
		driver.get(baseUrl);	
		
		String tempString="GOODCOMMENT";
		String s2text = driver.findElement(By.xpath("//article[@class='comment']")).getAttribute("innerText");

		if (s2text.contains(tempString) == true) {
			System.out.println("GOODCOMMENT found: " + this.GoodNr);
		} else {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution");
		}		
	} 
	
	
	@Test(priority = 1)
	public void checkBadComment() { 
		driver.get(baseUrl);	
		
		String tempString="BADCOMMENT";
		String s2text = driver.findElement(By.xpath("//article[@class='comment']")).getAttribute("innerText");

		if (s2text.contains(tempString) == true) {
			System.out.println("BADCOMMENT found: " + this.BadNr);
		} else {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution");
		}		
	}
	
	
	@AfterTest
    public void terminateBrowser(){
		driver.close();
        //driver.quit();
	}
}
