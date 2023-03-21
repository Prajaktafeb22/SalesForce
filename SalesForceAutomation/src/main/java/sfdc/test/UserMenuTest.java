package sfdc.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sfdc.pages.LoginPage;
import sfdc.pages.UserMenuPage;
import sfdc.utilities.ReusableUtils;


public class UserMenuTest extends BaseTest {
	
	@BeforeTest
	public void setup() throws IOException {
		
		super.setup();
		userMenuPage = new UserMenuPage(driver);
		loginPage.loginToApp(driver, selectEnvironment("prod"));
	}
	
	
	@Test
	public void selectUserMenuDropDownTC05(Method name) throws IOException {
		
		test= extent.createTest(name.getName());
		loginPage.loginToApp(driver, selectEnvironment("prod"));
		test.info("Log in to the app");
		Assert.assertTrue(userMenuPage.verifyUserName(),"Username verification failed");
		userMenuPage.userMenu.click();
		if(reusableUtils.isElementDispalyed(userMenuPage.userMenuDropDown, driver)) {
			boolean verify = userMenuPage.verifyUserMenuItems(driver);
			Assert.assertTrue(verify,"Failed to verify usermenu items");
			test.pass(name.getName());
		}else {
			test.fail("Usermenu item is not displayed");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
		
		
	}
	
	
	@Test(priority=2,testName="Test Case 6",enabled=true)	
	public void selectMyProfile_TC6() throws InterruptedException {

		driver.findElement(By.id("userNavLabel")).click();

		//Verifying available menu 
		userMenuPage.verifyUserMenuItems(driver);
		test.info("Verify available user menu.");

		//selecting provided menu
		reusableUtils.selectOptionInUserMenu(driver,"My Profile") ;
		threadSleep(2);

		//Edit profile pop up window is displayed with contact and About tabs to edit. 
		reusableUtils.clickOnElement(userMenuPage.editProfile);//Select Edit profile
		threadSleep(4);
		
		driver.switchTo().frame("contactInfoContentId");
		threadSleep(2);
		//Select about tab
		reusableUtils.clickOnElement(userMenuPage.aboutTab);
		//clear the last name 
		reusableUtils.clearElement(userMenuPage.editProfileLastName);
		threadSleep(2);
		reusableUtils.insertText(userMenuPage.editProfileLastName, "Bhandare123");
		threadSleep(2);
		//save edited last name
        reusableUtils.clickOnElement(userMenuPage.saveAllButton);
		////UserProfilePage with changed <last name> is displayed
		test.info("UserProfilePage with changed last name is displayed");

		String verify =driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();	
		if(verify.contains("Bhandare123")) {
			System.out.println("UserProfilePage with changed <lastname> is displayed");
			logger.info("UserProfilePage is verified");
		}else {
			logger.info("UserProfilePage is not updated");
		
		}
		//<text> entered should be displayed on the page
		threadSleep(2);
		reusableUtils.clickOnElement(userMenuPage.postLink);
		threadSleep(3);
		
		//reusableUtils.clickOnElement(userMenuPage.postLinkTextArea);
		driver.findElement(By.id("cke_publisherRichTextEditor")).click();
		threadSleep(1);
		WebElement currentElement = driver.switchTo().activeElement();
		threadSleep(1);
		//Text inserted in text area
		currentElement.sendKeys("Welcome to Post text area");
		threadSleep(1);
		currentElement.sendKeys("Welcome to post text area");
		
		threadSleep(3);
		reusableUtils.clickOnElement(userMenuPage.sharePostLink);
		
		threadSleep(2);
		//File link clicked
		reusableUtils.clickOnElement(userMenuPage.fileLink);
		threadSleep(1);
		//Upload file selected
		reusableUtils.clickOnElement(userMenuPage.uploadFileLink);
		threadSleep(2);
		
		//reusableUtils.clickOnElement(userMenuPage.chooseFileButton);
		threadSleep(1);
		
		//File selected to upload
		driver.findElement(By.id("chatterFile")).sendKeys("C:\\Users\\DECOMM\\Desktop\\111.txt"); 
		threadSleep(4);
		
		//Share uploaded file 
		threadSleep(1);
		
		reusableUtils.clickOnElement(userMenuPage.shareFileButton);
		
		threadSleep(1);

		//Verify file uploded successfully or not
		String ContentUploaded =driver.findElements(By.className("feeditembodyandfooter")).get(0).getText(); 
		System.out.println("starts here"+ContentUploaded);
		String action ="posted a file.";
		String fileName ="posted a file.";
		Assert.assertTrue(ContentUploaded.contains(action) && ContentUploaded.contains(fileName),
				"Input File not uploaded");

		test.info("Check input file uploaded successfully");
		
		
	}
	
	
	
	

	@Test(priority=3,testName="Test Case 7",enabled=true)	
	public void selectMyProfileSub_TC7() throws InterruptedException, IOException {

       //Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
		threadSleep(1);
		reusableUtils.clickOnElement(userMenuPage.userMenu);
		//driver.findElement(By.id("userNavLabel")).click();
		//My profile link selected
		reusableUtils.selectOptionInUserMenu(driver,"My Profile") ;
		threadSleep(2);	

		driver.findElement(By.id("publisherAttachContentPost")).click(); 
		threadSleep(3);
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
		
		
		reusableUtils.clickOnElement(userMenuPage.userMenu);
		
		//Verifying available menu 
		userMenuPage.verifyUserMenuItems(driver);
		test.info("Verify available menu");

		//selecting provided menu
		reusableUtils.selectOptionInUserMenu(driver,"My Settings") ;
		test.info("My setting option selected");

		threadSleep(1);
		//Personal menu selected
		reusableUtils.clickOnElement(userMenuPage.personalMenuLink);
		
		threadSleep(1);
		//Login history checked
		reusableUtils.clickOnElement(userMenuPage.loginHistoryLink);
		
		threadSleep(2);
		//Option selected
		reusableUtils.clickOnElement(userMenuPage.optionLink);
		
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
		
		//Display and layout link selected
		reusableUtils.clickOnElement(userMenuPage.displayAndLayoutLink);
		
		//driver.findElement(By.id("DisplayAndLayout_font")).click();
		
		//Customize my tabs selected
		reusableUtils.clickOnElement(userMenuPage.customMyTabsLink);
		//driver.findElement(By.xpath("//span[@id='CustomizeTabs_font' and text()='Customize My Tabs']")).click();
		threadSleep(2);
		
		reusableUtils.clickOnElement(userMenuPage.salesForseChatter);
		//driver.findElement(By.xpath("//select[@id='p4'] /option[text()='Salesforce Chatter']")).click();
		threadSleep(2);
		//driver.switchTo().frame("contactInfoContentId");
		
		reusableUtils.clickOnElement(userMenuPage.reportOption);
		
		//driver.findElement(By.xpath("//option[@value='report' and text()='Reports']")).click();
		threadSleep(2);
		//Option added to the left side
		reusableUtils.clickOnElement(userMenuPage.addLeftOption);
		//driver.findElement(By.id("duel_select_0_right")).click();
		threadSleep(2);
		
		reusableUtils.clickOnElement(userMenuPage.saveButton);
		//driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@title='Save']")).click();
		threadSleep(3);
		
		//Need to add n remove report tab manually to run test case without error
		//Assert.assertEquals(driver.findElement(By.id("report_Tab")).getText(), "Reports","Report not added");	
         
		//select[@id='duel_select_1'][@name='duel_select_1']
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

		//User menu selected
		reusableUtils.clickOnElement(userMenuPage.userMenu);
		//Verifying available menu 
		reusableUtils.selectOptionInUserMenu(driver,"My Settings") ;
		threadSleep(1);
		userMenuPage.verifyUserMenuItems(driver);
		userMenuPage.verifyMySettingUserMenu( driver);
		
		//Email menu selected
		userMenuPage.mySettingAvailableOpt( driver,"Email");
		test.info("Email option selected from available menu");
		
		//Provide <EmailName> in Email Name field, <EmailAddress> in Email Address field,
		//Select automatic BCC radio button and click on save button
		//Given details are saved as default mail options and My settings page is displayed.
		
		reusableUtils.clickOnElement(userMenuPage.myEmailSettingTAb);
		reusableUtils.clickOnElement(userMenuPage.emailNameSetting);
		
		threadSleep(1);
		reusableUtils.insertText(userMenuPage.emailNameSetting, "Prajakta Bhandare");
		
		threadSleep(1);
		
		reusableUtils.clearElement(userMenuPage.emailAddressField);
		reusableUtils.insertText(userMenuPage.emailAddressField, "prajaktabhandare@hotmail.com");
		threadSleep(1);
		reusableUtils.clickOnElement(userMenuPage.bccRadioButton);
		
		reusableUtils.clickOnElement(userMenuPage.saveEmailSetting);
		//Verify email setting
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText().trim(),("My Email Settings"));
		 String error = (userMenuPage.myEmailSettingHeader).getText();
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
		
		       reusableUtils.clickOnElement(userMenuPage.userMenu);
				//Verifying available menu 

				reusableUtils.selectOptionInUserMenu(driver, "My Settings");
				threadSleep(1);
				userMenuPage.verifyMySettingUserMenu( driver);
				 test.info("Driver menu verified");
				 
				userMenuPage.mySettingAvailableOpt( driver,"Calendar & Reminders");
				threadSleep(2);
				
				reusableUtils.clickOnElement(userMenuPage.activityReminderLink);	

				Click(driver.findElement(By.id("Reminders_font")));
				threadSleep(2);

				Click(userMenuPage.oprnATestReminderButton);
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
		reusableUtils.clickOnElement(userMenuPage.userMenu);
		//Verifying available menu 
		userMenuPage.verifyUserMenuItems(driver);
		reusableUtils.selectOptionInUserMenu(driver,"Developer Console");
		threadSleep(1);
		test.info("Developer Console selected");
		reusableUtils.VerifyMenu(driver);
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
		reusableUtils.clickOnElement(userMenuPage.userMenu);
		threadSleep(2);
		userMenuPage.verifyUserMenuItems(driver);
		threadSleep(2);
		reusableUtils.selectOptionInUserMenu(driver,"Logout");
		//reusableUtils.opportunitiesAvailableOpt(driver,"Logout");
		test.info("Logout page displyed");
		threadSleep(2);
		
		
        //Logout  of current sales force application  and https://login.salesforce.com/ page is displayed.
		Assert.assertEquals(driver.getCurrentUrl().trim(),"https://greendot5-dev-ed.my.salesforce.com/");

        test.pass("Logout page testcase successfully executed");


	}

}
