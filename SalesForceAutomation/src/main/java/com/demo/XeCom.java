package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XeCom {
	
	static WebDriver  driver;
	
	static {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		
	       }
	
	public static void main(String[] args) throws InterruptedException  {
		
		driver.get("https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=INR");
		
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"midmarketFromCurrency\"]")).clear();
		
		driver.findElement(By.xpath("//*[@id=\"midmarketFromCurrency\"]")).sendKeys("USD", Keys.ENTER);
	   
		driver.findElement(By.xpath("//*[@id=\"midmarketToCurrency\"]")).clear();
		
		driver.findElement(By.xpath("//*[@id=\"midmarketToCurrency\"]")).sendKeys("INR" , Keys.ENTER);
		 
		driver.findElement(By.xpath("//a[@class='tab-box__Tab-sc-28io75-2 gfPaoD'] /span[text()='Convert']")).click();
		
		synchronized(driver)
        {
        	driver.wait(1000);
        }
	//	driver.switchTo().frame(driver.findElement(By.id("element-77498")));
		driver.findElement(By.id("yie-close-button-18ba1593-89fa-5cf4-bc99-42c838ef3b2f")).click();
		//driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/section/div[2]/div/main/form/div[2]/button")).click();
	
		//driver.close();
		
		
		//*[@id=""]
		
	}
	
	
	
}
