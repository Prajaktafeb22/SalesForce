package com.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase04 {

	public static void main(String[] args) throws InterruptedException {
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
	     driver.findElement(By.id("username")).sendKeys("ghvfjhvj@hfghf.com");
	     driver.findElement(By.id("password")).sendKeys("ghvfjhvj");
	     driver.findElement(By.id("Login")).click();
	     
	     
	    // driver.findElement(By.id("forgot_password_link")).click();

	   //  driver.findElement(By.id("un")).sendKeys(prop.getProperty("username"));
	    // driver.findElement(By.id("continue")).click();

	}

}
