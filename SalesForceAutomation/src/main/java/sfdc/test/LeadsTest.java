package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sfdc.pages.CreateOptyPage;
import sfdc.pages.LeadsPage;
import sfdc.utilities.ReusableUtils;




public class LeadsTest extends BaseTest{

	
	@BeforeTest
	public void setup() throws IOException {
		pageName  = " LeadsTest";
		super.setup();
		leadsPage = new LeadsPage(driver);
		loginPage.loginToApp(driver, selectEnvironment("prod"));
		
	}
	
	@Test(priority=1,testName="Test Case 20",enabled=true)

	public void checkLeadsTabLink_TC20 (Method name ) throws InterruptedException, IOException {

		test= extent.createTest(name.getName());
        //Leads link selected
		clickOnElement(leadsPage.leadTab);
		test.info("Leads homepage should be displayed");
		threadSleep(2);
		//Verify Leads Home page 
		Assert.assertEquals(leadsPage.leadsHomePage.getText(),"Leads");
		threadSleep(2);
		test.pass("checkLeadsTabLink_TC20 should navigate to Leads Home page");

		  String error =leadsPage.leadsUpdatedHomePage.getText();
			String errorDisplay="Leads";
			if(error.equals(errorDisplay)) {
				
				test.pass("checkLeadsTabLink_TC20 executed successfully");	
				}
			
			else {
				test.fail("checkLeadsTabLink_TC20 not executed successfully");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    

		reusableUtils.clickOnElement(leadsPage.userMenuOption);	
		reusableUtils.clickOnElement(leadsPage.logOut);	
		threadSleep(2);	
		String logInText = driver.findElement(By.xpath("//img[@id='logo']")).getText();	
		System.out.println("Login starts here"+logInText);
			
	}
	
	
	@Test(priority=2,testName="Test Case 21",enabled=true)

	public void leadsSelectView_TC21(Method name) throws InterruptedException, IOException {

		test = extent.createTest(name.getName());
		checkLeadsTabLink_TC20(name) ;
		test.info("Leads homepage should be displayed");

		//Collect available lead option in string
		WebElement selectelement = driver.findElement(By.id("fcf"));
		//verify available option in leads
		reusableUtils.verifyDropDown(selectelement,new String[]{"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads"},"Unable to verify leads");
		test.pass("leadsSelectView_TC21() should drop down and should show the related content ");
		

	}


	@Test(priority=3,testName="Test Case 22",enabled=true)
      public void functionalityOfTheGoButton_TC22(Method name) throws InterruptedException, IOException {
		test = extent.createTest(name.getName());
		//Leads home page should be displayed
		checkLeadsTabLink_TC20(name) ;
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
		loginPage.loginToApp(driver, selectEnvironment("prod"));;

		//Leads home page should be displayed
		checkLeadsTabLink_TC20 (name);
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

	public void listItemTodaysLeadsWork_TC23(Method name) throws InterruptedException, IOException {

		test = extent.createTest(name.getName());
		checkLeadsTabLink_TC20 (name);
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



