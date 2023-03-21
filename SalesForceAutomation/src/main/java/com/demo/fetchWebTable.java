package com.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class fetchWebTable {

	public static void main(String[] args) {
		//Not successfully run
		WebDriver driver ;
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DECOMM\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("https://www.espncricinfo.com");
		driver.get("https://www.espncricinfo.com/series/england-in-australia-2022-23-1317467/australia-vs-england-2nd-t20i-1317487/full-scorecard");
		driver.manage().window().maximize();
//		WebElement el=driver.findElement(By.xpath("//a[@href='/series/england-in-australia-2022-23-1317467/australia-vs-england-2nd-t20i-1317487/full-scorecard']"));
//		el.click();
		WebElement table = driver.findElement(By.xpath("//*[@class='ds-flex ds-px-4 ds-border-b ds-border-line ds-py-3 ds-bg-ui-fill-translucent-hover']"));
		
		
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		for(int rnum=0;rnum<rows.size();rnum++) {
			
			List<WebElement> header = rows.get(rnum).findElements(By.tagName("th"));
			for(int head=0;head<header.size();head++) {
				
				System.out.print(header.get(head).getText());
				
			}
			
			header = rows.get(rnum).findElements(By.tagName("td"));
			
            for(int head=0;head<header.size();head++) {
				
				System.out.print(header.get(head).getText());
				
			}
            System.out.println("");
			
		}
		
		
	
		
	}
}


