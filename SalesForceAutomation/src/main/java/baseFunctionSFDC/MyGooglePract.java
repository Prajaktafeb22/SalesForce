package baseFunctionSFDC;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

//import net.bytebuddy.implementation.bind.annotation.DefaultCall.Binder.DefaultMethodLocator.Implicit;

public class MyGooglePract {

	@Test
	public static void test1() throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver",
//	    "C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
//		WebDriver driver=new ChromeDriver(); 
//		 driver.navigate().to("http://www.google.com/");  
//	//driver.close();
//		   WebDriverWait w = new WebDriverWait(driver, 10);	
//	    WebElement p=driver.findElement(By.xpath("//*[@class='gLFyf gsfi']"));
//	    		   p.sendKeys("Selenium java");
//	    			  
//		p.submit(); 

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\DECOMM\\Downloads\\prajakta\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		WebElement button = driver.findElement(By.xpath("//*[text()='No thanks']"));
		Thread.sleep(1000);
		button.click();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		// driver.quit();
		WebElement search = driver.findElement(By.xpath("//input[@class=\"gLFyf gsfi\"]"));
      
		search.sendKeys("Selenium");
		// search.submit();
		Thread.sleep(1000);
		List<WebElement> suggestions = driver.findElements(By.xpath("//*[@class='mkHrUc']//*//li"));

		if (suggestions.size() != 0) {
			System.out.println(suggestions.size() + "Elements found by tagname ");

			for (WebElement suggestion : suggestions) {

				System.out.println(suggestion.getText());

			}

		}
//just for extra practice 
		Actions act = new Actions(driver);
		
	}

}
