package com.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTestCase  implements ITestCase {

	 protected WebDriver driver;
	 protected Properties prop;
	 
	 public BaseTestCase()
	 {
		 init();
	 }
	 
	public void init()
	{
		InitialiseProperties();
		InitialiseDriver();
	}

	private void InitialiseDriver() {		 
		     System.setProperty("webdriver.chrome.driver","C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
		     WebDriverManager.chromedriver().setup();
		     driver=new ChromeDriver();		
	}

	private void InitialiseProperties() {
		// TODO Auto-generated method stub
		prop=new Properties();
		  
		 try {
			 FileInputStream ip= new FileInputStream("C:\\Users\\DECOMM\\eclipse-workspace\\Saleforceautomation\\src\\main\\resources\\config.properties");
			 prop.load(ip);// load the properties  
			 ip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
