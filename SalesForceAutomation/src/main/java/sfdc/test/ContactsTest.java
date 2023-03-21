package sfdc.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sfdc.pages.ContactsPage;
import sfdc.pages.LeadsPage;

public class ContactsTest extends BaseTest{

	@BeforeTest
	public void setup() throws IOException {
		pageName  = " ContactsTest";
		super.setup();
		contactsPage = new ContactsPage(driver);
		loginPage.loginToApp(driver, selectEnvironment("prod"));
		driver.manage().window().fullscreen();

	}


	@Test(priority=1,testName="Test Case 25",enabled=true)

	public void createNewcontact_TC25() throws InterruptedException, IOException {
		
		threadSleep(2);
		//Clicked Contacts menu 
		Click(contactsPage.contactsHomePage);
		threadSleep(2);
		test.info("Contacts option selected");
		//Create new contact option selected
		Click(contactsPage.newCreateContact);
		//verify new contact home page is loaded
		threadSleep(2);
		if((contactsPage.newContactPage.getText()).contains("New Contact")) {

			System.out.println("Contact Home page is verified");
			test.info("Verify Contact Home page");
		}
		else {
			System.out.println("Contact Home page is not verified");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
       //Clear last name 
		reusableUtils.clearElement(contactsPage.lastName);
		threadSleep(2);
		//Insert last name
		reusableUtils.insertText(contactsPage.lastName, "Bhandare");
		test.info("Last name should be enterd in Last name field");

		//Insert Account name
		threadSleep(2);
		reusableUtils.clearElement(contactsPage.accountName);
		threadSleep(2);
        //Account value set
		reusableUtils.insertText(contactsPage.accountName, "United Oil & Gas Corp.");
		
		//save button clicked
		BaseTest.clickOnElement(contactsPage.saveAccountName);
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
	public void createNewView_TC26() throws InterruptedException, IOException {

		//Contact page link selected
		reusableUtils.clickOnElement(contactsPage.contactPage);
		threadSleep(2);
		if(contactsPage.contactPage.getText().equals("Contacts")) {
			System.out.println("Contacts Home page verified");
			test.pass("Contact home page is verified");
			
		}
		else {
			System.out.println("Contacts Home page is not verified");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
		
		//Create new view link
		reusableUtils.clickOnElement(contactsPage.createNewViewLink);
		threadSleep(2);
		
		//driver.findElement(By.id("fname")).clear();
		reusableUtils.clearElement(contactsPage.createNewViewTextArea);
		threadSleep(2);

		//View Name should be entered in the view name field
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		reusableUtils.insertText(contactsPage.createNewViewTextArea,"Bhandare1234"+timeStamp);
		//driver.findElement(By.id("fname")).sendKeys("Bhandare1234" + timeStamp);
		test.info("View Name should be entered in the view name field");

		//View Unique Name should be entered in the view Unique Name field.
		reusableUtils.clearElement(contactsPage.createUniqueNameTextArea);
		threadSleep(2);
		reusableUtils.insertText(contactsPage.createUniqueNameTextArea,"Bhandare1234" + timeStamp);

		test.info("View Unique Name should be entered in the view Unique Name field.");

		//save button 
		reusableUtils.clickOnElement(contactsPage.saveCreateNewView);
		threadSleep(1);
		String selectedOption = driver.findElement(By.className("title"))
				.findElement(By.xpath("//*[@selected='selected']")).getText();

		//Contacts Home page is opened. Created View name is displayed in drop down in that page by default. 
		Assert.assertEquals("Bhandare1234" + timeStamp, selectedOption, "Last Edited Name is not equal");
		test.pass("Contacts Home page is opened.Created View name is displayed in drop down in that page by defalut. ");


	}

	
	
	@Test(priority=3,testName="Test Case 27",enabled=true)
    public void checkRecentlyContact_TC27() throws InterruptedException {
		
        reusableUtils.clickOnElement(contactsPage.contactPage);
        threadSleep(2);
        // Verify the current page.
		VerifyPage("Contacts");
		test.info("Contact Home page should be displayed");
        threadSleep(2);
        //Recently modified link
		 reusableUtils.clickOnElement(contactsPage.recentlyModifiedLink);
		threadSleep(1);
		//Recently created link
		 reusableUtils.clickOnElement(contactsPage.recentlyCreatedLink);
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

	public void checkMyContactView_TC28() throws InterruptedException, IOException {

		reusableUtils.clickOnElement(contactsPage.contactPage);
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
			test.addScreenCaptureFromPath(takeScreenshot());
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
		reusableUtils.clickOnElement(contactsPage.contactPage);
		threadSleep(2);

		String contactName = driver.findElement(By.id("bodyCell")).findElement(By.className("dataCell")).getText();		
		driver.findElement(By.id("bodyCell")).findElement(By.className("dataCell")).findElement(By.xpath("a")).click();
		threadSleep(2);
		//Contact Page related to contact name, which contains entire information about that contact name verified.					
		Assert.assertTrue(contactName.equals(driver.findElement(By.xpath("//h2[@class='topName']")).getText()),"Not Navigated to Contact Details");
		test.pass("Contact Page related to contact name, which contains entire information about that contact name should be displayed");


	}

	@Test(priority=6,testName="Test Case 30",enabled=true)

	public void checkTheErrorMsg_TC30() throws InterruptedException, IOException {
		//Contacts page is opened.
		reusableUtils.clickOnElement(contactsPage.contactPage);
		threadSleep(1);
		test.info("Contacts page is opened.");

		//Create New View page is opened.Save
		threadSleep(2);
		reusableUtils.clickOnElement(contactsPage.createNewViewLink);
		//driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		threadSleep(2);
		reusableUtils.clearElement(contactsPage.createNewViewTextArea);
		//driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
		threadSleep(1);

		//driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("EFGH");
		
		reusableUtils.insertText(contactsPage.createUniqueNameTextArea,"EFGH");
		threadSleep(1);
		//driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")).click();
		reusableUtils.clickOnElement(contactsPage.saveCreateNewView);
		test.pass("Create New View page is opened.");
		/*String errorMsg = driver
				.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"))
				.getText();*/

		String errorMsg = contactsPage.ErrorCreateViewName.getText();
		System.out.println(errorMsg);
		String actualMsg = "Error: You must enter a value";

		if(errorMsg.equals(actualMsg)) {

			System.out.println("Error message is showing");
		}
		else {
		      System.out.println("Error message is not showing");
		      test.addScreenCaptureFromPath(takeScreenshot());
     		}
		//Verify Error message is appeared under the View Name field. The Error message appears as "Error: You must enter a value".
		Assert.assertTrue(errorMsg.contains(actualMsg), "Error is not showing");
		test.pass("Error message is appeared under the View Name field. The Error message appears as \"Error: You must enter a value\".");

	}

	@Test(priority=7,testName="Test Case 31",enabled=true)

	public void checkTheCancelButton_TC31() throws InterruptedException {
		//Logged in salesforce.com
		//Contacts page is opened.
		reusableUtils.clickOnElement(contactsPage.contactPage);
		threadSleep(2);
		test.info("Contacts page is opened.");
		
		String savedView =contactsPage.afterSavedCurrentCreateViewName.getText();

		//Create New View page is opened.
		reusableUtils.clickOnElement(contactsPage.createNewViewLink);
		
		threadSleep(2);
		test.info("Create New View page is opened.");
		threadSleep(2);
		//The view name and Unique name should be entered
		reusableUtils.clearElement(contactsPage.createNewViewTextArea);
		
		threadSleep(1);
		reusableUtils.insertText(contactsPage.createNewViewTextArea,"ABCD");
		
		threadSleep(1);
		reusableUtils.clearElement(contactsPage.createUniqueNameTextArea);
		threadSleep(1);
		reusableUtils.insertText(contactsPage.createUniqueNameTextArea,"EFGH");
		
        //Cancel created view name
        reusableUtils.clickOnElement(contactsPage.cancelCreateViewName);
        
		//Contacts Home page is displayed and the View ABCD should not be created.
        Assert.assertTrue(savedView.equalsIgnoreCase(contactsPage.afterSavedCurrentCreateViewName.getText()),"The last saved account is same");
        
		threadSleep(2);
		test.info("The view name and Unique name should be entered");

		// Verify the current page.
		VerifyPage("Contacts");
		test.pass("Updated contact page should be display");

	}

	
	
	
	
	@Test(priority=8,testName="Test Case 32",enabled=true)
   public void checkTheSaveAndNweButton_TC32() throws InterruptedException {


		//Contacts page is opened.
		reusableUtils.clickOnElement(contactsPage.contactPage);
		threadSleep(2);

		//Contact Edit: New Contact Page is displayed
		//driver.findElement(By.xpath("//a[text()='Create New View']")).click();
		reusableUtils.clickOnElement(contactsPage.newCreateContact);
		threadSleep(2);

		//driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		//
		reusableUtils.clearElement(contactsPage.editNewContactLastName);
		String ContactName = "Indian" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		//driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys(ContactName);
		reusableUtils.insertText(contactsPage.editNewContactLastName, ContactName);

		
		reusableUtils.clearElement(contactsPage.editNewContactAccountName);
		
		//driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
		threadSleep(1);
		//driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys("EFGH");
		reusableUtils.insertText(contactsPage.editNewContactAccountName,"Global Media");
		
		reusableUtils.clickOnElement(contactsPage.saveNewContactPage);
		driver.findElement(By.xpath("//input[@name='save']")).click();
		threadSleep(2);

		//h2[@class="topName"]
				
		threadSleep(2);		
		Assert.assertTrue(contactsPage.afterSavedNewContactPage.getText().equals(ContactName),
				"New Contact not created Suscessfully");
		test.pass("New Contact is created.Contact Edit New Contact Page is created");

	}









}
