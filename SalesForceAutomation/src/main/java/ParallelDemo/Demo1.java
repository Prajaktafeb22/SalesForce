package ParallelDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1 {

	   WebDriver driver=null;
	   String projectPath = System.getProperty("user.dir");
	   
	   @Test
	   @Parameters("browserName")
	   public void verifyPageTitle(String browserName
			   ) {
		   
		
		if(browserName.equalsIgnoreCase("chrome")) {
			   
			String projectPath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",
		    "C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver=new ChromeDriver(); 
			
		   }
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			String projectPath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",
		    "C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver=new ChromeDriver(); 
			
		}  
		   
		   
	   }
	 @AfterTest 
	public void set() {
		
		driver.get("www.google.com");
		System.out.println("Successfully run");
		
	}	
		
		
		
	}


