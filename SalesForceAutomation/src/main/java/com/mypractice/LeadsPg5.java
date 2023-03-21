package com.mypractice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LeadsPg5 extends BaseSeleniumTestCase {


	@BeforeClass
	public void Init() throws IOException
	{
		init();
		InitiliseProp();
		pageName="FirefoxVersion Page";
		InitReport();
		driver.get("https://login.salesforce.com/");
		Login();


	}

	/*@AfterClass
		public void teardown()
		{
			driver.quit();
		}
	 */
	
	@Test(priority=1,testName="Test Case 20",enabled=true)

	public void checkLeadsTabLink_TC20 ( ) throws InterruptedException, IOException {

		driver.findElement(By.id("Lead_Tab")).click();
		test.info("Leads homepage should be displayed");
		threadSleep(2);
		//Verify Leads Home page 

		String verify=driver.findElement(By.xpath("//h1[@class='pageType']")).getText();

		System.out.println(verify);
		//Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageType']")).getText(),"Leads");
		test.pass("checkLeadsTabLink_TC20 should navigate to Leads Home page");

		  String error =driver.findElement(By.xpath("//h1[@class='pageType']")).getText();
			String errorDisplay="Leads";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass("checkLeadsTabLink_TC20 executed successfully");	
				}
			
			else {
				test.fail("checkLeadsTabLink_TC20 not executed successfully");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    

	}


	@Test(priority=2,testName="Test Case 21",enabled=true)

	public void leadsSelectView_TC21() throws InterruptedException, IOException {

		checkLeadsTabLink_TC20 ();
		test.info("Leads homepage should be displayed");

		//Collect available lead option in string
		WebElement selectelement = driver.findElement(By.id("fcf"));
		//verify available option in leads
		verifyDropDown(selectelement,new String[]{"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads"},"Unable to verify leads");
		test.pass("leadsSelectView_TC21() should drop down and should show the related content ");
		

	}


	@Test(priority=3,testName="Test Case 22",enabled=true)
      public void functionalityOfTheGoButton_TC22() throws InterruptedException, IOException {

		//Leads home page should be displayed
		checkLeadsTabLink_TC20 ();
		test.info("Leads homepage should be displayed");
		threadSleep(2);

		List<WebElement> list = driver.findElements(By.xpath("//select[@id='fcf']/option[text()=\"Today's Leads\" and @selected='selected']"));

		if(list.size()==1) {

			driver.findElement(By.xpath("//input[@value=' Go! ']")).click();

		}
		else if (list.size()==0) {

			driver.findElement(By.xpath("//select[@id='fcf']/option[text()=\"Today's Leads\"]")).click();
		}

		threadSleep(2);

		//Clicked logout button
		test.info("Todays leads' should be selected from the drop down and the salesforce login page appears");
		Logout();
		test.pass("User should be logged in to the application");
		Login();

		//Leads home page should be displayed
		checkLeadsTabLink_TC20 ();
		test.pass("Leads homepage should be displayed");
		threadSleep(2);
		//Click on go button without changing list selection

		driver.findElement(By.xpath("//input[@value=' Go! ']")).click();
		test.info("Click on go button without changing list selection");
		threadSleep(2);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@value='New Lead']")).getAttribute("title").trim(), "New Lead");
		test.pass("functionalityOfTheGoButton_TC22() executed successfully");



	}

	@Test(priority=4,testName="Test Case 23",enabled=true)

	public void listItemTodaysLeadsWork_TC23() throws InterruptedException, IOException {


		checkLeadsTabLink_TC20 ();
		test.pass("Leads homepage should be displayed");
		threadSleep(2);
		//Click on go button without changing list selection
		driver.findElement(By.xpath("//input[@value=' Go! ']")).click();
		test.pass("Todays's Lead page should be displayed.");
		//Verified Today's lead page
		Assert.assertEquals(driver.findElement(By.xpath("//input[@value='New Lead']")).getAttribute("title").trim(), "New Lead");
        test.pass(pageName+" should display successfully");

	}







}
