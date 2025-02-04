package org.gradle;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.AssertJUnit;
import org.testng.SkipException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
	

	private int NiceNr;
	private int BadNr;
	
	@BeforeTest
	public void launchBrowser() {
		
		/* VISUALIZE Chrome Browser */
		/*System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(); 
		driver.get(baseFormUrl);*/
		
		/* HEADLESS Chrome Browser */
		System.setProperty("webdriver.chrome.driver", driverPath);
		// Add options to Google Chrome. The window-size is important for responsive sites
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x900");
		//options.addArguments("--ppapi-flash-path=/usr/lib/adobe-flashplugin/libpepflashplayer.so");
		options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
		driver.get(baseFormUrl);
	}
	
	
	
	/* priority 0    	(lowest, is going to be executed first)
	 * priority 7		(highest, is going to be executed last)
	 * */
	@Test(priority = 0)
	public void sendNiceComment() { 
		driver.get(baseFormUrl);
		
		this.NiceNr = ThreadLocalRandom.current().nextInt(100, 998 + 1);
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//String sText =  js.executeScript("return document.documentElement.innerText;").toString();
		
		// Fill name into form 
		driver.findElement(By.id("mailpar_1")).click();
		//driver.findElement(By.id("mailpar_1")).sendKeys("RBB_Goodboy");
		(new Actions(driver)).sendKeys("TPS-Online_Goodboy").perform();
		
		// Fill e-mail into form 
		driver.findElement(By.id("mailpar_2")).click();
		(new Actions(driver)).sendKeys("manuelgeyer@web.de").perform();
		
		// Fill comment into form 
		driver.findElement(By.id("mailpar_4")).click();
		(new Actions(driver)).sendKeys("NiceComment-"+ NiceNr).perform();
		//Bitte ein Bild von der heiligen Magdalena. (vor-, nach-, waehrend der Suende?)
		
		driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	} 
	
	
	@Test(priority = 1)
	public void checkNiceComment() { 
		driver.get(baseFormUrl);
		
		String expectedString = Integer.toString(this.NiceNr);
		String s2text = ""; 

		System.out.println("NiceNr:" + expectedString);
		int i;
		for (i=1; i<=20; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			
			driver.get(baseUrl);
			s2text = driver.findElement(By.xpath("//article[@class='comment']")).getAttribute("innerText");
			
			if (s2text.contains(expectedString)==true) {
				break;
			}
			System.out.println("Waiting for GoodComment since: " + (i*2) + "s");
		}
		System.out.println("s2text:" + s2text);

		AssertJUnit.assertTrue(s2text.contains(expectedString));	
	} 


	@Test(priority = 2)
	public void sendBadComment() { 	
		driver.get(baseFormUrl);	
		driver.close();
		launchBrowser(); 
		
		this.BadNr = ThreadLocalRandom.current().nextInt(100, 998 + 1);
		
		// Fill name into form 
		driver.findElement(By.id("mailpar_1")).click();
		(new Actions(driver)).sendKeys("TPS-Online_Badboy").perform();
		
		// Fill e-mail into form 
		driver.findElement(By.id("mailpar_2")).click();
		(new Actions(driver)).sendKeys("manuelgeyer@web.de").perform();
		
		// Fill comment into form 
		driver.findElement(By.id("mailpar_4")).click();
		(new Actions(driver)).sendKeys("BadComment-"+ BadNr +": Das ist ein boeser Kommentar, mit Schimpfwoertern wie damisch depp depperter oder Doldi mit einer Drudschen an der Spitzen.").perform();
		
		driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[@value='Abschicken'][@name='savecomment'][@type='submit']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	
	
	@Test(priority = 3)
	public void checkBadComment() { 
		driver.get(baseFormUrl);	
				
		String expectedString= Integer.toString(this.BadNr);
		String s2text = ""; 
		
		System.out.println("BadNr:" + expectedString);
		int i;
		for (i=1; i<31; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			
			driver.get(baseUrl);
			s2text = driver.findElement(By.xpath("//article[@class='comment']")).getAttribute("innerText");
			
			if (s2text.contains(expectedString)==true) {
				break;
			}
			System.out.println("Waiting for BadComment since: " + (i*2) + "s");
		}
		System.out.println("s2text:" + s2text);
		
		AssertJUnit.assertFalse(s2text.contains(expectedString));	
	}
	
	@AfterTest
    public void terminateBrowser(){
		System.out.println("Closing tests");
		driver.close();
        //driver.quit();
	}
}
