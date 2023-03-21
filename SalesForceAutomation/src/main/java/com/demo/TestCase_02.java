package com.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase_02 {

	public static void main(String[] args) {
		 Properties prop=new Properties();
		  
		 try {
			 FileInputStream ip= new FileInputStream("C:\\Users\\DECOMM\\eclipse-workspace\\Saleforceautomation\\src\\main\\resources\\config.properties");
			 prop.load(ip);// load the properties  
			 ip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     WebDriver driver;
	    // System.setProperty("webdriver.chrome.driver","C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
	     WebDriverManager.chromedriver().setup();
	     driver=new ChromeDriver();
	     driver.get("https://login.salesforce.com/");
	     driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
	     driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
	     driver.findElement(By.id("Login")).click();
	     driver.findElement(By.id("userNavLabel")).click();
	     driver.findElement(By.cssSelector("a[title='Logout']")).click();

	     

	}

}
