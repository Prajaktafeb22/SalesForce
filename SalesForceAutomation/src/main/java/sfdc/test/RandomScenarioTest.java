package sfdc.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sfdc.pages.UserMenuPage;

public class RandomScenarioTest extends BaseTest{


	@BeforeTest
	public void setup() throws IOException {
		pageName  = "RandomScenarioTest";
		super.setup();
		userMenuPage = new UserMenuPage(driver);
		loginPage.loginToApp(driver, selectEnvironment("prod"));
	}
	
	@Test(priority=1,testName="Test Case 33",enabled=true)

	public void verifyFirstNameAndLatName_TC33() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a")).click();
		threadSleep(2);
		String UserName = driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a")).getText();
		System.out.println(UserName);
		Assert.assertTrue("Prajakta ABCD".equals(UserName), "UserName not formatted correctly");
		threadSleep(1);
		driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a")).click();
		threadSleep(2);


		//1.'User:FirstName LastName' page should be displayed. 2. This page should be same as the 'My Profile' page.		
		String MyProfilePage = "https://greendot5-dev-ed.my.salesforce.com/_ui/core/userprofile/UserProfilePage?tab=sfdc.ProfilePlatformFeed";	
		
		if(MyProfilePage.equals(driver.getCurrentUrl())) {
			test.pass("'User:FirstName LastName' page is same as the 'My Profile' page.");
		}
		else {
			test.fail("'User:FirstName LastName' page is not same as the 'My Profile' page.");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
	
	}



	@Test(priority=2,testName="Test Case 34",enabled=true)

	public void verifyEditedLastName_TC34() throws InterruptedException, IOException {


		//Home page should be displayed.                                    
		driver.findElement(By.id("home_Tab")).click();
		threadSleep(2);
		String HomePage = "https://greendot5-dev-ed.my.salesforce.com/home/home.jsp";	



		Assert.assertTrue(HomePage.equals(driver.getCurrentUrl()));
		threadSleep(2);

		//The 'User:FirstName LastName' page should be displayed. 
		verifyFirstNameAndLatName_TC33();
		threadSleep(2);


		//The 'Edit Profile' pop up should be displayed with the 'Contact' tab selected.
		driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']")).click();// Click open Edit Pop up


		driver.switchTo().frame("contactInfoContentId");// Switch to contact frame

		String SelectedTab= driver.findElement(By.xpath("//li[@id='contactTab' and @class='zen-current']")).getText();

		Assert.assertTrue("Contact".equals(SelectedTab),"Contact Tab not highlighted");

		threadSleep(1);
		driver.findElement(By.id("aboutTab")).click();// Click on about tab	

		threadSleep(1);

		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("ABCD");// Edit Last name to ABCD

		driver.findElement(By.xpath("//input[@value='Save All']")).click();//Click save all

		driver.switchTo().defaultContent();
		threadSleep(4);
		String CurrentuserName = driver.findElement(By.id("tailBreadcrumbNode")).getText().trim();
		System.out.println(CurrentuserName);

		//LastName of the account holder is displayed at the top left hand side of the page
		Assert.assertTrue(CurrentuserName.endsWith("ABCD"),"LastName of the account holder is not displayed at the top left hand side of the page");
		test.info("LastName of the account holder is displayed at the top left hand side of the page");

		threadSleep(2);
		String CurrentUserMenuTitle = driver.findElement(By.id("userNav")).getAttribute("title").trim();


		//1. The 'Edit Profile' pop up should be closed.
		//2. Verify that the updated LastName of the account holder is displayed at the top left hand side of the page.
		//3. Verify that the 'User menu for FirstName LastName' menu button shows the updated Last Name, at the top right hand side of the page.
		//4. Verify that the 'User:FirstName LastName' page has the updated LastName.


		Assert.assertTrue(CurrentUserMenuTitle.endsWith("ABCD"),"LastName of the account holder is not displayed at the top right hand side of the page");
		test.info(" Verify that the 'User:FirstName LastName' page has the updated LastName.");  


	}

	@Test(priority=3,testName="Test Case 35",enabled=true)

	public void verifyTabCustomization_TC35() throws InterruptedException, IOException {

		//The 'All Tabs' page should be displayed.
		driver.findElement(By.id("AllTab_Tab")).click();
		threadSleep(2);
		test.info("'All Tabs' page should be displayed.");

		driver.findElement(By.xpath("//input[@value='Customize My Tabs']")).click();
		threadSleep(2);

		//The 'Customize My Tabs' page should be displayed.
		Assert.assertTrue("Customize My Tabs".equals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim()),
				"Unable to navigate to Customize My Tabs");
		test.info("The 'Customize My Tabs' page should be displayed.");

