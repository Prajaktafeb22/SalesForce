package com.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSeleniumTestCase {
	
	public static WebDriver driver;
	public static Properties prop;
	 
	
	 
	 
	 
	public static void init() {
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 InitiliseProp();
		 Login();
	     
	     
	 }

	private static void InitiliseProp() {
		 prop=new Properties();
		  
		 try {
			 FileInputStream ip= new FileInputStream("C:\\Users\\DECOMM\\eclipse-workspace\\Saleforceautomation\\src\\main\\resources\\config.properties");
			 prop.load(ip);// load the properties  
			 ip.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		
	}

	protected static void Login() {
		
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
	    driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
	    driver.findElement(By.id("Login")).click();
		
	}
	 
	protected static void Wait(int seconds) throws InterruptedException
	{
		synchronized(driver)
        {
        	driver.wait((long)seconds*1000);
        	        }
	}
	protected static void Logout() {
		// TODO Auto-generated method stub
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
	    driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
	    driver.findElement(By.xpath("//a[@href='/secur/logout.jsp' and text()='Logout']")).click();
	    	
	}
	
	public static boolean selectOptionInUserMenu(WebDriver driver,String optionName) {
		boolean isOptionSelected =false;
		WebElement userMenuOption=driver.findElement(By.xpath("//a[text()=\""+optionName+"\"]"));
		driver.manage().window().maximize();
		
		//a[text()=\"+optionName+\"]"
		
		if(userMenuOption.isDisplayed()) {
			
			userMenuOption.click();
			 isOptionSelected =true;
			 System.out.println("User menu option "+optionName+" is  visible");
			
		}else {
			
			System.out.println("User menu option "+optionName+" is not visible");
			}
		return isOptionSelected;
	}
		
	protected static boolean VerifyMenu(WebDriver driver) {
	 boolean isOptionVerified = true; 
	 String[] userMenuOption = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};
	 List <WebElement> listOfUserMenuOption =driver.findElement(By.id("userNav-menuItems")).findElements(By.tagName("a"));
	 
	//*[@id="userNav-menuItems"]/a[1]

		for(int i=0;i<userMenuOption.length;i++) {
			
			String optionValue= listOfUserMenuOption.get(i).getText();
			if(optionValue.equals(userMenuOption[i])) {
				
				System.out.println("Option "+userMenuOption[i]+" is verified");
			}else {
				
				System.out.println(" Verification of " +userMenuOption[i]+ " failed");
				isOptionVerified=false;
				
			}
			
		}
		return isOptionVerified;
		
	}

	
	
	
	
	
}


