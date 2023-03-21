package ParallelDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


@Test
public class newDemo {
	public void setUp() {
	String projectPath = System.getProperty("user.dir");
	System.setProperty("webdriver.chrome.driver",
    "C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
	 WebDriver driver=new ChromeDriver();  
     
	// Launch website  
	    driver.navigate().to("http://www.google.com/");  
	          
//	    // Click on the search text box and send value  
//	    driver.findElement(By.id("lst-ib")).sendKeys("javatpoint tutorials");  
//	          
//	    // Click on the search button  
//	    driver.findElement(By.name("btnK")).click();  
	
	}
}
