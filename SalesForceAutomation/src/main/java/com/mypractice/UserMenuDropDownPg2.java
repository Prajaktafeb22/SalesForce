package com.mypractice;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserMenuDropDownPg2 extends BaseSeleniumTestCase {

	/**
	 * @throws IOException 
	 * 
	 */
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

	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
	/**
	 * Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
	 */
	@Test(priority=1,testName="Test Case 5",enabled=true)	
	public void selectUserMenuUserNameTC5() {

		//SalesForce login page is launched and application home page is logged in with correct user name.

		driver.findElement(By.id("userNavLabel")).click();
		test.info("Check available user menu ");

		//Verifying available menu 
		VerifyMenu(driver);
		test.info("Verify available user menu.");
		//Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed

		//selecting provided menu
		selectOptionInUserMenu (driver,"My Profile");
		test.info("User menu drop down shpuld be available");


	}

	@Test(priority=2,testName="Test Case 6",enabled=true)	
	public void selectMyProfile_TC6() throws InterruptedException {

		driver.findElement(By.id("userNavLabel")).click();

		//Verifying available menu 
		VerifyMenu(driver);
		test.info("Verify available user menu.");

		//selecting provided menu
		selectOptionInUserMenu (driver,"My Profile");
		threadSleep(2);

		//Edit profile pop up window is displayed with contact and About tabs to edit. 
		driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img")).click();// Click Edit profile 
		driver.switchTo().frame("contactInfoContentId");
		driver.findElement(By.xpath("//li[@id='aboutTab']//a[@role='tab']")).click();

		//clear 
		driver.findElement(By.xpath("//input[@id='lastName'][@class='lastName zen-inputFull zen-input']")).clear();
		driver.findElement(By.xpath("//input[@id='lastName'][@class='lastName zen-inputFull zen-input']")).sendKeys("Bhandare123");

		driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]")).click();// Save clicked
		////UserProfilePage with changed <last name> is displayed
		test.info("UserProfilePage with changed last name is displayed");

		//<text> entered should be displayed on the page
		threadSleep(2);
		driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img")).click();// Click Edit profile
		driver.switchTo().frame("contactInfoContentId");
		driver.findElement(By.xpath("//li[@id='aboutTab']//a[@role='tab']")).click();
		threadSleep(1);
		Assert.assertEquals(driver.findElement(By.id("lastName")).getAttribute("value"), "Bhandare123","Name not Updated");
		test.info("Check edited information updated successfully");

	}

	@Test(priority=3,testName="Test Case 7",enabled=true)	
	public void selectMyProfileSub_TC7() throws InterruptedException {


		//Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
		threadSleep(1);
		driver.findElement(By.id("userNavLabel")).click();
		//My profile link selected
		selectOptionInUserMenu (driver,"My Profile");
		threadSleep(2);	

		driver.findElement(By.id("publisherAttachContentPost")).click(); 
		threadSleep(1);
		driver.findElement(By.id("chatterUploadFileAction")).click(); 
		threadSleep(1);
		driver.findElement(By.id("chatterFile")).sendKeys("C:\\Users\\DECOMM\\Desktop\\111.txt");  
		threadSleep(1);
		driver.findElement(By.id("publishersharebutton")).click();  
		threadSleep(1);


		String ContentUploaded =driver.findElements(By.className("feeditembodyandfooter")).get(0).getText(); 
		String action ="posted a file.";
		String fileName ="posted a file.";
		Assert.assertTrue(ContentUploaded.contains(action) && ContentUploaded.contains(fileName),
				"Input File not uploaded");

		test.info("Check input file uploaded successfully");

	}


	@Test(priority=4,testName="Test Case 7",enabled=true)	
	public void LoginHistory_TC701() throws InterruptedException {
		driver.findElement(By.id("userNavLabel")).click();
		//Verifying available menu 
		VerifyMenu(driver);
		test.info("Verify available menu");

		//selecting provided menu
		selectOptionInUserMenu (driver,"My Settings");
		test.info("My setting option selected");

		threadSleep(1);
		driver.findElement(By.id("PersonalInfo")).click();
		threadSleep(1);
		driver.findElement(By.id("LoginHistory_font")).click();
		threadSleep(2);
		driver.findElement(By.className("pShowMore")).click();
		threadSleep(5);

		File folder = new File("C:\\Users\\DECOMM\\Downloads");

		//List the files on that folder
		File[] listOfFiles = folder.listFiles();
		boolean found = false;
		File f = null;
		//Look for the file in the files
		// You should write smart REGEX according to the filename
		for (File listOfFile : listOfFiles) {
			if (listOfFile.isFile()) {
				String fileName = listOfFile.getName();
				System.out.println("File " + listOfFile.getName());
				if (fileName.startsWith("LoginHistory")) {
					f = new File(fileName);
					found = true;
				}
			}

			if (found)
				break;
		}
		Assert.assertTrue(found, "Downloaded document is not found");
		f.delete();
		test.pass("LoginHistory testcase executed successfully");

	}



	@Test(priority=5,testName="Test Case 7",enabled=true)	
	public void displyAndLayout_TC702() throws InterruptedException, IOException{

		//Reports field is added to Selected Tabs list and also added in the links available in top of sales force page 
		//and sales force chatter page and sales and marketing pages.
		//Click on Display & Layout link and select Customize My Tabs link. 
		//Select "Sales force Chatter" option from custom App: drop down. 
		//Select Reports tab from Available Tabs list. Click on >(Add) button. 
		driver.get("https://greendot5-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup");
		driver.findElement(By.id("DisplayAndLayout_font")).click();
		driver.findElement(By.xpath("//span[@id='CustomizeTabs_font' and text()='Customize My Tabs']")).click();
		threadSleep(2);
		driver.findElement(By.xpath("//select[@id='p4'] /option[text()='Salesforce Chatter']")).click();
		threadSleep(2);
		//driver.switchTo().frame("contactInfoContentId");
		driver.findElement(By.xpath("//option[@value='report' and text()='Reports']")).click();
		threadSleep(2);
		driver.findElement(By.id("duel_select_0_right")).click();
		threadSleep(2);
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@title='Save']")).click();
		Assert.assertEquals(driver.findElement(By.id("report_Tab")).getText(), "Reports","Report not added");	
         
        
        String error = driver.findElement(By.id("report_Tab")).getText();
		String errorDisplay="Reports";
		System.out.println(error);
		if(error.equals(errorDisplay)) {
			
			test.pass("displyAndLayout testcase executed successfully");  
			}
		
		else {
			test.fail("displyAndLayout testcase not executed successfully");
			test.addScreenCaptureFromPath(takeScreenshot());
		}    
   
	}



	@Test(priority=6,testName="Test Case 7",enabled=true)
	public void emailLinkSetting_TC703() throws InterruptedException, IOException{


		driver.findElement(By.id("userNavLabel")).click();
		//Verifying available menu 

		selectOptionInUserMenu (driver,"My Settings");
		threadSleep(1);
		verifyMySettingUserMenu( driver);
		mySettingAvailableOpt( driver,"Email");
		test.info("Email option selected from available menu");
		
		//Provide <EmailName> in Email Name field, <EmailAddress> in Email Address field,
		//Select automatic BCC radio button and click on save button
		//Given details are saved as default mail options and My settings page is displayed.
		driver.findElement(By.id("EmailSettings_font")).click();

		driver.findElement(By.id("sender_name")).clear();
		threadSleep(1);
		driver.findElement(By.id("sender_name")).sendKeys("Prajakta Bhandare");
		threadSleep(1);
		driver.findElement(By.xpath("//input[@id='sender_email']")).clear();
		driver.findElement(By.xpath("//input[@id='sender_email']")).sendKeys("prajaktabhandare@hotmail.com");
		threadSleep(1);
		driver.findElement(By.id("auto_bcc1")).click();
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@class='btn primary']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim(),("My Email Settings"));
		
		  String error = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
			String errorDisplay="My Email Settings";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass("New updated email-id is updated");	
				}
			
			else {
				test.fail("New updated email-id is not updated");
				test.addScreenCaptureFromPath(takeScreenshot());
			}    
	   
		}
		
	

	@Test(priority=7,testName="Test Case 7",enabled=true)
	public void dislayCalenderAndRemainder_704() throws InterruptedException{

		
		//Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
		driver.findElement(By.id("userNavLabel")).click();
		//Verifying available menu 

		selectOptionInUserMenu (driver,"My Settings");
		threadSleep(1);
		verifyMySettingUserMenu( driver);
		 test.info("Driver menu verified");
		 
		mySettingAvailableOpt( driver,"Calendar & Reminders");
		threadSleep(2);
		driver.findElement(By.id("CalendarAndReminders_font")).click();		

		Click(driver.findElement(By.id("Reminders_font")));
		threadSleep(2);
		//driver.findElement(By.id("testbtn")).click();	

		Click(driver.findElement(By.id("testbtn")));
		threadSleep(2);

		GetToLatestPopupWindow();
		String sampleEvent = driver.findElement(By.className("subject")).getText().trim();		
		Assert.assertTrue(sampleEvent.equals("Sample Event."), "Unable to open Test Reminder");
		test.pass("dislay Calende rAnd Remainder passed");

		driver.findElement(By.id("allBox")).click();
		threadSleep(2);
		driver.findElement(By.id("dismiss_all")).click();
       test.pass("dislayCalenderAndRemainder testcase executed successfully");
       
       
	}





	@Test(priority=8,testName="Test Case 8",enabled=true)
	public void developersConsole_TC8() throws InterruptedException {
		
		threadSleep(1);
		driver.findElement(By.id("userNavLabel")).click();
		//Verifying available menu 

		selectOptionInUserMenu (driver,"Developer Console");
		threadSleep(1);
		test.info("Developer Console selected");
		//Verifying available menu 
		VerifyMenu(driver);	
		// Store the current window handle		
		String currentWinHandle = driver.getWindowHandle();

		threadSleep(2);
		GetToLatestPopupWindow();
		String popupwindowhandle= driver.getWindowHandle();		
		driver.close();
		threadSleep(2);
		driver.switchTo().window(currentWinHandle);
		Set<String>  windowshandles = driver.getWindowHandles();// get all current windows
		
		// pop up window should not be present there 
		Assert.assertTrue(!windowshandles.contains(popupwindowhandle), "Pop up window not closed");
         test.pass("developersConsole_TC8 testcase executed successfully");
		
	}	



	@Test(priority=9,testName="Test Case 9",enabled=true)
	public void logout_TC9() throws InterruptedException {
		//Logging out page should display
		
	   //user menu for <user name> drop down is selected
		threadSleep(2);
		driver.findElement(By.id("userNavLabel")).click();
		threadSleep(2);
		VerifyMenu(driver);
		threadSleep(2);
		selectOptionInUserMenu (driver,"Logout");
		test.info("Logout page displyed");
		threadSleep(2);
		
		
        //Logout  of current sales force application  and https://login.salesforce.com/ page is displayed.
		Assert.assertEquals(driver.getCurrentUrl().trim(),"https://greendot5-dev-ed.my.salesforce.com/");

        test.pass("Logout page testcase successfully executed");


	}




}
