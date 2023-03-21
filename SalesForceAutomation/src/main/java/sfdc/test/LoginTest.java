package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.TestListeners;

import sfdc.pages.LoginPage;


@Listeners(TestListeners.class)
public class LoginTest extends  BaseTest{
	
	@BeforeMethod
	public void launchApp()  throws IOException  {
		
		pageName  = "LoginTest";
		//driver.get((selectEnvironment("prod")));
		startTime = System.currentTimeMillis();
	}
	
	@AfterMethod
	public void afterConfigs() {
		endTime = System.currentTimeMillis();
		logger.info("TimeTaken for test: "+(endTime-startTime));
		
		
	}
	
	
	
	@Test
	public void loginErrorMessageTC01() throws IOException, InterruptedException {
		logger.info("Started Executing");		
		loginPage.LauchPage(driver, selectEnvironment("prod"));		
		test = extent.createTest("loginErrorMessageTC01");
		threadSleep(2);
	   if(reusableUtils.isElementDispalyed(loginPage.username, driver)) {
			logger.info("Username text box visible");
			loginPage.username.sendKeys(du.readAccountProperties("username"));
			test.info("Username entered");
			loginPage.password.clear();
			if(reusableUtils.waitForElementClikable(driver, loginPage.loginButton)) {
				loginPage.loginButton.click();
				loginPage.verifyErrorMessage();
				Assert.assertTrue(loginPage.verifyErrorMessage(),"fail to verify Error Message");
				test.pass("loginErrorMessageTC01"); 
				logger.info("test case executed");
			}
			else {
				System.out.println("Login button is not clickable");
				test.addScreenCaptureFromPath(takeScreenshot());
			}
			
		}else {
			System.out.println("UserName element is not visible");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
	}
	
	
	
	
	@Test(priority=2,testName="Test Case 2",enabled=true)	
	public void WelcomeToSdfc() throws InterruptedException, IOException
	{
		
		logger.info("Welcome to SFDC page");
		loginPage = new LoginPage(driver );
		loginPage.loginToApp(driver, selectEnvironment("prod"));
		//Getting password and user name from  the file.
		//InitiliseProp();
		//Login();
		driver.get("https://greendot5-dev-ed.my.salesforce.com/setup/forcecomHomepage.apexp?setupid=ForceCom");
		threadSleep(2);
		//Runs successfully when condition is true.
	    Assert.assertTrue(isloadComplete(driver), "Login page is not display");
	    test.pass("WelcomeToSdfc() executed successfully");
	}
	
	
	@Test(priority=3,testName="Test Case 3",enabled=true)	
	public void CheckRememberMe()throws InterruptedException, IOException {
				 
		
		driver.get("https://login.salesforce.com/");
		loginPage.username.sendKeys(du.readAccountProperties("username"));
		loginPage.password.sendKeys(du.readAccountProperties("password"));
		//Base class click on element function used
	    WebElement rememberbutton = driver.findElement(By.xpath("//input[@id='rememberUn']"));
	    BaseTest.clickOnElement(rememberbutton);
	   
	    test.info("Remember me option verified");
	    WebElement loginButton = driver.findElement(By.id("Login"));
	    BaseTest.clickOnElement(loginButton);
	    //Base class function click on element used
	    WebElement usermenuoption = driver.findElement(By.id("userNavLabel"));
	    BaseTest.clickOnElement(usermenuoption);
		threadSleep(2);
		driver.findElement(By.xpath("//a[@href='/secur/logout.jsp' and text()='Logout']")).click();
		//switch to default
		driver.switchTo().defaultContent();
		threadSleep(1);
		
		//Verify User name
		Assert.assertEquals(du.readAccountProperties("username"),driver.findElement(By.id("username")).getAttribute("value"));	
		test.pass("Validate the user name displayed in user name field");
		
	}

	
	@Test(priority=4,testName="Test Case 4",enabled=true)	
	public void forGotPassword1(Method name) throws InterruptedException, IOException {

		test.info(pageName);
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("forgot_password_link")).click();
		logger.info("Forgot password link clicked");
		//assertion for checking the forgot password page is displayed or not 
		String forgotpasswordcheck =driver.findElement(By.id("header")).getText();
		
		if(forgotpasswordcheck.equals("Forgot Your Password")) {
			
			System.out.println("Forgot password page is displayed");
			test.info("Forgot password page is passed successfully");
			
		}
		else {
			
			System.out.println("Forgot password page not displayed");
			test.info("Forgot password page is not passed successfully");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
		
		//Assert.assertTrue(driver.findElement(By.id("header")).getText().contains("Forgot Your Password"), "Forgot password page not displayed");
		//test.info("Forgot password page should displayed");
		
		driver.findElement(By.xpath("//*[@id=\"un\"]")).sendKeys(du.readAccountProperties("username"));
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
	public void forGotPassword2(Method name) throws InterruptedException, IOException {
		test.info(pageName);
		
		loginPage = new LoginPage(driver );
	   
				
		driver.get("https://login.salesforce.com/");
		threadSleep(2);
		// loginPage.loginToApp(driver, selectEnvironment("prod"));
		//loginPage.username.sendKeys(du.readAccountProperties("username"));
		//loginPage.password.sendKeys(du.readAccountProperties("password"));
		//driver.get("https://login.salesforce.com/");
		test.info("Login page successfully launched");
		driver.findElement(By.id("clear_link")).click();
		threadSleep(1);
		
		logger.info(name);
	     //enter invalid user name and password
		threadSleep(2);
	     driver.findElement(By.id("username")).sendKeys("ghvfjhvj@hfghf.com");
	     driver.findElement(By.id("password")).sendKeys("ghvfjhvj");
	     threadSleep(1);
	     driver.findElement(By.id("Login")).click();
	 	threadSleep(2);
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