		//The selected tab should be removed from the 'Selected Tabs' section and should be moved to the 'Available Tabs' section.
		threadSleep(2);
		
		String Selectedtaboption = driver.findElement(By.xpath("//select[@id='duel_select_1']/option[text()='Contacts']")).getText();
		driver.findElement(By.xpath("//select[@id='duel_select_1']/option[text()='Contacts']")).click();
		threadSleep(1);
		driver.findElement(By.id("duel_select_0_left")).click();// click of left 
		threadSleep(1);
		String Availabletaboption = driver.findElement(By.xpath("//select[@id='duel_select_0']/option[text()='Contacts']")).getText();
		
        Assert.assertTrue(Selectedtaboption.equals(Availabletaboption),"Selected tab not moved to Available tab");
		test.info("Selected tab moved to Available tab");

		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@title='Save']")).click();// Click on save

		//1. SalesForce application should be Launched.
		Assert.assertTrue("All Tabs".equals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim()),
				"Unable to navigate to All Tabs");
		
		test.pass("Able to navigate to All Tabs");

		List<WebElement> list =driver.findElements(By.id("Contact_Tab"));// Click on save

		//2. The tab removed at step 
		Assert.assertTrue(list.size()==0, "Tab option not removed");
		
		test.pass("Tab option not removed");

		//logout from main page
		Logout();

		loginPage.loginToApp(driver, selectEnvironment("prod"));

		list =driver.findElements(By.id("Contact_Tab"));// Click on save

		//3 should not be displayed in the tab bar.

		Assert.assertTrue(list.size()==0, "Removed Tab option added again");
        test.pass("Removed tab successfully removed");

	}

	@Test(priority=4,testName="Test Case 36",enabled=true)

	public void blockingAnEventInCalender_TC36() throws InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a")).click();
		threadSleep(2);

		//1. Verify Home page should be displayed.
		
		Assert.assertTrue(driver.getCurrentUrl().equals("https://greendot5-dev-ed.my.salesforce.com/home/home.jsp"),"Unable to reach Home page");// Verify home page
         test.pass("Able to reach Home page");
		
		
		driver.findElement(By.xpath("//div[@class='content']/span[@class='pageDescription']/a")).click();
		threadSleep(2);
		
		
		//2. Current date is displayed as a link below the FirstName and LastName in Day Month Date,Year format. Ex: Thursday July 18,2019
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim().startsWith("Calendar for"),"Unable to reach Calender Page");// Verify Calender page
        threadSleep(1);
        test.info("Able to reach Calender Page");

		List<WebElement> originalist = driver.findElements(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']"));

		driver.findElement(By.xpath("//div[@class='hourRowLabel timeCellDnD evenHour']/a[contains(text(),'8:00 PM')]")).click();

		// Store the current window handle		
		String currentWinHandle = driver.getWindowHandle();

		// Perform the click operation that opens new window		
		driver.findElement(By.xpath("//a[@title='Combo (New Window)']")).click();


		threadSleep(2);
		// Switch to new window opened

		Iterator<String> windowHandle = driver.getWindowHandles().iterator();

		while(windowHandle.hasNext())
		{
			String winHandle = windowHandle.next();
			if(!currentWinHandle.equals(winHandle))
			{
				driver.switchTo().window(winHandle);
				threadSleep(2);// Switch takes time hence wait here 
			}


		}
		threadSleep(1);
		driver.findElement(By.xpath("//a[text()='Other']")).click();
		// Switch back to original browser (first window)
		driver.switchTo().window(currentWinHandle);

		//Verify Able to set Other in Subject 
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='evt5'][@name='evt5']")).getAttribute("value").trim().equals("Other"),"Unable to set Other in Subject ");// Verify If Other is set in Subject
        test.info("Able to set Other in Subject ");

		// Now set end Time

		threadSleep(2);// Switch takes time hence wait here



		driver.findElement(By.id("EndDateTime_time")).sendKeys("");

		threadSleep(1);// Switch takes time hence wait here

		driver.findElement(By.id("timePickerItem_42")).click();

		threadSleep(1);// Switch takes time hence wait here 

		Assert.assertTrue(driver.findElement(By.id("EndDateTime_time")).getAttribute("value").trim().equals("9:00 PM"),"Unable to set End Time");// Verify If Other is set in Subject
        test.pass("Able to set End Time");
		
		driver.findElement(By.xpath("//input[@title='Save']")).click();

		List<WebElement> currnetList = driver.findElements(By.xpath("//div[@class='multiLineEventBlock dragContentPointer']"));


		Assert.assertTrue(originalist.size()+1 ==currnetList.size(),"Unable to Create Block Calender");// Verify if we were able to block a calendar
		test.pass("Verify if we were able to block a calendar");
		
		
	}


	@Test(priority=5,testName="Test Case 37",enabled=true)

	public void blockingEventWithWeeklyRecurrance_TC37() throws InterruptedException{

		//1. Home page should be displayed.                                                                                                                                                                                
		
		driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a")).click();
		threadSleep(2);
		test.pass(" Home page should be displayed.");
		
		
       //Verify to reach Home page
		Assert.assertTrue(driver.getCurrentUrl().equals("https://greendot5-dev-ed.my.salesforce.com/home/home.jsp"),"Unable to reach Home page");
		threadSleep(1);
		test.info("Ableto reach Home page");

		//Current date function-->click on current date function
		driver.findElement(By.xpath("//div[@class='content']/span[@class='pageDescription']/a")).click();
		threadSleep(2);

		// Verify Calendar page
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim().startsWith("Calendar for"),"Unable to reach Calender Page");
         test.info("Able to reach Calender Page");
		
		threadSleep(1);

		//Set the time according to function
		driver.findElement(By.xpath("//div[@class='hourRowLabel timeCellDnD evenHour']/a[contains(text(),'4:00 PM')]")).click();

		// Store the current window handle		
		String currentWinHandle = driver.getWindowHandle();

		// Perform the click operation that opens new window		
		driver.findElement(By.xpath("//a[@title='Combo (New Window)']")).click();

		threadSleep(2);
		// Switch to new window opened

		Iterator<String> windowHandle = driver.getWindowHandles().iterator();

		while(windowHandle.hasNext())
		{
			String winHandle = windowHandle.next();
			if(!currentWinHandle.equals(winHandle))
			{
				driver.switchTo().window(winHandle);
				threadSleep(2);// Switch takes time hence wait here 
			}


		}

		driver.findElement(By.xpath("//a[text()='Other']")).click();
		// Switch back to original browser (first window)
		driver.switchTo().window(currentWinHandle);

		// Verify If Other is set in Subject
		Assert.assertTrue(driver.findElement(By.id("evt5")).getAttribute("value").trim().equals("Other"),"Unable to set Other in Subject ");
		threadSleep(2);// Switch takes time hence wait here
		test.pass("Verify If Other is set in Subject");

		driver.findElement(By.id("EndDateTime_time")).sendKeys("");//activate the time zone

		threadSleep(1);// Switch takes time hence wait here

		driver.findElement(By.id("timePickerItem_38")).click();
		threadSleep(1);// Switch takes time hence wait here 

		Assert.assertTrue(driver.findElement(By.id("EndDateTime_time")).getAttribute("value").trim().equals("7:00 PM"),"Unable to set End Time");// Verify If Other is set in Subject

		driver.findElement(By.id("IsRecurrence")).click();
		WebElement checkbox = driver.findElement(By.id("IsRecurrence"));
		System.out.println("The checkbox is selection state is - " + checkbox.isSelected());
		
		//Recurrence verify
		Assert.assertTrue(checkbox.isSelected(),"Recurrence is not selected");
		test.info("Recurrence is selected");
		

		List<WebElement> frequecyRadiobutton = driver.findElements(By.id("rectypeftd"));

		//Verify Frequency not displayed
		Assert.assertTrue(frequecyRadiobutton.size() > 0,"Frequency not displayed");
		test.pass("Verify Frequency displayed or not");
		

		List<WebElement> RecurrenceStartDateTime = driver.findElements(By.id("RecurrenceStartDateTime"));

		
		// Verify Recurrence Start Date Time not displayed
		Assert.assertTrue(RecurrenceStartDateTime.size() > 0,"Recurrence Start Date Time not displayed");
		test.info(" Verify Recurrence Start Date Time displayed");

		List<WebElement> RecurrenceEndDateOnly = driver.findElements(By.id("RecurrenceEndDateOnly"));

		//Recurrence End Date not displayed
		Assert.assertTrue(RecurrenceEndDateOnly.size() > 0,"Recurrence End Date not displayed");
		test.pass("Verify Recurrence End Date displayed");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 14);
		driver.findElement(By.id("RecurrenceEndDateOnly")).sendKeys(dateFormat.format(c.getTime()),Keys.TAB);
		threadSleep(1);
		driver.findElement(By.xpath("//input[@title='Save']")).click();// Click save
		threadSleep(2);      
		WebElement monthView =  driver.findElement(By.xpath("//span[@class='dwmIcons']/a[@title='Month View']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", monthView);

		//Current updated page with month view displayed successfully
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim().startsWith("Calendar for"), "Current updated page with month view is not displayed successfully");
        test.pass("Current updated page with month view displayed successfully");


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
