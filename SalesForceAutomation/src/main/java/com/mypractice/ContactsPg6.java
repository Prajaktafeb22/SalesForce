package com.mypractice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContactsPg6 extends BaseSeleniumTestCase {

	@BeforeClass
	public void Init() throws IOException {
		init();
		InitiliseProp();
		pageName="FirefoxVersion Page";
		InitReport();
		driver.get("https://login.salesforce.com/");
		Login();

	}


	/*@AfterClass 
	public void teardown() { 
		driver.quit(); 

	}
	 */


	@Test(priority=1,testName="Test Case 25",enabled=true)

	public void createNewcontact_TC25() throws InterruptedException {

		//Navigate to Home page
		driver.findElement(By.xpath("//a[@title='Home Tab']")).click();

		threadSleep(2);
		//clicked on Create new 
		driver.findElement(By.xpath("//div[@class='menuButtonButton']//span[@class='menuButtonLabel']")).click();
		threadSleep(1);

		//Select Contact menu from create new option
		Click(driver.findElement(By.xpath("//a[@class='contactMru menuButtonMenuLink']")));
		threadSleep(2);


		//Insert last name 
		driver.findElement(By.xpath("//input[@id='name_lastcon2']")).clear();
		driver.findElement(By.xpath("//input[@id='name_lastcon2']")).sendKeys("Bhandare");
		test.info("Last name should be enterd in Last name field");

		//Insert Account name
		driver.findElement(By.xpath("//*[@id=\"con4\"]")).clear();
		threadSleep(1);
		driver.findElement(By.xpath("//*[@id=\"con4\"]")).sendKeys("United Oil & Gas Corp.");
		driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]")).click();
		test.info("Account name should be displayed in account name field");

		String ContactDetails = driver.findElement(By.id("ep")).getText();
		threadSleep(1);
		String action = "United Oil & Gas Corp.";
		String filename = "United Oil & Gas Corp.";

		//Verify  contact should be created
		Assert.assertTrue(ContactDetails.contains(action) && ContactDetails.contains(filename), "Data is not uploaded");
		test.pass(" contact should be created");
		
	}

	@Test(priority=2,testName="Test Case 26",enabled=true)

	public void createNewView_TC26() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();
		threadSleep(2);

		driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		threadSleep(2);

		// Verify the current page.
		VerifyPage("Contacts");
		test.info("Contact Home page should be displayed");

		driver.findElement(By.id("fname")).clear();
		threadSleep(2);

		//View Name should be entered in the view name field
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		driver.findElement(By.id("fname")).sendKeys("Bhandare1234" + timeStamp);
		test.info("View Name should be entered in the view name field");

		//View Unique Name should be entered in the view Unique Name field.
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("Bhandare1234" + timeStamp);

		test.info("View Unique Name should be entered in the view Unique Name field.");

		driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")).click();

		threadSleep(1);
		String selectedOption = driver.findElement(By.className("title"))
				.findElement(By.xpath("//*[@selected='selected']")).getText();

		//Contacts Home page is opened. Created View name is displayed in drop down in that page by default. 
		Assert.assertEquals("Bhandare1234" + timeStamp, selectedOption, "Last Edited Name is not equal");
		test.pass("Contacts Home page is opened.Created View name is displayed in drop down in that page by defalut. ");


	}

	@Test(priority=3,testName="Test Case 27",enabled=true)

	public void checkRecentlyContact_TC27() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();

		threadSleep(2);

		// Verify the current page.
		VerifyPage("Contacts");
		test.info("Contact Home page should be displayed");

		threadSleep(2);
		driver.findElement(By.xpath("//*[@id=\"hotlist_mode\"]"))
		.findElement(By.xpath("//option[text()='Recently Modified']")).click();
		threadSleep(1);
		driver.findElement(By.xpath("//*[@id=\"hotlist_mode\"]"))
		.findElement(By.xpath("//option[text()='Recently Created']")).click();
		threadSleep(2);
		System.out.println(driver.getCurrentUrl());

		//Recently created contacts verified
		Assert.assertTrue(
				driver.getCurrentUrl().equals("https://greendot5-dev-ed.my.salesforce.com/003/o?hotlist_mode=2"),
				"Recently created contact page is not verified");

		System.out.println("Recently created contact page is verified");
		test.pass("Recently created contacts should be displayed");


	}

	@Test(priority=4,testName="Test Case 28",enabled=true)

	public void checkMyContactView_TC28() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();

		threadSleep(2);
		// Verify the current page.
		VerifyPage("Contacts");

		test.info("Contact Home page should be displayed");
		// need to add assert here
		threadSleep(1);
		String selectedoption = driver.findElement(By.id("fcf")).findElement(By.xpath("//option[@selected='selected']")).getText();
		System.out.println(selectedoption);

		if(selectedoption.equals("My Contacts"))
		{
			driver.findElement(By.xpath("//input[@name='go'][@title='Go!']")).click();
		}
		else
		{
			driver.findElement(By.id("fcf")).findElement(By.xpath("//option[text()='My Contacts']")).click();
		}

		threadSleep(2);


		//My contacts View verified
		Assert.assertTrue("New Contact".equals(driver.findElement(By.xpath("//input[@name='newContact'] [@title='New Contact']")).getAttribute("value")),
				"My Contact Page is not displayed");	
		test.pass("My contacts View should be displayed");



	}

	@Test(priority=5,testName="Test Case 29",enabled=true)

	public void viewAContact_TC29() throws InterruptedException {
		//Link to Contact Home page
		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();
		threadSleep(2);

		String contactName = driver.findElement(By.id("bodyCell")).findElement(By.className("dataCell")).getText();		
		driver.findElement(By.id("bodyCell")).findElement(By.className("dataCell")).findElement(By.xpath("a")).click();

		//Contact Page related to contact name, which contains entire information about that contact name verified.					
		Assert.assertTrue(contactName.equals(driver.findElement(By.xpath("//h2[@class='topName']")).getText()),"Not Navigated to Contact Details");
		test.pass("Contact Page related to contact name, which contains entire information about that contact name should be displayed");


	}

	@Test(priority=6,testName="Test Case 30",enabled=true)

	public void checkTheErrorMsg_TC30() throws InterruptedException {
		//Contacts page is opened.
		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();
		threadSleep(1);
		test.info("Contacts page is opened.");

		//Create New View page is opened.
		driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		threadSleep(2);
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
		threadSleep(1);

		driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("EFGH");

		driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")).click();
		test.pass("Create New View page is opened.");
		String errorMsg = driver
				.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"))
				.getText();

		String error = "Error: You must enter a value";
		String actualMsg = "Error: You must enter a value";

		//Verify Error message is appeared under the View Name field. The Error message appears as "Error: You must enter a value".
		Assert.assertTrue(errorMsg.contains(error) && errorMsg.contains(actualMsg), "Error is not showing");
		test.pass("Error message is appeared under the View Name field. The Error message appears as \"Error: You must enter a value\".");

	}

	@Test(priority=7,testName="Test Case 31",enabled=true)

	public void checkTheCancelButton_TC31() throws InterruptedException {
		//Logged in salesforce.com
        //Contacts page is opened.
		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();
		threadSleep(2);
        test.info("Contacts page is opened.");
        
        //Create New View page is opened.
		driver.findElement(By.xpath("//a[text()='Create New View']")).click();
		threadSleep(2);
		test.info("Create New View page is opened.");

		//The view name and Unique name should be entered
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("ABCD");

		driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
		threadSleep(1);
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("EFGH");

		
		driver.findElement(By.xpath("//input[@value='Cancel']")).click();
		threadSleep(2);
		test.info("The view name and Unique name should be entered");
		
		// Verify the current page.
		VerifyPage("Contacts");
        test.pass("Updated contact page should be display");

	}

	@Test(priority=8,testName="Test Case 32",enabled=true)

	public void checkTheSaveAndNweButton_TC32() throws InterruptedException {

		
		//Contacts page is opened.
		driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a")).click();
		threadSleep(2);
		
        //Contact Edit: New Contact Page is displayed
		driver.findElement(By.xpath("//a[text()='Create New View']")).click();
		threadSleep(2);

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		String ContactName = "ABCD" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys(ContactName);

		driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
		threadSleep(1);
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("EFGH");

		driver.findElement(By.xpath("//input[@name='save']")).click();
		threadSleep(2);


     //New contact is created. Contact Edit: New Contact Page is displayed
		Assert.assertTrue(ContactName.equals(driver.findElement(By.xpath("//*[@class='title' and @name='fcf']/option[@selected='selected']")).getText()),
				"New Contact not created Suscessfully");
       test.pass("New contact is created.Contact Edit New Contact Page is created");

	}

}
