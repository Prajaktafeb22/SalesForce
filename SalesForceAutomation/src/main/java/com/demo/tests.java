package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class tests {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.findElement(By.name("username")).sendKeys("mithun.r@tekarch.com");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.name("Login")).click();
		
		
		

	}

}
