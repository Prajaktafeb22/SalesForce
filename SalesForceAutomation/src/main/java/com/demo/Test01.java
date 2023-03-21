package com.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test01 {
		
static WebDriver  driver;
	
	static {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		
	       }

 public static void main(String[] args) {
		 Properties prop=new Properties();
		 try {
			 FileInputStream ip= new FileInputStream("C:\\Users\\DECOMM\\eclipse-workspace\\Saleforceautomation\\src\\main\\resources\\config.properties");
			 prop.load(ip);// load the properties  
			 ip.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		    WebDriver driver;
		     WebDriverManager.chromedriver().setup();
		     driver=new ChromeDriver();
		     driver.get("https://login.salesforce.com/");
		     driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		     driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		     driver.findElement(By.id("Login")).click();

		

	}

}
