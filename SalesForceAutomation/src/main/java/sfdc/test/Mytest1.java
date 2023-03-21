package sfdc.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Mytest1 {

    public static void main(String[] args) {
    	
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DECOMM\\Downloads\\chromedriver_win32\\chromedriver.exe");
    	WebDriver driver = new ChromeDriver();
    	
    	driver.get("https://www.google.com/");
    	driver.manage().window().fullscreen();
    	
    	String check = driver.findElement(By.xpath("//div[@class=\"k1zIA rSk4se\"]")).getText();
    	
    	System.out.println(check);
    	
    	//driver.close();  	
}
}
