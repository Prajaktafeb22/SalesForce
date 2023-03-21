package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mouseHover {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://myntra.com");
		driver.manage().window().maximize();
		//driver.wait(2000);
		
		Actions action = new Actions (driver);
		WebElement menuOption = driver.findElement(By.xpath("//a[@href='/shop/men' and @data-index='0']"));
		//driver.wait(1000);
		menuOption.click();
		//action.moveToElement(menuOption).perform();
		System.out.println("Done Mouse hover on 'Music' from Menu");
		

	}

}
