package com.demo;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCase08 extends BaseSeleniumTestCase {

	public static void  TestCase08()
	{
		init();
	}
	

		
		
	
	
	
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		TestCase08.init();
		Wait(2);
		driver.findElement(By.id("userNavLabel")).click();
		driver.findElement(By.xpath("//a[@href='/secur/logout.jsp' and text()='Logout']")).click(); //Testcase09
		//TestCase08.Logout();
		
		//driver.findElement(By.xpath("//a[@class='debugLogLink menuButtonMenuLink']")).click();
		//driver.findElement(By.id("toolbar-1043")).click();
		//Wait(2);
		//driver.findElement(By.id("editorMenuEntry")).click();
		//driver.findElement(By.id("closeButton-textEl")).click();
		
	
	}
	
}
	