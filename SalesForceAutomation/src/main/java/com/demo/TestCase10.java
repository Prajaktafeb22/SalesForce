package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

public class TestCase10 extends BaseSeleniumTestCase {
	
	public static void  TestCase10()
	{
		init();
	}
	
	public static void TestCase11() throws InterruptedException {
		driver.findElement(By.id("Account_Tab")).click();
		Wait(2);
		driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		   Wait(2);
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("prajaktabhandare2022");
        driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("prajaktabhandare2022");
        Wait(2);
        driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")).click();
		
	}
	static void TestCaseSub10() throws InterruptedException {
		
		driver.findElement(By.id("Account_Tab")).click();
		Wait(2);
		driver.findElement(By.xpath("//span[@id='createNewLabel']")).click();
        Wait(2);
        driver.findElement(By.xpath("//a[@class='accountMru menuButtonMenuLink']")).click();
         
	}
	static void TestCase12() throws InterruptedException {
		driver.findElement(By.id("Account_Tab")).click();
		Wait(2);
		driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		Wait(2);
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("prajaktabhandare" + timeStamp);
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("prajaktabhandare" +timeStamp);
        Wait(2);
     
        driver.findElement(By.xpath("//select[@id='colselector_select_0']/option[@value='ACCOUNT.LAST_ACTIVITY']")).click();
        driver.findElement(By.xpath(" //a[@id='colselector_select_0_right']/img[@title='Add']")).click();
        Wait(2);
        driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")).click();
	}
	
	private static void TestCase13MergeAccount() throws InterruptedException {
		driver.findElement(By.id("Account_Tab")).click();
		Wait(2);
		driver.findElement(By.xpath("//table[@id='toolsContent']//a[text()='Merge Accounts']")).click();
		driver.findElement(By.xpath("//input[@id='srch']")).sendKeys("United");
		Wait(2);
		driver.findElement(By.xpath("//input[@value='Find Accounts']")).click();
		driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]")).click();
			
	}

	private static void TestCase14CreateAccReport() throws InterruptedException {
		driver.findElement(By.id("Account_Tab")).click();
		Wait(2);
		driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"ext-gen20\"]")).sendKeys("Created Date");
		
	}
	
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		TestCase10.init();
		//TestCaseSub10();
		//TestCase11();
		//TestCase12();
		//TestCase13MergeAccount();
		TestCase14CreateAccReport();
	}

	


	
}
