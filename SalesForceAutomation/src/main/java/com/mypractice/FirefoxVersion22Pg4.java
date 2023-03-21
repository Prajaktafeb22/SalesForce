package com.mypractice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirefoxVersion22Pg4 extends BaseSeleniumTestCase {

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

	@Test(priority=1,testName="Test Case 15",enabled=true)
	public void selectUserMenuTC15() throws InterruptedException {

		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");
		threadSleep(2);

		//Opportunities home page is displayed
		test.info("Click on opportunities link from the main menu");
		driver.switchTo().defaultContent();

		verifyOpportunitiesMenu(driver);
		test.pass("Verify opportunities drop down is present,selectUserMenuTC15 executed successfully");


	}

	@Test(priority=2,testName="Test Case 16",enabled=true)
	public void createANewOpty_TC16() throws InterruptedException, IOException {

		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");

		opportunitiesAvailableOpt(driver,"My Opportunities") ;
		test.info("Click on opportunities link from the main menu");
		threadSleep(2);
		driver.findElement(By.id("createNewLabel")).click();
		threadSleep(1);
		driver.findElement(By.xpath("//a[@class='opportunityMru menuButtonMenuLink']")).click();
		
		//New Opportunity set
		driver.findElement(By.xpath("//input[@id='opp3']")).sendKeys("Opportunity1");

		

		synchronized(driver) {
			driver.wait(2000);
		}
		driver.findElement(By.xpath("//input[@id='opp4'][@name='opp4']")).click();
		threadSleep(1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		threadSleep(1);
		driver.findElement(By.id("opp9")).sendKeys(formatter.format(new Date()));
		driver.findElement(By.xpath(" //*[@id=\"opp11\"]")).sendKeys("Needs Analysis");
		threadSleep(1);
		driver.findElement(By.xpath(" //*[@id=\"opp6\"]")).sendKeys("Web");
		driver.findElement(By.xpath(" //*[@id=\"opp12\"]")).clear();
		driver.findElement(By.xpath(" //*[@id=\"opp12\"]")).sendKeys("25");

		
		driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]")).click();
		
        //Verify new opportunity page 
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText(), "Opportunity1");

        test.pass("New Opportunity page is displayed with Opportunity details.");
        
        
		  String error = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
			String errorDisplay="Opportunity1";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass("New Opportunity page is displayed with Opportunity details.");	
				}
			
			else {
				test.fail("New Opportunity page is not displayed with Opportunity details.");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    
        

	}



	@Test(priority=3,testName="Test Case 17",enabled=true)
	public void testOpportunityPipeLineReport_TC17() throws InterruptedException, IOException {

		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");
		
		threadSleep(2);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim(), "Opportunities");
		//Report generate
		test.info("Opportunities home page is displayed");
		//Click Opportunity pipeline page
		
		opportunitiesAvailableOpt(driver,"Opportunity Pipeline") ;
		
		
		//driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim(), "Opportunity Pipeline");
		
		  String error = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
			String errorDisplay="Opportunity Pipeline";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass("testOpportunityPipeLineReport_TC17 executed successfully");	
				}
			
			else {
				test.fail("Report Page with the Opportunities that are pipelined not executed successfully ");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    
      
			test.pass("testOpportunityPipeLineReport_TC17 executed successfully");
		
	}


	@Test(priority=4,testName="Test Case 18",enabled=true)
	public void testStuckOpportunitiesReport_TC18 () throws InterruptedException, IOException {

		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");
		threadSleep(2);
		//Report generate
		test.info("Opportunities home page is displayed");
		//Clicked on Stuck Opportunity
		driver.findElement(By.xpath("//a[text()='Stuck Opportunities']")).click();
		System.out.println("Opening Stuck oppurtunities ");
		threadSleep(5);		
		//Verify Stuck Opportunities page
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim(), "Stuck Opportunities");
		
		
		test.pass("Report Page with the Opportunities that are Stuck will be displayed.");	
		  String error = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
			String errorDisplay="Stuck Opportunities";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass(" testStuckOpportunitiesReport_TC18 executed successfully");	
				}
			
			else {
				test.fail("Report Page with the Opportunities that are Stuck not executed successfully ");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    
    
	}
	
	
	@Test(priority=3,testName="Test Case 19",enabled=true)
	public void testQuarterlySummaryReport_TC19() throws InterruptedException, IOException {
		
		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");
		threadSleep(2);
		//Report generate
		test.info("Opportunities home page is displayed");
		
		//Clicked on Quarterly Summary
		
		driver.findElement(By.xpath("//select[@id='quarter_q']//option[@value='curnext1']")).click(); 
		
		driver.findElement(By.xpath("//select[@id='open']//option[@value='all']")).click(); 
		threadSleep(2);
		
		//Clicked Run report
		driver.findElement(By.xpath("//input[@value='Run Report']")).click(); 
		threadSleep(2);
		
		//Verify Opportunity Report
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim(), "Opportunity Report");
		
		test.pass("Report Page with the Opportunities that satisfies the search criteria will be displayed.");
		
		  String error = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
			String errorDisplay="Opportunity Report";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass(" testQuarterlySummaryReport_TC19() executed successfully");	
				}
			
			else {
				test.fail("Report Page with the Opportunities that satisfies the search criteria not executed successfully ");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    
		
	}

}
