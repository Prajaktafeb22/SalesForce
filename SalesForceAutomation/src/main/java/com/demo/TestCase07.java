package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCase07 extends BaseSeleniumTestCase {

	public static void  TestCase07()
	{
		init();
	}
	
	
	
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		TestCase07.init();
		driver.findElement(By.id("userNavLabel")).click();
		driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[@title=\"My Settings\"]")).click();
		
		Wait(2);	
		
		 
		//driver.findElement(By.xpath("//span[@class=\"itemCategory\" and text()=\"Personal Information\"]")).click();
		//Wait(2);
		//driver.findElement(By.xpath("//span[@id='LoginHistory_font' and text()='Login History']")).click();
		//driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a")).click();
		//driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a")).click();
		
		
		
		
		testDisplayNLayoutLink(driver);//Testcase 
		//myEmailSetting(driver);//	Testcase
		//calenderRemainder(driver);//Testcase
			
		
		
		

	}

	private static void calenderRemainder(WebDriver driver) {
		driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		driver.findElement(By.id("CalendarAndReminders_font")).click();
		driver.findElement(By.id("Reminders_font")).click();
		driver.findElement(By.id("testbtn")).click();
		
	}
	


	private static void myEmailSetting(WebDriver driver) throws InterruptedException {
		driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		driver.findElement(By.id("EmailSetup_font")).click();
		driver.findElement(By.id("EmailSettings_font")).click();
		driver.findElement(By.id("sender_name")).clear();
		Wait(2);
		driver.findElement(By.id("sender_name")).sendKeys("Prajakta Bhandare");
		driver.findElement(By.id("auto_bcc1")).click();
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@class='btn primary']")).click();
	}

	





	private static void testDisplayNLayoutLink(WebDriver driver) throws InterruptedException {
		driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		driver.findElement(By.id("DisplayAndLayout_font")).click();
		driver.findElement(By.xpath("//span[@id='CustomizeTabs_font' and text()='Customize My Tabs']")).click();
		Wait(2);
		driver.findElement(By.xpath("//select[@id='p4'] /option[text()='Salesforce Chatter']")).click();
		Wait(2);
		driver.switchTo().frame("contactInfoContentId");
		driver.findElement(By.xpath("//option[@value='report' and text()='Reports']")).click();
		Wait(2);
		driver.findElement(By.id("duel_select_0_right")).click();
		Wait(2);
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@title='Save']")).click();
		
	}

	

}

