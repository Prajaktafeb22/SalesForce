package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sfdc.utilities.ReusableUtils;
import sfdc.pages.CreateOptyPage;

public class CreateOptyTest extends BaseTest{


	@BeforeTest
	public void setup() throws IOException {
		pageName  = " CreateOptyTest";
		super.setup();
		createOptyPage = new CreateOptyPage(driver);
		loginPage.loginToApp(driver, selectEnvironment("prod"));
		
	}

	@Test(priority=1,testName="Test Case 15",enabled=true)
	public void selectUserMenuTC15(Method name) throws InterruptedException, IOException {
		
		test.info("Log in to the app");
		//String test1 = driver.findElement(By.xpath("//h1[@id='start-your-free-trial-no-credit-card-required-no-software-to-install']")).getText();
		//System.out.println(test1);
		test= extent.createTest(name.getName());
		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");
		threadSleep(2);

		//Opportunities home page is displayed
		test.info("Click on opportunities link from the main menu");
		driver.switchTo().defaultContent();

		CreateOptyPage.verifyOpportunitiesMenu(driver);
		test.pass("Verify opportunities drop down is present,selectUserMenuTC15 executed successfully");


	}

	@Test(priority=2,testName="Test Case 16",enabled=true)
	public void createANewOpty_TC16(Method name) throws InterruptedException, IOException {

		test= extent.createTest(name.getName());
		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");

		reusableUtils.opportunitiesAvailableOpt(driver,"My Opportunities") ;
		test.info("Click on opportunities link from the main menu");
		threadSleep(2);
		//User Menu option selected
		reusableUtils.clickOnElement(createOptyPage.createNewdropdown);
		threadSleep(2);
		//Opportunity tab selected from create new option
		reusableUtils.clickOnElement(createOptyPage.opportunityTab);
		
		//New Opportunity set
		threadSleep(2);
		reusableUtils.insertText(createOptyPage.opportunityNameField, "Opportunity1");
		reusableUtils.clickOnElement(createOptyPage.accountNameField);
		
		threadSleep(1);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		threadSleep(1);
		
		ReusableUtils.insertText(createOptyPage.closeDateField, "formatter.format(new Date())");
		ReusableUtils.insertText(createOptyPage.stageField, "Needs Analysis");
		
		threadSleep(1);
		ReusableUtils.insertText(createOptyPage.leadSourceField, "Web");
		
		reusableUtils.clickOnElement(createOptyPage.probabilityField);
		ReusableUtils.insertText(createOptyPage.probabilityField, "25");

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
	public void testOpportunityPipeLineReport_TC17(Method name) throws InterruptedException, IOException {

		test= extent.createTest(name.getName());
		driver.get("https://greendot5-dev-ed.my.salesforce.com/006/o");
		
		threadSleep(2);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim(), "Opportunities");
		//Report generate
		test.info("Opportunities home page is displayed");
		//Click Opportunity pipeline page
		
		reusableUtils.opportunitiesAvailableOpt(driver,"Opportunity Pipeline") ;
		threadSleep(4);
		
		 Click(driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']")));
		 threadSleep(2);
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
	public void testStuckOpportunitiesReport_TC18 (Method name) throws InterruptedException, IOException {

		test= extent.createTest(name.getName());
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
	public void testQuarterlySummaryReport_TC19(Method name) throws InterruptedException, IOException {
		
		test= extent.createTest(name.getName());
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
