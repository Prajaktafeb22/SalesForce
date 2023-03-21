package com.mypractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SFDCLoginTestCasepg1 extends BaseSeleniumTestCase{
	
	@BeforeClass
	public void Init() throws IOException
	{
		init();
		InitiliseProp();
		pageName="FirefoxVersion Page";
		InitReport();
		driver.get("https://login.salesforce.com/");
		//Login();
	}
	
	/* @AfterClass
	public void teardown()
	{
		driver.quit();
	}
	*/
	
	
	//Login Page 
	@Test(priority=1,testName="Test Case 1",enabled=true)	
	public void NavigateToSdfc() throws InterruptedException, IOException
	{	
		
	    driver.get("https://login.salesforce.com/");
	    test.info("login page launched");
	    WebElement username=driver.findElement(By.id("username"));
		BaseSeleniumTestCase.enterText(username, prop.getProperty("username"));
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("Login")).click();
	    threadSleep(3);
	    
	  //  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='error']")).getText(), "Please enter your password.","Password enter message not displayed");    
		test.info("Error message should be displayed");	
		 threadSleep(3);
		String error = driver.findElement(By.xpath("//div[@id='error'][@class='loginError']")).getText();
		String errorDisplay="Please enter your password.";
		System.out.println(error);
		 threadSleep(2);
		if(error.equals(errorDisplay)) {
			
			test.pass("TestCase NavigateToSdfc executed successfully");
			}
		
		else {
			test.fail("TestCase NavigateToSdfc not executed successfully");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
		
		
	}
	
	@Test(priority=2,testName="Test Case 2",enabled=true)	
	public void WelcomeToSdfc() throws InterruptedException, IOException
	{
		 
		//Getting password and user name from  the file.
		InitiliseProp();
		Login();
		driver.get("https://greendot5-dev-ed.my.salesforce.com/setup/forcecomHomepage.apexp?setupid=ForceCom");
		threadSleep(2);
		//Runs successfully when condition is true.
	    Assert.assertTrue(isloadComplete(driver), "Login page is not display");
	    test.pass("WelcomeToSdfc() executed successfully");
		
	}
	
	
	@Test(priority=3,testName="Test Case 3",enabled=true)	
	public void CheckRememberMe()throws InterruptedException {
				 
		
		driver.get("https://login.salesforce.com/");

	    //Base class Sendkeys function used
		WebElement username=driver.findElement(By.id("username"));
		BaseSeleniumTestCase.enterText(username, prop.getProperty("username"));
		WebElement password=  driver.findElement(By.id("password"));
		BaseSeleniumTestCase.enterText(password, prop.getProperty("password"));
		
		//Base class click on element function used
	    WebElement rememberbutton = driver.findElement(By.xpath("//input[@id='rememberUn']"));
	    BaseSeleniumTestCase.clickOnElement(rememberbutton);
	    
	    test.info("Remember me option verified");
	    WebElement loginbutton = driver.findElement(By.id("Login"));
	    BaseSeleniumTestCase.clickOnElement(loginbutton);
	    
	    //Base class function click on element used
	    WebElement usermenuoption = 	driver.findElement(By.id("userNavLabel"));
	    BaseSeleniumTestCase.clickOnElement(usermenuoption);
		
	    
	    //regular click function use
	    //driver.findElement(By.id("userNavLabel")).click();
		threadSleep(2);
		driver.findElement(By.xpath("//a[@href='/secur/logout.jsp' and text()='Logout']")).click();
		//switch to default
		driver.switchTo().defaultContent();
		threadSleep(1);
		
		//Verify User name
		Assert.assertEquals(prop.getProperty("username"),driver.findElement(By.id("username")).getAttribute("value"));	
		test.pass("Validate the user name displayed in user name field");
		
	}

	
	@Test(priority=4,testName="Test Case 4",enabled=true)	
	public void forGotPassword1() throws InterruptedException, IOException {

		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("forgot_password_link")).click();
		
		//assertion for checking the forgot password page is displayed or not 
		Assert.assertTrue(driver.findElement(By.id("header")).getText().contains("Forgot Your Password"), "Forgot password page not displayed");
		test.info("Forgot password page should displayed");
		
		driver.findElement(By.xpath("//*[@id=\"un\"]")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.id("continue")).click();
        threadSleep(1);
        
       // Password reset message page is displayed. 
       // An email associated with the user name account is sent.
        Assert.assertEquals("We've sent you an email with a link to finish resetting your password.",driver.findElement(By.xpath("//div[@id='forgotPassForm']//p[@class='senttext mb12']")).getText());
        test.info("An email associated with the user name account is sent.");
        
        String error = driver.findElement(By.xpath("//div[@id='forgotPassForm']//p[@class='senttext mb12']")).getText();
		String errorDisplay="We've sent you an email with a link to finish resetting your password.";
		System.out.println(error);
		if(error.equals(errorDisplay)) {
			
			test.pass("TestCase Forgot password executed successfully");
			}
		
		else {
			test.fail("TestCase Forgot password not executed successfully");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
		
        
        
	}
	
	
	
	
	
	@Test(priority=5,testName="Test Case 5",enabled=true)	
	public void forGotPassword2() throws InterruptedException, IOException {
		init();
		InitiliseProp();
		driver.get("https://login.salesforce.com/");
		test.info("Login page successfully launched");
		
	     //enter invalid user name and password
	     driver.findElement(By.id("username")).sendKeys("ghvfjhvj@hfghf.com");
	     driver.findElement(By.id("password")).sendKeys("ghvfjhvj");
	     threadSleep(1);
	     driver.findElement(By.id("Login")).click();
	     test.info("Invalid user name and password entered for verify forgot password function");
	     
	     Assert.assertEquals(driver.findElement(By.xpath("//div[@class='loginError'][@id='error']")).getText(), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.");

	     String error = driver.findElement(By.xpath("//div[@class='loginError'][@id='error']")).getText();
			String errorDisplay="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
			System.out.println(error);
			if(error.equals(errorDisplay)) {
				
				test.pass("TestCase Forgot password executed successfully");
				}
			
			else {
				test.fail("TestCase Forgot password not executed successfully");
				test.addScreenCaptureFromPath(takeScreenshot());
			}
			
	    
	}	
}




