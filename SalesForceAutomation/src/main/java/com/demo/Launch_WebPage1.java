package com.demo;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Launch_WebPage1 {
	
	private static Properties loadProperties()
	{
		 Properties prop=new Properties();
		  
		 try {
			 FileInputStream ip= new FileInputStream("C:\\Users\\DECOMM\\eclipse-workspace\\Saleforceautomation\\src\\main\\resources\\config.properties");
			 prop.load(ip);// load the properties  
			 ip.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return prop;
	}
	
	
	
	
	

	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		//WebDriverManager.chromedriver().setup();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10);
		
		driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
		//driver.manage().window().maximize();
		Properties myproperty = loadProperties();
		
		driver.findElement(By.id("ap_email")).sendKeys(myproperty.getProperty("amazonusername"));
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys(myproperty.getProperty("amazonpassword"));
		driver.findElement(By.id("signInSubmit")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("kids pants");
		
		driver.findElement(By.id("nav-search-submit-button")).click();
		
	}

}
