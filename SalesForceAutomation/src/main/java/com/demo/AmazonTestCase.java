package com.demo;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver.WindowType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTestCase {
	
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
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
		driver.manage().window().maximize();
		Properties myproperty = loadProperties();
		
		driver.findElement(By.id("ap_email")).sendKeys(myproperty.getProperty("amazonusername"));
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys(myproperty.getProperty("amazonpassword"));
		driver.findElement(By.id("signInSubmit")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("kindle");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		try 
		{
			driver.findElement(By.id("B08KTZ8249-amazons-choice-label")).click();
			synchronized(driver)
			{
				driver.wait(1500);
			}
			driver.findElement(By.id("add-to-cart-button")).click();
			
			driver.findElement(By.id("a-autoid-4")).click();
			driver.findElement(By.xpath("//*[@class=\"a-button-input\" and @type=\"submit\" and @aria-labelledby=\"a-autoid-4-announce\"]")).click();
			 
			
			String selectAll = Keys.chord(Keys.SHIFT, Keys.RETURN);
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
			synchronized(driver)
			{
				driver.wait(1000);
			}
			driver.findElement(By.cssSelector("body")).sendKeys(selectAll);
			
		}
		catch(NoSuchElementException ex)
		{
			System.out.println(ex.toString());			
		}
		
	}
}
		
		
	


		