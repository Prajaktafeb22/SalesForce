package com.mypractice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountPg3 extends BaseSeleniumTestCase {

	/**
	 * @throws IOException 
	 * 
	 */
	@BeforeClass
	public void Init() throws IOException
	{
		init();
		InitiliseProp();
		pageName="AccountPage";
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

	@Test(priority=1,testName="Test Case 10",enabled=true)
	public void createAnAccountTC10() throws InterruptedException, IOException {


		driver.findElement(By.id("Account_Tab")).click();
		threadSleep(2);
		test.info("Account tab selected");
		driver.findElement(By.id("createNewLabel")).click();
		threadSleep(2);
		driver.findElement(By.xpath("//a[text()='Account']")).click();
		threadSleep(2);

		String AccountName = "prajaktabhandare" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());


		driver.findElement(By.id("acc2")).sendKeys(AccountName);
		threadSleep(1);
		driver.findElement(By.xpath("//input[@title='Save']")).click();

		Assert.assertEquals(AccountName,driver.findElement(By.xpath("//h2[@class='topName']")).getText());


		String error = driver.findElement(By.xpath("//h2[@class='topName']")).getText();

		System.out.println(error);
		if(error.equals(AccountName)) {

			test.pass("Account name is  updated ");	
		}

		else {
			test.fail("Account name is not updated");
			test.addScreenCaptureFromPath(takeScreenshot());
		} 

		test.pass("createAnAccountTC10 testcase executed successfullyh"); 



	}





	@Test(priority=2,testName="Test Case 11",enabled=true)
	public void createNewViewTC11() throws InterruptedException, IOException {

		driver.findElement(By.id("Account_Tab")).click();//*[@id="filter_element"]/div/span/span[2]/a[1]
		threadSleep(2);
		driver.findElement(By.xpath("//span[@id='createNewLabel']")).click();
		threadSleep(2);
		// driver.findElement(By.xpath("//a[@class='accountMru menuButtonMenuLink']")).click();
		driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")).click();
		threadSleep(2);
		//Accounts page is displayed with correct user name
		String myViewName = "prajaktabhandare" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys(myViewName);
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"devname\"]")).sendKeys(myViewName);
		threadSleep(2);

		driver.findElement(By.xpath("//select[@id='colselector_select_0']/option[@value='ACCOUNT.LAST_ACTIVITY']")).click();
		driver.findElement(By.xpath(" //a[@id='colselector_select_0_right']/img[@title='Add']")).click();
		threadSleep(2);
		driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")).click();

		Assert.assertEquals( driver.findElement(By.xpath("//select[@class='title']/option[@selected='selected']")).getText(),myViewName);   
		test.pass("Newely added View should be displayed in the account view list");

		String error = driver.findElement(By.xpath("//select[@class='title']/option[@selected='selected']")).getText();

		System.out.println(error);
		if(error.equals(myViewName)) {

			test.pass("New create view is updated");	
		}

		else {
			test.fail("New create view is not updated");
			test.addScreenCaptureFromPath(takeScreenshot());
		} 

		test.pass("createNewViewTC11 testcase executed successfullyh"); 



	}



	@Test(priority=3,testName="Test Case 12",enabled=true)
	public void editViewTC12() throws InterruptedException, IOException {
		//Account tab selected
		driver.findElement(By.id("Account_Tab")).click();
		threadSleep(1);
		//Edit selected account
		driver.findElement(By.xpath("//a[text()='Edit']")).click();
		threadSleep(1);
		//New account value set
		driver.findElement(By.xpath("//select[@id='fcol1']")).sendKeys("Account Name");
		//new value for account details
		driver.findElement(By.xpath("//select[@id='fop1']")).sendKeys("contains");
		driver.findElement(By.xpath("//input[@id='fval1']")).clear();
		driver.findElement(By.xpath("//input[@id='fval1']")).sendKeys("a");
		threadSleep(1);
		driver.findElement(By.xpath("//*[@id=\"colselector_select_0\"]/option[31]")).click();
		driver.findElement(By.xpath("//*[@id=\"colselector_select_0_right\"]/img")).click();
		threadSleep(1);
		//Access the current edit page display
		String demo= driver.findElement(By.xpath("//h2[@class='pageDescription'and text()=' Edit View']")).getText();
		System.out.println(demo);
		// Verify current page is Edited page or not
		Assert.assertEquals(  driver.findElement(By.xpath("//h2[@class='pageDescription'and text()=' Edit View']")).getText(),demo); 

		//saved the edited account details
		driver.findElement(By.xpath("//input[@class='btn primary']")).click();



		String error = driver.findElement(By.xpath("//select[@class='title']/option[@selected='selected']")).getText();

		System.out.println(error);
		if(error.equals(demo)) {

			test.pass("Edit view is updated");	
		}

		else {
			test.fail("Edit view is not updated");
			test.addScreenCaptureFromPath(takeScreenshot());
		} 

		test.pass("editViewTC12 testcase executed successfullyh"); 




	}




	@Test(priority=4,testName="Test Case 13",enabled=true)
	public void mergeAccountTC13() throws InterruptedException, IOException {
		//Account page link
		driver.findElement(By.id("Account_Tab")).click();
		threadSleep(2);

		String verifyAccountPage= driver.findElement(By.xpath("//h2[@class='pageDescription' and text()=' Home']")).getText();
		System.out.println(verifyAccountPage);
		// Verify current page is Account page or not
		Assert.assertEquals(  driver.findElement(By.xpath("//h2[@class='pageDescription' and text()=' Home']")).getText(),verifyAccountPage); 

		//clicked Merge Account link
		driver.findElement(By.xpath("//div[@class='lbBody']//a[text()='Merge Accounts']")).click();

		//Set account name to merge
		driver.findElement(By.xpath("//input[@id='srch']")).sendKeys("pra");
		threadSleep(2);

		//Find account by given name
		driver.findElement(By.xpath("//input[@value='Find Accounts']")).click();
		threadSleep(2);
		driver.findElement(By.id("cid0")).click();
		threadSleep(1);
		driver.findElement(By.id("cid1")).click();
		driver.findElement(By.id("cid2")).click();

		//Next button clicked
		driver.findElement(By.xpath("//input[@value=' Next '][@title='Next']")).click();

		//Verify Merge Page account successfully executed 

		String verifyMergePage= driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType'][ text()='Merge My Accounts']")).getText();
		System.out.println(verifyMergePage);

		// Verify Merge Account page is Merge page or not
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType'][ text()='Merge My Accounts']")).getText(),verifyMergePage); 

		String error =driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType'][ text()='Merge My Accounts']")).getText();

		System.out.println(error);
		if(error.equals(verifyMergePage)) {

			test.pass("Merge account is updated");	
		}

		else {
			test.fail("Merge account is not updated");
			test.addScreenCaptureFromPath(takeScreenshot());
		} 

		test.pass(" mergeAccountTC13 testcase executed successfullyh"); 




	}



	@Test(priority=5,testName="Test Case 14",enabled=true)
	public void createAccountReportTC14() throws InterruptedException, IOException {

		//Open account page
		driver.findElement(By.id("Account_Tab")).click();
		threadSleep(2);

		String verifyAccountPage= driver.findElement(By.xpath("//h2[@class='pageDescription' and text()=' Home']")).getText();
		System.out.println(verifyAccountPage + "Verified");
		// Verify current page is Account page or not
		Assert.assertEquals(  driver.findElement(By.xpath("//h2[@class='pageDescription' and text()=' Home']")).getText(),verifyAccountPage);

		//Accounts with last activity option not available so Active Accounts option selected
		//Active account option clicked
		driver.findElement(By.xpath("//div[@class='lbBody']//a[text()='Active Accounts']")).click();

		String verifyUnsaveReportPage= driver.findElement(By.xpath("//h2[@class='pageDescription'][text()='Unsaved Report']")).getText();
		System.out.println(verifyUnsaveReportPage + "Verified");
		// Verify current page is Unsaved Report page or not
		Assert.assertEquals(  driver.findElement(By.xpath("//h2[@class='pageDescription'][text()='Unsaved Report']")).getText(),verifyUnsaveReportPage);


		driver.findElement(By.xpath("//input[@type='text'][@id='ext-gen20']")).click();


		driver.findElement(By.id("ext-gen148")).click();
		threadSleep(2);
		//driver.findElement(By.xpath("//div[text()='Created Date' and @class='x-combo-list-item']")).click();

		//Select available option
		Click(driver.findElement(By.xpath("//div[text()='Created Date' and @class='x-combo-list-item']")));

		//clicked save button
		driver.findElement(By.id("ext-gen49")).click();
		
		String reportname="Recent Report4" + generateRandom();

		//Save Report pop up window displayed
		driver.findElement(By.id("saveReportDlg_reportNameField")).sendKeys(reportname);

		driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']")).click();
		threadSleep(1);

		driver.findElement(By.xpath("//button[@id='ext-gen284'and text()='Save']")).click();
		threadSleep(2);

		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText(), reportname);

		
		
		String error = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();

		System.out.println(error);
		if(error.equals(reportname)) {

			test.pass("Create account report is updated");	
		}

		else {
			test.fail("Create account report is not updated");
			test.addScreenCaptureFromPath(takeScreenshot());
		} 

		test.pass(" createAccountReportTC14 testcase executed successfullyh"); 



		
		
		
		

	}






}
