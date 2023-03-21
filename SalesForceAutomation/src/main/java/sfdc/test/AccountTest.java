package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sfdc.pages.AccountsPage;
import sfdc.pages.LoginPage;

public class AccountTest extends BaseTest{

	@BeforeTest
	public void setup() throws IOException {

		super.setup();
		accountsPage = new AccountsPage(driver);
		loginPage = new LoginPage(driver );
		loginPage.loginToApp(driver, selectEnvironment("prod"));
	}


	@Test()
	public void createAnAccountTC10(Method name) throws InterruptedException, IOException {
		test= extent.createTest(name.getName());
		loginPage.loginToApp(driver, selectEnvironment("prod"));
		threadSleep(2);
		//reusableUtils.clickOnElement(accountsPage.Account);
		accountsPage.Accounts.click();
		threadSleep(2);
		
		reusableUtils.clickOnElement(accountsPage.Create_New);
		//accountsPage.Create_New.click();
		test.info("Account tab selected");
		threadSleep(2);
		reusableUtils.clickOnElement(accountsPage.Account);
		//accountsPage.Account.click();
		threadSleep(2);

		String AccountName = "prajaktabhandare" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		accountsPage.AccountName.sendKeys(AccountName);
		threadSleep(1);
		accountsPage.Save.click();
		Assert.assertEquals(AccountName,driver.findElement(By.xpath("//h2[@class='topName']")).getText());
		String error = driver.findElement(By.xpath("//h2[@class='topName']")).getText();

		System.out.println(error);
		if(error.equals(AccountName)) {

			test.pass("Account name is  updated ");	
			test.pass("createAnAccountTC10 testcase executed successfullyh"); 
		}

		else {
			test.fail("Account name is not updated");
			test.addScreenCaptureFromPath(takeScreenshot());
		} 

	}



 	@Test
	public void createNewViewTC11(Method name) throws InterruptedException, IOException {
		test= extent.createTest(name.getName());
		//Account tab selected
		accountsPage.Accounts.click();

		threadSleep(2);
		accountsPage.Create_New.click();
		test.info("Account tab selected");
		threadSleep(2);
		//Create new view selected
		accountsPage.CreateNewView.click();
		threadSleep(2);
		//Accounts page is displayed with correct user name
		String myViewName = "prajaktabhandare" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		//new view name set
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
	public void editViewTC12(Method name) throws InterruptedException, IOException {
		//Account tab selected
		accountsPage.Accounts.click();
		threadSleep(3);
		//Edit selected account
		accountsPage.Edit.click();
		threadSleep(2);
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
		//Account tab selected
		accountsPage.Accounts.click();
		threadSleep(3);

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
}